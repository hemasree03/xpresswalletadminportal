package com.ceva.base.action;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ceva.base.common.dao.BManagementDao;
import com.ceva.base.common.dto.RequestDTO;
import com.ceva.base.common.dto.ResponseDTO;
import com.ceva.base.common.utils.CevaCommonConstants;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BillerManagementAction  extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(BillerManagementAction.class);
	
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
	 private String status=null;
	 private String subcategory=null;
	 
	 
	 public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	
	private String userRights = null;
	private String prevJsonString = null;
	private String jsonVal = null;
	private String groupType = null;
	private String catids = null;
	private String type=null;
	
	private String keyPosVal = null;
	private String keyUssdVal = null;

	private String userPosRights = null;
	private String userUssdRights = null;
	
	private String jsonPosVal = null;
	private String jsonUssdVal = null;
	
	private String vdet = null;
	
	
	
	

	public String getVdet() {
		return vdet;
	}
	public void setVdet(String vdet) {
		this.vdet = vdet;
	}
	public String getJsonPosVal() {
		return jsonPosVal;
	}
	public void setJsonPosVal(String jsonPosVal) {
		this.jsonPosVal = jsonPosVal;
	}
	public String getJsonUssdVal() {
		return jsonUssdVal;
	}
	public void setJsonUssdVal(String jsonUssdVal) {
		this.jsonUssdVal = jsonUssdVal;
	}
	public String getUserPosRights() {
		return userPosRights;
	}
	public void setUserPosRights(String userPosRights) {
		this.userPosRights = userPosRights;
	}
	public String getUserUssdRights() {
		return userUssdRights;
	}
	public void setUserUssdRights(String userUssdRights) {
		this.userUssdRights = userUssdRights;
	}
	public String getKeyPosVal() {
		return keyPosVal;
	}
	public void setKeyPosVal(String keyPosVal) {
		this.keyPosVal = keyPosVal;
	}
	public String getKeyUssdVal() {
		return keyUssdVal;
	}
	public void setKeyUssdVal(String keyUssdVal) {
		this.keyUssdVal = keyUssdVal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserRights() {
		return userRights;
	}
	public void setUserRights(String userRights) {
		this.userRights = userRights;
	}
	public String getPrevJsonString() {
		return prevJsonString;
	}
	public void setPrevJsonString(String prevJsonString) {
		this.prevJsonString = prevJsonString;
	}
	public String getJsonVal() {
		return jsonVal;
	}
	public void setJsonVal(String jsonVal) {
		this.jsonVal = jsonVal;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getCatids() {
		return catids;
	}
	public void setCatids(String catids) {
		this.catids = catids;
	}
	public String blankPage() {
		
		logger.debug(" FirstPageView ....");

		return "success";

    }
	
	@Override
	public String execute() throws Exception {
		logger.debug("inside [CevaGroupAction][execute] prevJsonString  ["+prevJsonString+"]");
		logger.debug("inside [CevaGroupAction][execute] jsonVal 		["+jsonVal+"]");
		logger.debug("inside [CevaGroupAction][execute] groupType 		["+groupType+"]");
		logger.debug("inside [CevaGroupAction][execute] catids 		["+catids+"]");
		
		if(userRights != null ) userRights =  userRights.replaceAll("\"chkDisabled\":\"false\"", "\"chkDisabled\":\"true\"") ;
		if(prevJsonString != null ) prevJsonString =  prevJsonString.replaceAll("\"chkDisabled\":\"false\"", "\"chkDisabled\":\"true\"") ;
		if(jsonVal != null ) jsonVal =  jsonVal.replaceAll("\"chkDisabled\":\"false\"", "\"chkDisabled\":\"true\"") ;
		
		if(userPosRights != null ) userPosRights =  userPosRights.replaceAll("\"chkDisabled\":\"false\"", "\"chkDisabled\":\"true\"") ;
		if(userUssdRights != null ) userUssdRights =  userUssdRights.replaceAll("\"chkDisabled\":\"false\"", "\"chkDisabled\":\"true\"") ;
		
		if(keyPosVal != null ) keyPosVal =  keyPosVal.replaceAll("\"chkDisabled\":\"false\"", "\"chkDisabled\":\"true\"") ;
		if(keyUssdVal != null ) keyUssdVal =  keyUssdVal.replaceAll("\"chkDisabled\":\"false\"", "\"chkDisabled\":\"true\"") ;
		
		return super.execute();
	}

	public String catagerioInfo(){
		logger.debug(" [ BranchCreateAction ] [ branchInfo ] Inside branchInfo Action.. ");
		BManagementDao bcd = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();
			requestDTO.setRequestJSON(requestJSON);
			bcd = new BManagementDao();
			
			responseDTO = bcd.catagerioInfo(requestDTO);
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
	
	
	public String createUserGrps() {
		logger.debug("Inside CreateUserGrps..");
		BManagementDao cevaPowerDAO = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			responseJSON = new JSONObject();
			requestDTO = new RequestDTO();
			responseDTO = new ResponseDTO();
			requestDTO.setRequestJSON(requestJSON);

			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.getUserGroupDetails(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
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
			logger.debug("Exception in CreateUserGrps [" + e.getMessage() + "]");
			addActionError("Internal error occured.");
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}

		return result;
	}
	
	public String catadetails() {
		//String result="fail";
		 BManagementDao barcharDAO = null;
	
		ArrayList<String> errors = null;
		try {
			logger.debug("inside [BarChatAction][catadetails]");
			//logger.debug("inside [BarChatAction][filluserlevel][groupType::"+groupType+"]");
			requestJSON=new JSONObject();
			responseJSON=new JSONObject();
			requestDTO=new RequestDTO();
			responseDTO= new ResponseDTO();
			session = ServletActionContext.getRequest().getSession();
			
			requestJSON.put("makerid", session.getAttribute(CevaCommonConstants.MAKER_ID).toString());
			requestJSON.put("group", "GRPTYPE");
			
			requestDTO.setRequestJSON(requestJSON);	
			barcharDAO = new BManagementDao();
		    responseDTO = barcharDAO.fetchuserlevel(requestDTO);
		   
			if (responseDTO != null && responseDTO.getErrors().size()==0) {		
				responseJSON=(JSONObject) responseDTO.getData().get("USER_LEVEL_LIST");
				logger.debug("[BarChatAction][filluserlevel][responseJSON:::::"+responseJSON+"]");
			}else{			
				ArrayList<String> errors1=(ArrayList<String>) responseDTO.getErrors();
				for(int i=0;i<errors1.size();i++){
					addActionError(errors1.get(i));
				}
			}
	    	return SUCCESS;
		
		
	} catch (Exception e) {
		result = "fail";
		logger.debug("Exception in Get BarChatAction  filluserlevel ["
				+ e.getMessage() + "]");
		addActionError("Internal error occured.");
	} finally {
		requestDTO = null;
		responseDTO = null;
		requestJSON = null;
		barcharDAO = null;
		errors = null;
	}
	return result;
	}
	
	public String saveGroupDetails() {
		logger.debug("Inside SaveGroupDetails. ");
		ArrayList<String> errors = null;
		BManagementDao cevaPowerDAO = null;
		try {
			requestJSON = new JSONObject();
 			requestDTO = new RequestDTO();

			session = ServletActionContext.getRequest().getSession();

		
			requestJSON.put("GROUP_TYPE", getGroupType());
			//requestJSON.put("keyVal", getKeyVal());
			requestJSON.put("jsonVal", getJsonVal());
			requestJSON.put("groupType", groupType);
			requestJSON.put("catids", catids);
			requestJSON
					.put("user_id",
							(String) session.getAttribute("makerId") == null ? "NO_DATA"
									: (String) session.getAttribute("makerId"));
			requestJSON.put("entity_id", (String) session
					.getAttribute("loginEntity") == null ? "NO_DATA"
					: (String) session.getAttribute("loginEntity"));
			requestJSON.put("applCode", (String) session
					.getAttribute("ACCESS_APPL_NAME") == null ? "NO_DATA"
					: (String) session.getAttribute("ACCESS_APPL_NAME"));
			requestJSON.put(CevaCommonConstants.IP, ServletActionContext.getRequest().getRemoteAddr());
			//requestJSON.put("MAKER_ID",session.getAttribute(CevaCommonConstants.MAKER_ID).toString());
			requestJSON.put(CevaCommonConstants.MAKER_ID,session.getAttribute(CevaCommonConstants.MAKER_ID));
		

			logger.debug(" Request JSON  [" + requestJSON + "]");
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.insertUserGroupDetails(requestDTO);
			logger.debug(" Response DTO [" + responseDTO + "]");
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
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
			logger.debug("Exception in Save GroupDetails [" + e.getMessage()
					+ "]");
			addActionError("Internal error occured.");
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}

		return result;
	}
	
	
	public String subCategorySave() {
		logger.debug("Inside SaveGroupDetails. ");
		ArrayList<String> errors = null;
		BManagementDao cevaPowerDAO = null;
		try {
			requestJSON = new JSONObject();
 			requestDTO = new RequestDTO();

			session = ServletActionContext.getRequest().getSession();

		
			requestJSON.put("GROUP_TYPE", getGroupType());
			//requestJSON.put("keyVal", getKeyVal());
			requestJSON.put("jsonVal", getJsonVal());
			requestJSON.put("jsonPosVal", getJsonPosVal());
			requestJSON.put("jsonUssdVal", getJsonUssdVal());
			requestJSON.put("groupType", groupType);
			requestJSON.put("catids", catids);
			requestJSON.put("subcategory", subcategory);
			requestJSON
					.put("user_id",
							(String) session.getAttribute("makerId") == null ? "NO_DATA"
									: (String) session.getAttribute("makerId"));
			requestJSON.put("entity_id", (String) session
					.getAttribute("loginEntity") == null ? "NO_DATA"
					: (String) session.getAttribute("loginEntity"));
			requestJSON.put("applCode", (String) session
					.getAttribute("ACCESS_APPL_NAME") == null ? "NO_DATA"
					: (String) session.getAttribute("ACCESS_APPL_NAME"));
			requestJSON.put(CevaCommonConstants.IP, ServletActionContext.getRequest().getRemoteAddr());
			//requestJSON.put("MAKER_ID",session.getAttribute(CevaCommonConstants.MAKER_ID).toString());
			requestJSON.put(CevaCommonConstants.MAKER_ID,session.getAttribute(CevaCommonConstants.MAKER_ID));
		

			logger.debug(" Request JSON  [" + requestJSON + "]");
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.subCategorySave(requestDTO);
			logger.debug(" Response DTO [" + responseDTO + "]");
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
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
			logger.debug("Exception in Save GroupDetails [" + e.getMessage()
					+ "]");
			addActionError("Internal error occured.");
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}

		return result;
	}
	
	public String viewCategory() {
		logger.debug("Inside viewCategory. ");
		BManagementDao cevaPowerDAO = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();

			requestJSON.put("groupType", groupType);
			requestJSON.put("catids", catids);
			requestJSON.put("status", status);

			logger.debug("Request JSON [" + requestJSON + "]");
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.viewCategory(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
				responseJSON.put("groupType", groupType);
				responseJSON.put("catids", catids);
				responseJSON.put("status", status);
				
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
			logger.debug("Exception in View UserGroup [" + e.getMessage() + "]");
			addActionError("Internal error occured.");
			e.printStackTrace();
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}
		return result;
	}
	
	public String viewSubCategory() {
		logger.debug("Inside viewCategory. ");
		BManagementDao cevaPowerDAO = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();

			requestJSON.put("groupType", groupType);
			requestJSON.put("catids", catids);
			requestJSON.put("status", status);

			logger.debug("Request JSON [" + requestJSON + "]");
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.viewSubCategory(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
				responseJSON.put("groupType", groupType);
				responseJSON.put("catids", catids);
				responseJSON.put("status", status);
				
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
			logger.debug("Exception in View UserGroup [" + e.getMessage() + "]");
			addActionError("Internal error occured.");
			e.printStackTrace();
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}
		return result;
	}
	
	public String modifyCategory() {
		logger.debug("Inside viewCategory. ");
		BManagementDao cevaPowerDAO = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();
			
			requestJSON.put("type", type);
			requestJSON.put("groupType", groupType);
			requestJSON.put("catids", catids);
			requestJSON.put("status", status);

			logger.debug("Request JSON [" + requestJSON + "]");
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.modifyCategory(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
				responseJSON.put("groupType", groupType);
				responseJSON.put("catids", catids);
				responseJSON.put("status", status);
				responseJSON.put("type", type);
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
			logger.debug("Exception in View UserGroup [" + e.getMessage() + "]");
			addActionError("Internal error occured.");
			e.printStackTrace();
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}
		return result;
	}
	
	public String modifysaveGroupDetails() {
		logger.debug("Inside SaveGroupDetails. ");
		ArrayList<String> errors = null;
		BManagementDao cevaPowerDAO = null;
		try {
			requestJSON = new JSONObject();
 			requestDTO = new RequestDTO();

			session = ServletActionContext.getRequest().getSession();

			requestJSON.put("type", type);
			requestJSON.put("GROUP_TYPE", getGroupType());
			//requestJSON.put("keyVal", getKeyVal());
			requestJSON.put("jsonVal", getJsonVal());
			requestJSON.put("groupType", groupType);
			requestJSON.put("catids", catids);
			requestJSON.put("status", status);
			requestJSON
					.put("user_id",
							(String) session.getAttribute("makerId") == null ? "NO_DATA"
									: (String) session.getAttribute("makerId"));
			requestJSON.put("entity_id", (String) session
					.getAttribute("loginEntity") == null ? "NO_DATA"
					: (String) session.getAttribute("loginEntity"));
			requestJSON.put("applCode", (String) session
					.getAttribute("ACCESS_APPL_NAME") == null ? "NO_DATA"
					: (String) session.getAttribute("ACCESS_APPL_NAME"));
			requestJSON.put(CevaCommonConstants.IP, ServletActionContext.getRequest().getRemoteAddr());
			//requestJSON.put("MAKER_ID",session.getAttribute(CevaCommonConstants.MAKER_ID).toString());
			requestJSON.put(CevaCommonConstants.MAKER_ID,session.getAttribute(CevaCommonConstants.MAKER_ID));
		

			logger.debug(" Request JSON  [" + requestJSON + "]");
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.modifysaveGroupDetails(requestDTO);
			logger.debug(" Response DTO [" + responseDTO + "]");
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
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
			logger.debug("Exception in Save GroupDetails [" + e.getMessage()
					+ "]");
			addActionError("Internal error occured.");
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}

		return result;
	}
	
	public String billerCatagerioInfo(){
		logger.debug(" [ BranchCreateAction ] [ branchInfo ] Inside branchInfo Action.. ");
		BManagementDao bcd = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();
			requestDTO.setRequestJSON(requestJSON);
			bcd = new BManagementDao();
			
			responseDTO = bcd.billerCatagerioInfo(requestDTO);
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
	
	
	public String billerCategoryDetails() {
		logger.debug("Inside CreateUserGrps..");
		BManagementDao cevaPowerDAO = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			responseJSON = new JSONObject();
			requestDTO = new RequestDTO();
			responseDTO = new ResponseDTO();
			requestDTO.setRequestJSON(requestJSON);

			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.billerCategoryDetails(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
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
			logger.debug("Exception in CreateUserGrps [" + e.getMessage() + "]");
			addActionError("Internal error occured.");
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}

		return result;
	}
	
	public String billerCateSaveGroupDetails() {
		logger.debug("Inside SaveGroupDetails. ");
		ArrayList<String> errors = null;
		BManagementDao cevaPowerDAO = null;
		try {
			requestJSON = new JSONObject();
 			requestDTO = new RequestDTO();

			session = ServletActionContext.getRequest().getSession();

		
			requestJSON.put("GROUP_TYPE", getGroupType());
			//requestJSON.put("keyVal", getKeyVal());
			requestJSON.put("jsonVal", getJsonVal());
			requestJSON.put("groupType", groupType);
			requestJSON.put("catids", catids);
			requestJSON
					.put("user_id",
							(String) session.getAttribute("makerId") == null ? "NO_DATA"
									: (String) session.getAttribute("makerId"));
			requestJSON.put("entity_id", (String) session
					.getAttribute("loginEntity") == null ? "NO_DATA"
					: (String) session.getAttribute("loginEntity"));
			requestJSON.put("applCode", (String) session
					.getAttribute("ACCESS_APPL_NAME") == null ? "NO_DATA"
					: (String) session.getAttribute("ACCESS_APPL_NAME"));
			requestJSON.put(CevaCommonConstants.IP, ServletActionContext.getRequest().getRemoteAddr());
			//requestJSON.put("MAKER_ID",session.getAttribute(CevaCommonConstants.MAKER_ID).toString());
			requestJSON.put(CevaCommonConstants.MAKER_ID,session.getAttribute(CevaCommonConstants.MAKER_ID));
		

			logger.debug(" Request JSON  [" + requestJSON + "]");
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.billerCateSaveGroupDetails(requestDTO);
			logger.debug(" Response DTO [" + responseDTO + "]");
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
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
			logger.debug("Exception in Save GroupDetails [" + e.getMessage()
					+ "]");
			addActionError("Internal error occured.");
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}

		return result;
	}
	
	public String viewBillerCategory() {
		logger.debug("Inside viewCategory. ");
		BManagementDao cevaPowerDAO = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();

			requestJSON.put("groupType", groupType);
			requestJSON.put("catids", catids);
			requestJSON.put("status", status);

			logger.debug("Request JSON [" + requestJSON + "]");
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.viewBillerCategory(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
				responseJSON.put("groupType", groupType);
				responseJSON.put("catids", catids);
				responseJSON.put("status", status);
				
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
			logger.debug("Exception in View UserGroup [" + e.getMessage() + "]");
			addActionError("Internal error occured.");
			e.printStackTrace();
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}
		return result;
	}
	
	public String modifyBillerCategory() {
		logger.debug("Inside viewCategory. ");
		BManagementDao cevaPowerDAO = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();
			
			requestJSON.put("type", type);
			requestJSON.put("groupType", groupType);
			requestJSON.put("catids", catids);
			requestJSON.put("status", status);

			logger.debug("Request JSON [" + requestJSON + "]");
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.modifyBillerCategory(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
				responseJSON.put("groupType", groupType);
				responseJSON.put("catids", catids);
				responseJSON.put("status", status);
				responseJSON.put("type", type);
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
			logger.debug("Exception in View UserGroup [" + e.getMessage() + "]");
			addActionError("Internal error occured.");
			e.printStackTrace();
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}
		return result;
	}
	
	
	public String modifybillercatsaveGroupDetails() {
		logger.debug("Inside SaveGroupDetails. ");
		ArrayList<String> errors = null;
		BManagementDao cevaPowerDAO = null;
		try {
			requestJSON = new JSONObject();
 			requestDTO = new RequestDTO();

			session = ServletActionContext.getRequest().getSession();

			requestJSON.put("type", type);
			requestJSON.put("GROUP_TYPE", getGroupType());
			//requestJSON.put("keyVal", getKeyVal());
			requestJSON.put("jsonVal", getJsonVal());
			requestJSON.put("groupType", groupType);
			requestJSON.put("catids", catids);
			requestJSON.put("status", status);
			requestJSON
					.put("user_id",
							(String) session.getAttribute("makerId") == null ? "NO_DATA"
									: (String) session.getAttribute("makerId"));
			requestJSON.put("entity_id", (String) session
					.getAttribute("loginEntity") == null ? "NO_DATA"
					: (String) session.getAttribute("loginEntity"));
			requestJSON.put("applCode", (String) session
					.getAttribute("ACCESS_APPL_NAME") == null ? "NO_DATA"
					: (String) session.getAttribute("ACCESS_APPL_NAME"));
			requestJSON.put(CevaCommonConstants.IP, ServletActionContext.getRequest().getRemoteAddr());
			//requestJSON.put("MAKER_ID",session.getAttribute(CevaCommonConstants.MAKER_ID).toString());
			requestJSON.put(CevaCommonConstants.MAKER_ID,session.getAttribute(CevaCommonConstants.MAKER_ID));
		

			logger.debug(" Request JSON  [" + requestJSON + "]");
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.modifybillercatsaveGroupDetails(requestDTO);
			logger.debug(" Response DTO [" + responseDTO + "]");
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
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
			logger.debug("Exception in Save GroupDetails [" + e.getMessage()
					+ "]");
			addActionError("Internal error occured.");
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}

		return result;
	}
	
	
	public String billerFeeInfo(){
		logger.debug(" [ BranchCreateAction ] [ branchInfo ] Inside branchInfo Action.. ");
		BManagementDao bcd = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();
			requestDTO.setRequestJSON(requestJSON);
			bcd = new BManagementDao();
			
			responseDTO = bcd.billerFeeInfo(requestDTO);
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
	
	
	public String billerInfoDetails(){
		logger.debug(" [ BranchCreateAction ] [ branchInfo ] Inside branchInfo Action.. ");
		BManagementDao bcd = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();
			
			requestJSON.put("catids", catids);
			requestDTO.setRequestJSON(requestJSON);
			bcd = new BManagementDao();
			
			responseDTO = bcd.billerInfoDetails(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get("LMT_FEE_INFO");
				logger.debug("[ BranchCreateAction ] [ branchInfo ] Response JSON  [" + responseJSON + "]");
				responseJSON.put("catids", catids);
				
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
	
	public String billerInfoViewDetails(){
		logger.debug(" [ BranchCreateAction ] [ branchInfo ] Inside branchInfo Action.. ");
		BManagementDao bcd = null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();
			
			requestJSON.put("catids", catids);
			requestDTO.setRequestJSON(requestJSON);
			bcd = new BManagementDao();
			
			responseDTO = bcd.billerInfoViewDetails(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get("LMT_FEE_INFO");
				logger.debug("[ BranchCreateAction ] [ branchInfo ] Response JSON  [" + responseJSON + "]");
				responseJSON.put("catids", catids);
				
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
	
	public String paybillFeeCreation() {
		logger.debug("Inside SaveGroupDetails. ");
		ArrayList<String> errors = null;
		BManagementDao cevaPowerDAO = null;
		try {
			requestJSON = new JSONObject();
 			requestDTO = new RequestDTO();

			session = ServletActionContext.getRequest().getSession();

			requestJSON.put("vdet",vdet);
			requestJSON.put("catids", catids);
			requestJSON.put(CevaCommonConstants.IP, ServletActionContext.getRequest().getRemoteAddr());
			requestJSON.put(CevaCommonConstants.IP, ServletActionContext.getRequest().getRemoteAddr());
			//requestJSON.put("MAKER_ID",session.getAttribute(CevaCommonConstants.MAKER_ID).toString());
			requestJSON.put(CevaCommonConstants.MAKER_ID,session.getAttribute(CevaCommonConstants.MAKER_ID));
		

			logger.debug(" Request JSON  [" + requestJSON + "]");
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			cevaPowerDAO = new BManagementDao();
			responseDTO = cevaPowerDAO.paybillFeeCreation(requestDTO);
			logger.debug(" Response DTO [" + responseDTO + "]");
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get(
						"user_rights");
				logger.debug("Response JSON [" + responseJSON + "]");
				responseJSON.put("catids", catids);
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
			logger.debug("Exception in Save GroupDetails [" + e.getMessage()
					+ "]");
			addActionError("Internal error occured.");
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			cevaPowerDAO = null;
			errors = null;
		}

		return result;
	}

}
