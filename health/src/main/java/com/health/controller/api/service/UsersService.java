package com.health.controller.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.controller.api.dataexchange.response.UserResponse;
import com.health.controller.api.entity.Menus;
import com.health.controller.api.entity.Roles;
import com.health.controller.api.entity.Users;
import com.health.controller.api.repository.UsersRepository;

@Service
public class UsersService extends GenericService<Users, Long>{

	@Autowired
	private UsersRepository repository;

	
	@Autowired
	public UsersService(UsersRepository repository) {
		super(repository);
		this.repository = repository;
	}


	public Long verifyUser(String userName, String password) {
		return repository.verifyUser(userName,password);
		
	}


	public UserResponse fetchUserdetails(Long userid, MenusService menusService) {
		Users user=super.findById(userid);
		UserResponse res=new UserResponse(user.getId(),user.getUsername(),user.getRoles(),getMenuTree(user.getRoles(),menusService));
		return res;
	}
	
	

	public List<Menus> getMenuTree(List<Roles> roles, MenusService menusService){
		List<Menus> finalMenus=new ArrayList<>();
		List<Menus> menusBase = menusService.menusUnderMainMenu(0L);
		List<Menus> menuLNotBase = menusService.selectAllNotBase();
		for (Menus menu : menusBase) {
			List<Menus> menus = iterateMenus(menuLNotBase, menu.getId(),roles,menusService);
			if(menus!=null && menus.size()>0){
				  menu.setSubmenus(menus);
				  finalMenus.add(menu);
			}
				
		}
		return  finalMenus;
	}

	public List<Menus> iterateMenus(List<Menus> menuVoList,Long pid, List<Roles> roles, MenusService menusService){
		List<Menus> result = new ArrayList<Menus>();
		for (Menus menu : menuVoList) {
			//Get the id of the menu
			Long menuid = menu.getId();
			//Get the parent id of the menu
			Long parentid = menu.getParentid();
			if(parentid.equals(pid)){
				//Recursively query the submenu of the current submenu
				List<Menus> iterateMenu = iterateMenus(menuVoList,menuid,roles,menusService);
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
