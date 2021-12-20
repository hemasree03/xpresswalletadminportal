
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



function buildtable(jsonArray,divid)
{
	
	$('#'+divid).empty();
	var i=0;
	var htmlString="";
	
	$.each(jsonArray, function(index,jsonObject){
	
			++i;
			htmlString = htmlString + "<tr class='values' id="+i+">";
			htmlString = htmlString + "<td id=sno name=sno >" + i + "</td>";
			
			htmlString = htmlString + "<td id=CATGRP_CODE name=CATGRP_CODE >" + jsonObject.CATGRP_CODE + "</td>";
			htmlString = htmlString + "<td id=CATGRP_NAME name=CATGRP_NAME >" + jsonObject.CATGRP_NAME + "</td>";
			htmlString = htmlString + "<td id=MAKER_ID name=MAKER_ID >" + jsonObject.MAKER_ID + "</td>";
		
			 if(jsonObject.STATUS=='A'){
					htmlString = htmlString + "<td id=STATUS name=STATUS ><div class='label label-success' >Active</div></td>";	
				}
			 else{
					
					htmlString = htmlString + "<td id=STATUS name=STATUS ><div class='label label-important' >Deactive</div></td>";	
				}
			
/* htmlString = htmlString + "<td id='' ><a class='btn btn-warning' href='#' id='limitmodify' index="+i+" val="+i+" title='View User' data-rel='tooltip' > <i class='icon icon-book icon-white'></i></a></td>";
  */
  
	/* htmlString = htmlString + "&nbsp&nbsp<a class='btn btn-success' href='#' id='limitview' index="+i+" val="+i+" title='View' data-rel='tooltip' '> <i class='icon icon-book icon-white'></i></a>";
	*/ if(jsonObject.FEESTATUS=='0')
		 {
		htmlString = htmlString + "<td id='' ><a class='btn btn-warning' href='#' id='addfee' index="+i+" val="+i+" title='Add fee' data-rel='tooltip' '> <i class='icon icon-plus icon-white'></i></a>";
		 }else{
		htmlString = htmlString + "<td id='' ><a class='btn btn-warning' href='#' id='feeview' index="+i+" val="+i+" title='View' data-rel='tooltip' '> <i class='icon icon-book icon-white'></i></a>";

	//htmlString = htmlString + "&nbsp&nbsp<a class='btn btn-warning' href='#' id='limitmodify' index="+i+" val="+i+" title='Modify' data-rel='tooltip' '> <i class='icon icon-edit icon-white'></i></a>";
		 } 
	 
  htmlString = htmlString + "</td></tr>";
	
	});
	
	console.log("Final HtmlString ["+htmlString+"]");
	
	$('#'+divid).append(htmlString);

}

 function createLimitData(myaction){
	 $('#linkmode').val("NEW");
	    $("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/'+myaction+'.action';
		$("#form1").submit();
		return true;
 }
 

$(document).on('click','a',function(event) {
	
    var $row = jQuery(this).closest('tr');
    var $columns = $row.find('td');

    $columns.addClass('row-highlight');
    var values = "";
     var btn=this.id;
    
    jQuery.each($columns, function(i, item) {
    	
    	if(i==0)
    		{
    		values =  item.innerHTML;
    		}else{
    			values = values +"$"+ item.innerHTML;
    		}
    });
   
  	
	var val = values;
	var code = "";
	var codedesc = "";
	var status = "";
	if(val.match("$"))
		{
		
		var x = val.split("$");
		code = x[1];
		
		}

	if(btn == 'addfee')
	{
		
		//alert(code);
		
		$('#catids').val(code); 
		$('#type').val("addfee"); 
		
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/billerfeecreation.action';
		$("#form1").submit();
			return true; 
	}
	
	if(btn == 'feeview')
	{
		
		//alert(code);
		
		$('#catids').val(code); 
		$('#type').val("feeview"); 
		
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/billerfeecreationview.action';
		$("#form1").submit();
			return true; 
	}
	

	
	
	
    
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
					<li><a href="#">Biller Fee Management</a></li>
				</ul>
			</div>

			<!-- <div class="box-content" id="top-layer-anchor">
				<div>
					<a href="#" class="btn btn-success" id="createProduct"
						title='Biller Fee Creation' data-rel='popover'
						data-content='Biller Fee Creation'
						onClick="createLimitData('billerfeecreation')"><i
						class='icon icon-plus icon-white'></i>&nbsp;Biller Fee Creation</a> &nbsp;
				</div>
			</div> -->


			<div class="row-fluid sortable">
				<!--/span-->
				<div class="row-fluid sortable">
					<div class="box span12" id="groupInfo">
						<div class="box-header well" data-original-title>
							Biller Fee Creation Information
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a> <a href="#"
									class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content">
							<table width='100%'
								class="table table-striped table-bordered bootstrap-datatable datatable"
								id="DataTables_Table_0">
								<thead>
									<tr>
										<th>S No</th>
										<th>Category Id</th>
										<th>Category Name</th>
										<th>Maker ID</th>
										<th>status</th>
										<th>Action</th>
										
									</tr>
								</thead>
								<tbody id="LMT_TBody">

								</tbody>
							</table>
						</div>
					</div>
				</div>




				
				<input type="hidden" id="catids" name="catids" /> 
				
					<input type="hidden" id="type" name="type" />

				


			</div>
		</div>
</form>

	<script type="text/javascript"
		src='${pageContext.request.contextPath}/js/jquery.dataTables.min.js'></script>
	<script type="text/javascript"
		src='${pageContext.request.contextPath}/js/datatable-add-scr.min.js'></script>
</body>
</html>
