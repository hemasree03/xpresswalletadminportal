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
<%-- function redirectfun()
{
	
	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/clusterCreation.action';
	$("#form1").submit();
	return true; 
}
	
	
$('#btn-submit').live('click', function () {
	var url="${pageContext.request.contextPath}/<%=appName %>/BranchModifySave.action"; 
	$("#form1")[0].action=url;
	$("#form1").submit();

});
}); --%>

function redirectfun()
{
	
	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/clusterCreation.action';
	$("#form1").submit();
	return true; 
	}

</script>


<s:set value="responseJSON" var="respData"/> 
<body class="fixed-top">
<form name="form1" id="form1" method="post" >
	
      <div id="content" class="span10"> 
	    <div> 
			<ul class="breadcrumb">
			  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
			 <li><a href="clusterCreation.action">Branch Creation</a> <span
						class="divider"> &gt;&gt; </span></li>
						<li><a href="clusterCreation.action">Branch Modify Acknowledgment</a> <span
						class="divider"></span></li>
			
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
		
      				 
		<div class="box-content">
		
							<div class="box-content" id="primaryDetails">  
						  <fieldset> 
								<table width="950" border="0" cellpadding="5" cellspacing="1" 
									class="table table-striped table-bordered bootstrap-datatable ">
									<tr class="even">
										<td ><strong>Branch Modify  Successfully Updated </strong></td>
									</tr>
								</table>
							 </fieldset> 
							</div>
							
                
                        </div>
                   

						   	
				<div >
				<a class="btn btn-min btn-success" href="#" onClick="redirectfun()">Next</a>
			</div>		   
						   
			  </div>
			  
			  	
		
					  
		  </form>	
</body>
</html>
<!-- END PAGE -->