package com.ceva.base.action;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ceva.base.common.dto.RequestDTO;
import com.ceva.base.common.dto.ResponseDTO;
import com.cave.base.dao.ConfigDao;

import com.ceva.base.common.utils.CevaCommonConstants;
import com.opensymphony.xwork2.ActionSupport;

public class ConfigCreateAction  extends ActionSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(ConfigCreateAction.class);

	JSONObject requestJSON = null;
	JSONObject responseJSON = null;

	RequestDTO requestDTO = null;
	ResponseDTO responseDTO = null;
	String result=null;
	
	private String limitCode,limitDescription,finaljson;
	private String feefinaljson,feeCode,feeDescription;
	
	private HttpSession session = null;
	
	 private String limitDesc;
	 private String trrefno;
	 private String linkmode;
	 
	 
	 private String transaction;
	 private String frequency;
	 private String maxcount;
	 private String minamount;
	 private String maxamount;
	 private String seqno;
	 
	 private String flatpercentile;
	 private String fpValue;
	 private String criteria;
	 private String fromvalue;
	 private String tovalue;
	 
	 private String productcode;	 
	 private String productdesc;
	 private String productcurr;
	 private String application;
	 
	 private String agent;
	 private String ceva;
	 private String bank;
	 private String channel;
	 private String SuperAgent;
	 private String VAT;
	 private String subagent;
	 private String thirdparty;
	 private String qt;
	 private  String productname;
	 private  String  product;
	 
	 
	 
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getQt() {
		return qt;
	}
	public void setQt(String qt) {
		this.qt = qt;
	}
	public String getThirdparty() {
		return thirdparty;
	}
	public void setThirdparty(String thirdparty) {
		this.thirdparty = thirdparty;
	}
	public String getSubagent() {
		return subagent;
	}
	public void setSubagent(String subagent) {
		this.subagent = subagent;
	}
	public String getSuperAgent() {
		return SuperAgent;
	}
	public void setSuperAgent(String superAgent) {
		SuperAgent = superAgent;
	}
	public String getVAT() {
		return VAT;
	}
	public void setVAT(String vAT) {
		VAT = vAT;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getCeva() {
		return ceva;
	}
	public void setCeva(String ceva) {
		this.ceva = ceva;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getProductdesc() {
		return productdesc;
	}
	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}
	public String getProductcurr() {
		return productcurr;
	}
	public void setProductcurr(String productcurr) {
		this.productcurr = productcurr;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	

	public String getFlatpercentile() {
		return flatpercentile;
	}
	public void setFlatpercentile(String flatpercentile) {
		this.flatpercentile = flatpercentile;
	}
	public String getFpValue() {
		return fpValue;
	}
	public void setFpValue(String fpValue) {
		this.fpValue = fpValue;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public String getFromvalue() {
		return fromvalue;
	}
	public void setFromvalue(String fromvalue) {
		this.fromvalue = fromvalue;
	}
	public String getTovalue() {
		return tovalue;
	}
	public void setTovalue(String tovalue) {
		this.tovalue = tovalue;
	}
	public String getMaxcount() {
		return maxcount;
	}
	public void setMaxcount(String maxcount) {
		this.maxcount = maxcount;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getMinamount() {
		return minamount;
	}
	public void setMinamount(String minamount) {
		this.minamount = minamount;
	}
	public String getMaxamount() {
		return maxamount;
	}
	public void setMaxamount(String maxamount) {
		this.maxamount = maxamount;
	}
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getLinkmode() {
		return linkmode;
	}
	public void setLinkmode(String linkmode) {
		this.linkmode = linkmode;
	}
	public String getLimitDesc() {
		return limitDesc;
	}
	public void setLimitDesc(String limitDesc) {
		this.limitDesc = limitDesc;
	}
	public String getTrrefno() {
		return trrefno;
	}
	public void setTrrefno(String trrefno) {
		this.trrefno = trrefno;
	}
	public String getFeefinaljson() {
		return feefinaljson;
	}
	public void setFeefinaljson(String feefinaljson) {
		this.feefinaljson = feefinaljson;
	}
	public String getLimitCode() {
		return limitCode;
	}
	public void setLimitCode(String limitCode) {
		this.limitCode = limitCode;
	}
	public String getLimitDescription() {
		return limitDescription;
	}
	public void setLimitDescription(String limitDescription) {
		this.limitDescription = limitDescription;
	}
	public String getFeeCode() {
		return feeCode;
	}
	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}
	public String getFeeDescription() {
		return feeDescription;
	}
	public void setFeeDescription(String feeDescription) {
		this.feeDescription = feeDescription;
	}
		
	public String getFinaljson() {
		return finaljson;
	}
	public void setFinaljson(String finaljson) {
		this.finaljson = finaljson;
	}
	public JSONObject getRequestJSON() {
		return requestJSON;
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


	
 
 



public String FirstPageView() {
	
	logger.debug(" FirstPageView ....");

	return "success";

}







public String commonScreen() {
	logger.debug("Inside GetCommonScreen...");

	result = "success";
	return result;
} 			






public String initialproduct(){
 
	ConfigDao cfgDAO = null;
ArrayList<String> errors = null;
try {
	requestJSON = new JSONObject();
	requestDTO = new RequestDTO();
	requestDTO.setRequestJSON(requestJSON);
			/* ConfigDao = new ConfigDao(); */
	cfgDAO = new ConfigDao();
	
	
	responseDTO = cfgDAO.initialproduct(requestDTO);
	logger.debug("Response DTO [" + responseDTO + "]");
	if (responseDTO != null && responseDTO.getErrors().size() == 0) {
		responseJSON = (JSONObject) responseDTO.getData().get("Files_List1");
		logger.debug("[getLocationList][responseJSON:::::"
				+ responseJSON + "]");
		
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
	cfgDAO = null;
	errors = null;
}

return result;
}







public String initialproductConfirm() {

requestJSON = new JSONObject();
responseJSON = new JSONObject();

requestJSON.put("product", product);
requestJSON.put("productname", productname);


responseJSON.put("product", product);
responseJSON.put("productname", productname);



logger.info("product ["+product+"] productname ["+productname+"]");

logger.info("product ["+product+"] productname ["+productname+"]");



return "success";


} 



public String initialprdack() {


logger.debug("Action method  initialprdack");

ConfigDao cfgDAO = null;
ArrayList<String> errors = null;

try {
	
	requestJSON = new JSONObject();
	requestDTO = new RequestDTO();
	responseJSON = new JSONObject();

	requestJSON.put("product", product);
	requestJSON.put("productname", productname);
	
	
	session = ServletActionContext.getRequest().getSession();

	requestJSON.put(CevaCommonConstants.MAKER_ID,session.getAttribute(CevaCommonConstants.MAKER_ID));
	requestJSON.put("remoteip",session.getAttribute("REMOTE_IP"));
	
	
	
	requestDTO.setRequestJSON(requestJSON);
	logger.debug("Request DTO [" + requestDTO + "]");

	cfgDAO = new ConfigDao();
	responseDTO = cfgDAO.initialprdack(requestDTO);

	logger.debug("Response DTO [" + responseDTO + "]");
	if (responseDTO != null && responseDTO.getErrors().size() == 0) {

		//responseJSON = (JSONObject) responseDTO.getData().get("LIMIT_INFO");
		logger.debug("Response JSON  [" + responseDTO.getMessages() + "]");
		
		responseJSON.put("product", product);
		responseJSON.put("productname", productname);
		
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
	logger.debug("Exception in binCommonScreen  [" + e.getMessage()
			+ "]");
	addActionError("Internal error occured.");
} finally {
	requestDTO = null;
	responseDTO = null;
	requestJSON = null;
	cfgDAO = null;
	errors = null;
}


return "success";


}

 
}