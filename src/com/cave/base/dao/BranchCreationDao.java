package com.cave.base.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.log4j.Logger;

import com.ceva.base.common.dto.RequestDTO;
import com.ceva.base.common.dto.ResponseDTO;
import com.ceva.base.common.utils.CevaCommonConstants;
import com.ceva.base.common.utils.DBConnector;
import com.ceva.base.common.utils.DBUtil;
import com.ceva.base.service.utils.dao.CommonServiceDao;
import com.ceva.util.DBUtils;


public class BranchCreationDao {


	private Logger logger = Logger.getLogger(BranchCreationDao.class);

	ResponseDTO responseDTO = null;
	JSONObject requestJSON = null;
	JSONObject responseJSON = null;


	public ResponseDTO branchInfo(RequestDTO requestDTO) {

		logger.debug(" [ BranchCreationDao ] [ branchInfo ] Inside branchInfo Dao.. ");

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

			logger.debug("[ BranchCreationDao ] [ branchInfo ] connection is null [" + connection == null + "]");
			
			lmtfeeQry = "select CLUSTER_ID,CLUSTER_NAME,MAKER_ID,display_status	 from CLUSTER_TBL";

			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("CLUSTER_ID", getlmtfeeRs.getString(1));
				json.put("CLUSTER_NAME", getlmtfeeRs.getString(2));
				json.put("MAKER_ID", getlmtfeeRs.getString(3));
				json.put("STATUS", getlmtfeeRs.getString(4));
				
					
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
			logger.debug("[ BranchCreationDao ] [ branchInfo ] Got Exception  ["
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
	
	public ResponseDTO ClusterCreationAck(RequestDTO requestDTO) {


		Connection connection = null;
		HashMap<String, Object> branchMap = new HashMap<String, Object>();


		PreparedStatement servicePstmt = null;
		ResultSet serviceRS = null;

		String adminType=null;
		String adminName=null;
		String localGovernment =null;
		String stated = null;
		

		try {

			responseDTO = new ResponseDTO();
			responseJSON = new JSONObject();
			requestJSON = requestDTO.getRequestJSON();
			
			connection = DBConnector.getConnection();

			String maker_id=requestJSON.getString(CevaCommonConstants.MAKER_ID);
			String remoteip=requestJSON.getString("remoteip");
			
			String branchQry="insert into CLUSTER_TBL(CLUSTER_ID,CLUSTER_NAME,STATE,LGA,DISPLAY_STATUS,MAKER_ID,MAKER_DT) " +
					" values(?,?,UPPER(?),UPPER(?),'A',?,sysdate) ";

			servicePstmt=connection.prepareStatement(branchQry);
		


			JSONArray branchArr =  requestDTO.getRequestJSON().getJSONArray("FINAL_JSON");
			

			for (int i = 0; i < branchArr.size(); i++) {

				JSONObject reqData = branchArr.getJSONObject(i);

				adminType = reqData.getString("adminType");
				adminName = reqData.getString("adminName");
				stated = reqData.getString("stated");
				localGovernment = reqData.getString("localGovernment");
				
				
				System.out.println("adminType :: "+adminType+" :: adminName"+adminName+" maker id ::: "+maker_id);

				servicePstmt.setString(1,adminType);
				servicePstmt.setString(2,adminName);
				servicePstmt.setString(3,stated.split("-")[1]);
				servicePstmt.setString(4,localGovernment.split("-")[1]);
				servicePstmt.setString(5,maker_id);
				
				
				
				servicePstmt.addBatch();
				

			}

			servicePstmt.executeBatch(); // insert remaining records 
			servicePstmt.close();
			
			
			
			JSONObject json = new JSONObject();
			json.put(CevaCommonConstants.MAKER_ID, maker_id);
			json.put("transCode", "clusterCreation");
			json.put("channel", "WEB");
			json.put("message", "Acknowledgment :: Cluster Configuration : Cluster Id ::  "+adminType);
			json.put("ip", remoteip);
			json.put("det1", "");
			json.put("det2", "");
			json.put("det3", "");
			
			CommonServiceDao csd=new CommonServiceDao();
			csd.auditTrailInsert(json);
			
			connection.commit();

			responseDTO.setData(branchMap);

			logger.debug("Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("SQLException in DashboardUsers [" + e.getMessage()
					+ "]");
			responseDTO.addError("Internal Error Occured While Executing.");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("SQLException in DashboardUsers [" + e.getMessage()
					+ "]");
			responseDTO.addError("Internal Error Occured While Executing.");
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
			branchMap = null;


		}

		return responseDTO;
	}
	
	public ResponseDTO branchpopupDetails(RequestDTO requestDTO) {

		logger.debug("Inside fetchlimitcodeInfo.. ");
		HashMap<String, Object> limitcodeDataMap = null;

		JSONObject resultJson = null;
		JSONObject json = null;
		JSONArray JsonArray= new JSONArray();
		PreparedStatement getlimitcodePstmt = null;
		ResultSet getlimitcodeRs = null;
		Connection connection = null;

		String  limitcode = "";
		String  limitcodeCountQry = "select  CLUSTER_ID,CLUSTER_NAME,STATE,LGA,display_status from CLUSTER_TBL where CLUSTER_ID=?";

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

			if (getlimitcodeRs.next()) {				
				json.put("limitCode", getlimitcodeRs.getString(1));
			     json.put("limitDesc", getlimitcodeRs.getString(2));
			     json.put("STATE", getlimitcodeRs.getString(3));
			     json.put("LGA", getlimitcodeRs.getString(4));
			     json.put("status", getlimitcodeRs.getString(5));
			 
				
			}

			resultJson.put("limitcodedetails", json);	
			
			getlimitcodePstmt.close();
			getlimitcodeRs.close();
			
			String entityQry1 = "select user_name ,emp_id,NVL(location,' '),decode(USER_STATUS,'A','Active','L','De-Active','F','Active',USER_STATUS),NVL((SELECT GROUP_NAME from USER_GROUPS where GROUP_ID=user_groups),' '),"
					+ "NVL((select STATUS from CONFIG_DATA where key_group='USER_DESIGNATION' and key_value=user_level),' ') "
					+ "from user_information where CLUSTER_ID=? ";
			
			
			getlimitcodePstmt = connection.prepareStatement(entityQry1);
			getlimitcodePstmt.setString(1,limitcode);
			getlimitcodeRs = getlimitcodePstmt.executeQuery();
			     

			    while (getlimitcodeRs.next()) {
			     
			     json.put("user_name", getlimitcodeRs.getString(1));
			     json.put("emp_id", getlimitcodeRs.getString(2));
			     json.put("location", getlimitcodeRs.getString(3));
			     json.put("USER_STATUS", getlimitcodeRs.getString(4));
			     json.put("GROUP_NAME", getlimitcodeRs.getString(5));
			     json.put("STATUS", getlimitcodeRs.getString(6));
			     
			     JsonArray.add(json);
			     
			    }
			    
			    resultJson.put("limitcodedetails2", JsonArray);
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
	
	public ResponseDTO BranchModify(RequestDTO requestDTO) {

		logger.debug("Inside fetchlimitcodeInfo.. ");
		HashMap<String, Object> limitcodeDataMap = null;

		JSONObject resultJson = null;
		JSONObject json = null;
		JSONArray JsonArray= new JSONArray();
		PreparedStatement getlimitcodePstmt = null;
		ResultSet getlimitcodeRs = null;
		Connection connection = null;

		String  limitcode = "";
		String  limitcodeCountQry = "select  CLUSTER_ID,CLUSTER_NAME,STATE,LGA,DISPLAY_STATUS  from CLUSTER_TBL where CLUSTER_ID=?";

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

			if (getlimitcodeRs.next()) {				
				json.put("limitCode", getlimitcodeRs.getString(1));
			     json.put("limitDesc", getlimitcodeRs.getString(2));
			     json.put("STATE", getlimitcodeRs.getString(3));
			     json.put("LGA", getlimitcodeRs.getString(4));
			     json.put("status", getlimitcodeRs.getString(5));
			 
				
			}

			resultJson.put("limitcodedetails", json);	
			
			getlimitcodePstmt.close();
			getlimitcodeRs.close();
			
		
			    
			    resultJson.put("limitcodedetails2", JsonArray);
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
	
	public ResponseDTO BranchModifyAck(RequestDTO requestDTO) {

		logger.debug("Inside fetchlimitcodeInfo.. ");
		HashMap<String, Object> limitcodeDataMap = null;

		JSONObject resultJson = null;
		JSONObject json = null;
		JSONArray JsonArray= new JSONArray();
		PreparedStatement getlimitcodePstmt = null;
		ResultSet getlimitcodeRs = null;
		Connection connection = null;

		String  limitcode = "";
		String  limitcodeCountQry = "UPDATE  CLUSTER_TBL set  CLUSTER_NAME=?,STATE=?,LGA=?  WHERE CLUSTER_ID=?";
		// System.out.println("ranjit123 limitcode"+limitcode);
		try {
			requestJSON = requestDTO.getRequestJSON();
			responseDTO = new ResponseDTO();
			limitcodeDataMap = new HashMap<String, Object>();
			resultJson = new JSONObject();

			connection = DBConnector.getConnection();

			limitcode = requestJSON.getString(CevaCommonConstants. LIMIT_CODE);
                
			getlimitcodePstmt = connection.prepareStatement(limitcodeCountQry);
			getlimitcodePstmt.setString(1,requestJSON.getString("limitDesc"));
			getlimitcodePstmt.setString(2,requestJSON.getString("state"));
			getlimitcodePstmt.setString(3,requestJSON.getString("localGovernment"));
			getlimitcodePstmt.setString(4,limitcode);

			getlimitcodePstmt.executeUpdate();
			
			connection.commit();
			json = new JSONObject();
         
			
			    
			    resultJson.put("limitcodedetails2", JsonArray);
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
	
	public ResponseDTO BranchstatusAck(RequestDTO requestDTO) {

		logger.debug("Inside fetchlimitcodeInfo.. ");
		HashMap<String, Object> limitcodeDataMap = null;

		JSONObject resultJson = null;
		JSONObject json = null;
		JSONArray JsonArray= new JSONArray();
		PreparedStatement getlimitcodePstmt = null;
		ResultSet getlimitcodeRs = null;
		Connection connection = null;

		String  limitcode = "";
		String status=null;
		String  limitcodeCountQry = "UPDATE  CLUSTER_TBL set DISPLAY_STATUS=?  WHERE CLUSTER_ID=?";
		try {
			requestJSON = requestDTO.getRequestJSON();
			responseDTO = new ResponseDTO();
			limitcodeDataMap = new HashMap<String, Object>();
			resultJson = new JSONObject();

			connection = DBConnector.getConnection();

			limitcode = requestJSON.getString(CevaCommonConstants. LIMIT_CODE);
			status = requestJSON.getString(CevaCommonConstants.status);
	             
			if(status.equalsIgnoreCase("A")) {
				status="D";
			}else {
				status="A";
			}
			getlimitcodePstmt = connection.prepareStatement(limitcodeCountQry);
			getlimitcodePstmt.setString(1,status);
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


}
