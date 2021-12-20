package com.ceva.base.action;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cave.base.dao.BranchCreationDao;
import com.ceva.base.common.dao.AgentDAO;
import com.ceva.base.common.dto.RequestDTO;
import com.ceva.base.common.dto.ResponseDTO;
import com.ceva.base.common.product.dao.ProductManDAO;
import com.ceva.base.common.utils.CevaCommonConstants;
import com.opensymphony.xwork2.ActionSupport;

public class BranchCreateAction  extends ActionSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(BranchCreateAction.class);

	JSONObject requestJSON = null;
	JSONObject responseJSON = null;

	RequestDTO requestDTO = null;
	ResponseDTO responseDTO = null;
	String result=null;
	
	
	
	private HttpSession session = null;
	
	 private String finaljsonarray;
	 private String limitCode = null;
	 private String limitDesc = null;
	 
	 private String termilstatus=null;
	 
	 private String state=null;
	 private String lga=null;
	 private String localGovernment=null;
	 
	 
	 public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLga() {
		return lga;
	}
	public void setLga(String lga) {
		this.lga = lga;
	}
	public String getLocalGovernment() {
		return localGovernment;
	}
	public void setLocalGovernment(String localGovernment) {
		this.localGovernment = localGovernment;
	}
	public String getTermilstatus() {
			return termilstatus;
	 }
	public void setTermilstatus(String termilstatus) {
			this.termilstatus = termilstatus;
	}
	 
	 public String getLimitDesc() {
		return limitDesc;
	}
	public void setLimitDesc(String limitDesc) {
		this.limitDesc = limitDesc;
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

	public String branchInfo(){
		logger.debug(" [ BranchCreateAction ] [ branchInfo ] Inside branchInfo Action.. ");
		BranchCreationDao bcd = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();
			requestDTO.setRequestJSON(requestJSON);
			bcd = new BranchCreationDao();
			
			responseDTO = bcd.branchInfo(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get("LMT_FEE_INFO");
				logger.debug("[ BranchCreateAction ] [ branchInfo ] Response JSON  [" + responseJSON + "]");
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
			logger.debug("[ BranchCreateAction ] [ branchInfo ] Exception   ["
					+ e.getMessage() + "]");
			addActionError("Internal error occured.");
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			bcd = null;
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
	
	
	public String ClusterCreationAck(){

		logger.debug("########################### ChannelMappingAck Data Started ###########################");

		ArrayList<String> errors = null;
		BranchCreationDao bcd=null;
		try {
			requestDTO = new RequestDTO();
			requestJSON = new JSONObject();
			responseJSON = new JSONObject();
			
			session = ServletActionContext.getRequest().getSession();
			
			responseJSON.put(CevaCommonConstants.MAKER_ID,
					session.getAttribute(CevaCommonConstants.MAKER_ID));
			responseJSON.put("remoteip",session.getAttribute("REMOTE_IP"));
			
			
			
			bcd= new BranchCreationDao();

			logger.info("finaljsonarray >>> ["+finaljsonarray+"]");


			JSONArray jsonarray = JSONArray.fromObject(finaljsonarray);


			responseJSON.put("FINAL_JSON", jsonarray);

			requestDTO.setRequestJSON(responseJSON);
			
			responseDTO = bcd.ClusterCreationAck(requestDTO);
			
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

		logger.debug("########################### ChannelMappingAck Data End ###########################");

		return result;
	}

	 public String branchpopupDetails() {
		 
		 
		 BranchCreationDao bcd = null;
		 
			logger.debug("inside binCommonScreen.. ");
			
			ArrayList<String> errors = null;
			try {
				requestJSON = new JSONObject();
				requestDTO = new RequestDTO();
				requestJSON.put(CevaCommonConstants.LIMIT_CODE, limitCode);
				requestDTO.setRequestJSON(requestJSON);				
				logger.debug("Request DTO [" + requestDTO + "]");
				bcd = new BranchCreationDao();				
				responseDTO = bcd.branchpopupDetails(requestDTO);
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
	 
		public String BranchModify() {

			/* ProductManDAO productDAO = null; */
			BranchCreationDao bcd = null;

			logger.debug("inside binCommonScreen.. ");

			ArrayList<String> errors = null;
			try {
				requestJSON = new JSONObject();
				requestDTO = new RequestDTO();
				requestJSON.put(CevaCommonConstants.LIMIT_CODE, limitCode);
				requestDTO.setRequestJSON(requestJSON);
				logger.debug("Request DTO [" + requestDTO + "]");
				bcd = new BranchCreationDao();
				responseDTO = bcd.BranchModify(requestDTO);
				logger.debug("Response DTO [" + responseDTO + "]");
				if (responseDTO != null && responseDTO.getErrors().size() == 0) {

					responseJSON = (JSONObject) responseDTO.getData().get(CevaCommonConstants.BINGRP_INFO);
					logger.debug("Response JSON  [" + responseJSON + "]");
					// responseJSON = constructToResponseJson(httpRequest,responseJSON);
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
				logger.debug("Exception in feecodeCommonScreen  [" + e.getMessage() + "]");

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
			logger.debug("Inside GetCommonScreen...");

			result = "success";
			return result;
		}
		
		public String BranchModifyAck() {

			BranchCreationDao bcd = null;

			logger.debug("inside binCommonScreen.. ");

			ArrayList<String> errors = null;
			try {
				requestJSON = new JSONObject();
				requestDTO = new RequestDTO();
				requestJSON.put(CevaCommonConstants.LIMIT_CODE, limitCode);
				requestJSON.put("limitDesc", limitDesc);
				requestJSON.put("state", state);
				requestJSON.put("localGovernment", localGovernment);
				 //System.out.println("query "+limitDesc+"---"+state+"---"+localGovernment);
				requestDTO.setRequestJSON(requestJSON);
				logger.debug("Request DTO [" + requestDTO + "]");
				bcd = new BranchCreationDao();
				responseDTO = bcd.BranchModifyAck(requestDTO);
				logger.debug("Response DTO [" + responseDTO + "]");
				if (responseDTO != null && responseDTO.getErrors().size() == 0) {

					responseJSON = (JSONObject) responseDTO.getData().get(CevaCommonConstants.BINGRP_INFO);
					logger.debug("Response JSON  [" + responseJSON + "]");
					// responseJSON = constructToResponseJson(httpRequest,responseJSON);
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
				logger.debug("Exception in feecodeCommonScreen  [" + e.getMessage() + "]");

				addActionError("Internal error occured.");
			} finally {
				requestDTO = null;
				responseDTO = null;
				requestJSON = null;

				errors = null;
			}

			return "success";

		}
		
		 public String branchStatus() {
			 
			 
			 logger.debug("Inside GetCommonScreen...");

				result = "success";
				return result;

		 }
		 
	 public String branchStatusconfirm() {
			 
			 
			 logger.debug("Inside GetCommonScreen...sdfsd");

				result = "success";
				return result;

		 }
		 
	 
	 
	 public String Branchststusupdation() {
		 
		 
		 logger.debug("Inside GetCommonScreen...change");
		 
		  System.out.println("hello priayaa toooooooooooooo--->"+limitCode);
		  System.out.println("hello priayaa toooooooooooooo--->"+termilstatus);

		  BranchCreationDao bcd = null;

			logger.debug("inside binCommonScreen.. ");

			ArrayList<String> errors = null;
			try {
				requestJSON = new JSONObject();
				requestDTO = new RequestDTO();
				requestJSON.put(CevaCommonConstants.LIMIT_CODE, limitCode);
				requestJSON.put(CevaCommonConstants.status, termilstatus);
				requestDTO.setRequestJSON(requestJSON);
				logger.debug("Request DTO [" + requestDTO + "]");
				bcd = new BranchCreationDao();
				responseDTO = bcd.BranchstatusAck(requestDTO);
				logger.debug("Response DTO [" + responseDTO + "]");
				if (responseDTO != null && responseDTO.getErrors().size() == 0) {
					responseJSON = (JSONObject) responseDTO.getData().get(CevaCommonConstants.BINGRP_INFO);
					logger.debug("Response JSON  [" + responseJSON + "]");
					// responseJSON = constructToResponseJson(httpRequest,responseJSON);
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
				logger.debug("Exception in feecodeCommonScreen  [" + e.getMessage() + "]");

				addActionError("Internal error occured.");
			} finally {
				requestDTO = null;
				responseDTO = null;
				requestJSON = null;

				errors = null;
			}

			return "success";

	 }


 
}
