
<!DOCTYPE html>
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
 
    
<SCRIPT type="text/javascript">  

$(document).ready(function(){
	 
	$('#btn-submit').live('click',function() {  
		$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/userGrpCreation.action";
		$("#form1").submit();					
	});  
	 
});
//--> 
</SCRIPT> 
		
</head>

<body>
	<form name="form1" id="form1" method="post">
	 <div id="content" class="span10">  
			<div> 
				<ul class="breadcrumb">
				  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
				  <li> <a href="#">User Management</a> <span class="divider"> &gt;&gt; </span></li>
				  <li><a href="#">Group Definition View</a></li>
				</ul>
			</div>  
			
			<table height="3">
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
						<i class="icon-edit"></i>Group Definition View
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
							class="table table-striped table-bordered bootstrap-datatable "> 
							
						<tr >
							<td  width="8%">&nbsp;</td>
							<td  width="92%"><strong> Rights not assigned for the group ${groupID}. </strong></td>
						</tr> 
				</table>  
		</fieldset>  
		
		</div>
		</div>
		</div>
		<div class="form-actions">
			<input type="button" name="btn-submit" class="btn btn-primary" id="btn-submit" value="Next" /> 
	   </div>  
 </div> 
           
 </form>
</body>
</html>

