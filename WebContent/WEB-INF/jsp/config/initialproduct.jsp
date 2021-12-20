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

function redirectAct()
{
	$("#form2")[0].action='<%=request.getContextPath()%>/<%=appName %>/ParameterCreation.action';
	$("#form2").submit();
	return true;
} 

var finaljson; 

$(document).ready(function () {
	
	 $('#productcode').on('change', function (e) {
		
		 var valueSelected = this.value;
		 if((this.value)==""){
			 $('#productname').val("");
			 $('#productname1').text("");
			 $('#product').val(""); 
		 }else{
		 $('#productname').val((valueSelected).split("-")[1]);
		 $('#productname1').text((valueSelected).split("-")[1]);
		 $('#product').val((valueSelected).split("-")[0]);
		 }
	 });
	
	
	 var queryString9 = "method=fetchConfiguredData" 
			
			$.getJSON("postJson.action", queryString9,function(data) {
					if(data.message=="1"){
						$("#productcode").val(data.custcode+"-"+data.accNumber);
						$('#productcode').trigger("liszt:updated");
						
						 var valueSelected = $("#productcode").val();
						$('#productname').val((valueSelected).split("-")[1]);
						 $('#productname1').text((valueSelected).split("-")[1]);
						 $('#product').val((valueSelected).split("-")[0]);
						
					}	
						
				});
	 
	 
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
		    
		  
	
});

 
var datavalidation = {
 		
 		rules : {
 			productcode	: { required : true}
			
 		},		
 		messages : {
 			
 			productcode : { 
 							required : "Please Select Product Code"
			
 			}		 
 		} 
 };
 
 
function subitReq()
{   
	$("#form1").validate(datavalidation);
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/initialproductconfirm.action';
		$("#form1").submit();
		return true; 
			
} 

function redirectAct()
{   
	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/configuration.action';
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
														<td width="20%"><label for="Channel"><strong>Product Code<font color="red">*</font></strong></label></td>
														<td width="30%">
														<s:select cssClass="chosen-select" headerKey=""
															headerValue="Select" list="#respData.PRODUCT_DETAILS"
															name="productcode" id="productcode" requiredLabel="true"
															theme="simple" data-placeholder="Choose Channel"
															required="true" /> 
														</td>											
														<td width="20%"><label for="Services"><strong>Product Name<font color="red">*</font></strong></label></td>
														<td width="30%">
														<input name="product" id="product"  type="hidden"  class="field" />
														<input name="productname" id="productname"  type="hidden"  class="field" />
														<div id="productname1"></div></td>	
														
													</tr>
								
													
													
						</table>
							
			</fieldset>
			
			</div> 
			
			</div> 
			
			

			<br/>	
				
			
	</div>
		</div>
				
				
				
				<input type="button" id="non-printable" class="btn btn-success" onclick="redirectAct();" value="Back" />
				<input type="button" id="non-printable" class="btn btn-success" onclick="subitReq();" value="Submit" />
		
		
		</div>
		

		
		</form>
		
		
<script type="text/javascript" src='${pageContext.request.contextPath}/js/jquery.dataTables.min.js'></script> 
<script type="text/javascript" src='${pageContext.request.contextPath}/js/datatable-add-scr.js'></script> 

</body>
</html>
