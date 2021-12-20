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
.errorm {
color: red;
}
</style>

<script type="text/javascript">
var acctRules = {
		   rules : {
			   txnsamt: { required : true,regex: /^[0-9]+$/ },
			   txndatetime: { required : true},
			   telephone1 : { required : true, regex: /^[0-9]+$/ },
		   },  
		   messages : {
			   
			   txnsamt : { 
			   		required : "Please enter Transaction Amount .",
			   		regex : "First Name, can not allow  characters."
				},				
				txndatetime: { 
							   		required : "Please select Date Of Transaction."
								},
				telephone1: { 
									required : "Please enter Mobile Number.",
							   		regex : "Mobile number allows numbers only"
								},
				
						   } 
		 };
		
$.validator.addMethod("regex", function(value, element, regexpr) {          
	 return regexpr.test(value);
  }, ""); 	
  
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
 
$(function() {   
	

	
	
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){ 
		var finalData = "";
		 var allVals = [];
		 
		
		
		$("#form1").validate(acctRules);
		 if($("#form1").valid() ) {  
			 $("#telephone").val($("#isocode").val()+""+$("#telephone1").val());
			 
			 /*if($("#superagent").val()=="EXPRESS"){
				 
				 $("#superagn").val($("#superagent").val());
			 }else{
				 $("#superagn").val(($("#othsuperagent").val()).split("-")[1]);
			 }*/
			   alert("after validation in valid fun");
			 /*$("#dialog").dialog({
			      buttons : {
			        "Confirm" : function() { 
			        	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/wagentregistrationconf.action';
						$("#form1").submit();
			        },
			        "Cancel" : function() {
			           $(this).dialog("close");
			        }
			      }
			    });*/
				$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/unsettlemetreportinfodata.action';
						$("#form1").submit();
			 
		 } else {
			 //alert("in else");
			 return false;
		 }
		
			/* } 
		});*/
	});
});	





var list = "txndatetime".split(",");
var datepickerOptions = {
				showTime: true,
				showHour: true,
				showMinute: true,
	  		dateFormat:'dd-mm-yy',
	  		alwaysSetTime: false,
	  		//yearRange: '-110:-18',
			yearRange: '2000:2050',
	  		//maxDate: '-18Y',
			changeMonth: true,
			changeYear: true
};



$(function() {
	alert("before callig date picker");
	 $(list).each(function(i,val){
		$('.'+val).datepicker(datepickerOptions);
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
	 
	 
	 $('#superagent').on('change', function() {
		 
		 $('#sup3').css("display","");
			$('#sup4').css("display","");
			$('#sup1').css("display","none");
			$('#sup2').css("display","none");
			
			$('#productcode').val("");
  			$('#productdesc').val("");
  			$('#prdcode').text("");
  			$('#prddesc').text("");
			
			//alert($('#state').val());
			if($('#superagent').val()=="Others"){
				
				$('#sup3').css("display","none");
				$('#sup4').css("display","none");
				$('#sup1').css("display","");
				$('#sup2').css("display","");
				
				 var queryString1 = "sid=SUPERAGENT";
				$.getJSON("branchcodeajx.action", queryString1, function(data) {
						if(data.region!=""){
							var mydata=data.region;
			  			var mydata1=mydata.split(":");
			  			
			  			
			  			$('#othsuperagent').empty();
						$('#othsuperagent').trigger("liszt:updated");
						
					
						
			   			$.each(mydata1, function(i, v) {
			   				
			   				var options = $('<option/>', {value: mydata.split(":")[i], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
			   				
			   				$('#othsuperagent').append(options);
			   				$('#othsuperagent').trigger("liszt:updated");
			   			});
			   			
			   			
			  		} 
			 	});  
			}else{
				
				
				 var queryString1 = "sid=EXPRD";
					$.getJSON("branchcodeajx.action", queryString1, function(data) {
							if(data.region!=""){
								var mydata=data.region;
				  			/* var mydata1=mydata.split(":")[1];
				  			//alert(mydata1);
				  			$('#productcode').val(mydata1.split("-")[0]);
				  			$('#productdesc').val(mydata1.split("-")[1]);
				  			$('#prdcode').text(mydata1.split("-")[0]);
				  			$('#prddesc').text(mydata1.split("-")[1]); */
				  			
				  			
				  			var mydata1=mydata.split(":");
				  			
				  			
				  			$('#productcode1').empty();
							$('#productcode1').trigger("liszt:updated");
							
						
							
				   			$.each(mydata1, function(i, v) {
				   				
				   				var options = $('<option/>', {value: mydata.split(":")[i], text: (mydata.split(":")[i]).split("-")[0]}).attr('data-id',i);
				   				
				   				$('#productcode1').append(options);
				   				$('#productcode1').trigger("liszt:updated");
				   			});
				   			
				  		} 
				 	});  
				
			}
		});
	
	 $('#othsuperagent').on('change', function() {
		 
		 $('#productcode').val("");
			$('#productdesc').val("");
			$('#prdcode').text("");
			$('#prddesc').text("");
		
		 var queryString1 = "sid=SUPPRD&serialNumber="+$('#othsuperagent').val();
			$.getJSON("branchcodeajx.action", queryString1, function(data) {
					if(data.region!=""){
						var mydata=data.region;
		  			/* var mydata1=mydata.split(":")[1];
		  			//alert(mydata1);
		  			$('#productcode').val(mydata1.split("-")[0]);
		  			$('#productdesc').val(mydata1.split("-")[1]);
		  			$('#prdcode').text(mydata1.split("-")[0]);
		  			$('#prddesc').text(mydata1.split("-")[1]);
		  			 */
		  			
		  			var mydata1=mydata.split(":");
		  			
		  			
		  			$('#productcode1').empty();
					$('#productcode1').trigger("liszt:updated");
					
				
					
		   			$.each(mydata1, function(i, v) {
		   				
		   				var options = $('<option/>', {value: mydata.split(":")[i], text: (mydata.split(":")[i]).split("-")[0]}).attr('data-id',i);
		   				
		   				$('#productcode1').append(options);
		   				$('#productcode1').trigger("liszt:updated");
		   			});
		   			
		  		} 
		 	});  
		 
	 });
	 
	 $('#productcode1').on('change', function() {
		// alert(($('#productcode1').val()).split("-")[1]);
		 $('#productdesc').val(($('#productcode1').val()).split("-")[1]);
		 $('#prddesc').text(($('#productcode1').val()).split("-")[1]);
		 $('#productcode').val(($('#productcode1').val()).split("-")[0]);
	 });
	
});

var loadFile = function(event) {
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
  };
  
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
						}else{
							$("#errorm").text("Invalid Account Number");
						}
					}
			});
	  }
	  		
} 
</script>

</head> 

<body>

		
			<div id="content" class="span10">  
			    <div> 
					<ul class="breadcrumb">
					  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
					  <li> <a href="#">Transaction Details</a>  </li> 
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
		<div class="row-fluid sortable"> 
			<div class="box span12"> 
					<div class="box-header well" data-original-title>
							<i class="icon-edit"></i>Transaction Details
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
							<td width="25%"><label for="From Date"><strong>Transaction Amount <font color="red">*</font></strong></label></td>
							<td width="25%">
							<input type="text" name="txnsamt"  id="txnsamt"     /></td>
							<!--<td width="25%"><label for="From Date"><strong>Middle Name</strong></label></td>
						    <td width="25%"><input type="text" name="middlename"  id="middlename"     /></td>
							
							</tr>
						<tr class="even">
						<td width="25%"><label for="From Date"><strong>Last Name<font color="red">*</font></strong></label></td>
						    <td width="25%"><input type="text" name="lastname"  id="lastname"     /></td> -->
						<td  ><label for="To Date"><strong>Date Of Transaction<font color="red">*</font></strong></label> </td>
							<td>
							<input type="text" maxlength="10"  class="txndatetime" id="txndatetime" name="txndatetime"  readonly="readonly" />
							</td>
						
							
							  
						</tr>
						<tr class="odd">
						
							<!--<td ><label for="From Date"><strong>Email ID</strong></label></td>
							<td ><input type="text" name="email"  id="email"      />  </td> -->
						   <td><label for="To Date"><strong>User Id <font color="red">*</font></strong></label> </td> 
					       <td >
					    
								<input type="text" name="isocode" id="isocode" style="width:25px;" value="234" disabled />
								<input type="text" class="field" maxlength="10" name="telephone1" id="telephone1"  style="width:172px;"/>
 							<input type="hidden" class="field"  name="telephone" id="telephone"  />
						     
						   </td> 
						   <td></td>
						    <td></td>
						    
 						</tr>
		<!--				
 						<tr class="even" >
 						 <td><strong>Gender<font color="red">*</font></strong></td>
						      <td>
						      <s:select cssClass="chosen-select" 
							         headerKey="" 
							         headerValue="Select"
							         list="#{'Male':'Male','Female':'Female'}" 
							         name="gender" 
							         id="gender"
							         requiredLabel="true" 
							         theme="simple"
							         data-placeholder="Choose Account Type..." 
							           />
						    
						      </td>
							<td><strong>Marital Status<font color="red">*</font></strong></td>
						      <td>
						      <s:select cssClass="chosen-select" 
							         headerKey="" 
							         headerValue="Select"
							         list="#{'Married':'Married','Single':'Single'}" 
							         name="marital" 
							         id="marital"
							         requiredLabel="true" 
							         theme="simple"
							         data-placeholder="Choose Account Type..." 
							           />
						    
						      </td>
						</tr>
						
						<tr class="even">
						
							<td ><label for="From Date"><strong>Merchant Category<font color="red">*</font></strong></label></td>
							<td >
							 <select id="merchanttype" name="merchanttype" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select>  
							</td>
						   <td><label for="To Date"><strong>Business Name<font color="red">*</font></strong></label> </td>
					       <td ><input type="text" class="field"  name="businessname" id="businessname"  />
 							
						     
						   </td> 
						    
 						</tr>
 						<tr class="even">
						
							<td ><label for="From Date"><strong>Branch<font color="red">*</font></strong></label></td>
							<td >
							 <select id="branch" name="branch" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select>  
							</td>
						  <td><label for="To Date"><strong>BVN<font color="red">*</font></strong></label> </td>
					       <td ><input type="text" class="field"  name="bvn" id="bvn" maxlength="11" />
						    
 						</tr>
 						 <tr class="even">
						
							<td ><label for="From Date"><strong>Super Agent<font color="red">*</font></strong></label></td>
							<td >
							<select id="superagent" name="superagent" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
																<option value="EXPRESS">EXPRESS</option>
																<!-- <option value="Others">EXPRESS SUPER AGENT</option> 
															</select> 
							<input type="hidden" class="field"  name="superagn" id="superagn"  />	 
							</td>
						   <td id="sup1" style="display:none"><label for="From Date"><strong>Select Super Agent<font color="red">*</font></strong></label></td>
					       <td id="sup2" style="display:none">
					       <select id="othsuperagent" name="othsuperagent" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select>  
					       </td> 
						   <td id="sup3"></td> 
						   <td id="sup4"></td> 
 						</tr>
 						
 						<tr class="even">
						
							<td ><label for="From Date"><strong>Product Code<font color="red">*</font></strong></label></td>
							<td >
							
							 <select id="productcode1" name="productcode1" class="chosen-select-width" style="width: 220px;">
																<option value="">Select</option>
															</select> 
							<input type="hidden" class="field"  name="productcode" id="productcode"  />								
							</td>
						   <td><label for="To Date"><strong>Product Description</strong></label> </td>
					       <td ><div id="prddesc"></div>
					       <input type="hidden" class="field"  name="productdesc" id="productdesc"  /></td> 
						    
 						</tr> 
 						<tr class="odd">
						
							
						 <td><label for="Stop Id"><strong>Staff Code<font color="red">*</font></strong></label> </td>
					       <td ><input type="text" class="field"  name="staffcode" id="staffcode"  />
 							
						     
						   </td> 
						  
						  <td></td>
						  <td></td>
 						</tr>-->
 												
				 </table>
				 
				<!-- <input type="file" accept="image/*" onchange="loadFile(event)"></input>
<img id="output"/> -->
			<!--	  
				 
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
												<input type="text" name="addressLine" id="addressLine"  class="field"     required='true' />
												</textarea></td>
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
				
				
				 
				 
				 
				  
						        
								<input type="hidden" name="langugae"  id="langugae"   value="English"  />
								
				</fieldset> 
				</div>
				</div> 
					
				 	
				
		</div> 	-->
</form>	

<div id="dialog" title="Confirmation Required" style="display:none">
		   Proceed ? <div id="dia1"></div><font color="red"><div id="dia2"></div></font>
		</div>
		<div > 
			<a href="#" id="btn-back" class="btn btn-danger ajax-link">Home </a>&nbsp;
			<a href="#" id="btn-submit" class="btn btn-success ajax-link" >&nbsp;Submit</a>	
			<span id ="error_dlno" class="errors"></span>	 
		</div> 
		
	</div> 
 <script type="text/javascript">
$(function(){
	
	
	
	$('#accountNumber').live('keypress',function(){
		//console.log($(this).length);
		if($(this).length == 0){
			$('#billerMsg').text('');
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
});  
</script>
</body> 
</html>