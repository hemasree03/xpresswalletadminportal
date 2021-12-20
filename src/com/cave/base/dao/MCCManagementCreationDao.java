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
import com.ceva.unionbank.channel.ExternalServices;
import com.ceva.util.DBUtils;


public class MCCManagementCreationDao {


	private Logger logger = Logger.getLogger(MCCManagementCreationDao.class);

	ResponseDTO responseDTO = null;
	JSONObject requestJSON = null;
	JSONObject responseJSON = null;


	public ResponseDTO mccInfo(RequestDTO requestDTO) {

		logger.debug(" [ MCCManagementCreationDao ] [ mccInfo ] Inside mccInfo Dao.. ");

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

			logger.debug("[ MCCManagementCreationDao ] [ mccInfo ] connection is null [" + connection == null + "]");
			
			lmtfeeQry = "select CATE_CODE, CATE_DESCRIPTION,MAKER_ID from CATEGORY_MASTER";

			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("CATE_CODE", getlmtfeeRs.getString(1));
				json.put("CATE_DESCRIPTION", getlmtfeeRs.getString(2));
				json.put("MAKER_ID", getlmtfeeRs.getString(3));
							
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
			logger.debug("[ MCCManagementCreationDao ] [ branchInfo ] Got Exception  ["
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
	
	public ResponseDTO MCCManagementCreationAck(RequestDTO requestDTO) {


		Connection connection = null;
		HashMap<String, Object> branchMap = new HashMap<String, Object>();


		PreparedStatement servicePstmt = null;
		ResultSet serviceRS = null;

		String adminType=null;
		String adminName=null;
		
		

		try {

			responseDTO = new ResponseDTO();
			responseJSON = new JSONObject();
			requestJSON = requestDTO.getRequestJSON();
			
			connection = DBConnector.getConnection();

			String maker_id=requestJSON.getString(CevaCommonConstants.MAKER_ID);
			String remoteip=requestJSON.getString("remoteip");
			
			String branchQry="insert into CATEGORY_MASTER(CATE_CODE,CATE_DESCRIPTION,MAKER_ID,MAKER_DT) " +
					" values(?,?,?,sysdate) ";

			servicePstmt=connection.prepareStatement(branchQry);
		


			JSONArray branchArr =  requestDTO.getRequestJSON().getJSONArray("FINAL_JSON");
			

			for (int i = 0; i < branchArr.size(); i++) {

				JSONObject reqData = branchArr.getJSONObject(i);

				adminType = reqData.getString("adminType");
				adminName = reqData.getString("adminName");
				
				
				System.out.println("adminType :: "+adminType+" :: adminName"+adminName+" maker id ::: "+maker_id);

				servicePstmt.setString(1,adminType);
				servicePstmt.setString(2,adminName);
				servicePstmt.setString(3,maker_id);
				
				
				
				servicePstmt.addBatch();
				

			}

			servicePstmt.executeBatch(); // insert remaining records 
			servicePstmt.close();
			
			
			
			JSONObject json = new JSONObject();
			json.put(CevaCommonConstants.MAKER_ID, maker_id);
			json.put("transCode", "clusterCreation");
			json.put("channel", "WEB");
			json.put("message", "Acknowledgment :: MCC Configuration : MCC ID ::  "+adminType);
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
	
	public ResponseDTO mccpopupDetails(RequestDTO requestDTO) {

		logger.debug("Inside mccpopupDetails.. ");
		HashMap<String, Object> limitcodeDataMap = null;

		JSONObject resultJson = null;
		JSONObject json = null;
		JSONArray JsonArray= new JSONArray();
		PreparedStatement getlimitcodePstmt = null;
		ResultSet getlimitcodeRs = null;
		Connection connection = null;

		String  limitcode = "";
		String  limitcodeCountQry = "select CATE_CODE, CATE_DESCRIPTION,MAKER_ID from CATEGORY_MASTER where CATE_CODE=?";

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
			 
				
			}

			resultJson.put("limitcodedetails", json);	
			
			getlimitcodePstmt.close();
			getlimitcodeRs.close();
			/*
			 * String entityQry1 =
			 * "select user_name ,emp_id,NVL(location,' '),decode(USER_STATUS,'A','Active','L','De-Active','F','Active',USER_STATUS),NVL((SELECT GROUP_NAME from USER_GROUPS where GROUP_ID=user_groups),' '),"
			 * +
			 * "NVL((select STATUS from CONFIG_DATA where key_group='USER_DESIGNATION' and key_value=user_level),' ') "
			 * + "from user_information where CLUSTER_ID=? ";
			 * 
			 * 
			 * getlimitcodePstmt = connection.prepareStatement(entityQry1);
			 * getlimitcodePstmt.setString(1,limitcode); getlimitcodeRs =
			 * getlimitcodePstmt.executeQuery();
			 * 
			 * 
			 * while (getlimitcodeRs.next()) {
			 * 
			 * json.put("user_name", getlimitcodeRs.getString(1)); json.put("emp_id",
			 * getlimitcodeRs.getString(2)); json.put("location",
			 * getlimitcodeRs.getString(3)); json.put("USER_STATUS",
			 * getlimitcodeRs.getString(4)); json.put("GROUP_NAME",
			 * getlimitcodeRs.getString(5)); json.put("STATUS",
			 * getlimitcodeRs.getString(6));
			 * 
			 * JsonArray.add(json);
			 * 
			 * }
			 * 
			 * resultJson.put("limitcodedetails2", JsonArray);
			 */
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

	public ResponseDTO getMccdetails(RequestDTO requestDTO) {
		
	

			logger.debug("Inside mccpopupDetails.. ");
			HashMap<String, Object> limitcodeDataMap = null;

			JSONObject resultJson = null;
			JSONObject json = null;
			JSONArray JsonArray= new JSONArray();
			PreparedStatement getlimitcodePstmt = null;
			ResultSet getlimitcodeRs = null;
			Connection connection = null;

			String  limitcode = "";
			String  limitcodeCountQry = "select CATE_CODE, CATE_DESCRIPTION,MAKER_ID from CATEGORY_MASTER where CATE_CODE=?";

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
				 
					
				}

				resultJson.put("limitcodedetails", json);	
				
				getlimitcodePstmt.close();
				getlimitcodeRs.close();
				
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
	
	
	public ResponseDTO ModifyMccManagement(RequestDTO requestDTO) {
		logger.debug("insertBenifit");
		responseDTO = new ResponseDTO();
		requestJSON = requestDTO.getRequestJSON();
		responseJSON = new JSONObject();
		requestJSON = requestDTO.getRequestJSON();
		Connection connection = null;
		JSONObject resultJson = new JSONObject();
		HashMap<String, Object> benifitDataMap = new HashMap<String, Object>();
		PreparedStatement balpsmt = null;

		ResultSet resultset = null;
		String benefitQry = null;
		try {
			String benefitInsertQry = "UPDATE CATEGORY_MASTER  SET CATE_CODE =?,CATE_DESCRIPTION=? where CATE_CODE=?";
			connection = DBConnector.getConnection();
			balpsmt = connection.prepareStatement(benefitInsertQry);
			balpsmt.setString(1, requestJSON.getString("categoryCode"));
			balpsmt.setString(2, requestJSON.getString("categoryName"));
			balpsmt.setString(3, requestJSON.getString("categoryCode"));
			int insert=balpsmt.executeUpdate();
			benifitDataMap.put("INSERTED", insert);
			connection.commit();
			responseDTO.setData(benifitDataMap);
			logger.debug("[responseDTO:::" + responseDTO + "]");
		} catch (SQLException e) {
			logger.debug(e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(balpsmt);
			DBUtils.closeResultSet(resultset);
		}
		return responseDTO;
	}
	
	
	public ResponseDTO Productpaybilldata(RequestDTO requestDTO) {

		logger.debug(" [ MCCManagementCreationDao ] [ Productpaybilldata ] Inside Productpaybilldata Dao.. ");

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

			logger.debug("[ MCCManagementCreationDao ] [ Productpaybilldata ] connection is null [" + connection == null + "]");
			
			lmtfeeQry = "select BILLER_ID,BILLER_NAME,BILLER_CODE,(select count(*) from PAYBILL_LOAD where BILLER_CODE=PCT.BILLER_CODE) from PAYBILL_CATE_DT PCT";

			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("CATE_CODE", getlmtfeeRs.getString(1));
				json.put("CATE_DESCRIPTION", getlmtfeeRs.getString(2));
				json.put("BILLER_CODE", getlmtfeeRs.getString(3));
				json.put("LOAD_CNT", getlmtfeeRs.getString(4));
							
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
			logger.debug("[ MCCManagementCreationDao ] [ branchInfo ] Got Exception  ["
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
	
	 public ResponseDTO getProductpaybillcnf(RequestDTO requestDTO) {
			
			

			logger.debug("Inside mccpopupDetails.. ");
			HashMap<String, Object> limitcodeDataMap = null;

			JSONObject resultJson = null;
			JSONObject json = null;
			JSONArray JsonArray= new JSONArray();
			PreparedStatement getlimitcodePstmt = null;
			ResultSet getlimitcodeRs = null;
			Connection connection = null;

			String  limitcode = "";
			String  limitcodeCountQry = "select BILLER_ID, BILLER_CODE,BILLER_NAME from PAYBILL_CATE_DT where BILLER_ID=?";

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
				     json.put("billername", getlimitcodeRs.getString(3));
				 
					
				}

				resultJson.put("limitcodedetails", json);	
				
				getlimitcodePstmt.close();
				getlimitcodeRs.close();
				
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
	 
	 
		public ResponseDTO insercatDetailsAct(RequestDTO requestDTO) {
			logger.debug("insertBenifit");
			responseDTO = new ResponseDTO();
			requestJSON = requestDTO.getRequestJSON();
			responseJSON = new JSONObject();
			requestJSON = requestDTO.getRequestJSON();
			Connection connection = null;
			JSONObject resultJson = new JSONObject();
			HashMap<String, Object> benifitDataMap = new HashMap<String, Object>();
			PreparedStatement balpsmt = null;		
			ResultSet resultset = null;
			
			PreparedStatement Pstmt = null;
			ResultSet Rs = null;
			String benefitQry = null;
			try {
				
				String billerid=requestJSON.getString("billerid");
				String billername=requestJSON.getString("billername");
				String billercode=requestJSON.getString("billercode");
				String makerid=requestJSON.getString(CevaCommonConstants.MAKER_ID);
				connection = DBConnector.getConnection();
				
				JSONObject json1 = JSONObject.fromObject(ExternalServices.billersAndProductsFieldsByBillerCode(makerid,billercode));
				System.out.println("kailash biller json1 :: "+json1);
				
				Pstmt = connection.prepareStatement("select count(*) from PAYBILL_LOAD where BILLER_CODE=? ");
				Pstmt.setString(1,billercode);

				Rs = Pstmt.executeQuery();
				
				if (Rs.next()) {	
					
					if((Rs.getString(1)).equalsIgnoreCase("0")) {
						String benefitInsertQry = "INSERT INTO PAYBILL_LOAD(BILLER_ID,BILLER_NAME,BILLER_CODE,PAYLOAD,PL_DATE,MAKER_ID) values(?,?,?,?,SYSDATE,?)";
						
						balpsmt = connection.prepareStatement(benefitInsertQry);
						balpsmt.setString(1, billerid);
						balpsmt.setString(2, billername);
						balpsmt.setString(3, billercode);
						balpsmt.setString(4, json1.toString());
						balpsmt.setString(5, makerid);
						
						balpsmt.executeUpdate();
						connection.commit();
					}else {
						
						String benefitInsertQry = "UPDATE PAYBILL_LOAD SET PAYLOAD=? where BILLER_CODE=? ";
						
						balpsmt = connection.prepareStatement(benefitInsertQry);
						
						/*balpsmt.setString(1, billercode);
						balpsmt.setString(2, json1.toString());*/
						
						balpsmt.setString(1, json1.toString());
						balpsmt.setString(2, billercode);
						
						
						balpsmt.executeUpdate();
						connection.commit();
						
					}
				}
				
				
				
				
				responseDTO.setData(benifitDataMap);
				logger.debug("[responseDTO:::" + responseDTO + "]");
				
			} catch (SQLException e) {
				logger.debug(e.getLocalizedMessage());
				e.printStackTrace();
			} finally {
				DBUtils.closeConnection(connection);
				DBUtils.closePreparedStatement(balpsmt);
				DBUtils.closeResultSet(resultset);
				DBUtils.closePreparedStatement(Pstmt);
				DBUtils.closeResultSet(Rs);
			}
			return responseDTO;
		} 
	

}
