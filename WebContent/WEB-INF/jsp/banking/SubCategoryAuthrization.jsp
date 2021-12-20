
<head>
<meta charset="utf-8">
<title>NBK</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="Team">
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%String ctxstr = request.getContextPath(); %>
<% String appName= session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>
 
  
     
<link rel="stylesheet" href="<%=ctxstr%>/css/treenode/css/demo.css" type="text/css">
<link rel="stylesheet" href="<%=ctxstr%>/css/treenode/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=ctxstr%>/css/treenode/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=ctxstr%>/css/treenode/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=ctxstr%>/css/treenode/jquery.ztree.exhide-3.5.js"></script>
<SCRIPT type="text/javascript"> 

function checkChecked(classCheck){
	if(classCheck == "button chk checkbox_true_full" 
		|| classCheck == "button chk checkbox_true_part" 
		 ||classCheck == "button chk checkbox_true_disable")	return true;
	
	return false;
}

function getCheckVal(obj){ 
	var getText = "#"+$(obj).attr('id') +" > a > span:eq(1)";
	getText = $(getText).text(); 
	return getText;
}	 

function constructString(parentId) { 
	var getText = "";  
	var checkedStringConstruction="";	
	$(parentId).each(function(mainindex,mainele){   // Top Parent Iteration
	 
		var status = false;
		var pid = $(this).attr('class').replace("level","");
		var id = $(this).attr('id').replace("treeDemo_","");
		 
		var parentId = $(this).attr('id');
		
		var span1Class = "#"+parentId+" > span#"+parentId+"_switch";
		var span2Class = "#"+parentId+" > span#"+parentId+"_check";
	
		getText = getCheckVal(this); 
		
		checkedStringConstruction+='{ "id":"'+id+'", "pId":"'+pid+'",  "name":"'+getText+'" , "checked":"'+checkChecked($(span2Class).attr('class'))+'" , "open":"true", "title":"","chkDisabled":"false" },';
	 
		var getUlList = "#"+parentId +' > ul > li'; 
		if($(getUlList).length > 0) {
			checkedStringConstruction+=constructStringSub(getUlList,parentId); 
		} else return; 
		 
	});  

	return checkedStringConstruction;
} 
function constructStringSub(parentSubId,parentId) {

	var getText = "";  
	var str="";	
	$(parentSubId).each(function(mainindex,mainele) {   // Sub Child Iteration
	 
		var status = false;
		var pid = $("#"+parentId).attr('id').replace("treeDemo_","");
		var id = $(this).attr('id').replace("treeDemo_","");
		
		var parentSubId = $(this).attr('id');
		
		var span1Class = "#"+parentSubId+" > span#"+parentSubId+"_switch";
		var span2Class = "#"+parentSubId+" > span#"+parentSubId+"_check"; 
		
		getText = getCheckVal(this); 
		str+='{ "id":"'+id+'", "pId":"'+pid+'",  "name":"'+getText+'" , "checked":"'+checkChecked($(span2Class).attr('class'))+'" , "open":"true", "title":"","chkDisabled":"false" },';
 
		var getUlList = "#"+parentSubId +' > ul > li';
		 
		if($(getUlList).length > 0) {
			str+=constructStringSubSub(getUlList,parentSubId); 
		} else return;
	 
	}); 
	 
	return str;
}

function constructStringSubSub(parentSubSId,parentId) {

	var getText = "";  
	var str="";	
	$(parentSubSId).each(function(mainindex,mainele) {   // Sub Child Iteration
	 
		var status = false; 
		var pid = $("#"+parentId).attr('id').replace("treeDemo_","");
		var id = $(this).attr('id').replace("treeDemo_","");
		
		var parentSubSId = $(this).attr('id');
		
		var span1Class = "#"+parentSubSId+" > span#"+parentSubSId+"_switch";
		var span2Class = "#"+parentSubSId+" > span#"+parentSubSId+"_check";
		 
	 
		getText = getCheckVal(this); 
		str+='{ "id":"'+id+'", "pId":"'+pid+'",  "name":"'+getText+'" , "checked":"'+checkChecked($(span2Class).attr('class'))+'" , "open":"true", "title":"","chkDisabled":"false" },';
 
		var getUlList = "#"+parentSubSId +' > ul > li';
		 
		if($(getUlList).length > 0) {
			str+=constructStringSubSub(getUlList); 
		} else return;
	 
	});  
	 
	return str;
}

//<!--
var setting = {
	check: {
		enable: true
	},
	data: {
		key: {
			title: "title"
		},
		simpleData: {
			enable: true
		}
	},
	callback: {
		onCheck: onCheck
	}
}; 
 
//var zNodes = ; 
 

function onCheck(e, treeId, treeNode) { 
	count();
}

function setTitle(node) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = node ? [node]:zTree.transformToArray(zTree.getNodes());
	for (var i=0, l=nodes.length; i<l; i++) {
		var n = nodes[i];
		n.title = "[" + n.id + "] isFirstNode = " + n.isFirstNode + ", isLastNode = " + n.isLastNode;
		//zTree.updateNode(n);
	}
}
function count() {
	function isForceHidden(node) {
		if (!node.parentTId) return false;
		var p = node.getParentNode();
		return !!p.isHidden ? true : isForceHidden(p);
	}
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	checkCount = zTree.getCheckedNodes(true).length,
	nocheckCount = zTree.getCheckedNodes(false).length,
	hiddenNodes = zTree.getNodesByParam("isHidden", true),
	hiddenCount = hiddenNodes.length;

	for (var i=0, j=hiddenNodes.length; i<j; i++) {
		var n = hiddenNodes[i];
		if (isForceHidden(n)) {
			hiddenCount -= 1;
		} else if (n.isParent) {
			hiddenCount += zTree.transformToArray(n.children).length;
		}
	}

	$("#isHiddenCount").text(hiddenNodes.length);
	$("#hiddenCount").text(hiddenCount);
	$("#checkCount").text(checkCount);
	$("#nocheckCount").text(nocheckCount);
}
function showNodes() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	nodes = zTree.getNodesByParam("isHidden", true);
	zTree.showNodes(nodes);
	setTitle();
	count();
}
function hideNodes() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	nodes = zTree.getSelectedNodes();
	if (nodes.length == 0) {
		alert("Please select one node at least.");
		return;
	}
	zTree.hideNodes(nodes);
	setTitle();
	count();
} 

var toDisp = '${type}';
var checkedParentStringConstruction = '';

function checkLink(){
	var parentId = '#treeDemo > li'; 
	var getBuildStr = constructString(parentId); 
	getBuildStr = getBuildStr.substr(0,getBuildStr.lastIndexOf(",")); 
	$('#userRights').val("["+getBuildStr+"]"); 
	checkedParentStringConstruction = '{ "user_details" : [' + getBuildStr+ ']}';
}

$(document).ready(function(){
	wow();
	if($("#AUTH_CODE").val()=="ACTDCTGRP")
		{
		$("#formName").val("Group Active/Deactive");
		$("#htitle").text("Group Active/Deactive");
		$("#stitle").text("Group Active/Deactive")
		
		}else if($("#AUTH_CODE").val()=="MODGRP"){
			$("#formName").val("Group Modify");
			$("#htitle").text("Group Modify");
			$("#stitle").text("Group Modify")
		}else if($("#AUTH_CODE").val()=="ADDNEWGRP"){
			$("#formName").val("Group New");
			$("#htitle").text("Group New");
			$("#stitle").text("Group New")
		}
	var metcheck = "${method}" ;
	
	if( metcheck == "Modify") {
		$.fn.zTree.init($("#treeDemo"), setting, jQuery.parseJSON('${userRights}') );
	} else {
		$.fn.zTree.init($("#treeDemo"), setting, jQuery.parseJSON('${responseJSON.json_val.user_details}')); 
	} 

 	$("#hideNodesBtn").bind("click", {type:"rename"}, hideNodes);
	$("#showNodesBtn").bind("click", {type:"icon"}, showNodes);
	setTitle();
	count();
	
	var actionLink = "";
	
	

	$('#btn-submit').live('click',function() {  
		
		var searchIDs="";
		 
 		$("div#merchant-auth-data input:radio:checked").each(function(index) {
 			searchIDs=$(this).val();
 			 $('#DECISION').val(searchIDs);
		});
		
		  if(searchIDs.length == 0) {
				$("#error_dlno").text("Please check atleast one record.");
			} else {
						$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/commonAuthRecordconfirm.action";
						$("#form1").submit();	
			}
	});  
	
	$('#btn-back').live('click',function() {  
		
		
		$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/AuthorizationAll.action";
		$("#form1").submit();	

});  
});
	//--> 

	
	function wow(){
		
		var auth=$('#STATUS').val();
		
		

		if ( auth == 'C' || auth == 'R'){
			$("#merchant-auth-data").hide();
			$("#btn-submit").hide();
		}

		  
	}
	//--> 
</SCRIPT> 
	 
</head>

<body>
	<form name="form1" id="form1" method="post">
	  <div id="content" class="span10"> 
			 
		    <div> 
				<ul class="breadcrumb">
				  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
				  <li> <a href="userGrpCreation.action">Biller Management</a> <span class="divider"> &gt;&gt; </span></li>
					  <li><a href="#"><span id="htitle"></span> Authorization Confirmation </a></li>
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
				 <i class="icon-edit"></i><span id="stitle"></span> Authorization
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
					
				</div>
			</div>
						
			<div class="box-content">
			<fieldset> 
				<table width="950" border="0" cellpadding="5" cellspacing="1" 
					class="table table-striped table-bordered bootstrap-datatable "> 
							
						<tr >
							<td  width="8%">&nbsp;</td>
							<td  width="25%"><strong><label for="Region">Category Id</strong></label></td>
							<td  width="67%" class="regionTd">
							 ${responseJSON.catids} </td>
						</tr>
							
						<tr >
							<td>&nbsp;</td>
							<td><strong><label for="Region">Sub Category Name</strong></label></td>
							<td class="regionTd">
							 ${responseJSON.groupType}</td>
						</tr> 
						
						
						
				</table>  
		</fieldset>   
		</div>
		</div>
		</div>
					
		<div class="row-fluid sortable" id="assgrp"> 
			<div class="box span12"> 
				<div class="box-header well" data-original-title>
						<i class="icon-edit"></i>Assign To Billers
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round"><i
							class="icon-cog"></i></a> <a href="#"
							class="btn btn-minimize btn-round"><i
							class="icon-chevron-up"></i></a>

					</div>
				</div>
						
		<div class="box-content">
		 <fieldset> 
			<table  width="950" border="0" cellpadding="5" cellspacing="1" class="table table-striped table-bordered bootstrap-datatable " >
				<tr class="odd">
					<td width="20%">&nbsp;</td> 
					<td width="80%"><div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul> 
					</div> 
					</td>
			 	</tr>
			</table> 
		</fieldset>  
	  
		</div>
		</div>
		</div> 
		
		 <div id="merchant-auth-data"> 
				<ul class="breadcrumb">
				 <li> <strong>Authorize&nbsp&nbsp&nbsp&nbsp&nbsp</label></strong><input  name="authradio" id="authradio"  class='center-chk' type='radio' value='A' />&nbsp&nbsp </li>
				 <li> <strong>Reject&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label></strong><input  name="authradio" id="authradio"  class='center-chk' type='radio' value='R' /> &nbsp&nbsp&nbsp</li>
				</ul>
		</div>  
		
         	<div class="form-actions">
			
				<input type="button" class="btn btn-success" name="btn-submit"
						id="btn-submit" value="Confirm"  />

				<input type="button" class="btn btn-danger" name="btn-back"
						id="btn-back" value="Back"  />

				<span id ="error_dlno" class="errors"></span>

  			   <input name="STATUS" type="hidden" id="STATUS" value="${STATUS}" />
  				<input name="AUTH_CODE" type="hidden" id="AUTH_CODE" value="${AUTH_CODE}"  />
				<input type="hidden" name="REF_NO" id="REF_NO" value="${REF_NO}"/>
				<input type="hidden" name="DECISION" id="DECISION" />
				<input type="hidden" name="remark" id="remark" />
				
				<input type="hidden" name="formName" id="formName" />
			

			</div>     						 
	</div><!--/#content.span10--> 
 </form>
</body>
</html> 