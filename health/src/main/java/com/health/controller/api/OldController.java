package com.health.controller.api;
/*package com.ayukoash.controller.api.custodyService;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.custodywms.controller.api.custodyService.entity.CustodyItems;
import com.custodywms.controller.api.custodyService.entity.CustodyShipConf;
import com.custodywms.controller.api.custodyService.entity.CustodyShipConfDetails;
import com.custodywms.controller.api.custodyService.entity.CustodyStatus;
import com.custodywms.controller.api.custodyService.entity.Request;
import com.custodywms.controller.api.custodyService.entity.Response;
import com.custodywms.controller.api.custodyService.entity.ResponseStatus;
import com.custodywms.controller.api.custodyService.service.CustodyItemsService;
import com.custodywms.controller.api.custodyService.service.CustodyShipConfService;
import com.custodywms.controller.api.custodyService.service.CustodyStatusService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/custody")
@Api(value="Custody Services Controller", description="Operations related to Custody Services")
public class CustodyServicesController {

	@Autowired
	private CustodyItemsService service;

	@Autowired
	private CustodyStatusService statusService;

	@Autowired
	private CustodyShipConfService confService;



	@ApiOperation(value = "Inserting the Order details and order status ")
	@PostMapping("/orderstatus")
	public ResponseStatus saveOrderStatus(
			@ApiParam(value = "Inserting the Order details and order status ", required = true)
			@Valid @RequestBody CustodyStatus req) {

		ResponseStatus res=new ResponseStatus();
		if(req!=null) {

			if(req.getClientCode()==null || req.getClientCode().equals("")) {
				res.setResult("Request failed, Please check the Client code in the request ");
			}
			else if(req.getDisplayOrderNumber()==null || req.getDisplayOrderNumber().equals("")) {
				res.setResult("Request failed, Please check the display order number in the request ");
			}
			else if(req.getOrderNumber()==null || req.getOrderNumber().equals("")) {
				res.setResult("Request failed, Please check the order number in the request ");
			}
			else if(req.getStatus()==null || req.getStatus().equals("")) {
				res.setResult("Request failed, Please check the status in the request ");
			}
			else if(req.getWareHouseId()==null || req.getWareHouseId().equals("")) {
				res.setResult("Request failed, Please check the ware house id in the request ");
			}
			else {
				Boolean orderexists=false;
				try {
					orderexists=statusService.checkorder(Long.parseLong(req.getOrderNumber()));
					if(orderexists) {
						req.setCreatedDate(new Date());
						CustodyStatus resEntity=statusService.save(req);
						res.setResult("Status for Order Number "+resEntity.getDisplayOrderNumber()+" Saved Successfully ");
						
					}else {
						res.setResult("Order doesn't Exists ");
						
					}
				}catch (Exception e) {
					res.setResult("Please check order number ");
				}

			}
		}else {
			res.setResult("Request failed, Please check the parameters in the request ");

		}
		return res;
	}

	@ApiOperation(value = "Saving Custody Item ")
	@PostMapping("/savecustodyitems")
	public Response saveCustodyItem(
			@ApiParam(value = "Save Custody Items ", required = true)
			@Valid @RequestBody Request req) {
		Response res=new Response();
		res.setResult(new ArrayList<String>());
		if(req !=null && req.getItem()!=null && req.getItem().size()>0) {
			for (CustodyItems item : req.getItem()) {
				if(item.getItemCode()==null || "".equals(item.getItemCode())) {
					res.getResult().add("Not saved-Item Code required") ;
				}
				else {
					service.save(item);
					res.getResult().add("Item code "+item.getItemCode()+" Saved Successfully ") ; 
				}
			}
		}
		return res;
	}

	@ApiOperation(value = "Delete Custody item")
	@PostMapping("/deletecustodyitem")
	public Response deletecustodyitem(
			@ApiParam(value = "Delete Custody item", required = true)
			@Valid @RequestBody Request request){
		Response res=new Response();
		res.setResult(new ArrayList<String>());
		if(request==null ||request.getItemcode()==null ) {
			res.getResult().add( "Item code not found");
		}
		else {
			CustodyItems item=service.findByItemCode(request.getItemcode());
			if(item==null ||item.getItemCode()==null ) {
				res.getResult().add( "Item code not found");
			}
			else {
				service.delete(item);
				res.getResult().add( "item Deleted Succesfuly");
			}

		}
		return res;


	}


	@ApiOperation(value = "Saving Ship confirmation details")
	@PostMapping("/saveshipconfirmation")
	public ResponseStatus saveshipconfirmation(
			@ApiParam(value = "Saving Ship confirmation details", required = true)
			@Valid @RequestBody CustodyShipConf req) {
		ResponseStatus res=new ResponseStatus();
		if(req !=null) {
			if(req.getWarehouseid()==null && req.getWarehouseid().equals("")) {
				res.setResult("Request failed, Please check the Warehouse parameters in the request ");
			}
			else if(req.getOrderNumber()==null && req.getOrderNumber().equals("")) {
				res.setResult("Request failed, Please check the Order Number parameters in the request ");
			}
			else {
				 boolean  stats=false;
				if(req.getWarehouseid()!=null && !req.getWarehouseid().equals("") && req.getOrderNumber()!=null && !req.getOrderNumber().equals("")) {
					stats=statusService.findwarehouseandorderno(req.getWarehouseid(),req.getOrderNumber());
				}
				if(!stats) {
					res.setResult("Request failed, No order found for the mentioned Warehouse  ");
				}else {
					for (CustodyShipConfDetails det : req.getShipdetailsList()) {
						if(det!=null) {
							det.setCustodyShipConf(req);
							det.setCreatedDate(new Date());
						}
					}
					try {
						req.setCreatedDate(new Date());
						CustodyShipConf ent=confService.save(req);
						res.setResult("Success!! Request saved with confirmation id "+ent.getId());
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				
				
			}
			
			
			

		}
		else {
			res.setResult("Request failed, Please check the parameters in the request ");

		}
		return res;
	}



}
*/