<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>NBK Salary Processing</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="">
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%String ctxstr = request.getContextPath(); %>
<%String appName = session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>
<%@taglib uri="/struts-tags" prefix="s"%> 
<s:set value="responseJSON" var="respData"/>
 <style type="text/css">
label.error {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}

.errmsg {
	color: red;
}

.messages {
	font-weight: bold;
	color: green;
	padding: 2px 8px;
	margin-top: 2px;
}

.errors {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}
</style>
<script type="text/javascript">

	

$(document).ready(function() {

	$('#non-printable').live('click', function () {
		$("form").validate().cancelSubmit = true;
		var url="${pageContext.request.contextPath}/<%=appName %>/disputerequest.action";
		$("#form1")[0].action=url;
		$("#form1").submit();

	});

	$('#btn-submit').live('click', function () {
		$("#form1").validate(datavalidation);
		var url="${pageContext.request.contextPath}/<%=appName %>/procceddetailsack.action"; 
		$("#form1")[0].action=url;
		$("#form1").submit();

		
	});
});

var datavalidation = {
 		
 		rules : {
 			
 			limitDesc: { required : true}
			
 		},		
 		messages : {
 			
 			
					 limitDesc : { 
 							required : "Please Enter Branch Name."
					 }
			
					 
 		} 
 };

</script>


 
<body class="fixed-top">
<form name="form1" id="form1" method="post" >
	
      <div id="content" class="span10"> 
	    <div> 
			<ul class="breadcrumb">
			  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
			 <li><a href="clusterCreation.action">Dispute Request</a> <span
						class="divider"></span></li>
			<li><a href="clusterCreation.action">Proceed details</a> </li>
 			</ul>
		</div>  
	 	<table height="3">
				 <tr>
					<td colspan="3">
						<div class="messages" id="messages"> <s:actionmessage /></div>
						<div class="errors" id="errors"> <s:actionerror /></div>
					</td>
				</tr>
			 </table> 
				
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" >
					<i class="icon-edit"></i>Proceed Details 
					
					
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
				</div>
			</div> 
		
      				 
		<div class="box-content">
		
								
								<table width="950" border="0" cellpadding="5" cellspacing="1"  class="table table-striped table-bordered bootstrap-datatable ">
								<tr class="even">
								<td width="25%"><label for="Limit Code"><strong>Request Id</strong></label></td>
								 <td width="25%">${REQUEST_ID}
								 <input name="REQUEST_ID" id="REQUEST_ID" type="hidden"  required="true" class="field" value="${REQUEST_ID}" />
								 </td>
								
								 <td width="25%"><label for="Limit Description"><strong>Mobile Number<font color="red">*</font></strong></label></td>
							<td width="25%">${MOBILE_NUMBER}
							<input name="MOBILE_NUMBER" id="MOBILE_NUMBER" type="hidden"  required="true" class="field" value="${MOBILE_NUMBER}" /><span id="bin_err1" class="errmsg"></span></td>
							
							</tr>
							<tr class="even">
								<td width="25%"><label for="Limit Code"><strong>Wallet Account</strong></label></td>
								<td width="25%">${WALLET_ACC}
								<input name="WALLET_ACC" id="WALLET_ACC" type="hidden"  required="true" class="field" value="${WALLET_ACC}" />
								</td>
								 <td width="25%"><label for="Limit Description"><strong>Payment Reff No</strong></label></td>
								 <td width="25%">${PAYMENT_REF_NO}
								 <s:property value='#respData.limitcodedetails.LGA' /> 
								 <input name="PAYMENT_REF_NO" id="PAYMENT_REF_NO" type="hidden"  required="true" class="field" value="${PAYMENT_REF_NO}" />
								 </td>
							</tr>
							<tr class="even">
								<td width="25%"><label for="Limit Code"><strong>Transaction Date</strong></label></td>
								<td width="25%">${TRANS_DATE}
								<input name="TRANS_DATE" id="TRANS_DATE" type="hidden"  required="true" class="field" value="${TRANS_DATE}" />
								</td>
								 <td width="25%"><label for="Limit Description"><strong>Amount</strong></label></td>
								 <td width="25%">${AMOUNT}
								<%--  <s:property value='#respData.limitcodedetails.AMOUNT' />  --%>
								 <input name="AMOUNT" id="PAYMENT_REF_NO" type="hidden"  required="true" class="field" value="${AMOUNT}" />
								 </td>
							</tr>
							
								<tr class="even">
								<td width="25%"><label for="Limit Code"><strong>Registration Status</strong></label></td>
								<td width="25%">${REG_STATUS}
								<input name="REG_STATUS" id="REG_STATUS" type="hidden"  required="true" class="field" value="${REG_STATUS}" />
								</td>
								 <td width="25%"><label for="Limit Description"><strong>Required date</strong></label></td>
								 <td width="25%">${REQ_DATE}
							
								 <input name="REQ_DATE" id="REQ_DATE" type="hidden"  required="true" class="field" value="${REQ_DATE}" />
								 </td>
							</tr>
							<tr class="even">
								<td width="25%"><label for="Limit Code"><strong>Reason</strong></label></td>
								<td width="25%">${REASON}
								<input name="REASON" id="REASON" type="hidden"  required="true" class="field" value="${REASON}" />
								</td>
							
							<td></td>
							<td></td>
							</tr>
							
							</table>
								
							<input name="termilstatus" id="termilstatus" type="hidden"  required="true" class="field" value="${termilstatus}" />
                
                        </div>
                   

						   </div>	
						   
						   
			  </div>
			  
			  	
			  
			<input type="button" id="non-printable" class="btn btn-info" onclick="redirectHome();" value="BACK" />
					 <input type="button" class="btn btn-success" name="btn-submit" id="btn-submit" value="CONFIRM" width="100"  ></input> 
						 
				
			</div>
			</div>
		  
		  </form>	
</body>
</html>
<!-- END PAGE -->