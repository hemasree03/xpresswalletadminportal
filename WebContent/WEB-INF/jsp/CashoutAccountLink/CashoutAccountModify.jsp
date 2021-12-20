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
			   bankname: { required : true},
			   bankaccnumber: { required : true},
			   bankcustname: { required : true}
			  
		   },  
		   messages : {
			   bankname: { 
			   		required : "Please select Bank Name."
				},
			bankaccnumber: { 
						   		required : "Please enter Bank Account Number."
							},
			bankcustname: { 
						   		required : "Please enter Account Name."
							}
		   } 
		 }; 
		
	
$(function() {  
	
	$('#bvn').on("keypress", function (e) {
		  
	    if (e.which != 8 && e.which != 0 && ((e.which < 48 || e.which > 57) && e.which != 46) ) {
	        e.preventDefault();
	    }
	});	 
	
	
	
	
	



 $('#bankaccnumber').on("keypress", function (e) {
	  
	    if (e.which != 8 && e.which != 0 && ((e.which < 48 || e.which > 57) && e.which != 46) ) {
	        e.preventDefault();
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
			
			
		
			
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){
		$("#form1").validate(acctRules);
		 if($("#form1").valid() ) {  
		
			 $("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/cashoutmodifycnfrm.action'; 
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
							$("#bankcustname").val(data.actionname);
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
</head>
<body>
	<form name="form1" id="form1" method="post">

		<div id="content" class="span10">
			<div>
				<ul class="breadcrumb">
					<li><a href="home.action">Home</a> <span class="divider">
							&gt;&gt; </span></li>
					<li><a href="#">Cashout Account Link Details</a></li>
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

									<tr class="odd">
							<td width="25%"><label for="fname"><strong>First Name <font color="red">*</font></strong></label></td>
									<td width="30%">${responseJSON.firstname}
									<input type="hidden" name="fname" id="fname" value="${responseJSON.firstname}" /></td>
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
					       <td  width="30%" >${responseJSON.telephone}<input type="hidden" value='${responseJSON.telephone}' style="width:155px;"  name="telephone" id="telephone"  /> 
 							 </td>
						</tr>
						<tr class="even">
						     </td> 
								<td  width="25%"><strong>Gender</strong></td>
								<td  width="25%">${responseJSON.gender} 
								<input type="hidden" value='${responseJSON.gender}' style="width:155px;"  name="gender" id="gender"  /></td>
						 <td><label for="To Date"><strong> Marital Status</strong></label></td>
									<td>${responseJSON.marital}
									<input type="hidden" value='${responseJSON.marital}' name="marital" id="marital" /></td>
							</tr>
 						
 						 <tr class="odd">
						     
								<td  width="25%"><strong>Merchant Category</strong></td>
								<td  width="25%">${responseJSON.mcc} 
								<input type="hidden" value='${responseJSON.mcc}' style="width:155px;"  name="mcc" id="mcc"  /></td>
					<td><label for="Business Name"><strong>Business Name</strong></label></td>
								<td>${responseJSON.business}
								<input type="hidden" value='${responseJSON.business}' name="business" id="business" /></td>		
								
									</tr>
										 <tr class="even">
										<td><label for="BVN"><strong>BVN</strong></label></td>
								<td>${responseJSON.bvn}
								<input type="hidden" value='${responseJSON.bvn}' name="bvn" id="bvn" /></td>		
						
						<td></td>
						<td></td>
						</tr>
									
							</table>
						</fieldset>

					

						<div class="row-fluid sortable">
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
												class="table table-striped table-bordered bootstrap-datatable ">
												
										<tr class="even" >
 						 <td width="25%"><strong>Bank Name<font color="red">*</font></strong></td>
						      <td width="25%">${responseJSON.bankname}
						      <input type="hidden" value='${responseJSON.bankname}' name="bankname" id="bankname" /></td>
						   
						    
							<td width="25%"><strong>Bank Account Number<font color="red">*</font></strong></td>
						      <td width="25%">
						      <input type="text"  value='${responseJSON.accountno}' name="accountno" id="accountno" class="field"   maxlength="10"  required='true'  />
						    <input type="button" class="btn btn-success" name="btn-account" id="btn-account" value="Check Account Number"  onclick="getdata()"></input>
						    <input type="button" class="btn btn-success" name="btn-editaccount" id="btn-editaccount" value="Edit Account Number"  onclick="getdata1()" style="display:none"></input>
						    <div class="errorm" id="errorm"></div>
						      </td>
						</tr>			
										   
										   
										    <tr class="odd">
										        
												<td><label for="Account Name"><strong>Account Name<font color="red">*</font></strong></label></td>
												<td>
												<input  type="text" class="field" value='${responseJSON.bankcustname}'   id="bankcustname" name="bankcustname" /></td>
										
										<td></td>
										<td></td>
											</tr>
										    
								</table>
										</fieldset>

								
							</div>
							<input type="hidden" name="langugae" id="langugae" value="English" />
							<input type="hidden" name="customercode" id="customercode" value="${responseJSON.customercode}" />
</div></div>
				</div>
							<div > 
			<a href="#" id="btn-back" class="btn btn-danger ajax-link">Home </a>&nbsp;
			<a href="#" id="btn-submit" class="btn btn-success ajax-link" style="display:none" >&nbsp;Submit</a>	
			<span id ="error_dlno" class="errors"></span>	 
		</div> 

						

					





			</div>
	</form>

</body>
</html>