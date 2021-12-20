<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="">
 <%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%String ctxstr = request.getContextPath(); %>
<%String appName = session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>
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
<script type="text/javascript" >



var list = "dob".split(",");
var datepickerOptions = {
				showTime: true,
				showHour: true,
				showMinute: true,
	  		dateFormat:'dd-mm-yy',
	  		alwaysSetTime: false,
	  		yearRange: '-110:-18',
	  		maxDate: '-18Y',
			changeMonth: true,
			changeYear: true
};


$(function() {
	$(list).each(function(i,val){
		$('.'+val).datepicker(datepickerOptions);
	});
});


$(document).ready(function() {
	
	
	$( "#superagentname" ).keyup(function() {
		
		$( "#superagentname" ).val((this.value).toUpperCase());
		});
	
	
	$.getJSON("superagentCountJSONAct.action", queryString,function(data) { 
		var billerCount =data.responseJSON.BILLER_COUNT;
		//alert(billerCount);
		$('#CBNAgentId').val(billerCount);
	});

	
var queryString1 = "sid=STATE";
	
	$.getJSON("branchcodeajx.action", queryString1, function(data) {
			if(data.region!=""){
				var mydata=data.region;
  			var mydata1=mydata.split(":");
  
   			$.each(mydata1, function(i, v) {
   				
   				var options = $('<option/>', {value: mydata.split(":")[i], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
   				
   				$('#stated').append(options);
   				$('#stated').trigger("liszt:updated");
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
	
	
	$('#stated').on('change', function() {
		//alert($('#state').val());
		 var queryString1 = "sid=STATESEARCH&serialNumber="+($('#stated').val()).split("-")[0];
		 $('#state').val(($('#stated').val()).split("-")[1]);
		$.getJSON("branchcodeajx.action", queryString1, function(data) {
				if(data.region!=""){
					var mydata=data.region;
	  			var mydata1=mydata.split(":");
	  			
	  			
	  			$('#localGovernment1').empty();
				$('#localGovernment1').trigger("liszt:updated");
				
				$('#wardname').empty();
				$('#wardname').trigger("liszt:updated");
				
	   			$.each(mydata1, function(i, v) {
	   				
	   				var options = $('<option/>', {value: mydata.split(":")[i], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
	   				
	   				$('#localGovernment1').append(options);
	   				$('#localGovernment1').trigger("liszt:updated");
	   			});
	   			
	   			
	  		} 
	 	});  
	});
	
	 $('#localGovernment1').on('change', function() {
		//alert($('#state').val());
		var queryString1 = "sid=WARD&serialNumber="+($('#stated').val()).split("-")[0]+"&type="+($('#localGovernment1').val()).split("-")[0];
		$('#localGovernment').val(($('#localGovernment1').val()).split("-")[1]);
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
	
	
	
	$('#nationality').val('<s:property value="agent.nationality"/>');
	$('#gender').val('<s:property value="agent.gender"/>');
	$('#telco').val('<s:property value="agent.telco"/>');
	$('#IDType').val('<s:property value="agent.IDType"/>');
	$('#country').val('<s:property value="agent.country"/>');
	
	
	
	
	var queryString = "";
	
	
	
	 $('#product1').on('change', function() {
    	 // alert( this.value ); 
    	  $('#desc').text((this.value).split("-")[1]);
    	  $('#product').val((this.value).split("-")[0]);
    	  $('#prodesc').val((this.value).split("-")[1]);
    	  
    	  
    	 
    	  
    	  
    	});
	
	// $('#telco').on('change', function() {	
	
		 $.getJSON("superproductajx.action", queryString, function(data) {
				if(data.region!=""){
					//alert($('#telco').val());
				//	alert(data.region);
					var mydata=data.region;
	  			//var mydata=(data.region).substring(5,(data.region).length);
	  			document.getElementById("product1").length=1;
	  			var mydata1=mydata.split(":");
	  
	   			$.each(mydata1, function(i, v) {
	   				//if((mydata.split(":")[i]).split("@")[2]==$('#telco').val()){
	   				var options = $('<option/>', {value: (mydata.split(":")[i]).split("@")[0], text: (mydata.split(":")[i]).split("@")[0]}).attr('data-id',i);
	   				//}
	   				$('#product1').append(options);
	   				$('#product1').trigger("liszt:updated");
	   			});
	   			
	   			
	  		} 
	 	}); 
		 
	 //});
	
	
	

	$('#btn-back').live('click', function () {
		//$("form1").validate().cancelSubmit = true;
		var url="${pageContext.request.contextPath}/<%=appName %>/superagent.action";
		$("#form1")[0].action=url;
		$("#form1").submit();

	});

	$('#btn-deactive').live('click', function () {
		$('#form1').find('input[type="text"], textarea, select').attr('disabled','disabled');
		$('#form1').find('.chosen-select').prop('readonly', true).trigger("liszt:updated");

		$('#btn-deactive-cnf').show();
		$('#btn-deactive').hide();
	});

$('#btn-deactive-cnf').live('click', function () {
		$('#status').val('D');
		$("form").validate().cancelSubmit = true;
		var url="${pageContext.request.contextPath}/<%=appName %>/activedeactive.action";
		$("#form1")[0].action=url;
		$("#form1").submit();

	});

	$('#btn-active').live('click', function () {
		$('#status').val('A');
		$("form").validate().cancelSubmit = true;
		var url="${pageContext.request.contextPath}/<%=appName %>/activedeactive.action";
		$("#form1")[0].action=url;
		$("#form1").submit();

	});

	$('#btn-submit').live('click', function () {
		encryptPassword();
	//	alert($('#product1').val());
		//$('#product').val($('#product1').val());
		$("#form1").validate(merchantCreateRules);
		if($("#form1").valid()){
			
			var queryString = "method=superagentsearch&mnumber=234"+$('#telephone1').val();	
			$.getJSON("postJson.action", queryString,function(data) { 
				
				
			if(data.message!="SUCCESS"){
				
				
				$('#errors').text(data.message);
			}else{ 
				$("#mobile").val($("#isocode").val()+""+$("#telephone1").val());
			var url="${pageContext.request.contextPath}/<%=appName %>/superagentconfirmAck.action";
			$("#form1")[0].action=url;
			$("#form1").submit();
			 }
			});
				
		}

	});

	function randomString1() {
		   var charSet = '0123456789';
		    var randomString2 = '';
		    for (var i = 0; i < 5; i++) {
		    	var randomPoz = Math.floor(Math.random() * charSet.length);
		    	randomString2 += charSet.substring(randomPoz,randomPoz+1);
		    }
		    return randomString2;
		}

	function randomString() {
		   var charSet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
		    var randomString = '';
		    for (var i = 0; i < 5; i++) {
		    	var randomPoz = Math.floor(Math.random() * charSet.length);
		    	randomString += charSet.substring(randomPoz,randomPoz+1);
		    }
		    return randomString;
		}

		function encryptPassword(){
			var encryptPass;

			var otpData = randomString1();
			$("#otp").val(otpData);
			var password=randomString();
			$("#password").val(password);
			encryptPass = b64_sha256(password);
			$("#encryptPassword").val(encryptPass);
		}



		
	var merchantCreateRules= {
			rules : {
				 
				   dob: { required : true},
				   telephone1: { required : true},
				   gender: { required : true},
				   mailid : { email : true},
				   idcardtype: { required : true},
				   idcardnumber: { required : true},
				   addressLine: { required : true},
				   nationality: { required : true},
				   city: { required : true},
				   stated: { required : true},
				   localGovernment1: { required : true},
				   wardname: { required : true},
				   country: { required : true},
				   bankname: { required : true},
				   bankaccnumber: { required : true},
				   accname: { required : true},
				   bvn: { required : true},
				   streetname : { required : true},
				   branch: { required : true},
				   superagentname: { required : true},
				   accountName: { required : true},
				 
				   product1: { required : true}
				   
			   },  
			   messages : {
				   
				   
					dob: { 
								   		required : "Please select Date Of Birth."
									},
					telephone1: { 
								   		required : "Please enter Mobile Number."
									},
					gender: { 
								   		required : "Please select Gender."
									},
									mailid : { 
										
										email : "Please enter a valid email address."
									},
					
					idcardtype: { 
								   		required : "Please select ID Card Type."
									},
					idcardnumber: { 
								   		required : "Please enter ID Card Identity Number."
									},
					addressLine: { 
								   		required : "Please enter Address."
									},
					nationality: { 
								   		required : "Please enter Nationality."
									},
					city: { 
								   		required : "Please enter City."
									},
					stated: { 
								   		required : "Please select State."
									},
					localGovernment1: { 
								   		required : "Please select Local Government."
									},
					wardname: { 
								   		required : "Please select Ward Name."
									},
					country: { 
								   		required : "Please enter Country."
									},
					bankname: { 
								   		required : "Please select Bank Name."
									},
					bankaccnumber: { 
								   		required : "Please enter Bank Account Number."
									},
					accname: { 
								   		required : "Please enter Account Name."
									},
					bvn: { 
								   		required : "Please enter BVN."
									},
					streetname: { 
								   		required : "Please enter Street Name."
									},
					branch: { 
								   		required : "Please enter Branch."
									},
									superagentname: { 
								   		required : "Please enter Super Agent Name."
									},
									accountName: { 
								   		required : "Please enter Business Owner Name."
									},
									product1: { 
								   		required : "Please Select Product."
									}
							   } 
		};
});

$(document).ready(function(){
	
	 $('#bankaccnumber').on("keypress", function (e) {
		  
		    if (e.which != 8 && e.which != 0 && ((e.which < 48 || e.which > 57) && e.which != 46) ) {
		        e.preventDefault();
		    }
		});	 
	 
	 
	 $('#bvn').on("keypress", function (e) {
		  
		    if (e.which != 8 && e.which != 0 && ((e.which < 48 || e.which > 57) && e.which != 46) ) {
		        e.preventDefault();
		    }
		});	 
	 
	
	 
	 $('#telephone1').on("keypress", function (e) {
		  
		    if (e.which != 8 && e.which != 0 && ((e.which < 48 || e.which > 57) && e.which != 46) ) {
		        e.preventDefault();
		    }
		});	
	});	 
	


function getdata1(){
	
	  $("#btn-submit").css("display","none");
	  $("#btn-account").css("display","");
	  $("#btn-editaccount").css("display","none");
	  $("#bankaccnumber").attr('readonly', false);
}


function getdata(){
	
	  
	  $("#btn-submit").css("display","none");
	  $("#btn-editaccount").css("display","none");
	  $("#errorm").text("");
	  if($("#bankaccnumber").val()==""){
	  		
	  		$("#errorm").text("Please Enter Account Number");
	  		return false;
	  }else{
		  
		  var queryString1 = "serialNumber="+$('#bankaccnumber').val();
			$.getJSON("validacctajx.action", queryString1, function(data) {
					if(data.region!=""){
						//alert(data.region);
						if(data.region=="00"){
							$("#btn-submit").css("display","");
							$("#btn-account").css("display","none");
							$("#btn-editaccount").css("display","");
							$("#accname").val(data.actionname);
							$("#bankaccnumber").attr('readonly', true);
							
						}else if(data.region=="99"){
							$("#errorm").text("Account validation Service not working");
						}else if(data.region=="89"){
							$("#errorm").text("This Account number already Linked");
						}else{
							$("#errorm").text("Invalid Account Number");
						}
					}
			});
	  }
	  		
} 


</script>
<s:set name="command" value="cmd"/>
</head>
<body>
	<form name="form1" id="form1" method="post" action="" >
			<div class="span10" id="create">
			<div>
				<ul class="breadcrumb">
				  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
				  <li> <a href="superagent.action">Super Agent Management</a> <span class="divider"> &gt;&gt; </span></li>
				</ul>
			</div>
			<div class="errors" id="errors"><s:actionerror /></div>
			<s:actionerror cssClass="errors"/>
			<div class="row-fluid sortable"><!--/span-->
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<i class="icon-edit"></i>Super Agent Creation
							<div class="box-icon">
								<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
								<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							</div>
						</div>
						
						<input type="hidden" name="srchcriteria" id="srchcriteria" value='${agent.srchcriteria}'>
						<div id="primaryDetails" class="box-content">
							<fieldset>
								<table width="950" border="0" cellpadding="5" cellspacing="1"
									class="table table-striped table-bordered bootstrap-datatable " id="bank-details">
									
									 <tr class="odd">
										   		<td><label for="Telco Type"><strong>Super Agent Name</strong><font color="red">*</font></label></td>
												<td><input type="text" name="superagentname"  id="superagentname"    >
												</td>
												<td width="25%"><label for="Account Currency Code"><strong>Super Agent Id</strong><font color="red">*</font></label></td>
										<td width="25%">${agent.CBNAgentId}<input type="text" name="CBNAgentId" id="CBNAgentId" value='${agent.CBNAgentId}' readonly></td> 
											 	
										   </tr>
									<tr class="even">
									<td width="25%"><strong><label for="Super Agent Name"><strong>Business Owner Name<font color="red">*</font></strong></label></strong></td>
									<td width="25%"><input type="text" name="accountName" value='${agent.accountName}'></td>
								<td ><label for="Date Of Birth"><strong>Date Of Birth</strong><font color="red">*</font></label></td>
					                        <td ><input type="text" maxlength="10"  class="dob" id="dob" name="dob" required=true  readonly="readonly" value="<s:property value="agent.dob"/>"/></td>
								
										
										
									</tr>
									<tr class="even">
											
										  	    <td ><label for="Gender"><strong>Gender</strong><font color="red">*</font></label>	</td>
												<td ><select name="gender" id="gender" Class="chosen-select">
													<option value="">Select</option>
													<option value="Male">Male</option>
													<option value="Felame">Female</option>
												</select></td>
												<td><strong><label for="Email"><strong>Email</strong></label></strong></td>
										<td><input type="text" name="mailid" id="mailid" ></td>
										   </tr>
									<tr class="odd">
										
										<td ><strong><label for="Mobile"><strong>Mobile<font color="red">*</font></strong></label></strong></td>
										<td>
										<input type="text" name="isocode" id="isocode" style="width:25px;" value="234" disabled />
								<input type="text" class="field" maxlength="10" name="telephone1" id="telephone1"  style="width:172px;"/>
 							<input type="hidden" class="field"  name="mobile" id="mobile"  />
										</td>
										
										 <td><label for="Local Government"><strong>BVN<font color="red">*</font></strong></label></td>
												<td>
												<input  type="text" class="field" id="bvn" name="bvn" maxlength="11" /></td>
											</tr>
								
									
									
										   
										    
										   <tr class="even">
												<td width="25%"><label for="Product"><strong>Product</strong><font color="red">*</font></label></td>
												<td width="25%">
												<select id="product1" name="product1" class="chosen-select-width" >
												 <option value="">Select</option>
												</select>
												</td>
												 <td width="25%"><label for="Description"><strong>Description</strong></label></td>
										       <td width="25%"><div id="desc" ></div>
										       <input type="hidden"  name="product" id="product"  value="" />
												<input type="hidden"  name="prodesc" id="prodesc"  value="" />
										       </td>  
											</tr> 
							
									

								</table>
							 </fieldset>
							</div>


						</div>
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
 						 <td><strong>ID Card Type<font color="red">*</font></strong></td>
						      <td>
						      <s:select cssClass="chosen-select" 
							         headerKey="" 
							         headerValue="Select"
							         list="#{'Driver license':'Driver license','National ID':'National ID','International Passport':'International Passport','Voters Card':'Voters Card','NIN':'NIN'}" 
							         name="idcardtype" 
							         id="idcardtype"
							         requiredLabel="true" 
							         theme="simple"
							         data-placeholder="Choose Account Type..." 
							           />
						    
						      </td>
							<td><strong>ID Card Identity Number<font color="red">*</font></strong></td>
						      <td><input type="text" name="idcardnumber" id="idcardnumber" class="field"     required='true'  />
						    
						      </td>
						</tr>			
										   <tr class="even">
										   		
												
											   
												 <td width="25%"><label for="Address Line 1"><strong>House Number<font color="red">*</font></strong></label></td>
												<td width="25%">
												<input type="text" name="addressLine" id="addressLine" class="field"     required='true'  />
												
												 <td width="25%"><label for="Nationality"><strong>Nationality<font color="red">*</font></strong></label></td>
												<td width="25%">
												<input type="text" name="nationality" id="nationality" class="field"     required='true'  />
	
												</td>
										   </tr>
										   
										    <tr class="odd">
										        
												<td><label for="Local Government"><strong>Street Name<font color="red">*</font></strong></label></td>
												<td>
												<input  type="text" class="field" id="streetname" name="streetname" /></td>
												<td><label for="Local Government"><strong>City<font color="red">*</font></strong></label></td>
												<td>
												<input  type="text" class="field" id="city" name="city" /></td>
											</tr>
										    
										    <tr class="odd">
										        
												
												<td><label for="State"><strong>State<font color="red">*</font></strong></label></td>
												<td>
												
												<select id="stated" name="stated" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select> 
											 <input name="state" type="hidden" class="field" id="state"  />
														 </td>
												<td><label for="Local Government"><strong>Local Government<font color="red">*</font></strong></label></td>
												<td> 
												 <select id="localGovernment1" name="localGovernment1" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select> 
												 <input name="localGovernment" type="hidden" class="field" id="localGovernment"  />
													 </td>
											</tr>

										   <tr class="odd">
										   
										  
												
										    
											
													 
													 <td><label for="State"><strong>Ward Name<font color="red">*</font></strong></label></td>
												<td>
												
												<select id="wardname" name="wardname" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select> 
											
														 </td>
													 
													 <td><label for="Nationality"><strong>Country<font color="red">*</font></strong></label></td>
												<td>
											<input type="text" name="country" id="country" class="field"     required='true' />
												
												</td>
											</tr>
										   
										 
										  

									</table>
								</fieldset>
							</div>
						</div>
				</div>
				
	
			</div>
			
			
			
			
			<s:if test="%{#command == 'VIEW'}">
				<div class="form-actions">
					<input type="button" class="btn btn-danger" name="btn-back" id="btn-back" value="Back" />
				</div>
			</s:if>
			<s:elseif test="%{#command == 'ACTIVEDEACTIVE'}">
				<div class="form-actions">
					<s:if test="#status == A">
						<input type="button" class="btn btn-danger" name="btn-deactive" id="btn-deactive" value="Deactive" />
						<input type="button" class="btn btn-success" name="btn-deactive-cnf" id="btn-deactive-cnf" value="Confirm" style="display:none;"/>
					</s:if>
					<s:elseif test="#status == D">
						<input type="button" class="btn btn-success" name="btn-active" id="btn-active" value="Active" />
						<input type="button" class="btn btn-success" name="btn-active-cnf" id="btn-active-cnf" value="Confirm"  />
					</s:elseif>
				</div>
			</s:elseif>
			<s:else>
			    <div class="form-actions">
				<input type="button" class="btn btn-success" name="btn-submit" id="btn-submit" value="Submit" />&nbsp;
				<input type="button" class="btn btn-danger" name="btn-back" id="btn-back" value="Back" />
				<input name="merchant.password" type="hidden" id="password" />
			 	<input name="merchant.encryptPassword" type="hidden" id="encryptPassword"  />
			 	<input name="merchant.otp" type="hidden" id="otp"  />
			 	<input type="hidden" name="agent.email" id="email" >
				&nbsp;<span id ="error_dlno" class="errors"></span>
			</div>
			</s:else>
	</div>
<input type="hidden" name="status" id="status"/>
</form>
</body>
<script>
 $(function() {
	 
		var config = {
	      '.chosen-select'           : {},
	      '.chosen-select-deselect'  : {allow_single_deselect:true},
	      '.chosen-select-no-single' : {disable_search_threshold:10},
	      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
	      '.chosen-select-width'     : {width:"95%"}
	    }
		
	    for (var selector in config) {
	      $(selector).chosen(config[selector]);
	    } 
	  	 
		 
		  
		/* $('#plasticCode').val(ses); 
		$('#plasticCode').trigger("liszt:updated");  */
	});
 </script>
<script language="Javascript" src="${pageContext.request.contextPath}/js/manual-validation.js"></script>
<script language="Javascript" src="${pageContext.request.contextPath}/js/authenticate.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/sha256.js" ></script>

</html>
