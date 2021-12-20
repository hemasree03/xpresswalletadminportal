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
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/cashoutaccountmodifyack.action'; 
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
					<li><a href="#">Cashout Account Modify  Confirmation</a></li>
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
						<i class="icon-edit"></i>Agent Details confirmation
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


									<td><label for="To Date"><strong>Date
												Of Birth 
										</strong></label></td>
									<td><s:property value='dateofbirth' /><input type="hidden"
										name="dateofbirth" id="dateofbirth"
										value="<s:property value='dateofbirth'/>" /></td>
								</tr>
								<tr class="even">
									<td><label for="From Date"><strong>Email
												ID</strong></label></td>
									<td><s:property value='email' /> <input type="hidden"
										name="email" id="email" value="<s:property value='email'/>" />
									</td>

                              <td><label for="Mobile Number"><strong>Mobile Number
												</strong></label></td>
									<td><s:property value='telephone' /> <input type="hidden"
										name="telephone" id="telephone" value="<s:property value='telephone'/>" />
									</td>



								</tr>
								<tr class="even">

									<td><strong>Gender</strong></td>
									<td><s:property value='gender' /> <input type="hidden"
										value="<s:property value='gender'/>" 
										name="gender" id="gender" /></td>
									<td><strong> Marital Status </strong></td>
									<td><s:property value='marital' /> <input type="hidden"
										value="<s:property value='marital'/> " 
										name="marital" id="marital" /></td>
								</tr>
								<tr class="even" id="superadmin" name="superadmin">
									<td><label for="merchanttype"><strong>Merchant Category </strong></label></td>
							<td><s:property value='mcc' />
						 <input	type="hidden" value="<s:property value='mcc'/>" name="mcc" id="mcc" /></td>
									<td><strong>Business Name </strong></td>
									<td><s:property value='business' /> 
									<input type="hidden" value="<s:property value='business'/>"  name="business" id="business" /></td>
								</tr>
								
								
								<tr class="odd"><td><strong>BVN</strong></td>
									<td><s:property value='bvn' /> 
									<input type="hidden" 	value="<s:property value='bvn'/>" name="bvn" id="bvn" /></td>
									
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

								<div class="box-content">
									<div class="box-content">
										<fieldset>
											<table width="950" border="0" cellpadding="5" cellspacing="1"
												class="table table-striped table-bordered bootstrap-datatable "
												id="Account-details">

												<tr class="even">
													<td width="25%"><label for="bankname"><strong>Bank Name </label></td>
													<td width="25%"><s:property value='bankname' /><input type="hidden" name="bankname" id="bankname" value="<s:property value='bankname'/>" /></td>
													<td width="25%"><label for="bankaccountnumber"><strong>Bank Account Number </strong></label></td>
													<td width="25%"><s:property value='bankaccnumber' /> 
													<input type="hidden" name="bankaccnumber"  id="bankaccnumber" value="	<s:property value='bankaccnumber'/>" /></td>
												</tr>
												<tr class="even">
													<td width="25%"><label for="Account Name"><strong>Account Name </strong></label></td>
													<td width="25%"><s:property value='accname' /> <input type="hidden" name="accname" id="accname" value="<s:property value='accname'/>" /></td>

													<td width="25%"><label for="Account Name"><strong>Account BVN </strong></label></td>
													<td width="25%"><s:property value='accbvn' /> <input type="hidden" name="accbvn" id="accbvn" value="<s:property value='accbvn'/>" /></td>

												</tr>

											</table>
										</fieldset>

									</div>
								</div>
							</div>
							<input type="hidden" name="langugae" id="langugae" value="English" />
							<input type="hidden" name="customercode" id="customercode" value="<s:property value='customercode'/>" />

							<div>
								<a href="#" id="btn-back" class="btn btn-danger ajax-link">Home
								</a>&nbsp; <a href="#" id="btn-submit"
									class="btn btn-success ajax-link">&nbsp;Confirm</a> <span
									id="error_dlno" class="errors"></span>
							</div>

						</div>

					</div>
				</div>







			</div>
	</form>

</body>
</html>