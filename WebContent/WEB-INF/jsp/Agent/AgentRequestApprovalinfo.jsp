 <!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>CEVA </title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="">
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%@taglib uri="/struts-tags" prefix="s"%>  
<%String ctxstr = request.getContextPath(); %>
<% String appName= session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>
	
	
<style type="text/css">
.messages {
  font-weight: bold;
  color: green;
  padding: 2px 8px;
  margin-top: 2px;
}

.errors{
  font-weight: bold;
  color: red;
  padding: 2px 8px;
  margin-top: 2px;
}
label.error {
	  font-weight: bold;
	  color: red;
	  padding: 2px 8px;
	  margin-top: 2px;
}
.errmsg {
color: red;
}
 
</style>    
<script type="text/javascript" >


$(document).ready(function() {
	$('#btn-submit').live('click', function () { 
	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/agentrequestinfoapprovalack.action';
	$("#form1").submit();
	return true;
	});
});



		 
function redirectAct(){
	if($('#requesttype').val()=="Reject" && $('#reason').val()==""){
		$("#errormsg").html("<font colour='red'>Please Enter Reason </font>");
		return false;
	}else{
	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/agentrequestapproval.action';
	$("#form1").submit();
	return true;
	}
}


function fun(id){
	//alert(id);
	$('#reasontg').css("display", "none");
	$("#requesttype").val(id);
	if(id=="Reject"){
		$('#reasontg').css("display", "");
	}
	
}

 
</script> 
		
</head>

<body>
	<form name="form1" id="form1" method="post" action="">	
	 
	<div id="content" class="span10">   
		 
			  <div>
				 <ul class="breadcrumb">
					<li><a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span></li>
					<li><a href="#">Wallet Transaction Reversal Request Approval Confirmation</a></li>
				</ul>
			</div>
			
		
		<div class="row-fluid sortable">
			<div class="box span12" id="groupInfo">
				<div class="box-header well" data-original-title>Wallet Transaction Information
					<div class="box-icon"> 
						<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
						<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a> 
					</div>
				</div> 
				
				
				
				
				<div class="box-content"> 
				
				
				<table width="950"  border="0" cellpadding="5" cellspacing="1"
					class="table table-striped table-bordered bootstrap-datatable " >
					  <tr >
							<td width="20%" ><strong><label for="Customer ID">Payment Reference Number</label></strong></td>
							<td width="30%" >${responseJSON.VIEW_LMT_DATA.PAYMENTREFERENCE}
							<input type="hidden" name="paymentrefno"  id="paymentrefno" value="${responseJSON.VIEW_LMT_DATA.PAYMENTREFERENCE}" />
							<td width="20%" ><strong><label for="Customer Name">Amount</label></strong></td>
							<td width="30%" >${responseJSON.VIEW_LMT_DATA.TRNS_AMT}
							<input type="hidden" name="txnamt"  id="txnamt" value="${responseJSON.VIEW_LMT_DATA.TRNS_AMT}" /></td>
							
						</tr>
						<tr >
							<td><strong><label for="Account Number">User Id</label></strong></td>
							<td>${responseJSON.VIEW_LMT_DATA.USERID}
							<input type="hidden" name="userid"  id="userid" value="${responseJSON.VIEW_LMT_DATA.USERID}" /></td>
							<td><strong><label for="Alias Name">Channel</label></strong></td>
							<td> ${responseJSON.VIEW_LMT_DATA.CHANNEL}
							<input type="hidden" name="channel"  id="channel" value="${responseJSON.VIEW_LMT_DATA.CHANNEL}" /></td>
							
						</tr>
						<tr >
							<td><strong><label for="Account Type">Transaction Type</label></strong></td>
							<td>${responseJSON.VIEW_LMT_DATA.REQUEST_TYPE}
							<input type="hidden" name="txntype"  id="txntype" value="${responseJSON.VIEW_LMT_DATA.REQUEST_TYPE}" /></td>
							<td><strong><label for="Institute">Transaction Date and Time</label></strong></td>
							<td>${responseJSON.VIEW_LMT_DATA.TRANS_DATE}
							<input type="hidden" name="txndatetime"  id="txndatetime" value="${responseJSON.VIEW_LMT_DATA.TRANS_DATE}" /></td>
						</tr>

						 <tr >
								
								<td><strong><label for="branchcode">Wallet Reference number</label></strong></td>
								<td>${responseJSON.VIEW_LMT_DATA.WALLETNO}
								<input type="hidden" name="batchid"  id="batchid" value="${responseJSON.VIEW_LMT_DATA.WALLETNO}" /></td>
						
								<td><strong><label for="Date Created">Wallet Account Number</label></strong></td>
								<td>${responseJSON.VIEW_LMT_DATA.ACCT_NO}
								<input type="hidden" name="waccountno"  id="waccountno" value="${responseJSON.VIEW_LMT_DATA.ACCT_NO}" /></td>
								
						 </tr>
						 <tr>
						 <td colspan="4"><strong>${responseJSON.VIEW_LMT_DATA.DETAILS}</strong></td>
						 </tr>
						  <tr >
								
								<td><strong><label for="branchcode">Action Type</label></strong></td>
								<td>${responseJSON.VIEW_LMT_DATA.ACTION_TYPE}
								<input type="hidden" name="actiontype"  id="actiontype" value="${responseJSON.VIEW_LMT_DATA.ACTION_TYPE}" /></td>
						
								<td></td>
								<td></td>
								
						 </tr>
						 <tr >
								<td><strong><label for="Date Created">Remarks</label></strong></td>
								<td>
								${responseJSON.VIEW_LMT_DATA.REMARK}
								</td>
								<td></td>
								<td></td>
						 </tr>
						 <tr >
								
								<td colspan="4">
								<strong>Authorize</strong>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="radio" name="request" id="Approval" onclick="fun(this.id)" checked> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
								<strong>Reject</strong>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="radio" name="request" id="Reject" onclick="fun(this.id)">
								</td>
								
						 </tr>
						 
						  <tr style="display:none" id="reasontg">
								<td><strong><label for="Date Created"><strong>Reason</strong><font color="red">*</font></label></strong></td>
								<td>
								<textarea rows="10" cols="5" name="reason"  id="reason"></textarea>
								</td>
								<td></td>
								<td></td>
						 </tr>
						
				</table>
				<input type="hidden" id="requesttype" name="requesttype" value="Approval"/>
				
				<input type="hidden" name="Narration"  id="Narration" value="${responseJSON.VIEW_LMT_DATA.DEBITNARRATION}" />
				<input type="hidden" name="refno"  id="refno" value="${responseJSON.VIEW_LMT_DATA.P_SEQNO}" />
					
						
				</div>
			</div>
		</div> 
	
	<input name="application" type="hidden" id="application"   value="${responseJSON.VIEW_LMT_DATA.TRANS_TYPE}"  />
		 <div class="form-actions" align="left"> 
				<input type="button" id="non-printable" class="btn btn-info" onclick="redirectAct();" value="Back" />
				<input type="button" class="btn btn-success" name="btn-submit" id="btn-submit" value="Confirm" width="100"  >
			</div> 
			 
		</div> 
	 

  
   
  
</form>
 
<script type="text/javascript" src='${pageContext.request.contextPath}/js/jquery.dataTables.min.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/datatable-add-scr.min.js'></script> 
</body>
</html>
 