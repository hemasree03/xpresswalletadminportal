 <!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>CEVA </title>
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

.errors{
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
<script type="text/javascript" >
binDetails = "";
var prdIndex = new Array();

$(document).ready(function() {
	
	var lmtjson =  '${responseJSON.VIEW_LMT_DATA}';
	
	console.log("Welcome to pro");
	
	var finaljsonobj = jQuery.parseJSON(lmtjson);
	
	// console.log("Limit Json ["+finaljsonobj+"]");
	 // console.log("Fee Json ["+feefinaljsonobj+"]");
	
	buildtable(finaljsonobj,'LMT_TBody');
	//buildtable(feefinaljsonobj,'FEE_TBody');
	
	
	$('#btn-back').live('click',function() {

		$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/superagentsapproval.action";
		$("#form1").submit();


	});
	

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
			htmlString = htmlString + "<td id=ID name=ID >" + jsonObject.ID + "</td>";
			 htmlString = htmlString + "<td id=SUPERAGENT_NAME name=SUPERAGENT_NAME >" + jsonObject.SUPERAGENT_NAME + "</td>";
			htmlString = htmlString + "<td id=MOBILE_NUMBER name=MOBILE_NUMBER >" + jsonObject.MOBILE_NUMBER + "</td>";
			htmlString = htmlString + "<td id=NAME name=NAME >" + jsonObject.NAME + "</td>";
			 htmlString = htmlString + "<td id=MAKER_DTTM name=MAKER_DTTM >" + jsonObject.MAKER_DTTM + "</td>";
				
   				htmlString = htmlString + "<td id='' ><a class='btn btn-success' href='#' id='terminalview' index="+i+" val="+i+" title='View' data-rel='tooltip' > <i class='icon icon-book icon-white'></i></a></td>";				
			
			htmlString = htmlString + "</tr>";
	
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
	if(val.match("$"))
		{
		var x = val.split("$");
		code = x[1];
		
		}

	if(btn == 'terminalview')
	{
		$('#customercode').val(code);
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/superagentapprovalsearch.action';
		$("#form1").submit();
			return true; 
	}else if(btn == 'terminalmodify')
	{
		$('#terminalid').val(code);
		$('#limitCode').val(code);
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/terminalmanagementmodify.action';
		$("#form1").submit();
			return true; 
	}else if(btn == 'terminalactive')
	{
		$('#terminalid').val(code);
		$('#feeCode').val(code);
		$("#form1")[0].action='<%=request.getContextPath()%>/<%=appName %>/terminalmanagementstatus.action';
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
					<li><a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span></li>
					<li><a href="#">Super Agent Approval</a></li>
				</ul>
			</div>
			
			 			  
           <div class="row-fluid sortable"><!--/span-->
			<div class="row-fluid sortable">
			<div class="box span12" id="groupInfo">
				<div class="box-header well" data-original-title>Super Agent Information
					<div class="box-icon"> 
						<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
						<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a> 
					</div>
				</div> 
				<div class="box-content"> 
					<table width='100%' class="table table-striped table-bordered bootstrap-datatable datatable"  
						id="DataTables_Table_0" >
						<thead>
							<tr>
								<th>S No</th>
								<th>Reference Number</th>
								<th>Super Agent</th>
								<th>Mobile Number</th>
								<th>Name</th>
								<th>Created Date </th>  								
								<th>Actions</th>
							</tr>
						</thead> 
						 <tbody id="LMT_TBody">
							   
						</tbody>  
					</table>
					<input type="hidden" id="customercode" name="customercode"/>
					
				</div> 
				
				<div class="form-actions">

				<input type="button" class="btn btn-danger" name="btn-back" id="btn-back" value="Back"  />
				<span id ="error_dlno" class="errors"></span>

  			
			</div>
				
			</div>
			
			
			
		</div> 
		
		
		
	
		
		  <script type="text/javascript"> 
			   $(document).ready(function () {
				  binDetails = binDetails.split(",");
					$.each(binDetails, function(indexLink, val) {	
						prdIndex[indexLink]=val;	 
					}); 					
			  });  
			  
			  
			  
		</script>  
		<s:set value="responseJSON" var="respData"/> 
		<s:set value="%{'_STORES'}" var="searchKey"/> 
		
		<s:bean name="com.ceva.base.common.bean.JsonDataToObject" var="jsonToList">
		  	<s:param name="jsonData" value="#respData"/>  
 		    <s:param name="searchData" value="#searchKey"/>  
		</s:bean>
		<!-- Loading Stores -->
	 	 
			 
		</div> 
	</div> 
	 

   
</form>
 
<script type="text/javascript" src='${pageContext.request.contextPath}/js/jquery.dataTables.min.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/datatable-add-scr.min.js'></script> 
</body>
</html>
 