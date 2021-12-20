package com.cave.base.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ceva.base.common.dto.JsonValueValidator;
import com.ceva.base.common.dto.RequestDTO;
import com.ceva.base.common.dto.ResponseDTO;
import com.ceva.base.common.utils.CevaCommonConstants;
import com.ceva.base.common.utils.DBConnector;
import com.ceva.base.service.utils.dao.CommonServiceDao;
import com.ceva.unionbank.channel.ExternalServices;
import com.ceva.util.DBUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WalletAgentDao {
	private Logger logger = Logger.getLogger(WalletAgentDao.class);

	ResponseDTO responseDTO = null;
	JSONObject requestJSON = null;
	JSONObject responseJSON = null;
	
	public ResponseDTO agentRegistration(RequestDTO requestDTO) {
		Connection connection = null;
		String insQRY = "";
		String ip = null;

		CallableStatement cstmt = null;
		JSONObject resultJson = null;
		String encpin = null;
		String pin = null;

		try {
			responseDTO = new ResponseDTO();
			requestJSON = requestDTO.getRequestJSON();
			
			
			connection = DBConnector.getConnection();
			
			/*System.out.println("fullname -- "+requestJSON.getString("fullname"));
			System.out.println("middlename -- "+requestJSON.getString("middlename"));
			System.out.println("lastname -- "+requestJSON.getString("lastname"));
			System.out.println("dob -- "+requestJSON.getString("dob"));
			System.out.println("email -- "+requestJSON.getString("email"));
			System.out.println("telephone -- "+requestJSON.getString("telephone"));
			System.out.println("gender -- "+requestJSON.getString("gender"));
			System.out.println("marital -- "+requestJSON.getString("marital"));
			System.out.println("merchanttype -- "+requestJSON.getString("merchanttype"));
			System.out.println("businessname -- "+requestJSON.getString("businessname"));
			System.out.println("branch -- "+requestJSON.getString("branch"));
			
			System.out.println("idcardtype -- "+requestJSON.getString("idcardtype"));
			System.out.println("idcardnumber -- "+requestJSON.getString("idcardnumber"));
			System.out.println("addressLine -- "+requestJSON.getString("addressLine"));
			System.out.println("nationality -- "+requestJSON.getString("nationality"));
			System.out.println("streetname -- "+requestJSON.getString("streetname"));
			System.out.println("city -- "+requestJSON.getString("city"));
			System.out.println("state -- "+requestJSON.getString("state"));
			System.out.println("localGovernment -- "+requestJSON.getString("localGovernment"));
			System.out.println("wardname -- "+requestJSON.getString("wardname"));
			System.out.println("country -- "+requestJSON.getString("country"));
			
			System.out.println("bankname -- "+requestJSON.getString("bankname"));
			System.out.println("bankaccnumber -- "+requestJSON.getString("bankaccnumber"));
			System.out.println("accname -- "+requestJSON.getString("accname"));
			System.out.println("bvn -- "+requestJSON.getString("bvn"));
			
			System.out.println("super agent -- "+requestJSON.getString("superagn"));
			System.out.println("product	 -- "+requestJSON.getString("productcode"));
			System.out.println("product description -- "+requestJSON.getString("productdesc"));
			
			
			System.out.println("makerid -- "+requestJSON.getString(CevaCommonConstants.MAKER_ID));
			System.out.println("IP -- "+requestJSON.getString(CevaCommonConstants.IP));*/
			
			
			
				
				

				insQRY = "{call AGENTREGISTRATIONPKG(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

				
				ip = requestJSON.getString(CevaCommonConstants.IP);


				
				
				cstmt = connection.prepareCall(insQRY);
				cstmt.setString(1, requestJSON.getString("fullname"));
				cstmt.setString(2, requestJSON.getString("middlename"));
				cstmt.setString(3, requestJSON.getString("lastname"));
				cstmt.setString(4, requestJSON.getString("dob"));
				cstmt.setString(5, requestJSON.getString("email"));
				cstmt.setString(6, requestJSON.getString("telephone"));
				cstmt.setString(7, requestJSON.getString("gender"));
				cstmt.setString(8, requestJSON.getString("marital"));			
				cstmt.setString(9, requestJSON.getString("merchanttype"));
				cstmt.setString(10, JsonValueValidator.stripXSSRev(requestJSON.getString("businessname")));
				cstmt.setString(11, requestJSON.getString("branch"));
				
				cstmt.setString(12, requestJSON.getString("idcardtype"));
				cstmt.setString(13, requestJSON.getString("idcardnumber"));
				cstmt.setString(14, requestJSON.getString("addressLine")); 
				cstmt.setString(15, requestJSON.getString("nationality"));			
				cstmt.setString(16, requestJSON.getString("streetname"));
				cstmt.setString(17, requestJSON.getString("city"));
				cstmt.setString(18, requestJSON.getString("state"));
				cstmt.setString(19, requestJSON.getString("localGovernment"));			
				cstmt.setString(20, requestJSON.getString("wardname"));
				cstmt.setString(21, requestJSON.getString("country"));
				cstmt.setString(22, requestJSON.getString("staffcode"));
				
				cstmt.setString(23, requestJSON.getString("bankaccnumber"));
				cstmt.setString(24, requestJSON.getString("accname"));
				cstmt.setString(25, requestJSON.getString("bvn"));
				
				cstmt.setString(26, requestJSON.getString("superagn"));
				cstmt.setString(27, requestJSON.getString("productcode"));
				cstmt.setString(28, requestJSON.getString("productdesc"));
				
				cstmt.setString(29, requestJSON.getString(CevaCommonConstants.MAKER_ID));
				cstmt.setString(30, requestJSON.getString(CevaCommonConstants.IP));
				
				cstmt.registerOutParameter(31, Types.VARCHAR);
				cstmt.executeQuery();

				if (!(cstmt.getString(31).split("-")[1]).contains("SUCCESS")) {
					responseDTO.addError((cstmt.getString(31)).split("-")[0]);
				}else{
					
					responseDTO.addMessages((cstmt.getString(31)).split("-")[0]);
				}
				
			
			responseDTO.addMessages("success");

		} catch (Exception ex) {
			logger.error("Error Occured..!" + ex.getLocalizedMessage());
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeCallableStatement(cstmt);
			DBUtils.closeConnection(connection);
			cstmt = null;
			connection = null;
		}
		return responseDTO;
	}
	
	public ResponseDTO superAgentRegistration(RequestDTO requestDTO) {
		Connection connection = null;
		String insQRY = "";
		String ip = null;

		CallableStatement cstmt = null;
		JSONObject resultJson = null;
		String encpin = null;
		String pin = null;

		try {
			responseDTO = new ResponseDTO();
			requestJSON = requestDTO.getRequestJSON();
			
			
			connection = DBConnector.getConnection();
			
			/*System.out.println("fullname -- "+requestJSON.getString("fullname"));
			System.out.println("middlename -- "+requestJSON.getString("middlename"));
			System.out.println("lastname -- "+requestJSON.getString("lastname"));
			System.out.println("dob -- "+requestJSON.getString("dob"));
			System.out.println("email -- "+requestJSON.getString("email"));
			System.out.println("telephone -- "+requestJSON.getString("telephone"));
			System.out.println("gender -- "+requestJSON.getString("gender"));
			System.out.println("marital -- "+requestJSON.getString("marital"));
			System.out.println("merchanttype -- "+requestJSON.getString("merchanttype"));
			System.out.println("businessname -- "+requestJSON.getString("businessname"));
			System.out.println("branch -- "+requestJSON.getString("branch"));
			
			System.out.println("idcardtype -- "+requestJSON.getString("idcardtype"));
			System.out.println("idcardnumber -- "+requestJSON.getString("idcardnumber"));
			System.out.println("addressLine -- "+requestJSON.getString("addressLine"));
			System.out.println("nationality -- "+requestJSON.getString("nationality"));
			System.out.println("streetname -- "+requestJSON.getString("streetname"));
			System.out.println("city -- "+requestJSON.getString("city"));
			System.out.println("state -- "+requestJSON.getString("state"));
			System.out.println("localGovernment -- "+requestJSON.getString("localGovernment"));
			System.out.println("wardname -- "+requestJSON.getString("wardname"));
			System.out.println("country -- "+requestJSON.getString("country"));
			
			System.out.println("bankname -- "+requestJSON.getString("bankname"));
			System.out.println("bankaccnumber -- "+requestJSON.getString("bankaccnumber"));
			System.out.println("accname -- "+requestJSON.getString("accname"));
			System.out.println("bvn -- "+requestJSON.getString("bvn"));
			
			System.out.println("super agent -- "+requestJSON.getString("superagn"));
			System.out.println("product	 -- "+requestJSON.getString("productcode"));
			System.out.println("product description -- "+requestJSON.getString("productdesc"));
			
			
			System.out.println("makerid -- "+requestJSON.getString(CevaCommonConstants.MAKER_ID));
			System.out.println("IP -- "+requestJSON.getString(CevaCommonConstants.IP));*/
			
			
			
				
				

				insQRY = "{call AGENTREGAPROVALPKG(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

				
				ip = requestJSON.getString(CevaCommonConstants.IP);


				
				
				cstmt = connection.prepareCall(insQRY);
				cstmt.setString(1, requestJSON.getString("fullname"));
				cstmt.setString(2, requestJSON.getString("middlename"));
				cstmt.setString(3, requestJSON.getString("lastname"));
				cstmt.setString(4, requestJSON.getString("dob"));
				cstmt.setString(5, requestJSON.getString("email"));
				cstmt.setString(6, requestJSON.getString("telephone"));
				cstmt.setString(7, requestJSON.getString("gender"));
				cstmt.setString(8, requestJSON.getString("marital"));			
				cstmt.setString(9, requestJSON.getString("merchanttype"));
				cstmt.setString(10, JsonValueValidator.stripXSSRev(requestJSON.getString("businessname")));
				cstmt.setString(11, requestJSON.getString("branch"));
				
				cstmt.setString(12, requestJSON.getString("idcardtype"));
				cstmt.setString(13, requestJSON.getString("idcardnumber"));
				cstmt.setString(14, requestJSON.getString("addressLine")); 
				cstmt.setString(15, requestJSON.getString("nationality"));			
				cstmt.setString(16, requestJSON.getString("streetname"));
				cstmt.setString(17, requestJSON.getString("city"));
				cstmt.setString(18, requestJSON.getString("state"));
				cstmt.setString(19, requestJSON.getString("localGovernment"));			
				cstmt.setString(20, requestJSON.getString("wardname"));
				cstmt.setString(21, requestJSON.getString("country"));
				cstmt.setString(22, requestJSON.getString("decision"));
				
				cstmt.setString(23, requestJSON.getString("reason"));
				cstmt.setString(24, "");
				cstmt.setString(25, "");
				
				cstmt.setString(26, "");
				cstmt.setString(27, requestJSON.getString("customercode"));
				cstmt.setString(28, "");
				
				cstmt.setString(29, requestJSON.getString(CevaCommonConstants.MAKER_ID));
				cstmt.setString(30, requestJSON.getString(CevaCommonConstants.IP));
				
				cstmt.registerOutParameter(31, Types.VARCHAR);
				cstmt.executeQuery();

				if (!(cstmt.getString(31).split("-")[1]).contains("SUCCESS")) {
					responseDTO.addError((cstmt.getString(31)).split("-")[0]);
				}else{
					
					responseDTO.addMessages((cstmt.getString(31)).split("-")[0]);
				}
				
			
			responseDTO.addMessages("success");

		} catch (Exception ex) {
			logger.error("Error Occured..!" + ex.getLocalizedMessage());
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeCallableStatement(cstmt);
			DBUtils.closeConnection(connection);
			cstmt = null;
			connection = null;
		}
		return responseDTO;
	}
	
	
	public ResponseDTO accountOpen(RequestDTO requestDTO) {
		Connection connection = null;
		String insQRY = "";
		String ip = null;

		PreparedStatement pstmt = null;
		JSONObject resultJson = null;
		String encpin = null;
		String pin = null;

		try {
			responseDTO = new ResponseDTO();
			requestJSON = requestDTO.getRequestJSON();
			
			
			connection = DBConnector.getConnection();
			
			System.out.println("fullname -- "+requestJSON.getString("fullname"));
			System.out.println("middlename -- "+requestJSON.getString("middlename"));
			System.out.println("lastname -- "+requestJSON.getString("lastname"));
			System.out.println("dob -- "+requestJSON.getString("dob"));
			System.out.println("email -- "+requestJSON.getString("email"));
			System.out.println("telephone -- "+requestJSON.getString("telephone"));
			System.out.println("gender -- "+requestJSON.getString("gender"));
			System.out.println("marital -- "+requestJSON.getString("marital"));
			
			System.out.println("bvn -- "+requestJSON.getString("bvn"));
			
			String gen="F";
			if((requestJSON.getString("gender")).equalsIgnoreCase("Male")) {
				gen="M";
			}
			
			
			System.out.println("makerid -- "+requestJSON.getString(CevaCommonConstants.MAKER_ID));
			System.out.println("IP -- "+requestJSON.getString(CevaCommonConstants.IP));
			
			JSONObject json = new JSONObject();
			json.put("FirstName",requestJSON.getString("fullname"));
			json.put("LastName",requestJSON.getString("lastname"));
			json.put("MiddleName",requestJSON.getString("middlename"));
			json.put("Gender",gen);
			json.put("DoB",convertDOBnobvn("dd-MM-yyyy",requestJSON.getString("dob")));
			json.put("Email",requestJSON.getString("email"));
			json.put("MaritalStatus",requestJSON.getString("marital"));
			json.put("PhoneNumber","0"+(requestJSON.getString("telephone")).substring(3));			
			json.put("BVNNumber",requestJSON.getString("bvn"));
			json.put("RequestID",System.currentTimeMillis()+"");
			
			
			JSONObject json1 = JSONObject.fromObject(ExternalServices.accountOpen(json.toString(),requestJSON.getString(CevaCommonConstants.MAKER_ID)));
				
				
			
				

			
			
			if((json1.getString("ResponseCode")).equalsIgnoreCase("00")) {
				responseDTO.addMessages(json1.getString("AccountNumber"));
				
				pstmt = connection.prepareStatement("INSERT INTO ACCOUNT_OPEN(REQUESTID,FIRSTNAME,LASTNAME,MIDDLENAME,DATEOFBIRTH,EMAIL,GENDER,MARITASTATUS,PHONENUMBER,BVN_ACT_NO,ACCOUNTNUMBER,TRANS_DATE,CHANNEL,MAKER_ID,MAKER_DATE,RESPDESC,RESPONSECODE) "
						+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?,sysdate,?,?)");
				pstmt.setString(1, json.getString("RequestID"));
				pstmt.setString(2, json.getString("FirstName"));
				pstmt.setString(3, json.getString("LastName"));
				pstmt.setString(4, json.getString("MiddleName"));
				pstmt.setString(5, json.getString("DoB"));
				pstmt.setString(6, json.getString("Email"));
				pstmt.setString(7, json.getString("Gender"));
				pstmt.setString(8, json.getString("MaritalStatus"));
				pstmt.setString(9, json.getString("PhoneNumber"));
				pstmt.setString(10, json.getString("BVNNumber"));
				pstmt.setString(11, json1.getString("AccountNumber"));
				pstmt.setString(12, "WEB");
				pstmt.setString(13, requestJSON.getString(CevaCommonConstants.MAKER_ID));
				pstmt.setString(14, json1.getString("ResponseCode"));
				pstmt.setString(15, json1.getString("ResponseMessage"));
				pstmt.executeUpdate();
				connection.commit();
				
			}else {
				responseDTO.addError(json1.getString("ResponseMessage"));
				
				pstmt = connection.prepareStatement("INSERT INTO ACCOUNT_OPEN(REQUESTID,FIRSTNAME,LASTNAME,MIDDLENAME,DATEOFBIRTH,EMAIL,GENDER,MARITASTATUS,PHONENUMBER,BVN_ACT_NO,ACCOUNTNUMBER,TRANS_DATE,CHANNEL,MAKER_ID,MAKER_DATE,RESPDESC,RESPONSECODE) "
						+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?,sysdate,?,?)");
				pstmt.setString(1, json.getString("RequestID"));
				pstmt.setString(2, json.getString("FirstName"));
				pstmt.setString(3, json.getString("LastName"));
				pstmt.setString(4, json.getString("MiddleName"));
				pstmt.setString(5, json.getString("DoB"));
				pstmt.setString(6, json.getString("Email"));
				pstmt.setString(7, json.getString("Gender"));
				pstmt.setString(8, json.getString("MaritalStatus"));
				pstmt.setString(9, json.getString("PhoneNumber"));
				pstmt.setString(10, json.getString("BVNNumber"));
				pstmt.setString(11, "");
				pstmt.setString(12, "WEB");
				pstmt.setString(13, requestJSON.getString(CevaCommonConstants.MAKER_ID));
				pstmt.setString(14, json1.getString("ResponseCode"));
				pstmt.setString(15, json1.getString("ResponseMessage"));
				pstmt.executeUpdate();
				connection.commit();
			}
				
			
			responseDTO.addMessages("success");

		} catch (Exception ex) {
			logger.error("Error Occured..!" + ex.getLocalizedMessage());
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {

			}
		}
		return responseDTO;
	}
	
	public ResponseDTO bvnvalidateInfo(RequestDTO requestDTO) {
		
		
		
		
		logger.debug("inside [AgentDAO][bvnvalidateInfo]");

		
		
		HashMap<String, Object> viewDataMap = null;
		Connection connection = null;

		PreparedStatement servicePstmt = null;
		ResultSet serviceRS = null;

		try {
			responseDTO = new ResponseDTO();
			responseJSON = new JSONObject();
			requestJSON = requestDTO.getRequestJSON();
			
			connection = DBConnector.getConnection();
			viewDataMap = new HashMap<String, Object>();
			
			 JSONObject json = new JSONObject();
			 
			 System.out.println("BVN ::: "+requestJSON.getString("bvn"));
			 String str = "{'bankCode':'058','bankVerificationNumber':'"+requestJSON.getString("bvn")+"'}";
			 
			 JSONObject json1 = JSONObject.fromObject(ExternalServices.bvndetails(str));
			 
			// logger.debug("bvnvalidateInfo :: "+json1.getString("data")status);
			 System.out.println(JSONObject.fromObject(JSONObject.fromObject(JSONObject.fromObject(json1.getString("data")).getString("details")).getString("data")).getString("lastName"));
			 if((JSONObject.fromObject(JSONObject.fromObject(JSONObject.fromObject(json1.getString("data")).getString("details"))).getString("status")).equals("00")) {
				 
			 
			 
			 JSONObject json2 =JSONObject.fromObject(JSONObject.fromObject(JSONObject.fromObject(json1.getString("data")).getString("details")).getString("data"));
				 
			 json.put("fullname",json2.getString("firstName"));
			 json.put("middlename",json2.getString("middleName"));
			 json.put("lastname",json2.getString("lastName"));
			 json.put("dateofbirth",json2.getString("dateOfBirth"));
			 json.put("email",json2.getString("email"));
			 json.put("telephone",json2.getString("phoneNumber"));
			 json.put("gender",json2.getString("gender"));
			 json.put("martStatus",json2.getString("maritalStatus"));
			 
			 json.put("levelOfAccount",json2.getString("levelOfAccount"));
			 json.put("enrollmentBranch",json2.getString("enrollmentBranch"));
			
			 json.put("nameOnCard",json2.getString("nameOnCard"));
			 json.put("nationalIdentityNumber",json2.getString("nationalIdentityNumber"));
			 json.put("address",json2.getString("residentialAddress"));
			 json.put("nationality",json2.getString("nationality"));
			 json.put("stateOfOrigin",json2.getString("stateOfOrigin"));
			 json.put("stateOfResidence",json2.getString("stateOfResidence"));
			 json.put("image"," ");
			 viewDataMap.put("BVN_DETAILS", json);
			 responseDTO.setData(viewDataMap);
				 
			 }else {
				 responseDTO.addError("Invalid BVN"); 
			 }
			
			 
			
						
			
			
	       } catch (Exception e) {

			logger.debug("[AgentDAO][AgentRegModify] SQLException in AgentRegModify [" + e.getMessage()
					+ "]");
			responseDTO.addError("[AgentDAO][AgentRegModify] Internal Error Occured While Executing.");
		} finally {
			try {

				
				if (servicePstmt != null)
					servicePstmt.close();
				if (serviceRS != null)
					serviceRS.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {

			}
			viewDataMap = null;
			
			
		}

		return responseDTO;
	}	
	
	

	
	public ResponseDTO pendingInfo(RequestDTO requestDTO) {

		

		HashMap<String, Object> lmtfeeDataMap = null;
		JSONArray lmtJsonArray = null;
		JSONArray feeJsonArray = null;
		
		
		JSONObject resultJson = null;
		JSONObject json = null;

		PreparedStatement getlmtfeePstmt = null;
		ResultSet getlmtfeeRs = null;

		Connection connection = null;

		String lmtfeeQry = null;



		try {
			responseDTO = new ResponseDTO();

			lmtfeeDataMap = new HashMap<String, Object>();
			lmtJsonArray = new JSONArray();
			feeJsonArray = new JSONArray();
			
			resultJson = new JSONObject();

			requestJSON = requestDTO.getRequestJSON();
			connection = DBConnector.getConnection();

			logger.debug("[ WalletAgentDao ] [ pendingInfo ] connection is null [" + connection == null + "]");
			
			lmtfeeQry = "select REQUEST_ID,MOBILE_NUMBER,TRANS_DATE,REG_STATUS,AMOUNT 	 from DISPUTE_REQUEST_TBL where REG_STATUS='P' ";


			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("REQUEST_ID", getlmtfeeRs.getString(1));
				json.put("MOBILE_NUMBER", getlmtfeeRs.getString(2));
				json.put("AMOUNT", getlmtfeeRs.getString(3));
				
				json.put("TRANS_DATE", getlmtfeeRs.getString(4));
				json.put("REG_STATUS", getlmtfeeRs.getString(5));
			
					
				lmtJsonArray.add(json);
				feeJsonArray.add(json);
			}

			resultJson.put("VIEW_LMT_DATA", lmtJsonArray);
			resultJson.put("VIEW_FEE_DATA", feeJsonArray);
			
			logger.info("Final Json Object ["+resultJson+"]");
			
			lmtfeeDataMap.put("LMT_FEE_INFO", resultJson);
			logger.debug("Limit Fee DataMap    [" + lmtfeeDataMap + "]");
			responseDTO.setData(lmtfeeDataMap);


		} catch (Exception e) {
			logger.debug("[ WalletAgentDao ] [ pendingInfo ]  Got Exception  ["
					+ e.getMessage() + "]");
		} finally {
			
			try {

				if (getlmtfeeRs != null) {
					getlmtfeeRs.close();
				}

				if (getlmtfeePstmt != null) {
					getlmtfeePstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			lmtfeeDataMap = null;
			resultJson = null;
		}

		return responseDTO;
	}
	
	
	
	
	
	
	public ResponseDTO pendingView(RequestDTO requestDTO) {

		logger.debug("Inside fetchlimitcodeInfo.. ");
		HashMap<String, Object> limitcodeDataMap = null;

		JSONObject resultJson = null;
		JSONObject json = null;
		JSONArray JsonArray= new JSONArray();
		PreparedStatement getlimitcodePstmt = null;
		ResultSet getlimitcodeRs = null;
		Connection connection = null;

		String  limitcode = "";
		String  limitcodeCountQry = " select REQUEST_ID,MOBILE_NUMBER,WALLET_ACC,PAYMENT_REF_NO,TRANS_DATE,AMOUNT,REG_STATUS,REQ_DATE	 from DISPUTE_REQUEST_TBL where REQUEST_ID=? ";
		try {
			requestJSON = requestDTO.getRequestJSON();
			responseDTO = new ResponseDTO();
			limitcodeDataMap = new HashMap<String, Object>();
			resultJson = new JSONObject();

			connection = DBConnector.getConnection();

			limitcode = requestJSON.getString(CevaCommonConstants. LIMIT_CODE);

			getlimitcodePstmt = connection.prepareStatement(limitcodeCountQry);
			getlimitcodePstmt.setString(1,limitcode);

			getlimitcodeRs = getlimitcodePstmt.executeQuery();
			json = new JSONObject();

			if (getlimitcodeRs.next()) 
			{				
				json.put("REQUEST_ID", getlimitcodeRs.getString(1));
			     json.put("MOBILE_NUMBER", getlimitcodeRs.getString(2));
			     json.put("WALLET_ACC", getlimitcodeRs.getString(3));
			     json.put("PAYMENT_REF_NO", getlimitcodeRs.getString(4));
			     json.put("TRANS_DATE", getlimitcodeRs.getString(5));
			     json.put("AMOUNT", getlimitcodeRs.getString(6));
			     json.put("REG_STATUS", getlimitcodeRs.getString(7));
			     json.put("REQ_DATE", getlimitcodeRs.getString(8));
			}

			resultJson.put("limitcodedetails", json);	
			
			getlimitcodePstmt.close();
			getlimitcodeRs.close();
			
			
			
  logger.info("Final Json Object ["+resultJson+"]");
			

			  
			    limitcodeDataMap.put(CevaCommonConstants.BINGRP_INFO, resultJson);
			    logger.debug("limitcodeDataMap[" + limitcodeDataMap + "]");
			    responseDTO.setData(limitcodeDataMap);

		} catch (SQLException e) {
			responseDTO.addError("Internal Error Occured While Executing.");
			logger.debug("SQLException in checkfeecode [" + e.getMessage() + "]");
		} catch (Exception e) {
			responseDTO.addError("Internal Error Occured While Executing.");
			logger.debug("Exception in checkfeecode [" + e.getMessage() + "]");
		} finally {
			try {

				if (getlimitcodeRs != null) {
					getlimitcodeRs.close();
				}

				if (getlimitcodePstmt != null) {
					getlimitcodePstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			limitcodeDataMap = null;
			resultJson = null;
		}
		return responseDTO;
}
	
	
	
	
	
	
	
	
	public ResponseDTO countView(RequestDTO requestDTO) {

		logger.debug(" Inside fetchProductGridInfo.. ");

		HashMap<String, Object> lmtfeeDataMap = null;
		JSONArray lmtJsonArray = null;
		JSONArray feeJsonArray = null;
		
		
		JSONObject resultJson = null;
		JSONObject json = null;

		PreparedStatement getlmtfeePstmt = null;
		ResultSet getlmtfeeRs = null;

		Connection connection = null;

		String qrey = "";
		String application="";



		try {
			responseDTO = new ResponseDTO();

			lmtfeeDataMap = new HashMap<String, Object>();
			lmtJsonArray = new JSONArray();
			feeJsonArray = new JSONArray();
			
			resultJson = new JSONObject();

			requestJSON = requestDTO.getRequestJSON();
			connection = DBConnector.getConnection();

			logger.debug("connection is null [" + connection == null + "]");
			
		
			
			String makerId=requestJSON.getString(CevaCommonConstants.MAKER_ID);
			String remoteip=requestJSON.getString("remoteip");
			
			
			StringBuilder sb=new StringBuilder();
		
			sb.append("SELECT (select count(*) from DISPUTE_REQUEST_TBL where REG_STATUS='P'),");
			
			sb.append("(select count(*) from DISPUTE_REQUEST_TBL where REG_STATUS='S'),");
		
			
			sb.append("(select count(*) from DISPUTE_REQUEST_TBL where REG_STATUS='C')  FROM DUAL");
			
			
				 
				
				getlmtfeePstmt = connection.prepareStatement(sb.toString());

				getlmtfeeRs = getlmtfeePstmt.executeQuery();

				while (getlmtfeeRs.next()) {

					json = new JSONObject();
					json.put("count", getlmtfeeRs.getString(1));
					json.put("count2", getlmtfeeRs.getString(2));
					json.put("count3", getlmtfeeRs.getString(3));
				}

				getlmtfeePstmt.close();
				getlmtfeeRs.close();
				
			
			
			
			
			resultJson.put("VIEW_LMT_DATA", json);
			
			logger.info("Final Json Object ["+resultJson+"]");
			
			lmtfeeDataMap.put("LMT_FEE_INFO", resultJson);
			logger.debug("Limit Fee DataMap    [" + lmtfeeDataMap + "]");
			responseDTO.setData(lmtfeeDataMap);


		} catch (Exception e) {
			logger.debug("Got Exception in View Product Details ["
					+ e.getMessage() + "]");
		} finally {
			
			try {

				if (getlmtfeeRs != null) {
					getlmtfeeRs.close();
				}

				if (getlmtfeePstmt != null) {
					getlmtfeePstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			lmtfeeDataMap = null;
			resultJson = null;
		}

		return responseDTO;
	}

	
	
	public ResponseDTO Pendingststusupdation(RequestDTO requestDTO) {

		logger.debug("Inside fetchlimitcodeInfo.. ");
		HashMap<String, Object> limitcodeDataMap = null;

		JSONObject resultJson = null;
		JSONObject json = null;
		JSONArray JsonArray= new JSONArray();
		PreparedStatement getlimitcodePstmt = null;
		ResultSet getlimitcodeRs = null;
		Connection connection = null;

		String  limitcode = "";
		String REG_STATUS=null;
		String  limitcodeCountQry = "UPDATE  DISPUTE_REQUEST_TBL set REG_STATUS=?  WHERE REQUEST_ID=?";
		try {
			requestJSON = requestDTO.getRequestJSON();
			responseDTO = new ResponseDTO();
			limitcodeDataMap = new HashMap<String, Object>();
			resultJson = new JSONObject();

			connection = DBConnector.getConnection();

			limitcode = requestJSON.getString(CevaCommonConstants. LIMIT_CODE);
			REG_STATUS = requestJSON.getString(CevaCommonConstants.status);
	             
			if(REG_STATUS.equalsIgnoreCase("P")) {
				REG_STATUS="S";
			}else {
				REG_STATUS="C";
			}
			getlimitcodePstmt = connection.prepareStatement(limitcodeCountQry);
			getlimitcodePstmt.setString(1,REG_STATUS);
			getlimitcodePstmt.setString(2,limitcode);
		
			getlimitcodePstmt.executeUpdate();
			
			connection.commit();
			json = new JSONObject();
	     
			resultJson.put("success", "00") ;  
		    limitcodeDataMap.put(CevaCommonConstants.BINGRP_INFO, resultJson);
		    logger.debug("limitcodeDataMap[" + limitcodeDataMap + "]");
		    responseDTO.setData(limitcodeDataMap);

		} catch (SQLException e) {
			responseDTO.addError("Internal Error Occured While Executing.");
			logger.debug("SQLException in checkfeecode [" + e.getMessage() + "]");
		} catch (Exception e) {
			responseDTO.addError("Internal Error Occured While Executing.");
			logger.debug("Exception in checkfeecode [" + e.getMessage() + "]");
		} finally {
			try {

				if (getlimitcodeRs != null) {
					getlimitcodeRs.close();
				}

				if (getlimitcodePstmt != null) {
					getlimitcodePstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			limitcodeDataMap = null;
			resultJson = null;
		}
		return responseDTO;
	}


	


	
	
	
public ResponseDTO closeInfo(RequestDTO requestDTO) {

		

		HashMap<String, Object> lmtfeeDataMap = null;
		JSONArray lmtJsonArray = null;
		JSONArray feeJsonArray = null;
		
		
		JSONObject resultJson = null;
		JSONObject json = null;

		PreparedStatement getlmtfeePstmt = null;
		ResultSet getlmtfeeRs = null;

		Connection connection = null;

		String lmtfeeQry = null;



		try {
			responseDTO = new ResponseDTO();

			lmtfeeDataMap = new HashMap<String, Object>();
			lmtJsonArray = new JSONArray();
			feeJsonArray = new JSONArray();
			
			resultJson = new JSONObject();

			requestJSON = requestDTO.getRequestJSON();
			connection = DBConnector.getConnection();

			logger.debug("[ WalletAgentDao ] [ pendingInfo ] connection is null [" + connection == null + "]");
			
			lmtfeeQry = "select REQUEST_ID,MOBILE_NUMBER,TRANS_DATE,REG_STATUS,AMOUNT 	 from DISPUTE_REQUEST_TBL where REG_STATUS='C' ";


			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("REQUEST_ID", getlmtfeeRs.getString(1));
				json.put("MOBILE_NUMBER", getlmtfeeRs.getString(2));
				json.put("AMOUNT", getlmtfeeRs.getString(3));
				
				json.put("TRANS_DATE", getlmtfeeRs.getString(4));
				json.put("REG_STATUS", getlmtfeeRs.getString(5));
					
				lmtJsonArray.add(json);
				feeJsonArray.add(json);
			}

			resultJson.put("VIEW_LMT_DATA", lmtJsonArray);
			resultJson.put("VIEW_FEE_DATA", feeJsonArray);
			
			logger.info("Final Json Object ["+resultJson+"]");
			
			lmtfeeDataMap.put("LMT_FEE_INFO", resultJson);
			logger.debug("Limit Fee DataMap    [" + lmtfeeDataMap + "]");
			responseDTO.setData(lmtfeeDataMap);


		} catch (Exception e) {
			logger.debug("[ WalletAgentDao ] [ pendingInfo ]  Got Exception  ["
					+ e.getMessage() + "]");
		} finally {
			
			try {

				if (getlmtfeeRs != null) {
					getlmtfeeRs.close();
				}

				if (getlmtfeePstmt != null) {
					getlmtfeePstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			lmtfeeDataMap = null;
			resultJson = null;
		}

		return responseDTO;
	}
	
	
	




	public ResponseDTO proccedInfo(RequestDTO requestDTO) {

		

		HashMap<String, Object> lmtfeeDataMap = null;
		JSONArray lmtJsonArray = null;
		JSONArray feeJsonArray = null;
		
		
		JSONObject resultJson = null;
		JSONObject json = null;

		PreparedStatement getlmtfeePstmt = null;
		ResultSet getlmtfeeRs = null;

		Connection connection = null;

		String lmtfeeQry = null;



		try {
			responseDTO = new ResponseDTO();

			lmtfeeDataMap = new HashMap<String, Object>();
			lmtJsonArray = new JSONArray();
			feeJsonArray = new JSONArray();
			
			resultJson = new JSONObject();

			requestJSON = requestDTO.getRequestJSON();
			connection = DBConnector.getConnection();

			logger.debug("[ WalletAgentDao ] [ pendingInfo ] connection is null [" + connection == null + "]");
			
			lmtfeeQry = "select REQUEST_ID,MOBILE_NUMBER,TRANS_DATE,REG_STATUS,AMOUNT 	 from DISPUTE_REQUEST_TBL where REG_STATUS='S' ";


			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("REQUEST_ID", getlmtfeeRs.getString(1));
				json.put("MOBILE_NUMBER", getlmtfeeRs.getString(2));
				json.put("AMOUNT", getlmtfeeRs.getString(3));
		    	json.put("TRANS_DATE", getlmtfeeRs.getString(4));
				json.put("REG_STATUS", getlmtfeeRs.getString(5));
					
				lmtJsonArray.add(json);
				feeJsonArray.add(json);
			}

			resultJson.put("VIEW_LMT_DATA", lmtJsonArray);
			resultJson.put("VIEW_FEE_DATA", feeJsonArray);
			
			logger.info("Final Json Object ["+resultJson+"]");
			
			lmtfeeDataMap.put("LMT_FEE_INFO", resultJson);
			logger.debug("Limit Fee DataMap    [" + lmtfeeDataMap + "]");
			responseDTO.setData(lmtfeeDataMap);


		} catch (Exception e) {
			logger.debug("[ WalletAgentDao ] [ pendingInfo ]  Got Exception  ["
					+ e.getMessage() + "]");
		} finally {
			
			try {

				if (getlmtfeeRs != null) {
					getlmtfeeRs.close();
				}

				if (getlmtfeePstmt != null) {
					getlmtfeePstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			lmtfeeDataMap = null;
			resultJson = null;
		}

		return responseDTO;
	}
	

	
	
	
	
	public ResponseDTO countInfo(RequestDTO requestDTO) {

		

		HashMap<String, Object> lmtfeeDataMap = null;
		JSONArray lmtJsonArray = null;
		JSONArray feeJsonArray = null;
		
		
		JSONObject resultJson = null;
		JSONObject json = null;

		PreparedStatement getlmtfeePstmt = null;
		ResultSet getlmtfeeRs = null;

		Connection connection = null;

		String lmtfeeQry = null;



		try {
			responseDTO = new ResponseDTO();

			lmtfeeDataMap = new HashMap<String, Object>();
			lmtJsonArray = new JSONArray();
			feeJsonArray = new JSONArray();
			
			resultJson = new JSONObject();

			requestJSON = requestDTO.getRequestJSON();
			connection = DBConnector.getConnection();

			logger.debug("[ WalletAgentDao ] [ pendingInfo ] connection is null [" + connection == null + "]");
			
			lmtfeeQry = "select count(*) from DISPUTE_REQUEST_TBL where  REG_STATUS='P' ";


			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("count", getlmtfeeRs.getString(1));
				
				lmtJsonArray.add(json);
				feeJsonArray.add(json);
			}

			resultJson.put("VIEW_LMT_DATA", lmtJsonArray);
			resultJson.put("VIEW_FEE_DATA", feeJsonArray);
			
			logger.info("Final Json Object ["+resultJson+"]");
			
			lmtfeeDataMap.put("LMT_FEE_INFO", resultJson);
			logger.debug("Limit Fee DataMap    [" + lmtfeeDataMap + "]");
			responseDTO.setData(lmtfeeDataMap);


		} catch (Exception e) {
			logger.debug("[ WalletAgentDao ] [ pendingInfo ]  Got Exception  ["
					+ e.getMessage() + "]");
		} finally {
			
			try {

				if (getlmtfeeRs != null) {
					getlmtfeeRs.close();
				}

				if (getlmtfeePstmt != null) {
					getlmtfeePstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			lmtfeeDataMap = null;
			resultJson = null;
		}

		return responseDTO;
	}
	

public ResponseDTO closecountConfig(RequestDTO requestDTO) {

		

		HashMap<String, Object> lmtfeeDataMap = null;
		JSONArray lmtJsonArray = null;
		JSONArray feeJsonArray = null;
		
		
		JSONObject resultJson = null;
		JSONObject json = null;

		PreparedStatement getlmtfeePstmt = null;
		ResultSet getlmtfeeRs = null;

		Connection connection = null;

		String lmtfeeQry = null;



		try {
			responseDTO = new ResponseDTO();

			lmtfeeDataMap = new HashMap<String, Object>();
			lmtJsonArray = new JSONArray();
			feeJsonArray = new JSONArray();
			
			resultJson = new JSONObject();

			requestJSON = requestDTO.getRequestJSON();
			connection = DBConnector.getConnection();

			logger.debug("[ WalletAgentDao ] [ pendingInfo ] connection is null [" + connection == null + "]");
			
			lmtfeeQry = "select count(*) from DISPUTE_REQUEST_TBL where  REG_STATUS='C' ";


			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("count", getlmtfeeRs.getString(1));
				
				lmtJsonArray.add(json);
				feeJsonArray.add(json);
			}

			resultJson.put("VIEW_LMT_DATA", lmtJsonArray);
			resultJson.put("VIEW_FEE_DATA", feeJsonArray);
			
			logger.info("Final Json Object ["+resultJson+"]");
			
			lmtfeeDataMap.put("LMT_FEE_INFO", resultJson);
			logger.debug("Limit Fee DataMap    [" + lmtfeeDataMap + "]");
			responseDTO.setData(lmtfeeDataMap);


		} catch (Exception e) {
			logger.debug("[ WalletAgentDao ] [ pendingInfo ]  Got Exception  ["
					+ e.getMessage() + "]");
		} finally {
			
			try {

				if (getlmtfeeRs != null) {
					getlmtfeeRs.close();
				}

				if (getlmtfeePstmt != null) {
					getlmtfeePstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			lmtfeeDataMap = null;
			resultJson = null;
		}

		return responseDTO;
	}
	

	
	
public ResponseDTO processcountConfig(RequestDTO requestDTO) {

	HashMap<String, Object> lmtfeeDataMap = null;
	JSONArray lmtJsonArray = null;
	JSONArray feeJsonArray = null;
	
	
	JSONObject resultJson = null;
	JSONObject json = null;

	PreparedStatement getlmtfeePstmt = null;
	ResultSet getlmtfeeRs = null;

	Connection connection = null;

	String lmtfeeQry = null;



	try {
		responseDTO = new ResponseDTO();

		lmtfeeDataMap = new HashMap<String, Object>();
		lmtJsonArray = new JSONArray();
		feeJsonArray = new JSONArray();
		
		resultJson = new JSONObject();

		requestJSON = requestDTO.getRequestJSON();
		connection = DBConnector.getConnection();

		logger.debug("[ WalletAgentDao ] [ pendingInfo ] connection is null [" + connection == null + "]");
		
		lmtfeeQry = "select count(*) from DISPUTE_REQUEST_TBL where  REG_STATUS='S' ";


		getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

		getlmtfeeRs = getlmtfeePstmt.executeQuery();

		while (getlmtfeeRs.next()) {

			
			json = new JSONObject();
			json.put("count", getlmtfeeRs.getString(1));
			
			lmtJsonArray.add(json);
			feeJsonArray.add(json);
		}

		resultJson.put("VIEW_LMT_DATA", lmtJsonArray);
		resultJson.put("VIEW_FEE_DATA", feeJsonArray);
		
		logger.info("Final Json Object ["+resultJson+"]");
		
		lmtfeeDataMap.put("LMT_FEE_INFO", resultJson);
		logger.debug("Limit Fee DataMap    [" + lmtfeeDataMap + "]");
		responseDTO.setData(lmtfeeDataMap);


	} catch (Exception e) {
		logger.debug("[ WalletAgentDao ] [ pendingInfo ]  Got Exception  ["
				+ e.getMessage() + "]");
	} finally {
		
		try {

			if (getlmtfeeRs != null) {
				getlmtfeeRs.close();
			}

			if (getlmtfeePstmt != null) {
				getlmtfeePstmt.close();
			}

			if (connection != null) {
				connection.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		lmtfeeDataMap = null;
		resultJson = null;
	}

	return responseDTO;
}



public ResponseDTO Agentcustomerview(RequestDTO requestDTO) {
	
	responseDTO=new ResponseDTO();
	requestJSON=new JSONObject();
	responseJSON=new JSONObject();
	
	logger.debug("inside [AgentDAO][AgentRegModifySearch]");

	logger.debug("Inside  AgentRegModifySearch.... ");

	HashMap<String, Object> serviceDataMap = null;
	JSONObject resultJson = null;
	JSONArray IncomeMTFilesJSONArray2 = null;
	JSONArray IncomeMTFilesJSONArray1 = null;
	JSONArray IncomeMTFilesJSONArray3 = null;
	JSONArray JsonArray= new JSONArray();
	Connection connection = null;

	PreparedStatement servicePstmt = null;
	PreparedStatement servicePstmt1 = null;
	ResultSet serviceIdRS = null;
	ResultSet serviceRS = null;
	ResultSet serviceRS1 = null;
	String userReport="";
	String userReport1="";
	String userReport2="";
	

	try {
		responseDTO = new ResponseDTO();
		responseJSON = new JSONObject();
		requestJSON = requestDTO.getRequestJSON();

		connection = DBConnector.getConnection();

		logger.debug("Connection is null [" + connection == null + "]");

		serviceDataMap = new HashMap<String, Object>();
		resultJson = new JSONObject();
		IncomeMTFilesJSONArray2 = new JSONArray();
		IncomeMTFilesJSONArray1 = new JSONArray();
		IncomeMTFilesJSONArray3 = new JSONArray();
		String makerId=requestJSON.getString(CevaCommonConstants.MAKER_ID);
		String remoteip=requestJSON.getString("remoteip");
		String msg="";
		
		String customercode=((requestJSON.getString("customercode")).equalsIgnoreCase("")) ? null :  "'"+requestJSON.getString("customercode")+"'";
		String application=((requestJSON.getString("application")).equalsIgnoreCase("")) ? null : ""+requestJSON.getString("application")+"";

		
		
		
		
		 String Actionname="";
		 String Actiondesc="";
		
		
			 
			  servicePstmt = connection.prepareStatement("select ACM.ACCOUNT_NO,ACM.FIRST_NAME,ACM.MIDDLE_NAME,ACM.LAST_NAME,ACM.ACC_BRANCH,to_char(ACM.DOB,'dd-mm-yyyy'),ACM.EMAILID,ACM.GENDER,ACM.SUPER_ADM,ACM.W_PRD_CODE,MCI.MOBILE_NUMBER,"
			  		+ "MCI.ADDRESS,MCI.ID_TYPE,MCI.ID_DETAILS,MCI.NATIONALITY,MCI.RL_LGA,MCI.R_STATE,MCI.COUNTRY,decode(ACM.STATUS,'B','Block','F','Inactive','A','Active','L','Deactive','Active'),ACM.ID,NVL(ACM.USER_ID,' '),WAD.ACCT_NO,to_char(ACM.DATE_CREATED,'dd-mm-yyyy hh:mi:ss'),MCI.AREA,MCI.CITY,MCI.WARD_NAME,ACM.BANK_CUST_NAME,ACM.BANK_NAME,ACM.BVN,ACM.MARITAL_STATUS,ACM.MCC_NAME,ACM.BUSINESS_NAME,W_PRD_CODE,W_PRD_DESC,AGENCY_TYPE,"
			  		+ "decode(ACM.USSD_STATUS,'A','Active','Deactive'),decode(ACM.MOBILE_STATUS,'A','Active','Deactive'),decode(ACM.POS_STATUS,'A','Active','Deactive'),WAD.BALANCE from AGENT_CUSTOMER_MASTER ACM,AGENT_CONTACT_INFO MCI,WALLET_ACCT_DATA WAD where ACM.ID=MCI.CUST_ID and MCI.CUST_ID=WAD.CUST_ID and WAD.CUST_TYPE='AGENT' and ACM.CUSTOMER_CODE="+customercode+"");
			  serviceRS = servicePstmt.executeQuery();
			  JSONObject json = new JSONObject();
				 while(serviceRS.next())
					{
					json.put("refno",customercode);
					json.put("accountno",serviceRS.getString(1));
					json.put("fullname",serviceRS.getString(2));
					json.put("middlename",serviceRS.getString(3));
					json.put("lastname",serviceRS.getString(4));
					json.put("branchcode",serviceRS.getString(5));
					json.put("dateofbirth",serviceRS.getString(6));
					json.put("email",serviceRS.getString(7));
					json.put("gender",serviceRS.getString(8));
					json.put("superadmin",serviceRS.getString(9));
					json.put("telephone",serviceRS.getString(11));
					
					json.put("address",serviceRS.getString(12));
					json.put("idtype",serviceRS.getString(13));
					json.put("iddetails",serviceRS.getString(14));
					json.put("nationality",serviceRS.getString(15));
					json.put("lga",serviceRS.getString(16));
					json.put("state",serviceRS.getString(17));
					json.put("country",serviceRS.getString(18));
					json.put("status",serviceRS.getString(19));
					json.put("id",serviceRS.getString(20));
					json.put("userid",serviceRS.getString(21));
					json.put("walletaccountno",serviceRS.getString(22));
					json.put("onboarddate",serviceRS.getString(23));
					json.put("area",serviceRS.getString(24));
					json.put("city",serviceRS.getString(25));
					json.put("wardname",serviceRS.getString(26));
					json.put("accountname",serviceRS.getString(27));
					json.put("bankname",serviceRS.getString(28));
					json.put("bvn",serviceRS.getString(29));
					json.put("martStatus",serviceRS.getString(30));
					json.put("mcc",serviceRS.getString(31));
					json.put("bsnname",serviceRS.getString(32));
					
							
					json.put("product",serviceRS.getString(33));
					json.put("prodesc",serviceRS.getString(34));
					json.put("agenttype",serviceRS.getString(35));
					
					json.put("ussd_status",serviceRS.getString(36));
					json.put("mobile_status",serviceRS.getString(37));
					json.put("pos_status",serviceRS.getString(38));
					json.put("walletbalance",serviceRS.getString(39));
					
					}
				 
					
					servicePstmt.close();
					serviceRS.close();
					
					
		
		serviceDataMap.put("Files_List", json);
		
		

		//logger.debug("ServiceDataMap [" + serviceDataMap + "]");
		responseDTO.setData(serviceDataMap);

	} catch (SQLException e) {
		logger.debug("SQLException in AgentRegModifySearch [" + e.getMessage()
				+ "]");
		responseDTO.addError("Internal Error Occured While Executing.");
	} catch (Exception e) {

		logger.debug("SQLException in AgentRegModifySearch [" + e.getMessage()
				+ "]");
		responseDTO.addError("Internal Error Occured While Executing.");
	} finally {
		try {

			if (servicePstmt != null)
				servicePstmt.close();
			if (serviceIdRS != null)
				serviceIdRS.close();
			if (serviceRS != null)
				serviceRS.close();
			if (connection != null)
				connection.close();

		} catch (SQLException e) {

		}
		serviceDataMap = null;
		resultJson = null;
		IncomeMTFilesJSONArray1.clear();
		IncomeMTFilesJSONArray1 = null;
		IncomeMTFilesJSONArray2.clear();
		IncomeMTFilesJSONArray2 = null;
	}

	return responseDTO;
}

public ResponseDTO agentrequestbonusackAck(RequestDTO requestDTO) {
	
	responseDTO=new ResponseDTO();
	requestJSON=new JSONObject();
	responseJSON=new JSONObject();
	
	logger.debug("inside [AgentDAO][gtAgentRegistrtionack]");


	HashMap<String, Object> serviceDataMap = null;
	JSONObject resultJson = null;
	JSONArray IncomeMTFilesJSONArray = null;

	Connection connection = null;
	
	PreparedStatement pstmt = null;
	
	PreparedStatement pstmt1 = null;
	ResultSet rs1 = null;

	

	try {
		responseDTO = new ResponseDTO();
		responseJSON = new JSONObject();
		requestJSON = requestDTO.getRequestJSON();

		connection = DBConnector.getConnection();


		serviceDataMap = new HashMap<String, Object>();
		resultJson = new JSONObject();
		IncomeMTFilesJSONArray = new JSONArray();

				String fullname=requestJSON.getString("fullname");
				String middlename=requestJSON.getString("middlename");
				String lastname=requestJSON.getString("lastname");
				String telephone=requestJSON.getString("telephone");
				String walletaccountno=requestJSON.getString("walletaccountno");
				String bonusamt=requestJSON.getString("bonusamt");
				String paymentref=requestJSON.getString("paymentref");
				String email=requestJSON.getString("email");
				String monthyear=requestJSON.getString("monthyear");
				
				String makerid=requestJSON.getString(CevaCommonConstants.MAKER_ID);
				String remoteip=requestJSON.getString("remoteip");
		
				String entityQry = "select count(*)   from WALLET_AGENT_BONUS WHERE MOBILE_NUMBER='"+telephone+"' and STATUS='P'";
				

				pstmt1 = connection.prepareStatement(entityQry);
				rs1 = pstmt1.executeQuery();
				if(rs1.next()) {
					if(rs1.getInt(1)==0) {
						connection.setAutoCommit(false);
						pstmt = connection.prepareStatement("INSERT INTO WALLET_AGENT_BONUS(REFERENCE_NO,F_NAME,M_NAME,L_NAME,EMAIL,MOBILE_NUMBER,W_ACCOUNT_NO,BONUS_AMT,PAY_REFERENCENO,MAKER_ID,MAKER_DT,TRANS_DATE,STATUS,MONTHYEAR) "
								+ "VALUES(AGENTBONUS_SEQ.nextval,?,?,?,?,?,?,?,?,?,sysdate,sysdate,'P',?)");
						pstmt.setString(1, fullname);
						pstmt.setString(2, middlename);
						pstmt.setString(3, lastname);
						pstmt.setString(4, email);
						pstmt.setString(5, telephone);
						pstmt.setString(6, walletaccountno);
						pstmt.setString(7, bonusamt);
						pstmt.setString(8, paymentref);
						pstmt.setString(9, makerid);
						pstmt.setString(10, monthyear);
						
						pstmt.executeUpdate();
						connection.commit();
					}
					
				}
		
				

		responseDTO.addMessages("Success");

	} catch (SQLException e) { 
		logger.debug("[AgentDAO][gtAgentRegistrtionack] SQLException in usdnastrooptionConf [" + e.getMessage()
				+ "]");
		responseDTO.addError("Internal Error Occured While Executing.");
	} catch (Exception e) {

		logger.debug("[AgentDAO][gtAgentRegistrtionack] SQLException in usdnastrooptionConf [" + e.getMessage()
				+ "]");
		responseDTO.addError("Internal Error Occured While Executing.");
	} finally {
		try {

			if (pstmt != null)
				pstmt.close();
			if (pstmt1 != null)
				pstmt1.close();
			if (rs1 != null)
				rs1.close();
			if (connection != null)
				connection.close();

		} catch (SQLException e) {

		}
		serviceDataMap = null;
		resultJson = null;
		IncomeMTFilesJSONArray.clear();
		IncomeMTFilesJSONArray = null;
	}

	return responseDTO;
}

public ResponseDTO agentbonusapprovalinfo(RequestDTO requestDTO) {

	logger.debug(" Inside fetchProductGridInfo.. ");

	HashMap<String, Object> lmtfeeDataMap = null;
	JSONArray agntJsonArray = null;
	
	
	JSONObject resultJson = null;
	JSONObject json = null;

	PreparedStatement getlmtfeePstmt = null;
	ResultSet getlmtfeeRs = null;

	Connection connection = null;

	String lmtfeeQry = "SELECT REFERENCE_NO,F_NAME||' '||M_NAME||' '||L_NAME,MOBILE_NUMBER,W_ACCOUNT_NO,BONUS_AMT,MAKER_ID,to_char(MAKER_DT,'dd-mm-yyyy hh24:mi:ss') FROM WALLET_AGENT_BONUS where STATUS='P'";



	try {
		responseDTO = new ResponseDTO();

		lmtfeeDataMap = new HashMap<String, Object>();
		agntJsonArray = new JSONArray();
		
		resultJson = new JSONObject();

		requestJSON = requestDTO.getRequestJSON();
		connection = DBConnector.getConnection();

		logger.debug("connection is null [" + connection == null + "]");

		getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

		getlmtfeeRs = getlmtfeePstmt.executeQuery();

		while (getlmtfeeRs.next()) {

			json = new JSONObject();
			json.put("REFERENCE_NO", getlmtfeeRs.getString(1));
			json.put("FULLNAME", getlmtfeeRs.getString(2));
			json.put("MOBILE_NUMBER", getlmtfeeRs.getString(3));
			json.put("W_ACCOUNT_NO", getlmtfeeRs.getString(4));
			json.put("BONUS_AMT", getlmtfeeRs.getString(5));
			json.put("MAKER_ID", getlmtfeeRs.getString(6));
			json.put("MAKER_DT", getlmtfeeRs.getString(7));
			
			agntJsonArray.add(json);
			

		}

		resultJson.put("VIEW_AGNT_DATA", agntJsonArray);
		
		logger.info("Final Json Object ["+resultJson+"]");
		
		lmtfeeDataMap.put("SUPER_AGENT_INFO", resultJson);
		logger.debug("Limit Fee DataMap    [" + lmtfeeDataMap + "]");
		responseDTO.setData(lmtfeeDataMap);


	} catch (Exception e) {
		logger.debug("Got Exception in View Product Details ["
				+ e.getMessage() + "]");
	} finally {
		
		try {

			if (getlmtfeeRs != null) {
				getlmtfeeRs.close();
			}

			if (getlmtfeePstmt != null) {
				getlmtfeePstmt.close();
			}

			if (connection != null) {
				connection.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		lmtfeeDataMap = null;
		resultJson = null;
	}

	return responseDTO;
}

public ResponseDTO AgentBonusapprovalinfoView(RequestDTO requestDTO) {

	logger.debug("Inside Create ProductAuth .... ");

	HashMap<String, Object> merchantMap = null;
	String merchantQry = "";
	JSONObject resultJson = null;

	Connection connection = null;
	PreparedStatement merchantPstmt = null;
	ResultSet merchantRS = null;
	String accountNumbers = "";

	try {

		responseDTO = new ResponseDTO();
		merchantMap = new HashMap<String, Object>();
		resultJson = new JSONObject();

		requestJSON = requestDTO.getRequestJSON();
		logger.debug("Request JSON [" + requestJSON + "]");
		responseJSON = requestJSON;

		connection = DBConnector.getConnection();
		logger.debug("connection is null [" + (connection == null) + "]");

		accountNumbers = requestJSON.getString("accNumbers");

		merchantQry = "SELECT REFERENCE_NO,F_NAME,M_NAME,L_NAME,MOBILE_NUMBER,W_ACCOUNT_NO,BONUS_AMT,PAY_REFERENCENO,EMAIL,MAKER_ID,to_char(MAKER_DT,'dd-mm-yyyy hh24:mi:ss'),MONTHYEAR FROM WALLET_AGENT_BONUS where STATUS='P' and REFERENCE_NO=? ";




		merchantPstmt = connection.prepareStatement(merchantQry);

		merchantPstmt.setString(1, accountNumbers);
		merchantRS = merchantPstmt.executeQuery();

		if (merchantRS.next()) {

			resultJson.put("REFERENCE_NO", merchantRS.getString(1));
			resultJson.put("fullname", merchantRS.getString(2));
			resultJson.put("middlename", merchantRS.getString(3));
			resultJson.put("lastname", merchantRS.getString(4));
			resultJson.put("telephone", merchantRS.getString(5));
			resultJson.put("walletaccountno", merchantRS.getString(6));
			resultJson.put("bonusamt", merchantRS.getString(7));
			resultJson.put("paymentref", merchantRS.getString(8));
			resultJson.put("email", merchantRS.getString(9));
			resultJson.put("MAKER_ID", merchantRS.getString(10));
			resultJson.put("MAKER_DT", merchantRS.getString(11));
			resultJson.put("monthyear", merchantRS.getString(12));
			
			

		}

		merchantPstmt.close();
		merchantRS.close();

		merchantMap.put("user_rights", resultJson);

		logger.debug("MerchantMap [" + merchantMap + "]");

		responseDTO.setData(merchantMap);

		logger.debug("Response DTO [" + responseDTO + "]");

	} catch (SQLException e) {
		responseDTO.addError("Internal Error Occured While Executing.");
		logger.debug("SQLException in  Get Biller ID Details ["
				+ e.getMessage() + "]");
	} catch (Exception e) {

		responseDTO.addError("Internal Error Occured While Executing.");
		logger.debug("Exception in  Get Biller ID Details ["
				+ e.getMessage() + "]");
	} finally {
		DBUtils.closeResultSet(merchantRS);
		DBUtils.closePreparedStatement(merchantPstmt);
		DBUtils.closeConnection(connection);
		merchantQry = null;
	}
	return responseDTO;
}

public ResponseDTO agentbonusapprovalAck(RequestDTO requestDTO) {
	
	responseDTO=new ResponseDTO();
	requestJSON=new JSONObject();
	responseJSON=new JSONObject();
	
	logger.debug("inside [AgentDAO][gtAgentRegistrtionack]");


	HashMap<String, Object> serviceDataMap = null;
	JSONObject resultJson = null;
	JSONArray IncomeMTFilesJSONArray = null;

	Connection connection = null;
	
	PreparedStatement pstmt = null;
	
	PreparedStatement pstmt1 = null;
	ResultSet rs1 = null;
	HashMap<String, Object> merchantMap = null;
	

	try {
		responseDTO = new ResponseDTO();
		responseJSON = new JSONObject();
		requestJSON = requestDTO.getRequestJSON();

		connection = DBConnector.getConnection();
		merchantMap = new HashMap<String, Object>();

		serviceDataMap = new HashMap<String, Object>();
		resultJson = new JSONObject();
		IncomeMTFilesJSONArray = new JSONArray();

				String fullname=requestJSON.getString("fullname")+" "+requestJSON.getString("middlename")+" "+requestJSON.getString("lastname");
				String telephone=requestJSON.getString("telephone");
				String walletaccountno=requestJSON.getString("walletaccountno");
				String bonusamt=requestJSON.getString("bonusamt");
				String paymentref=requestJSON.getString("paymentref");
				String reason=requestJSON.getString("reason");
				String requesttype=requestJSON.getString("requesttype");
				String referenceno=requestJSON.getString("referenceno");
				
				String makerid=requestJSON.getString(CevaCommonConstants.MAKER_ID);
				String remoteip=requestJSON.getString("remoteip");
		
		
		if(requesttype.equalsIgnoreCase("Approval")) {
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("UPDATE WALLET_AGENT_BONUS SET STATUS='I' where REFERENCE_NO=? AND MOBILE_NUMBER=? and W_ACCOUNT_NO=?");
			pstmt.setString(1, referenceno);
			pstmt.setString(2, telephone);
			pstmt.setString(3, walletaccountno);
			
			
			pstmt.executeUpdate();
			connection.commit();
			pstmt.close();
			
			JSONObject json1 = JSONObject.fromObject(ExternalServices.agentbonus(telephone,paymentref,bonusamt));
			
			if((json1.getString("respcode")).equalsIgnoreCase("00")) {
				
				pstmt = connection.prepareStatement("UPDATE WALLET_AGENT_BONUS SET STATUS='C',SERVICE_RESP=?,CHECKER_ID=?,CHECKER_DATE=sysdate where REFERENCE_NO=? AND MOBILE_NUMBER=? and W_ACCOUNT_NO=?");
				pstmt.setString(1, json1.getString("respdesc"));
				pstmt.setString(2, makerid);
				pstmt.setString(3, referenceno);
				pstmt.setString(4, telephone);
				pstmt.setString(5, walletaccountno);
				
				
				pstmt.executeUpdate();
			
			String Qry = "select trim(TO_CHAR('"+bonusamt+"', '999,999,999,999.00')) from dual ";
			pstmt = connection.prepareStatement(Qry);
			rs1 = pstmt.executeQuery();

			String months=requestJSON.getString("monthyear");
			String amt="";
			if (rs1.next()) {
				amt=rs1.getString(1);
			}
			
			
			StringBuilder sb=new StringBuilder();
			sb.append("<FONT FACE=ARIAL>Dear "+fullname+", <BR></FONT>");
			sb.append("<BR>As one of our top transacting Agents in "+months+", your wallet has been credited with the cash incentive of "+amt+" Naira.");
			sb.append("<BR>The Xpress Agent incentive campaign is still ongoing - we encourage to continue the good work and increase your transactions on the Xpress Agent platform.");
			
			pstmt = connection.prepareStatement("insert into EMAIL_TBL(EMAIL,SUBJECT,L_MESSAGE,FETCH_FLAG,MSG_DATE,MAKER_ID,TEMPLATE,MESSAGE_TYPE,ATTACHMENT,LONG_DATA,BCC_MAIL,CC_MAIL,NAME) "
					+ "values('"+requestJSON.getString("email")+"','Xpress Agent Transact & Win Promo','"+sb.toString()+"','N',sysdate,'"+makerid+"','STATEMENT','EMAIL','N','N','NO','NO','AGENTBONUS')");
			
			
			pstmt.executeUpdate();
			connection.commit();
			pstmt.close();
			resultJson.put("responsemsg", "Agent Bonus Successfully Created .");
			}else {
				pstmt = connection.prepareStatement("UPDATE WALLET_AGENT_BONUS SET STATUS='F',SERVICE_RESP=?,CHECKER_ID=?,CHECKER_DATE=sysdate where REFERENCE_NO=? AND MOBILE_NUMBER=? and W_ACCOUNT_NO=?");
				pstmt.setString(1, json1.getString("respdesc"));
				pstmt.setString(2, makerid);
				pstmt.setString(3, referenceno);
				pstmt.setString(4, telephone);
				pstmt.setString(5, walletaccountno);
				
				
				pstmt.executeUpdate();
				connection.commit();
				resultJson.put("responsemsg", "Agent Bonus Funding Failed  .");	
			}
			
		}else {
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("UPDATE WALLET_AGENT_BONUS SET STATUS='R',REASON=? where REFERENCE_NO=? AND MOBILE_NUMBER=? and W_ACCOUNT_NO=?");
			pstmt.setString(1, reason);
			pstmt.setString(2, referenceno);
			pstmt.setString(3, telephone);
			pstmt.setString(4, walletaccountno);
			
			
			pstmt.executeUpdate();
			connection.commit();
			resultJson.put("responsemsg", "Rejected Agent Bonus Request .");
		}
				
		
		merchantMap.put("user_rights", resultJson);

		logger.debug("MerchantMap [" + merchantMap + "]");

		responseDTO.setData(merchantMap);

	} catch (SQLException e) { 
		logger.debug("[AgentDAO][gtAgentRegistrtionack] SQLException in usdnastrooptionConf [" + e.getMessage()
				+ "]");
		responseDTO.addError("Internal Error Occured While Executing.");
	} catch (Exception e) {

		logger.debug("[AgentDAO][gtAgentRegistrtionack] SQLException in usdnastrooptionConf [" + e.getMessage()
				+ "]");
		responseDTO.addError("Internal Error Occured While Executing.");
	} finally {
		try {

			if (pstmt != null)
				pstmt.close();
			if (rs1 != null)
				rs1.close();
			if (connection != null)
				connection.close();

		} catch (SQLException e) {

		}
		serviceDataMap = null;
		resultJson = null;
		IncomeMTFilesJSONArray.clear();
		IncomeMTFilesJSONArray = null;
	}

	return responseDTO;
}

	
	public static String convertDOBnobvn(String pattern ,String dob)
	{

	String formateddob ="-";

	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	try {//20-MAY-1978
	Date date = sdf.parse(dob);
	sdf.applyPattern("dd-MMM-yyyy");
	formateddob = sdf.format(date);
	} catch (Exception e) {
	e.printStackTrace();
	}
	return formateddob;
	}
	
	public static void main(String args[]) {
		System.out.println(convertDOBnobvn("dd-MM-yyyy","12-12-2012"));
		/*try {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		Date date = simpleDateFormat.parse("2018-09-09");
		System.out.println(date);
		
		} catch (Exception e) {
			e.printStackTrace();
			}*/
	}

}
