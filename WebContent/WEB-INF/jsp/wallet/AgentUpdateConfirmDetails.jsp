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
	
	
			
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/agentmodifyack.action'; 
		$("#form1").submit();
		
		 $(this).prop('disabled', true);
			$("#btn-submit").hide();
		return true;
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
					<li><a href="#"> Agent Modify Confirmation</a></li>
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
						<i class="icon-edit"></i>Agent Modify Details Confirmation
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
									<td width="25%"><label for="fname"><strong>First
												Name 
										</strong></label></td>
									<td width="25%"><s:property value='fname' /><input
										type="hidden" name="fname" id="fname"
										value="<s:property value='fname'/>" /></td>
									<td width="25%"><label for="middlename"><strong>Middle
												Name</strong></label></td>
									<td width="25%"><s:property value='middlename' /> <input
										type="hidden" name="middlename" id="middlename"
										value="<s:property value='middlename'/>" /></td>
								</tr>
								<tr class="even">
									<td width="25%"><label for="lastname"><strong>Last
												Name 
										</strong></label></td>
									<td width="25%"><s:property value='lastname' /> <input
										type="hidden" name="lastname" id="lastname"
										value="<s:property value='lastname'/>" /></td>


									<td width="25%"><label for="To Date"><strong>Date
												Of Birth 
										</strong></label></td>
									<td width="25%"><s:property value='dateofbirth' /><input type="hidden"
										name="dateofbirth" id="dateofbirth"
										value="<s:property value='dateofbirth'/>" /></td>
								</tr>
								<tr class="even">
									<td width="25%"><label for="From Date"><strong>Email
												ID</strong></label></td>
									<td width="25%"><s:property value='email' /> <input type="hidden"
										name="email" id="email" value="<s:property value='email'/>" />
									</td>


									<td width="25%"><label for="To Date"><strong>Mobile
												Number 
										</strong></label></td>
									<td width="25%"><s:property value='telephone' /> <input type="hidden"
										value="<s:property value='telephone'/>" 
										name="telephone" id="telephone" /></td>
								</tr>
								<tr class="even">

									<td width="25%"><strong>Gender</strong></td>
									<td width="25%"><s:property value='gender' /> <input type="hidden"
										value="<s:property value='gender'/>" 
										name="gender" id="gender" /></td>
									<td width="25%"><strong> Marital Status </strong></td>
									<td width="25%"><s:property value='marital' /> <input type="hidden"
										value="<s:property value='marital'/>"
										name="marital" id="marital" /></td>
								</tr>
								
							
							<tr class="even" >
								
							
								<td  width="25%"><strong>Merchant Category</strong></td>
								<td  width="25%"><s:property value='merchanttype' /> 
								<input type="hidden"<s:property value='merchanttype' /> style="width:155px;"  name="merchanttype" id="merchanttype"  /></td>
						 	<td width="25%"><label for="Business Name"><strong>Business Name</strong></label></td>
								<td width="25%"><s:property value='business' /> 
								<input type="hidden" value="<s:property value='business'/>" name="business" id="business" /></td>		
									
								</tr>
								<tr class="odd">
								
								
									<td width="25%"><label for="BVN"><strong>BVN</strong></label></td>
								<td width="25%"><s:property value='bvn' /> 
								<input type="hidden" <s:property value='bvn' /> name="bvn" id="bvn" /></td>		
						
								<td ><label for="From Date"><strong>Staff Code</strong></label></td>
							<td ><s:property value='staffcode' />
								<input type="hidden"  name="staffcode" id="staffcode" value="<s:property value='staffcode' />" /> 
							</td>
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
						<div class="box-content">
							<fieldset>
								<table width="950" border="0" cellpadding="5" cellspacing="1"
									class="table table-striped table-bordered bootstrap-datatable "
									id="user-details">

									<tr class="even">
										<td width="25%"><label for="idtype"><strong>Id
													Type 
											</strong></label></td>
										<td width="25%"><s:property value='idtype' /> <input
											type="hidden" value="<s:property value='idtype'/>"
											maxlength="13" name="idtype" id="idtype" /></td>
										<td width="25%"><label for="From Date"><strong>Id
													Card Identity Number 
											</strong></label></td>
										<td width="25%"><s:property value='iddetails' /> <input
											type="hidden" name="iddetails" id="iddetails"
											value="<s:property value='iddetails'/>" /></td>
									</tr>
									<tr class="even">
										<td width="25%"><label for="address"><strong>House Number
													
											</strong></label></td>
										<td width="25%"><s:property value='address' />
										<input type="hidden" name="address" id="address" value="<s:property value='address' />" />
										</td>


										<td width="25%"><label for="nationality"><strong>Nationality
													
											</strong></label></td>
										<td width="25%"><s:property value='nationality' /><input
											type="hidden" name="nationality" id="nationality"
											value="<s:property value='nationality'/>" /></td>
									</tr>
									<tr class="even">
										<td width="25%"><label for="Street"><strong>Street
													Name 
											</strong></label></td>
										<td width="25%"><s:property value='area' /> <input type="hidden"
											name="area" id="area" value="<s:property value='area'/>" />
										</td>


										<td width="25%"><label for="City"><strong>City
													
											</strong></label></td>
										<td width="25%"><s:property value='city' /> <input
											type="hidden" value="<s:property value='city'/>"
											maxlength="13" name="city" id="city" /></td>
									</tr>
									<tr class="even">


									<%-- 	<td width="25%"><label for="State"><strong>State</strong></label></td>
										<td width="25%"><s:property value='state' /> <input name="state"
											type="hidden" class="field" id="state"
											value="<s:property value='stated'/>" /></td> --%>
									
							      <td width="25%"><label for="State"><strong>State</strong></label></td>
										<td width="25%"><s:property value='state' /> <input type="hidden" value="<s:property value='state'/>" maxlength="13" name="state" id="state" /></td>									
										
										<td width="25%"><label for="Local Government"><strong>Local 	Government </strong></label></td>
										<td width="25%"><s:property value='lga' /> <input
											name="lga" type="hidden" class="field" id="lga"
											value="<s:property value='lga'/>" /></td>
									</tr>
									<tr class="even" id="superadmin" name="superadmin">

										<td width="25%"><label for="wardname"><strong>Ward
													Name
											</strong></label></td>

										<td width="25%"><s:property value='wardname' /> <input type="hidden"
											name="wardname" id="wardname" class="field"
											value="<s:property value='wardname'/>" required='true' /></td>
										<td width="25%"><strong>Country </strong></td>
										<td width="25%"><s:property value='country' /> <input
											type="hidden" name="country" id="country" class="field"
											value='<s:property value='country'/>' required='true' /></td>
									</tr>

								</table>
							</fieldset>
						</div>

					<%-- 	<div class="row-fluid sortable">
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

								<div class="box-content">
									<div class="box-content">
										<fieldset>
											<table width="950" border="0" cellpadding="5" cellspacing="1"
												class="table table-striped table-bordered bootstrap-datatable "
												id="Account-details">

												<tr class="even">
													<td width="25%"><label for="bankname"><strong>Bank
																Name </label></td>
													<td width="25%"><s:property value='bankname' /><input
														type="hidden" name="bankname" id="bankname"
														value="<s:property value='bankname'/>" /></td>
													<td width="25%"><label for="bankaccountnumber"><strong>Bank
																Account Number </strong></label></td>
													<td width="25%"><s:property value='bankaccountnumber' /> <input
														type="hidden" name="bankaccountnumber"
														id="bankaccountnumber"
														value="	<s:property value='bankaccountnumber'/>" /></td>
												</tr>
												<tr class="even">
													<td width="25%"><label for="Account Name"><strong>Account
																Name </strong></label></td>
													<td width="25%"><s:property value='bankcustname' /> <input
														type="hidden" name="bankcustname" id="bankcustname"
														value="<s:property value='bankcustname'/>" /></td>


													<td width="25%"><label for="BVN"><strong>BVN </strong></label></td>
													<td width="25%"><s:property value='bvn' /><input type="hidden"
														name="bvn" id="bvn" value="<s:property value='bvn'/>" />
													</td>
												</tr>

											</table>
										</fieldset>

									</div>
								</div>
							</div> --%>
							<input type="hidden" name="langugae" id="langugae" value="English" />
							<input type="hidden" name="customercode" id="customercode" value="<s:property value='customercode'/>" />

							

						</div>
						
					</div>
					<div>
								<a href="#" id="btn-back" class="btn btn-danger ajax-link">Home
								</a>&nbsp; <a href="#" id="btn-submit"
									class="btn btn-success ajax-link">&nbsp;Confirm</a> <span
									id="error_dlno" class="errors"></span>
							</div>
				</div>





			</div>
	</form>

</body>
</html>