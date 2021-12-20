
<html lang="en">
<head>
<meta charset="utf-8">
<title>NBK</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="Team">
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%String ctxstr = request.getContextPath(); %>
<% String appName= session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>



 <style type="text/css">
.messages {
  font-weight: bold;
  color: green;
  padding: 2px 8px;
  margin-top: 2px;
}

.errors{
  font-weight: bold;
  color: red;
  padding: 2px 8px;
  margin-top: 2px;
}
</style>
<script type="text/javascript">

var toDisp = '${type}';

$(document).ready(function(){

	var actionLink = "";
	if('${responseJSON.CV0014}' == 'MERTADM' || '${responseJSON.CV0014}' == 'MERCHTUSR' || '${responseJSON.CV0014}' == 'MERCNTSUPE')
	{
		$('#idtype').empty();
		$('#idtype').text('ID');
	}
	var userStatus = '${responseJSON.CV0013}';
	var text = "";

	if( userStatus == 'Active')
		text = "<a href='#' class='label label-success'  >"+userStatus+"</a>";
	else if( userStatus == 'De-Active')
		text = "<a href='#'  class='label label-warning'  >"+userStatus+"</a>";
	else if( userStatus == 'InActive')
		text = "<a href='#'  class='label label-info' >"+userStatus+"</a>";
	else if( userStatus == 'Un-Authorize')
		text = "<a href='#'  class='label label-primary'   >"+userStatus+"</a>";

	$('#spn-user-status').append(text);

	$('#btn-submit').on('click',function() {
		$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/resetPassword.action";
		$("#form1").submit();
	});
	$('#btn-cancel').on('click',function() {
		  $("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/userGrpCreation.action";
		  $("#form1").submit();
		 });

});
</script>
</head>

<body>
	<form name="form1" id="form1" method="post">
	<!-- topbar ends -->

	 <div id="content" class="span10">

		    <div>
				<ul class="breadcrumb">
				  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
				  <li> <a href="userGrpCreation.action">User Management</a> <span class="divider"> &gt;&gt; </span></li>
					  <li><a href="#">User ${type} Confirmation</a></li>
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
						 <i class="icon-edit"></i>User Password Reset Information
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>

						</div>
					</div>

					<div class="box-content">
						<fieldset>
							<table width="950"  border="0" cellpadding="5" cellspacing="1"
								class="table table-striped table-bordered bootstrap-datatable">
								  <tr >
										<td width="25%" ><strong><label for="User Id"><strong>User Id</label></strong></td>
										<td width="25%" >${responseJSON.CV0001}<input type="hidden" name="userId"  id="userId" value="${responseJSON.CV0001}" /> </td>
										<td width="25%" ><strong><label for="ID/Driving License"><strong><span id="">Employee No</span></label></strong></td>
										<td width="25%" >${responseJSON.CV0002}<input type="hidden" name="CV0002"  id="empNo" value="${responseJSON.CV0002}" /></td>
									</tr>
									<tr >
										<td><strong><label for="First Name"><strong>First Name</label></strong></td>
										<td>${responseJSON.CV0003} <input type="hidden" name="CV0003"  id="firstName" value="${responseJSON.CV0003}" /></td>
										<td><strong><label for="Last Name"><strong>Last Name</label></strong></td>
										<td> ${responseJSON.CV0004}<input type="hidden" name="CV0004"  id="lastName" value="${responseJSON.CV0004}" /></td>
									</tr>
									<tr >
										<td><strong><label for="Telephone Res"><strong>Telephone(Res)</label></strong></td>
										<td>${responseJSON.CV0005}<input type="hidden" name="CV0005"  id="telephoneRes" value="${responseJSON.CV0005}" /></td>
										<td><strong><label for="Telephone Off"><strong>Telephone(Off)</label></strong></td>
										<td>${responseJSON.CV0006}<input type="hidden" name="CV0006"  id="telephoneOff" value="${responseJSON.CV0006}" /></td>
									</tr>

									 <tr >
											<td><strong><label for="Mobile"><strong>Mobile</label></strong></td>
											<td>234-${responseJSON.CV0007}<input type="hidden" name="CV0007"  id="mobile" value="${responseJSON.CV0007}" /></td>
											<td><strong><label for="E-Mail"><strong>E-Mail</label></strong></td>
										<td>
											${responseJSON.CV0012} <input type="hidden" name="CV0012"  id="email" value="${responseJSON.CV0012}" />
										</td>
									 </tr>

									<tr >
									  <td><strong><label for="User Level"><strong>User Level</label></strong></td>
									  <td>
										${responseJSON.CV0009} <input type="hidden" name="CV0009"  id="adminType" value="${responseJSON.CV0009}" />
									  </td>
									  <td><strong><label for="Office Location"><strong>Office Location</label></strong></td>
						              <td>
							            ${responseJSON.CV0018} <input type="hidden" name="CV0018"  id="officeLocation" value="${responseJSON.CV0018}" />
						              </td>
									</tr>
									<tr >
										<td><strong><label for="User Status"><strong>User Status</label></strong></td>
										<td>
											<span id="spn-user-status"></span> <input type="hidden" name="CV0013"  id="user_status" value="${responseJSON.CV0013}" />
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<%-- <tr id="MerchantInfo">
							            <td><strong><label for="Merchant Id"><strong>Merchant Id</label></strong></td>
							            <td>
								           ${responseJSON.CV0015}
							            </td>
							            <td><strong><label for="Store Id"><strong>Store Id</label></strong></td>
							            <td>
								           ${responseJSON.CV0016}
								           <input type="hidden" id="acttype" name="acttype" value="${responseJSON.acttype}" />
							            </td>
						           </tr> --%>
							</table>

							<table width="950"  border="0" cellpadding="5" cellspacing="1"
							class="table table-striped table-bordered bootstrap-datatable">
								<tr >
									<td colspan="2" width="25%"><strong><label for="Reason"><strong>Reason</label></strong></td>
									<td colspan="2" width="75%">
										 <input name="reason" id="reason" value="${responseJSON.reason}"  type="text" class="field" required=true  maxlength="50" >
									</td>

								</tr>
								<tr class="odd">
									<td colspan="4">
										<span > <strong> Please click on confirm to reset the user password.</strong></span>
									</td>
								</tr>
						</table>

						</fieldset>
						</div>

				</div>
<input type="hidden" name="CV0015"  id="user_status" value="${responseJSON.CV0015}" />
<input type="hidden" name="CV0016"  id="user_status" value="${responseJSON.CV0016}" />
				</div>
			<div class="form-actions">
		         <input type="button" class="btn btn-success"  name="btn-submit" id="btn-submit" value="Confirm" width="100" ></input>
				 &nbsp;<input type="button" class="btn"  name="btn-cancel" id="btn-cancel" value="Cancel" width="100" ></input>
 		    </div>
	</div><!--/#content.span10-->

 </form>
</body>
</html>

