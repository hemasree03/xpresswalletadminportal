<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<%@taglib uri="/struts-tags" prefix="s"%>  
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta charset="utf-8">
<title>Ceva</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="Team">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/nicEdit/nicEdit.js"></script>
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%@taglib uri="/struts-tags" prefix="s"%>  
<%String ctxstr = request.getContextPath(); %>
<% String appName= session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString();%>
<%@taglib uri="/struts-tags" prefix="s"%> 

<style type="text/css">
.errors {
	  font-weight: bold;
	  color: red;
	  padding: 2px 8px;
	  margin-top: 2px;
}
input#abbreviation{text-transform:uppercase};

</style>
<s:set value="responseJSON" var="respData"/>
 <SCRIPT type="text/javascript">


$(document).ready(function(){   
	
 	$('#btn-submit').live('click',function() {  
 		var message = $("#area2").val();
 		var nicInstance = $("div.nicEdit-main").html();

 		$('#cmpMessage').val(encodeURI(nicInstance));
 		
       var finalData = "";
 		 $("#error_dlno").text(''); 
		
			
				 $("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/insertemailCampDetailsAct.action";
				 $("#form1").submit(); 
				 
			
	}); 
	
    $('#btn-Cancel').live('click',function() {  
		$("#form1").validate().cancelSubmit = true; 
		
		
		 	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/campaignmgntact.action';		
			$("#form1").submit();		
	}); 
	
});


$(function() {
	 
	var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
	
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    } 
	 
});

</script> 


</head> 
<body>
	<form name="form1" id="form1" method="post" >
	  <div id="content" class="span10"> 
			 
		    <div> 
				<ul class="breadcrumb">
				  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
				  <li><a href="campaignmgntact.action">Campaign Management</a><span class="divider"> &gt;&gt; </span> </li>
				   <li><a href="#">Campaign Management</a></li>
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
		  <div class="row-fluid sortable"> 
			<div class="box span12">  
				<div class="box-header well" data-original-title>
					 <i class="icon-edit"></i>To Mail
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
						<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
						
					</div>
				</div>
							
			<div class="box-content">
				<fieldset>

				 <s:select cssClass="chosen-select" multiple="true" headerKey=""
											headerValue="Select" list="#respData.EMAILID"
											name="tomailid" id="tomailid" requiredLabel="true"
											theme="simple" data-placeholder="Choose Email Id"  style="width:98%" /> 
			</fieldset>
		</div>
		</div>
		
		</div>
		 <div class="row-fluid sortable"> 
			<div class="box span12">  
				<div class="box-header well" data-original-title>
					 <i class="icon-edit"></i>Subject
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
						<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
						
					</div>
				</div>
							
			<div class="box-content">
				<fieldset>

				<input name="templateName" id="templateName"  type="text" class="field" required=true style="width:98%" />
			</fieldset>
		</div>
		</div>
		
		</div>
		
              
				<input type="hidden" name="cmpMessage" id="cmpMessage" value=""/>
 				


 <div class="row-fluid sortable"> 
		<div class="box span12"> 
				<div class="box-header well" data-original-title>
						<i class="icon-edit"></i>Subject Body
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
						<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
					</div>
				</div>  
				
			<div id="remarksInformation1" class="box-content"> 								
				
				<textarea name="area2" id="area2" style="width: 100%;height: 400px"></textarea>
			</div></div>
		
		
		</div>	

  
    	<div class="form-actions" >
	         <input type="button" class="btn btn-success"  name="btn-submit" id="btn-submit" value="Send" width="100" ></input>&nbsp;
	         <input type="button" class="btn btn-info" name="btn-Cancel" id="btn-Cancel" value="Back" width="100" ></input> &nbsp;
       </div>  
			</div>  
				
 </form> 

 
  
  <script type="text/javascript">
 
  bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });

</script>
</body> 
</html>