<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
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
</style>
<script type="text/javascript">


var tconfigid = "Please select Configuration ";


var configidRule={required: true};


var configidMessages = {required: tconfigid};

var valid = {

		rules :{
			
			configid : configidRule
		},
		messages :{

			
			configid: configidMessages
		}
};
function queryUser()
{
	$("#form1").validate(valid);
	if($("#form1").valid())
	{
		
		if($("#configid").val()=="Initial Product"){
			event.preventDefault();
	          $(this).prop('disabled', true);
				$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/initialproduct.action';
				$("#form1").submit();

				return true;

		}
		
	}
	else
	{
			return false;
	}
}


function getdata(){
if($("#configid").val()==""){
		
		$("#errorm").text("Please select Configuration");
		return false;
}
	if($("#configid").val()=="Initial Product"){
		$("#errorm").text("");
		
	
	var queryString9 = "method=fetchConfiguredData" 
	
	$.getJSON("postJson.action", queryString9,function(data) {
			if(data.message=="1"){
				$("#product").text(data.custcode);
				$("#productname").text(data.accNumber);
				$("#makerid").text(data.userId);
				$("#distable").css("display","");
				
				$("#save").css("display","none");
				
			}else{
				
				$("#errorm").text("Initail product not yet configured");
				//alert("Kailash here");
				$("#add").css("display","");
			}
				
				
				});
	}
}

function getOption(key,value)
{
	return "<option value='"+value+"'>"+key+"</option>";
}







$(document).ready(function(){
	
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
<s:set value="responseJSON" var="respData" />
</head>


<body>
<form name="form1" id="form1" method="post">

			<div id="content" class="span10">
			    <div>
					<ul class="breadcrumb">
					  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
					  <li> <a href="#">Configuration </a>  </li>
 					</ul>
				</div>

				<table>
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
					<i class="icon-edit"></i>Configuration
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>

				</div>
			</div>
		<div class="box-content">
			<fieldset>
				 <table width="950" border="0" cellpadding="5" cellspacing="1"
							class="table table-striped table-bordered bootstrap-datatable " id="user-details">
				<tr class="even">
					<td width="20%" ><label for="User Id"><span id="enname">Configuration</span> <font color="red">*</font></label></td>
					<td width="30%" >
					<select class="chosen-select" name="configid" 
										id="configid" >
										<option value="">Select</option>
										<option value="Initial Product">Initial Product</option>
										<!-- <option value="POS">POS Configuration</option> -->
								</select>
						
					</td>

					<td width="20%" ></td>
					<td width="30%" >
				
					</td>
				</tr>
				
		 </table>
		 <br></br>
		<table class="table table-striped table-bordered bootstrap-datatable "  style="width: 100%;display:none"  id="distable">
						  <thead>
								<tr>
									<th>Product Code</th>
									<th>Product Name</th>
									<th>Maker Id</th>
									<th>Action</th>
									
								</tr>
						  </thead>    
						  <tbody > 
							  <tr>
							  		
						  			<td><span id="product" name="product" ></span></td>
						  			<td><span id="productname" name="productname"></span></td>
						  			<td><span id="makerid" name="makerid"></span></td>
						  			<td><input type="button"  class="btn btn-success"  name="modify" id="modify" value="Modify" onClick="queryUser()" ></input></td>
						  			
							  </tr>
							   
						  </tbody>
						</table>
		 
		</fieldset>
	 	  </div>
	  </div>
	  </div>
		<div class="form-actions">
				<input type="button"  class="btn btn-success"  name="save" id="save" value="View" onClick="getdata()"></input>
				<input type="button"  class="btn btn-success"  name="add" id="add" value="Add" onClick="queryUser()" style="display:none"></input>
				<span id="errorm" name="errorm" class="errors" ></span>
		</div>
	</div>
 </form>
</body>
</html>