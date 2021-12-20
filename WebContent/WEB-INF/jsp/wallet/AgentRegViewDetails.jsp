<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<%@taglib uri="/struts-tags" prefix="s"%>  
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<%String ctxstr = request.getContextPath(); %>
<% String appName= session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %> 
 

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
.errors {
color: red;
}
</style>
<script type="text/javascript">


var userLinkData ='${USER_LINKS}';
var jsonLinks = jQuery.parseJSON(userLinkData);
var linkIndex = new Array();
var linkaction = new Array();
var linkStatus = new Array();



$(document).ready(function () { 
	
	var num="";
	
	$.each(jsonLinks, function(index, v) {
	linkIndex[index] = index;
	linkaction[index] = v.name;
	linkStatus[index] = v.status;
	

	
	num=index;
	
	

	
}); 
	
	
	for(ii=0;ii<=num;ii++){
		var v=linkaction[ii];
		//alert(v);
		$('#'+v).removeAttr("disabled");
		
		 
	 }  
	
}); 

$(function() { 
	
	
	
	if("${responseJSON.status}"=="Active"){
		$("#userstatus").html("<div class='label label-success' style='width:40px' >Active</div>");	
	}else if("${responseJSON.status}"=="Inactive"){
		$("#userstatus").html("<div class='label label-important' style='width:40px' >Inactive</div>");
		$('#active').attr('disabled','disabled');
		$('#pinreset').attr('disabled','disabled');
		$('#password').attr('disabled','disabled');
	}else{
		$("#userstatus").html("<div class='label label-important' style='width:40px' >Deactive</div>");	
	}
	
	if("${responseJSON.mobile_status}"=="Active"){
		$("#mobile").html("<div class='label label-success' style='width:40px' >Active</div>");	
	}else{
		$("#mobile").html("<div class='label label-important' style='width:40px' >Deactive</div>");	
	}
	
	if("${responseJSON.ussd_status}"=="Active"){
		$("#ussd").html("<div class='label label-success' style='width:40px'>Active</div>");	
	}else{
		$("#ussd").html("<div class='label label-important' style='width:40px' >Deactive</div>");	
	}
	
	if("${responseJSON.pos_status}"=="Active"){
		$("#pos").html("<div class='label label-success' style='width:40px' >Active</div>");	
	}else{
		$("#pos").html("<div class='label label-important' style='width:40px' >Deactive</div>");	
	}
	
	if("${responseJSON.mobile_status}"=="Active"){
		$("#mobile").html("<div class='label label-success' style='width:40px' >Active</div>");	
	}else{
		$("#mobile").html("<div class='label label-important' style='width:40px' >Deactive</div>");	
	}
	
	if($("#status").val()=="Deactivated"){
		$("#statusagn").text("Active Agent");
		
	}else{
		$("#statusagn").text("Disable Agent");
	}
	
	$('#active').on('click', function(){
		if("${responseJSON.status}"!="Inactive"){
			
		
		$("#actiontype").val("${responseJSON.application}STATUS");
		$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/agentregmodifydetails.action";
	 	$("#form1").submit();
	  return true;
		}
	});
	
$('#pinreset').on('click', function(){
	if("${responseJSON.status}"!="Inactive"){
	$("#actiontype").val("${responseJSON.application}PIN");
		$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/agentregmodifydetails.action";
	 	$("#form1").submit();
	  return true; 
	}
	});
$('#password').on('click', function(){
	if("${responseJSON.status}"!="Inactive"){
	$("#actiontype").val("${responseJSON.application}PASSWORD");
		$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/agentregmodifydetails.action";
	 	$("#form1").submit();
	  return true; 
	}
	});
	
	
$('#mobilestatus').on('click', function(){
	
	$("#actiontype").val("${responseJSON.application}MOBSTATUS");
		$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/agentregmodifydetails.action";
	 	$("#form1").submit();
	  return true; 
	});
	
	
$('#ussdstatus').on('click', function(){
	
	$("#actiontype").val("${responseJSON.application}USSDSTATUS");
		$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/agentregmodifydetails.action";
	 	$("#form1").submit();
	  return true; 
	});
	
$('#posstatus').on('click', function(){
	
	$("#actiontype").val("${responseJSON.application}POSSTATUS");
		$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/agentregmodifydetails.action";
	 	$("#form1").submit();
	  return true; 
	});	



  
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/agentservice.action'; 
		$("#form1").submit();
		
		 $(this).prop('disabled', true);
			$("#btn-submit").hide();
		return true;
	}); 
});

</script> 
</head> 
<body>
<form name="form1" id="form1" method="post"> 
		
			<div id="content" class="span10">  
			    <div> 
					<ul class="breadcrumb">
					  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
					   <li> <a href="#">Wallet Agent View</a>  </li> 
 					</ul>
				</div>  

				<table>
					<tr>
						<td colspan="3">
							<div class="messages" id="messages"><s:actionmessage /></div>
							<div class="errors" id="errors"><s:actionerror /></div>
						</td>
					</tr>
				</table>
			
  <div class="row-fluid sortable"> 
	<div class="box span12"> 
			<div class="box-header well" data-original-title>
					<i class="icon-edit"></i>Wallet Agent Details
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
				</div>
			</div>  
				<div class="box-content">
					<fieldset> 
						<table width="950" border="0" cellpadding="5" cellspacing="1" 
									class="table table-striped table-bordered bootstrap-datatable " id="user-details"> 
						   
						<tr class="odd">
							<td width="25%"><label for="First Name"><strong>First Name</strong></label></td>
							<td width="25%">${responseJSON.fullname}<input type="hidden" name="fullname"  id="fullname"   value="${responseJSON.fullname}"   /></td>
							<td width="20%"><label for="From Date"><strong>Middle Name</strong></label></td>
							<td width="30%">${responseJSON.middlename} <input type="hidden" name="middlename"  id="middlename"   value="${responseJSON.middlename}"   />  </td>
						</tr> 
							
							
						<tr class="even">
						    <td width="25%"><label for="Last Name"><strong>Last Name</strong></label> </td>
							<td width="20%">${responseJSON.lastname} <input type="hidden" name="lastname"  id="lastname"   value="${responseJSON.lastname}"   />  </td>
							<td  width="20%"><label for="To Date"><strong>Date Of Birth</strong></label></td>
							<td  width="30%">${responseJSON.dateofbirth} <input type="hidden" name="dateofbirth"  id="dateofbirth"   value="${responseJSON.dateofbirth}"   />  </td>
						</tr>
							 
						<tr class="odd">
							<td  width="25%"><label for="Email ID"><strong>Email ID</strong></label></td>
							<td  width="25%">${responseJSON.email} <input type="hidden" name="email"  id="email"   value="${responseJSON.email}"   />  </td>
							<td width="20%" ><label for="Mobile Number"><strong>Mobile Number</strong></label> </td>
					       <td  width="30%" >${responseJSON.telephone}<input type="hidden" value='${responseJSON.telephone}' style="width:155px;" maxlength="13" name="telephone" id="telephone"  /> 
 							 </td>
						</tr>
						<tr class="even">
						     </td> 
								<td  width="25%"><strong>Gender</strong></td>
								<td  width="25%">${responseJSON.gender} 
								<input type="hidden" value='${responseJSON.gender}' style="width:155px;"  name="gender" id="gender"  /></td>
						       <td  width="20%"><label for="Marital Status"><strong>Marital Status</strong></label></td>
							<td  width="30%">${responseJSON.martStatus} <input type="hidden" name="martStatus"  id="martStatus"   value="${responseJSON.martStatus}"   />  </td>
						</tr>
 						
 						 <tr class="odd">
						     </td> 
								<td  width="25%"><strong>Merchant Category</strong></td>
								<td  width="25%">${responseJSON.mcc} 
								<input type="hidden" value='${responseJSON.mcc}' style="width:155px;"  name="mcc" id="mcc"  /></td>
						       <td  width="20%"><label for="Businessname"><strong>Business Name</strong></label></td>
							<td  width="30%">${responseJSON.bsnname} <input type="hidden" name="bsnname"  id="bsnname"   value="${responseJSON.bsnname}"   />  </td>
						</tr>
						
						 <tr class="even">
							<td><label for="Product"><strong>Product</strong></label></td>
							<td>${responseJSON.product} <input type="hidden" name="product"  id="product"   value="${responseJSON.product}"   />  </td>
							<td><strong>Product Description </strong></td>
							<td>${responseJSON.prodesc}</td>
							</tr>
							
							<tr class="even">
							<td><label for="Product"><strong>User Status</strong></label></td>
							<td id="userstatus">  </td>
							<td><label for="Product"><strong>Onboard Date</strong></label></td>
							<td>${responseJSON.onboarddate}</td>
							</tr>
							<tr class="odd">
						     </td> 
								<td  width="25%"><strong>Wallet Account Number</strong></td>
								<td  width="25%">${responseJSON.walletaccountno} </td>
						       <td  width="20%"><strong>Wallet Account Balance</strong></td>
							<td  width="30%"> ${responseJSON.walletbalance} </td>
						</tr>
						
							
 												
				 </table>
				</fieldset> 
				
				
				 <div >
			<a href="#" id="active" class="btn btn-danger ajax-link" disabled>&nbsp;<span id="statusagn"></span></a>&nbsp;
			<a href="#" id="pinreset" class="btn btn-success ajax-link" disabled>&nbsp;Pin Reset</a>	&nbsp;
			<a href="#" id="password" class="btn btn-success ajax-link" disabled>&nbsp;Password Reset</a>				 
		</div> 
		</div>
		</div> 
		<br />
		<div class="row-fluid sortable"> 
		<div class="box span12"> 
			<div class="box-header well" data-original-title>
					<i class="icon-edit"></i>Channel Status
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
				</div>
			</div>  
				<div class="box-content">
				<fieldset> 
						 <table width="100%" class="table table-striped table-bordered " 
							id="DataTables_Table_0" >
							<thead  >
								<tr >
									<th width="30%">Channel</th>
									<th width="30%">Status</th>
									<th width="30%">  Action</th>
									<!-- <th width="10%">Action</th> -->
									
								</tr>
								
							</thead>
									<tr class="even"  >
										<td><strong>Mobile</strong></label></td>
										<td id="mobile"></td>
								<td><a class='btn btn-warning' href='#' id='mobilestatus' index="+i+" val="+i+" title='Status Active/Deactive' data-rel='tooltip' onclick='viewfun("+i+")'> <i class='icon icon-lock icon-white'></i></a></td>
									</tr> 
									<tr class="even"  >
										<td><strong>USSD</strong></label></td>
										<td id="ussd"></td>
 								<td><a class='btn btn-warning' href='#' id='ussdstatus' index="+i+" val="+i+" title='Status Active/Deactive' data-rel='tooltip' onclick='viewfun("+i+")'> <i class='icon icon-lock icon-white'></i></a></td>		
									</tr>   
									<tr class="even"  >
										<td><strong>POS</strong></label></td>
										<td id="pos"></td>
								 		<td><a class='btn btn-warning' href='#' id='posstatus' index="+i+" val="+i+" title='Status Active/Deactive' data-rel='tooltip' onclick='viewfun("+i+")'> <i class='icon icon-lock icon-white'></i></a></td>
									</tr>     
						</table>
				</fieldset>
				
				</div>
				</div>
	    
				
	  </div>
	  </div> 
	  
	<input type="hidden"    id="custid" name="custid" value="${responseJSON.custid}" /> 
						<input type="hidden"    id="actiontype" name="actiontype"  /> 
										<input type="hidden" name="langugae"  id="langugae"   value="English"  />
			
	
		
	</div> 	 
 </form>

</body> 
</html>