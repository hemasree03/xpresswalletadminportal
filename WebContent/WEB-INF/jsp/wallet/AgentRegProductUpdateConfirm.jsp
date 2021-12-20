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
	
	
	
	var config = {
		      '.chosen-select'           : {},
		      '.chosen-select-deselect'  : {allow_single_deselect:true},
		      '.chosen-select-no-single' : {disable_search_threshold:10},
		      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
		      '.chosen-select-width'     : {width:"95%"}
		    };
			
		    for (var selector in config) {
		      $(selector).chosen(config[selector]);
		    }  
  
	$('#btn-back').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
		$("#form1").submit();
		return true;
	});
	
	$('#btn-submit').on('click', function(){
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/agentregproductmodifyack.action'; 
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
					   <li> <a href="#">Wallet Agent Product Update</a>  </li> 
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
						 <tr class="even" id="superadmin" name="superadmin" >
							<td><label for="Product"><strong>Product</strong></label></td>
							<td>${responseJSON.product} <input type="hidden" name="product"  id="product"   value="${responseJSON.product}"   />  </td>
							<td><strong>Product Description </strong></td>
							<td>${responseJSON.prodesc} <input type="hidden" name="prodesc"  id="prodesc"   value="${responseJSON.prodesc}"   /></td>
						</tr>  	
									
				 </table>
				</fieldset> 
				
				
				 
				
				
				
				</div>  
				
	  </div>
	  </div> 
	  <input type="hidden" name="refno"  id="refno"   value="${responseJSON.refno}"  />
	
										<input type="hidden" name="langugae"  id="langugae"   value="English"  />
			
	
		<div >
			<a href="#" id="btn-back" class="btn btn-danger ajax-link">&nbsp;Home </a>&nbsp;
			<a href="#" id="btn-submit" class="btn btn-success ajax-link">&nbsp;Confirm</a>					 
		</div> 
	</div> 	 
 </form>

</body> 
</html>