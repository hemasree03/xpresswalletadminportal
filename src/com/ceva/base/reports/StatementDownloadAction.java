package com.ceva.base.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.ceva.base.common.dao.ReportsDAO;
import com.ceva.base.common.utils.DBConnector;
import com.ceva.util.DBUtils;
import com.opensymphony.xwork2.ActionSupport;

public class StatementDownloadAction  extends ActionSupport{
	
	private static Logger logger = Logger.getLogger(DownloadAction.class);
 
	private InputStream inputStream;
    private String fileName;
    private String fromdate;
    private String todate;
    private String branchid;
    private String type;
    
    
    
    
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBranchid() {
		return branchid;
	}

	public void setBranchid(String branchid) {
		this.branchid = branchid;
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

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	private long contentLength;
    
    String result=SUCCESS;
    
	public String execute()
	{
		Connection connection = null;
		String pdffilename="";
		StringBuilder sb=new StringBuilder();
		boolean reportresult=true;
		String entityQry="";
		String accountno="";
		ResultSet searchRS = null;
		PreparedStatement pstmt = null;
		PreparedStatement SPreStatement = null;
		ResultSet SResultSet = null;
		ResourceBundle rb = ResourceBundle.getBundle("configurations");
		try
		{
			connection = DBConnector.getConnection();
			logger.debug("Connection is null [" + connection == null + "]");
			
			System.out.println("from date :: "+fromdate);
			System.out.println("to date :: "+todate);
			System.out.println("BranchCode :: "+branchid);
			System.out.println("wallet type :: "+type);
			
			ReportGeneration rg=new ReportGeneration();
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			entityQry = "select acm.first_name||' '||acm.middle_name||' '||acm.last_name,acm.emailid,MD.ACCT_NO,MOBILE_NUMBER,TO_CHAR(BALANCE,'999,999,999.99') from WALLET_ACCT_DATA MD,AGENT_CONTACT_INFO MCI,AGENT_CUSTOMER_MASTER ACM where ACM.ID=MCI.CUST_ID AND MD.CUST_ID=MCI.CUST_ID AND PRIM_FLAG='P' AND MOBILE_NUMBER='234"+branchid+"'";
			pstmt = connection.prepareStatement(entityQry);
			searchRS = pstmt.executeQuery();
			if (searchRS.next()) {
				parameters.put("mobileno", searchRS.getString(4));
				parameters.put("accountno", searchRS.getString(3));
				parameters.put("name", searchRS.getString(1));
				//parameters.put("balance", searchRS.getString(5));
				//parameters.put("open_balance", searchRS.getString(5));
				
				String querystr="select * from (select TXNID,ACCOUNT, NVL(decode(DRCR_FLAG,'D',TO_CHAR(AMOUNT,'999,999,999.99')),' ') dramt, NVL(decode(DRCR_FLAG,'C',TO_CHAR(AMOUNT,'999,999,999.99')),' ') cramt,(SELECT BSCM.SERVICEDESC FROM BANK_SERVICE_CODE_MASTER BSCM WHERE BSCM.SERVICECODE=WFTP.SERVICECODE) AS WFTPSERVICECODE ,TXN_REF_NO,CHANNEL,TO_CHAR(TXN_STAMP,'dd-mm-yyyy hh:mi:ss') AS TXN_DATE,DECODE(TXN_TYPE,'T','Transaction','F','Commission') as TXN_TYPES,decode(CHANNEL,'POS',(select NVL(json_value(JREQUEST,'$.jbody.extrainfo.cardinfo'),' ') from WALLET_FIN_TXN where TXN_REF_NO=WFTP.TXN_REF_NO),NVL((select NVL(json_value(FTO_RESPONSE,'$.data.creditTransfer.sessionId'),' ') from AGENT_WALLET_FTO_TRANS_TBL where FTID=WFTP.TXN_REF_NO),' ')) as NARRATION from WALLET_FIN_TXN_POSTING WFTP where " + 
						"ACCOUNT in  ('"+searchRS.getString(3)+"','0"+searchRS.getString(3)+"')  AND TRUNC(TXN_STAMP) between to_date('"+fromdate+"','dd/mm/yyyy') and to_date('"+todate+"','dd/mm/yyyy') " + 
						"and CHANNEL!='AUTO' " + 
						"union all " + 
						"select TXNID,ACCOUNT, NVL(decode(DRCR_FLAG,'D',TO_CHAR(AMOUNT,'999,999,999.99')),' ') dramt, NVL(decode(DRCR_FLAG,'C',TO_CHAR(AMOUNT,'999,999,999.99')),' ') cramt,(SELECT BSCM.SERVICEDESC FROM BANK_SERVICE_CODE_MASTER BSCM WHERE BSCM.SERVICECODE=WFTP.SERVICECODE) AS WFTPSERVICECODE ,TXN_REF_NO,CHANNEL,TO_CHAR(TXN_STAMP,'dd-mm-yyyy hh:mi:ss') AS TXN_DATE,DECODE(TXN_TYPE,'T','Transaction','F','Commission') as TXN_TYPES,decode(CHANNEL,'POS',(select NVL(json_value(JREQUEST,'$.jbody.extrainfo.cardinfo'),' ') from WALLET_FIN_TXN where TXN_REF_NO=WFTP.TXN_REF_NO),NVL((select NVL(json_value(FTO_RESPONSE,'$.data.creditTransfer.sessionId'),' ') from AGENT_WALLET_FTO_TRANS_TBL where FTID=WFTP.TXN_REF_NO),' ')) as NARRATION from WALLET_FIN_TXN_POSTING WFTP where " + 
						"ACCOUNT='"+searchRS.getString(3)+"'  AND TRUNC(TXN_STAMP) between to_date('"+fromdate+"','dd/mm/yyyy') and to_date('"+todate+"','dd/mm/yyyy') " + 
						"and CHANNEL='AUTO') order by TXNID asc";
				
				//System.out.println("kailash : "+querystr);
				
				
				parameters.put("QUERY","select ACCOUNT, NVL(decode(DRCR_FLAG,'D',TO_CHAR(AMOUNT,'999,999,999.99')),' ') dramt, NVL(decode(DRCR_FLAG,'C',TO_CHAR(AMOUNT,'999,999,999.99')),' ') cramt,(SELECT BSCM.SERVICEDESC FROM BANK_SERVICE_CODE_MASTER BSCM WHERE BSCM.SERVICECODE=WFTP.SERVICECODE) AS WFTPSERVICECODE ,TXN_REF_NO,CHANNEL,TO_CHAR(TXN_STAMP,'dd-mm-yyyy hh:mi:ss') AS TXN_DATE,DECODE(TXN_TYPE,'T','Transaction','F','Commission') as TXN_TYPES,decode(CHANNEL,'POS',(select NVL(json_value(JREQUEST,'$.jbody.extrainfo.cardinfo'),' ') from WALLET_FIN_TXN where TXN_REF_NO=WFTP.TXN_REF_NO),NVL((select NVL(json_value(FTO_RESPONSE,'$.data.creditTransfer.sessionId'),' ') from AGENT_WALLET_FTO_TRANS_TBL where FTID=WFTP.TXN_REF_NO),' ')) as NARRATION from WALLET_FIN_TXN_POSTING WFTP where ACCOUNT in  ('"+searchRS.getString(3)+"','0"+searchRS.getString(3)+"')  AND TRUNC(TXN_STAMP) between to_date('"+fromdate+"','dd/mm/yyyy') and to_date('"+todate+"','dd/mm/yyyy') order by TXNID asc");
				//parameters.put("QUERY",querystr);
				parameters.put("SELECT_DATE", "");
				
				SPreStatement = connection.prepareStatement("select STORE_NAME from STORE_MASTER where TELEPHONE_NO='"+searchRS.getString(4)+"' and rownum=1");
				SResultSet=SPreStatement.executeQuery();
				parameters.put("storename", " ");
				if (SResultSet.next()) {
					parameters.put("storename", SResultSet.getString(1));
					
				}
				
				SPreStatement = connection.prepareStatement("select sum(decode(wftp.DRCR_FLAG,'D',-wftp.AMOUNT,+wftp.AMOUNT)) from wallet_fin_txn_posting wftp where wftp.ACCOUNT='"+searchRS.getString(3)+"' and TRUNC(TXN_STAMP)<=to_date('"+fromdate+"','dd/mm/yyyy')-1");
				SResultSet=SPreStatement.executeQuery();
				parameters.put("storename", " ");
				if (SResultSet.next()) {
					parameters.put("open_balance", SResultSet.getString(1));
					
				}
				
				SPreStatement.close();
				SResultSet.close();
				
				SPreStatement = connection.prepareStatement("select sum(decode(wftp.DRCR_FLAG,'D',-wftp.AMOUNT,+wftp.AMOUNT)) from wallet_fin_txn_posting wftp where wftp.ACCOUNT='"+searchRS.getString(3)+"' and TRUNC(TXN_STAMP)<=to_date('"+todate+"','dd/mm/yyyy')");
				SResultSet=SPreStatement.executeQuery();
				parameters.put("storename", " ");
				if (SResultSet.next()) {
					parameters.put("balance", SResultSet.getString(1));
					
				}
				
				SPreStatement.close();
				SResultSet.close();
				
				
				
				SPreStatement = connection.prepareStatement("select TO_CHAR(SUM(decode(DRCR_FLAG,'C',AMOUNT,0)),'999,999,999.99'),TO_CHAR(SUM(decode(DRCR_FLAG,'D',AMOUNT,0)),'999,999,999.99'),COUNT(decode(DRCR_FLAG,'D',AMOUNT)),COUNT(decode(DRCR_FLAG,'C',AMOUNT)) from WALLET_FIN_TXN_POSTING where ACCOUNT in ('"+searchRS.getString(3)+"','0"+searchRS.getString(3)+"') AND TRUNC(TXN_STAMP) between to_date('"+fromdate+"','dd/mm/yyyy') and to_date('"+todate+"','dd/mm/yyyy') AND TXN_TYPE in ('T','F')");
				SResultSet=SPreStatement.executeQuery();
				if (SResultSet.next()) {
					parameters.put("debittotal", SResultSet.getString(2));
					parameters.put("credittotal", SResultSet.getString(1));
					parameters.put("drcnt", SResultSet.getString(3));
					parameters.put("crcnt", SResultSet.getString(4));
				}
				
				SPreStatement.close();
				SResultSet.close();
				
				SPreStatement = connection.prepareStatement("select TO_CHAR((SUM(decode(DRCR_FLAG,'C',AMOUNT,0))-SUM(decode(DRCR_FLAG,'D',AMOUNT,0))),'999,999,999.99') from WALLET_FIN_TXN_POSTING where ACCOUNT='0"+searchRS.getString(3)+"' AND TRUNC(TXN_STAMP) between to_date('"+fromdate+"','dd/mm/yyyy') and to_date('"+todate+"','dd/mm/yyyy') AND TXN_TYPE='F'");

				SResultSet=SPreStatement.executeQuery();
				if (SResultSet.next()) {
					parameters.put("totalcommission", SResultSet.getString(1));	
				}
				
				SPreStatement.close();
				SResultSet.close();
				
				
			}
			
			parameters.put("start_date", fromdate);
			parameters.put("end_date", todate);
			
			rg.GeneratePdfReportWithJasperReports(connection,parameters,"statement_report","wallet_account_statement","pdf");
			String pathx = rb.getString("OUT_PATH");
			File fileToDownload = new File(pathx+"/wallet_account_statement.pdf");
			
			
			if(fileToDownload.exists() && !fileToDownload.isDirectory() && reportresult) //&& fileToDownload.isFile())
			{
				try
				{
					inputStream = new FileInputStream(fileToDownload);
			        fileName = fileToDownload.getName();
			        contentLength = fileToDownload.length();

			        result = SUCCESS;
				}
				catch(Exception e)
				{
					result="fail";
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
			result="fail";
			e.printStackTrace();
		}
		 finally {
				DBUtils.closeConnection(connection);
			}
		return result;
	}
}
