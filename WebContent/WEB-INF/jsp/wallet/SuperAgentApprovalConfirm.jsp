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
	
	if("${responseJSON.decision}"== "Reject"){
		
		$('#locstatus1').css("display","");
		}else{
		
		
		$('#locstatus1').css("display","none");
		}
 
  
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){
		
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/superagentapprovalack.action'; 
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
					   <li> <a href="#">Agent Registration</a>  </li> 
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
					<i class="icon-edit"></i>Agent Registration Confirmation
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
				</div>
			</div>  
				<div class="box-content">
					<fieldset> 
					<table width="950" border="0" cellpadding="5" cellspacing="1" 
									class="table table-striped table-bordered bootstrap-datatable " id="user-details"> 
						 <tr class="even">
							<td width="25%"><label for="From Date"><strong>Reference Number</strong></label></td>
							<td width="25%">${responseJSON.customercode}
							<input type="hidden" name="customercode"  id="customercode"  value="${responseJSON.customercode}"   /></td>
							<td width="25%"></td>
						    <td width="25%"></td>
							
							</tr>  
						<tr class="even">
							<td width="25%"><label for="From Date"><strong>First Name</strong></label></td>
							<td width="25%">${responseJSON.fullname}
							<input type="hidden" name="fullname"  id="fullname"   value="${responseJSON.fullname}"  /></td>
							<td width="25%"><label for="From Date"><strong>Middle Name</strong></label></td>
						    <td width="25%">${responseJSON.middlename}
						    <input type="hidden" name="middlename"  id="middlename"  value="${responseJSON.middlename}"    /></td>
							
							</tr>
						<tr class="even">
						<td width="25%"><label for="From Date"><strong>Last Name</strong></label></td>
						    <td width="25%">${responseJSON.lastname}
						    <input type="hidden" name="lastname"  id="lastname"   value="${responseJSON.lastname}"  /></td>
						<td  ><label for="To Date"><strong>Date Of Birth</strong></label> </td>
							<td>${responseJSON.dob}
							<input type="hidden"  id="dob" name="dob"  value="${responseJSON.dob}" />
							</td>
						
							
							  
						</tr>
						<tr class="even">
						
							<td ><label for="From Date"><strong>Email ID</strong></label></td>
							<td >${responseJSON.email}
							<input type="hidden" name="email"  id="email"    value="${responseJSON.email}"  />  </td>
						   <td><label for="To Date"><strong>Mobile Number</strong></label> </td>
					       <td >${responseJSON.telephone}
					    
								<input type="hidden"  name="telephone" id="telephone" value="${responseJSON.telephone}" />
 							
						     
						   </td> 
						    
 						</tr>
 						<tr class="even" >
 						 <td><strong>Gender</strong></td>
						      <td>
						      ${responseJSON.gender}
					    
								<input type="hidden"  name="gender" id="gender" value="${responseJSON.gender}" />
						    
						      </td>
							<td><strong>Marital Status</strong></td>
						      <td>
						      ${responseJSON.marital}
					    
								<input type="hidden"  name="marital" id="marital" value="${responseJSON.marital}" />
						    
						      </td>
						</tr>
						<tr class="even" >
 						 <td><strong>Merchant Category</strong></td>
						      <td>
						      ${responseJSON.merchanttype}
					    
								<input type="hidden"  name="merchanttype" id="merchanttype" value="${responseJSON.merchanttype}" />
						    
						      </td>
							<td><strong>Business Name</strong></td>
						      <td>
						      ${responseJSON.businessname}
					    
								<input type="hidden"  name="businessname" id="businessname" value="${responseJSON.businessname}" />
						    
						      </td>
						</tr>
						<tr class="even">
						
							<td ><label for="From Date"><strong>Branch</strong></label></td>
							<td >
							  ${responseJSON.branch}
					    
								<input type="hidden"  name="branch" id="branch" value="${responseJSON.branch}" /> 
							</td>
						   <td></td>
					       <td ></td> 
						    
 						</tr>
 						
 						
						
 												
				 </table>
				 
				</fieldset> 
				
				
				 <div class="row-fluid sortable">
						<div class="box span12">
								<div class="box-header well" data-original-title>
										<i class="icon-edit"></i>Communication Details
									<div class="box-icon">
										<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
										<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
									</div>
								</div>

								<div id="communicationDetails" class="box-content">
									<fieldset>
										<table width="950" border="0" cellpadding="5" cellspacing="1"
												class="table table-striped table-bordered bootstrap-datatable ">
											<tr class="even" >
					 						 <td><strong>ID Card Type</strong></td>
											      <td>
											      ${responseJSON.idcardtype}
										    
													<input type="hidden"  name="idcardtype" id="idcardtype" value="${responseJSON.idcardtype}" />
											    
											      </td>
												<td><strong>ID Card Identity Number</strong></td>
											      <td>
											      ${responseJSON.idcardnumber}
										    
													<input type="hidden"  name="idcardnumber" id="idcardnumber" value="${responseJSON.idcardnumber}" />
											    
											      </td>
											</tr>
											
										   <tr class="even">
										   		
												
											   
												 <td width="25%"><label for="Address Line 1"><strong>House Number</strong></label></td>
												<td width="25%">${responseJSON.addressLine}
												<input type="hidden" name="addressLine" id="addressLine" value="${responseJSON.addressLine}"  />
												</textarea></td>
												 <td width="25%"><label for="Nationality"><strong>Nationality</strong></label></td>
												<td width="25%">${responseJSON.nationality}
												<input type="hidden" name="nationality" id="nationality" value="${responseJSON.nationality}"  />
	
												</td>
										   </tr>
										    
										    <tr class="odd">
										        <td><label for="Local Government"><strong>Street Name</strong></label></td>
												<td>${responseJSON.streetname}
												<input  type="hidden" class="field" id="streetname" name="streetname" value="${responseJSON.streetname}" /></td>
												<td><label for="Local Government"><strong>City</strong></label></td>
												<td>${responseJSON.city}
												<input  type="hidden" class="field" id="city" name="city" value="${responseJSON.city}" /></td>
												
											</tr>

										   <tr class="odd">
										   
										  <td><label for="State"><strong>State</strong></label></td>
												<td>
												${responseJSON.state}
												<input  type="hidden" class="field" id="state" name="state" value="${responseJSON.state}" />
												
														 </td>
												
										    
											<td><label for="Local Government"><strong>Local Government</strong></label></td>
												<td> 
												${responseJSON.localGovernment}
												<input  type="hidden" class="field" id="localGovernment" name="localGovernment" value="${responseJSON.localGovernment}" />
												 
												 
													 </td>
												</tr>

										   <tr class="odd">	
										   <td><label for="Local Government"><strong>Ward Name</strong></label></td>
												<td> 
												${responseJSON.wardname}
												<input  type="hidden" class="field" id="wardname" name="wardname" value="${responseJSON.wardname}" />
												 
												 
													 </td> 
													 <td><label for="Nationality"><strong>Country</strong></label></td>
												<td>${responseJSON.country}
												<input  type="hidden" class="field" id="country" name="country" value="${responseJSON.country}" />
											
												
												</td>
											</tr>
										  
										   
										 
										  

									</table>
								</fieldset>
							</div>
						</div>
				</div>
				
				<div id="auth-data"> 
				
				
		    
				
				
							
							
				     <table width="950" border="0" cellpadding="5" cellspacing="1"  class="table table-striped table-bordered bootstrap-datatable " >
				     <tr class="odd" >		
							<td width="25%"><label for="Reason"><strong>${responseJSON.decision} </strong></label></td>
								 <td width="25%"></td>
								 
								
	           </tr>
							<tr class="odd" id="locstatus1" style="display:none">		
							<td width="25%"><label for="Reason"><strong>Reason</strong></label></td>
								 <td width="25%">${responseJSON.reason}
								 <input type="hidden" name="reason" id="reason" value="${responseJSON.reason}" />
								 </td>
								 
								
	           </tr>
	          </table>
		</div>  
		<input type="hidden" name="decision" id="decision" value="${responseJSON.decision}" />
				
				<input type="hidden"  name="bankname" id="bankname" value="${responseJSON.bankname}" />
				<input type="hidden"  name="bankaccnumber" id="bankaccnumber" value="${responseJSON.bankaccnumber}" />
				<input type="hidden" name="accname" id="accname" value="${responseJSON.accname}"  />
				<input type="hidden" name="bvn" id="bvn" value="${responseJSON.bvn}"  />
				
				
				
				
				
				
				</div>  
				
	  </div>
	  </div> 
	  
	
										<input type="hidden" name="langugae"  id="langugae"   value="English"  />
										<input type="hidden" name="telco"  id="telco"   value=""  />
										<input type="hidden" name="isocode"  id="isocode"   value=""  />
										<input type="hidden" name="IDType"  id="IDType"   value=""  />
			
	
		<div >
			<a href="#" id="btn-back" class="btn btn-danger ajax-link">&nbsp;Home </a>&nbsp;
			<a href="#" id="btn-submit" class="btn btn-success ajax-link">&nbsp;Confirm</a>					 
		</div> 
	</div> 	 
 </form>
 <script type="text/javascript">
$(function(){
	
	var actlen = $('#tbody_data > tr').length;
	
	if(actlen == 0){
		$('#add-new-act').hide();
	}else {
		$('#add-new-act').show();
	}
	 
});  
</script>
</body> 
</html>