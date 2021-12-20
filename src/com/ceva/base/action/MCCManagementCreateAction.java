package com.ceva.base.action;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cave.base.dao.BranchCreationDao;
import com.cave.base.dao.MCCManagementCreationDao;
import com.ceva.base.common.dao.AgentDAO;
import com.ceva.base.common.dao.CampaignManagementDAO;
import com.ceva.base.common.dto.RequestDTO;
import com.ceva.base.common.dto.ResponseDTO;
import com.ceva.base.common.product.dao.ProductManDAO;
import com.ceva.base.common.utils.CevaCommonConstants;
import com.opensymphony.xwork2.ActionSupport;

public class MCCManagementCreateAction  extends ActionSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(MCCManagementCreateAction.class);

	JSONObject requestJSON = null;
	JSONObject responseJSON = null;

	RequestDTO requestDTO = null;
	ResponseDTO responseDTO = null;
	String result=null;
	

	
	private HttpSession session = null;
	
	 private String finaljsonarray;
	 private String limitCode = null;
	 
	 private String categoryCode=null;
	 private String categoryName=null;
	 
	 private String billerid=null;
	 private String billername=null;
	 private String billercode=null;
	 
	 
	 
	 public String getBillerid() {
		return billerid;
	}
	public void setBillerid(String billerid) {
		this.billerid = billerid;
	}
	public String getBillername() {
		return billername;
	}
	public void setBillername(String billername) {
		this.billername = billername;
	}
	public String getBillercode() {
		return billercode;
	}
	public void setBillercode(String billercode) {
		this.billercode = billercode;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getLimitCode() {
		return limitCode;
	}
	public void setLimitCode(String limitCode) {
		this.limitCode = limitCode;
	}
	public String getFinaljsonarray() {
			return finaljsonarray;
		}
		public void setFinaljsonarray(String finaljsonarray) {
			this.finaljsonarray = finaljsonarray;
		}
	
	
	public void setRequestJSON(JSONObject requestJSON) {
		this.requestJSON = requestJSON;
	}
	public JSONObject getResponseJSON() {
		return responseJSON;
	}
	public void setResponseJSON(JSONObject responseJSON) {
		this.responseJSON = responseJSON;
	}
	public RequestDTO getRequestDTO() {
		return requestDTO;
	}
	public void setRequestDTO(RequestDTO requestDTO) {
		this.requestDTO = requestDTO;
	}
	public ResponseDTO getResponseDTO() {
		return responseDTO;
	}
	public void setResponseDTO(ResponseDTO responseDTO) {
		this.responseDTO = responseDTO;
	}

	public String blankPage() {
		
		logger.debug(" FirstPageView ....");

		return "success";

    }

	public String mccInfo(){
		logger.debug(" [ MCCCreateAction ] [ mccInfo ] Inside mccInfo Action.. ");
		MCCManagementCreationDao mccmcd = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();
			requestDTO.setRequestJSON(requestJSON);
			mccmcd = new MCCManagementCreationDao();
			
			responseDTO = mccmcd.mccInfo(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get("LMT_FEE_INFO");
				logger.debug("[ MCCCreateAction ] [ mccInfo ] Response JSON  [" + responseJSON + "]");
				result = "success";
			} else {
				errors = (ArrayList<String>) responseDTO.getErrors();
				for (int i = 0; i < errors.size(); i++) {
					addActionError(errors.get(i));
				}
				result = "fail";
			}
		} catch (Exception e) {
			result = "fail";
			logger.debug("[ MCCCreateAction ] [ mccInfo ] Exception   ["
					+ e.getMessage() + "]");
			addActionError("Internal error occured.");
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			mccmcd = null;
			errors = null;
		}

		return result;
	}
	
	public String ParameterCofigConf(){
		
		try {

			requestJSON = new JSONObject();
			responseJSON = new JSONObject();
			
		
			logger.info("finaljsonarray >>> ["+finaljsonarray+"]");

			JSONArray jsonarray = JSONArray.fromObject(finaljsonarray);


			responseJSON.put("FINAL_JSON", jsonarray);



			logger.info("Response Json ["+ responseJSON+"]");

			result = "success";	


		} catch (Exception e) {

			result = "fail";
			e.printStackTrace();
			logger.debug("Exception in getRequest["
					+ e.getMessage() + "]");
			addActionError("Internal error occured.");

		} finally {

			requestJSON = null;

		}

		return result;
	}
	
	
	public String mccCreationAck(){

		logger.debug("########################### mccCreationack Data Started ###########################");

		ArrayList<String> errors = null;
		MCCManagementCreationDao mccmcd=null;
		try {
			requestDTO = new RequestDTO();
			requestJSON = new JSONObject();
			responseJSON = new JSONObject();
			
			session = ServletActionContext.getRequest().getSession();
			
			responseJSON.put(CevaCommonConstants.MAKER_ID,
					session.getAttribute(CevaCommonConstants.MAKER_ID));
			responseJSON.put("remoteip",session.getAttribute("REMOTE_IP"));
			
			
			
			mccmcd= new MCCManagementCreationDao();

			logger.info("finaljsonarray >>> ["+finaljsonarray+"]");


			JSONArray jsonarray = JSONArray.fromObject(finaljsonarray);


			responseJSON.put("FINAL_JSON", jsonarray);

			requestDTO.setRequestJSON(responseJSON);
			
			responseDTO = mccmcd.MCCManagementCreationAck(requestDTO);
			
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {

				result = "success";

			} else {

				errors = (ArrayList<String>) responseDTO.getErrors();


				for (int i = 0; i < errors.size(); i++) {

					addActionError(errors.get(i));

				}
				result = "fail";

			}
			

			logger.info("Response Json ["+ responseJSON+"]");


		} catch (Exception e) {

			result = "fail";
			e.printStackTrace();
			logger.debug("Exception in getRequest["
					+ e.getMessage() + "]");
			addActionError("Internal error occured.");

		} finally {

			requestJSON = null;

		}

		logger.debug("########################### mccCreationack Data End ###########################");

		return result;
	}

	 public String mccpopupDetails() {
		 
		 
		 MCCManagementCreationDao mccd = null;
		 
			logger.debug("inside mccpopupDetails.. ");
			
			ArrayList<String> errors = null;
			try {
				requestJSON = new JSONObject();
				requestDTO = new RequestDTO();
				requestJSON.put(CevaCommonConstants.LIMIT_CODE, limitCode);
				requestDTO.setRequestJSON(requestJSON);				
				logger.debug("Request DTO [" + requestDTO + "]");
				mccd = new MCCManagementCreationDao();				
				responseDTO = mccd.mccpopupDetails(requestDTO);
				logger.debug("Response DTO [" + responseDTO + "]");
				if (responseDTO != null && responseDTO.getErrors().size() == 0) {
					
					
					responseJSON = (JSONObject) responseDTO.getData().get(CevaCommonConstants.BINGRP_INFO);
					logger.debug("Response JSON  [" + responseJSON + "]");
					//responseJSON = constructToResponseJson(httpRequest,responseJSON);
					result = "success";
				} else {
					errors = (ArrayList<String>) responseDTO.getErrors();
					for (int i = 0; i < errors.size(); i++) {
						addActionError(errors.get(i));
					}
					result = "fail";
				}
			} catch (Exception e) {
				result = "fail";
				logger.debug("Exception in feecodeCommonScreen  [" + e.getMessage()
						+ "]");
				
				addActionError("Internal error occured.");
			} finally {
				requestDTO = null;
				responseDTO = null;
				requestJSON = null;
				
				errors = null;
			}


		return "success";

	 }
	 
	 public String mccModifyScreen() {
		 
		 MCCManagementCreationDao mccd = null;
		 
			logger.debug("inside mccpopupDetails.. ");
			
			ArrayList<String> errors = null;
			try {
				requestJSON = new JSONObject();
				requestDTO = new RequestDTO();
				requestJSON.put(CevaCommonConstants.LIMIT_CODE, limitCode);
				requestDTO.setRequestJSON(requestJSON);				
				logger.debug("Request DTO [" + requestDTO + "]");
				mccd = new MCCManagementCreationDao();				
				responseDTO = mccd.getMccdetails(requestDTO);
				logger.debug("Response DTO [" + responseDTO + "]");
				if (responseDTO != null && responseDTO.getErrors().size() == 0) {
					
					
					responseJSON = (JSONObject) responseDTO.getData().get(CevaCommonConstants.BINGRP_INFO);
					logger.debug("Response JSON  [" + responseJSON + "]");
					//responseJSON = constructToResponseJson(httpRequest,responseJSON);
					result = "success";
				} else {
					errors = (ArrayList<String>) responseDTO.getErrors();
					for (int i = 0; i < errors.size(); i++) {
						addActionError(errors.get(i));
					}
					result = "fail";
				}
			} catch (Exception e) {
				result = "fail";
				logger.debug("Exception in feecodeCommonScreen  [" + e.getMessage()
						+ "]");
				
				addActionError("Internal error occured.");
			} finally {
				requestDTO = null;
				responseDTO = null;
				requestJSON = null;
				
				errors = null;
			}


		return "success";

		}
	 
	 
	 public String commonScreen() {
			logger.debug("Inside CommonScreen .. ");
			return SUCCESS;
		}
	 
	 
	 
 
	 public String updateModifyMccDetails() {
			logger.debug("Inside Insert Method .. ");

			ArrayList<String> errors = null;
			try {
				requestJSON = new JSONObject();
				requestDTO = new RequestDTO();
				session = ServletActionContext.getRequest().getSession();

				requestJSON.put(CevaCommonConstants.MAKER_ID,
						session.getAttribute(CevaCommonConstants.MAKER_ID));
				requestJSON.put(CevaCommonConstants.IP, ServletActionContext
						.getRequest().getRemoteAddr());
				
				logger.debug("categoryCode::: "+categoryCode);
			
				logger.debug("categoryName::: "+categoryName);
		
				
				requestJSON.put("categoryCode",categoryCode);
				requestJSON.put("categoryName",categoryName);

			
				requestDTO.setRequestJSON(requestJSON);

				responseDTO = new MCCManagementCreationDao().ModifyMccManagement(requestDTO);

				if (responseDTO != null && responseDTO.getErrors().size() == 0) {
					
					result = "success";
				} else {
					errors = (ArrayList<String>) responseDTO.getErrors();
					for (int i = 0; i < errors.size(); i++) {
						addActionError(errors.get(i));
					}
					result = "fail";
				}

			} catch (Exception e) {
				result = "fail";
				addActionError("Internal Error Occured, Please try again.");
				logger.debug("Exception in Insert [" + e.getMessage() + "]");
				e.printStackTrace();
			}

			
			result = "success";
			
			
			return result;

		}
	 
	 
		public String Productpaybilldata(){
			logger.debug(" [ MCCCreateAction ] [ Productpaybilldata ] Inside Productpaybilldata Action.. ");
			MCCManagementCreationDao mccmcd = null;
			ArrayList<String> errors = null;
			try {
				requestJSON = new JSONObject();
				requestDTO = new RequestDTO();
				requestDTO.setRequestJSON(requestJSON);
				mccmcd = new MCCManagementCreationDao();
				
				responseDTO = mccmcd.Productpaybilldata(requestDTO);
				logger.debug("Response DTO [" + responseDTO + "]");
				if (responseDTO != null && responseDTO.getErrors().size() == 0) {
					responseJSON = (JSONObject) responseDTO.getData().get("LMT_FEE_INFO");
					logger.debug("[ MCCCreateAction ] [ Productpaybilldata ] Response JSON  [" + responseJSON + "]");
					result = "success";
				} else {
					errors = (ArrayList<String>) responseDTO.getErrors();
					for (int i = 0; i < errors.size(); i++) {
						addActionError(errors.get(i));
					}
					result = "fail";
				}
			} catch (Exception e) {
				result = "fail";
				logger.debug("[ MCCCreateAction ] [ Productpaybilldata ] Exception   ["
						+ e.getMessage() + "]");
				addActionError("Internal error occured.");
			} finally {
				requestDTO = null;
				responseDTO = null;
				requestJSON = null;
				mccmcd = null;
				errors = null;
			}

			return result;
		}
		
		public String Productpaybillcnf() {
			 
			 MCCManagementCreationDao mccd = null;
			 
				logger.debug("inside mccpopupDetails.. ");
				
				ArrayList<String> errors = null;
				try {
					requestJSON = new JSONObject();
					requestDTO = new RequestDTO();
					requestJSON.put(CevaCommonConstants.LIMIT_CODE, limitCode);
					requestDTO.setRequestJSON(requestJSON);				
					logger.debug("Request DTO [" + requestDTO + "]");
					mccd = new MCCManagementCreationDao();				
					responseDTO = mccd.getProductpaybillcnf(requestDTO);
					logger.debug("Response DTO [" + responseDTO + "]");
					if (responseDTO != null && responseDTO.getErrors().size() == 0) {
						
						
						responseJSON = (JSONObject) responseDTO.getData().get(CevaCommonConstants.BINGRP_INFO);
						logger.debug("Response JSON  [" + responseJSON + "]");
						//responseJSON = constructToResponseJson(httpRequest,responseJSON);
						result = "success";
					} else {
						errors = (ArrayList<String>) responseDTO.getErrors();
						for (int i = 0; i < errors.size(); i++) {
							addActionError(errors.get(i));
						}
						result = "fail";
					}
				} catch (Exception e) {
					result = "fail";
					logger.debug("Exception in feecodeCommonScreen  [" + e.getMessage()
							+ "]");
					
					addActionError("Internal error occured.");
				} finally {
					requestDTO = null;
					responseDTO = null;
					requestJSON = null;
					
					errors = null;
				}


			return "success";

			}
		
		
		public String insercatDetailsAct() {
			logger.debug("Inside Insert Method .. ");

			ArrayList<String> errors = null;
			try {
				requestJSON = new JSONObject();
				requestDTO = new RequestDTO();
				session = ServletActionContext.getRequest().getSession();

				requestJSON.put(CevaCommonConstants.MAKER_ID,
						session.getAttribute(CevaCommonConstants.MAKER_ID));
				requestJSON.put(CevaCommonConstants.IP, ServletActionContext
						.getRequest().getRemoteAddr());
			
				requestJSON.put("billerid",billerid);
				requestJSON.put("billername",billername);
				requestJSON.put("billercode",billercode);

			
				requestDTO.setRequestJSON(requestJSON);

				responseDTO = new MCCManagementCreationDao().insercatDetailsAct(requestDTO);

				if (responseDTO != null && responseDTO.getErrors().size() == 0) {
					
					result = "success";
				} else {
					errors = (ArrayList<String>) responseDTO.getErrors();
					for (int i = 0; i < errors.size(); i++) {
						addActionError(errors.get(i));
					}
					result = "fail";
				}

			} catch (Exception e) {
				result = "fail";
				addActionError("Internal Error Occured, Please try again.");
				logger.debug("Exception in Insert [" + e.getMessage() + "]");
				e.printStackTrace();
			}

			
			result = "success";
			
			
			return result;



		}
		
}
