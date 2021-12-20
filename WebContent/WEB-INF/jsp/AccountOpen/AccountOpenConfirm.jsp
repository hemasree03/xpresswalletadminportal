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
	
	 
    
	
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/accountopeningack.action'; 
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
					  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
					   <li> <a href="#">Account Opening Details</a>  </li> 
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
					<i class="icon-edit"></i>Account Opening Confirmation
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
				</div>
			</div>  
				<div class="box-content">
					<table width="950" border="0" cellpadding="5" cellspacing="1" 
									class="table table-striped table-bordered bootstrap-datatable " id="user-details"> 
						   
						<tr class="even">
							<td width="25%"><label for="From Date"><strong>First Name</strong></label></td>
							<td width="25%">${responseJSON.fullname}
							<input type="hidden" name="fullname"  id="fullname"   value="${responseJSON.fullname}"  /></td>
							<td width="25%"><label for="From Date"><strong>Middle Name</strong></label></td>
						    <td width="25%">${responseJSON.middlename}
						    <input type="hidden" name="middlename"  id="middlename"  value="${responseJSON.middlename}"    /></td>
							
							</tr>
						<tr class="even">
						<td width="25%"><label for="From Date"><strong>Last Name</strong></label></td>
						    <td width="25%">${responseJSON.lastname}
						    <input type="hidden" name="lastname"  id="lastname"   value="${responseJSON.lastname}"  /></td>
						<td  ><label for="To Date"><strong>Date Of Birth</strong></label> </td>
							<td>${responseJSON.dob}
							<input type="hidden"  id="dob" name="dob"  value="${responseJSON.dob}" />
							</td>
						
							
							  
						</tr>
						<tr class="even">
						
							<td ><label for="From Date"><strong>Email ID</strong></label></td>
							<td >${responseJSON.email}
							<input type="hidden" name="email"  id="email"    value="${responseJSON.email}"  />  </td>
						   <td><label for="To Date"><strong>Mobile Number</strong></label> </td>
					       <td >${responseJSON.telephone}
					    
								<input type="hidden"  name="telephone" id="telephone" value="${responseJSON.telephone}" />
 							
						     
						   </td> 
						    
 						</tr>
 						<tr class="even" >
 						 <td><strong>Gender</strong></td>
						      <td>
						      ${responseJSON.gender}
					    
								<input type="hidden"  name="gender" id="gender" value="${responseJSON.gender}" />
						    
						      </td>
							<td><strong>Marital Status</strong></td>
						      <td>
						      ${responseJSON.marital}
					    
								<input type="hidden"  name="marital" id="marital" value="${responseJSON.marital}" />
						    
						      </td>
						</tr>
						<tr>
						
						<td><label for="Local Government"><strong>BVN</strong></label></td>
												<td>${responseJSON.bvn}
												<input  type="hidden" class="field" id="bvn" name="bvn" value="${responseJSON.bvn}"/></td>
												<td></td>
												<td></td>
						</tr>
											
				 </table>
				 
				 
				
				
				
				</div>  
				
	  </div>
	  </div> 
	  
	
									
			
	
		<div >
			<a href="#" id="btn-back" class="btn btn-danger ajax-link">&nbsp;Home </a>&nbsp;
			<a href="#" id="btn-submit" class="btn btn-success ajax-link">&nbsp;Confirm</a>					 
		</div> 
	</div> 	 
 </form>
 <script type="text/javascript">
$(function(){
	
	var actlen = $('#tbody_data > tr').length;
	
	if(actlen == 0){
		$('#add-new-act').hide();
	}else {
		$('#add-new-act').show();
	}
	 
});  
</script>
</body> 
</html>