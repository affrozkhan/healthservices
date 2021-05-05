package com.health.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.controller.api.dataexchange.request.LoginRequest;
import com.health.controller.api.dataexchange.response.MessageResponse;
import com.health.controller.api.entity.Menus;
import com.health.controller.api.entity.Roles;
import com.health.controller.api.entity.Users;
import com.health.controller.api.service.MenusService;
import com.health.controller.api.service.UsersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestController
@RequestMapping("/health/webui")
@Api(value="Web Service Controller", description="Operations related to Web Service Controller")
public class WebController {


	@Autowired
	private UsersService userservice;


	@Autowired
	private MenusService menusService;


	@ApiOperation(value = "Verify user credentials to login")
	@PostMapping("/verifyuser")
	public ResponseEntity<Object> verifyAndGetUserDetails(
			@ApiParam(value = "Get all appointments by userid", required = true)
			@Valid @RequestBody LoginRequest req) {
		if(req!=null && req.getUserName()!=null && req.getPassword()!=null){
			Long userid=userservice.verifyUser(req.getUserName(),req.getPassword());
			if(userid!=null && userid!=0L){
				Users user=userservice.findById(userid);
				user.setMenus(getMenuTree(user.getRoles()));
				return new ResponseEntity<>(user, HttpStatus.OK);

			}else{
				return new ResponseEntity<>(new MessageResponse("Please verify Username and Password", "HCE-100"), HttpStatus.UNAUTHORIZED);	
			}

		}else{
			return new ResponseEntity<>(new MessageResponse("Please verify Username and Password", "HCE-100"), HttpStatus.UNAUTHORIZED);
		}
	}



	public List<Menus> getMenuTree(List<Roles> roles){
		List<Menus> finalMenus=new ArrayList<>();
		List<Menus> menusBase = menusService.menusUnderMainMenu(0L);
		List<Menus> menuLNotBase = menusService.selectAllNotBase();
		for (Menus menu : menusBase) {
			List<Menus> menus = iterateMenus(menuLNotBase, menu.getId(),roles);
			if(menus!=null && menus.size()>0){
				  menu.setSubmenus(menus);
				  finalMenus.add(menu);
			}
				
		}
		return  finalMenus;
	}

	public List<Menus> iterateMenus(List<Menus> menuVoList,Long pid, List<Roles> roles){
		List<Menus> result = new ArrayList<Menus>();
		for (Menus menu : menuVoList) {
			//Get the id of the menu
			Long menuid = menu.getId();
			//Get the parent id of the menu
			Long parentid = menu.getParentid();
			if(parentid.equals(pid)){
				//Recursively query the submenu of the current submenu
				List<Menus> iterateMenu = iterateMenus(menuVoList,menuid,roles);
				if(iterateMenu!=null && iterateMenu.size()>0){
					menu.setSubmenus(iterateMenu);	
				}
				
				if(menu!=null && menu.getId()!=null){
					if(menu.getAction()!=null && !"".equals(menu.getAction())){
						Boolean hasaccess=false;
						for (Roles role : roles) {
							hasaccess=menusService.checkmenuaccess(menu.getId(),role.getId());
							if(hasaccess){
								result.add(menu);
								break;
							}
						}
					}else{
						if((menu.getAction()==null || "".equals(menu.getAction())&& menu.getSubmenus()!=null && menu.getSubmenus().size()>0)){
							result.add(menu);
						}
						
					}
					
					
				}
			}
		}
		return result;
	}
}
