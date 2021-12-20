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

var rules = {
		   rules : {
			   termilstatus : { required : true}
			   
		   },  
		   messages : {
			   termilstatus : { 
			   		required : "Please Select Status."
				}
				
		   } 
		 };

$(function() {  
	
	if($('#ismerchant').val()=="YES"){
		$("#nibtid").css("display","");
	} 
  
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){
		$("#form1").validate(rules);
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/PosRegistrationStatusConf.action'; 
		$("#form1").submit();
		
		
		return true;
	}); 
});

$(function() {
	
if("${responseJSON.status}"=="Active"){
		
		$("#status1").html("<div class='label label-success' >Active</div>");
	}else{
		$("#status1").html("<div class='label label-important' >Deactive</div>");
	}
	 //alert("${responseJSON.terminal_status}");
$("#termilstatus").val("${responseJSON.terminal_status}");
$('#termilstatus').trigger("liszt:updated");
	 
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
  	 
	 
	  
	
});
</script> 

<style type="">
.label {
    padding: 1px 4px 2px;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
    width: 40px;
}
</style>

</head> 
<body>
<form name="form1" id="form1" method="post"> 
		
			<div id="content" class="span10">  
			    <div> 
					<ul class="breadcrumb">
					  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
					   <li> <a href="#">Wallet Agent Terminal Status Update</a>  </li> 
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
							<tr class="even" id="accno" >
							<td width="25%"><label for="From Date"><strong>Core Bank Account Number</strong></label></td>
							<td width="25%">${responseJSON.accountno}<input type="hidden" name="accountno"  id="accountno"   value="${responseJSON.accountno}"   /></td>
							<td width="25%"><strong>User Id</strong></td>
							<td width="25%">${responseJSON.userid}
							<input type="hidden" name="userid"  id="userid"   value="${responseJSON.userid}"   />
							</td>
							</tr>
							<tr class="even" id="accno" >
							<td width="25%"><label for="From Date"><strong>Wallet Account Number</strong></label></td>
							<td width="25%">${responseJSON.walletaccountno}<input type="hidden" name="walletaccountno"  id="walletaccountno"   value="${responseJSON.walletaccountno}"   /></td>
							<td ><strong>Agent Customer Id</strong></td>
							<td >${responseJSON.id}
							<input type="hidden" name="id"  id="id"   value="${responseJSON.id}"   />
							</td>
							</tr>
					 	 <tr class="even">
							<td width="25%"><label for="From Date"><strong>Name</strong></label></td>
							<td width="25%">${responseJSON.fullname}<input type="hidden" name="fullname"  id="fullname"   value="${responseJSON.fullname}"   /></td>
							<td><label for="Product"><strong>Status</strong></label></td>
							<td><div id="status1"></div> <input type="hidden" name="status"  id="status"   value="${responseJSON.status}"   />  </td>
							</tr> 
				 </table>
				</fieldset> 
				
				
				 <div class="row-fluid sortable">
						<div class="box span12">
								<div class="box-header well" data-original-title>
										<i class="icon-edit"></i>Store Details
									<div class="box-icon">
										<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
										<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
									</div>
								</div>

								<div id="communicationDetails" class="box-content">
									<fieldset>
										<table width="950" border="0" cellpadding="5" cellspacing="1"
												class="table table-striped table-bordered bootstrap-datatable ">
										   <tr class="odd">
										   <td width="25%"><label for="Address Line 1"><strong>Store Name</strong></label></td>
												<td width="25%">${responseJSON.storename}
												<input  type="hidden"   name="storename" id="storename" class="field" value="${responseJSON.storename}" />
												</td>
										   <td width="25%"><label for="Address Line 1"><strong>Store Id</strong></label></td>
												<td width="25%">${responseJSON.storeid}
												<input  type="hidden"   name="storeid" id="storeid" class="field" value="${responseJSON.storeid}" /></td>
												
										     	
										   
												
											</tr>
											
										
										  

									</table>
								</fieldset>
							</div>
						</div>
				</div> 
				
				<div class="row-fluid sortable">
						<div class="box span12">
								<div class="box-header well" data-original-title>
										<i class="icon-edit"></i>Terminal Information
									<div class="box-icon">
										<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
										<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
									</div>
								</div>

								<div id="communicationDetails" class="box-content">
									<fieldset>
										<table width="950" border="0" cellpadding="5" cellspacing="1"
												class="table table-striped table-bordered bootstrap-datatable ">
											
											
										   <tr class="even">
										   <td width="25%"><label for="Address Line 1"><strong>Terminal Id</strong></label></td>
												<td width="25%">${responseJSON.terminalid}
												<input  type="hidden"   name="terminalid" id="terminalid" class="field" value="${responseJSON.terminalid}" /></td>
										   		<td width="25%"><label for="IDType"><strong>Terminal Make</strong></label></td>
												<td width="25%">${responseJSON.terminalmake}
												<input type="hidden" name="terminalmake"  id="terminalmake"   value="${responseJSON.terminalmake}"   /></td>
												
										   </tr>
										    

										   <tr class="odd">
										   <td width="25%"><label for="IDNumber"><strong>Model Number</strong></label>	</td>
												<td width="25%">${responseJSON.modelno}
												<input type="hidden" class="field" name="modelnumber" id="modelnumber"  value="${responseJSON.modelno}"/></td>
										   <td ><label for="Address Line 1"><strong>Serial Number</strong></label></td>
												<td >${responseJSON.serialno}
												<input  type="hidden"   name="serialnumber" id="serialnumber" class="field" value="${responseJSON.serialno}" /></td>
												
										     	
										   
												
											</tr>
											
										<tr class="odd">
										<td><label for="Address Line 1"><strong>Status<font color="red">*</font></strong></label></td>
												<td>
												
												<s:select cssClass="chosen-select" 
											         headerKey="" 
											         headerValue="Select"
											         list="#{'Active':'Active','Deactive':'Deactive','Retrive':'Retrive'}" 
											         name="termilstatus" 
											         id="termilstatus"
											         requiredLabel="true" 
											         theme="simple"
											         data-placeholder="Choose Account Type..." 
											           />
												
												</td>
												<td></td>
												<td></td>
										  
									</tr>
									<tr class="odd">
										   <td width="25%"><label for="IDNumber"><strong>Merchant</strong></label>	</td>
												<td width="25%">${responseJSON.ismerchant}
												<input type="hidden" class="field" name="ismerchant" id="ismerchant"  value="${responseJSON.ismerchant}" /></td>
										    <td></td>
										    <td></td>
										   
												
											</tr>
										  <tr class="odd" id="nibtid" style="display:none">
										  
										   <td ><label for="Address Line 1"><strong>NIBBS TID</strong></label></td>
												<td >${responseJSON.merchantid}
												<input  type="hidden"   name="merchantid" id="merchantid" class="field" value="${responseJSON.merchantid}" /></td>
												
										     	 <td></td>
										    <td></td>
										   
												
											</tr>
									</table>
								</fieldset>
							</div>
						</div>
				</div> 
				
				
				
				</div>  
				
	  </div>
	  </div> 
	  
	
										<input type="hidden" name="langugae"  id="langugae"   value="English"  />
			
	
		<div >
			<a href="#" id="btn-back" class="btn btn-danger ajax-link">&nbsp;Home </a>&nbsp;
			<a href="#" id="btn-submit" class="btn btn-success ajax-link">&nbsp;Submit</a>					 
		</div> 
	</div> 	 
 </form>
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
	  	 
	
	});
 </script>
</body> 
</html>