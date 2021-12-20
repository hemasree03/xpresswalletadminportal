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


var acctRules = {
		   rules : {
			   bonusamt: { required : true,regex: /^[0-9. ]+$/},
			   paymentref: { required : true,regex: /^[a-zA-Z0-9 ]+$/},
			   monthyear: { required : true}
		   },  
		   messages : {
			   
			   bonusamt : { 
			   		required : "Please enter Bonus Amount.",
			   		regex : "Bonus Amount, allowed only numbers."
				},
				paymentref: { 
							   		required : "Please enter Payment Reference Number.",
							   		regex : "Payment Reference Number, can not allow special characters."
								},
								monthyear : { 
							   		required : "Please select Month and Year."
								}
				
				
				
						   } 
		 };
		
$.validator.addMethod("regex", function(value, element, regexpr) {          
	 return regexpr.test(value);
}, ""); 

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
		  	 
	
	
	if("${responseJSON.status}"=="Active"){
		$("#userstatus").html("<div class='label label-success' style='width:40px' >Active</div>");	
	}else if("${responseJSON.status}"=="Inactive"){
		$("#userstatus").html("<div class='label label-important' style='width:40px' >Inactive</div>");
	}else{
		$("#userstatus").html("<div class='label label-important' style='width:40px' >Deactive</div>");	
	}
	
	
  
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){
		if($("#years").val()!="" && $("#months").val()!=""){
			$("#monthyear").val($("#months").val()+" "+$("#years").val());
		}
		
		
		$("#form1").validate(acctRules);
		 if($("#form1").valid() ) { 
			 
			 var queryString1 = "serialNumber="+$('#telephone').val()+"&merchantId="+$('#monthyear').val();
				$.getJSON("validagentbonusajx.action", queryString1, function(data) {
						
							//alert(data.region);
							if(data.region=="0"){ 
			
								$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/agentcustomerdetailsbonusconf.action'; 
								$("#form1").submit();
								
								 $(this).prop('disabled', true);
									$("#btn-submit").hide();
								return true;
							 }else{
								$("#errorm").text("Pending for Authorization");
							}
						}); 
		 }
	}); 
});




</script> 
</head> 
<body>
<div id="content" class="span10">  
			    <div> 
					<ul class="breadcrumb">
					  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
					  <li> <a href="#">Agent Bonus Request</a>  </li> 
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
							<td><label for="Product"><strong>User Status</strong></label></td>
							<td id="userstatus">  </td>
							<td></td>
							<td></td>
							</tr>
							<tr class="odd">
						     </td> 
								<td  width="25%"><strong>Wallet Account Number</strong></td>
								<td  width="25%">${responseJSON.walletaccountno} 
								<input type="hidden" value='${responseJSON.walletaccountno}'  name="walletaccountno" id="walletaccountno"  /></td>
						       <td  width="20%"><strong>Wallet Account Balance</strong></td>
							<td  width="30%"> ${responseJSON.walletbalance} </td>
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
							<td width="25%"><label for="First Name"><strong>Bonus Amount</strong><font color="red">*</font></label></td>
							<td width="25%"><input type="text" name="bonusamt"  id="bonusamt"      /></td>
							<td width="20%"><label for="From Date"><strong>Payment Reference Number</strong><font color="red">*</font></label></td>
							<td width="30%"><input type="text" name="paymentref"  id="paymentref"     />  </td>
						</tr> 
							
						<tr class="odd">
							<td width="25%"><label for="First Name"><strong>Bonus Month</strong><font color="red">*</font></label></td>
							<td width="25%" colspan="2"> 
							<s:select cssClass="chosen-select" style="width:120px"
							         headerKey="" 
							         headerValue="Select"
							         list="#{'January':'January', 'February':'February', 'March':'March', 'April':'April', 'May':'May', 'June':'June', 'July':'July', 'August':'August', 'September':'September', 'October':'October', 'November':'November', 'December':'December'}" 
							         name="months" 
							         id="months"
							         requiredLabel="true" 
							         theme="simple"
							         data-placeholder="Choose Account Type..." 
							           />
							         
							           <s:select cssClass="chosen-select" style="width:80px"
							         headerKey="" 
							         headerValue="Select"
							         list="#{'2021':'2021', '2022':'2022', '2023':'2023', '2024':'2024', '2025':'2025', '2026':'2026', '2027':'2027', '2028':'2028', '2029':'2029', '2030':'2030'}" 
							         name="years" 
							         id="years"
							         requiredLabel="true" 
							         theme="simple"
							         data-placeholder="Choose Account Type..." 
							           />
							           <input type="hidden" name="monthyear"  id="monthyear"     />
							</td>
							
							<td width="30%"></td>
						</tr> 	
						
						
						
							
 												
				 </table>
				 
				  
								
								
				</fieldset> 
				</div>
				</div> 
					
			</div> 	 	
			<div > 
			<a href="#" id="btn-back" class="btn btn-danger ajax-link">Home </a>&nbsp;
			<a href="#" id="btn-submit" class="btn btn-success ajax-link" >&nbsp;Submit</a>
			<br></br>
			<div class="errors" id="errorm"></div>	
			<span id ="error_dlno" class="errors"></span>	 
		</div> 	
		</div> 	
		<input type="hidden" name="langugae"  id="langugae"   value="English"  />
</form>	

		
		
	</div> 

</body> 
</html>