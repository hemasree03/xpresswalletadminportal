<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>MicroInsurance</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="">
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String ctxstr = request.getContextPath();
	String appName = session.getAttribute(
			CevaCommonConstants.ACCESS_APPL_NAME).toString();
%>
<script language="Javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script language="Javascript" src="${pageContext.request.contextPath}/js/authenticate.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/sha256.js" ></script>
<link href="${pageContext.request.contextPath}/css/body.css" rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath}/css/link/css1" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/link/screen.css">
<link href="${pageContext.request.contextPath}/css/link/sticky.css" rel="stylesheet" type="text/css">

<script type="text/javascript">




function funcall(v,v1){
	
	$("#application").val(v);
	$("#txntype").val(v1);
	var url="${pageContext.request.contextPath}/<%=appName %>/Settlemetrequestapprovaldetails.action"; 
	$("#form1")[0].action=url;
	$("#form1").submit();
	return true;
}


function redirectAct(){
				$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action';
				$("#form1").submit();
				return true;
}

			






function funsubmit2(){
	
	$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/pendingConfig.action";
	$("#form1").submit();
	return true;
}
 

function funsubmit3(){
	
	$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/proccedConfig.action";
	$("#form1").submit();
	return true;
}
function funsubmit4(){
	
	$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/closeConfig.action";
	$("#form1").submit();
	return true;
}

</script>

<s:set value="responseJSON" var="respData" />
<style>
div.gallery {
    margin: 5px;
    
    float: left;
    width: 70px;
    height: 70px;
    cursor: pointer;
}


div.gallery img {

    width: 70px;
    height: 70px;
}

div.desc {
    padding: 15px;
    text-align: center;
}


.numberCircle {
    border-radius: 100%;
    behavior: url(PIE.htc); /* remove if you don't care about IE8 */

    width: 60px;
    height: 60px;
    padding: 8px;

    background: #fff;
    /* border: 2px solid #666; */
    color: #666;
    text-align: center;

    font: 16px Arial, sans-serif;
}



</style>
</head>
<body>
<form name="form1" id="form1" method="post" >
	<div id="content" class="span10">
			<!-- content starts -->
			<div>
				<ul class="breadcrumb">
					<li><a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span></li>
					<li><a href="#">Dispute Request</a></li>
					
				</ul>
			</div>
			
			 <div id="errormsg" class="errores"></div>
			 <div  class="screenexit"></div>
			 
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<!-- Customer Negotiated Rate Confirmation -->
						Dispute Request
						 <div class="box-icon"> 
						<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
						<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a> 
					  </div>
					</div>
					
				 
					<div class="box-content" id="secondaryDetails">
						<fieldset>
						
						<table width="100%"  border="0" cellpadding="5" cellspacing="1" align="center" >
						<tr><td width="30%">
						<table border="0" width="100%" ><tr><td colspan="2" >
						<span  style="color: #000000;font-weight: bold;font-size: 15px;">Pending</span>
						</td></tr>
						<tr><td width="30%" >
						<div class="gallery"  id="pending" onclick=pending(this.id) >
						 
						   <img src="${pageContext.request.contextPath}/images/Agent/pending.png"  width="100" height="100">
						   
						  </div>
						  </tr></table>
						</td>
						<td width="30%">
						
						
						<table border="0" width="100%" ><tr><td colspan="2" >
						<span  style="color: #000000;font-weight: bold;font-size: 15px;">Processing</span>
						</td></tr>
						<tr><td width="30%" >
						<div class="gallery"  id="processed" onclick=process(this.id) >
						 
						   <img src="${pageContext.request.contextPath}/images/Agent/processing.png"  width="100" height="100">
						   
						  </div>
						  </tr></table>
						
						
						</td>
						
						
						
						<td width="30%">
						
						<table border="0" width="100%" ><tr><td colspan="2" >
						<span  style="color: #000000;font-weight: bold;font-size: 15px;">Close</span>
						</td></tr>
						<tr><td width="30%" >
						<div class="gallery"  id="close" onclick=funsubmit1(this.id) >
						 
						   <img src="${pageContext.request.contextPath}/images/Agent/close.png" width="100" height="100">
						   
						  </div>
						  </tr></table>
			         	</td>
					<tr>
						<td>
						
						<table border="0" width="100%" ><tr><td colspan="2" >
					
						</td></tr>
						<tr><td width="30%" >
					
					   <input type="button" class="btn btn-success" name="btn-submit" id="btn-submit" value="Pending View" width="100" onclick="funsubmit2(this.id)"  ></input> 
			   
						  </div>
						  </td> </tr></table>
						
						
						
						</td>
						<td>
						
						
						<table border="0" width="100%" ><tr><td colspan="2" >
				
						</td></tr>
						<tr><td width="30%" >
					
					
					   <input type="button" class="btn btn-success" name="btn-submit" id="btn-submit" value="Proccessing View" width="100" onclick="funsubmit3(this.id)" ></input> 
					
					  </div>
						  </td></tr></table>
						
						
						</td><td>
						
						
						<table border="0" width="100%" ><tr><td colspan="2" >
						</td></tr>
						<tr><td width="30%" >
						 
					   <input type="button" class="btn btn-success" name="btn-submit" id="btn-submit" value="Close View" width="100" onclick="funsubmit4(this.id)" ></input> 
			   
					   
						  </div>
						  </td> </tr></table>
						
						
						</td>
					
						</table>
						
						
						
				
			
				 
					<div class="box-content" id="secondaryDetails">
						<fieldset>
						
							
							<table width="950"  border="0" cellpadding="5" cellspacing="1" 
								class="table table-striped table-bordered bootstrap-datatable" id="check-kra-details" >
								
								<tr class="odd" id="mobileselect" >
								<td width="25%" ><strong>Request Type</strong></td>
									<td width="25%" ><strong>Count</strong></td>
									
									
							</tr>
							
							<tr class="odd" id="searchenter" >
									<td >Pending</td>
									
									<td ><div class='label label-success' style="cursor: pointer;width:20px;text-align:center">${responseJSON.VIEW_LMT_DATA.count}</div></td>
								</tr>
								
						
								
								<tr class="odd" id="searchenter" >
									<td >Processed</td>
									<td ><div class='label label-success' style="cursor: pointer;width:20px;text-align:center">${responseJSON.VIEW_LMT_DATA.count2}</div></td>
									
								</tr>
								
								<tr class="odd" id="searchenter" >
									<td >Close</td>
									<td ><div class='label label-success'  style="cursor: pointer;width:20px;text-align:center">${responseJSON.VIEW_LMT_DATA.count3}</div></td>
								
								</tr>
								
								
								
								
							</table>
							
						</fieldset>
						
					</div>
					
				</div>
			</div>
			
			
			<input name="application" type="hidden" id="application"     />
			<input name="txntype" type="hidden" id="txntype"     />
			
			<div class="form-actions" align="left"> 
				<input type="button" id="non-printable" class="btn btn-info" onclick="redirectAct();" value="Cancel" />
				 
			</div>  

	
			
			</div>
			

</form>

	
</body>
</html>
