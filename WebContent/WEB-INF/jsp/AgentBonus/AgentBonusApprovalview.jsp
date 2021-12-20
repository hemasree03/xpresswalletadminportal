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
<%String appName = session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>
<s:head />
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


<script type="text/javascript" >



var userLinkData ='${USER_LINKS}';
var jsonLinks = jQuery.parseJSON(userLinkData);
var linkIndex = new Array();
var linkaction = new Array();
var linkStatus = new Array();

var num="";

$(document).ready(function () { 
	
	
	
	$.each(jsonLinks, function(index, v) {
	linkIndex[index] = index;
	linkaction[index] = v.name;
	linkStatus[index] = v.status;
	
	$("#"+v.name+"1").removeAttr("disabled");
	$("#"+v.name+"1").attr("id", v.name);
	
	num=index;
	

	
});  
}); 



var val = 1;
var rowindex = 0;
var colindex = 0;
var bankAcctFinalData="";
var binstatus = "";
var v_message = "";


$(document).ready(function() {
	
	var lmtjson =  '${responseJSON.VIEW_AGNT_DATA}';
	
	console.log("Welcome to pro");
	
	var finaljsonobj = jQuery.parseJSON(lmtjson);
		
	buildtable(finaljsonobj,'LMT_TBody');
	
	
});



function buildtable(jsonArray,divid)
{
	
	$('#'+divid).empty();
	var i=0;
	
	var htmlString="";
	$.each(jsonArray, function(index,jsonObject){
		
			++i;
			htmlString = htmlString + "<tr class='values' id="+i+">";
			htmlString = htmlString + "<td id=REFERENCE_NO name=REFERENCE_NO >" + jsonObject.REFERENCE_NO + "</td>";
			htmlString = htmlString + "<td id=FULLNAME name=FULLNAME >" + jsonObject.FULLNAME + "</td>";
			htmlString = htmlString + "<td id=MOBILE_NUMBER name=MOBILE_NUMBER >" + jsonObject.MOBILE_NUMBER + "</td>";
			htmlString = htmlString + "<td id=W_ACCOUNT_NO name=W_ACCOUNT_NO >" + jsonObject.W_ACCOUNT_NO + "</td>";
			htmlString = htmlString + "<td id=BONUS_AMT name=BONUS_AMT >" + jsonObject.BONUS_AMT + "</td>";
			htmlString = htmlString + "<td id=MAKER_ID name=MAKER_ID >" + jsonObject.MAKER_ID + "</td>";
			htmlString = htmlString + "<td id=MAKER_DT name=MAKER_DT >" + jsonObject.MAKER_DT + "</td>";
			
				htmlString = htmlString + "<td id='' ><a class='btn btn-success' href='#' id='agentview' index="+i+" val="+i+" title='View Details' data-rel='tooltip'  > <i class='icon icon-book icon-white'></i></a>";
				
			htmlString = htmlString + "</td></tr>";
			
			
			 
	});
	
	console.log("Final HtmlString ["+htmlString+"]");
	$('#'+divid).append(htmlString);
	

	/* for(ii=0;ii<=num;ii++){
		for(j=1;j<=i;j++){
			//alert(linkaction[ii]);
			var v=linkaction[ii]+"_"+(j);
			$('#'+v).removeAttr("disabled");
			$('#'+v).attr("id", linkaction[ii]);
		}
		
		 
	 }   */
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
	if(val.match("$"))
		{
		var x = val.split("$");
		code = x[0];
		
		}

	if(btn == 'agentview')
	{
		
		 $('#accNumbers').val(code);
		$('#cmd').val('VIEW');
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/agentbonusapprovalView.action';
		$("#form1").submit();
			return true;  
	}
	
    
}); 
		 


</script>

</head>
<body>
	<form name="form1" id="form1" method="post" action="">
		
	<div class="span10" id="list" >
		<div>
			<ul class="breadcrumb">
			  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
			  <li> <a href="superagent.action">Agent Bonus Approval</a> <span class="divider"> &gt;&gt; </span></li>
			</ul>
		</div>
		<div class="row-fluid sortable"><!--/span-->
		
		<!-- <a href="#" class="btn btn-success" id="super-agent-creation1"    title='Approved Agent Bonus' data-rel='popover'  data-content='Approved Agent Bonus'  ><i class='icon icon-plus icon-white'></i>&nbsp;Approved Agent Bonus</a> &nbsp;					
		 -->	<div class="row-fluid sortable">
				
				
				<div class="box span12">
					<div class="box-header well" data-original-title>
						Agent Bonus Details
					</div>
				
				<div class="box-content">
					<fieldset>
						<table width="100%" class="table table-striped table-bordered bootstrap-datatable datatable" id="products"  >
							  <thead>
							<tr>
								<th>Reference No</th>
								<th>Full Name</th>
								<th>Mobile Number</th>
								<th>Wallet Number</th>
								<th>Bonus</th>
							    <th>Maker Id </th> 
							    <th>Maker Date </th>  
								<th>Actions</th>
							</tr>
						</thead> 
						 <tbody id="LMT_TBody">
							   
						</tbody>  
						</table>
					</fieldset>
					
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" name="accNumbers" id="accNumbers">
	
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

<script type="text/javascript" src='${pageContext.request.contextPath}/js/jquery.dataTables.min.js'></script> 
<script type="text/javascript" src='${pageContext.request.contextPath}/js/datatable-add-scr.js'></script> 
</html>
