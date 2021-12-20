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
			
			   fname : { required : true, regex: /^[a-zA-Z0-9 -]+$/} ,
			   lastname: { required : true, regex: /^[a-zA-Z -]+$/} ,
			   middlename: {specialChars: true},
			 
			   dateofbirth: { required : true},
			  telephone : { required : true, regex: /^[0-9]+$/ },
			   email : { email : true},
			   gender: { required : true},
			   marital: { required : true},
		
			   idtype: { required : true},
			   iddetails: { required : true},
			   address: { required : true},
			   nationality: { required : true},
			   city: { required : true},
			   state: { required : true},
			   lga: { required : true},
			   wardname: { required : true},
			   country: { required : true},
			   area : { required : true},
			   branch: { required : true},
			   business: { required : true},
			   staffcode: { required : true,regex: /^[a-zA-Z0-9 ]+$/}
			   
		   },  
		   messages : {
			   
			   fname : { 
					required : "Please Enter First Name.",
					regex : "First Name, can not allow digits or special characters."
				},
				lastname: { 
							   		required : "Please enter Last Name.",
							   		regex : "Last Name, can not allow digits or special characters."
								},
								
								
			 /* 	middlename: { 
					
							   		pattern : "middle Name, can not allow digits or special characters."
								},  */
								dateofbirth: { 
							   		required : "Please select Date Of Birth."
								},
				telephone: { 
							   		required : "Please enter Mobile Number.",
							   		regex : "Mobile number allows numbers only"
								},
				
								email : { 
								
									email : "Please enter a valid email address."
								},				
				gender: { 
							   		required : "Please select Gender."
								},
				marital: { 
							   		required : "Please select Marital Status."
								},
				merchanttype: { 
							   		required : "Please select Merchant Category."
								},
								business: { 
							   		required : "Please enter Business Name."
								},
								idtype: { 
							   		required : "Please select ID Card Type."
								},
								iddetails: { 
							   		required : "Please enter ID Card Identity Number."
								},
								address: { 
							   		required : "Please enter HouseNumber."
								},
				nationality: { 
							   		required : "Please enter Nationality."
								},
				city: { 
							   		required : "Please enter City."
								},
				state: { 
							   		required : "Please select State."
								},
								lga: { 
							   		required : "Please select Local Government."
								},
				wardname: { 
							   		required : "Please select Ward Name."
								},
				country: { 
							   		required : "Please enter Country."
								},
				
								area: { 
							   		required : "Please enter Street Name."
								},
				branch: { 
							   		required : "Please enter Branch."
								},
								business: { 
							   		required : "Please enter Business Name."
								},
				staffcode: { 
							   		required : "Please enter Staff Code.",
							   		regex : "Staff Code, can not allow special characters."
								},
						   } 
		 };
		

$.validator.addMethod("specialChars", function( value, element ) {
	//alert(value);
 if(value==""){
 
 }else{
	var regex = new RegExp("^[a-zA-Z0-9 ]+$");
 var key = value;

 if (!regex.test(key)) {
    return false;
 }
 
 }
 return true;
}, "Middle Name, can not allow special characters.");


$.validator.addMethod("regex", function(value, element, regexpr) {          
	 return regexpr.test(value);
}, "");  


	
$(function() {  
	
	
	
	$("#dateofbirth").datepicker({
		maxDate: '-18Y',
		changeMonth: true,
		changeYear: true,
		yearRange: '-110:-18',
		dateFormat: 'dd-mm-yy',

		onSelect: function (date) {
		var dob = new Date(date);
		var today = new Date();

		// alert(date.getFullYear());
		if (date.getFullYear() + 18 <= today.getFullYear())
		{
		$('#errormsg').hide();
		$('#errormsg').val('');
		}
		else
		{
		$('#errormsg').text('Age should not less than 18 Years');
		$('#errormsg').val('Age should not less than 18 Years');
		}
		}

		});
	
	$('#locstatus').click(function() {
		
		if ( $('#locstatus').attr('checked')) {
			$('#locstatus1').css("display","");
			
	    } else {
	    	$('#locstatus1').css("display","none");
	    	
	    }
	});
	
	
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
		    
		    
		    $("#gender").val("${responseJSON.gender}");
			$('#gender').trigger("liszt:updated");
			$("#marital").val("${responseJSON.marital}");
			$('#marital').trigger("liszt:updated");
  

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
		   			
		   			$("#state").val("${responseJSON.state}");
					$('#state').trigger("liszt:updated");
		  		} 
		 	}); 
			
			var queryString1 = "sid=STATESEARCHNAME&serialNumber=${responseJSON.state}";
			$.getJSON("branchcodeajx.action", queryString1, function(data) {
					if(data.region!=""){
						var mydata=data.region;
		  			var mydata1=mydata.split(":");
		  			
		  			
		  			$('#lga').empty();
					$('#lga').trigger("liszt:updated");
					
					$('#wardname').empty();
					$('#wardname').trigger("liszt:updated");
					
		   			$.each(mydata1, function(i, v) {
		   				
		   				var options = $('<option/>', {value: (mydata.split(":")[i]).split("-")[1], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
		   				
		   				$('#lga').append(options);
		   				$('#lga').trigger("liszt:updated");
		   			});
		   			
		   			$("#lga").val("${responseJSON.lga}");
					$('#lga').trigger("liszt:updated");
		   			
		  		} 
		 	}); 
			
			 var queryString1 = "sid=WARDNAME&serialNumber=${responseJSON.state}&type=${responseJSON.lga}";
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
		   			
		   			$("#wardname").val("${responseJSON.wardname}");
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
			  			
			  			
			  			$('#lga').empty();
						$('#lga').trigger("liszt:updated");
						
						$('#wardname').empty();
						$('#wardname').trigger("liszt:updated");
						
			   			$.each(mydata1, function(i, v) {
			   				
			   				var options = $('<option/>', {value: (mydata.split(":")[i]).split("-")[1], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
			   				
			   				$('#lga').append(options);
			   				$('#lga').trigger("liszt:updated");
			   			});
			   			
			   			
			  		} 
			 	});  
			});
			
			 $('#lga').on('change', function() {
					//alert($('#state').val());
					var queryString1 = "sid=WARDNAME&serialNumber="+$('#state').val()+"&type="+$('#lga').val();
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
			
			
		
			
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){
		$("#form1").validate(acctRules);
		 if($("#form1").valid() ) {  
		
			 $("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/agentupdateconfirm.action'; 
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
									<td width="25%"><label for="fname"><strong>First Name <font color="red">*</font></strong></label></td>
									<td width="25%"><input type="text" name="fname" id="fname" value="${responseJSON.firstname}" /></td>
									<td width="25%"><label for="middlename"><strong>Middle Name</strong></label></td>
									<td width="25%"><input type="text" name="middlename" id="middlename" value="${responseJSON.middlename}" /></td>
								</tr>
								<tr class="even">
									<td width="25%"><label for="lastname"><strong>Last Name <font color="red">*</font></strong></label></td>
									<td width="25%"><input type="text" name="lastname" id="lastname" value="${responseJSON.lastname}" />
									</td>
							
									
									<td  ><label for="To Date"><strong>Date Of Birth<font color="red">*</font></strong></label> </td>
							<td>
							<input type="text" maxlength="10"  class="dateofbirth" id="dateofbirth" name="dateofbirth"  readonly="readonly" value="${responseJSON.dateofbirth}" />
							</td>
								</tr>
								<tr class="even">
									<td width="25%"><label for="From Date"><strong>Email ID</strong></label></td>
									<td width="25%"><input type="text" name="email" id="email" value="${responseJSON.email}" /></td>
									<td width="25%"><label for="To Date"><strong>Mobile Number <font color="red">*</font></strong></label></td>
									<td width="25%">${responseJSON.telephone}<input type="hidden" value='${responseJSON.telephone}' maxlength="13" name="telephone" id="telephone" /></td>
								</tr>
								<tr class="even">
								
				
							 
							 	<td width="25%"><label for=Gender><strong>Gender<font color="red">*</font></strong></label></td>
												
									<td width="25%"><select id="gender" name="gender" class="chosen-select-width" style="width: 220px;">
																
																<option value="">Select</option>
																<option value="Female">Female</option>
																	<option value="Male">Male</option>
															</select> 
													 </td>
											
							 	 <td width="25%"><label for=Marital Status><strong>Marital Status<font color="red">*</font></strong></label></td>
												
									<td width="25%">	<select id="marital" name="marital" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
																<option value="Married">Married</option>
																	<option value="Single">Single</option>
															</select> 
													 </td>
							 
							 
							 </tr>
								<tr class="even" >
								<td width="25%"><label for="Merchant Category"><strong>Merchant Category</strong></label></td>
							<td width="25%">${responseJSON.mcc}
								<input type="hidden" value='${responseJSON.mcc}' name="merchanttype" id="merchanttype" /></td>
								<td width="25%"><label for="Business Name"><strong>Business Name</strong><font color="red">*</font></label></td>
								<td width="25%">
								<input type="text" value='${responseJSON.business}' name="business" id="business" /></td>		
									
								</tr>
								<tr class="odd">
									<td width="25%"><label for="BVN"><strong>BVN</strong></label></td>
								<td width="25%">${responseJSON.bvn}
								<input type="hidden" value='${responseJSON.bvn}' name="bvn" id="bvn" /></td>		
								
									 <td><label for="Stop Id"><strong>Staff Code<font color="red">*</font></strong></label> </td>
					       <td ><input type="text" class="field"  name="staffcode" id="staffcode" value='${responseJSON.staffcode}' />
									
									</tr>	
									
							</table>
						</fieldset>

						<div class="box-header well" data-original-title>
							<i class="icon-edit"></i>Communication Details
							<div class="box-icon">
								<a href="#" class="btn btn-setting btn-round"><i
									class="icon-cog"></i></a> <a href="#"
									class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a>
							</div>
						</div>
						
							<fieldset>
								<table width="950" border="0" cellpadding="5" cellspacing="1"
									class="table table-striped table-bordered bootstrap-datatable "
									id="user-details">

									<tr class="even">
									
									<td width="25%"><label for="To Date"><strong>Id Type</strong></label></td>
									<td width="25%">${responseJSON.idtype}
									<input type="hidden" value='${responseJSON.idtype}' name="idtype" id="idtype" /></td>
									
								    <td width="25%"><label for="From Date"><strong>Id Card Identity Number </strong></label></td>
									<td width="25%">${responseJSON.iddetails}
									<input type="hidden" name="iddetails" id="iddetails" value="${responseJSON.iddetails}" /></td>
									</tr>
									<tr class="even">
									<td width="25%"><label for="To Date"><strong>House Number</strong></label></td>
									<td width="25%">
									<input type="text" name="address" id="address" value="${responseJSON.address}" />
									</td>
									<td width="25%"><label for="nationality"><strong>Nationality<font color="red">*</font></strong></label></td>
									<td width="25%"><input type="text" name="nationality" id="nationality" value="${responseJSON.nationality}" /></td>
									</tr>
									<tr class="even">
										<td width="25%"><label for="Street"><strong>Street
													Name <font color="red">*</font>
											</strong></label></td>
										<td width="25%"><input type="text" name="area" id="area"
											value="${responseJSON.area}" /></td>


										<td width="25%"><label for="City"><strong>City <font
													color="red">*</font></strong></label></td>
										<td width="25%"><input type="text" value='${responseJSON.city}' name="city" id="city" /></td>
									</tr>
								 <tr class="even">

					<td width="25%"><label for="State"><strong>State<font color="red">*</font></strong></label></td>
												<td width="25%">
												
												<select id="state" name="state" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select> 
											<!--  <input name="state" type="hidden" class="field" id="state"  /> -->
														 </td>
												<td width="25%"><label for="Local Government"><strong>Local Government<font color="red">*</font></strong></label></td>
												<td width="25%"> 
												 <select id="lga" name="lga" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select> 
													 </td>
											</tr>

										   <tr class="odd">
										   
										  
												
										    
											
													 
													 <td width="25%"><label for="State"><strong>Ward Name<font color="red">*</font></strong></label></td>
												<td width="25%">
												
												<select id="wardname" name="wardname" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select> 
											
														 </td>

                                          <td width="25%"><label for="Country"><strong>Country<font
													color="red">*</font></strong></label></td>
										<td width="25%"><input type="text" value='${responseJSON.country}' name="country" id="country" /></td>
									</tr>



									 
									

								</table>
							</fieldset>
						<%-- 	
							<input type="checkbox" name="locstatus" id="locstatus"><strong>Change Account Details</strong>
								
							
						

						<div class="row-fluid sortable" id="locstatus1" style="display:none">
							<!--/span-->
							<div class="box span12">
								<div class="box-header well" data-original-title>
									Account Details
									<div class="box-icon">
										<a href="#" class="btn btn-minimize btn-round"><i
											class="icon-chevron-up"></i></a> <a href="#"
											class="btn btn-close btn-round"><i class="icon-remove"></i></a>
									</div>
								</div>

								
										<fieldset>
											<table width="950" border="0" cellpadding="5" cellspacing="1"
												class="table table-striped table-bordered bootstrap-datatable "
												id="Account-details">
                                    <tr class="even">
												<td width="25%"><strong>Bank Name</strong></td>
												<td width="25%"> <input type="text"
													name="bankname" id="bankname"
													value="${responseJSON.bankname}" />

												</td>
												<td width="25%"><strong>Bank Account Number</strong></td>
												<td width="25%">${responseJSON.accountno} <input type="hidden"
													name="bankaccountnumber" id="bankaccountnumber"
													value="${responseJSON.accountno}" />

												</td>
											</tr>

												<tr class="even">
													<td width="25%"><label for="Account Name"><strong>Account
																Name </strong></label></td>
													<td width="25%">${responseJSON.bankcustname} <input
														type="hidden" name="bankcustname" id="bankcustname"
														value="${responseJSON.bankcustname}" /></td>


													<td width="25%"><label for="BVN"><strong>BVN </strong></label></td>
													<td width="25%">${responseJSON.bvn} <input type="hidden"
														name="bvn" id="bvn" value="${responseJSON.bvn}" />
													</td>
												</tr>
											</table>
										</fieldset>

								
							</div>
							 --%><input type="hidden" name="langugae" id="langugae" value="English" />
							<input type="hidden" name="customercode" id="customercode" value="${responseJSON.customercode}" />
</div></div>
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