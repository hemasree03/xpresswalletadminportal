package com.ceva.base.action;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.cave.base.dao.WalletAgentDao;
import com.ceva.base.common.dto.RequestDTO;
import com.ceva.base.common.dto.ResponseDTO;
import com.ceva.base.common.utils.CevaCommonConstants;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class WalletAgentAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	
Logger logger = Logger.getLogger(WalletAgentAction.class);
	
	private HttpSession session = null;	

	JSONObject requestJSON = null;
	JSONObject responseJSON = null;

	RequestDTO requestDTO = null;
	ResponseDTO responseDTO = null;
	String result=null;
	
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
	
	private String REQUEST_ID = null;
	private String MOBILE_NUMBER = null;
	private String TRANS_DATE = null;
	private String REG_STATUS = null;
	private String AMOUNT = null;
	private String accNumbers;
	private String	limitCode = null;
	private String  WALLET_ACC = null;
	private String PAYMENT_REF_NO = null;
	private String termilstatus = null;
	private String REASON = null;
	private String REQ_DATE = null;
	
	private String customercode = null;
	private String application = null;
	
	private String fullname = null;
	private String middlename = null;
	private String lastname = null;
	private String telephone = null;
	private String walletaccountno = null;
	private String bonusamt = null;
	private String paymentref = null;
	private String email = null;
	
	private String reason = null;
	private String requesttype = null;
	private String referenceno = null;
	private String monthyear =null;
	
	

	public String getMonthyear() {
		return monthyear;
	}
	public void setMonthyear(String monthyear) {
		this.monthyear = monthyear;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRequesttype() {
		return requesttype;
	}
	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}
	public String getReferenceno() {
		return referenceno;
	}
	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getWalletaccountno() {
		return walletaccountno;
	}
	public void setWalletaccountno(String walletaccountno) {
		this.walletaccountno = walletaccountno;
	}
	public String getBonusamt() {
		return bonusamt;
	}
	public void setBonusamt(String bonusamt) {
		this.bonusamt = bonusamt;
	}
	public String getPaymentref() {
		return paymentref;
	}
	public void setPaymentref(String paymentref) {
		this.paymentref = paymentref;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}
	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}
	public String getCustomercode() {
		return customercode;
	}
	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getREQUEST_ID() {
		return REQUEST_ID;
	}
	public void setREQUEST_ID(String rEQUEST_ID) {
		REQUEST_ID = rEQUEST_ID;
	}
	public String getMOBILE_NUMBER() {
		return MOBILE_NUMBER;
	}
	public void setMOBILE_NUMBER(String mOBILE_NUMBER) {
		MOBILE_NUMBER = mOBILE_NUMBER;
	}
	public String getTRANS_DATE() {
		return TRANS_DATE;
	}
	public void setTRANS_DATE(String tRANS_DATE) {
		TRANS_DATE = tRANS_DATE;
	}
	public String getREG_STATUS() {
		return REG_STATUS;
	}
	public void setREG_STATUS(String rEG_STATUS) {
		REG_STATUS = rEG_STATUS;
	}
	public String getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getAccNumbers() {
		return accNumbers;
	}
	public void setAccNumbers(String accNumbers) {
		this.accNumbers = accNumbers;
	}
	public String getLimitCode() {
		return limitCode;
	}
	public void setLimitCode(String limitCode) {
		this.limitCode = limitCode;
	}
	public String getWALLET_ACC() {
		return WALLET_ACC;
	}
	public void setWALLET_ACC(String wALLET_ACC) {
		WALLET_ACC = wALLET_ACC;
	}
	public String getPAYMENT_REF_NO() {
		return PAYMENT_REF_NO;
	}
	public void setPAYMENT_REF_NO(String pAYMENT_REF_NO) {
		PAYMENT_REF_NO = pAYMENT_REF_NO;
	}
	public String getTermilstatus() {
		return termilstatus;
	}
	public void setTermilstatus(String termilstatus) {
		this.termilstatus = termilstatus;
	}
	public String getREASON() {
		return REASON;
	}
	public void setREASON(String rEASON) {
		REASON = rEASON;
	}
	public String getREQ_DATE() {
		return REQ_DATE;
	}
	public void setREQ_DATE(String rEQ_DATE) {
		REQ_DATE = rEQ_DATE;
	}
	public String blankPage() {
		
		logger.debug(" FirstPageView ....");

		return "success";

    }
	
	private HttpServletRequest httpRequest = null;

	@Override
	public void setServletRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}
	
	public String confirmationpage() {
		
		logger.debug(" FirstPageView ....");
		
		Enumeration enumParams = null;
		logger.debug("Inside ConstructToResponseJson...");
		
		try {
			responseJSON = new JSONObject();
			
			enumParams = httpRequest.getParameterNames();
			while (enumParams.hasMoreElements()) {
				String key = (String) enumParams.nextElement();
				String val = httpRequest.getParameter(key);
				//System.out.println(key+"---"+ val);
				responseJSON.put(key, val);
			}

		} catch (Exception e) {
			logger.debug(" Exception in ConstructToResponseJson ["
					+ e.getMessage() + "]");
			e.printStackTrace();
		} finally {
			enumParams = null;
		}

		return "success";

    }
	
	public String agentRegistration() {
		
		logger.debug("Inside Insert New Account Info .. ");
		ArrayList<String> errors = null;
		ArrayList<String> succesmessage = null;
		WalletAgentDao WADAO = null;
		Enumeration enumParams = null;
		try {
			session = ServletActionContext.getRequest().getSession();

			requestDTO = new RequestDTO();
			requestJSON = new JSONObject();

			enumParams = httpRequest.getParameterNames();
			while (enumParams.hasMoreElements()) {
				String key = (String) enumParams.nextElement();
				String val = httpRequest.getParameter(key);
				requestJSON.put(key, val);
			}

			requestJSON.put(CevaCommonConstants.MAKER_ID,
					session.getAttribute(CevaCommonConstants.MAKER_ID));
			requestJSON.put(CevaCommonConstants.IP, ServletActionContext
					.getRequest().getRemoteAddr());
			requestDTO.setRequestJSON(requestJSON);

			WADAO = new WalletAgentDao();
			responseDTO = WADAO.agentRegistration(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				
				succesmessage = (ArrayList<String>) responseDTO.getMessages();
				for (int i = 0; i < succesmessage.size(); i++) {
					addActionMessage(succesmessage.get(i));
				}
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
			logger.debug("Exception in Account Fetch Details ["
					+ e.getMessage() + "]");
			e.printStackTrace();
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			errors = null;
		}
		return result;
	}
	
public String superAgentRegistration() {
		
		logger.debug("Inside Insert New Account Info .. ");
		ArrayList<String> errors = null;
		ArrayList<String> succesmessage = null;
		WalletAgentDao WADAO = null;
		Enumeration enumParams = null;
		try {
			session = ServletActionContext.getRequest().getSession();

			requestDTO = new RequestDTO();
			requestJSON = new JSONObject();

			enumParams = httpRequest.getParameterNames();
			while (enumParams.hasMoreElements()) {
				String key = (String) enumParams.nextElement();
				String val = httpRequest.getParameter(key);
				requestJSON.put(key, val);
			}

			requestJSON.put(CevaCommonConstants.MAKER_ID,
					session.getAttribute(CevaCommonConstants.MAKER_ID));
			requestJSON.put(CevaCommonConstants.IP, ServletActionContext
					.getRequest().getRemoteAddr());
			requestDTO.setRequestJSON(requestJSON);

			WADAO = new WalletAgentDao();
			responseDTO = WADAO.superAgentRegistration(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				
				succesmessage = (ArrayList<String>) responseDTO.getMessages();
				for (int i = 0; i < succesmessage.size(); i++) {
					addActionMessage(succesmessage.get(i));
				}
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
			logger.debug("Exception in Account Fetch Details ["
					+ e.getMessage() + "]");
			e.printStackTrace();
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			errors = null;
		}
		return result;
	}
	
public String accountOpen() {
		
		logger.debug("Inside Insert New Account Info .. ");
		ArrayList<String> errors = null;
		ArrayList<String> succesmessage = null;
		WalletAgentDao WADAO = null;
		Enumeration enumParams = null;
		try {
			session = ServletActionContext.getRequest().getSession();

			requestDTO = new RequestDTO();
			requestJSON = new JSONObject();

			enumParams = httpRequest.getParameterNames();
			while (enumParams.hasMoreElements()) {
				String key = (String) enumParams.nextElement();
				String val = httpRequest.getParameter(key);
				requestJSON.put(key, val);
			}

			requestJSON.put(CevaCommonConstants.MAKER_ID,
					session.getAttribute(CevaCommonConstants.MAKER_ID));
			requestJSON.put(CevaCommonConstants.IP, ServletActionContext
					.getRequest().getRemoteAddr());
			requestDTO.setRequestJSON(requestJSON);

			WADAO = new WalletAgentDao();
			responseDTO = WADAO.accountOpen(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				
				succesmessage = (ArrayList<String>) responseDTO.getMessages();
				for (int i = 0; i < succesmessage.size(); i++) {
					addActionMessage(succesmessage.get(i));
				}
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
			logger.debug("Exception in Account Fetch Details ["
					+ e.getMessage() + "]");
			e.printStackTrace();
		} finally {
			requestDTO = null;
			responseDTO = null;
			requestJSON = null;
			errors = null;
		}
		return result;
	}

public String bvnvalidateInfo(){
	logger.debug(" [ BranchCreateAction ] [ branchInfo ] Inside branchInfo Action.. ");
	WalletAgentDao WADAO = null;
	ArrayList<String> errors = null;
	Enumeration enumParams = null;
	try {
		requestJSON = new JSONObject();
		requestDTO = new RequestDTO();
		requestDTO.setRequestJSON(requestJSON);
		WADAO = new WalletAgentDao();
		
		enumParams = httpRequest.getParameterNames();
		while (enumParams.hasMoreElements()) {
			String key = (String) enumParams.nextElement();
			String val = httpRequest.getParameter(key);
			requestJSON.put(key, val);
		}
		
		responseDTO = WADAO.bvnvalidateInfo(requestDTO);
		logger.debug("Response DTO [" + responseDTO + "]");
		if (responseDTO != null && responseDTO.getErrors().size() == 0) {
			responseJSON = (JSONObject) responseDTO.getData().get("BVN_DETAILS");
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
		WADAO = null;
		errors = null;
	}

	return result;
}


public String proccedInfo(){
	
	WalletAgentDao WADAO = null;
	ArrayList<String> errors = null;
	try {
		requestJSON = new JSONObject();
		requestDTO = new RequestDTO();
		requestDTO.setRequestJSON(requestJSON);
		WADAO = new WalletAgentDao();
		
		responseDTO = WADAO.proccedInfo(requestDTO);
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
		WADAO = null;
		errors = null;
	}

	return result;
}


public String countInfo(){
	
	WalletAgentDao WADAO = null;
	ArrayList<String> errors = null;
	try {
		requestJSON = new JSONObject();
		requestDTO = new RequestDTO();
		requestDTO.setRequestJSON(requestJSON);
		WADAO = new WalletAgentDao();
		
		responseDTO = WADAO.countInfo(requestDTO);
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
		WADAO = null;
		errors = null;
	}

	return result;
}



public String closecountConfig(){
	
	WalletAgentDao WADAO = null;
	ArrayList<String> errors = null;
	try {
		requestJSON = new JSONObject();
		requestDTO = new RequestDTO();
		requestDTO.setRequestJSON(requestJSON);
		WADAO = new WalletAgentDao();
		
		responseDTO = WADAO.closecountConfig(requestDTO);
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
		WADAO = null;
		errors = null;
	}

	return result;
}




public String processcountConfig(){
	
	WalletAgentDao WADAO = null;
	ArrayList<String> errors = null;
	try {
		requestJSON = new JSONObject();
		requestDTO = new RequestDTO();
		requestDTO.setRequestJSON(requestJSON);
		WADAO = new WalletAgentDao();
		
		responseDTO = WADAO.processcountConfig(requestDTO);
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
		WADAO = null;
		errors = null;
	}

	return result;
}



public String countView(){
 
WalletAgentDao WADAO = null;
ArrayList<String> errors = null;
try {
	requestJSON = new JSONObject();
	requestDTO = new RequestDTO();
	requestDTO.setRequestJSON(requestJSON);
	
	
	session = ServletActionContext.getRequest().getSession();

	requestJSON.put(CevaCommonConstants.MAKER_ID,
						session.getAttribute(CevaCommonConstants.MAKER_ID));
	requestJSON.put("remoteip",session.getAttribute("REMOTE_IP"));
	
	

	
	WADAO = new WalletAgentDao();
	responseDTO = WADAO.countView(requestDTO);
	logger.debug("Response DTO [" + responseDTO + "]");
	if (responseDTO != null && responseDTO.getErrors().size() == 0) {
		responseJSON = (JSONObject) responseDTO.getData().get("LMT_FEE_INFO");
		logger.debug("Response JSON  [" + responseJSON + "]");
		
		
		responseJSON.put(CevaCommonConstants.MAKER_ID,
				session.getAttribute(CevaCommonConstants.MAKER_ID));
		
		//responseJSON.put("trans",trans);
		
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
	logger.debug("Exception in binCommonScreen  ["
			+ e.getMessage() + "]");
	addActionError("Internal error occured.");
} finally {
	requestDTO = null;
	responseDTO = null;
	requestJSON = null;
	WADAO = null;
	errors = null;
}

return result;
}




public String pendingView() {

	/* ProductManDAO productDAO = null; */
	WalletAgentDao WADAO = null;

	logger.debug("inside binCommonScreen.. ");

	ArrayList<String> errors = null;
	try {
		requestJSON = new JSONObject();
		requestDTO = new RequestDTO();
		requestJSON.put(CevaCommonConstants.LIMIT_CODE, limitCode);
		requestDTO.setRequestJSON(requestJSON);
		logger.debug("Request DTO [" + requestDTO + "]");

WADAO = new WalletAgentDao();
		responseDTO = WADAO.pendingView(requestDTO);
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





public String Pendingststusupdation() {
	 
	 
	 logger.debug("Inside GetCommonScreen...change");
	 
	
	 WalletAgentDao WADAO = null;

		logger.debug("inside binCommonScreen.. ");

		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();
			requestJSON.put(CevaCommonConstants.LIMIT_CODE, REQUEST_ID);
			requestJSON.put(CevaCommonConstants.status, REG_STATUS);
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			WADAO = new WalletAgentDao();
			responseDTO = WADAO.Pendingststusupdation(requestDTO);
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

 




public String closeInfo(){
	logger.debug(" [ BranchCreateAction ] [ branchInfo ] Inside branchInfo Action.. ");
	WalletAgentDao WADAO = null;
	ArrayList<String> errors = null;
	try {
		requestJSON = new JSONObject();
		requestDTO = new RequestDTO();
		requestDTO.setRequestJSON(requestJSON);
		WADAO = new WalletAgentDao();
		
		responseDTO = WADAO.closeInfo(requestDTO);
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
		WADAO = null;
		errors = null;
	}

	return result;
}



public String pendingInfo(){
	logger.debug(" [ BranchCreateAction ] [ branchInfo ] Inside branchInfo Action.. ");
	WalletAgentDao WADAO = null;
	ArrayList<String> errors = null;
	try {
		requestJSON = new JSONObject();
		requestDTO = new RequestDTO();
		requestDTO.setRequestJSON(requestJSON);
		WADAO = new WalletAgentDao();
		
		responseDTO = WADAO.pendingInfo(requestDTO);
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
		WADAO = null;
		errors = null;
	}

	return result;
}


public String Agentcustomerview()
{
	logger.debug("Inside AgentRegModifySearch..");
	WalletAgentDao WADAO = null;
	ArrayList<String> errors = null;
	try {
		requestJSON = new JSONObject();
		requestDTO = new RequestDTO();
		
		session = ServletActionContext.getRequest().getSession();

		requestJSON.put(CevaCommonConstants.MAKER_ID,
							session.getAttribute(CevaCommonConstants.MAKER_ID));
							requestJSON.put("remoteip",session.getAttribute("REMOTE_IP"));
		
		requestJSON.put("customercode", customercode);
		requestJSON.put("application", application);
		
		requestDTO.setRequestJSON(requestJSON);
		logger.debug("Request DTO [" + requestDTO + "]");
		WADAO = new WalletAgentDao();
		responseDTO = WADAO.Agentcustomerview(requestDTO);
		logger.debug("Response DTO [" + responseDTO + "]");
		if (responseDTO != null && responseDTO.getErrors().size() == 0) {
			responseJSON = (JSONObject) responseDTO.getData().get("Files_List");
			
			//logger.debug("Response JSON***************************************** [" + responseJSON + "]");
			responseJSON.put("application", application);
			
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
		logger.debug("Exception in GetProcessingCodeViewDetails ["
				+ e.getMessage() + "]");
		addActionError("Internal error occured.");
	} finally {
		if(WADAO!=null)
			WADAO = null;
		 if(requestDTO!=null)
			 requestDTO = null;
		 if(responseDTO!=null)
			 responseDTO = null;
		 if(requestJSON!=null)
			 requestJSON = null;
		 if(errors!=null)
			 errors = null;
	}

	return result;
}

public String agentrequestbonusackAck() {
	 logger.debug("[AgentRegAck][AgentRegAck] Inside AgentRegAck ..");
	 
	 
	 WalletAgentDao WADAO = null;
	 ArrayList<String> errors = null;
	 
	 try {
		 requestJSON = new JSONObject();
			responseJSON = new JSONObject();
			requestDTO = new RequestDTO();
			responseDTO = new ResponseDTO();
			session = ServletActionContext.getRequest().getSession();
			requestJSON.put(CevaCommonConstants.MAKER_ID,
					session.getAttribute(CevaCommonConstants.MAKER_ID));
			requestJSON.put("remoteip",session.getAttribute("REMOTE_IP"));
			
			requestJSON.put("fullname", fullname);
			requestJSON.put("middlename", middlename);
			
			requestJSON.put("lastname", lastname);
			requestJSON.put("telephone", telephone);
			requestJSON.put("walletaccountno", walletaccountno);
			requestJSON.put("bonusamt", bonusamt);
			requestJSON.put("paymentref", paymentref);
			requestJSON.put("email", email);
			requestJSON.put("monthyear", monthyear);
			
			
	  requestDTO.setRequestJSON(requestJSON);

	  logger.debug("[AgentRegAck][AgentRegAck] Request DTO [" + requestDTO + "]");

	     WADAO = new WalletAgentDao();
		responseDTO = WADAO.agentrequestbonusackAck(requestDTO);
	  logger.debug("Response DTO is ["+responseDTO+"]");
	  if (responseDTO != null && responseDTO.getErrors().size() == 0) {
		  
		 
			
			responseJSON.put("fullname", fullname);
			responseJSON.put("middlename", middlename);
			
			responseJSON.put("lastname", lastname);
			responseJSON.put("telephone", telephone);
			responseJSON.put("walletaccountno", walletaccountno);
			responseJSON.put("bonusamt", bonusamt);
			responseJSON.put("paymentref", paymentref);
			responseJSON.put("email", email);
			
	

	   result = "success";
	  } else {
	   errors = (ArrayList<String>) responseDTO
	     .getErrors();
	   for (int i = 0; i < errors.size(); i++) {
	    addActionError(errors.get(i));
	   }
	   result = "fail";
	  }
	 } catch (Exception e) {
	  result = "fail";
	  logger.debug("[SalaryParameterAction][DefineReturnCodesAck]Exception in search ["+e.getMessage()+"]");
	  addActionError("Internal error occured.");
	 } finally{
		 if(WADAO!=null)
			 WADAO = null;
		 if(requestDTO!=null)
			 requestDTO = null;
		 if(responseDTO!=null)
			 responseDTO = null;
		 if(requestJSON!=null)
			 requestJSON = null;
		 if(errors!=null)
			 errors = null;
	 }

	 return result;

	}


public String agentbonusapprovalinfo(){
	 
	WalletAgentDao WADAO = null;
	ArrayList<String> errors = null;
	try {
		requestJSON = new JSONObject();
		requestDTO = new RequestDTO();
		requestDTO.setRequestJSON(requestJSON);
		WADAO = new WalletAgentDao();
	   responseDTO = WADAO.agentbonusapprovalinfo(requestDTO);
		logger.debug("Response DTO [" + responseDTO + "]");
		if (responseDTO != null && responseDTO.getErrors().size() == 0) {
			responseJSON = (JSONObject) responseDTO.getData().get("SUPER_AGENT_INFO");
			logger.debug("Response JSON  [" + responseJSON + "]");
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
		logger.debug("Exception in binCommonScreen  ["
				+ e.getMessage() + "]");
		addActionError("Internal error occured.");
	} finally {
		requestDTO = null;
		responseDTO = null;
		requestJSON = null;
		WADAO = null;
		errors = null;
	}

	return result;
}

public String AgentBonusapprovalinfoView() {

	logger.debug("Inside AgentView... ");
	WalletAgentDao WADAO = null;
	ArrayList<String> errors = null;

	try {
		requestJSON = new JSONObject();
		requestDTO = new RequestDTO();
		requestJSON.put("accNumbers", accNumbers);
		
		
		logger.debug("[Class Name][AgentView][requestDTO : "+requestDTO+"]");
		
		requestDTO.setRequestJSON(requestJSON);

		

		logger.debug("Request DTO [" + requestDTO + "]");
		WADAO = new WalletAgentDao();
   responseDTO = WADAO.AgentBonusapprovalinfoView(requestDTO);
		logger.debug("Response DTO [" + responseDTO + "]");



		if (responseDTO != null && responseDTO.getErrors().size() == 0) {
			responseJSON = (JSONObject) responseDTO.getData().get("user_rights");
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
		logger.debug("Exception in AgentView [" + e.getMessage()
				+ "]");
		addActionError("Internal error occured.");
		e.printStackTrace();
	}
	finally {
		requestDTO = null;
		responseDTO = null;
		requestJSON = null;
		errors = null;
		WADAO = null;
	}

	return result;
}

public String agentbonusapprovalAck() {
	 logger.debug("[AgentRegAck][AgentRegAck] Inside AgentRegAck ..");
	 
	 
	 WalletAgentDao WADAO = null;
	 ArrayList<String> errors = null;
	 
	 try {
		 requestJSON = new JSONObject();
			responseJSON = new JSONObject();
			requestDTO = new RequestDTO();
			responseDTO = new ResponseDTO();
			session = ServletActionContext.getRequest().getSession();
			requestJSON.put(CevaCommonConstants.MAKER_ID,
					session.getAttribute(CevaCommonConstants.MAKER_ID));
			requestJSON.put("remoteip",session.getAttribute("REMOTE_IP"));
			
			requestJSON.put("fullname", fullname);
			requestJSON.put("middlename", middlename);
			
			requestJSON.put("lastname", lastname);
			requestJSON.put("telephone", telephone);
			requestJSON.put("walletaccountno", walletaccountno);
			requestJSON.put("bonusamt", bonusamt);
			requestJSON.put("paymentref", paymentref);
			requestJSON.put("reason", reason);
			requestJSON.put("requesttype", requesttype);
			requestJSON.put("referenceno", referenceno);
			requestJSON.put("email", email);
			requestJSON.put("monthyear", monthyear);
			
			
	  requestDTO.setRequestJSON(requestJSON);

	  logger.debug("[AgentRegAck][AgentRegAck] Request DTO [" + requestDTO + "]");

	     WADAO = new WalletAgentDao();
		responseDTO = WADAO.agentbonusapprovalAck(requestDTO);
	  logger.debug("Response DTO is ["+responseDTO+"]");
	  if (responseDTO != null && responseDTO.getErrors().size() == 0) {
		  
		  responseJSON = (JSONObject) responseDTO.getData().get("user_rights");
          logger.debug("Response JSON [" + responseJSON + "]");

	   result = "success";
	  } else {
	   errors = (ArrayList<String>) responseDTO
	     .getErrors();
	   for (int i = 0; i < errors.size(); i++) {
	    addActionError(errors.get(i));
	   }
	   result = "fail";
	  }
	 } catch (Exception e) {
	  result = "fail";
	  logger.debug("[SalaryParameterAction][DefineReturnCodesAck]Exception in search ["+e.getMessage()+"]");
	  addActionError("Internal error occured.");
	 } finally{
		 if(WADAO!=null)
			 WADAO = null;
		 if(requestDTO!=null)
			 requestDTO = null;
		 if(responseDTO!=null)
			 responseDTO = null;
		 if(requestJSON!=null)
			 requestJSON = null;
		 if(errors!=null)
			 errors = null;
	 }

	 return result;

	}

}
