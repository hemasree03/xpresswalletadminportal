<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>NBK</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
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
.modal-content {
  background-color: #0480be;
}
.modal-body {
  background-color: #fff;
}
</style>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/bootstrap-2.3.2.min.js'></script>
<link href="${pageContext.request.contextPath}/css/link/css1" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/link/screen.css">
<link href="${pageContext.request.contextPath}/css/link/sticky.css" rel="stylesheet" type="text/css">

 
<script type="text/javascript" > 

		
	
	var subrules = {
			   rules : {
				   fullname: { required : true},
				   lastname: { required : true},
				   dob: { required : true},
				   telephone1: { required : true},
				   gender: { required : true},
				   marital: { required : true},
				   bvn: { required : true},
				   
			   },  
			   messages : {
				   fullname : { 
				   		required : "Please enter First Name."
					},
					lastname: { 
								   		required : "Please enter Last Name."
									},
					dob: { 
								   		required : "Please select Date Of Birth."
									},
					telephone1: { 
								   		required : "Please enter Mobile Number."
									},
					gender: { 
								   		required : "Please select Gender."
									},
					marital: { 
								   		required : "Please select Marital Status."
									},
									bvn: { 
								   		required : "Please enter BVN."
									}
					
			   } 
			 };
		

			
		
	var list = "dob".split(",");
	var datepickerOptions = {
					showTime: true,
					showHour: true,
					showMinute: true,
		  		dateFormat:'dd-mm-yy',
		  		alwaysSetTime: false,
		  		yearRange: '-110:-18',
		  		maxDate: '-18Y',
				changeMonth: true,
				changeYear: true
	};	

		    
	 
	$(document).ready(function(){
		
		
		
		var myApp;
		myApp = myApp || (function () {
		    var pleaseWaitDiv = $('<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false"><div class="modal-header"><h1>Processing...</h1></div><div class="modal-body"><div class="progress progress-striped active"><div class="bar"  style="width: 100%;"></div></div></div></div>');
		    return {
		        showPleaseWait: function() {
		            pleaseWaitDiv.modal();
		        },
		        hidePleaseWait: function () {
		            pleaseWaitDiv.modal('hide');
		        },

		    };
		})();
		
		
	
	$('#btn-submit').click(function(){ 
		
			$("#form1").validate(subrules);
			
			
			
			
			//myApp.showPleaseWait();
				
 				$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/accountopeningconfirm.action'; 
				$('#form1').submit();
				 return true; 
			
	});
	
	$('#btn-back').click(function(){ 
		//$("#form1").validate(acctRules);
		
			$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/home.action'; 
			$('#form1').submit();
			
	});
	
	
	 $(list).each(function(i,val){
			$('.'+val).datepicker(datepickerOptions);
		}); 

});



</script>

</head>

<body>
	<form name="form1" id="form1" method="post" action="">
		<div id="content" class="span10">
		    <div>
				<ul class="breadcrumb">
				  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
				  <li><a href="#">Account Opening</a></li>
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
					<div class="box-header well" data-original-title>
						<i class="icon-edit"></i>Account Opening Details
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
						</div>
					</div>
					<div class="box-content" id="primaryDetails">
						<fieldset>
						
						
						
							<table width="950" border="0" cellpadding="5" cellspacing="1" 
									class="table table-striped table-bordered bootstrap-datatable " id="user-details"> 
						   
						<tr class="even">
							<td width="25%"><label for="From Date"><strong>First Name<font color="red">*</font></strong></label></td>
							<td width="25%">
							<input type="text" name="fullname"  id="fullname"     /></td>
							<td width="25%"><label for="From Date"><strong>Middle Name</strong></label></td>
						    <td width="25%"><input type="text" name="middlename"  id="middlename"     /></td>
							
							</tr>
						<tr class="even">
						<td width="25%"><label for="From Date"><strong>Last Name<font color="red">*</font></strong></label></td>
						    <td width="25%"><input type="text" name="lastname"  id="lastname"     /></td>
						<td  ><label for="To Date"><strong>Date Of Birth<font color="red">*</font></strong></label> </td>
							<td>
							<input type="text" maxlength="10"  class="dob" id="dob" name="dob"  readonly="readonly" />
							</td>
						
							
							  
						</tr>
						<tr class="even">
						
							<td ><label for="From Date"><strong>Email ID</strong></label></td>
							<td ><input type="text" name="email"  id="email"      />  </td>
						   <td><label for="To Date"><strong>Mobile Number<font color="red">*</font></strong></label> </td>
					       <td >
					    
								<input type="text" name="isocode" id="isocode" style="width:25px;" value="234" disabled />
								<input type="text" class="field" maxlength="10" name="telephone1" id="telephone1"  style="width:172px;"/>
 							<input type="hidden" class="field"  name="telephone" id="telephone"  />
						     
						   </td> 
						    
 						</tr>
 						<tr class="even" >
 						 <td><strong>Gender<font color="red">*</font></strong></td>
						      <td>
						      <s:select cssClass="chosen-select" 
							         headerKey="" 
							         headerValue="Select"
							         list="#{'Male':'Male','Female':'Female'}" 
							         name="gender" 
							         id="gender"
							         requiredLabel="true" 
							         theme="simple"
							         data-placeholder="Choose Account Type..." 
							           />
						    
						      </td>
							<td><strong>Marital Status<font color="red">*</font></strong></td>
						      <td>
						      <s:select cssClass="chosen-select" 
							         headerKey="" 
							         headerValue="Select"
							         list="#{'Married':'Married','Unmarried':'Unmarried'}" 
							         name="marital" 
							         id="marital"
							         requiredLabel="true" 
							         theme="simple"
							         data-placeholder="Choose Account Type..." 
							           />
						    
						      </td>
						      
						</tr>
						<tr>
						
						<td><label for="Local Government"><strong>BVN<font color="red">*</font></strong></label></td>
												<td>
												<input  type="text" class="field" id="bvn" name="bvn" maxlength="11" /></td>
												<td></td>
												<td></td>
						</tr>
						</table>
							<input type="hidden" name="srchcriteria"  id="srchcriteria"  value="ACCOUNTOPEN" /> 
							</fieldset>
					</div>
			</div>
		</div>
		
		
		<input type="hidden" name="apptype"  id="apptype"   /> 
		
	<div class="form-actions">
		<a  class="btn btn-danger" href="#" id="btn-back" name="btn-back">Home</a>
		<a  class="btn btn-success" href="#" id="btn-submit" name="btn-submit" >Proceed</a>
		<span id="billerMsg" class="errors"></span>
	</div>
</div> 
</form>
<script type="text/javascript">
$(function(){
	
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
</script>
</body>
</html>
