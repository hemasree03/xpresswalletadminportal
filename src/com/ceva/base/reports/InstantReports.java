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

import com.ceva.base.common.utils.DBConnector;
import com.ceva.util.DBUtils;
import com.opensymphony.xwork2.ActionSupport;

public class InstantReports extends ActionSupport{
	
	private static Logger logger = Logger.getLogger(InstantReports.class);
	 
	private InputStream inputStream;
    private String fileName;
    private String fromdate;
    private String todate;
    private String type;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
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
		String filename="";
		ResultSet searchRS = null;
		PreparedStatement pstmt = null;
		ResourceBundle rb = ResourceBundle.getBundle("configurations");
		File fileToDownload =null;
    	try {
    		
    		connection = DBConnector.getConnection();
			logger.debug("Connection is null [" + connection == null + "]");
			String pathx = rb.getString("OUT_PATH");
			
			if(type.equalsIgnoreCase("eodreport")) {
			
			System.out.println("from date :: "+fromdate);
			System.out.println("wallet type :: "+type);
			String fromdates=fromdate;
			fromdate=fromdate.replaceAll("/", "");
			
			filename="eod_report-"+fromdate;
			
			String delim="\'\"'||";
			String delim1="||\'\"'";
		
			
			  System.out.println("delim"+delim);
			//entityQry = "select wt.TXNID,wt.TXN_STAMP,"+"\" " +tp.txn_Ref_no+"\""+","+"\""+ wt.ext_txn_Ref_no+"\""+","+ "wad.acct_no,wad.acct_name,tp.user_id,wt.SERVICECODE,wt.CHANNEL,decode(substr(wt.DR_ACCOUNT,1,1),'5','Debit','Credit'),LTRIM(TO_CHAR(NVL(wt.amount,0)),0),LTRIM(TO_CHAR(NVL(wt.txn_amt,0)),0),wt.FEE_AMT,tp.agentcomm,tp.bankcommission,tp.cevacommission,tp.NIP,tp.Paybill,tp.superagent,tp.VAT,nvl(WT.CHARGE_TO,'Customer') AS CHARGE_TO ,(SELECT acm.W_PRD_CODE  FROM agent_customer_master ACM  WHERE  ACM.ID=ACI.cust_id) AS PRD_CODE " + 
					
			entityQry = "select wt.TXNID,wt.TXN_STAMP,"+delim+"tp.txn_Ref_no "+delim1+", "+delim+"wt.ext_txn_Ref_no "+delim1+",wad.acct_no,wad.acct_name,tp.user_id,wt.SERVICECODE,wt.CHANNEL,decode(substr(wt.DR_ACCOUNT,1,1),'5','Debit','Credit'),LTRIM(TO_CHAR(NVL(wt.amount,0)),0),LTRIM(TO_CHAR(NVL(wt.txn_amt,0)),0),wt.FEE_AMT,tp.agentcomm,tp.bankcommission,tp.cevacommission,tp.NIP,tp.Paybill,tp.superagent,tp.VAT,nvl(WT.CHARGE_TO,'Customer') AS CHARGE_TO ,(SELECT acm.W_PRD_CODE  FROM agent_customer_master ACM  WHERE  ACM.ID=ACI.cust_id) AS PRD_CODE " + 					
			" from  " + 
					"(select user_id,txn_Ref_no, " + 
					"nvl(sum((case when substr(ACCOUNT,1,1)='0' then wft.AMOUNT else 0 end)),0) as agentcomm, " + 
					"nvl(sum((case when ACCOUNT='9866094003' then wft.AMOUNT else 0 end)),0) as bankcommission, " + 
					"nvl(sum((case when ACCOUNT='9866094002' then wft.AMOUNT else 0 end)),0) as cevacommission, " + 
					"nvl(sum((case when ACCOUNT='9866094019' then wft.AMOUNT else 0 end)),0) as NIP, " + 
					"nvl(sum((case when ACCOUNT='9866094007' then wft.AMOUNT else 0 end)),0) as Paybill, " + 
					"nvl(sum((case when ACCOUNT='9866094004' then wft.AMOUNT else 0 end)),0) as superagent, " + 
					"nvl(sum((case when ACCOUNT='9866094934' then wft.AMOUNT else 0 end)),0) as VAT  " + 
					"from wallet_fin_txn_posting wft  group by user_id,txn_Ref_no ) tp, " + 
					"wallet_fin_txn wt, " + 
					"wallet_acct_data wad,agent_contact_info aci  where tp.user_id=aci.mobile_number and  aci.cust_id=wad.cust_id and wt.txn_Ref_no=tp.txn_Ref_no and TRUNC(wt.TXN_STAMP)=to_date('"+fromdates+"','dd/mm/yyyy') order by wt.TXNID";
			
			String header="Sequence Number,Transaction Date and Time,Wallet Reference Numer,External Reference Number,Wallet Account Number,First Name,User id,Service Code,Channel,DR/CR Flag,Transaction Amount,Amount,Fee,Agent Commission,Xpress Commission,Ceva Commission,NIP Commission,Paybill Commission,Super Agent Commission,VAT,CHARGE_TO,PRD_CODE";
			
			CSVReportGeneration.perform(filename, connection, entityQry,header);
			fileToDownload = new File(pathx+filename+".csv");
			
			}
			
			
			
			
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
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		DBUtils.closePreparedStatement(pstmt);
    		DBUtils.closeResultSet(searchRS);
			DBUtils.closeConnection(connection);
		}
    	
    	return result;
	}
}
