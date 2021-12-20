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
	
	
	if("${responseJSON.status}"=="Active"){
		$("#userstatus").html("<div class='label label-success' style='width:40px' >Active</div>");	
	}else if("${responseJSON.status}"=="Inactive"){
		$("#userstatus").html("<div class='label label-important' style='width:40px' >Inactive</div>");
	}else{
		$("#userstatus").html("<div class='label label-important' style='width:40px' >Deactive</div>");	
	}
	
	if("${responseJSON.mobile_status}"=="Active"){
		$("#mobile").html("<div class='label label-success' style='width:40px' >Active</div>");	
	}else{
		$("#mobile").html("<div class='label label-important' style='width:40px' >Deactive</div>");	
	}
	
	if("${responseJSON.ussd_status}"=="Active"){
		$("#ussd").html("<div class='label label-success' style='width:40px'>Active</div>");	
	}else{
		$("#ussd").html("<div class='label label-important' style='width:40px' >Deactive</div>");	
	}
	
	if("${responseJSON.pos_status}"=="Active"){
		$("#pos").html("<div class='label label-success' style='width:40px' >Active</div>");	
	}else{
		$("#pos").html("<div class='label label-important' style='width:40px' >Deactive</div>");	
	}
	
	if("${responseJSON.mobile_status}"=="Active"){
		$("#mobile").html("<div class='label label-success' style='width:40px' >Active</div>");	
	}else{
		$("#mobile").html("<div class='label label-important' style='width:40px' >Deactive</div>");	
	}
  
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/agentservice.action'; 
		$("#form1").submit();
		
		 $(this).prop('disabled', true);
			$("#btn-submit").hide();
		return true;
	}); 
});


$(document).ready(function () {
	var tds=new Array();
	var merchantData ='${responseJSON.Files_List}';
	//alert(merchantData);

	var json = jQuery.parseJSON(merchantData);
	console.log(json);
	
	var val = 1;
	var rowindex = 0;
	var colindex = 0;
	var addclass = "";
	var i=0;
	
	
	

	$.each(json, function(i, v) {
		if(val % 2 == 0 ) {
			addclass = "even";
			val++;
		}
		else {
			addclass = "odd";
			val++;
		}  
		var rowCount = $('#merchantTBody > tr').length;

		
		colindex = ++ colindex; 
		
		
		var STORE_NAME=(v.STORE_NAME == undefined) ? "" : v.STORE_NAME;
		var STORE_ID=(v.STORE_ID == undefined) ? "" : v.STORE_ID;
		var ADDRESS=(v.ADDRESS == undefined) ? "" : v.ADDRESS;
		var LOC_GOV=(v.LOC_GOV == undefined) ? "" : v.LOC_GOV;
		var STATE=(v.STATE == undefined) ? "" : v.STATE;
		var SSTATUS=(v.SSTATUS == undefined) ? "" : v.SSTATUS;
		var COREACCNO=(v.COREACCNO == undefined) ? "" : v.COREACCNO;
		
		index=colindex-1;
		
		var tabData="DataTables_Table_0";
			
		
		
		
		
		var vsstatus="";
		
		if(SSTATUS=="ACTIVE"){
			
			vsstatus="<div class='label label-success' >Active</div>";
		}else{
			vsstatus="<div class='label label-important' >Deactive</div>";
		}
		
		
		
		var appendTxt = "<tr class="+addclass+" id='"+rowindex+"' index='"+rowindex+"'  > "+
			"<td >"+colindex+"</td>"+
			"<td >"+STORE_NAME+"</span></td>"+
			"<td>"+STORE_ID+"</span></td>"+
			"<td>"+ADDRESS+"</span></td>"+
			"<td >"+LOC_GOV+"</span></td>"+
			"<td >"+STATE+"</span></td>"+
			"<td >"+vsstatus+"</span></td>"+
			"</tr>";
			
			i++;
			$("#seachdetails").append(appendTxt);	
			rowindex = ++rowindex;
			
			
			
	}); 
	

	

	
}); 

$(document).ready(function () {
	var tds=new Array();
	var merchantData1 ='${responseJSON.Files_List1}';
	//alert(merchantData1);

	var json1 = jQuery.parseJSON(merchantData1);
	console.log(json1);
	
	var val = 1;
	var rowindex = 0;
	var colindex = 0;
	var addclass = "";
	var i=0;
	
	
	

	$.each(json1, function(i, v) {
		if(val % 2 == 0 ) {
			addclass = "even";
			val++;
		}
		else {
			addclass = "odd";
			val++;
		}  
		var rowCount = $('#merchantTBody > tr').length;

		
		colindex = ++ colindex; 
		
		
		var TERMINAL_ID=(v.TERMINAL_ID == undefined) ? "" : v.TERMINAL_ID;
		var SERIAL_NO=(v.SERIAL_NO == undefined) ? "" : v.SERIAL_NO;
		var TERMINAL_MAKE=(v.TERMINAL_MAKE == undefined) ? "" : v.TERMINAL_MAKE;
		var MODEL_NO=(v.MODEL_NO == undefined) ? "" : v.MODEL_NO;
		var MAKER_DTTM=(v.MAKER_DTTM == undefined) ? "" : v.MAKER_DTTM;
		var STATUS=(v.STATUS == undefined) ? "" : v.STATUS;
		var TSTORE_ID=(v.TSTORE_ID == undefined) ? "" : v.TSTORE_ID;
		
		index=colindex-1;
		
		var tabData="DataTables_Table_1";
			
		
		
		
		
		var vsstatus="";
		
		if(STATUS=="ACTIVE"){
			
			vsstatus="<div class='label label-success' >Active</div>";
		}else if(STATUS=="PROCESS"){
			vsstatus="<div class='label label-success' >Process</div>";
		}else if(STATUS=="RETRIVAL"){
			vsstatus="<div class='label label-important' >Retrival</div>";
		}else{
			vsstatus="<div class='label label-important' >Deactive</div>";
		}
		
		
		
		var appendTxt1 = "<tr class="+addclass+" id='"+rowindex+"' index='"+rowindex+"'  > "+
				"<td >"+colindex+"</td>"+
				"<td >"+TSTORE_ID+"</span></td>"+
				 "<td >"+TERMINAL_ID+"</span></td>"+
				 "<td>"+TERMINAL_MAKE+"</span></td>"+
				 "<td>"+MODEL_NO+"</span></td>"+
				 "<td >"+SERIAL_NO+"</span></td>"+
				 "<td >"+MAKER_DTTM+"</span></td>"+
				 "<td >"+vsstatus+"</span></td>"+ 
				"</tr>";
			
			i++;
			//alert(appendTxt1);
			$("#terminaldetails").append(appendTxt1);	
			rowindex = ++rowindex;
			
			
			
	}); 
	

	

	
}); 

$(document).ready(function () {
	var tds=new Array();
	var merchantData2 ='${responseJSON.Files_List2}';
	//alert(merchantData2);

	var json2 = jQuery.parseJSON(merchantData2);
	console.log(json2);
	
	var val = 1;
	var rowindex = 0;
	var colindex = 0;
	var addclass = "";
	var i=0;
	
	
	

	$.each(json2, function(i, v) {
		if(val % 2 == 0 ) {
			addclass = "even";
			val++;
		}
		else {
			addclass = "odd";
			val++;
		}  
		var rowCount = $('#merchantTBody > tr').length;

		
		colindex = ++ colindex; 
		
		
		var MAC_ADDR=(v.MAC_ADDR == undefined) ? "" : v.MAC_ADDR;
		var DEVICE_IP=(v.DEVICE_IP == undefined) ? "" : v.DEVICE_IP;
		var IMEI_NO=(v.IMEI_NO == undefined) ? "" : v.IMEI_NO;
		var SERIAL_NO=(v.SERIAL_NO == undefined) ? "" : v.SERIAL_NO;
		var VERSION=(v.VERSION == undefined) ? "" : v.VERSION;
		var DEVICE_TYPE=(v.DEVICE_TYPE == undefined) ? "" : v.DEVICE_TYPE;
		var TRANS_DTTM=(v.TRANS_DTTM == undefined) ? "" : v.TRANS_DTTM;
		var MSTATUS=(v.MSTATUS == undefined) ? "" : v.MSTATUS;
		
		index=colindex-1;
		
		var tabData="DataTables_Table_2";
			
		
		
		
		
		var vsstatus="";
		
		if(MSTATUS=="Active"){
			
			vsstatus="<div class='label label-success' >Active</div>";
		}else{
			vsstatus="<div class='label label-important' >Deactive</div>";
		}
		
		
		
		var appendTxt1 = "<tr class="+addclass+" id='"+rowindex+"' index='"+rowindex+"'  > "+
				"<td >"+MAC_ADDR+"</span></td>"+
				 "<td >"+DEVICE_IP+"</span></td>"+
				 "<td>"+IMEI_NO+"</span></td>"+
				 "<td>"+SERIAL_NO+"</span></td>"+
				 "<td >"+VERSION+"</span></td>"+
				 "<td >"+DEVICE_TYPE+"</span></td>"+
				 "<td >"+TRANS_DTTM+"</span></td>"+
				 "<td >"+vsstatus+"</span></td>"+ 
				"</tr>";
			
			i++;
			//alert(appendTxt1);
			$("#imeidetails").append(appendTxt1);	
			rowindex = ++rowindex;
			
			
			
	}); 
	

	

	
}); 

</script> 
</head> 
<body>
<div id="content" class="span10">  
			    <div> 
					<ul class="breadcrumb">
					  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
					  <li> <a href="#">Agent Details View</a>  </li> 
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
						     </td> 
								<td  width="25%"><strong>Gender</strong></td>
								<td  width="25%">${responseJSON.gender} 
								<input type="hidden" value='${responseJSON.gender}' style="width:155px;"  name="gender" id="gender"  /></td>
						       <td  width="20%"><label for="Marital Status"><strong>Marital Status</strong></label></td>
							<td  width="30%">${responseJSON.martStatus} <input type="hidden" name="martStatus"  id="martStatus"   value="${responseJSON.martStatus}"   />  </td>
						</tr>
 						
 						 <tr class="odd">
						     </td> 
								<td  width="25%"><strong>Merchant Category</strong></td>
								<td  width="25%">${responseJSON.mcc} 
								<input type="hidden" value='${responseJSON.mcc}' style="width:155px;"  name="mcc" id="mcc"  /></td>
						       <td  width="20%"><label for="Businessname"><strong>Business Name</strong></label></td>
							<td  width="30%">${responseJSON.bsnname} <input type="hidden" name="bsnname"  id="bsnname"   value="${responseJSON.bsnname}"   />  </td>
						</tr>
						
						 <tr class="even">
							<td><label for="Product"><strong>Product</strong></label></td>
							<td>${responseJSON.product} <input type="hidden" name="product"  id="product"   value="${responseJSON.product}"   />  </td>
							<td><strong>Product Description </strong></td>
							<td>${responseJSON.prodesc}</td>
							</tr>
							
							<tr class="even">
							<td><label for="Product"><strong>User Status</strong></label></td>
							<td id="userstatus">  </td>
							<td><label for="Product"><strong>Onboard Date</strong></label></td>
							<td>${responseJSON.onboarddate}</td>
							</tr>
							<tr class="odd">
						     </td> 
								<td  width="25%"><strong>Wallet Account Number</strong></td>
								<td  width="25%">${responseJSON.walletaccountno} </td>
						       <td  width="20%"><strong>Wallet Account Balance</strong></td>
							<td  width="30%"> ${responseJSON.walletbalance} </td>
						</tr>
						<tr class="odd">
						     </td> 
								<td  width="25%"><strong>Unpaid Commission Amount</strong></td>
								<td  width="25%">${responseJSON.commissionunst} </td>
						       <td  width="25%"><strong>BVN</strong></td>
								<td  width="25%">${responseJSON.bvn} 
								<input type="hidden" value='${responseJSON.bvn}' style="width:155px;"  name="bvn" id="bvn"  /></td>
						</tr>
						
							
 												
				 </table>
				 
				<div class="row-fluid sortable"> 
		<div class="box span12"> 
			<div class="box-header well" data-original-title>
					<i class="icon-edit"></i>Channel Status
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
				</div>
			</div>  
				<div class="box-content">
				<fieldset> 
						 <table width="100%" class="table table-striped table-bordered " 
							id="DataTables_Table_0" >
							<thead  >
								<tr >
									<th width="10%">Channel</th>
									<th width="10%">Status</th>
									<th colspan="2" width="20%"></th>
									
									
								</tr>
								
							</thead>
									<tr class="even"  >
										<td><strong>Mobile</strong></label></td>
										<td id="mobile"></td>
										<td colspan="2"></td>
										
									</tr> 
									<tr class="even"  >
										<td><strong>USSD</strong></label></td>
										<td id="ussd"></td>
										<td colspan="2"></td>
										
									</tr>   
									<tr class="even"  >
										<td><strong>POS</strong></label></td>
										<td id="pos"></td>
										<td colspan="2"></td>
									</tr>     
						</table>
				</fieldset>
				
				</div>
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
															
					 <tr class="odd">
						<td width="25%"><label for="Id Card Type"><strong>Id Card Type</strong></label></td>
						<td width="25%">${responseJSON.idtype}<input type="hidden" name="idtype"  id="idtype" value="${responseJSON.idtype}"   /></td>
						<td  width="25%"><label for="Id Card Number"><strong>Id Card Number</strong></label></td>
						<td  width="25%">${responseJSON.iddetails} <input type="hidden" name="iddetails"  id="iddetails"   value="${responseJSON.iddetails}"   />  </td>
					</tr> 
					   
                       <tr class="even">
								<td width="25%"><label for="Address"><strong>House Number</strong></label></td>
								<td width="25%">${responseJSON.address}<input type="hidden" name="address"  id="address" value="${responseJSON.address}"   /></td>
								<td  width="25%"><label for="To Date"><strong>Nationality</strong></label></td>
								<td  width="25%">${responseJSON.nationality} <input type="hidden" name="nationality"  id="nationality"   value="${responseJSON.nationality}"   />  </td>
					</tr>
										    
										<tr class="odd">
											<td width="25%"><label for="Street Name"><strong>Street Name</strong></label></td>
											<td width="25%">${responseJSON.area}<input type="hidden" name="area"  id="area" value="${responseJSON.area}"   /></td>
											<td  width="25%"><label for="City"><strong>City</strong></label></td>
											<td  width="25%">${responseJSON.city} <input type="hidden" name="city"  id="city"   value="${responseJSON.city}"   />  </td>
										</tr>

									 <tr class="even">
											<td  width="25%"><label for="State"><strong>State</strong></label></td>
											<td  width="25%">${responseJSON.state} <input type="hidden" name="state"  id="state"   value="${responseJSON.state}"   />  </td>
											<td width="25%" ><label for="Local Government"><strong>Local Government</strong></label> </td>
											<td  width="25%" >${responseJSON.lga}<input type="hidden" value='${responseJSON.lga}' style="width:155px;" maxlength="13" name="telephone" id="telephone"  /> 
											</td>
										</tr>
										 
								 <tr class="odd">
										<td  width="25%"><label for="WardName"><strong>Ward Name</strong></label></td>
										<td  width="25%">${responseJSON.wardname} <input type="hidden" name="wardname"  id="wardname"   value="${responseJSON.wardname}"  />  </td>
										<td  width="25%"><label for="Country"><strong>Country</strong></label></td>
										<td  width="25%">${responseJSON.country} <input type="hidden" name="country"  id="country"   value="${responseJSON.state}"   />  </td>

				                 </tr>	  

									</table>
								</fieldset>
							</div>
						</div>
				</div>
				
				<div class="row-fluid sortable">
						<div class="box span12">
								<div class="box-header well" data-original-title>
										<i class="icon-edit"></i>Account Details
									<div class="box-icon">
										<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
										<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
									</div>
								</div>

								<div id="communicationDetails" class="box-content">
									<fieldset>
										<table width="950" border="0" cellpadding="5" cellspacing="1"
												class="table table-striped table-bordered bootstrap-datatable ">
												
										
 						 
				          <tr class="even" id="accno" >
							<td width="25%"><label for="From Date"><strong>Core Bank Account Number</strong></label></td>
							<td width="25%">${responseJSON.accountno}<input type="hidden" name="accountno"  id="accountno"   value="${responseJSON.accountno}"   /></td>
							
				           <td width="25%"><label for="Bank Name"><strong>Bank Name</strong></label></td>
						  <td width="25%">${responseJSON.bankname}<input type="hidden" name="bankname"  id="bankname"   value="${responseJSON.bankname}"   /></td>
					    </tr>		
										   
										   
					 <tr class="odd">
							
							<td width="25%"><label for="AccountName"><strong>Account Name</strong></label></td>
						  <td width="25%">${responseJSON.accountname} <input type="hidden" name="accountname"  id="accountname"   value="${responseJSON.accountname}"   />  </td>
							<td width="25%"></td>
						  <td width="25%"></td>
						  </tr>
										    
								</table>
								</fieldset>
							</div>
						</div>
				</div>
				
				<div class="row-fluid sortable">
						<div class="box span12">
								<div class="box-header well" data-original-title>
										<i class="icon-edit"></i>Cash Out Account Details
									<div class="box-icon">
										<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
										<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
									</div>
								</div>

								<div id="communicationDetails" class="box-content">
									<fieldset>
										<table width="950" border="0" cellpadding="5" cellspacing="1"
												class="table table-striped table-bordered bootstrap-datatable ">
												
										
 						 
				          <tr class="even" id="accno" >
							<td width="25%"><label for="From Date"><strong>Account Number</strong></label></td>
							<td width="25%">${responseJSON.cbankacc}<input type="hidden" name="cbankacc"  id="cbankacc"   value="${responseJSON.cbankacc}"   /></td>
							
				           <td width="25%"><label for="Bank Name"><strong>Bank Name</strong></label></td>
						  <td width="25%">${responseJSON.cbankname}<input type="hidden" name="cbankname"  id="cbankname"   value="${responseJSON.cbankname}"   /></td>
					    </tr>		
										   
										   
					 
										    
								</table>
								</fieldset>
							</div>
						</div>
				</div>
				 
				 <div class="row-fluid sortable">
						<div class="box span12">
				<div class="box-header well" data-original-title>Store Details
					  <div class="box-icon"> 
						<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
						<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a> 
					  </div>
					</div>
					<div id="communicationDetails" class="box-content">
						<table width="100%" class="table table-striped table-bordered bootstrap-datatable dataTable" 
							id="DataTables_Table_0" >
							<thead  >
								<tr >
									<th width="5%">SNo</th>
									<th width="10%">Store Name</th>							
									<th width="10%">Store Id</th>
									<th width="10%">Address</th>
									<th width="10%">Local Government</th>
									<th width="10%">State</th>
									<th width="10%">Store Status</th>
									
								</tr>
								
							</thead>
							
						

							<tbody  id="seachdetails">
							</tbody>
							
						</table>
						</div>
						</div>
				</div>
						<br></br> 
					<div class="row-fluid sortable">
						<div class="box span12">	
						<div class="box-header well" data-original-title>Terminal Details
						  <div class="box-icon"> 
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a> 
						  </div>
						</div>
						<div id="communicationDetails" class="box-content">
						<table width="100%" class="table table-striped table-bordered bootstrap-datatable dataTable" 
							id="DataTables_Table_1" >
							<thead  >
								<tr >
									<th width="5%">SNo</th>
									<th width="10%">Store Id</th>
									<th width="10%">Terminal Id</th>
									<th width="10%">Terminal Manufacturer</th>							
									<th width="10%">Model Number</th>
									<th width="10%">Serial Number</th>
									<th width="10%">Created Date</th>
									<th width="10%">Status</th>
									
								</tr>
								
							</thead>
							
						

							<tbody  id="terminaldetails">
							</tbody>
							
						</table>
						</div>
						</div>
				</div>   
				<br></br> 
					<div class="row-fluid sortable">
						<div class="box span12">	
						<div class="box-header well" data-original-title>IMEI Details
						  <div class="box-icon"> 
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a> 
						  </div>
						</div>
						<div id="communicationDetails" class="box-content">
						<table width="100%" class="table table-striped table-bordered bootstrap-datatable dataTable" 
							id="DataTables_Table_2" >
							<thead  >
								<tr >
									<th width="10%">MAC Address</th>
									<th width="10%">Device IP</th>
									<th width="10%">IMEI NO</th>							
									<th width="10%">Serial No</th>
									<th width="10%">Version</th>
									<th width="10%">Device Type</th>									
									<th width="10%">Created Date</th>
									<th width="10%">Status</th>
									
								</tr>
								
							</thead>
							
						

							<tbody  id="imeidetails">
							</tbody>
							
						</table>
						</div>
						</div>
				</div>   
								<input type="hidden" name="langugae"  id="langugae"   value="English"  />
								
				</fieldset> 
				</div>
				</div> 
					
				 	
				
		</div> 	
</form>	

		
		
	</div> 

</body> 
</html>