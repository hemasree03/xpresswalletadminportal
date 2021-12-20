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

$(function() {  
	
	if("${responseJSON.requesttype}"=="Reject"){
		$('#reasontg').css("display", "");
	}

	
  
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){
		
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/agentbonusapprovalack.action'; 
		$("#form1").submit();
		
		 $(this).prop('disabled', true);
			$("#btn-submit").hide();
		return true;
		
	}); 
});


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
<div id="content" class="span10">  
			    <div> 
					<ul class="breadcrumb">
					  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
					  <li> <a href="#">Agent Bonus Approval Confirmation</a>  </li> 
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
<form name="form1" id="form1" method="post"> 
<div id="errormsg" class="errores"></div>
		<div class="row-fluid sortable"> 
			<div class="box span12"> 
					<div class="box-header well" data-original-title>
							<i class="icon-edit"></i>Agent Details
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
							<td  width="25%"><label for="Email ID"><strong>Email ID</strong></label></td>
							<td  width="25%">${responseJSON.email} <input type="hidden" name="email"  id="email"   value="${responseJSON.email}"   />  </td>
							
						</tr>
							 
						<tr class="odd">
							<td width="20%" ><label for="Mobile Number"><strong>Mobile Number</strong></label> </td>
					       <td  width="30%" >${responseJSON.telephone}<input type="hidden" value='${responseJSON.telephone}' style="width:155px;" maxlength="13" name="telephone" id="telephone"  /> 
 							 </td>
						
								<td  width="25%"><strong>Wallet Account Number</strong></td>
								<td  width="25%">${responseJSON.walletaccountno} 
								<input type="hidden" value='${responseJSON.walletaccountno}'   name="walletaccountno" id="walletaccountno"  /></td>
						      
						</tr>
						
						
							
 												
				 </table>
				 
				  
								
								
				</fieldset> 
				</div>
				</div> 
				<div class="row-fluid sortable"> 
				<div class="box span12"> 
					<div class="box-header well" data-original-title>
							<i class="icon-edit"></i>Agent Bonus
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
							<td width="25%"><label for="First Name"><strong>Bonus Amount</strong></label></td>
							<td width="25%">${responseJSON.bonusamt}<input type="hidden" name="bonusamt"  id="bonusamt"  value="${responseJSON.bonusamt}"    /></td>
							<td width="20%"><label for="From Date"><strong>Payment Reference Number</strong></label></td>
							<td width="30%">${responseJSON.paymentref}<input type="hidden" name="paymentref"  id="paymentref"  value="${responseJSON.paymentref}"    />  </td>
						</tr> 
						<tr class="odd">
							<td width="25%"><label for="First Name"><strong>Bonus Month</strong></label></td>
							<td width="25%">${responseJSON.monthyear}<input type="hidden" name="monthyear"  id="monthyear"  value="${responseJSON.monthyear}"    /></td>
							<td width="25%"></td>
							<td width="25%"></td>
						</tr>
							
						<tr class="odd">
							<td width="25%"><label for="First Name"><strong>Maker Id</strong></label></td>
							<td width="25%">${responseJSON.makerid}<input type="hidden" name="makerid"  id="makerid"  value="${responseJSON.makerid}"    /></td>
							<td width="20%"><label for="From Date"><strong>Maker Date</strong></label></td>
							<td width="30%">${responseJSON.makerdt}<input type="hidden" name="makerdt"  id="makerdt"  value="${responseJSON.makerdt}"    />  </td>
						</tr> 	
						
						<tr >
								
								<td colspan="4">
								<strong>${responseJSON.requesttype}</strong>
								</td>
								
						 </tr>
						 
						  <tr style="display:none" id="reasontg">
								<td><strong><label for="Date Created"><strong>Reason</strong></label></strong></td>
								<td>
								${responseJSON.reason}
								</td>
								<td></td>
								<td></td>
						 </tr>
						
							
 												
				 </table>
				 <input type="hidden" id="requesttype" name="requesttype" value="${responseJSON.requesttype}" />
				  <input type="hidden" id="reason" name="reason" value="${responseJSON.reason}" />
				   <input type="hidden" id="referenceno" name="referenceno" value="${responseJSON.referenceno}"/>
				  
								
								
				</fieldset> 
				</div>
				</div> 
					
			</div> 	 	
			<div > 
			<a href="#" id="btn-back" class="btn btn-danger ajax-link">Home </a>&nbsp;
			<a href="#" id="btn-submit" class="btn btn-success ajax-link" >&nbsp;Confirm</a>	
			<span id ="error_dlno" class="errors"></span>	 
		</div> 	
		</div> 	
		
</form>	

		
		
	</div> 

</body> 
</html>