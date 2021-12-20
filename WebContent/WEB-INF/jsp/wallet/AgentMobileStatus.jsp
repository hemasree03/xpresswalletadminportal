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
		var url="${pageContext.request.contextPath}/<%=appName %>/agentservice.action";
		$("#form1")[0].action=url;
		$("#form1").submit();

	});

	$('#btn-submit').live('click', function () {
		$("#form1").validate(datavalidation);
		var url="${pageContext.request.contextPath}/<%=appName %>/mobileconfirmScreen.action"; 
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
 
$(document).ready(function() {
	
	 if("${responseJSON.mobile_status}"=="Active"){
		 $("#stastus").html("<div class='label label-success' >Active</div>");	
		 $("#stastus1").html("<div class='label label-important' >Deactive</div>");	
		}
	 else{
		 $("#stastus1").html("<div class='label label-success' >Active</div>");	
		 $("#stastus").html("<div class='label label-important' >Deactive</div>");
		}
	
});


</script>


 
<body class="fixed-top">
<form name="form1" id="form1" method="post" >
	
      <div id="content" class="span10"> 
	    <div> 
			<ul class="breadcrumb">
			  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
			 <li><a href="agentservice.action">Agent Services </a> <span
						class="divider"> &gt;&gt; </span></li>
			<li><a href="agentservice.action">Status Update</a> </li>
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
					<i class="icon-edit"></i>  Channel Status Details
					
					
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
				</div>
			</div> 
		
      				 
		<div class="box-content">
		
								
								
								<table width="950" border="0" cellpadding="5" cellspacing="1"  class="table table-striped table-bordered bootstrap-datatable ">
								<tr class="even" id="accno" >
							<td width="25%"><label for="From Date"><strong>Core Bank Account Number</strong></label></td>
							<td width="30%">${responseJSON.accountno}<input type="hidden" name="accountno"  id="accountno"   value="${responseJSON.accountno}"   /></td>
							<td ><strong>User Id</strong></td>
							<td >${responseJSON.userid}
							<input type="hidden" name="userid"  id="userid"   value="${responseJSON.userid}"   />
							</td>
							</tr>
							<tr class="even" id="accno" >
							<td width="25%"><label for="From Date"><strong>Wallet Account Number</strong></label></td>
							<td width="30%">${responseJSON.walletaccountno}<input type="hidden" name="walletaccountno"  id="walletaccountno"   value="${responseJSON.walletaccountno}"   /></td>
							<td ><strong>On-Boarded Date</strong></td>
							<td >${responseJSON.onboarddate}
							<input type="hidden" name="onboarddate"  id="onboarddate"   value="${responseJSON.onboarddate}"   />
							</td>
							
							<tr class="odd">
							<td width="25%"><label for="First Name"><strong>First Name</strong></label></td>
							<td width="25%">${responseJSON.fullname}<input type="hidden" name="fullname"  id="fullname"   value="${responseJSON.fullname}"   /></td>
							
								<td width="20%" ><label for="Mobile Number"><strong>Mobile Number</strong></label> </td>
					       <td  width="30%" >${responseJSON.telephone}<input type="hidden" value='${responseJSON.telephone}' style="width:155px;" maxlength="13" name="telephone" id="telephone"  /> 
 							 </td>
							</tr>
						 <tr class="even">
							<td><label for="Product"><strong>Product</strong></label></td>
							<td>${responseJSON.product} <input type="hidden" name="product"  id="product"   value="${responseJSON.product}"   />  </td>
							<td><strong>Product Description </strong></td>
							<td>${responseJSON.prodesc}</td>
							</tr>
							<tr class="even">
								<td width="25%"><label for="channel"><strong>Channel</strong></label></td>
								<td width="25%"><label for="Mobile"><strong>Mobile</strong></label></td>
								<td  width="25%"><label for="Email ID"><strong>Email ID</strong></label></td>
							<td  width="25%">${responseJSON.email} <input type="hidden" name="email"  id="email"   value="${responseJSON.email}"   />  </td>
							
								</tr>
							
							<tr class="even">
								<td width="25%"><label for="Limit Code"><strong>Current Status</strong></label></td>
								<td width="25%" id="stastus"></td>
								 <td width="25%"><label for="Limit Code"><strong>Update Status</strong></label></td>
								<td width="25%" id="stastus1"></td>
							</tr>
							
							</table>
								
							<input name="termilstatus" id="termilstatus" type="hidden"  required="true" class="field" value="<s:property value='#respData.limitcodedetails.status' />" />
                
                        </div>
                   

						   </div>	
						   
						   
			  </div>
			  <input type="hidden" name="langugae"  id="langugae"   value="English"  />
										<input type="hidden" name="refno"  id="refno"   value="${responseJSON.id}"  />
										<input type="hidden"    id="custid" name="custid" value="${responseJSON.custid}" />
										<input type="hidden"    id="actiontype" name="actiontype"  /> 
					<div>
			  	
			<input type="button" id="non-printable" class="btn btn-info" onclick="redirectHome();" value="BACK" />
					 <input type="button" class="btn btn-success" name="btn-submit" id="btn-submit" value="Confirm" width="100"  ></input> 
				</div>		 
				
			</div>
			
		  
		  </form>	
</body>
</html>
<!-- END PAGE -->