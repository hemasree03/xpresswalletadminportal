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
<link href="${pageContext.request.contextPath}/css/body.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/lightbox.css"
	type="text/css" />

<script src="${pageContext.request.contextPath}/js/datafetchfillinng.js"></script>

<script type="text/javascript"> 

var rules = {
		   rules : {
			   termilstatus : { required : true}
			   
		   },  
		   messages : {
			   termilstatus : { 
			   		required : "Please Select Status."
				}
				
		   } 
		 };

$('#btn-back').live('click',function() {

	$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/superagent.action";
	$("#form1").submit();


});




$('#btn-submit').live('click', function () {
	

	
		var url="${pageContext.request.contextPath}/<%=appName %>/superagentproductcnfrm.action";
		$("#form1")[0].action=url;
		$("#form1").submit();


});


var finaljson; 

$(document).ready(function () {
	
	
	$('#product1').on('change', function() {
	  $('#desc').text((this.value).split("-")[1]);
	  $('#product').val((this.value).split("-")[1]);
	  $('#prodesc').val((this.value).split("-")[1]);
	  
	  
	 
	  
	  
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
		    
		    $( "#superagentname" ).keyup(function() {
				
				$( "#superagentname" ).val((this.value).toUpperCase());
			});	
		    
 			$( "#CBNAgentId" ).keyup(function() {
				
				$( "#CBNAgentId" ).val((this.value).toUpperCase());
			});	
	
});


  
 
var branchdata="CBNAgentId|text#superagentname|text#product1|text#prodesc|text#"; //branchaccno|text#

var branchdatajsonObj = [];

function addrow()
{
 
		//$("#form1").validate(datavalidation);
		
		console.log("Addrow >>>> ");
		
		if($("#form1").valid()) { 
		
			var myval =  buildSingleRequestjson(branchdata);
			var finalobj = jQuery.parseJSON(myval);
			
			 var status = validateData(finalobj,branchdatajsonObj,1); 
			 console.log("finalobj ---> ["+finalobj+"] myval ["+myval+"]" );
			
			// var status2 = validateData(finalobj,finaljson,2); 
			
			
			// console.log("status  ["+status+"] status2 ["+status2+"]" );
		
			var queryString = "entity=${loginEntity}&method=validatecluster&transaction="+finalobj.CBNAgentId;
	
	$.getJSON("postJson.action", queryString,function(data) { 
		
		if(data.finalCount==0){	
				if(status){
					
						$("#errormsg").html("<font colour='red'>Allready Exists1</font>");
					
					}else { 
						
						branchdatajsonObj.push(finalobj);
						$("#errormsg").empty();
						makeempty();
						
							
					 } 
			/*  branchdatajsonObj.push(finalobj);
				$("#errormsg").empty();
				makeempty(); */
			 
			var totval = JSON.stringify(branchdatajsonObj);	
			console.log("Final Json Object ["+totval+"]");
		
			
			buildfeetable(branchdatajsonObj);
			
			$("#submitdata").show();
		
			return true;
		}else{
			$("#errormsg").html("<font colour='red'>Allready Exists2</font>");
		}
	});
	
		}else{
		
			return false;
		
		}
		
 
 }
 
 

function validateData(myval,myfinalobj,val)
{

	var status = false;
	
	var CBNAgentId = myval.CBNAgentId;
	var superagentname = myval.superagentname;
	
	var product1 = myval.product1;
	var prodesc = myval.prodesc;
	

	
	
	
	
	
	
			$.each(myfinalobj, function(index,jsonObject)
					{ 
							var tabCBNAgentId = jsonObject.CBNAgentId;
							var tabsuperagentname = jsonObject.superagentname;
							var tabproduct1  = jsonObject.product1;
							var tabprodesc =jsonObect.prodesc;
							
							
							if((CBNAgentId==tabCBNAgentId))
							{
								status=true;
							}
							
							

					});
		
	
	return status;

}

function makeempty()
{
	
	$("#product1").val("");
	
		
} 
 
function buildfeetable(jsonArray)
{

	$("#tbody_data").empty();
	var i=0;
	var htmlString="";
	
	$.each(jsonArray, function(index,jsonObject){
		
			var data = JSON.stringify(jsonObject);
			console.log("Data ["+data+"]");
			
			
			++i;
			htmlString = htmlString + "<tr class='values' id="+i+">";
			htmlString = htmlString + "<td id=sno name=Transaction >" + i + "</td>";
			htmlString = htmlString + "<td id='product1' name=product1 >" + jsonObject.product1 + "</td>";
			htmlString = htmlString + "<td id='prodesc' name=prodesc >" + jsonObject.prodesc+ "</td>";
			
			htmlString = htmlString + "<td><a class='btn btn-warning' href='#' id='modify'   title='Modify' data-rel='tooltip'  onclick='upatedata("+data+")' >"+ 
				"<i class='icon icon-edit icon-white'></i></a>&nbsp;"/* +
				"<a id='remove' class='btn btn-info'  href='#'   title='Remove' data-rel='tooltip' ><i class='icon-remove'  onclick='deletedata("+data+")' ></i></a>&nbsp</td>";
			 */
			htmlString = htmlString + "</tr>";

	});
	
	console.log("Final HtmlString ["+htmlString+"]");
	
	if(htmlString=='')
		$("#submitdata").hide();
	
	$("#tbody_data").append(htmlString);

}


function subitReq()
{   

		var totval = JSON.stringify(branchdatajsonObj);	 
			
			
		var myEscapedJSONString = totval.replace(/\\n/g, " ")
                                      .replace(/\\'/g, " ")
                                      .replace(/\\"/g, ' ')
                                      .replace(/\\&/g, " ")
                                      .replace(/\\r/g, " ")
                                      .replace(/\\t/g, " ")
                                      .replace(/\\b/g, " ")
                                      .replace(/\\f/g, " ");
									  
		console.log("After Final Json Object ["+totval+"]");

		console.log("myEscapedJSONString ["+myEscapedJSONString+"]");
		
		totval = myEscapedJSONString;
		//alert($("#Product").val());
		//alert(totval);
		$("#finaljsonarray").val(totval);
		
		
		$("#form2")[0].action='<%=request.getContextPath()%>/<%=appName %>/superagentproductcnfrm.action';
		$("#form2").submit();
		return true; 
			
} 


var dataupdateval;

function upatedata(myval)
{

	$("#ADD").hide();
	$("#UPDATE").show();
	
	console.log("Val ["+myval.CBNAgentId+"] ");
	console.log("Val ["+myval.superagentname+"] ");
	
	
	/* var totval = JSON.stringify(val);	
			console.log("Final Json Object ["+totval+"]"); */
	//alert(totval);
			
	//console.log(listid);
//	$('#CBNAgentId').val(myval.CBNAgentId);
//	$('#superagentname').val(myval.superagentname);
	$('#product1').val(myval.product1);
	$("#prodesc").val(myval.prodesc);
	dataupdateval=myval; 
	
}

function adddeletedata(val)
{

	console.log("Val ["+val+"]");
	fillsingledata(branchdata,val);
	dataupdateval=val;
	viewdata(val);
	
	// $("#Remove").show();
	
}

function deletedata(val){

	var finaljsonobj=[];

	var r = confirm("Do You Want To Remove?");
	
	if (r == true) {

	console.log("Final Value ["+val+"]");

	dataupdateval=val;
	
	var superagentname = dataupdateval.CBNAgentId;
	var CBNAgentId  = dataupdateval.superagentname;

	
	
	$.each(branchdatajsonObj, function(index,jsonObject){ 
		
		 	var tabaCBNAgentId = jsonObject.CBNAgentId
			var tabsuperagentname  = jsonObject.superagentname;
		
			
			console.log("tabCBNAgentId ["+tabCBNAgentId+"] tabsuperagentname ["+tabsuperagentname+"]");
		
			if((tabCBNAgentId==CBNAgentId) &&(tabsuperagentname==superagentname) )
			{
				
			}else{
				
				finaljsonobj.push(jsonObject);
			}
			

	});
	
	branchdatajsonObj =[];
	branchdatajsonObj = finaljsonobj;
	
	// acqjsonObj.push(finalobj);
	
	buildfeetable(branchdatajsonObj);
		
	} 

	makeempty();	

}

function updaterow()
{
//	$("#form1").validate(datavalidation);
	if($("#form1").valid()) { 
	checkEqual('U');
	makeempty();
	
	$("#ADD").show();
	$("#UPDATE").hide();
	}
	
}


function checkEqual(val)
{

	var finaljsonobj=[];

	var myval =  buildSingleRequestjson(branchdata); 
	var newupdateddataobj = jQuery.parseJSON(myval);
	
	var CBNAgentId = dataupdateval.CBNAgentId;
	var superagentname  = dataupdateval.superagentname;
	
	
	$.each(branchdatajsonObj, function(index,jsonObject)
	{ 
		 	// alert(jsonObject.Services);
		
		 	var tabCBNAgentId = jsonObject.CBNAgentId;
			var tabsuperagentname  = jsonObject.superagentname;
			
			// && (branchaddress==tabbranchaddress)
			
			 if((CBNAgentId==tabCBNAgentId) && (superagentname==tabsuperagentname))
			{ 
				if(val=='U'){
					finaljsonobj.push(newupdateddataobj);
					
				}
				
			}else{
			
				finaljsonobj.push(jsonObject);
			
			}
			

	});
	
	branchdatajsonObj =[];
	branchdatajsonObj = finaljsonobj;
	
	//acqjsonObj.push(finalobj);
	
	buildfeetable(branchdatajsonObj);


}



function viewdata(val){

	console.log("Val ["+val+"]");

	//var jsondata = jQuery.parseJSON(val);
	
	 var viewdata="branchcode|text#branchname|text#branchaddress|text#branchaccno|text#";
				
	fillsingledata(viewdata,val);
	$("#Remove").hide();
	
	//lightbox_open();
				 
}


var queryString = "";





// $('#telco').on('change', function() {	

	 $.getJSON("superproductajx.action", queryString, function(data) {
			if(data.region!=""){
				//alert($('#telco').val());
			//	alert(data.region);
				var mydata=data.region;
 			//var mydata=(data.region).substring(5,(data.region).length);
 			document.getElementById("product1").length=1;
 			var mydata1=mydata.split(":");
 
  			$.each(mydata1, function(i, v) {
  				//if((mydata.split(":")[i]).split("@")[2]==$('#telco').val()){
  				var options = $('<option/>', {value: (mydata.split(":")[i]).split("@")[0], text: (mydata.split(":")[i]).split("@")[0]}).attr('data-id',i);
  				//}
  				$('#product1').append(options);
  			
  				$('#product1').trigger("liszt:updated");
  			});
  			
  			$("#product1").val("${responseJSON.PRODUCT}");
			$('#product1').trigger("liszt:updated");
 		} 
	}); 
	 
	
</script>
<s:set value="responseJSON" var="respData" />
</head>

<body>


								<form name="form1" id="form1" method="post" action="">
			<div id="content" class="span10">
			    <div>
					<ul class="breadcrumb">
					  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
					  <li> <a href="#">Super Agent </a> <span class="divider"> &gt;&gt; </span></li>
					  <li><a href="#"> Super Agent View</a></li>
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
							<i class="icon-edit"></i>Super Agent Creation
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
						</div>
					</div>
					<div class="box-content" id="primaryDetails">
						<fieldset>
						<table width="950" border="0" cellpadding="5" cellspacing="1"
									class="table table-striped table-bordered bootstrap-datatable " id="bank-details">
									
									 <tr class="odd">
										   		<td><label for="Telco Type"><strong>Super Agent Name<font color="red">*</font></label></td>
												<td>${responseJSON.SUPERAGENT_NAME}
												<input type="hidden" name="superagentname"  id="superagentname"  size="20" maxlength="20" 
                                                           style="text-transform:uppercase" value="${responseJSON.SUPERAGENT_NAME}" >
												
													
												
													</td>
												<td width="25%"><label for="Account Currency Code"><strong>Super Agent Id<font color="red">*</font></label></td>
										<td width="25%">${responseJSON.AGENTID}
										<input type="hidden" name="CBNAgentId" id="CBNAgentId" value="${responseJSON.AGENTID}" />
											 	
										   </tr>
									<tr class="even">
									<td width="25%"><strong><label for="Super Agent Name"><strong>Business Owner Name<font color="red">*</font></strong></label></strong></td>
									<td width="25%">${responseJSON.B_O_NAME}
									<input type="hidden" id="accountName" name="agent.accountName"value="${responseJSON.B_O_NAME}" />
								</td>
								<td><label for="To Date"><strong>Date Of Birth<font color="red">*</font> </strong></label></td>
									<td>${responseJSON.DOB}<input type="hidden" name="agent.dob" id="dob" value="${responseJSON.DOB}" />
									</td>
								
								
								
					                        
								</tr>
									<tr class="even">
											
										  	    <td ><label for="Gender"><strong>Gender<font color="red">*</font></label>	</td>
												<td>${responseJSON.GENDER}<input type="hidden"  id="gender" name="agent.gender" value="${responseJSON.GENDER}" />
												
												</td>
												<td><strong><label for="Email"><strong>Email<font color="red">*</font></strong></label></strong></td>
										<td>${responseJSON.EMAIL}
										<input type="hidden"  name="agent.email" id="email" value='${responseJSON.EMAIL}'/>
										   </tr>
									<tr class="odd">
										
										<td ><strong><label for="Mobile"><strong>Mobile<font color="red">*</font></strong></label></strong></td>
										<td>${responseJSON.MOBILE}
										<input type="hidden" name="agent.mobile"  maxlength="13" id="mobile" value='${responseJSON.MOBILE}'/>
										</td>
												 <td width="25%"><label for="Nationality"><strong>Nationality<font color="red">*</font></strong></label></td>
												<td width="25%">${responseJSON.NATIONALITY}
												<input type="hidden" name="agent.nationality" id="nationality" value='${responseJSON.NATIONALITY}'/>
	
												</td>
									</tr>
								
								</table>
						</fieldset>
						</div>
					</div>
				</div>

</div>
						<div class="row-fluid sortable">
						<div class="box span10">
								<div class="box-header well" data-original-title>
										<i class="icon-edit"></i>Product Details
									<div class="box-icon">
										<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
										<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
									</div>
								</div>

							<div class="box-content" id="primaryDetails">
						<fieldset>
						<table width="950" border="0" cellpadding="5" cellspacing="1"
									class="table table-striped table-bordered bootstrap-datatable " id="bank-details">
									
									   	
									
										      <tr class="even">
												<td width="25%"><label for="Product"><strong>Product</strong><font color="red">*</font></label></td>
												<td width="25%">
												<select id="product1" name="product1" class="chosen-select-width" >
												 <option value="">Select</option>
												</select>
												</td>
												 <td width="25%"><label for="Description"><strong>Description</strong></label></td>
										       <td width="25%"><div id="desc" ></div>
										       <input type="hidden"  name="product" id="product"  value="" />
												<input type="hidden"  name="prodesc" id="prodesc"  value="" />
										       </td>  
											</tr> 
											</table>
								</fieldset>
							</div>
						</div>
				</div>
								


				<div class="box-content">

					<input type="hidden" id="reqjson" name="reqjson" /> <input
						type="button" id="ADD" class="btn btn-success" onclick="addrow();"
						value="Add" /> &nbsp;&nbsp; <input type="button" id="UPDATE"
						class="btn btn-success" onclick="updaterow();" value="Update"
						style="display: none" /> <br /> <br />



					<table width="84%"
						class="table table-striped table-bordered bootstrap-datatable"
						id="acqdetails" style="width: 84%;">
						<thead>
							<tr>
								<th width="5%">Sno</th>
								<th width="20%">Product</th>
								<th width="20%">Description</th>
								<th width="20%">Action</th>
							</tr>
						</thead>
						<tbody id="tbody_data">
						</tbody>

					</table>



				</div>


				<form name="form2" id="form2" method="post" action="">
					<div class="form-actions" id="submitdata" style="display: none">

								<a href="#" id="btn-back" class="btn btn-danger ajax-link">Home
								</a>&nbsp; <a href="#" id="btn-submit"
									class="btn btn-success ajax-link">&nbsp;Submit</a> <span
									id="error_dlno" class="errors"></span>
							</div>


				</form>

			</div>

		</div>





	</div>
	<script type="text/javascript"
		src='${pageContext.request.contextPath}/js/jquery.dataTables.min.js'></script>
	<script type="text/javascript"
		src='${pageContext.request.contextPath}/js/datatable-add-scr.js'></script>

</body>
</html>
