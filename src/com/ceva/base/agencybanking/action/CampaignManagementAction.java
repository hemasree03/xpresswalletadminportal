package com.ceva.base.agencybanking.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import au.com.bytecode.opencsv.CSVReader;

import com.ceva.aestools.AES;
import com.ceva.base.common.bean.AccountBean;
import com.ceva.base.common.dao.AccountPropertiesDAO;
import com.ceva.base.common.dao.AddNewAccountDAO;
import com.ceva.base.common.dao.CampaignManagementDAO;
import com.ceva.base.common.dto.RequestDTO;
import com.ceva.base.common.dto.ResponseDTO;
import com.ceva.base.common.utils.CevaCommonConstants;
import com.ceva.util.CommonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CampaignManagementAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(CampaignManagementAction.class);

	private JSONObject requestJSON = null;
	private JSONObject responseJSON = null;
	private ResponseDTO responseDTO = null;
	private RequestDTO requestDTO = null;
	private HttpServletRequest request = null;
	private HttpSession session = null;

	private String result = null;
	private String templateName = null;
	private String productName = null;
	private String cmpMessage = null;
	private String rmrk  = null;
	
	private String tempId= null;
	private String template= null;
	private String createdby= null;
	private String createdate= null;
	
	private String mobileNumber= null;
	private String customerid= null;
	private String fromdate= null;
	private String todate= null;
	
	private String	campaignFilter = null;
	private String chooseName = null;
	private String status = null;
	private String msgtype = null;
	private String datetime = null;
	private String tomailid = null;
	
	
	

	public String getTomailid() {
		return tomailid;
	}

	public void setTomailid(String tomailid) {
		this.tomailid = tomailid;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	





	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}






	JSONObject resJSON = null;

	public String commonScreen() {
		logger.debug("Inside CommonScreen .. ");
		
		CampaignManagementDAO cmd = new CampaignManagementDAO();
		resJSON = cmd.getProductNames();
		
		logger.debug("Inside resJSON.."+resJSON);

		return SUCCESS;
	}

	
	
	
	
	public String InsertCampDetails() {
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
			
			logger.debug("templateName::: "+templateName);
			logger.debug("productName::: "+productName);

			logger.debug("cmpMessage::: "+URLDecoder.decode(cmpMessage,"UTF-8"));
			
			logger.debug("rmrk::: "+rmrk);
			//System.out.println("kailash :: "+URLDecoder.decode(cmpMessage,"UTF-8"));
			
			requestJSON.put("Template_Name",templateName);
			requestJSON.put("Product_Name",productName);
			requestJSON.put("Cmp_Message",URLDecoder.decode(cmpMessage,"UTF-8"));
			requestJSON.put("Rmrk",rmrk);

		
			requestDTO.setRequestJSON(requestJSON);

			responseDTO = new CampaignManagementDAO()
					.insertCmpManagement(requestDTO,URLDecoder.decode(cmpMessage,"UTF-8"));

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

		return result;

	}
	
	
	public String InsertEmailCampDetails() {
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
			
			logger.debug("templateName::: "+templateName);

			logger.debug("cmpMessage::: "+URLDecoder.decode(cmpMessage,"UTF-8"));
			
			logger.debug("rmrk::: "+rmrk);
			//System.out.println("kailash :: "+URLDecoder.decode(cmpMessage,"UTF-8"));
			
			requestJSON.put("tomailid",tomailid);
			requestJSON.put("Template_Name",templateName);
			requestJSON.put("Product_Name",productName);
			requestJSON.put("Cmp_Message",URLDecoder.decode(cmpMessage,"UTF-8"));
			requestJSON.put("Rmrk",rmrk);

		
			requestDTO.setRequestJSON(requestJSON);

			responseDTO = new CampaignManagementDAO()
					.InsertEmailCampDetails(requestDTO,URLDecoder.decode(cmpMessage,"UTF-8"));

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

		return result;

	}
	
	public String fetchCampaignInformation() {
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
			
			logger.debug("templateName::: "+templateName);
			logger.debug("productName::: "+productName);

			logger.debug("cmpMessage::: "+cmpMessage);
			
			logger.debug("rmrk::: "+rmrk);

			
			requestJSON.put("Template_Name",templateName);
			requestJSON.put("Product_Name",productName);
			requestJSON.put("Cmp_Message",cmpMessage);
			requestJSON.put("Rmrk",rmrk);

		
			requestDTO.setRequestJSON(requestJSON);

			responseDTO = new CampaignManagementDAO()
					.getCampaignInfo(requestDTO);

			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				
				responseJSON = (JSONObject) responseDTO.getData().get("CAMP_INFO");
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
			addActionError("Internal Error Occured, Please try again.");
			logger.debug("Exception in Insert [" + e.getMessage() + "]");
			e.printStackTrace();
		}

		return result;
	}
	
	public String fetchassigned() {
		logger.debug("Inside Fetching the assigned templates ");
		
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();
			session = ServletActionContext.getRequest().getSession();
			
			requestJSON.put(CevaCommonConstants.MAKER_ID,session.getAttribute(CevaCommonConstants.MAKER_ID));
			requestJSON.put(CevaCommonConstants.IP, ServletActionContext.getRequest().getRemoteAddr());
			
			requestDTO.setRequestJSON(requestJSON);
			responseDTO = new CampaignManagementDAO().getassgned(requestDTO);
			
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				
				responseJSON = (JSONObject) responseDTO.getData().get("CAMP_INFO");
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
			addActionError("Internal Error Occured, Please try again.");
			logger.debug("Exception in Insert [" + e.getMessage() + "]");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public String getChooseName() {
		return chooseName;
	}





	public void setChooseName(String chooseName) {
		this.chooseName = chooseName;
	}





	public String cmapModifyScreen() {
		logger.debug("Inside CommonScreen .. ");
		
		logger.debug("tempId::: "+tempId);
		logger.debug("template::: "+template);

		logger.debug("createdby::: "+createdby);
		
		logger.debug("createdate::: "+createdate);

		
		CampaignManagementDAO cmd = new CampaignManagementDAO();
		resJSON = cmd.getProductNames();
		
		logger.debug("Inside resJSON.."+resJSON);

		return SUCCESS;
	}


	public String updateModifyCampDetails() {
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
			
			logger.debug("templateName::: "+templateName);
		
			logger.debug("cmpMessage::: "+cmpMessage);
	
			
			requestJSON.put("Template_Name",templateName);
			requestJSON.put("Cmp_Message",cmpMessage);

		
			requestDTO.setRequestJSON(requestJSON);

			responseDTO = new CampaignManagementDAO()
					.ModifyCmpManagement(requestDTO);

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
	
	
	
	public String updateActiveCampDetails() {
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
			
			logger.debug("templateName::: "+templateName);
		
			logger.debug("cmpMessage::: "+cmpMessage);
	
			
			requestJSON.put("Template_Name",templateName);
			requestJSON.put("Cmp_Message",cmpMessage);
			requestJSON.put("status",status);

		
			requestDTO.setRequestJSON(requestJSON);

			responseDTO = new CampaignManagementDAO()
					.updateActiveCampDetails(requestDTO);

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

	public String AssignCampDetailsinsertAction() {
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
			
			logger.debug("templateName::: "+templateName);
			logger.debug("productName::: "+productName);

			logger.debug("cmpMessage::: "+cmpMessage);
			
			logger.debug("campaignFilter::: "+campaignFilter);
			logger.debug("fromdate::: "+fromdate);

			
			requestJSON.put("templateName",templateName);
			requestJSON.put("chooseName",chooseName);
			requestJSON.put("productName",productName);
			requestJSON.put("mobileNumber",mobileNumber);
			requestJSON.put("customerid",customerid);
			
			requestJSON.put("fromdate",fromdate);
			requestJSON.put("todate",todate);
			
			requestJSON.put("Cmp_Message",cmpMessage);
			requestJSON.put("campaignFilter",campaignFilter);
			requestJSON.put("msgtype",msgtype);
			requestJSON.put("datetime",datetime);
			


		
			requestDTO.setRequestJSON(requestJSON);

			responseDTO = new CampaignManagementDAO()
					.insertAssginCmpManagement(requestDTO);

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

		return result;

	}
	
	
	public String searchEmailid() {
		logger.debug("[POSAction][terminalCreation] Inside POSAgentAdd... ");
		
		ArrayList<String> errors = null;
		ArrayList<String> messages = null;
		try {
			requestJSON = new JSONObject();
			responseJSON = new JSONObject();
			requestDTO = new RequestDTO();
			responseDTO = new ResponseDTO();
			session = ServletActionContext.getRequest().getSession();
			requestJSON.put(CevaCommonConstants.MAKER_ID,
					session.getAttribute(CevaCommonConstants.MAKER_ID));
					requestJSON.put("remoteip",session.getAttribute("REMOTE_IP"));

			
				
				requestDTO.setRequestJSON(requestJSON);
			    logger.debug("[POSAction][AgentRegModify]  Request DTO [" + requestDTO + "]");
			  
				
				responseDTO = new CampaignManagementDAO()
						.searchEmailid(requestDTO);
			  if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get("VewDetails");
				logger.debug("[POSAction][AgentRegModify]  Response JSON++++++++ kailash [" + responseJSON + "]");
				
					
				
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
			logger.debug("[POSAction][POSAgentAdd] Exception in View details ["
					+ e.getMessage() + "]");
			addActionError("Internal error occured.");
		} finally {
			
		 		if(requestDTO!=null)
				 requestDTO = null;
		 		if(responseDTO!=null)
				 responseDTO = null;
		 		if(requestJSON!=null)
				 requestJSON = null;
		 		if(messages!=null)
				 messages = null;
		 		if(errors!=null)
				 errors = null;
		}

		return result;
	}
	

	
	public JSONObject getRequestJSON() {
		return requestJSON;
	}

	public void setRequestJSON(JSONObject requestJSON) {
		this.requestJSON = requestJSON;
	}

	public JSONObject getResJSON() {
		return resJSON;
	}

	public void setResJSON(JSONObject resJSON) {
		this.resJSON = resJSON;
	}



	public String getTemplateName() {
		return templateName;
	}



	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getCmpMessage() {
		return cmpMessage;
	}



	public void setCmpMessage(String cmpMessage) {
		this.cmpMessage = cmpMessage;
	}



	public String getRmrk() {
		return rmrk;
	}



	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
	}





	public JSONObject getResponseJSON() {
		return responseJSON;
	}





	public void setResponseJSON(JSONObject responseJSON) {
		this.responseJSON = responseJSON;
	}





	public String getTempId() {
		return tempId;
	}





	public void setTempId(String tempId) {
		this.tempId = tempId;
	}





	public String getTemplate() {
		return template;
	}





	public void setTemplate(String template) {
		this.template = template;
	}





	public String getCreatedby() {
		return createdby;
	}





	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}





	public String getCreatedate() {
		return createdate;
	}





	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}





	public String getCampaignFilter() {
		return campaignFilter;
	}





	public void setCampaignFilter(String campaignFilter) {
		this.campaignFilter = campaignFilter;
	}

	
	
}
