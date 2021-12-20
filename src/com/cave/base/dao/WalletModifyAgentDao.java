package com.cave.base.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ceva.base.common.dto.JsonValueValidator;
import com.ceva.base.common.dto.RequestDTO;
import com.ceva.base.common.dto.ResponseDTO;
import com.ceva.base.common.utils.CevaCommonConstants;
import com.ceva.base.common.utils.DBConnector;
import com.ceva.util.DBUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WalletModifyAgentDao {
	private Logger logger = Logger.getLogger(WalletModifyAgentDao.class);

	ResponseDTO responseDTO = null;
	JSONObject requestJSON = null;
	JSONObject responseJSON = null;
	
	public ResponseDTO agentModifyDetails(RequestDTO requestDTO) {
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
			
			System.out.println("fname -- "+requestJSON.getString("fname"));
			System.out.println("middlename -- "+requestJSON.getString("middlename"));
			System.out.println("lastname -- "+requestJSON.getString("lastname"));
		    System.out.println("email -- "+requestJSON.getString("email"));
		    System.out.println("dob -- "+requestJSON.getString("dateofbirth"));
			System.out.println("telephone -- "+requestJSON.getString("telephone"));
			System.out.println("gender -- "+requestJSON.getString("gender"));
			System.out.println("marital -- "+requestJSON.getString("marital"));
			System.out.println("merchanttype -- "+requestJSON.getString("merchanttype"));
			System.out.println("businessname -- "+requestJSON.getString("business"));
		    System.out.println("idcardtype -- "+requestJSON.getString("idtype"));
			System.out.println("idcardnumber -- "+requestJSON.getString("iddetails"));
			System.out.println("addressLine -- "+requestJSON.getString("address"));
			System.out.println("nationality -- "+requestJSON.getString("nationality"));
			System.out.println("streetname -- "+requestJSON.getString("area"));
			System.out.println("city -- "+requestJSON.getString("city"));
			System.out.println("state -- "+requestJSON.getString("state"));
			System.out.println("localGovernment -- "+requestJSON.getString("lga"));
			System.out.println("wardname -- "+requestJSON.getString("wardname"));
			System.out.println("country -- "+requestJSON.getString("country"));
			
			/*
			 * System.out.println("bankname -- "+requestJSON.getString("bankname"));
			 * System.out.println("bankaccountnumber -- "+requestJSON.getString(
			 * "bankaccountnumber"));
			 * System.out.println("bankcustname -- "+requestJSON.getString("bankcustname"));
			 * System.out.println("bvn -- "+requestJSON.getString("bvn"));
			 */
			
			System.out.println("makerid -- "+requestJSON.getString(CevaCommonConstants.MAKER_ID));
			System.out.println("IP -- "+requestJSON.getString(CevaCommonConstants.IP));
			
			
			
				
				

				insQRY = "{call WALLETREGISTRATIONPKG.AGENTMODIFY(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

				
				ip = requestJSON.getString(CevaCommonConstants.IP);


				
				
				cstmt = connection.prepareCall(insQRY);
				
				cstmt.setString(1, requestJSON.getString("customercode"));
				cstmt.setString(2, requestJSON.getString("fname"));
				cstmt.setString(3, requestJSON.getString("middlename"));
				cstmt.setString(4, requestJSON.getString("lastname"));
				cstmt.setString(5, requestJSON.getString("email"));
				cstmt.setString(6, requestJSON.getString("dateofbirth"));
				cstmt.setString(7, requestJSON.getString("telephone"));
				cstmt.setString(8, requestJSON.getString("gender"));
				cstmt.setString(9, requestJSON.getString("marital"));
				cstmt.setString(10, requestJSON.getString("address")); 
				cstmt.setString(11, requestJSON.getString("nationality"));			
				cstmt.setString(12, requestJSON.getString("area"));
				cstmt.setString(13, requestJSON.getString("city"));
				cstmt.setString(14, requestJSON.getString("state"));
				cstmt.setString(15, requestJSON.getString("lga"));			
				cstmt.setString(16, requestJSON.getString("wardname"));
				cstmt.setString(17, requestJSON.getString("country"));
				cstmt.setString(18, JsonValueValidator.stripXSSRev(requestJSON.getString("business")));
			/*
			 * cstmt.setString(15, requestJSON.getString("bankname"));
			 * 
			 * cstmt.setString(16, requestJSON.getString("bankaccountnumber"));
			 * cstmt.setString(17, requestJSON.getString("bankcustname"));
			 * cstmt.setString(18, requestJSON.getString("bvn"));
			 */
				
				
				cstmt.setString(19, requestJSON.getString(CevaCommonConstants.MAKER_ID));
				cstmt.setString(20, requestJSON.getString(CevaCommonConstants.IP));
				cstmt.setString(21, requestJSON.getString("staffcode"));
				
				cstmt.registerOutParameter(22, Types.VARCHAR);
				cstmt.executeQuery();

				if (!(cstmt.getString(22).split("-")[1]).contains("SUCCESS")) {
					responseDTO.addError((cstmt.getString(22)).split("-")[0]);
				}else{
					
					responseDTO.addMessages((cstmt.getString(22)).split("-")[0]);
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


	public ResponseDTO AgentUpdatePinSearch(RequestDTO requestDTO) {

		
		responseDTO=new ResponseDTO();
		requestJSON=new JSONObject();
		responseJSON=new JSONObject();
		
		logger.debug("inside [AgentDAO][AgentUpdatePinSearch]");

		logger.debug("Inside  AgentUpdatePinSearch.... ");

		HashMap<String, Object> serviceDataMap = null;
		JSONObject resultJson = null;
		JSONArray IncomeMTFilesJSONArray = null;

		Connection connection = null;

		PreparedStatement servicePstmt = null;

		ResultSet serviceIdRS = null;
		ResultSet serviceRS = null;
		String userReport="";

		try {
			responseDTO = new ResponseDTO();
			responseJSON = new JSONObject();
			requestJSON = requestDTO.getRequestJSON();

			connection = DBConnector.getConnection();

			logger.debug("Connection is null [" + connection == null + "]");

			serviceDataMap = new HashMap<String, Object>();
			resultJson = new JSONObject();
			IncomeMTFilesJSONArray = new JSONArray();
			String makerId=requestJSON.getString(CevaCommonConstants.MAKER_ID);
			String remoteip=requestJSON.getString("remoteip");
			String msg="";
			
			String customercode=((requestJSON.getString("customercode")).equalsIgnoreCase("")) ? null :  "'"+requestJSON.getString("customercode")+"'";
			String application=((requestJSON.getString("application")).equalsIgnoreCase("")) ? null : ""+requestJSON.getString("application")+"";

			
			
			
			 String Actionname="";
			 String Actiondesc="";
			
			
				 
			servicePstmt = connection.prepareStatement("select ACM.ACCOUNT_NO,ACM.FIRST_NAME,ACM.MIDDLE_NAME,ACM.LAST_NAME,ACM.ACC_BRANCH,to_char(ACM.DOB,'dd-mm-yyyy'),ACM.EMAILID,ACM.GENDER,ACM.MARITAL_STATUS,ACM.W_PRD_CODE,MCI.MOBILE_NUMBER,"
				  		+ "MCI.ADDRESS,MCI.ID_TYPE,MCI.ID_DETAILS,MCI.NATIONALITY,MCI.RL_LGA,MCI.R_STATE,MCI.COUNTRY,decode(ACM.STATUS,'B','Block','F','Inactive','A','Active','L','Deactivation','Active'),ACM.ID,NVL(ACM.USER_ID,' '),WAD.ACCT_NO,to_char(ACM.DATE_CREATED,'dd-mm-yyyy hh:mi:ss'),MCI.AREA,MCI.CITY,MCI.WARD_NAME,ACM.BANK_CUST_NAME,ACM.BANK_NAME,ACM.BVN,ACM.MCC_NAME,W_PRD_CODE,W_PRD_DESC,BUSINESS_NAME,NVL(ACM.RM_CODE,' ') from AGENT_CUSTOMER_MASTER ACM,AGENT_CONTACT_INFO MCI,WALLET_ACCT_DATA WAD where ACM.ID=MCI.CUST_ID and MCI.CUST_ID=WAD.CUST_ID and WAD.CUST_TYPE='AGENT' and ACM.CUSTOMER_CODE="+customercode+"");
				  serviceRS = servicePstmt.executeQuery();
				  JSONObject json = new JSONObject();
					 while(serviceRS.next())
						{
						json.put("customercode",requestJSON.getString("customercode"));
						json.put("accountno",serviceRS.getString(1));
						json.put("firstname",serviceRS.getString(2));
						json.put("middlename",serviceRS.getString(3));
						json.put("lastname",serviceRS.getString(4));
						json.put("branchcode",serviceRS.getString(5));
						json.put("dateofbirth",serviceRS.getString(6));
						json.put("email",serviceRS.getString(7));
						json.put("gender",serviceRS.getString(8));
						json.put("marital",serviceRS.getString(9));
						json.put("product",serviceRS.getString(10));
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
						json.put("bankcustname",serviceRS.getString(27));
						json.put("bankname",serviceRS.getString(28));
						json.put("bvn",serviceRS.getString(29));
						json.put("mcc",serviceRS.getString(30));
						json.put("productdesc",serviceRS.getString(32));
						json.put("business",serviceRS.getString(33));
						json.put("staffcode",serviceRS.getString(34));
						
						}
			
		
			serviceDataMap.put("Files_List", json);

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
			IncomeMTFilesJSONArray.clear();
			IncomeMTFilesJSONArray = null;
		}

		return responseDTO;
		
	}
	
public ResponseDTO linkedAccountModify(RequestDTO requestDTO) {

		
		responseDTO=new ResponseDTO();
		requestJSON=new JSONObject();
		responseJSON=new JSONObject();
		
		logger.debug("inside [AgentDAO][AgentUpdatePinSearch]");

		logger.debug("Inside  AgentUpdatePinSearch.... ");

		HashMap<String, Object> serviceDataMap = null;
		JSONObject resultJson = null;
		JSONArray IncomeMTFilesJSONArray = null;

		Connection connection = null;

		PreparedStatement servicePstmt = null;

		ResultSet serviceIdRS = null;
		ResultSet serviceRS = null;
		String userReport="";

		try {
			responseDTO = new ResponseDTO();
			responseJSON = new JSONObject();
			requestJSON = requestDTO.getRequestJSON();

			connection = DBConnector.getConnection();

			logger.debug("Connection is null [" + connection == null + "]");

			serviceDataMap = new HashMap<String, Object>();
			resultJson = new JSONObject();
			IncomeMTFilesJSONArray = new JSONArray();
			String makerId=requestJSON.getString(CevaCommonConstants.MAKER_ID);
			String remoteip=requestJSON.getString("remoteip");
			String msg="";
			
			String customercode=((requestJSON.getString("customercode")).equalsIgnoreCase("")) ? null :  "'"+requestJSON.getString("customercode")+"'";
			String application=((requestJSON.getString("application")).equalsIgnoreCase("")) ? null : ""+requestJSON.getString("application")+"";

			
			
			
			 String Actionname="";
			 String Actiondesc="";
			
			
				 
			servicePstmt = connection.prepareStatement("select ACM.ACCOUNT_NO,ACM.FIRST_NAME,ACM.MIDDLE_NAME,ACM.LAST_NAME,ACM.ACC_BRANCH,to_char(ACM.DOB,'dd-mm-yyyy'),ACM.EMAILID,ACM.GENDER,ACM.MARITAL_STATUS,ACM.W_PRD_CODE,MCI.MOBILE_NUMBER,"
				  		+ "MCI.ADDRESS,MCI.ID_TYPE,MCI.ID_DETAILS,MCI.NATIONALITY,MCI.RL_LGA,MCI.R_STATE,MCI.COUNTRY,decode(ACM.STATUS,'B','Block','F','Inactive','A','Active','L','Deactivation','Active'),ACM.ID,NVL(ACM.USER_ID,' '),WAD.ACCT_NO,to_char(ACM.DATE_CREATED,'dd-mm-yyyy hh:mi:ss'),MCI.AREA,MCI.CITY,MCI.WARD_NAME,ACM.BANK_CUST_NAME,ACM.BANK_NAME,ACM.BVN,ACM.MCC_NAME,W_PRD_CODE,W_PRD_DESC,BUSINESS_NAME from AGENT_CUSTOMER_MASTER ACM,AGENT_CONTACT_INFO MCI,WALLET_ACCT_DATA WAD where ACM.ID=MCI.CUST_ID and MCI.CUST_ID=WAD.CUST_ID and WAD.CUST_TYPE='AGENT' and ACM.CUSTOMER_CODE="+customercode+"");
				  serviceRS = servicePstmt.executeQuery();
				  JSONObject json = new JSONObject();
					 while(serviceRS.next())
						{
						json.put("customercode",requestJSON.getString("customercode"));
						json.put("accountno",serviceRS.getString(1));
						json.put("firstname",serviceRS.getString(2));
						json.put("middlename",serviceRS.getString(3));
						json.put("lastname",serviceRS.getString(4));
						json.put("branchcode",serviceRS.getString(5));
						json.put("dateofbirth",serviceRS.getString(6));
						json.put("email",serviceRS.getString(7));
						json.put("gender",serviceRS.getString(8));
						json.put("marital",serviceRS.getString(9));
						json.put("product",serviceRS.getString(10));
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
						json.put("bankcustname",serviceRS.getString(27));
						json.put("bankname",serviceRS.getString(28));
						json.put("bvn",serviceRS.getString(29));
						json.put("mcc",serviceRS.getString(30));
						json.put("productdesc",serviceRS.getString(32));
						json.put("business",serviceRS.getString(33));
						
						}
			
		
			serviceDataMap.put("Files_List", json);

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
			IncomeMTFilesJSONArray.clear();
			IncomeMTFilesJSONArray = null;
		}

		return responseDTO;
		
	}

public ResponseDTO cashoutAccountModify(RequestDTO requestDTO) {

	
	responseDTO=new ResponseDTO();
	requestJSON=new JSONObject();
	responseJSON=new JSONObject();
	
	logger.debug("inside [AgentDAO][AgentUpdatePinSearch]");

	logger.debug("Inside  AgentUpdatePinSearch.... ");

	HashMap<String, Object> serviceDataMap = null;
	JSONObject resultJson = null;
	JSONArray IncomeMTFilesJSONArray = null;

	Connection connection = null;

	PreparedStatement servicePstmt = null;

	ResultSet serviceIdRS = null;
	ResultSet serviceRS = null;
	String userReport="";

	try {
		responseDTO = new ResponseDTO();
		responseJSON = new JSONObject();
		requestJSON = requestDTO.getRequestJSON();

		connection = DBConnector.getConnection();

		logger.debug("Connection is null [" + connection == null + "]");

		serviceDataMap = new HashMap<String, Object>();
		resultJson = new JSONObject();
		IncomeMTFilesJSONArray = new JSONArray();
		String makerId=requestJSON.getString(CevaCommonConstants.MAKER_ID);
		String remoteip=requestJSON.getString("remoteip");
		String msg="";
		
		String customercode=((requestJSON.getString("customercode")).equalsIgnoreCase("")) ? null :  "'"+requestJSON.getString("customercode")+"'";
		String application=((requestJSON.getString("application")).equalsIgnoreCase("")) ? null : ""+requestJSON.getString("application")+"";

		
		
		
		 String Actionname="";
		 String Actiondesc="";
		
		
			 
		servicePstmt = connection.prepareStatement("select ACM.ACCOUNT_NO,ACM.FIRST_NAME,ACM.MIDDLE_NAME,ACM.LAST_NAME,ACM.ACC_BRANCH,to_char(ACM.DOB,'dd-mm-yyyy'),ACM.EMAILID,ACM.GENDER,ACM.MARITAL_STATUS,ACM.W_PRD_CODE,MCI.MOBILE_NUMBER,"
			  		+ "MCI.ADDRESS,MCI.ID_TYPE,MCI.ID_DETAILS,MCI.NATIONALITY,MCI.RL_LGA,MCI.R_STATE,MCI.COUNTRY,decode(ACM.STATUS,'B','Block','F','Inactive','A','Active','L','Deactivation','Active'),ACM.ID,NVL(ACM.USER_ID,' '),WAD.ACCT_NO,to_char(ACM.DATE_CREATED,'dd-mm-yyyy hh:mi:ss'),MCI.AREA,MCI.CITY,MCI.WARD_NAME,ACM.BANK_CUST_NAME,ACM.BANK_NAME,ACM.BVN,ACM.MCC_NAME,W_PRD_CODE,W_PRD_DESC,BUSINESS_NAME from AGENT_CUSTOMER_MASTER ACM,AGENT_CONTACT_INFO MCI,WALLET_ACCT_DATA WAD where ACM.ID=MCI.CUST_ID and MCI.CUST_ID=WAD.CUST_ID and WAD.CUST_TYPE='AGENT' and ACM.CUSTOMER_CODE="+customercode+"");
			  serviceRS = servicePstmt.executeQuery();
			  JSONObject json = new JSONObject();
				 while(serviceRS.next())
					{
					json.put("customercode",requestJSON.getString("customercode"));
					json.put("accountno",serviceRS.getString(1));
					json.put("firstname",serviceRS.getString(2));
					json.put("middlename",serviceRS.getString(3));
					json.put("lastname",serviceRS.getString(4));
					json.put("branchcode",serviceRS.getString(5));
					json.put("dateofbirth",serviceRS.getString(6));
					json.put("email",serviceRS.getString(7));
					json.put("gender",serviceRS.getString(8));
					json.put("marital",serviceRS.getString(9));
					json.put("product",serviceRS.getString(10));
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
					json.put("bankcustname",serviceRS.getString(27));
					json.put("bankname",serviceRS.getString(28));
					json.put("bvn",serviceRS.getString(29));
					json.put("mcc",serviceRS.getString(30));
					json.put("productdesc",serviceRS.getString(32));
					json.put("business",serviceRS.getString(33));
					
					}
				 
				 servicePstmt.close(); 
				 serviceRS.close();
				 
				 servicePstmt = connection.prepareStatement("select BEN_BANK_NAME,BEN_ACCT,BEN_NAME from AGENT_CASHOUT_ACT_TBL where USER_ID='"+json.getString("telephone")+"'");
					  serviceRS = servicePstmt.executeQuery();
					  
						 while(serviceRS.next())
							{
							json.put("BEN_BANK_NAME",serviceRS.getString(1));
							json.put("BEN_ACCT",serviceRS.getString(2));
							json.put("BEN_NAME",serviceRS.getString(3));
							
							}
		
	
		serviceDataMap.put("Files_List", json);

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
		IncomeMTFilesJSONArray.clear();
		IncomeMTFilesJSONArray = null;
	}

	return responseDTO;
	
}

public ResponseDTO linkedAccountDetails(RequestDTO requestDTO) {
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
		
		
		System.out.println("telephone -- "+requestJSON.getString("telephone"));
		
		System.out.println("custid -- "+requestJSON.getString("customercode"));
		
		System.out.println("bankname -- "+requestJSON.getString("bankname"));
		System.out.println("bankaccountnumber -- "+(requestJSON.getString("bankaccnumber")).trim());
		System.out.println("bankcustname -- "+requestJSON.getString("accname"));
		System.out.println("bvn -- "+requestJSON.getString("bvn"));
		
		
		System.out.println("makerid -- "+requestJSON.getString(CevaCommonConstants.MAKER_ID));
		System.out.println("IP -- "+requestJSON.getString(CevaCommonConstants.IP));
		
		
		
			
			

			insQRY = "{call WALLETREGISTRATIONPKG.LINKEDACCOUNT(?,?,?,?,?,?,?,?,?)}";

			
			ip = requestJSON.getString(CevaCommonConstants.IP);


			
			
			cstmt = connection.prepareCall(insQRY);
			
			cstmt.setString(1, requestJSON.getString("customercode"));			
			cstmt.setString(2, requestJSON.getString("telephone"));			
			cstmt.setString(3, requestJSON.getString("bankname"));			
			cstmt.setString(4, (requestJSON.getString("bankaccnumber")).trim());
			cstmt.setString(5, requestJSON.getString("accname"));
			cstmt.setString(6, requestJSON.getString("bvn"));			
			cstmt.setString(7, requestJSON.getString(CevaCommonConstants.MAKER_ID));
			cstmt.setString(8, requestJSON.getString(CevaCommonConstants.IP));
			
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.executeQuery();

			if (!(cstmt.getString(9)).contains("SUCCESS")) {
				responseDTO.addError(cstmt.getString(9));
			}else{
				
				responseDTO.addMessages(cstmt.getString(9));
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

public ResponseDTO SuperAgentApprovalSearch(RequestDTO requestDTO) {

	
	responseDTO=new ResponseDTO();
	requestJSON=new JSONObject();
	responseJSON=new JSONObject();
	
	logger.debug("inside [AgentDAO][AgentUpdatePinSearch]");

	logger.debug("Inside  AgentUpdatePinSearch.... ");

	HashMap<String, Object> serviceDataMap = null;
	JSONObject resultJson = null;
	JSONArray IncomeMTFilesJSONArray = null;

	Connection connection = null;

	PreparedStatement servicePstmt = null;

	ResultSet serviceIdRS = null;
	ResultSet serviceRS = null;
	String userReport="";

	try {
		responseDTO = new ResponseDTO();
		responseJSON = new JSONObject();
		requestJSON = requestDTO.getRequestJSON();

		connection = DBConnector.getConnection();

		logger.debug("Connection is null [" + connection == null + "]");

		serviceDataMap = new HashMap<String, Object>();
		resultJson = new JSONObject();
		IncomeMTFilesJSONArray = new JSONArray();
		String makerId=requestJSON.getString(CevaCommonConstants.MAKER_ID);
		String remoteip=requestJSON.getString("remoteip");
		String msg="";
		
		String customercode=((requestJSON.getString("customercode")).equalsIgnoreCase("")) ? null :  "'"+requestJSON.getString("customercode")+"'";

		
		
		
	
		
			 
		servicePstmt = connection.prepareStatement("select FIRST_NAME,MIDDLE_NAME,LAST_NAME,DOB,EMAILID,GENDER,MARITAL_STATUS,MCC_NAME,BUSINESS_NAME,MOBILE_NUMBER,ADDRESS,NATIONALITY,STREET_NAME,CITY,STATE,LOCAL_GVM,WARD_NAME,COUNTRY,ID_CARD_TYPE,ID_CARD_NO,ID_IMAGE from SUPER_AGENT_AGENTS where ID="+customercode+"");
			  serviceRS = servicePstmt.executeQuery();
			  JSONObject json = new JSONObject();
				 while(serviceRS.next())
					{
					json.put("customercode",requestJSON.getString("customercode"));
					json.put("firstname",serviceRS.getString(1));
					json.put("middlename",serviceRS.getString(2));
					json.put("lastname",serviceRS.getString(3));
					json.put("dateofbirth",serviceRS.getString(4));
					json.put("email",serviceRS.getString(5));
					json.put("gender",serviceRS.getString(6));
					json.put("marital",serviceRS.getString(7));
					json.put("mcc",serviceRS.getString(8));
					json.put("business",serviceRS.getString(9));
					json.put("telephone",serviceRS.getString(10));
					
					json.put("address",serviceRS.getString(11));
					json.put("nationality",serviceRS.getString(12));
					json.put("streetname",serviceRS.getString(13));
					json.put("city",serviceRS.getString(14));
					json.put("state",serviceRS.getString(15));
					json.put("lgv",serviceRS.getString(16));
					json.put("wardname",serviceRS.getString(17));
					json.put("country",serviceRS.getString(18));
					json.put("idtype",serviceRS.getString(19));
					json.put("idnumber",serviceRS.getString(20));
					json.put("idimage",serviceRS.getString(21));
					
					
					
					}
		
	
		serviceDataMap.put("Files_List", json);

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
		IncomeMTFilesJSONArray.clear();
		IncomeMTFilesJSONArray = null;
	}

	return responseDTO;
	
}

public ResponseDTO cashoutAccountView(RequestDTO requestDTO) {

	
	responseDTO=new ResponseDTO();
	requestJSON=new JSONObject();
	responseJSON=new JSONObject();
	
	logger.debug("inside [AgentDAO][AgentUpdatePinSearch]");

	logger.debug("Inside  AgentUpdatePinSearch.... ");

	HashMap<String, Object> serviceDataMap = null;
	JSONObject resultJson = null;
	JSONArray IncomeMTFilesJSONArray = null;

	Connection connection = null;

	PreparedStatement servicePstmt = null;

	ResultSet serviceIdRS = null;
	ResultSet serviceRS = null;
	String userReport="";

	try {
		responseDTO = new ResponseDTO();
		responseJSON = new JSONObject();
		requestJSON = requestDTO.getRequestJSON();

		connection = DBConnector.getConnection();

		logger.debug("Connection is null [" + connection == null + "]");

		serviceDataMap = new HashMap<String, Object>();
		resultJson = new JSONObject();
		IncomeMTFilesJSONArray = new JSONArray();
		String makerId=requestJSON.getString(CevaCommonConstants.MAKER_ID);
		String remoteip=requestJSON.getString("remoteip");
		String msg="";
		
		String customercode=((requestJSON.getString("customercode")).equalsIgnoreCase("")) ? null :  "'"+requestJSON.getString("customercode")+"'";
		String application=((requestJSON.getString("application")).equalsIgnoreCase("")) ? null : ""+requestJSON.getString("application")+"";

		
		
		
		 String Actionname="";
		 String Actiondesc="";
		
		
			 
		servicePstmt = connection.prepareStatement("select ACO.BEN_ACCT,ACM.FIRST_NAME,ACM.MIDDLE_NAME,ACM.LAST_NAME,ACM.ACC_BRANCH,to_char(ACM.DOB,'dd-mm-yyyy'),ACM.EMAILID,ACM.GENDER,ACM.MARITAL_STATUS,ACM.W_PRD_CODE,MCI.MOBILE_NUMBER," + 
				"MCI.ADDRESS,MCI.ID_TYPE,MCI.ID_DETAILS,MCI.NATIONALITY,MCI.RL_LGA,MCI.R_STATE,MCI.COUNTRY,decode(ACM.STATUS,'B','Block','F','Inactive','A','Active','L','Deactivation','Active'),ACM.ID,NVL(ACM.USER_ID,' '),WAD.ACCT_NO,to_char(ACM.DATE_CREATED,'dd-mm-yyyy hh:mi:ss'),MCI.AREA,MCI.CITY,MCI.WARD_NAME,ACO.BEN_NAME,ACO.BEN_BANK_NAME,ACM.BVN,ACM.MCC_NAME,W_PRD_CODE,W_PRD_DESC,BUSINESS_NAME from " + 
				"AGENT_CUSTOMER_MASTER ACM,AGENT_CONTACT_INFO MCI,WALLET_ACCT_DATA WAD ,AGENT_CASHOUT_ACT_TBL ACO " + 
				"where ACM.ID=MCI.CUST_ID and " + 
				"MCI.MOBILE_NUMBER=ACO.user_id " + 
				" and MCI.CUST_ID=WAD.CUST_ID and WAD.CUST_TYPE='AGENT'  and ACM.CUSTOMER_CODE="+customercode+"");
			  serviceRS = servicePstmt.executeQuery();
			  JSONObject json = new JSONObject();
				 while(serviceRS.next())
					{
					json.put("customercode",requestJSON.getString("customercode"));
					json.put("accountno",serviceRS.getString(1));
					json.put("firstname",serviceRS.getString(2));
					json.put("middlename",serviceRS.getString(3));
					json.put("lastname",serviceRS.getString(4));
					json.put("branchcode",serviceRS.getString(5));
					json.put("dateofbirth",serviceRS.getString(6));
					json.put("email",serviceRS.getString(7));
					json.put("gender",serviceRS.getString(8));
					json.put("marital",serviceRS.getString(9));
					json.put("product",serviceRS.getString(10));
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
					json.put("bankcustname",serviceRS.getString(27));
					json.put("bankname",serviceRS.getString(28));
					json.put("bvn",serviceRS.getString(29));
					json.put("mcc",serviceRS.getString(30));
					json.put("productdesc",serviceRS.getString(32));
					json.put("business",serviceRS.getString(33));
					
					}
		
	
		serviceDataMap.put("Files_List", json);

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
		IncomeMTFilesJSONArray.clear();
		IncomeMTFilesJSONArray = null;
	}

	return responseDTO;
	
}

public ResponseDTO CashoutAccountDetails(RequestDTO requestDTO) {
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
		
		
		System.out.println("telephone -- "+requestJSON.getString("telephone"));
		
		System.out.println("custid -- "+requestJSON.getString("customercode"));
		
		System.out.println("bankname -- "+requestJSON.getString("bankname"));
		System.out.println("bankaccountnumber -- "+(requestJSON.getString("bankaccnumber")).trim());
		System.out.println("bankcustname -- "+requestJSON.getString("accname"));
		System.out.println("accbvn -- "+requestJSON.getString("accbvn"));
		
		
		System.out.println("makerid -- "+requestJSON.getString(CevaCommonConstants.MAKER_ID));
		System.out.println("IP -- "+requestJSON.getString(CevaCommonConstants.IP));
		
		
		
			
			

			insQRY = "{call WALLETREGISTRATIONPKG.CASHOUTACCOUNT(?,?,?,?,?,?,?,?,?)}";

			
			ip = requestJSON.getString(CevaCommonConstants.IP);


			
			
			cstmt = connection.prepareCall(insQRY);
			
			cstmt.setString(1, (requestJSON.getString("bankname")).split("-")[0]);			
			cstmt.setString(2, requestJSON.getString("telephone"));			
			cstmt.setString(3, (requestJSON.getString("bankname")).split("-")[1]);			
			cstmt.setString(4, (requestJSON.getString("bankaccnumber")).trim());
			cstmt.setString(5, requestJSON.getString("accname"));
			cstmt.setString(6, requestJSON.getString("accbvn"));
	    	cstmt.setString(7, requestJSON.getString(CevaCommonConstants.MAKER_ID));
			cstmt.setString(8, requestJSON.getString(CevaCommonConstants.IP));
			
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.executeQuery();

			if (!(cstmt.getString(9)).contains("SUCCESS")) {
				responseDTO.addError(cstmt.getString(9));
			}else{
				
				responseDTO.addMessages(cstmt.getString(9));
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

public ResponseDTO CashoutAccountModifyDetails(RequestDTO requestDTO) {
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
		
		
		System.out.println("telephone -- "+requestJSON.getString("telephone"));
		
		System.out.println("custid -- "+requestJSON.getString("customercode"));
		
		System.out.println("bankname -- "+requestJSON.getString("bankname"));
		System.out.println("bankaccountnumber -- "+(requestJSON.getString("bankaccnumber")).trim());
		System.out.println("bankcustname -- "+requestJSON.getString("accname"));
		System.out.println("accbvn -- "+requestJSON.getString("accbvn"));
		
		
		System.out.println("makerid -- "+requestJSON.getString(CevaCommonConstants.MAKER_ID));
		System.out.println("IP -- "+requestJSON.getString(CevaCommonConstants.IP));
		
		
		
			
			

			insQRY = "{call WALLETREGISTRATIONPKG.CASHOUTACCOUNTMODIFY(?,?,?,?,?,?,?,?,?)}";

			
			ip = requestJSON.getString(CevaCommonConstants.IP);


			
			
			cstmt = connection.prepareCall(insQRY);
			
			cstmt.setString(1, (requestJSON.getString("bankname")).split("-")[0]);			
			cstmt.setString(2, requestJSON.getString("telephone"));			
			cstmt.setString(3, (requestJSON.getString("bankname")).split("-")[1]);			
			cstmt.setString(4, (requestJSON.getString("bankaccnumber")).trim());
			cstmt.setString(5, requestJSON.getString("accname"));
			cstmt.setString(6, requestJSON.getString("accbvn"));
	    	cstmt.setString(7, requestJSON.getString(CevaCommonConstants.MAKER_ID));
			cstmt.setString(8, requestJSON.getString(CevaCommonConstants.IP));
			
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.executeQuery();

			if (!(cstmt.getString(9)).contains("SUCCESS")) {
				responseDTO.addError(cstmt.getString(9));
			}else{
				
				responseDTO.addMessages(cstmt.getString(9));
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


}
