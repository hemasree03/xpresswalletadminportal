package com.ceva.base.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.cave.base.dao.BranchCreationDao;
import com.ceva.base.common.dto.RequestDTO;
import com.ceva.base.common.dto.ResponseDTO;
import com.ceva.base.common.utils.CevaCommonConstants;
import com.ceva.base.common.utils.DBConnector;
import com.ceva.unionbank.channel.ExternalServices;
import com.ceva.unionbank.channel.ServiceRequestClient;
import com.ceva.util.DBUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class BManagementDao {
	
	private Logger logger = Logger.getLogger(BManagementDao.class);

	ResponseDTO responseDTO = null;
	JSONObject requestJSON = null;
	JSONObject responseJSON = null;
	
	public ResponseDTO catagerioInfo(RequestDTO requestDTO) {

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
			
			lmtfeeQry = "select CAT_ID,CAT_NAME,MAKER_ID,STATUS	 from PAYBILL_CATE_TBL";

			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("CAT_ID", getlmtfeeRs.getString(1));
				json.put("CAT_NAME", getlmtfeeRs.getString(2));
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
	
	public ResponseDTO getUserGroupDetails(RequestDTO requestDTO) {
		logger.debug("Inside GetUserGroupDetails... ");

		PreparedStatement merchantPstmt = null;
		ResultSet merchantRS = null;
		Connection connection = null;

		HashMap<String, Object> merchantMap = null;
		JSONObject resultJson = null;
		JSONArray userRightsJSONArray = null;
		JSONObject json = null;
		JSONObject rightsJson2 = null;

	
		try {

			

			responseDTO = new ResponseDTO();

			merchantMap = new HashMap<String, Object>();
			resultJson = new JSONObject();
			userRightsJSONArray = new JSONArray();

			connection = DBConnector.getConnection();
			logger.debug("Connection [" + connection + "]");

			merchantPstmt = connection.prepareStatement("select BILLER_ID,BILLER_NAME from PAYBILL_CATE_DT");
			merchantRS = merchantPstmt.executeQuery();
			
			rightsJson2 = new JSONObject();

			while (merchantRS.next()) {
				rightsJson2.put(merchantRS.getString(1), merchantRS.getString(1));
			}
			
			//JSONObject json1 = JSONObject.fromObject(ServiceRequestClient.getBillers());
			JSONObject json1 = JSONObject.fromObject(ExternalServices.billers());
			JSONArray jsonarray =  JSONArray.fromObject(json1.get("bankresp"));
			//JSONArray jsonarray =  JSONArray.fromObject(json1.get("billersdata"));
			/*JSONObject json2 = JSONObject.fromObject(ServiceRequestClient.getProduct());
			JSONArray jsonarray1 =  JSONArray.fromObject(json2.get("productdata"));*/
			
			
			Iterator iterator = jsonarray.iterator();
		       while (iterator.hasNext()) {
		    	   json = new JSONObject();
		    	   JSONObject jsonobj=(JSONObject)iterator.next();
			      //System.out.println(((jsonobj).get("billerName")).toString()); 
		    	  // System.out.println(jsonobj);
		    	   if(!rightsJson2.containsKey(((jsonobj).get("id")).toString())) {
			      json.put("id", ((jsonobj).get("id")).toString());
					json.put("pId", ((jsonobj).get("id")).toString());
					json.put("name", ((jsonobj).get("id")).toString()+"-"+((jsonobj).get("billerName")).toString());	
					json.put("checked", "false");
					json.put("open", "open");
					json.put("title", ((jsonobj).get("billerName")).toString());
					 userRightsJSONArray.add(json);
					
		    	   }
					
			     
		       }

			resultJson.put("user_rights", userRightsJSONArray);
			merchantMap.put("user_rights", resultJson);

			logger.debug("MerchantMap [" + merchantMap + "]");
			responseDTO.setData(merchantMap);

		} catch (SQLException e) {
			logger.debug("SQLException in GetUserGroupDetails ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {

			logger.debug("Exception in GetUserGroupDetails  [" + e.getMessage()
					+ "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(merchantPstmt);
			DBUtils.closeResultSet(merchantRS);
			json = null;
			merchantMap = null;
			resultJson = null;
			userRightsJSONArray = null;
			json = null;
		}

		return responseDTO;
	}
	
	
	public ResponseDTO fetchuserlevel(RequestDTO requestDTO){
		responseDTO=new ResponseDTO();
		requestJSON=new JSONObject();
	
		Connection connection=null;
		logger.debug("inside [BarCharDAO][fetchuserlevel]");
		HashMap<String,Object> regionDataMap=new HashMap<String,Object>();
		JSONObject resultJson=new JSONObject();
		JSONArray regionJSONArray=new JSONArray();
		PreparedStatement regionPstmt=null;
		ResultSet regionRS=null;
		PreparedStatement regionPstmt1=null;
		ResultSet regionRS1=null;
		String regionQry= null;
		String rQry= null;
		String usertype= null;
		
		try {
			connection=DBConnector.getConnection();
			logger.debug("inside [BarCharDAO][fetchuserlevel][connection:::"+connection+"]");
			
			requestJSON=requestDTO.getRequestJSON();
			
			
				regionQry="SELECT CAT_NAME||'-'||CAT_ID,CAT_NAME FROM PAYBILL_CATE_MASTER where CAT_ID not in (select CAT_ID from PAYBILL_CATE_TBL)";
				
				regionPstmt=connection.prepareStatement(regionQry);


			
			
		

			regionRS=regionPstmt.executeQuery();
			JSONObject json=null;
			while(regionRS.next()){
				json=new JSONObject();
				json.put(CevaCommonConstants.SELECT_KEY, regionRS.getString(2));
				json.put(CevaCommonConstants.SELECT_VAL, regionRS.getString(1));
				regionJSONArray.add(json);
			}
			logger.debug("inside [BarCharDAO][fetchuserlevel][userlevelJSONArray:::"+regionJSONArray+"]");

			resultJson.put("USER_LEVEL_LIST", regionJSONArray);
			regionDataMap.put("USER_LEVEL_LIST", resultJson);

			logger.debug("inside [BarCharDAO][fetchuserlevel][resultJson:::"+resultJson+"]");
			responseDTO.setData(regionDataMap);
			logger.debug("inside [BarCharDAO][fetchuserlevel][responseDTO:::"+responseDTO+"]");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 
		}finally{
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(regionPstmt);
			DBUtils.closeResultSet(regionRS);
		}

		return responseDTO;
		
	}
	
	public ResponseDTO insertUserGroupDetails(RequestDTO requestDTO) {

		logger.debug("Inside InsertUserGroupDetails.... ");

		// JSONObject object = null;
		String groupId = null;
		String groupDesc = null;
		String jsonVal = null;
		String userID = null;
		String entity = null;
		String applCode = null;
		String groupType = null;
		String ip = null;
		String makerid = null;
		String auditQry = "";

		String userlevel = null;

		org.json.JSONArray jArray = null;
		org.json.JSONObject jobj = null;

		String id = "";
		String pid = "";
		String title = "";
		String merchantQry = "";
		
		PreparedStatement prepareStmt2 = null;
		ResultSet resultSet1 = null;

		PreparedStatement prepareStmt1 = null;
		ResultSet getId = null;
		Connection connection = null;
		PreparedStatement merchantPstmt =null;

		try {
			responseDTO = new ResponseDTO();
			requestJSON = requestDTO.getRequestJSON();

			jsonVal = requestJSON.getString("jsonVal");
			
			ip=requestJSON.getString(CevaCommonConstants.IP);
			makerid=requestJSON.getString(CevaCommonConstants.MAKER_ID);
			//groupId=groupId.toUpperCase();
			System.out.println(jsonVal);
			try {

				jobj = new org.json.JSONObject(jsonVal);
				logger.debug(" jobj [" + jobj.toString() + "]");
				jArray = new org.json.JSONArray(jobj.getString("user_details"));
				org.json.JSONObject json_data = null;
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					//System.out.println("kailash ::: "+json_data);										
				}
				
				
				
			} catch (JSONException e) {
				logger.debug("Exception in parsing Json String.."
						+ e.getMessage());

			}
			connection = DBConnector.getConnection();

			logger.debug("Connection is null [" + connection == null + "]");

			connection.setAutoCommit(false);
			
			
			
			String ref_num="";
			String branch_code="";
			prepareStmt2 = connection.prepareStatement("select to_char(systimestamp,'DDISSSSSFF'),NVL(CLUSTER_ID,' ')  from USER_INFORMATION where COMMON_ID=(select common_id from USER_LOGIN_CREDENTIALS where LOGIN_USER_ID='"+requestJSON.getString("makerId")+"')");

			resultSet1 = prepareStmt2.executeQuery();
			while (resultSet1.next()) {
				ref_num=resultSet1.getString(1);
				branch_code=resultSet1.getString(2);
			}
			
			merchantPstmt = connection.prepareStatement("insert into auth_pending ( ref_num,maker_id,maker_dttm,auth_code,status,auth_flag,MAKER_BRCODE,ACTION,ACTION_DETAILS) values('"+ref_num+"','"+makerid+"',sysdate,'CATCREAUTH','P','BM','"+branch_code+"','MAKER','Category Creation  "+requestJSON.getString("groupType")+" ')");
			merchantPstmt.executeUpdate();
			merchantPstmt.close();

			System.out.println(requestJSON.getString("groupType"));
			System.out.println(requestJSON.getString("catids"));
			String catid=requestJSON.getString("catids");
			String catname=requestJSON.getString("groupType");
			
			merchantPstmt = connection.prepareStatement("insert into PAYBILL_CATE_TBL_TEMP ( CAT_ID,CAT_NAME,STATUS,MAKER_ID,MAKER_DT,JSON_VAL,REF_NUM) "
					+ "values('"+catid+"','"+catname+"','A','"+makerid+"',sysdate,'AUP','"+ref_num+"')");
			merchantPstmt.executeUpdate();
			merchantPstmt.close();
			
			
			merchantQry = "insert into PAYBILL_CATE_DT_TEMP(CAT_ID ,BILLER_ID ,BILLER_NAME ,AUTH_FLAG ,REF_NUM ) "
					+ "values (?,?,?,?,?)";
			merchantPstmt = connection.prepareStatement(merchantQry);
			
			org.json.JSONObject json_data = null;
			
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				if (json_data.getString("checked").equalsIgnoreCase("true")) {
					
					System.out.println(json_data.getString("name"));
					
					

					merchantPstmt.setString(1, catid);
					merchantPstmt.setString(2, (json_data.getString("name")).split("-")[0]);
					merchantPstmt.setString(3, (json_data.getString("name")).split("-")[1]);
					merchantPstmt.setString(4, "AUP");
					merchantPstmt.setString(5, ref_num);
					merchantPstmt.addBatch();
					
					
				}
			}
			
			int size[] = merchantPstmt.executeBatch();

				
			connection.commit();

			//logger.debug("Rows Inserted are [" + size.length + "]");

			responseDTO = getUserGroupDetails(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			logger.debug("SQLException in InsertUserGroupDetails ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {
			logger.debug("Exception in InsertUserGroupDetails  ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(prepareStmt1);
			DBUtils.closeResultSet(getId);

			groupId = null;
			groupDesc = null;
			jsonVal = null;
			userID = null;
			entity = null;
			applCode = null;

			jArray = null;
			jobj = null;
			merchantQry = null;
			id = null;
			pid = null;
			title = null;
		}

		return responseDTO;
	}
	
	
	public ResponseDTO subCategorySave(RequestDTO requestDTO) {

		logger.debug("Inside InsertUserGroupDetails.... ");

		// JSONObject object = null;
		String groupId = null;
		String groupDesc = null;
		String jsonVal = null;
		String userID = null;
		String entity = null;
		String applCode = null;
		String groupType = null;
		String ip = null;
		String makerid = null;
		String auditQry = "";

		String userlevel = null;

		org.json.JSONArray jArray = null;
		org.json.JSONObject jobj = null;
		
		org.json.JSONArray jArrayPos = null;
		org.json.JSONObject jobjPos = null;
		
		org.json.JSONArray jArrayUssd = null;
		org.json.JSONObject jobjUssd = null;

		String id = "";
		String pid = "";
		String title = "";
		String merchantQry = "";
		
		PreparedStatement prepareStmt2 = null;
		ResultSet resultSet1 = null;

		PreparedStatement prepareStmt1 = null;
		ResultSet getId = null;
		Connection connection = null;
		PreparedStatement merchantPstmt =null;
		
		String jsonPosVal = null;
		String jsonUssdVal = null;
		String subid = null;

		try {
			responseDTO = new ResponseDTO();
			requestJSON = requestDTO.getRequestJSON();

			jsonVal = requestJSON.getString("jsonVal");
			jsonPosVal = requestJSON.getString("jsonPosVal");
			jsonUssdVal = requestJSON.getString("jsonUssdVal");
			
			ip=requestJSON.getString(CevaCommonConstants.IP);
			makerid=requestJSON.getString(CevaCommonConstants.MAKER_ID);
			//groupId=groupId.toUpperCase();
			System.out.println(jsonVal);
			try {

				jobj = new org.json.JSONObject(jsonVal);
				logger.debug(" jobj [" + jobj.toString() + "]");
				jArray = new org.json.JSONArray(jobj.getString("user_details"));
				org.json.JSONObject json_data = null;
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					//System.out.println("kailash ::: "+json_data);										
				}
				
				jobjPos = new org.json.JSONObject(jsonPosVal);
				logger.debug(" jobjPos [" + jobjPos.toString() + "]");
				jArrayPos = new org.json.JSONArray(jobjPos.getString("user_Pos_details"));
				org.json.JSONObject json_data_pos = null;
				for (int j = 0; j < jArrayPos.length(); j++) {
					json_data_pos = jArrayPos.getJSONObject(j);
					//System.out.println("kailash ::: "+json_data);										
				}
				
				
				jobjUssd = new org.json.JSONObject(jsonUssdVal);
				logger.debug(" jobjUssd [" + jobjUssd.toString() + "]");
				jArrayUssd = new org.json.JSONArray(jobjUssd.getString("user_Ussd_details"));
				org.json.JSONObject json_data_ussd = null;
				for (int k = 0; k < jArrayUssd.length(); k++) {
					json_data_ussd = jArrayUssd.getJSONObject(k);
					//System.out.println("kailash ::: "+json_data);										
				}
				
				
				
				
			} catch (JSONException e) {
				logger.debug("Exception in parsing Json String.."
						+ e.getMessage());

			}
			connection = DBConnector.getConnection();

			logger.debug("Connection is null [" + connection == null + "]");

			connection.setAutoCommit(false);
			
			
			
			String ref_num="";
			String branch_code="";
			prepareStmt2 = connection.prepareStatement("select to_char(systimestamp,'DDISSSSSFF'),NVL(CLUSTER_ID,' ')  from USER_INFORMATION where COMMON_ID=(select common_id from USER_LOGIN_CREDENTIALS where LOGIN_USER_ID='"+requestJSON.getString("makerId")+"')");

			resultSet1 = prepareStmt2.executeQuery();
			while (resultSet1.next()) {
				ref_num=resultSet1.getString(1);
				branch_code=resultSet1.getString(2);
			}
			
			prepareStmt2.close();
			resultSet1.close();
			
			prepareStmt2 = connection.prepareStatement("select SUBCAT_SEQ.nextval from dual");

			resultSet1 = prepareStmt2.executeQuery();
			if (resultSet1.next()) {
				subid=resultSet1.getString(1);
			}
			
			merchantPstmt = connection.prepareStatement("insert into auth_pending ( ref_num,maker_id,maker_dttm,auth_code,status,auth_flag,MAKER_BRCODE,ACTION,ACTION_DETAILS) values('"+ref_num+"','"+makerid+"',sysdate,'SUBCATCREAUTH','P','BM','"+branch_code+"','MAKER','Category Creation  "+requestJSON.getString("groupType")+" ')");
			merchantPstmt.executeUpdate();
			merchantPstmt.close();

			System.out.println(requestJSON.getString("groupType"));
			System.out.println(requestJSON.getString("catids"));
			System.out.println(requestJSON.getString("subcategory"));
			String catid=requestJSON.getString("catids");
			String catname=requestJSON.getString("groupType");
			String subcategory=requestJSON.getString("subcategory");
			
			merchantPstmt = connection.prepareStatement("insert into PAYBILL_SUBCATE_TBL_TEMP ( CAT_ID,SUBCAT_ID,SUBCAT_NAME,MAKER_ID,MAKER_DT,AUTH_FLAG,REF_NUM) "
					+ "values('"+catid+"','"+subid+"','"+subcategory+"','"+makerid+"',sysdate,'AUP','"+ref_num+"')");
			merchantPstmt.executeUpdate();
			merchantPstmt.close();
			
			
			merchantQry = "insert into PAYBILL_SUBCATE_DT_TEMP(CAT_ID ,SUBCAT_ID,BILLER_ID ,BILLER_NAME ,CHANNEL,AUTH_FLAG ,REF_NUM ) "
					+ "values (?,?,?,?,?,?,?)";
			merchantPstmt = connection.prepareStatement(merchantQry);
			
			org.json.JSONObject json_data = null;
			
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				if (json_data.getString("checked").equalsIgnoreCase("true")) {
					
					System.out.println(json_data.getString("name"));
					
					

					merchantPstmt.setString(1, catid);
					merchantPstmt.setString(2, subid);
					merchantPstmt.setString(3, (json_data.getString("name")).split("-")[0]);
					merchantPstmt.setString(4, (json_data.getString("name")).split("-")[1]);
					merchantPstmt.setString(5, "MOBILE");
					merchantPstmt.setString(6, "AUP");
					merchantPstmt.setString(7, ref_num);
					merchantPstmt.addBatch();
					
					
				}
			}
			
			int size[] = merchantPstmt.executeBatch();
			
			merchantPstmt.close();
			merchantQry = "insert into PAYBILL_SUBCATE_DT_TEMP(CAT_ID ,SUBCAT_ID,BILLER_ID ,BILLER_NAME ,CHANNEL,AUTH_FLAG ,REF_NUM ) "
					+ "values (?,?,?,?,?,?,?)";
			merchantPstmt = connection.prepareStatement(merchantQry);
			
			org.json.JSONObject json_datapos = null;
			
			for (int k = 0; k < jArrayPos.length(); k++) {
				json_datapos = jArrayPos.getJSONObject(k);
				if (json_datapos.getString("checked").equalsIgnoreCase("true")) {
					
					System.out.println(json_datapos.getString("name"));
					
					

					merchantPstmt.setString(1, catid);
					merchantPstmt.setString(2, subid);
					merchantPstmt.setString(3, (json_datapos.getString("name")).split("-")[0]);
					merchantPstmt.setString(4, (json_datapos.getString("name")).split("-")[1]);
					merchantPstmt.setString(5, "POS");
					merchantPstmt.setString(6, "AUP");
					merchantPstmt.setString(7, ref_num);
					merchantPstmt.addBatch();
					
					
				}
			}
			
			int size1[] = merchantPstmt.executeBatch();
			
			merchantPstmt.close();
			merchantQry = "insert into PAYBILL_SUBCATE_DT_TEMP(CAT_ID ,SUBCAT_ID,BILLER_ID ,BILLER_NAME ,CHANNEL,AUTH_FLAG ,REF_NUM ) "
					+ "values (?,?,?,?,?,?,?)";
			merchantPstmt = connection.prepareStatement(merchantQry);
			
			org.json.JSONObject json_dataussd = null;
			
			for (int j = 0; j < jArrayUssd.length(); j++) {
				json_dataussd = jArrayUssd.getJSONObject(j);
				if (json_dataussd.getString("checked").equalsIgnoreCase("true")) {
					
					System.out.println(json_dataussd.getString("name"));
					
					

					merchantPstmt.setString(1, catid);
					merchantPstmt.setString(2, subid);
					merchantPstmt.setString(3, (json_dataussd.getString("name")).split("-")[0]);
					merchantPstmt.setString(4, (json_dataussd.getString("name")).split("-")[1]);
					merchantPstmt.setString(5, "USSD");
					merchantPstmt.setString(6, "AUP");
					merchantPstmt.setString(7, ref_num);
					merchantPstmt.addBatch();
					
					
				}
			}
			
			int size2[] = merchantPstmt.executeBatch();

				
			connection.commit();

			//logger.debug("Rows Inserted are [" + size.length + "]");

			responseDTO = getUserGroupDetails(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			logger.debug("SQLException in InsertUserGroupDetails ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {
			logger.debug("Exception in InsertUserGroupDetails  ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(prepareStmt1);
			DBUtils.closeResultSet(getId);

			groupId = null;
			groupDesc = null;
			jsonVal = null;
			userID = null;
			entity = null;
			applCode = null;

			jArray = null;
			jobj = null;
			merchantQry = null;
			id = null;
			pid = null;
			title = null;
		}

		return responseDTO;
	}
	
	public ResponseDTO viewCategory(RequestDTO requestDTO) {

		logger.debug("Inside  viewCategory... ");

		HashMap<String, Object> merchantMap = null;
		JSONObject resultJson = null;
		JSONArray jsonArray = null;
		JSONObject rightsJson = null;
		JSONObject rightsJson1 = null;

		String merchantQry = "";
		String type = "";
		String entity = "";
		String catids = "";

		PreparedStatement prepareStmt1 = null;
		ResultSet merchantRS = null;
		Connection connection = null;

		try {
			responseDTO = new ResponseDTO();
			logger.debug("Request JSON [" + requestDTO.getRequestJSON() + "]");

			
			catids = requestDTO.getRequestJSON().getString("catids");
			merchantMap = new HashMap<String, Object>();
			resultJson = new JSONObject();

			connection = DBConnector.getConnection();
			logger.debug("Connection is null [" + connection == null + "]");

			
			
			jsonArray = new JSONArray();
			rightsJson = new JSONObject();
			rightsJson1 = new JSONObject();
			
			prepareStmt1 = connection
					.prepareStatement("select BILLER_ID,BILLER_NAME from PAYBILL_CATE_DT where CAT_ID=?");
			prepareStmt1.setString(1, catids);
			merchantRS = prepareStmt1.executeQuery();
			while (merchantRS.next()) {
				rightsJson.put("id", merchantRS.getString(1));
				rightsJson.put("pId", merchantRS.getString(1));
				rightsJson.put("name", merchantRS.getString(1)+"-"+merchantRS.getString(2));
				rightsJson.put("checked", "true");
				rightsJson.put("open", "open");
				rightsJson.put("title", merchantRS.getString(2));
				rightsJson.put("chkDisabled", "true");
				jsonArray.add(rightsJson);
				rightsJson.clear();
			}

			rightsJson1.put("user_details", jsonArray);
			resultJson.put("json_val", rightsJson1);

			merchantMap.put("user_rights", resultJson);
			logger.debug("EntityMap [" + merchantMap + "]");
			responseDTO.setData(merchantMap);
			logger.debug("inside Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			logger.debug("SQLException in viewCategory [" + e.getMessage()
					+ "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {
			logger.debug("Exception in viewCategory  [" + e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(prepareStmt1);
			DBUtils.closeResultSet(merchantRS);

			merchantMap = null;
			resultJson = null;
			jsonArray = null;
			rightsJson = null;
			rightsJson1 = null;

			merchantQry = null;
			type = null;
			entity = null;

		}

		return responseDTO;
	}
	
	
	public ResponseDTO viewSubCategory(RequestDTO requestDTO) {

		logger.debug("Inside  viewCategory... ");

		HashMap<String, Object> merchantMap = null;
		JSONObject resultJson = null;
		JSONArray jsonArray = null;
		JSONObject rightsJson = null;
		JSONObject rightsJson1 = null;

		String merchantQry = "";
		String type = "";
		String entity = "";
		String catids = "";

		PreparedStatement prepareStmt1 = null;
		ResultSet merchantRS = null;
		Connection connection = null;

		try {
			responseDTO = new ResponseDTO();
			logger.debug("Request JSON [" + requestDTO.getRequestJSON() + "]");

			
			catids = requestDTO.getRequestJSON().getString("catids");
			merchantMap = new HashMap<String, Object>();
			resultJson = new JSONObject();

			connection = DBConnector.getConnection();
			logger.debug("Connection is null [" + connection == null + "]");

			
			
			jsonArray = new JSONArray();
			rightsJson = new JSONObject();
			rightsJson1 = new JSONObject();
			
			prepareStmt1 = connection
					.prepareStatement("select BILLER_ID,BILLER_NAME from PAYBILL_CATE_DT where CAT_ID=?");
			prepareStmt1.setString(1, catids);
			merchantRS = prepareStmt1.executeQuery();
			while (merchantRS.next()) {
				rightsJson.put("id", merchantRS.getString(1));
				rightsJson.put("pId", merchantRS.getString(1));
				rightsJson.put("name", merchantRS.getString(1)+"-"+merchantRS.getString(2));
				rightsJson.put("checked", "true");
				rightsJson.put("open", "open");
				rightsJson.put("title", merchantRS.getString(2));
				rightsJson.put("chkDisabled", "false");
				jsonArray.add(rightsJson);
				rightsJson.clear();
			}

			rightsJson1.put("user_details", jsonArray);
			resultJson.put("json_val", rightsJson1);

			merchantMap.put("user_rights", resultJson);
			logger.debug("EntityMap [" + merchantMap + "]");
			responseDTO.setData(merchantMap);
			logger.debug("inside Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			logger.debug("SQLException in viewCategory [" + e.getMessage()
					+ "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {
			logger.debug("Exception in viewCategory  [" + e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(prepareStmt1);
			DBUtils.closeResultSet(merchantRS);

			merchantMap = null;
			resultJson = null;
			jsonArray = null;
			rightsJson = null;
			rightsJson1 = null;

			merchantQry = null;
			type = null;
			entity = null;

		}

		return responseDTO;
	}
	
	
	public ResponseDTO modifyCategory(RequestDTO requestDTO) {

		logger.debug("Inside  viewCategory... ");

		HashMap<String, Object> merchantMap = null;
		JSONObject resultJson = null;
		JSONArray jsonArray = null;
		JSONObject rightsJson = null;
		JSONObject rightsJson1 = null;
		JSONObject rightsJson2 = null;

		String merchantQry = "";
		String type = "";
		String entity = "";
		String catids = "";

		PreparedStatement prepareStmt1 = null;
		ResultSet merchantRS = null;
		Connection connection = null;

		try {
			responseDTO = new ResponseDTO();
			logger.debug("Request JSON [" + requestDTO.getRequestJSON() + "]");

			
			
			catids = requestDTO.getRequestJSON().getString("catids");
			merchantMap = new HashMap<String, Object>();
			resultJson = new JSONObject();

			connection = DBConnector.getConnection();
			logger.debug("Connection is null [" + connection == null + "]");

			
			
			jsonArray = new JSONArray();
			rightsJson = new JSONObject();
			rightsJson1 = new JSONObject();
			rightsJson2 = new JSONObject();
			
			prepareStmt1 = connection
					.prepareStatement("select BILLER_ID,BILLER_NAME from PAYBILL_CATE_DT where CAT_ID=?");
			prepareStmt1.setString(1, catids);
			merchantRS = prepareStmt1.executeQuery();
			while (merchantRS.next()) {
				rightsJson2.put(merchantRS.getString(1), merchantRS.getString(1));
				
			}
			
			/*JSONObject json1 = JSONObject.fromObject(ServiceRequestClient.getBillers());
			JSONArray jsonarray =  JSONArray.fromObject(json1.get("billersdata"));*/
			
			JSONObject json1 = JSONObject.fromObject(ExternalServices.billers());
			JSONArray jsonarray =  JSONArray.fromObject(json1.get("bankresp"));
			
			Iterator iterator = jsonarray.iterator();
		       while (iterator.hasNext()) {
		    	   JSONObject jsonobj=(JSONObject)iterator.next();
		    	   rightsJson.put("id", ((jsonobj).get("id")).toString());
					rightsJson.put("pId", ((jsonobj).get("id")).toString());
					rightsJson.put("name", ((jsonobj).get("id")).toString()+"-"+((jsonobj).get("billerName")).toString());
					if(rightsJson2.containsKey(((jsonobj).get("id")).toString())) {
						rightsJson.put("checked", "true");
					}else {
						rightsJson.put("checked", "false");
					}
					
					rightsJson.put("open", "open");
					rightsJson.put("title", ((jsonobj).get("billerName")).toString());
					rightsJson.put("chkDisabled", "false");
					jsonArray.add(rightsJson);
					rightsJson.clear();
		       }

			rightsJson1.put("user_details", jsonArray);
			resultJson.put("json_val", rightsJson1);

			merchantMap.put("user_rights", resultJson);
			logger.debug("EntityMap [" + merchantMap + "]");
			responseDTO.setData(merchantMap);
			logger.debug("inside Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			logger.debug("SQLException in viewCategory [" + e.getMessage()
					+ "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {
			logger.debug("Exception in viewCategory  [" + e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(prepareStmt1);
			DBUtils.closeResultSet(merchantRS);

			merchantMap = null;
			resultJson = null;
			jsonArray = null;
			rightsJson = null;
			rightsJson1 = null;

			merchantQry = null;
			type = null;
			entity = null;

		}

		return responseDTO;
	}
	
	public ResponseDTO modifysaveGroupDetails(RequestDTO requestDTO) {

		logger.debug("Inside modifysaveGroupDetails.... ");

		// JSONObject object = null;
		String groupId = null;
		String groupDesc = null;
		String jsonVal = null;
		String userID = null;
		String entity = null;
		String applCode = null;
		String groupType = null;
		String ip = null;
		String makerid = null;
		String auditQry = "";

		String userlevel = null;

		org.json.JSONArray jArray = null;
		org.json.JSONObject jobj = null;

		String id = "";
		String pid = "";
		String title = "";
		String merchantQry = "";
		
		PreparedStatement prepareStmt2 = null;
		ResultSet resultSet1 = null;

		PreparedStatement prepareStmt1 = null;
		ResultSet getId = null;
		Connection connection = null;
		PreparedStatement merchantPstmt =null;

		try {
			responseDTO = new ResponseDTO();
			requestJSON = requestDTO.getRequestJSON();

			jsonVal = requestJSON.getString("jsonVal");
			
			ip=requestJSON.getString(CevaCommonConstants.IP);
			makerid=requestJSON.getString(CevaCommonConstants.MAKER_ID);
			//groupId=groupId.toUpperCase();
			System.out.println(jsonVal);
			try {

				jobj = new org.json.JSONObject(jsonVal);
				logger.debug(" jobj [" + jobj.toString() + "]");
				jArray = new org.json.JSONArray(jobj.getString("user_details"));
				org.json.JSONObject json_data = null;
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					//System.out.println("kailash ::: "+json_data);										
				}
				
				
				
			} catch (JSONException e) {
				logger.debug("Exception in parsing Json String.."
						+ e.getMessage());

			}
			connection = DBConnector.getConnection();

			logger.debug("Connection is null [" + connection == null + "]");

			connection.setAutoCommit(false);
			
			
			
			String ref_num="";
			String branch_code="";
			prepareStmt2 = connection.prepareStatement("select to_char(systimestamp,'DDISSSSSFF'),NVL(CLUSTER_ID,' ')  from USER_INFORMATION where COMMON_ID=(select common_id from USER_LOGIN_CREDENTIALS where LOGIN_USER_ID='"+requestJSON.getString("makerId")+"')");

			resultSet1 = prepareStmt2.executeQuery();
			while (resultSet1.next()) {
				ref_num=resultSet1.getString(1);
				branch_code=resultSet1.getString(2);
			}
			
			

			System.out.println(requestJSON.getString("groupType"));
			System.out.println(requestJSON.getString("catids"));
			String catid=requestJSON.getString("catids");
			String catname=requestJSON.getString("groupType");
			
			
			if((requestJSON.getString("type")).equalsIgnoreCase("Modify")) {
				
				merchantPstmt = connection.prepareStatement("insert into auth_pending ( ref_num,maker_id,maker_dttm,auth_code,status,auth_flag,MAKER_BRCODE,ACTION,ACTION_DETAILS) values('"+ref_num+"','"+makerid+"',sysdate,'CATMODIFYAUTH','P','BM','"+branch_code+"','MAKER','Caregory Modify "+requestJSON.getString("groupType")+" ')");
				merchantPstmt.executeUpdate();
				merchantPstmt.close();
			
			
			
			
			merchantPstmt = connection.prepareStatement("insert into PAYBILL_CATE_TBL_TEMP ( CAT_ID,CAT_NAME,STATUS,MAKER_ID,MAKER_DT,JSON_VAL,REF_NUM) "
					+ "values('"+catid+"','"+catname+"','A','"+makerid+"',sysdate,'AUP','"+ref_num+"')");
			merchantPstmt.executeUpdate();
			merchantPstmt.close();
			
			
			merchantQry = "insert into PAYBILL_CATE_DT_TEMP(CAT_ID ,BILLER_ID ,BILLER_NAME ,AUTH_FLAG ,REF_NUM ) "
					+ "values (?,?,?,?,?)";
			merchantPstmt = connection.prepareStatement(merchantQry);
			
			org.json.JSONObject json_data = null;
			
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				if (json_data.getString("checked").equalsIgnoreCase("true")) {
					
					System.out.println(json_data.getString("name"));
					
					

					merchantPstmt.setString(1, catid);
					merchantPstmt.setString(2, (json_data.getString("name")).split("-")[0]);
					merchantPstmt.setString(3, (json_data.getString("name")).split("-")[1]);
					merchantPstmt.setString(4, "AUP");
					merchantPstmt.setString(5, ref_num);
					merchantPstmt.addBatch();
					
					
				}
			}
			
			int size[] = merchantPstmt.executeBatch();
			}else if((requestJSON.getString("type")).equalsIgnoreCase("Status")) {
				String status=requestJSON.getString("status");
				if(status.equalsIgnoreCase("Active")) {
					status="L";
				}else {
					status="A";
				}
				
				merchantPstmt = connection.prepareStatement("insert into auth_pending ( ref_num,maker_id,maker_dttm,auth_code,status,auth_flag,MAKER_BRCODE,ACTION,ACTION_DETAILS) values('"+ref_num+"','"+makerid+"',sysdate,'CATSTATUSAUTH','P','BM','"+branch_code+"','MAKER','Caregory Modify "+requestJSON.getString("groupType")+" ')");
				merchantPstmt.executeUpdate();
				merchantPstmt.close();
				
				
				merchantPstmt = connection.prepareStatement("insert into PAYBILL_CATE_TBL_TEMP ( CAT_ID,CAT_NAME,STATUS,MAKER_ID,MAKER_DT,JSON_VAL,REF_NUM) "
						+ "values('"+catid+"','"+catname+"','"+status+"','"+makerid+"',sysdate,'AUP','"+ref_num+"')");
				merchantPstmt.executeUpdate();
				merchantPstmt.close();
				
				
			}
			/*
			
			JSONObject auditjson = new JSONObject();
			auditjson.put(CevaCommonConstants.MAKER_ID,userID);
			auditjson.put("transCode", "group-creation");
			auditjson.put("channel", "WEB");
			auditjson.put("message","User Group code "+groupId +"  Created Successfully,Pendind for Autherization");
			auditjson.put("ip", ServletActionContext.getRequest().getRemoteAddr());
			auditjson.put("det1", "");
			auditjson.put("det2", "saveGroupDetails");
			auditjson.put("det3", "");
			
			CommonServiceDao csd=new CommonServiceDao();
			csd.auditTrailInsert(auditjson);	*/		
			connection.commit();

			//logger.debug("Rows Inserted are [" + size.length + "]");

			responseDTO = getUserGroupDetails(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			logger.debug("SQLException in InsertUserGroupDetails ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {
			logger.debug("Exception in InsertUserGroupDetails  ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(prepareStmt1);
			DBUtils.closeResultSet(getId);

			groupId = null;
			groupDesc = null;
			jsonVal = null;
			userID = null;
			entity = null;
			applCode = null;

			jArray = null;
			jobj = null;
			merchantQry = null;
			id = null;
			pid = null;
			title = null;
		}

		return responseDTO;
	}
	
	public ResponseDTO billerCatagerioInfo(RequestDTO requestDTO) {

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
			
			lmtfeeQry = "select CATGRP_CODE,CATGRP_NAME,MAKER_ID,STATUS	 from PAYBILLCATE_GRP_TBL";

			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("CATGRP_CODE", getlmtfeeRs.getString(1));
				json.put("CATGRP_NAME", getlmtfeeRs.getString(2));
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
	
	public ResponseDTO billerCategoryDetails(RequestDTO requestDTO) {

		logger.debug("Inside  viewCategory... ");

		HashMap<String, Object> merchantMap = null;
		JSONObject resultJson = null;
		JSONArray jsonArray = null;
		JSONObject rightsJson = null;
		JSONObject rightsJson1 = null;

		String merchantQry = "";
		String type = "";
		String entity = "";

		PreparedStatement prepareStmt1 = null;
		ResultSet merchantRS = null;
		Connection connection = null;

		try {
			responseDTO = new ResponseDTO();
			logger.debug("Request JSON [" + requestDTO.getRequestJSON() + "]");

			
			merchantMap = new HashMap<String, Object>();
			resultJson = new JSONObject();

			connection = DBConnector.getConnection();
			logger.debug("Connection is null [" + connection == null + "]");

			
			
			jsonArray = new JSONArray();
			
			rightsJson1 = new JSONObject();
			
			prepareStmt1 = connection
					.prepareStatement("select CAT_ID,'1',CAT_NAME from PAYBILL_CATE_TBL where CAT_ID in (select distinct CAT_ID from PAYBILL_SUBCATE_TBL) " + 
							"UNION ALL " + 
							"select SUBCAT_ID,CAT_ID,SUBCAT_NAME from PAYBILL_SUBCATE_TBL order by CAT_ID");
			merchantRS = prepareStmt1.executeQuery();
			while (merchantRS.next()) {
				rightsJson = new JSONObject();
				rightsJson.put("id", merchantRS.getString(1));
				rightsJson.put("pId", merchantRS.getString(2));
				rightsJson.put("name", merchantRS.getString(1)+"-"+merchantRS.getString(3));
				rightsJson.put("checked", "false");
				rightsJson.put("open", "true");
				rightsJson.put("title", merchantRS.getString(2));
				jsonArray.add(rightsJson);
				rightsJson.clear();
			}

			rightsJson1.put("user_rights", jsonArray);

			merchantMap.put("user_rights", rightsJson1);
			logger.debug("EntityMap [" + merchantMap + "]");
			responseDTO.setData(merchantMap);
			logger.debug("inside Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			logger.debug("SQLException in viewCategory [" + e.getMessage()
					+ "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {
			logger.debug("Exception in viewCategory  [" + e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(prepareStmt1);
			DBUtils.closeResultSet(merchantRS);

			merchantMap = null;
			resultJson = null;
			jsonArray = null;
			rightsJson = null;
			rightsJson1 = null;

			merchantQry = null;
			type = null;
			entity = null;

		}

		return responseDTO;
	}
	
	public ResponseDTO billerCateSaveGroupDetails(RequestDTO requestDTO) {

		logger.debug("Inside InsertUserGroupDetails.... ");

		// JSONObject object = null;
		String groupId = null;
		String groupDesc = null;
		String jsonVal = null;
		String userID = null;
		String entity = null;
		String applCode = null;
		String groupType = null;
		String ip = null;
		String makerid = null;
		String auditQry = "";

		String userlevel = null;

		org.json.JSONArray jArray = null;
		org.json.JSONObject jobj = null;

		String id = "";
		String pid = "";
		String title = "";
		String merchantQry = "";
		
		PreparedStatement prepareStmt2 = null;
		ResultSet resultSet1 = null;

		PreparedStatement prepareStmt1 = null;
		ResultSet getId = null;
		Connection connection = null;
		PreparedStatement merchantPstmt =null;

		try {
			responseDTO = new ResponseDTO();
			requestJSON = requestDTO.getRequestJSON();

			jsonVal = requestJSON.getString("jsonVal");
			
			ip=requestJSON.getString(CevaCommonConstants.IP);
			makerid=requestJSON.getString(CevaCommonConstants.MAKER_ID);
			//groupId=groupId.toUpperCase();
			System.out.println(jsonVal);
			try {

				jobj = new org.json.JSONObject(jsonVal);
				logger.debug(" jobj [" + jobj.toString() + "]");
				jArray = new org.json.JSONArray(jobj.getString("user_details"));
				org.json.JSONObject json_data = null;
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					//System.out.println("kailash ::: "+json_data);										
				}
				
				
				
			} catch (JSONException e) {
				logger.debug("Exception in parsing Json String.."
						+ e.getMessage());

			}
			connection = DBConnector.getConnection();

			logger.debug("Connection is null [" + connection == null + "]");

			connection.setAutoCommit(false);
			
			
			
			String ref_num="";
			String branch_code="";
			prepareStmt2 = connection.prepareStatement("select to_char(systimestamp,'DDISSSSSFF'),NVL(CLUSTER_ID,' ')  from USER_INFORMATION where COMMON_ID=(select common_id from USER_LOGIN_CREDENTIALS where LOGIN_USER_ID='"+requestJSON.getString("makerId")+"')");

			resultSet1 = prepareStmt2.executeQuery();
			while (resultSet1.next()) {
				ref_num=resultSet1.getString(1);
				branch_code=resultSet1.getString(2);
			}
			
			merchantPstmt = connection.prepareStatement("insert into auth_pending ( ref_num,maker_id,maker_dttm,auth_code,status,auth_flag,MAKER_BRCODE,ACTION,ACTION_DETAILS) values('"+ref_num+"','"+makerid+"',sysdate,'BILLERCATCREAUTH','P','BM','"+branch_code+"','MAKER','PaybillCategory Code "+requestJSON.getString("catids")+"')");
			merchantPstmt.executeUpdate();
			merchantPstmt.close();

			System.out.println(requestJSON.getString("groupType"));
			System.out.println(requestJSON.getString("catids"));
			String groupcode=requestJSON.getString("groupType");
			String catname=requestJSON.getString("catids");
			
			merchantPstmt = connection.prepareStatement("insert into PAYBILLCATE_GRP_TBL_TEMP ( CATGRP_CODE,CATGRP_NAME,STATUS,MAKER_ID,MAKER_DT,JSON_VAL,REF_NUM) "
					+ "values('"+groupcode+"','"+catname+"','A','"+makerid+"',sysdate,'AUP','"+ref_num+"')");
			merchantPstmt.executeUpdate();
			merchantPstmt.close();
			
			
			merchantQry = "insert into PAYBILLCATE_GRP_DT_TEMP(CATGRP_CODE ,CAT_ID ,SUBCAT_ID ,AUTH_FLAG ,REF_NUM,NAME ) "
					+ "values (?,?,?,?,?,?)";
			merchantPstmt = connection.prepareStatement(merchantQry);
			
			org.json.JSONObject json_data = null;
			
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				if (json_data.getString("checked").equalsIgnoreCase("true")) {
					
					System.out.println(json_data.getString("name"));
					
					if((json_data.getString("pId")).equalsIgnoreCase("0")) {
						
						merchantPstmt.setString(1, groupcode);
						merchantPstmt.setString(2, (json_data.getString("name")).split("-")[0]);
						merchantPstmt.setString(3, "1");
						merchantPstmt.setString(4, "AUP");
						merchantPstmt.setString(5, ref_num);
						merchantPstmt.setString(6, (json_data.getString("name")).split("-")[1]);
						merchantPstmt.addBatch();
						
					}else {
						merchantPstmt.setString(1, groupcode);
						prepareStmt1 = connection
								.prepareStatement("select CAT_ID from PAYBILL_SUBCATE_TBL where SUBCAT_ID=?");
						prepareStmt1.setString(1, (json_data.getString("name")).split("-")[0]);
						getId = prepareStmt1.executeQuery();
						merchantPstmt.setString(2, (json_data.getString("name")).split("-")[0]);
						if (getId.next()) {
							merchantPstmt.setString(3, getId.getString(1));
						}
						
						
						
						merchantPstmt.setString(4, "AUP");
						merchantPstmt.setString(5, ref_num);
						merchantPstmt.setString(6, (json_data.getString("name")).split("-")[1]);
						merchantPstmt.addBatch();
					}
					
					
					
					
				}
			}
			
			int size[] = merchantPstmt.executeBatch();

			/*
			
			JSONObject auditjson = new JSONObject();
			auditjson.put(CevaCommonConstants.MAKER_ID,userID);
			auditjson.put("transCode", "group-creation");
			auditjson.put("channel", "WEB");
			auditjson.put("message","User Group code "+groupId +"  Created Successfully,Pendind for Autherization");
			auditjson.put("ip", ServletActionContext.getRequest().getRemoteAddr());
			auditjson.put("det1", "");
			auditjson.put("det2", "saveGroupDetails");
			auditjson.put("det3", "");
			
			CommonServiceDao csd=new CommonServiceDao();
			csd.auditTrailInsert(auditjson);	*/		
			connection.commit();

			//logger.debug("Rows Inserted are [" + size.length + "]");

			responseDTO = getUserGroupDetails(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			logger.debug("SQLException in InsertUserGroupDetails ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {
			logger.debug("Exception in InsertUserGroupDetails  ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(prepareStmt1);
			DBUtils.closeResultSet(getId);

			groupId = null;
			groupDesc = null;
			jsonVal = null;
			userID = null;
			entity = null;
			applCode = null;

			jArray = null;
			jobj = null;
			merchantQry = null;
			id = null;
			pid = null;
			title = null;
		}

		return responseDTO;
	}
	
	public ResponseDTO viewBillerCategory(RequestDTO requestDTO) {

		logger.debug("Inside  viewCategory... ");

		HashMap<String, Object> merchantMap = null;
		JSONObject resultJson = null;
		JSONArray jsonArray = null;
		JSONObject rightsJson = null;
		JSONObject rightsJson1 = null;

		String merchantQry = "";
		String type = "";
		String entity = "";
		String groupType = "";

		PreparedStatement prepareStmt1 = null;
		ResultSet merchantRS = null;
		Connection connection = null;

		try {
			responseDTO = new ResponseDTO();
			logger.debug("Request JSON [" + requestDTO.getRequestJSON() + "]");

			
			groupType = requestDTO.getRequestJSON().getString("groupType");
			merchantMap = new HashMap<String, Object>();
			resultJson = new JSONObject();

			connection = DBConnector.getConnection();
			logger.debug("Connection is null [" + connection == null + "]");

			
			
			jsonArray = new JSONArray();
			
			rightsJson1 = new JSONObject();
			
			prepareStmt1 = connection
					.prepareStatement("select CAT_ID,SUBCAT_ID,NAME from PAYBILLCATE_GRP_DT where CATGRP_CODE=?");
			prepareStmt1.setString(1, groupType);
			merchantRS = prepareStmt1.executeQuery();
			while (merchantRS.next()) {
				rightsJson = new JSONObject();
				rightsJson.put("id", merchantRS.getString(1));
				rightsJson.put("pId", merchantRS.getString(2));
				rightsJson.put("name", merchantRS.getString(1)+"-"+merchantRS.getString(3));
				rightsJson.put("checked", "true");
				rightsJson.put("open", "true");
				rightsJson.put("title", merchantRS.getString(2));
				rightsJson.put("chkDisabled", "true");
				jsonArray.add(rightsJson);
				rightsJson.clear();
			}

			rightsJson1.put("user_details", jsonArray);
			resultJson.put("json_val", rightsJson1);

			merchantMap.put("user_rights", resultJson);
			logger.debug("EntityMap [" + merchantMap + "]");
			responseDTO.setData(merchantMap);
			logger.debug("inside Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			logger.debug("SQLException in viewCategory [" + e.getMessage()
					+ "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {
			logger.debug("Exception in viewCategory  [" + e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(prepareStmt1);
			DBUtils.closeResultSet(merchantRS);

			merchantMap = null;
			resultJson = null;
			jsonArray = null;
			rightsJson = null;
			rightsJson1 = null;

			merchantQry = null;
			type = null;
			entity = null;

		}

		return responseDTO;
	}
	
	public ResponseDTO modifyBillerCategory(RequestDTO requestDTO) {

		logger.debug("Inside  viewCategory... ");

		HashMap<String, Object> merchantMap = null;
		JSONObject resultJson = null;
		JSONArray jsonArray = null;
		JSONObject rightsJson = null;
		JSONObject rightsJson1 = null;
		JSONObject rightsJson2 = null;

		String merchantQry = "";
		String type = "";
		String entity = "";
		String groupType = "";

		PreparedStatement prepareStmt1 = null;
		ResultSet merchantRS = null;
		Connection connection = null;

		try {
			responseDTO = new ResponseDTO();
			logger.debug("Request JSON [" + requestDTO.getRequestJSON() + "]");

			
			
			groupType = requestDTO.getRequestJSON().getString("groupType");
			merchantMap = new HashMap<String, Object>();
			resultJson = new JSONObject();

			connection = DBConnector.getConnection();
			logger.debug("Connection is null [" + connection == null + "]");

			
			
			jsonArray = new JSONArray();
			
			rightsJson1 = new JSONObject();
			rightsJson2 = new JSONObject();
			
			prepareStmt1 = connection
					.prepareStatement("select CAT_ID from PAYBILLCATE_GRP_DT where CATGRP_CODE=?");
			prepareStmt1.setString(1, groupType);
			merchantRS = prepareStmt1.executeQuery();
			while (merchantRS.next()) {
				rightsJson2.put(merchantRS.getString(1), merchantRS.getString(1));
				
			}
			
			prepareStmt1.close();
			merchantRS.close();
			
			prepareStmt1 = connection
					.prepareStatement("select CAT_ID,'1',CAT_NAME from PAYBILL_CATE_TBL where CAT_ID in (select distinct CAT_ID from PAYBILL_SUBCATE_TBL) " + 
							"UNION ALL " + 
							"select SUBCAT_ID,CAT_ID,SUBCAT_NAME from PAYBILL_SUBCATE_TBL order by CAT_ID");
			merchantRS = prepareStmt1.executeQuery();
			while (merchantRS.next()) {
				rightsJson = new JSONObject();
				rightsJson.put("id", merchantRS.getString(1));
				rightsJson.put("pId", merchantRS.getString(2));
				rightsJson.put("name", merchantRS.getString(1)+"-"+merchantRS.getString(3));
					if(rightsJson2.containsKey(merchantRS.getString(1))) {
						rightsJson.put("checked", "true");
					}else {
						rightsJson.put("checked", "false");
					}
					
					rightsJson.put("open", "true");
					rightsJson.put("title", merchantRS.getString(2));
					rightsJson.put("chkDisabled", "false");
					jsonArray.add(rightsJson);
					rightsJson.clear();
			}
			
			

			rightsJson1.put("user_details", jsonArray);
			resultJson.put("json_val", rightsJson1);

			merchantMap.put("user_rights", resultJson);
			logger.debug("EntityMap [" + merchantMap + "]");
			responseDTO.setData(merchantMap);
			logger.debug("inside Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			logger.debug("SQLException in viewCategory [" + e.getMessage()
					+ "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {
			logger.debug("Exception in viewCategory  [" + e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(prepareStmt1);
			DBUtils.closeResultSet(merchantRS);

			merchantMap = null;
			resultJson = null;
			jsonArray = null;
			rightsJson = null;
			rightsJson1 = null;

			merchantQry = null;
			type = null;
			entity = null;

		}

		return responseDTO;
	}
	
	
	public ResponseDTO modifybillercatsaveGroupDetails(RequestDTO requestDTO) {

		logger.debug("Inside modifysaveGroupDetails.... ");

		// JSONObject object = null;
		String groupId = null;
		String groupDesc = null;
		String jsonVal = null;
		String userID = null;
		String entity = null;
		String applCode = null;
		String ip = null;
		String makerid = null;
		String auditQry = "";

		String userlevel = null;

		org.json.JSONArray jArray = null;
		org.json.JSONObject jobj = null;

		String id = "";
		String pid = "";
		String title = "";
		String merchantQry = "";
		
		PreparedStatement prepareStmt2 = null;
		ResultSet resultSet1 = null;

		PreparedStatement prepareStmt1 = null;
		ResultSet getId = null;
		Connection connection = null;
		PreparedStatement merchantPstmt =null;

		try {
			responseDTO = new ResponseDTO();
			requestJSON = requestDTO.getRequestJSON();

			jsonVal = requestJSON.getString("jsonVal");
			
			ip=requestJSON.getString(CevaCommonConstants.IP);
			makerid=requestJSON.getString(CevaCommonConstants.MAKER_ID);
			//groupId=groupId.toUpperCase();
			System.out.println(jsonVal);
			try {

				jobj = new org.json.JSONObject(jsonVal);
				logger.debug(" jobj [" + jobj.toString() + "]");
				jArray = new org.json.JSONArray(jobj.getString("user_details"));
				org.json.JSONObject json_data = null;
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					//System.out.println("kailash ::: "+json_data);										
				}
				
				
				
			} catch (JSONException e) {
				logger.debug("Exception in parsing Json String.."
						+ e.getMessage());

			}
			connection = DBConnector.getConnection();

			logger.debug("Connection is null [" + connection == null + "]");

			connection.setAutoCommit(false);
			
			
			
			String ref_num="";
			String branch_code="";
			prepareStmt2 = connection.prepareStatement("select to_char(systimestamp,'DDISSSSSFF'),NVL(CLUSTER_ID,' ')  from USER_INFORMATION where COMMON_ID=(select common_id from USER_LOGIN_CREDENTIALS where LOGIN_USER_ID='"+requestJSON.getString("makerId")+"')");

			resultSet1 = prepareStmt2.executeQuery();
			while (resultSet1.next()) {
				ref_num=resultSet1.getString(1);
				branch_code=resultSet1.getString(2);
			}
			
			

			System.out.println(requestJSON.getString("groupType"));
			System.out.println(requestJSON.getString("catids"));
			String groupType=requestJSON.getString("groupType");
			String catname=requestJSON.getString("catids");
			
			
			if((requestJSON.getString("type")).equalsIgnoreCase("Modify")) {
			
			
				merchantPstmt = connection.prepareStatement("insert into auth_pending ( ref_num,maker_id,maker_dttm,auth_code,status,auth_flag,MAKER_BRCODE,ACTION,ACTION_DETAILS) values('"+ref_num+"','"+makerid+"',sysdate,'BILLERCATMODIFYAUTH','P','BM','"+branch_code+"','MAKER','PaybillCategory Modify "+requestJSON.getString("catids")+"')");
				merchantPstmt.executeUpdate();
				merchantPstmt.close();
			
			merchantPstmt = connection.prepareStatement("insert into PAYBILLCATE_GRP_TBL_TEMP ( CATGRP_CODE,CATGRP_NAME,STATUS,MAKER_ID,MAKER_DT,JSON_VAL,REF_NUM) "
					+ "values('"+groupType+"','"+catname+"','A','"+makerid+"',sysdate,'AUP','"+ref_num+"')");
			merchantPstmt.executeUpdate();
			merchantPstmt.close();
			
			
			merchantQry = "insert into PAYBILLCATE_GRP_DT_TEMP(CATGRP_CODE ,CAT_ID ,SUBCAT_ID ,AUTH_FLAG ,REF_NUM,NAME ) "
					+ "values (?,?,?,?,?,?)";
			merchantPstmt = connection.prepareStatement(merchantQry);
			
			org.json.JSONObject json_data = null;
			
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				if (json_data.getString("checked").equalsIgnoreCase("true")) {
					
					System.out.println(json_data.getString("name"));
					
					if((json_data.getString("pId")).equalsIgnoreCase("0")) {
						
						merchantPstmt.setString(1, groupType);
						merchantPstmt.setString(2, (json_data.getString("name")).split("-")[0]);
						merchantPstmt.setString(3, "1");
						merchantPstmt.setString(4, "AUP");
						merchantPstmt.setString(5, ref_num);
						merchantPstmt.setString(6, (json_data.getString("name")).split("-")[1]);
						merchantPstmt.addBatch();
						
					}else {
						merchantPstmt.setString(1, groupType);
						prepareStmt1 = connection
								.prepareStatement("select CAT_ID from PAYBILL_SUBCATE_TBL where SUBCAT_ID=?");
						prepareStmt1.setString(1, (json_data.getString("name")).split("-")[0]);
						getId = prepareStmt1.executeQuery();
						merchantPstmt.setString(2, (json_data.getString("name")).split("-")[0]);
						if (getId.next()) {
							merchantPstmt.setString(3, getId.getString(1));
						}
						
						
						
						merchantPstmt.setString(4, "AUP");
						merchantPstmt.setString(5, ref_num);
						merchantPstmt.setString(6, (json_data.getString("name")).split("-")[1]);
						merchantPstmt.addBatch();
					}
					
					
					
					
				}
			}
			
			int size[] = merchantPstmt.executeBatch();
			}else if((requestJSON.getString("type")).equalsIgnoreCase("Status")) {
				String status=requestJSON.getString("status");
				if(status.equalsIgnoreCase("Active")) {
					status="L";
				}else {
					status="A";
				}
				
				
				merchantPstmt = connection.prepareStatement("insert into auth_pending ( ref_num,maker_id,maker_dttm,auth_code,status,auth_flag,MAKER_BRCODE,ACTION,ACTION_DETAILS) values('"+ref_num+"','"+makerid+"',sysdate,'BILLERCATSTATUSAUTH','P','BM','"+branch_code+"','MAKER','PaybillCategory Status "+requestJSON.getString("catids")+"')");
				merchantPstmt.executeUpdate();
				merchantPstmt.close();
				
				merchantPstmt = connection.prepareStatement("insert into PAYBILLCATE_GRP_TBL_TEMP ( CATGRP_CODE,CATGRP_NAME,STATUS,MAKER_ID,MAKER_DT,JSON_VAL,REF_NUM) "
						+ "values('"+groupType+"','"+catname+"','"+status+"','"+makerid+"',sysdate,'AUP','"+ref_num+"')");
				merchantPstmt.executeUpdate();
				merchantPstmt.close();
				
			}
			/*
			
			JSONObject auditjson = new JSONObject();
			auditjson.put(CevaCommonConstants.MAKER_ID,userID);
			auditjson.put("transCode", "group-creation");
			auditjson.put("channel", "WEB");
			auditjson.put("message","User Group code "+groupId +"  Created Successfully,Pendind for Autherization");
			auditjson.put("ip", ServletActionContext.getRequest().getRemoteAddr());
			auditjson.put("det1", "");
			auditjson.put("det2", "saveGroupDetails");
			auditjson.put("det3", "");
			
			CommonServiceDao csd=new CommonServiceDao();
			csd.auditTrailInsert(auditjson);	*/		
			connection.commit();

			//logger.debug("Rows Inserted are [" + size.length + "]");

			responseDTO = getUserGroupDetails(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			logger.debug("SQLException in InsertUserGroupDetails ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {
			logger.debug("Exception in InsertUserGroupDetails  ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(prepareStmt1);
			DBUtils.closeResultSet(getId);

			groupId = null;
			groupDesc = null;
			jsonVal = null;
			userID = null;
			entity = null;
			applCode = null;

			jArray = null;
			jobj = null;
			merchantQry = null;
			id = null;
			pid = null;
			title = null;
		}

		return responseDTO;
	}
	
	
	public ResponseDTO billerFeeInfo(RequestDTO requestDTO) {

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
			
			lmtfeeQry = "select CATGRP_CODE,CATGRP_NAME,MAKER_ID,STATUS,(select count(*) from PAYBILL_FEE_DETAILS where TXN_CODE=PGT.CATGRP_CODE) as fee_status	 from PAYBILLCATE_GRP_TBL PGT";

			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("CATGRP_CODE", getlmtfeeRs.getString(1));
				json.put("CATGRP_NAME", getlmtfeeRs.getString(2));
				json.put("MAKER_ID", getlmtfeeRs.getString(3));
				json.put("STATUS", getlmtfeeRs.getString(4));
				json.put("FEESTATUS", getlmtfeeRs.getString(5));
				
					
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
	
	public ResponseDTO billerInfoDetails(RequestDTO requestDTO) {

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
			
			System.out.println("kailash :: "+requestJSON.getString("catids"));

			logger.debug("[ BranchCreationDao ] [ branchInfo ] connection is null [" + connection == null + "]");
			
			lmtfeeQry = "select PCT.CAT_NAME,PCD.BILLER_NAME,PCT.CAT_ID,PCD.BILLER_ID from PAYBILL_CATE_DT PCD, PAYBILL_CATE_MASTER PCT where PCD.CAT_ID=PCT.CAT_ID";

			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("CAT_NAME", getlmtfeeRs.getString(1));
				json.put("BILLER_NAME", getlmtfeeRs.getString(2));
				json.put("CAT_ID", getlmtfeeRs.getString(3));
				json.put("BILLER_ID", getlmtfeeRs.getString(4));
				
				
					
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
	
	public ResponseDTO billerInfoViewDetails(RequestDTO requestDTO) {

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
			
			System.out.println("kailash :: "+requestJSON.getString("catids"));

			logger.debug("[ BranchCreationDao ] [ branchInfo ] connection is null [" + connection == null + "]");
			
			lmtfeeQry = "select CAT_NAME,BILLER_NAME,BANK,BANK_FEE_TYPE,AGENT,AGENT_FEE_TYPE,SUPERAGENT,SUPERAGENT_FEE_TYPE,CEVA,CEVA_FEE_TYPE,THIRD_PARTY,TR_FEE_TYPE,VAT,VAT_FEE_TYPE,FEE_CHARGE from PAYBILL_FEE_DETAILS where TXN_CODE='"+requestJSON.getString("catids")+"'";

			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();

			while (getlmtfeeRs.next()) {

				
				json = new JSONObject();
				json.put("CAT_NAME", getlmtfeeRs.getString(1));
				json.put("BILLER_NAME", getlmtfeeRs.getString(2));
				json.put("BANK", getlmtfeeRs.getString(3));
				json.put("BANK_FEE_TYPE", getlmtfeeRs.getString(4));
				
				json.put("AGENT", getlmtfeeRs.getString(5));
				json.put("AGENT_FEE_TYPE", getlmtfeeRs.getString(6));
				
			
				
				json.put("SUPERAGENT", getlmtfeeRs.getString(7));
				json.put("SUPERAGENT_FEE_TYPE", getlmtfeeRs.getString(8));
				
				json.put("CEVA", getlmtfeeRs.getString(9));
				json.put("CEVA_FEE_TYPE", getlmtfeeRs.getString(10));
				
				json.put("THIRD_PARTY", getlmtfeeRs.getString(11));
				json.put("TR_FEE_TYPE", getlmtfeeRs.getString(12));
				
				json.put("VAT", getlmtfeeRs.getString(13));
				json.put("VAT_FEE_TYPE", getlmtfeeRs.getString(14));
				
				json.put("FEE_CHARGE", getlmtfeeRs.getString(15));
				
				
					
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
	
	public ResponseDTO paybillFeeCreation(RequestDTO requestDTO) {

		logger.debug("Inside InsertUserGroupDetails.... ");

		// JSONObject object = null;
		String groupId = null;
		String groupDesc = null;
		String jsonVal = null;
		String userID = null;
		String entity = null;
		String applCode = null;
		String groupType = null;
		String ip = null;
		String makerid = null;
		String auditQry = "";

		String userlevel = null;

		org.json.JSONArray jArray = null;
		org.json.JSONObject jobj = null;

		String id = "";
		String pid = "";
		String title = "";
		String merchantQry = "";
		
		PreparedStatement prepareStmt2 = null;
		ResultSet resultSet1 = null;

		PreparedStatement prepareStmt1 = null;
		ResultSet getId = null;
		Connection connection = null;
		PreparedStatement merchantPstmt =null;

		try {
			responseDTO = new ResponseDTO();
			requestJSON = requestDTO.getRequestJSON();

			jsonVal = requestJSON.getString("vdet");
			
			ip=requestJSON.getString(CevaCommonConstants.IP);
			makerid=requestJSON.getString(CevaCommonConstants.MAKER_ID);
			
			connection = DBConnector.getConnection();

			logger.debug("Connection is null [" + connection == null + "]");

			connection.setAutoCommit(false);
			
			
			
			String ref_num="";
			String branch_code="";
			prepareStmt2 = connection.prepareStatement("select to_char(systimestamp,'DDISSSSSFF'),NVL(CLUSTER_ID,' ')  from USER_INFORMATION where COMMON_ID=(select common_id from USER_LOGIN_CREDENTIALS where LOGIN_USER_ID='"+requestJSON.getString("makerId")+"')");

			resultSet1 = prepareStmt2.executeQuery();
			while (resultSet1.next()) {
				ref_num=resultSet1.getString(1);
				branch_code=resultSet1.getString(2);
			}
			
			merchantPstmt = connection.prepareStatement("insert into auth_pending ( ref_num,maker_id,maker_dttm,auth_code,status,auth_flag,MAKER_BRCODE,ACTION,ACTION_DETAILS) values('"+ref_num+"','"+makerid+"',sysdate,'BILLERFEECREAUTH','P','BM','"+branch_code+"','MAKER','Category Creation  "+requestJSON.getString("catids")+" ')");
			merchantPstmt.executeUpdate();
			merchantPstmt.close();
			
			merchantPstmt = connection.prepareStatement("insert into PAYBILL_FEE_MASTER_TEMP (CATGRP_CODE,FEE_CODE,REF_NO,AUTH_CODE) values('"+requestJSON.getString("catids")+"','"+ref_num+"','"+ref_num+"','AUP')");
			merchantPstmt.executeUpdate();
			merchantPstmt.close();

			
			merchantQry = "insert into PAYBILL_FEE_DETAILS_TEMP(BANK,BANK_FEE_TYPE,AGENT,AGENT_FEE_TYPE,SUPERAGENT,SUPERAGENT_FEE_TYPE,CEVA,CEVA_FEE_TYPE,THIRD_PARTY,TR_FEE_TYPE,VAT,VAT_FEE_TYPE,FEE_CHARGE,CAT_ID,CAT_NAME,BILLER_ID,BILLER_NAME,FREQUENCY,MIN_CNT,MAX_CNT,TXN_CODE,REF_NO,AUTH_FLAG,FEE_CODE) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Per Txn','1','1',?,?,'AUP',?)";
			merchantPstmt = connection.prepareStatement(merchantQry);
			
			
			String arraydata[]=jsonVal.split("#");
			
			for (int i = 0; i < arraydata.length; i++) {
				
					merchantPstmt.setString(1, (arraydata[i].split("@")[4]).split("-")[0]);
					merchantPstmt.setString(2, (arraydata[i].split("@")[4]).split("-")[1]);
					
					merchantPstmt.setString(3, (arraydata[i].split("@")[5]).split("-")[0]);
					merchantPstmt.setString(4, (arraydata[i].split("@")[5]).split("-")[1]);
					
					merchantPstmt.setString(5, (arraydata[i].split("@")[6]).split("-")[0]);
					merchantPstmt.setString(6, (arraydata[i].split("@")[6]).split("-")[1]);
					
					merchantPstmt.setString(7, (arraydata[i].split("@")[7]).split("-")[0]);
					merchantPstmt.setString(8, (arraydata[i].split("@")[7]).split("-")[1]);
					
					merchantPstmt.setString(9, (arraydata[i].split("@")[8]).split("-")[0]);
					merchantPstmt.setString(10, (arraydata[i].split("@")[8]).split("-")[1]);
					
					merchantPstmt.setString(11, (arraydata[i].split("@")[9]).split("-")[0]);
					merchantPstmt.setString(12, (arraydata[i].split("@")[9]).split("-")[1]);
					
					merchantPstmt.setString(13, (arraydata[i].split("@")[10]));
					
					merchantPstmt.setString(14, (arraydata[i].split("@")[2]));
					merchantPstmt.setString(15, (arraydata[i].split("@")[0]));
					merchantPstmt.setString(16, (arraydata[i].split("@")[3]));
					merchantPstmt.setString(17, (arraydata[i].split("@")[1]));
					merchantPstmt.setString(18, requestJSON.getString("catids"));
					merchantPstmt.setString(19, ref_num);
					merchantPstmt.setString(20, ref_num);
					
					
					merchantPstmt.addBatch();
					
					
				
			}
			
			int size[] = merchantPstmt.executeBatch();

				
			connection.commit();

			//logger.debug("Rows Inserted are [" + size.length + "]");

			responseDTO = getUserGroupDetails(requestDTO);
			logger.debug("Response DTO [" + responseDTO + "]");

		} catch (SQLException e) {
			logger.debug("SQLException in InsertUserGroupDetails ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} catch (Exception e) {
			logger.debug("Exception in InsertUserGroupDetails  ["
					+ e.getMessage() + "]");
			responseDTO.addError("Internal Error Occured While Executing.");
		} finally {
			DBUtils.closeConnection(connection);
			DBUtils.closePreparedStatement(prepareStmt1);
			DBUtils.closeResultSet(getId);

			groupId = null;
			groupDesc = null;
			jsonVal = null;
			userID = null;
			entity = null;
			applCode = null;

			jArray = null;
			jobj = null;
			merchantQry = null;
			id = null;
			pid = null;
			title = null;
		}

		return responseDTO;
	}

}
