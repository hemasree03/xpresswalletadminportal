<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="">
 <%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%String ctxstr = request.getContextPath(); %>
<%String appName = session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>
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
<script type="text/javascript" >
$(document).ready(function() {

	
	
	

	
	$('#btn-back').live('click', function () {
		var url="${pageContext.request.contextPath}/<%=appName %>/superagent.action";
		$("#form1")[0].action=url;
		$("#form1").submit();

	});

	

	$('#btn-submit').live('click', function () {
		

		
			var url="${pageContext.request.contextPath}/<%=appName %>/superagentmodifyack.action";
			$("#form1")[0].action=url;
			$("#form1").submit();
	

	});


});

</script>
<s:set name="command" value="cmd"/>
</head>
<body>
	<form name="form1" id="form1" method="post" action="" >
			<div class="span10" id="create">
			<div>
				<ul class="breadcrumb">
				  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
				  <li> <a href="superagent.action">Super Agent Management</a> <span class="divider"> &gt;&gt; </span></li>
				</ul>
			</div>
			<s:actionerror cssClass="errors"/>
			<div class="row-fluid sortable"><!--/span-->
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<i class="icon-edit"></i>Super Agent Creation
							<div class="box-icon">
								<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
								<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							</div>
						</div>
						
						<input type="hidden" name="srchcriteria" id="srchcriteria" value='${agent.srchcriteria}'>
						<div id="primaryDetails" class="box-content">
							<fieldset>
								<table width="950" border="0" cellpadding="5" cellspacing="1"
									class="table table-striped table-bordered bootstrap-datatable " id="bank-details">
									
									 <tr class="odd">
										   		<td><label for="Telco Type"><strong>Super Agent Name</label></td>
												<td>${superagentname}
												<input type="hidden" name="agent.superagentname"  id="superagentname"   value="${superagentname}" >
												</td>
												<td width="25%"><label for="Account Currency Code"><strong>Super Agent Id</label></td>
										<td width="25%">${CBNAgentId}
										<input type="hidden" name="agent.CBNAgentId" id="CBNAgentId" value='${CBNAgentId}' readonly></td> 
											 	
										   </tr>
									<tr class="even">
									<td width="25%"><strong><label for="Super Agent Name"><strong>Business Owner Name</strong></label></strong></td>
									<td width="25%">${accountName}
									<input type="hidden" name="agent.accountName" value='${accountName}'></td>
								<td ><label for="Date Of Birth"><strong>Date Of Birth</label></td>
					                        <td >${dob}
					                        <input type="hidden"   id="dob" name="agent.dob"  value="${dob}"/></td>
								
										
										
									</tr>
									<tr class="even">
											
										  	    <td ><label for="Gender"><strong>Gender</label>	</td>
												<td >${gender}
												<input type="hidden"   id="gender" name="agent.gender" required=true   value="${gender}"/>
												</td>
												<td><strong><label for="Email"><strong>Email</strong></label></strong></td>
										<td>${email}
										<input type="hidden" name="agent.email" id="email" value='${email}'></td>
										   </tr>
									<tr class="odd">
										
										<td ><strong><label for="Mobile"><strong>Mobile</strong></label></strong></td>
										<td>234${mobile}
										<input type="hidden" name="agent.mobile"  id="mobile"  value='234${mobile}'  ></td>
									 <td width="25%"><label for="Nationality"><strong>BVN</strong></label></td>
												<td width="25%">${bvn}
												<input type="hidden" name="agent.bvn" id="bvn" value="${bvn}"  />
	
												</td>
									</tr>
									
									
										   
										  
							
									

								</table>
							 </fieldset>
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
											<tr class="even" >
					 						 <td><strong>ID Card Type</strong></td>
											      <td>
											      ${idcardtype}
										    
													<input type="hidden"  name="agent.idcardtype" id="idcardtype" value="${idcardtype}" />
											    
											      </td>
												<td><strong>ID Card Identity Number</strong></td>
											      <td>
											      ${idcardnumber}
										    
													<input type="hidden"  name="agent.idcardnumber" id="idcardnumber" value="${idcardnumber}" />
											    
											      </td>
											</tr>
											
										   <tr class="even">
										   		
												
											   
												 <td width="25%"><label for="Address Line 1"><strong>Address</strong></label></td>
												<td width="25%">${addressLine}
												<input type="hidden" name="agent.addressLine" id="addressLine" value="${addressLine}"  />
												</td>
												
												 <td width="25%"><label for="Nationality"><strong>Nationality</strong></label></td>
												<td width="25%">${nationality}
												<input type="hidden" name="agent.nationality" id="nationality" value="${nationality}"  />
	
												</td>
										   </tr>
										    
										    <tr class="odd">
										        <td><label for="Local Government"><strong>Street Name</strong></label></td>
												<td>${streetname}
												<input  type="hidden" class="field" id="streetname" name="agent.streetname" value="${streetname}" /></td>
												
												<td><label for="Local Government"><strong>City</strong></label></td>
												<td>${city}
												<input  type="hidden" class="field" id="city" name="agent.city" value="${city}" /></td>
												
											</tr>

										   <tr class="odd">
										   
										  <td><label for="State"><strong>State</strong></label></td>
												<td>
												${state}
												<input  type="hidden" class="field" id="state" name="agent.state" value="${state}" />
												
														 </td>
												
										    
											<td><label for="Local Government"><strong>Local Government</strong></label></td>
												<td> 
												${localGovernment}
												<input  type="hidden" class="field" id="localGovernment" name="agent.localGovernment" value="${localGovernment}" />
												 
												 
													 </td>
												</tr>

										   <tr class="odd">	
										   <td><label for="Local Government"><strong>Ward Name</strong></label></td>
												<td> 
												${wardname}
												<input  type="hidden" class="field" id="wardname" name="agent.wardname" value="${wardname}" />
												 
												 
													 </td> 
													 <td><label for="Nationality"><strong>Country</strong></label></td>
												<td>${country}
												<input  type="hidden" class="field" id="country" name="agent.country" value="${country}" />
											
												
												</td>
											</tr>
										  
										   
										 
										  

									</table>
								</fieldset>
							</div>
						</div>
				</div>
			
			</div>
			
		
			
			
			<s:if test="%{#command == 'VIEW'}">
				<div class="form-actions">
					<input type="button" class="btn btn-danger" name="btn-back" id="btn-back" value="Back" />
				</div>
			</s:if>
			<s:elseif test="%{#command == 'ACTIVEDEACTIVE'}">
				<div class="form-actions">
					<s:if test="#status == A">
						<input type="button" class="btn btn-danger" name="btn-deactive" id="btn-deactive" value="Deactive" />
						<input type="button" class="btn btn-success" name="btn-deactive-cnf" id="btn-deactive-cnf" value="Confirm" style="display:none;"/>
					</s:if>
					<s:elseif test="#status == D">
						<input type="button" class="btn btn-success" name="btn-active" id="btn-active" value="Active" />
						<input type="button" class="btn btn-success" name="btn-active-cnf" id="btn-active-cnf" value="Confirm"  />
					</s:elseif>
				</div>
			</s:elseif>
			<s:else>
			    <div class="form-actions">
				<input type="button" class="btn btn-success" name="btn-submit" id="btn-submit" value="Confirm" />&nbsp;
				<input type="button" class="btn btn-danger" name="btn-back" id="btn-back" value="Back" />
				<input name="merchant.password" type="hidden" id="password" />
			 	<input name="merchant.encryptPassword" type="hidden" id="encryptPassword"  />
			 	<input name="merchant.otp" type="hidden" id="otp"  />
			 	 <input name="STATUS" type="hidden" id="STATUS" value="${STATUS}" />
				&nbsp;<span id ="error_dlno" class="errors"></span>
			</div>
			</s:else>
	</div>
<input type="hidden" name="status" id="status"/>
<input type="hidden" name="accNumbers" id="accNumbers" value="${CBNAgentId}"/>
</form>
</body>
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
	  	 
		 
		  
		/* $('#plasticCode').val(ses); 
		$('#plasticCode').trigger("liszt:updated");  */
	});
 </script>
<script language="Javascript" src="${pageContext.request.contextPath}/js/manual-validation.js"></script>
<script language="Javascript" src="${pageContext.request.contextPath}/js/authenticate.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/sha256.js" ></script>

</html>

