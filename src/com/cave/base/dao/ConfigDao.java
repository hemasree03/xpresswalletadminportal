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


public class ConfigDao {


	private Logger logger = Logger.getLogger(ConfigDao.class);

	ResponseDTO responseDTO = null;
	JSONObject requestJSON = null;
	JSONObject responseJSON = null;


	
		
	
	public ResponseDTO initialproduct(RequestDTO requestDTO) {

		logger.debug(" Inside fetchProductGridInfo.. ");

		HashMap<String, Object> viewDataMap = null;
		JSONArray lmtJsonArray = null;
		JSONArray feeJsonArray = null;
		
		
		JSONObject resultJson = null;
		JSONObject json = new JSONObject();
		JSONObject jsonlmt = new JSONObject();

		PreparedStatement getlmtfeePstmt = null;
		ResultSet getlmtfeeRs = null;

		Connection connection = null;

		String lmtfeeQry = "select PRD_CODE||'-'||PRD_NAME from PRODUCT";



		try {
			responseDTO = new ResponseDTO();

			viewDataMap = new HashMap<String, Object>();
			lmtJsonArray = new JSONArray();
			feeJsonArray = new JSONArray();
			
			resultJson = new JSONObject();

			requestJSON = requestDTO.getRequestJSON();
			connection = DBConnector.getConnection();

			logger.debug("connection is null [" + connection == null + "]");

			getlmtfeePstmt = connection.prepareStatement(lmtfeeQry);

			getlmtfeeRs = getlmtfeePstmt.executeQuery();
			
			while (getlmtfeeRs.next()) {
				jsonlmt.put(getlmtfeeRs.getString(1), getlmtfeeRs.getString(1));
			}
			
			json.put("PRODUCT_DETAILS", jsonlmt);
			jsonlmt.clear();
			
			
			logger.info("Final Json Object ["+json+"]");
			viewDataMap.put("Files_List1", json);
			logger.debug("Limit Fee DataMap    [" + viewDataMap + "]");
			responseDTO.setData(viewDataMap);


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

		
			resultJson = null;
		}

		return responseDTO;
	}
	
	public ResponseDTO initialprdack(RequestDTO requestDTO) {


		logger.debug("Inside fetchProductInfo.. ");
		HashMap<String, Object> lmtDataMap = null;

		Connection connection = null;
		CallableStatement cs = null;

		JSONObject requestJSON = requestDTO.getRequestJSON();

		String retstr = null;
		int retval=0;

		try {

			responseDTO = new ResponseDTO();

			lmtDataMap = new HashMap<String, Object>();


			 connection = DBConnector.getConnection();
			
			//connection=DBUtil.getDBConnection();
			
			String product = requestJSON.getString("product");
			String productname = requestJSON.getString("productname");

			String makerid = requestJSON.getString(CevaCommonConstants.MAKER_ID);

			logger.debug("connection is null [" + connection == null + "]");

			if(connection!=null)
			{
				String confirmQuery = "{call CONFIGURATION.INSERTPRODUCT(?,?,?,?,?,?)}";

				cs = connection.prepareCall(confirmQuery);
				cs.setString(1,  product);	
				cs.setString(2, productname);
				cs.setString(3, makerid);
				cs.setString(4, requestJSON.getString("remoteip"));
				
				cs.registerOutParameter(5, java.sql.Types.VARCHAR);
				cs.registerOutParameter(6, java.sql.Types.NUMERIC);


				cs.execute();

				//System.out.println("FLAG VALUE : "+ cs.getString(16));
				retstr = cs.getString(5);
				retval= cs.getInt(6);

				/*retstr = cs.getString(5);
				int rretval= cs.getInt(6);*/
				
				
				JSONObject json = new JSONObject();
				json.put(CevaCommonConstants.MAKER_ID, requestJSON.getString("makerId"));
				json.put("transCode", "productlimitsettings");
				json.put("channel", "WEB");
				json.put("message", "Acknowledgment :: Created for  initital product code is  "+product);
				json.put("ip", requestJSON.getString("remoteip"));
				json.put("det1", "");
				json.put("det2", "");
				json.put("det3", "");
				
				CommonServiceDao csd=new CommonServiceDao();
				csd.auditTrailInsert(json);

				if (retval == 1) {
					responseDTO.addMessages(retstr);
				} else
				{
					responseDTO.addError(retstr);
				}

				logger.info("call LimitFeeConfigPKG.pLimitConfig ********** "+retstr+" ************ "+retval);


			}else
			{

				logger.warn("Connection Null Plese Check once");

			}

		} catch (Exception e) {

			logger.debug("Got Exception in   [" + e.getMessage() + "]");
			responseDTO.addError("Internal Error");
			e.printStackTrace();

		} finally {


			try {

				if (connection != null) {

					connection.close();

				}

				if(cs!=null)
				{
					cs.close();

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			/*lmtDataMap = null;
			resultJson = null;*/
		}

		return responseDTO;
	
	}
}