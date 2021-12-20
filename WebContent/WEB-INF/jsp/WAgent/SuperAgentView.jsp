
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

	

});


$('#btn-back').live('click',function() {

	$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/superagent.action";
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
							<i class="icon-edit"></i>Super Agent View
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
												<td><s:property value="#respData.SUPERAGENT_NAME"/>
												</td>
												<td width="25%"><label for="Account Currency Code"><strong>Super Agent Id</label></td>
										<td width="25%"><s:property value="#respData.AGENTID"/>
											 	
										   </tr>
									<tr class="even">
									<td width="25%"><strong><label for="Super Agent Name"><strong>Business Owner Name</strong></label></strong></td>
									<td width="25%"><s:property value="#respData.B_O_NAME"/>
								<td ><label for="Date Of Birth"><strong>Date Of Birth</label></td>
					                        <td ><s:property value="#respData.DOB"/>
								</tr>
									<tr class="even">
											
										  	    <td ><label for="Gender"><strong>Gender</label>	</td>
												<td ><s:property value="#respData.GENDER"/>
												</td>
												<td><strong><label for="Email"><strong>Email</strong></label></strong></td>
										<td><s:property value="#respData.EMAIL"/>
										   </tr>
									<tr class="odd">
										
										<td ><strong><label for="Mobile"><strong>Mobile</strong></label></strong></td>
										<td><s:property value="#respData.MOBILE"/>
										<td><strong><label for="CBN Agent Id"><strong>Status</strong></label></strong></td>
									<td><div id="spn-user-status"></div></td>
									</tr>
										<tr class="even">
									
															 <td width="25%"><label for="Nationality"><strong>BVN</strong></label></td>
												<td width="25%"><s:property value="#respData.BVN"/>
	
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
					 						 <td><strong>ID Card Type</strong></td>
											      <td>
											      <s:property value="#respData.ID_TYPE"/>
										    
											    
											      </td>
												<td><strong>ID Card Identity Number</strong></td>
											      <td>
											     <s:property value="#respData.ID_NUMBER"/>
										    
											    
											      </td>
											</tr>
											
										   <tr class="even">
										   		
												
											   
												 <td width="25%"><label for="Address Line 1"><strong>House Number</strong></label></td>
												<td width="25%"><s:property value="#respData.ADDRESSLINE"/>
												</td>
												 <td width="25%"><label for="Nationality"><strong>Nationality</strong></label></td>
												<td width="25%"><s:property value="#respData.NATIONALITY"/>
	
												</td>
										   </tr>
										    
										    <tr class="odd">
										        <td><label for="Local Government"><strong>Street Name</strong></label></td>
												<td><s:property value="#respData.STREET_NAME"/>
												<td><label for="Local Government"><strong>City</strong></label></td>
												<td><s:property value="#respData.CITY"/>
												
											</tr>

										   <tr class="odd">
										   
										  <td><label for="State"><strong>State</strong></label></td>
												<td>
												<s:property value="#respData.STATE"/>
												
														 </td>
												
										    
											<td><label for="Local Government"><strong>Local Government</strong></label></td>
												<td> 
												<s:property value="#respData.LOCALGOVERNMENT"/>
												 
												 
													 </td>
												</tr>

										   <tr class="odd">	
										   <td><label for="Local Government"><strong>Ward Name</strong></label></td>
												<td> 
												<s:property value="#respData.WARD_NAME"/>
												 
												 
													 </td> 
													 <td><label for="Nationality"><strong>Country</strong></label></td>
												<td><s:property value="#respData.COUNTRY"/>
											
												
												</td>
											</tr>
										  
										   
										 
										  

									</table>
				</fieldset>
			</div>

		</div>
		</div>

<div class="row-fluid sortable">
						<div class="box span12">
								<div class="box-header well" data-original-title>
										<i class="icon-edit"></i>Wallet Account Details
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
						     </td> 
						     
						     
						       <td  width="25%"><label for="Local Government"><strong>Wallet Account Number</strong></label></td>
												<td width="25%"> <s:property value="#respData.ACCT_NO"/></td> 
							     
						   <td  width="25%"><strong>Wallet Account Balance</strong></td>
							<td width="25%"> <s:property value="#respData.BALANCE"/></td> 
						</tr>
						
				
							
							<tr class="even">
							
							 <td  width="25%"><label for="Product"><strong>Onboard Date</strong></label></td>
								<td width="25%"> <s:property value="#respData.DATE_CREATED"/></td> 
							<td width="25%"></td>
							<td width="25%"></td>
						
							</tr>	  
				          			   
										   
					 
										    
								</table>
								</fieldset>
							</div>
						</div>
				</div>
		

		<div class="form-actions">

				<input type="button" class="btn btn-danger" name="btn-back" id="btn-back" value="Back"  />
				<span id ="error_dlno" class="errors"></span>

  			   <input name="STATUS" type="hidden" id="STATUS" value="${STATUS}" />
  				<input name="AUTH_CODE" type="hidden" id="AUTH_CODE" value="${AUTH_CODE}"  />
				<input type="hidden" name="REF_NO" id="REF_NO" value="${REF_NO}"/>
				<input type="hidden" name="DECISION" id="DECISION" />

				  <input type="hidden" name="formName" id="formName" value="Merchant"/>
			</div>
	</div>

</form>
</body>
</html>
