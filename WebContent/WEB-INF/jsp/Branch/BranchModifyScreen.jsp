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
<s:set value="responseJSON" var="respData"/>
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

$(document).ready(function() {

	$('#non-printable').live('click', function () {
		$("form").validate().cancelSubmit = true;
		var url="${pageContext.request.contextPath}/<%=appName %>/clusterCreation.action";
		$("#form1")[0].action=url;
		$("#form1").submit();

	});

	$('#btn-submit').live('click', function () {
		$("#form1").validate(datavalidation);
		
		if ( $('#locstatus').attr('checked')) {
			$('#state').val(($('#stated').val()).split("-")[1]);
			$('#localGovernment').val($('#localGovernment1').val());
	    } else {
	    	$('#state').val("<s:property value='#respData.limitcodedetails.STATE' />");
			$('#localGovernment').val("<s:property value='#respData.limitcodedetails.LGA' />");
	    }
		
		var url="${pageContext.request.contextPath}/<%=appName %>/BranchModifyScreencnf.action"; 
		$("#form1")[0].action=url;
		$("#form1").submit();

		
	});
});

var datavalidation = {
 		
 		rules : {
 			
 			limitDesc: { required : true}
			
 		},		
 		messages : {
 			
 			
					 limitDesc : { 
 							required : "Please Enter Branch Name."
					 }
			
					 
 		} 
 };
 
$(document).ready(function() {
	
	 if("<s:property value='#respData.limitcodedetails.status' />"=="A"){
		 $("#stastus").html("<div class='label label-success' >Active</div>");	
		}
	 else{
			
		 $("#stastus").html("<div class='label label-important' >Deactive</div>");
		}
	
});

$(function() {  
	$('#state').val("<s:property value='#respData.limitcodedetails.STATE' />");
	$('#localGovernment').val("<s:property value='#respData.limitcodedetails.LGA' />");
	
	$('#locstatus').click(function() {
	
		if ( $('#locstatus').attr('checked')) {
			$('#locstatus1').css("display","");
			
	    } else {
	    	$('#locstatus1').css("display","none");
	    	
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
		    
	var queryString1 = "sid=STATE";

	$.getJSON("branchcodeajx.action", queryString1, function(data) {
			if(data.region!=""){
				var mydata=data.region;
				var mydata1=mydata.split(":");

				$.each(mydata1, function(i, v) {
					
					var options = $('<option/>', {value: mydata.split(":")[i], text: (mydata.split(":")[i]).split("-")[1]}).attr('data-id',i);
					
					$('#stated').append(options);
					$('#stated').trigger("liszt:updated");
				});
				
			}
	});
				$('#stated').on('change', function() {
					//alert($('#state').val());
					 var queryString1 = "sid=STATESEARCH&serialNumber="+($('#stated').val()).split("-")[0];
					 $('#state').val(($('#stated').val()).split("-")[1]);
					$.getJSON("branchcodeajx.action", queryString1, function(data) {
							if(data.region!=""){
								var mydata=data.region;
				  			var mydata1=mydata.split(":");
				  			
				  			
				  			$('#localGovernment1').empty();
							$('#localGovernment1').trigger("liszt:updated");
							
				   			$.each(mydata1, function(i, v) {
				   				
				   				var options = $('<option/>', {value: mydata.split(":")[i], text: mydata.split(":")[i]}).attr('data-id',i);
				   				
				   				$('#localGovernment1').append(options);
				   				$('#localGovernment1').trigger("liszt:updated");
				   			});
				   			
							}
							
					});
					
				});	
		});	
</script>


 
<body class="fixed-top">
<form name="form1" id="form1" method="post" >
	
      <div id="content" class="span10"> 
	    <div> 
			<ul class="breadcrumb">
			  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
			 <li><a href="clusterCreation.action">Branch Creation</a> <span
						class="divider">&gt;&gt;</span></li>
				<li><a href="clusterCreation.action">Branch Modify</a> <span
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
				
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" >
					<i class="icon-edit"></i>Branch Details 
					
					
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
				</div>
			</div> 
		
      				 
		<div class="box-content">
		
								
								<table width="950" border="0" cellpadding="5" cellspacing="1"  class="table table-striped table-bordered bootstrap-datatable ">
								<tr class="even">
								<td width="25%"><label for="Limit Code"><strong>Branch Id</strong></label></td>
								 <td width="25%"><s:property value='#respData.limitcodedetails.limitCode' />
								 <input name="limitCode" id="limitCode" type="hidden"  required="true" class="field" value="<s:property value='#respData.limitcodedetails.limitCode' />" />
								 </td>
								
								 <td width="25%"><label for="Limit Description"><strong>Branch Name<font color="red">*</font></strong></label></td>
							<td width="25%"><input name="limitDesc" id="limitDesc" type="text"  required="true" class="field" value="<s:property value='#respData.limitcodedetails.limitDesc' />" /><span id="bin_err1" class="errmsg"></span></td>
							
							</tr>
							<tr class="even">
								<td width="25%"><label for="Limit Code"><strong>State</strong></label></td>
								<td width="25%"><s:property value='#respData.limitcodedetails.STATE' /></td>
								 <td width="25%"><label for="Limit Description"><strong>Local Government</strong></label></td>
								 <td width="25%"><s:property value='#respData.limitcodedetails.LGA' /> </td>
							</tr>
							<tr class="even">
								<td width="25%"><label for="Limit Code"><strong>Status</strong></label></td>
								<td width="25%" id="stastus"></td>
								 <td width="25%"></td>
								 <td width="25%"></td>
							</tr>
							
							</table>
							
							<input type="checkbox" name="locstatus" id="locstatus"><strong>Change Location</strong>
								
							<table width="950" border="0" cellpadding="5" cellspacing="1"  class="table table-striped table-bordered bootstrap-datatable " id="locstatus1" style="display:none">
							<tr class="odd">


													<td width="25%"><label for="State"><strong>State<font
																color="red">*</font></strong></label></td>
													<td width="25%"><select id="stated" name="stated"
														class="chosen-select-width" style="width: 220px;">
															<option value="">Select</option>
													</select> <!-- <input name="state" type="hidden" class="field" id="state" /> -->
													</td>
													<td width="25%"><label for="Local Government"><strong>Local
																Government<font color="red">*</font>
														</strong></label></td>
													<td width="25%"><select id="localGovernment1"
														name="localGovernment1" class="chosen-select-width"
														style="width: 220px;">
															<option value="">Select</option>
													</select></td>
												</tr>
							</table>
                
                        </div>
                   

						   </div>	
						   
						   
			  </div>
			  
			  	<input type="hidden" id="localGovernment"	name="localGovernment" >
			  		<input type="hidden" id="state"	name="state" >
			  
			<input type="button" id="non-printable" class="btn btn-info" onclick="redirectHome();" value="BACK" />
					 <input type="button" class="btn btn-success" name="btn-submit" id="btn-submit" value="SUBMIT" width="100"  ></input> 
						 
				
			</div>
			</div>
		  
		  </form>	
</body>
</html>
<!-- END PAGE -->