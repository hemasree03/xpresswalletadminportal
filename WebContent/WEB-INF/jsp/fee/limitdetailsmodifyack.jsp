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
	
	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/productlimitsettings.action';
	$("#form1").submit();
	return true; 
	}
	
$(document).on('click','a',function(event) {
	 $('#btn-submit').live('click', function () { 
 	    
				var url="${pageContext.request.contextPath}/<%=appName %>/limitsettingsmodifyack.action"; 
 				$("#form1")[0].action=url;
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
			  <li> <a href="#">Limit Code Details </a>  <span class="divider"> &gt;&gt; </span> </li>
			
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
					<i class="icon-edit"></i>Limit Code Details 
					
					
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
				</div>
			</div> 
		
      				 
		<div class="box-content">
		
								
								<table width="950" border="0" cellpadding="5" cellspacing="1"  class="table table-striped table-bordered bootstrap-datatable ">
								<tr class="even">
								<td width="20%"><label for="Fee Code"><strong>Product Code</strong></label></td>
								<td width="30%"><s:property value='#respData.productcode' />
								<input name="productcode" type="hidden" maxlength ='6' id="productcode" required="true" class="field"  value="<s:property value='#respData.productcode' />"  readonly /> <span id="bin_err" class="errmsg"></span></td>
								 <td width="20%"><label for="Fee Description"><strong>Application</strong></label></td>
								 <td width="30%"><s:property value='#respData.application' />
								 <input name="application"  type="hidden" maxlength ='25' id="application" required="true"  class="field"  value="<s:property value='#respData.application' />" /><span id="bin_err1" class="errmsg"></span> </td>
							</tr>
							<tr class="even">
								<td width="20%"><label for="Limit Code"><strong>Limit Code</strong></label></td>
								<td width="30%"><s:property value='#respData.limitCode' />
								<input type="hidden" value="<s:property value='#respData.limitCode' />" name="limitCode" id="limitCode" />
								</td>
								 <td width="20%"><label for="Limit Description"><strong>Limit Description</strong></label></td>
								 <td width="30%"><s:property value='#respData.limitDesc' />
								 <input type="hidden" value="<s:property value='#respData.limitDesc' />" name="limitDesc" id="limitDesc" />
								  </td>
							</tr>
							<%-- <tr class="even">
								<td width="20%"><label for="Fee Code"><strong>Channel</strong></label></td>
								<td width="30%"><s:property value='#respData.limitcodedetails.CHANNEL' />
								 <td width="20%"></td>
								 <td width="30%"></td>
							</tr> --%>
								<tr class="even">
								<td width="20%"><label for="Fee Code"><strong>Channel</strong></label></td>
								<td width="30%"><s:property value='#respData.limitcodedetails.CHANNEL' />
								 <td width="20%"><label for="Fee Code"><strong>Operators</strong></label></td>
								 <td width="30%"><s:property value='#respData.limitcodedetails.OPERATORS' /></td>
							</tr> 
							
							</table>
								
							<input type="hidden"  name="trrefno" id="trrefno" />
                								<input name="channel" type="hidden" id="channel" required="true" class="field"  value="<s:property value='#respData.limitcodedetails.CHANNEL' />"  readonly /> <span id="bin_err" class="errmsg"></span>
                
                        </div>
                   

						   </div>	
						   
						   
			  </div>
			  
			  	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" >
					<i class="icon-edit"></i>   Limit Transaction Details 
					
					
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
				</div>
			</div> 
			
			<div class="box-content">
			<fieldset>   
						<table width="950" border="0" cellpadding="5" cellspacing="1"  class="table table-striped table-bordered bootstrap-datatable ">
							<tr class="even">
								<td width="20%"><label for="Transaction"><strong>Transaction</strong></label></td>
								<td width="30%"><s:property value='#respData.limitcodedetails.TXNNAME' /></td>
								 <td width="20%"><label for="Frequency"><strong>Frequency</strong></label></td>
								 <td width="30%"><s:property value='#respData.limitcodedetails.FREQ' /></td>
							</tr>
							
							<tr class="even">
								<td width="20%"><label for="MaxCount"><strong>Max Count<font color="red">*</font></strong></label></td>
								<td width="30%"><s:property value='#respData.limitcodedetails.MAX_CNT' /></td>
								 <td width="20%"><label for="Min Amount"><strong>Min Amount <font color="red">*</font></strong></label></td>
								 <td width="30%"><s:property value='#respData.limitcodedetails.MIN_AMT' /></td>
							</tr>
							
							<tr class="even">
								<td width="20%"><label for="Max Amount"><strong>Max Amount<font color="red">*</font></strong></label></td>
								<td width="30%"><s:property value='#respData.limitcodedetails.MAX_AMT' /></td>
								 <td width="20%"></td>
								 <td width="30%"></td>
							</tr>
							
						</table>
						Successfully Updated, Pending for Authorization.
					</fieldset>
			
		
      				 </div>	 
		

						   </div>	
						   
						   
			  </div>
			  
			  
			  
			  <div class="form-actions" align="left"> 
				
				<a class="btn btn-min btn-success" href="#" onClick="redirectfun()">Next</a>
				
			</div>  
			  
			  
			  
			 
			</div>
		  
		  </form>	
</body>
</html>
<!-- END PAGE -->