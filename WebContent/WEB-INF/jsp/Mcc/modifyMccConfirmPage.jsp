<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>NBK Salary Processing</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="">
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%String ctxstr = request.getContextPath(); %>
<%String appName = session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>
<%@taglib uri="/struts-tags" prefix="s"%> 
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

.messages {
	font-weight: bold;
	color: green;
	padding: 2px 8px;
	margin-top: 2px;
}

.errors {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}
</style>
<script type="text/javascript">
function redirectfun()
{
	
	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/mccManagement.action';
	$("#form1").submit();
	return true; 
	}
	

$(document).ready(function(){   
	
 	$('#btn-submit').live('click',function() {  
 		
 		var message = $("#categoryName").val();
 	//	alert(message);
			$('#categoryName').val(message);
 	    var finalData = "";
 			$("#error_dlno").text(''); 
			
			if ($("#form1").valid()==true)
				{
					 $("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/insertModifyMccDetailsAct.action";
					 $("#form1").submit();
				} 
	}); 
	
 	 $('#btn-Cancel').live('click',function() {  
 		$("#form1").validate().cancelSubmit = true; 
 		
 		
 		 	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/mccManagement.action';		
 	
 		$("#form1").submit();		
 	}); 
	
});
</script>


<s:set value="responseJSON" var="respData"/> 
<body class="fixed-top">
<form name="form1" id="form1" method="post" >
	
      <div id="content" class="span10"> 
	    <div> 
			<ul class="breadcrumb">
			  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
			  <li> <a href="#">Merchant Category Code Updation </a>  <span class="divider"> &gt;&gt; </span> </li>
			
 			</ul>
		</div>  
	 	<table height="3">
				 <tr>
					<td colspan="3">
						<div class="messages" id="messages"> <s:actionmessage /></div>
						<div class="errors" id="errors"> <s:actionerror /></div>
					</td>
				</tr>
			 </table> 
				
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" >
					<i class="icon-edit"></i>Merchant Category Code Management Details 
					
					
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
				</div>
			</div> 
		
      				 
		<div class="box-content">
		
								
								<table width="950" border="0" cellpadding="5" cellspacing="1"  class="table table-striped table-bordered bootstrap-datatable ">
								<tr class="even">
								<td width="20%"><label for="Category Code"><strong>Category Code</strong></label></td>
								<td width="30%"><s:property value='categoryCode'/>
								
								<input name="categoryCode" id="categoryCode"  type="hidden" class="field" required=true maxlength="150" value="<s:property value='categoryCode'/>" /></td>
								 <td width="20%"><label for="Category Name"><strong>Category Name</strong></label></td>
								 <td width="30%"><s:property value='categoryName'/></td>
								 <td width="30%"><input name="categoryName" id="categoryName"  type="hidden" class="field" required=true maxlength="150" value="<s:property value='categoryName'/>" /> </td>
							</tr>
							
							</table>
								
							
                
                        </div>
                   

						   </div>	
						   
						   
			  </div>
			  
			  	<div class="row-fluid sortable">
		
						   
			  </div>
			  
			  <div  >
			   <input type="button" class="btn btn-success"  name="btn-submit" id="btn-submit" value="Confirm" width="100" ></input>
				 <input type="button" class="btn btn-info" name="btn-Cancel" id="btn-Cancel" value="Back" width="100" ></input>
				
			</div>
			</div>
		  
		  </form>	
</body>
</html>
<!-- END PAGE -->