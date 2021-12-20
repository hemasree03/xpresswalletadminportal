<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>BackOffice</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="">
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%@taglib uri="/struts-tags" prefix="s"%> 
<%String ctxstr = request.getContextPath(); %>
<% String appName= session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>
<link href="${pageContext.request.contextPath}/css/body.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/lightbox.css"  type="text/css" />

<script src="${pageContext.request.contextPath}/js/datafetchfillinng.js"></script>
 
<script type="text/javascript" > 


 
 
function subitReq()
{   
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/configuration.action';
		$("#form1").submit();
		return true; 
			
} 

function redirectAct()
{   
	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action';
	$("#form1").submit();
	return true; 
		
} 
	
</script>
<s:set value="responseJSON" var="respData" />
</head>

<body>
<form name="form1" id="form1" method="post" action="">	
	<div id="content" class="span10">  
			<div>
				<ul class="breadcrumb">
				  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
				  <li><a href="#">Configuration</a></li>
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
					
					<div id="primaryDetails" class="box-content">
						
							<div class="box span12"> 
					<div class="box-header well" data-original-title>Initial Product Configuration
					  <div class="box-icon"> 
						<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
						<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a> 
					  </div>
					</div>
			<div class="box-content"> 				
			<fieldset>
			
						<table width="950" border="0" cellpadding="5" cellspacing="1" 
							   class="table table-striped table-bordered bootstrap-datatable " id="bank-details">
													
													<tr class="odd">
														<td width="20%"><label for="Channel"><strong>Product Code</strong></label></td>
														<td width="30%">${responseJSON.product}
														<input name="product" id="product"  type="hidden"   value="${responseJSON.product}"/>
														</td>											
														<td width="20%"><label for="Services"><strong>Product Name </strong></label></td>
														<td width="30%">${responseJSON.productname}
														<input name="productname" id="productname"  type="hidden"  value="${responseJSON.productname}"/></td>	
														
													</tr>
								
													
													
						</table>
							
			</fieldset>
			
			</div> 
			
			</div> </div> 
			
			

			<br/>	
				
			
	</div>
		</div>
				
				
				
				<input type="button" id="non-printable" class="btn btn-success" onclick="redirectAct();" value="Home" />
				<input type="button" id="non-printable" class="btn btn-success" onclick="subitReq();" value="Next" />
		
		
		</div>
		

		
		</form>
		
		
<script type="text/javascript" src='${pageContext.request.contextPath}/js/jquery.dataTables.min.js'></script> 
<script type="text/javascript" src='${pageContext.request.contextPath}/js/datatable-add-scr.js'></script> 

</body>
</html>
