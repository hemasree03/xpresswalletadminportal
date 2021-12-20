
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>NBK</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="">
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%String ctxstr = request.getContextPath(); %>
<% String appName= session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>
<%@taglib uri="/struts-tags" prefix="s"%>
<SCRIPT type="text/javascript">
var toDisp = '${type}';

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

	$('#btn-submit').live('click',function() {

		var searchIDs="";

 		$("div#merchant-auth-data input:radio:checked").each(function(index) {
 			searchIDs=$(this).val();
 			 $('#DECISION').val(searchIDs);
		});

		  if(searchIDs.length == 0) {
				$("#error_dlno").text("Please check atleast one record.");
			} else {
						$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/commonAuthRecordconfirm.action";
						$("#form1").submit();
			}
	});

});


$('#btn-back').live('click',function() {

	$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/commonAuthListAct.action";
	$("#form1").submit();


});
	//-->
</SCRIPT>

<s:set value="responseJSON" var="respData"/>

</head>

<body>
	<form name="form1" id="form1" method="post" action="">
			<div id="content" class="span10">
			    <div>
					<ul class="breadcrumb">
					  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
					  <li> <a href="#">All Authorization </a> <span class="divider"> &gt;&gt; </span></li>
					  <li><a href="#"> Super Agent Status Update Authorization Confirmation </a></li>
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

				<div class="row-fluid sortable"> 
	<div class="box span12"> 
			<div class="box-header well" data-original-title>
					<i class="icon-edit"></i>Agent Customer Details
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
							<td width="20%"><label for="From Date"><strong>Customer Id</strong></label></td>
							<td width="30%"><s:property value='#respData.custcode' /><input type="hidden" name="custcode"  id="custcode"   value="<s:property value='#respData.custcode' />"   />
							<td width="20%"><label for="To Date"><strong>Customer Name</strong></label><input type="hidden" name="institute"  id="institute"   value="<s:property value='#respData.institute' />"   /> </td>
							<td width="30%"><s:property value='#respData.fullname' /> <input type="hidden" name="fullname"  id="fullname"   value="<s:property value='#respData.fullname' />"   />  </td>
						</tr>  
						<tr class="even">
							<td ><label for="To Date"><strong>Mobile Number</strong></label> </td>
							<td ><s:property value='#respData.mobileno' />

								<input type="hidden" value="<s:property value='#respData.isocode' />" style="width:25px;" readonly name="isocode" id="isocode"/>&nbsp;
								<input type="hidden" value='<s:property value='#respData.mobileno' />' style="width: 80px;" maxlength="9" name="telephone" id="telephone" readonly style="width:130px;" />
 							</td>					
							
							<td><label for="To Date"><strong>Date Of Birth</strong></label> </td>
							<td><s:property value='#respData.nationalid' /><input type="hidden" name="idnumber"  id="idnumber"   value="<s:property value='#respData.nationalid' />"   />  </td>
						</tr>
						<tr class="even">
							<td><label for="From Date"><strong>Email ID</strong></label></td>
							<td><s:property value='#respData.email' /><input type="hidden" name="email"  id="email" readonly  value="<s:property value='#respData.email' />"   />  </td>
							<td><label for="To Date"><strong>Super Admin</strong></label> </td>
							<td><s:property value='#respData.language' /> <input type="hidden" name="langugae"  id="langugae"   value="<s:property value='#respData.language' />"   /> 
							
							 <input type="hidden" name="STATUS" id="STATUS" value="${STATUS}" />
  							 <input type="hidden" name="AUTH_CODE"  id="AUTH_CODE" value="${AUTH_CODE}"  />
							 <input type="hidden" name="REF_NO" id="REF_NO" value="${REF_NO}"/>
							 <input type="hidden" name="DECISION" id="DECISION" />
							 <input type="hidden" name="remark" id="remark" />
							 <input type="hidden" name="type" id="type" value="${type}"/>
							 <input type="hidden" name="multiData" id="multiData"/>
							  </td>
						</tr>
				
						<tr class="even">
							<td width="20%"><label for="Product"><strong>Product</strong></label></td>
							<td width="30%"><s:property value='#respData.product' /></td>
							<td width="20%"><label for="Description"><strong>Description</strong></label></td>
							<td width="30%"><s:property value='#respData.prodesc' /></td>
						</tr> 
						<tr class="even">
							<td width="20%"><label for="Product"><strong>Status</strong></label></td>
							<td width="30%"><s:property value='#respData.status' /></td>
						    <td  width="20%"><label for="Marital Status"><strong>Marital Status</strong></label></td>
							 <td  width="30%">${responseJSON.martStatus} <input type="hidden" name="martStatus"  id="martStatus"   value="${responseJSON.martStatus}"   />  </td>
						
						</tr> 
				 </table>
				</fieldset> 
				</div>  
				
	  </div>
	  </div> 
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

									 <tr class="odd">
						<td width="25%"><label for="Id Card Type"><strong>Id Card Type</strong></label></td>
						<td width="25%">${responseJSON.idtype}<input type="hidden" name="idtype"  id="idtype" value="${responseJSON.idtype}"   /></td>
						<td  width="25%"><label for="Id Card Number"><strong>Id Card Number</strong></label></td>
						<td  width="25%">${responseJSON.iddetails} <input type="hidden" name="iddetails"  id="iddetails"   value="${responseJSON.iddetails}"   />  </td>
					</tr> 
					   
                       <tr class="even">
								<td width="25%"><label for="Address"><strong>Address</strong></label></td>
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
											<td  width="25%" >${responseJSON.lga}<input type="hidden" value='${responseJSON.lga}' style="width:155px;" maxlength="13" name="lga" id="lga"  /> 
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
												class="table table-striped table-bordered bootstrap-datatable "
												id="Account-details">
                                    <tr class="even">
												<td><strong>Bank Name</strong></td>
												<td>${responseJSON.bankname} <input type="hidden"
													name="bankname" id="bankname"
													value="${responseJSON.bankname}" />

												</td>
												<td><strong>Bank Account Number</strong></td>
												<td>${responseJSON.accountno} <input type="hidden"
													name="bankaccountnumber" id="bankaccountnumber"
													value="${responseJSON.accountno}" />

												</td>
											</tr>

												<tr class="even">
													<td width="25%"><label for="Account Name"><strong>Account
																Name </strong></label></td>
													<td width="25%"> ${responseJSON.bankcustname}<input
														type=""hidden name="bankcustname" id="bankcustname"
														value="${responseJSON.bankcustname}" /></td>


													<td><label for="BVN"><strong>BVN </strong></label></td>
													<td>${responseJSON.bvn} <input type="hidden"
														name="bvn" id="bvn" value="${responseJSON.bvn}" />
													</td>
												</tr>
											</table>
										</fieldset>

								
							</div>


		        <div class="row-fluid sortable" id='remarks'><!--/span-->
					<div class="box span12">
							<div class="box-header well" data-original-title>
									<i class="icon-edit"></i>Remarks
								<div class="box-icon">
									<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
									<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>

								</div>
							</div>
							<div id="remarksInformation" class="box-content">

								<table width="100%" class="table table-striped table-bordered bootstrap-datatable "
										id="documentData" >
										<tr>
											<td><label for="Remarks"><strong>Remarks<font color="red">*</font></strong></label></td>
											<td><input type="text" name="remark"  id="remark"  value="${responseJSON.remarks}" /></td>
											<td></td>
											<td></td>
										</tr>
								</table>

						</div>
				</div>
		</div>
		<div id="merchant-auth-data">
				<ul class="breadcrumb">
				 <li> <strong>Authorize&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong><input  name="authradio" id="authradio"  class='center-chk' type='radio' value='A' />&nbsp;&nbsp; </li>
				 <li> <strong>Reject&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong><input  name="authradio" id="authradio"  class='center-chk' type='radio' value='R' /> &nbsp;&nbsp;&nbsp;</li>
				</ul>
		</div>

		<div class="form-actions">
				<input type="button" class="btn btn-danger" name="btn-back" id="btn-back" value="Back"  />
				<input type="button" class="btn btn-success" name="btn-submit" id="btn-submit" value="Confirm"  />
				
				<span id ="error_dlno" class="errors"></span>

  			   <input name="STATUS" type="hidden" id="STATUS" value="${STATUS}" />
  				<input name="AUTH_CODE" type="hidden" id="AUTH_CODE" value="${AUTH_CODE}"  />
				<input type="hidden" name="REF_NO" id="REF_NO" value="${REF_NO}"/>
				<input type="hidden" name="DECISION" id="DECISION" />

				  <input type="hidden" name="formName" id="formName" value="Super Agent Status Update"/>
			</div>
	</div>

</form>
</body>
</html>
