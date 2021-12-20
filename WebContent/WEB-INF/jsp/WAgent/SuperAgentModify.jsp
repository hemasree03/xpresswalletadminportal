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
  

			 var queryString1 = "sid=STATE";
			
			$.getJSON("branchcodeajx.action", queryString1, function(data) {
					if(data.region!=""){
						var mydata=data.region;
		  			var mydata1=mydata.split(":");
		  
		   			$.each(mydata1, function(i, v) {
		   				
		   				var options = $('<option/>', {value: (mydata.split(":")[i]).split("-")[1], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
		   				
		   				$('#state').append(options);
		   				$('#state').trigger("liszt:updated");
		   			});
		   			
		   			$("#state").val("${responseJSON.STATE}");
					$('#state').trigger("liszt:updated");
		  		} 
		 	}); 
			
			var queryString1 = "sid=STATESEARCHNAME&serialNumber=${responseJSON.STATE}";
			$.getJSON("branchcodeajx.action", queryString1, function(data) {
					if(data.region!=""){
						var mydata=data.region;
		  			var mydata1=mydata.split(":");
		  			
		  			
		  			$('#localGovernment').empty();
					$('#localGovernment').trigger("liszt:updated");
					
					$('#wardname').empty();
					$('#wardname').trigger("liszt:updated");
					
		   			$.each(mydata1, function(i, v) {
		   				
		   				var options = $('<option/>', {value: (mydata.split(":")[i]).split("-")[1], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
		   				
		   				$('#localGovernment').append(options);
		   				$('#localGovernment').trigger("liszt:updated");
		   			});
		   			
		   			$("#localGovernment").val("${responseJSON.LOCALGOVERNMENT}");
					$('#localGovernment').trigger("liszt:updated");
		   			
		  		} 
		 	}); 
			
			 var queryString1 = "sid=WARDNAME&serialNumber=${responseJSON.STATE}&type=${responseJSON.LOCALGOVERNMENT}";
			$.getJSON("branchcodeajx.action", queryString1, function(data) {
					if(data.region!=""){
						var mydata=data.region;
		  			var mydata1=mydata.split(":");
		  			
		  			$('#wardname').empty();
					$('#wardname').trigger("liszt:updated");
		  
		   			$.each(mydata1, function(i, v) {
		   				
		   				var options = $('<option/>', {value: (mydata.split(":")[i]).split("-")[1], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
		   				
		   				$('#wardname').append(options);
		   				$('#wardname').trigger("liszt:updated");
		   				
		   			});
		   			
		   			$("#wardname").val("${responseJSON.WARD_NAME}");
					$('#wardname').trigger("liszt:updated");
		  		} 
		 	}); 	 
			

			
			
			
			
			
			
			
			
			$('#state').on('change', function() {
				//alert($('#state').val());
				 var queryString1 = "sid=STATESEARCHNAME&serialNumber="+$('#state').val();
				$.getJSON("branchcodeajx.action", queryString1, function(data) {
						if(data.region!=""){
							var mydata=data.region;
			  			var mydata1=mydata.split(":");
			  			
			  			
			  			$('#localGovernment').empty();
						$('#localGovernment').trigger("liszt:updated");
						
						$('#').empty();
						$('#wardname').trigger("liszt:updated");
						
			   			$.each(mydata1, function(i, v) {
			   				
			   				var options = $('<option/>', {value: (mydata.split(":")[i]).split("-")[1], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
			   				
			   				$('#localGovernment').append(options);
			   				$('#localGovernment').trigger("liszt:updated");
			   			});
			   			
			   			
			  		} 
			 	});  
			});
			
			 $('#localGovernment').on('change', function() {
					//alert($('#state').val());
					var queryString1 = "sid=WARDNAME&serialNumber="+$('#state').val()+"&type="+$('#localGovernment').val();
					$.getJSON("branchcodeajx.action", queryString1, function(data) {
							if(data.region!=""){
								var mydata=data.region;
				  			var mydata1=mydata.split(":");
				  			
				  			$('#wardname').empty();
							$('#wardname').trigger("liszt:updated");
				  
				   			$.each(mydata1, function(i, v) {
				   				
				   				var options = $('<option/>', {value: (mydata.split(":")[i]).split("-")[1], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
				   				
				   				$('#wardname').append(options);
				   				$('#wardname').trigger("liszt:updated");
				   			});
				   			
				   			
				  		} 
				 	}); 	
				}); 
			
			/*	
		var queryString1 = "sid=MERCHANT";
			
			$.getJSON("branchcodeajx.action", queryString1, function(data) {
					if(data.region!=""){
						var mydata=data.region;
		  			var mydata1=mydata.split(":");
		  
		   			$.each(mydata1, function(i, v) {
		   				
		   				var options = $('<option/>', {value: (mydata.split(":")[i]).split("-")[1], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
		   				
		   				$('#merchanttype').append(options);
		   				$('#merchanttype').trigger("liszt:updated");
		   			});
		   			
		   			
		  		} 
		 	}); 
			
		var queryString1 = "sid=BANK";
			
			$.getJSON("branchcodeajx.action", queryString1, function(data) {
					if(data.region!=""){
						var mydata=data.region;
		  			var mydata1=mydata.split(":");
		  
		   			$.each(mydata1, function(i, v) {
		   				
		   				var options = $('<option/>', {value: (mydata.split(":")[i]).split("-")[1], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
		   				
		   				$('#bankname').append(options);
		   				$('#bankname').trigger("liszt:updated");
		   			});
		   			
		   			
		  		} 
		 	}); 
			
			
		
			 */
			
			 $(document).ready(function(){


					/* var dataobject = '${responseJSON.object.IDNumber}';

					alert(dataobject);
				 */

					var auth=$('#STATUS').val();

					if( auth == 'C')
					{
						$("#merchant-auth-data").hide();
						$("#btn-submit").hide();
						$("#remarks").hide();
					}


					 $("#authradio").click(function()
						 {
						    $("#error_dlno").text(" ");
						 });

					 $("#merchauth").hide();

					 var actionLink = "";
					 var userStatus = '${responseJSON.STATUS}';
					 var text = "";

					if( userStatus == 'Active')
						text = "<a href='#' class='label label-success'  >"+userStatus+"</a>";
					else if( userStatus == 'De-Active')
						text = "<a href='#'  class='label label-warning' >"+userStatus+"</a>";
					else if( userStatus == 'InActive')
						text = "<a href='#'  class='label label-info'  >"+userStatus+"</a>";
					else if( userStatus == 'Un-Authorize')
						text = "<a href='#'  class='label label-primary'   >"+userStatus+"</a>";

					$('#spn-user-status').append(text);

					

				});


				$('#btn-back').live('click',function() {

					$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/superagent.action";
					$("#form1").submit();


				});
	
				
				$('#btn-submit').on('click', function() {
					//$("form").validate().cancelSubmit = true;
					$("#form1").validate(merchantCreateRules);
					
					var url="${pageContext.request.contextPath}/<%=appName %>/superagentupdateconfirm.action";
					$("#form1")[0].action=url;
					$("#form1").submit();

				});

				
				
<%-- 	$('#btn-submit').on('click', function(){
		$("#form1").validate(acctRules);
		 if($("#form1").valid() ) {  
		
			 $("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/superagentupdateconfirm.action'; 
				$("#form1").submit();
				
				 $(this).prop('disabled', true);
					$("#btn-submit").hide();
				return true;
			 
		 } else {
			 //alert("in else");
			 return false;
		 }
		
	});  --%>

	var merchantCreateRules= {
			rules : {
		 		'agent.managerName' : {required : true},
		 		'superagentname' :  {required : true},
		 		'agent.accountName': {required : true},
		 	
		 		'agent.email' :  { required : true , email : true},
		 		'agent.mobile' : {required : true},
		 	    'agent.addressLine' : {required : true},
				'agent.nationality' : {required : true},
				'"agent.streetname"' : {required : true},
				'agent.city' : {required : true},
				'state' : {required : true},
				'agent.localGovernment' : {required : true},
				'wardname' : {required : true},
				'agent.country' : {required : true},
				
},
			messages : {
				'agent.managerName' : { required : "Please Enter Manager Name.",
								regex:"Please Enter Valid Manager Name" },
			  'superagentname' : 	{ required : "Please Enter SuperAgentName."},					
			       'agent.accountName': { required : "Please Enter Agent Account Name."},	
					'dob' : {required : "Please Select Date Of Birth."},
					'agent.email' : {  required : "Please Enter email address.",
						
						email : "Please enter a valid email address."
					},
					'agent.mobile' : {required : "Please Enter Mobile Number.",
						number : "Please Enter Numbers Only."},
					'idcardnumber' : {required : "Pleas Enter IDNumber."},
					'idcardtype' : {required : "Pleas Select IDType."},
					'agent.addressLine' : { required : "Please Enter House Number ."},
					'agent.nationality' : {required : "Please Select Nationality."},
					'agent.streetname' : {required : "Please Enter streetname."},
					'agent.city' : {required : "Please Enter city."},
					'state' : {required : "Please select state."},
					'agent.localGovernment' : {required : "Please select localGovernment."},
					'wardname' : {required : "Please Enter wardname."},
					'agent.country' : {required : "Please Enter country."},
					
				
			}
	};
});

	
	
	
	
	

</script>
</head>
<body>
	<form name="form1" id="form1" method="post" action="">
			<div id="content" class="span10">
			    <div>
					<ul class="breadcrumb">
					  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
					  <li> <a href="#">Super Agent </a> <span class="divider"> &gt;&gt; </span></li>
					  <li><a href="#"> Super Agent View</a></li>
					</ul>
				</div>
				<table height="3">
					 <tr>
						<td colspan="3">
							<div class="messages" id="messages"><s:actionmessage /></div>
							<div class="errors" id="errors"><s:actionerror /></div>
						</td>
					</tr>
				 </table>

					<div class="row-fluid sortable"><!--/span-->

					<div class="box span12">
					<div class="box-header well" data-original-title>
							<i class="icon-edit"></i>Super Agent Creation
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
						</div>
					</div>
					<div class="box-content" id="primaryDetails">
						<fieldset>
						<table width="950" border="0" cellpadding="5" cellspacing="1"
									class="table table-striped table-bordered bootstrap-datatable " id="bank-details">
									
									 <tr class="odd">
										   		<td><label for="Telco Type"><strong>Super Agent Name</label></td>
												<td>${responseJSON.SUPERAGENT_NAME}
												<input type="hidden" name="superagentname"  id="superagentname"    value="${responseJSON.SUPERAGENT_NAME}" />
												
												
												
													</td>
												<td width="25%"><label for="Account Currency Code"><strong>Super Agent Id<font color="red">*</font></label></td>
										<td width="25%">${responseJSON.AGENTID}
										<input type="hidden" name="agent.CBNAgentId" id="CBNAgentId" value="${responseJSON.AGENTID}" />
											 	
										   </tr>
									<tr class="even">
									<td width="25%"><strong><label for="Super Agent Name"><strong>Business Owner Name<font color="red">*</font></strong></label></strong></td>
									<td width="25%"><input type="text" id="accountName" name="agent.accountName"value="${responseJSON.B_O_NAME}" />
								</td>
								<td><label for="To Date"><strong>Date Of Birth </strong></label></td>
									<td>${responseJSON.DOB}<input type="hidden" name="agent.dob" id="dob" value="${responseJSON.DOB}" />
									</td>
								
								
								
					                        
								</tr>
									<tr class="even">
											
										  	    <td ><label for="Gender"><strong>Gender</label>	</td>
												<td>${responseJSON.GENDER}<input type="hidden"  id="gender" name="agent.gender" value="${responseJSON.GENDER}" />
												
												</td>
												<td><strong><label for="Email"><strong>Email<font color="red">*</font></strong></label></strong></td>
										<td><input type="text"  name="agent.email" id="email" value='${responseJSON.EMAIL}'/>
										   </tr>
									<tr class="odd">
										
										<td ><strong><label for="Mobile"><strong>Mobile</strong></label></strong></td>
										<td>${responseJSON.MOBILE}
										<input type="hidden" name="agent.mobile"  maxlength="13" id="mobile" value='${responseJSON.MOBILE}'/></td>
										<td><strong><label for="CBN Agent Id"><strong>Status</strong></label></strong></td>
									<td><div id="spn-user-status"></div></td>
									</tr>
									
										<tr class="even">
										   
										    
												 <td width="25%"><label for="Nationality"><strong>BVN</strong></label></td>
												<td width="25%">${responseJSON.BVN}
												<input type="hidden" name="agent.bvn" id="bvn" value="${responseJSON.BVN}"  />
	<td></td>
	<td></td>
	</tr>											
										   
									

								</table>
						</fieldset>
						</div>
					</div>
				</div>





		<div class="row-fluid sortable"><!--/span-->
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
					 						 <td><strong>ID Card Type<font color="red">*</font></strong></td>
											      <td>
											     ${responseJSON.ID_TYPE}
										    <input type="hidden"  name="agent.idcardtype" id="idcardtype" value='${responseJSON.ID_TYPE}'/>
											    
											      </td>
												<td><strong>ID Card Identity Number<font color="red">*</font></strong></td>
											      <td>
											    ${responseJSON.ID_NUMBER}
											    <input type="hidden" name="agent.idcardnumber" id="idcardnumber" value='${responseJSON.ID_NUMBER}'/>
											    
										    
											    
											      </td>
											</tr>
											
										   <tr class="even">
										   		
												
											   
												 <td width="25%"><label for="Address Line 1"><strong>House Number<font color="red">*</font></strong></label></td>
												<td width="25%"> <input type="text"  name="agent.addressLine" id="addressLine" value='${responseJSON.ADDRESSLINE}'/>
												
												</td>
												 <td width="25%"><label for="Nationality"><strong>Nationality<font color="red">*</font></strong></label></td>
												<td width="25%"><input type="text" name="agent.nationality" id="nationality" value='${responseJSON.NATIONALITY}'/>
	
												</td>
										   </tr>
										    
										    <tr class="odd">
										        <td><label for="Street Name"><strong>Street Name<font color="red">*</font></strong></label></td>
												<td><input type="text" id="streetname" name="agent.streetname" value='${responseJSON.STREET_NAME}'/>
												<td><label for="City"><strong>City<font color="red">*</font></strong></label></td>
												<td><input type="text" id="city" name="agent.city" value='${responseJSON.CITY}'/>
											</tr>

													 
													 <td><label for="State"><strong>State<font color="red">*</font></strong></label></td>
												<td>
												
												<select id="state" name="state" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select> 
											 <input name="State" type="hidden" class="field" id="state"  />
														 </td>
												<td><label for="Local Government"><strong>Local Government<font color="red">*</font></strong></label></td>
												<td> 
												 <select id="localGovernment" name="agent.localGovernment" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select> 
													 </td>
											</tr>

										   <tr class="odd">
										   
										  
												
										    
												 
													 <td><label for="State"><strong>Ward Name<font color="red">*</font></strong></label></td>
												<td>
												
												<select id="wardname" name="wardname" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select> 
											
														 </td>
											
												
													 
													 
													 <td><label for="Country"><strong>Country<font color="red">*</font></strong></label></td>
												<td><input type="text" id="country" name="agent.country" value='${responseJSON.COUNTRY}' />
												
											
												
												</td>
											</tr>
										  
										   
										 
										  

										
												</td>
											</tr>
										  
										   
										 
										  

									</table>
				</fieldset>
			</div>

		</div>
		</div>

		

		<div class="form-actions">

				</div>
							<div>
								<a href="#" id="btn-back" class="btn btn-danger ajax-link">Home
								</a>&nbsp; <a href="#" id="btn-submit"
									class="btn btn-success ajax-link">&nbsp;Submit</a> <span
									id="error_dlno" class="errors"></span>
							</div>

				<span id ="error_dlno" class="errors"></span>

  			   <input name="STATUS" type="hidden" id="STATUS" value="${STATUS}" />
  				<input name="AUTH_CODE" type="hidden" id="AUTH_CODE" value="${AUTH_CODE}"  />
				<input type="hidden" name="REF_NO" id="REF_NO" value="${REF_NO}"/>
				<input type="hidden" name="DECISION" id="DECISION" />

				  <input type="hidden" name="formName" id="formName" value="Merchant"/>
			</div>
	

</form>
</body>
</html>
