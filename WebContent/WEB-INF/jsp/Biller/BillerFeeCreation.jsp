
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
			
			htmlString = htmlString + "<td id=CAT_ID"+ i +" name=CAT_ID"+ i +" style='display:none' >" + jsonObject.CAT_ID + "</td>";
			htmlString = htmlString + "<td id=BILLER_ID"+ i +" name=BILLER_ID"+ i +"  style='display:none' >" + jsonObject.BILLER_ID + "</td>";
			
			htmlString = htmlString + "<td><input type='text' name='Xpressv"+ i +"' id='Xpressv"+ i +"' value='0' style='width:50px' /><select name='XpressFlatPercentile"+ i +"' id='XpressFlatPercentile"+ i +"'  Class ='chosen-select' data-placeholder='Choose Flat/Percentile...' style='width:50px' ><option value='F' >F</option><option value='P'>P</option></select></td>";
			htmlString = htmlString + "<td><input type='text' name='Agentv"+ i +"' id='Agentv"+ i +"' value='0' style='width:50px'/><select name='AgentFlatPercentile"+ i +"' id='AgentFlatPercentile"+ i +"'  Class ='chosen-select' data-placeholder='Choose Flat/Percentile...' style='width:50px' ><option value='F' >F</option><option value='P'>P</option></select></td>";
			htmlString = htmlString + "<td><input type='text' name='Superv"+ i +"' id='Superv"+ i +"' value='0' style='width:50px'/><select name='SuperFlatPercentile"+ i +"' id='SuperFlatPercentile"+ i +"'  Class ='chosen-select' data-placeholder='Choose Flat/Percentile...' style='width:50px' ><option value='F' >F</option><option value='P'>P</option></select></td>";
			htmlString = htmlString + "<td><input type='text' name='Cevav"+ i +"' id='Cevav"+ i +"' value='0' style='width:50px'/><select name='CevaFlatPercentile"+ i +"' id='CevaFlatPercentile"+ i +"'  Class ='chosen-select' data-placeholder='Choose Flat/Percentile...' style='width:50px' ><option value='F' >F</option><option value='P'>P</option></select></td>";
			htmlString = htmlString + "<td><input type='text' name='TPv"+ i +"' id='TPv"+ i +"' value='0' style='width:50px'/><select name='TPFlatPercentile"+ i +"' id='TPFlatPercentile"+ i +"'  Class ='chosen-select' data-placeholder='Choose Flat/Percentile...' style='width:50px' ><option value='F' >F</option><option value='P'>P</option></select></td>";
			htmlString = htmlString + "<td><input type='text' name='vatv"+ i +"' id='vatv"+ i +"' value='0' style='width:50px'/><select name='vatFlatPercentile"+ i +"' id='vatFlatPercentile"+ i +"'  Class ='chosen-select' data-placeholder='Choose Flat/Percentile...' style='width:50px' ><option value='F' >F</option><option value='P'>P</option></select></td>";
			htmlString = htmlString + "<td><input type='radio' name='feecharge"+ i + "' value='Customer' checked/><strong>Customer</strong><br>"+
			"<input type='radio' name='feecharge"+ i + "'  value='Agent' /><strong>Agent</strong><br>"+
			"<input type='radio' name='feecharge"+ i + "'  value='Rebate' /><strong>Rebate</strong></td>";
	 
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

function feecreation(){
	//alert(i);
	var v="";
	for(j=1;j<=i;j++){
		v=v+$("#CAT_NAME"+j).text()+"@"+$("#BILLER_NAME"+j).text()+"@"+$("#CAT_ID"+j).text()+"@"+$("#BILLER_ID"+j).text()+"@"+$("#Xpressv"+j).val()+"-"+$("#XpressFlatPercentile"+j).val()+"@"+$("#Agentv"+j).val()+"-"+$("#AgentFlatPercentile"+j).val()+"@"+$("#Superv"+j).val()+"-"+$("#SuperFlatPercentile"+j).val()+"@"+$("#Cevav"+j).val()+"-"+$("#CevaFlatPercentile"+j).val()+"@"+$("#TPv"+j).val()+"-"+$("#TPFlatPercentile"+j).val()+"@"+$("#vatv"+j).val()+"-"+$("#vatFlatPercentile"+j).val()+"@"+$('input[name=feecharge'+j+']:checked', '#form1').val()+"#";
	}
	//alert(v);
	$("#vdet").val(v);
	
	$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName%>/billerfeecreationconfirm.action';
	$("#form1").submit();
	return true;
	
}

$(document).ready(function(){
	
	$('input[type=text]').on("keypress", function (e) {
		  
	    if (e.which != 8 && e.which != 0 && ((e.which < 48 || e.which > 57) && e.which != 46) ) {
	        e.preventDefault();
	    }
	});	
	
	
	
});

</script>

</head>

<body>
	<form name="form1" id="form1" method="post" action="">

		<div id="content" class="span10">

			<div>
				<ul class="breadcrumb">
					<li><a href="home.action">Home</a> <span class="divider">
							&gt;&gt; </span></li>
					<li><a href="#">Biller Fee Creation</a></li>
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
				&nbsp;<a class="btn btn-success" href="#"
					onClick="feecreation()">Submit</a>
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
