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
<s:set value="responseJSON" var="respData" />
<script type="text/javascript">
var acctRules = {
		   rules : {
			   branch : { required : true}
			   
		   },  
		   messages : {
			   branch : { 
			   		required : "Please enter Branch."
				}
		   } 
		 };
		
	
$(function() {  
	
	var config = {
		      '.chosen-select'           : {},
		      '.chosen-select-deselect'  : {allow_single_deselect:true},
		      '.chosen-select-no-single' : {disable_search_threshold:10},
		      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
		      '.chosen-select-width'     : {width:"95%"}
		    };
			
		    for (var selector in config) {
		      $(selector).chosen(config[selector]);
		    }
  

		  
			 
			 var queryString1 = "sid=BRANCH";
				
				$.getJSON("branchcodeajx.action", queryString1, function(data) {
						if(data.region!=""){
							var mydata=data.region;
			  			var mydata1=mydata.split(":");
			  
			   			$.each(mydata1, function(i, v) {
			   				
			   				var options = $('<option/>', {value: mydata.split(":")[i], text: mydata.split(":")[i]}).attr('data-id',i);
			   				
			   				$('#branch').append(options);
			   				$('#branch').trigger("liszt:updated");
			   			});
			   			
			   			
			  		} 
			 	}); 
				
			
		
			
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){
		$("#form1").validate(acctRules);
		 if($("#form1").valid() ) {  
			 if($("#decision").val()=="Reject" && $("#reason").val()==""){
				 $("#errors").text("Please Enter Reason ");
				return false; 
			 }
			 
			 $("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/superagentapprovalconf.action'; 
				$("#form1").submit();
				
				 $(this).prop('disabled', true);
					$("#btn-submit").hide();
				return true;
			 
		 } else {
			 //alert("in else");
			 return false;
		 }
		
	}); 
});


function rfun(check){
	
	$('#reason').val("");
	$('#decision').val(check);
	if(check== "Reject"){
		
		$('#locstatus1').css("display","");
		}else{
		
		
		$('#locstatus1').css("display","none");
		}
}
</script>
</head>
<body>
	<form name="form1" id="form1" method="post">

		<div id="content" class="span10">
			<div>
				<ul class="breadcrumb">
					<li><a href="home.action">Home</a> <span class="divider">
							&gt;&gt; </span></li>
					<li><a href="#"> Agent Modify</a></li>
				</ul>
			</div>

			<table>
				<tr>
					<td colspan="3">
						<div class="messages" id="messages">
							<s:actionmessage />
						</div>
						<div class="errors" id="errors">
							<s:actionerror />
						</div>
					</td>
				</tr>
			</table>

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<i class="icon-edit"></i>Agent Details
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i
								class="icon-cog"></i></a> <a href="#"
								class="btn btn-minimize btn-round"><i
								class="icon-chevron-up"></i></a>
						</div>
					</div>
					<div class="box-content">
						<fieldset>
							<table width="950" border="0" cellpadding="5" cellspacing="1"
								class="table table-striped table-bordered bootstrap-datatable "
								id="user-details">
								<tr class="even">
							<td width="25%"><label for="From Date"><strong>Reference Number</strong></label></td>
							<td width="25%">${responseJSON.customercode}
							<input type="hidden" name="customercode"  id="customercode"  value="${responseJSON.customercode}"   /></td>
							<td width="25%"></td>
						    <td width="25%"></td>
							
							</tr>

								<tr class="even">
							<td width="25%"><label for="From Date"><strong>First Name</strong></label></td>
							<td width="25%">${responseJSON.firstname}
							<input type="hidden" name="fullname"  id="fullname"  value="${responseJSON.firstname}"   /></td>
							<td width="25%"><label for="From Date"><strong>Middle Name</strong></label></td>
						    <td width="25%">${responseJSON.middlename}
						    <input type="hidden" name="middlename"  id="middlename"  value="${responseJSON.middlename}"   /></td>
							
							</tr>
						<tr class="even">
						<td width="25%"><label for="From Date"><strong>Last Name</strong></label></td>
						    <td width="25%">${responseJSON.lastname}
						    <input type="hidden" name="lastname"  id="lastname"   value="${responseJSON.lastname}"  /></td>
						<td  ><label for="To Date"><strong>Date Of Birth</strong></label> </td>
							<td>${responseJSON.dateofbirth}
							<input type="hidden"   class="dob" id="dob" name="dob"  value="${responseJSON.dateofbirth}" />
							</td>
						
							
							  
						</tr>
						<tr class="even">
						
							<td ><label for="From Date"><strong>Email ID</strong></label></td>
							<td >${responseJSON.email}
							<input type="hidden" name="email"  id="email"    value="${responseJSON.email}"  />  </td>
						   <td><label for="To Date"><strong>Mobile Number</strong></label> </td>
					       <td >${responseJSON.telephone}
					    
								<input type="hidden" class="field"  name="telephone" id="telephone"  value="${responseJSON.telephone}" />
						     
						   </td> 
						    
 						</tr>
 						<tr class="even" >
 						 <td><strong>Gender</strong></td>
						      <td>${responseJSON.gender}
							 <input type="hidden" class="field"  name="gender" id="gender"  value="${responseJSON.gender}" />
 
						    
						      </td>
							<td><strong>Marital Status</strong></td>
						      <td>${responseJSON.marital}
							  <input type="hidden" class="field"  name="marital" id="marital"  value="${responseJSON.marital}" />

						    
						      </td>
						</tr>
						
						<tr class="even">
						
							<td ><label for="From Date"><strong>Merchant Category</strong></label></td>
							<td >${responseJSON.mcc}
							<input type="hidden" class="field"  name="merchanttype" id="merchanttype"  value="${responseJSON.mcc}" />

							
							</td>
						   <td><label for="To Date"><strong>Business Name</strong></label> </td>
					       <td >${responseJSON.business}
					       <input type="hidden" class="field"  name="businessname" id="businessname" value="${responseJSON.business}"  />
 							
						     
						   </td> 
						    
 						</tr>
								<tr class="even">
						
							<td ><label for="From Date"><strong>Branch<font color="red">*</font></strong></label></td>
							<td >
							 <select id="branch" name="branch" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select>  
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
						      <td>${responseJSON.idtype}
							           <input type="hidden" name="idcardtype" id="idcardtype" value="${responseJSON.idtype}"  />
						    
						      </td>
							<td><strong>ID Card Identity Number</strong></td>
						      <td>${responseJSON.idnumber}
						      <input type="hidden" name="idcardnumber" id="idcardnumber" value="${responseJSON.idnumber}"  />
						    
						      </td>
						</tr>
						<tr class="even" >
 						 <td colspan="4">
 						 <img  src="${responseJSON.idimage}"  width="450" height="250" ></img>
 						 </td>
						</tr>	
								
										   <tr class="even">
										   		
												
											   
												 <td width="25%"><label for="Address Line 1"><strong>House Number</strong></label></td>
												<td width="25%">${responseJSON.address}
												<input type="hidden" name="addressLine" id="addressLine"  value="${responseJSON.address}" />
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
												<td>${responseJSON.state}
												<input  type="hidden" class="field" id="state" name="state" value="${responseJSON.state}"  /></td>
												
												
												<td><label for="Local Government"><strong>Local Government</strong></label></td>
												<td>${responseJSON.lgv}
												 <input  type="hidden" class="field" id="localGovernment" name="localGovernment" value="${responseJSON.lgv}"  />
												 
													 </td>
											</tr>

										   <tr class="odd">
										   
										  
												
										    
											
													 
													 <td><label for="State"><strong>Ward Name</strong></label></td>
												<td>${responseJSON.wardname}
												<input  type="hidden" class="field" id="wardname" name="wardname" value="${responseJSON.wardname}" />
												
												
											
														 </td>
													 
													 <td><label for="Nationality"><strong>Country</strong></label></td>
												<td>${responseJSON.country}
											<input type="hidden" name="country" id="country" value="${responseJSON.country}" />
												
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
							<td width="25%"><strong>Authorize &nbsp&nbsp&nbsp&nbsp&nbsp </strong>
				 <input  type='radio' name="authradio" id="Authorize"  class='center-chk'  onclick="rfun(this.id)" checked/>&nbsp&nbsp 
				 <strong>Reject &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp </strong>
				<input type="radio" name="authradio" id="Reject"  class='center-chk'   onclick="rfun(this.id)" /> &nbsp&nbsp&nbsp</td>
								 <td width="75%"></td>
								 
								
	           </tr>
							<tr class="odd" id="locstatus1" style="display:none">		
							<td width="25%"><label for="Reason"><strong>Reason<font color="red">*</font></strong></label></td>
								 <td width="75%">
								 <input type="text" name="reason" id="reason"  />
								 </td>
								 
								
	           </tr>
	          </table>
		</div>  
						
						<input type="hidden" name="decision" id="decision" value="Authorize" />
						<input type="hidden" name="langugae" id="langugae" value="English" />
							<input type="hidden" name="customercode" id="customercode" value="${responseJSON.customercode}" />

						</div>
				</div>
							<div>
								<a href="#" id="btn-back" class="btn btn-danger ajax-link">Home
								</a>&nbsp; <a href="#" id="btn-submit"
									class="btn btn-success ajax-link">&nbsp;Submit</a> <span
									id="error_dlno" class="errors"></span>
							</div>

						

					





			</div>
	</form>

</body>
</html>