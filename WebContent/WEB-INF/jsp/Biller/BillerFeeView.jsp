
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>CEVA</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="">
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%String ctxstr = request.getContextPath(); %>
<% String appName= session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>


<style type="text/css">
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

label.error {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}

.errmsg {
	color: red;
}
</style>
<script type="text/javascript">
binDetails = "";
var prdIndex = new Array();

$(document).ready(function() {
	
	var lmtjson =  '${responseJSON.VIEW_LMT_DATA}';
	var feejson =  '${responseJSON.VIEW_FEE_DATA}';
	
	console.log("Welcome to pro");
	
	var finaljsonobj = jQuery.parseJSON(lmtjson);
	var feefinaljsonobj = jQuery.parseJSON(feejson);
	
	// console.log("Limit Json ["+finaljsonobj+"]");
	 // console.log("Fee Json ["+feefinaljsonobj+"]");
	
	buildtable(finaljsonobj,'LMT_TBody');
	//buildtable(feefinaljsonobj,'FEE_TBody');
	

});

var i=0;

function buildtable(jsonArray,divid)
{
	
	$('#'+divid).empty();
	
	var htmlString="";
	
	$.each(jsonArray, function(index,jsonObject){
	
			++i;
			htmlString = htmlString + "<tr class='values' id="+i+">";
			htmlString = htmlString + "<td id=sno name=sno >" + i + "</td>";
			
			htmlString = htmlString + "<td id=CAT_NAME"+ i +" name=CAT_NAME"+ i +" >" + jsonObject.CAT_NAME + "</td>";
			htmlString = htmlString + "<td id=BILLER_NAME"+ i +" name=BILLER_NAME"+ i +" >" + jsonObject.BILLER_NAME + "</td>";
			
			htmlString = htmlString + "<td id=BANK"+ i +" name=BANK"+ i +"  >" + jsonObject.BANK + "-" + jsonObject.BANK_FEE_TYPE + "</td>";
			
			htmlString = htmlString + "<td id=AGENT"+ i +" name=AGENT"+ i +"  >" + jsonObject.AGENT + "-" + jsonObject.AGENT_FEE_TYPE + "</td>";
			
			
			htmlString = htmlString + "<td id=SUPERAGENT"+ i +" name=SUPERAGENT"+ i +"  >" + jsonObject.SUPERAGENT + "-" + jsonObject.SUPERAGENT_FEE_TYPE + "</td>";
			
			htmlString = htmlString + "<td id=CEVA"+ i +" name=CEVA"+ i +"  >" + jsonObject.CEVA + "-" + jsonObject.CEVA_FEE_TYPE + "</td>";
			
			htmlString = htmlString + "<td id=THIRD_PARTY"+ i +" name=THIRD_PARTY"+ i +"  >" + jsonObject.THIRD_PARTY + "-" + jsonObject.TR_FEE_TYPE + "</td>";
			
			htmlString = htmlString + "<td id=VAT"+ i +" name=VAT"+ i +"  >" + jsonObject.VAT + "-" + jsonObject.VAT_FEE_TYPE + "</td>";
			
			htmlString = htmlString + "<td id=FEE_CHARGE"+ i +" name=FEE_CHARGE"+ i +"   >" + jsonObject.FEE_CHARGE + "</td>";
			
			
	 
  htmlString = htmlString + "</tr>";
	
	});
	
	console.log("Final HtmlString ["+htmlString+"]");
	
	$('#'+divid).append(htmlString);

}


function getProductScreen(){
	$("#form1").validate().cancelSubmit = true;
	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName%>/billerfeemanagement.action';
	$("#form1").submit();
	return true;
}



 
</script>

</head>

<body>
	<form name="form1" id="form1" method="post" action="">

		<div id="content" class="span10">

			<div>
				<ul class="breadcrumb">
					<li><a href="home.action">Home</a> <span class="divider">
							&gt;&gt; </span></li>
					<li><a href="#">Biller Fee View</a></li>
				</ul>
			</div>

			<div class="box-content" id="top-layer-anchor">
				
			</div>

<div class="row-fluid sortable">

			<div class="box span12">

				<div class="box-header well" data-original-title>
						<i class="icon-edit"></i>biller Category Details
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
						<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>

					</div>
				</div>
						
				<div class="box-content">
					<fieldset> 
						<table width="950" border="0" cellpadding="5" cellspacing="1" 
									class="table table-striped table-bordered bootstrap-datatable "> 
								
								<tr>
									<td width="15%"><strong><label for="Group Type"><strong>Biller Category Id</strong></strong></td>
									<td width="35%" >${responseJSON.catids}
									<input type="hidden" id="catids" name="catids" value="${responseJSON.catids}"/> </td>
									<td width="25%"></td>
									<td width="25%" ></td> 
									
								</tr>
						</table>  
					</fieldset>   
				</div>
			</div>
		</div>

			<div class="row-fluid sortable">
				<!--/span-->
				<div class="row-fluid sortable">
					<div class="box span12" id="groupInfo">
						
						<div class="box-content">
							<table width='100%'
								class="table"
								id="DataTables_Table_0">
								<thead>
									<tr>
										<th>S No</th>
										<th>Category Name</th>
										<th>Biller Name</th>
										<th>Xpress</th>
										<th>Agent</th>
										<th>Super Agent</th>
										<th>Ceva</th>
										<th>Third Party</th>
										<th>VAT</th>
										<th>Fee Charge to</th>
										
										
									</tr>
								</thead>
								<tbody id="LMT_TBody">

								</tbody>
							</table>
						</div>
					</div>
				</div>


<input type="hidden" name="vdet" id="vdet" />

				<div class="form-actions">
				<a class="btn btn-danger" href="#" onClick="getProductScreen()">Back</a>
				
			</div>

				


			</div>
		</div>
</form>

	<script type="text/javascript"
		src='${pageContext.request.contextPath}/js/jquery.dataTables.min.js'></script>
	<script type="text/javascript"
		src='${pageContext.request.contextPath}/js/datatable-add-scr.min.js'></script>
</body>
</html>
