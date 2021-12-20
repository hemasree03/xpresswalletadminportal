package com.ceva.base.action;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.cave.base.dao.WalletAgentDao;
import com.cave.base.dao.WalletModifyAgentDao;
import com.ceva.base.common.dao.AgentDAO;
import com.ceva.base.common.dto.RequestDTO;
import com.ceva.base.common.dto.ResponseDTO;
import com.ceva.base.common.utils.CevaCommonConstants;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class WalletAgentModifyAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	
Logger logger = Logger.getLogger(WalletAgentModifyAction.class);
	
	private HttpSession session = null;	

	JSONObject requestJSON = null;
	JSONObject responseJSON = null;

	RequestDTO requestDTO = null;
	ResponseDTO responseDTO = null;
	String result=null;
	private String customercode;
	private String application;
	 private String marital;

	public String getMarital() {
		return marital;
	}
	public void setMarital(String marital) {
		this.marital = marital;
	}

	private String agenttype;
	private String fname;
	 private String lname;
	 private String dob;
	 private String mnumber;
	 private String mailid;
	 private String gender;
	 
	 private String middlename;
	 private String lastname;
	 private String dateofbirth;
	 private String email;
	 private String merchanttype;
	 private String telephone;
	 private String idtype;
	private String firstname;
	 private String iddetails;
	 private String address;
	 private String nationality;
	 private String area;
	 private String city;
	 private String state;
	 private String lga;
	 private String wardname;
	 private String fullname;
	 
	 private String bankaccnumber;
	 private String accname;
	 
	 private String mcc;
	 private String business;
	 private String staffcode;
	 private String accbvn;
	 
	 
	 
	 public String getAccbvn() {
		return accbvn;
	}
	public void setAccbvn(String accbvn) {
		this.accbvn = accbvn;
	}
	public String getStaffcode() {
		return staffcode;
	}
	public void setStaffcode(String staffcode) {
		this.staffcode = staffcode;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getBankaccnumber() {
		return bankaccnumber;
	}
	public void setBankaccnumber(String bankaccnumber) {
		this.bankaccnumber = bankaccnumber;
	}
	public String getAccname() {
		return accname;
	}
	public void setAccname(String accname) {
		this.accname = accname;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	private String country;
	 private String bankname;
	 private String bankcustname;
	 private String bankaccountnumber;
	 private String bvn;
	 
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
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMerchanttype() {
		return merchanttype;
	}
	public void setMerchanttype(String merchanttype) {
		this.merchanttype = merchanttype;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	

	public String getAgenttype() {
		return agenttype;
	}
	public void setAgenttype(String agenttype) {
		this.agenttype = agenttype;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMnumber() {
		return mnumber;
	}
	public void setMnumber(String mnumber) {
		this.mnumber = mnumber;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getIddetails() {
		return iddetails;
	}
	public void setIddetails(String iddetails) {
		this.iddetails = iddetails;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getWardname() {
		return wardname;
	}
	public void setWardname(String wardname) {
		this.wardname = wardname;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankcustname() {
		return bankcustname;
	}
	public void setBankcustname(String bankcustname) {
		this.bankcustname = bankcustname;
	}
	public String getBankaccountnumber() {
		return bankaccountnumber;
	}
	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}
	public String getBvn() {
		return bvn;
	}
	public void setBvn(String bvn) {
		this.bvn = bvn;
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
	
	public String AgentUpdateLockSearch() throws Exception
	{
		logger.debug("Inside AgentRegModifySearch..");
		WalletModifyAgentDao agdao=null;
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
			agdao = new WalletModifyAgentDao();
			responseDTO= agdao.AgentUpdatePinSearch(requestDTO);
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
			if(agdao!=null)
				 agdao = null;
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
	public String agentModifyDetails() {
		
		logger.debug("Inside agentModifyRegistration Info .. ");
		ArrayList<String> errors = null;
		ArrayList<String> succesmessage = null;
		WalletModifyAgentDao WADAO = null;
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

			WADAO = new WalletModifyAgentDao();
			responseDTO = WADAO.agentModifyDetails(requestDTO);
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
	
	public String linkedAccountModify() throws Exception
	{
		logger.debug("Inside AgentRegModifySearch..");
		WalletModifyAgentDao agdao=null;
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
			agdao = new WalletModifyAgentDao();
			responseDTO= agdao.linkedAccountModify(requestDTO);
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
			if(agdao!=null)
				 agdao = null;
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
	
	
	public String cashoutAccountModify() throws Exception
	{
		logger.debug("Inside AgentRegModifySearch..");
		WalletModifyAgentDao agdao=null;
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
			agdao = new WalletModifyAgentDao();
			responseDTO= agdao.cashoutAccountModify(requestDTO);
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
			if(agdao!=null)
				 agdao = null;
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
	
	public String linkedAccountDetails() {
		
		logger.debug("Inside agentModifyRegistration Info .. ");
		ArrayList<String> errors = null;
		ArrayList<String> succesmessage = null;
		WalletModifyAgentDao WADAO = null;
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

			WADAO = new WalletModifyAgentDao();
			responseDTO = WADAO.linkedAccountDetails(requestDTO);
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
	
	public String SuperAgentApprovalSearch()
	{
		logger.debug("Inside AgentRegModifySearch.kk.");
		WalletModifyAgentDao agdao=null;
		ArrayList<String> errors = null;
		try {
			requestJSON = new JSONObject();
			requestDTO = new RequestDTO();
			
			session = ServletActionContext.getRequest().getSession();

			requestJSON.put(CevaCommonConstants.MAKER_ID,
								session.getAttribute(CevaCommonConstants.MAKER_ID));
								requestJSON.put("remoteip",session.getAttribute("REMOTE_IP"));
			
			requestJSON.put("customercode", customercode);
			
			requestDTO.setRequestJSON(requestJSON);
			logger.debug("Request DTO [" + requestDTO + "]");
			agdao = new WalletModifyAgentDao();
			responseDTO= agdao.SuperAgentApprovalSearch(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");
			if (responseDTO != null && responseDTO.getErrors().size() == 0) {
				responseJSON = (JSONObject) responseDTO.getData().get("Files_List");
				//logger.debug("Response JSON***************************************** [" + responseJSON + "]");
				responseJSON.put("application", application);
				responseJSON.put("customercode", customercode);
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
			if(agdao!=null)
				 agdao = null;
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
	
	public String cashoutAccountView() throws Exception
	{
		logger.debug("Inside AgentRegModifySearch..");
		WalletModifyAgentDao agdao=null;
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
			agdao = new WalletModifyAgentDao();
			responseDTO= agdao.cashoutAccountView(requestDTO);
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
			if(agdao!=null)
				 agdao = null;
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
	
	public String CashoutAccountDetails() {
		
		logger.debug("Inside agentModifyRegistration Info .. ");
		ArrayList<String> errors = null;
		ArrayList<String> succesmessage = null;
		WalletModifyAgentDao WADAO = null;
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

			WADAO = new WalletModifyAgentDao();
			responseDTO = WADAO.CashoutAccountDetails(requestDTO);
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
	
	
	
	public String CashoutAccountModifyDetails() {
		
		logger.debug("Inside agentModifyRegistration Info .. ");
		ArrayList<String> errors = null;
		ArrayList<String> succesmessage = null;
		WalletModifyAgentDao WADAO = null;
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

			WADAO = new WalletModifyAgentDao();
			responseDTO = WADAO.CashoutAccountModifyDetails(requestDTO);
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
	
	
	
}
