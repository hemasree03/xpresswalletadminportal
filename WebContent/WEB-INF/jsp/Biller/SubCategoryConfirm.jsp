
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Ceva</title>
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
		 
 			var pid = $(this).attr('class').replace("level","");
			var id = $(this).attr('id').replace("treeDemo_","");
 			 
			var parentId = $(this).attr('id');
 			
 			var span2Class = "#"+parentId+" > span#"+parentId+"_check";
		 
			getText = getCheckVal(this); 
			
 			checkedStringConstruction+='{ "id":"'+id+'", "pId":"'+pid+'",  "name":"'+getText+'" , "checked":"'+checkChecked($(span2Class).attr('class'))+'" , "open":"true", "title":"" },';
 		 
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
 			
			//var span1Class = "#"+parentSubId+" > span#"+parentSubId+"_switch";
			var span2Class = "#"+parentSubId+" > span#"+parentSubId+"_check"; 
			
			getText = getCheckVal(this); 
 			str+='{ "id":"'+id+'", "pId":"'+pid+'",  "name":"'+getText+'" , "checked":"'+checkChecked($(span2Class).attr('class'))+'" , "open":"true", "title":"" },';
	 
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
		 
 			var pid = $("#"+parentId).attr('id').replace("treeDemo_","");
			var id = $(this).attr('id').replace("treeDemo_","");
 			
			var parentSubSId = $(this).attr('id');
 			
			//var span1Class = "#"+parentSubSId+" > span#"+parentSubSId+"_switch";
			var span2Class = "#"+parentSubSId+" > span#"+parentSubSId+"_check";
			 
		 
			getText = getCheckVal(this); 
 			str+='{ "id":"'+id+'", "pId":"'+pid+'",  "name":"'+getText+'" , "checked":"'+checkChecked($(span2Class).attr('class'))+'" , "open":"true", "title":"" },';
	 
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
		 
		var zNodes = ${userRights}; 
		var zNodespos = ${userPosRights};
		var zNodesussd = ${userUssdRights};
		 
	 
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

			/* $("#isHiddenCount").text(hiddenNodes.length);
			$("#hiddenCount").text(hiddenCount);
			$("#checkCount").text(checkCount);
			$("#nocheckCount").text(nocheckCount); */
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

		$(document).ready(function(){
			
			/* var gptype=$('#groupType').val();
			var gslevel=$('#userlevel').val();
			
			$('#glevel').html(gslevel.split("-")[2]);
			$('#gtype').html(gptype.split("-")[1]);  */
			
			
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$.fn.zTree.init($("#postreeDemo"), setting, zNodespos);
			$.fn.zTree.init($("#ussdtreeDemo"), setting, zNodesussd);
			$("#hideNodesBtn").bind("click", {type:"rename"}, hideNodes);
			$("#showNodesBtn").bind("click", {type:"icon"}, showNodes);
			setTitle();
			count();
			//$('#treeDemo > li').attr('disabled','disabled');
			$('#btn-submit').live('click',function() { 
				
				var parentId = '#treeDemo > li'; 
				var getBuildStr = constructString(parentId); 
				getBuildStr = getBuildStr.substr(0,getBuildStr.lastIndexOf(",")); 
				
				//var checkedParentStringConstruction = '{ "user_details" : [' + getBuildStr+ ']}'; 
				$('#userRights').val("["+getBuildStr+"]");
				//$('#jsonVal').val(checkedParentStringConstruction);
				$('#keyVal').val("user_details");
				$('#keyPosVal').val("user_Pos_details");
				$('#keyUssdVal').val("user_Ussd_details");
				  
				$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/SubCategorySave.action";
				$("#form1").submit();				
			});
			
			$('#btn-cancel').live('click',function() {  
				$("#form1")[0].action="<%=request.getContextPath()%>/<%=appName %>/catagorymng.action";
				$("#form1").submit();				
			});
			 
		});
		//--> 
	</SCRIPT>
    
   <style>
  
  div.zTreeDemoBackground {
    width: 250px;
     height: 120px;
    text-align: left;
}

ul.ztree {
    margin-top: 10px;
    border: 1px solid #617775;
    background: #f0f6e4;
    width: 370px;
   height: 100px;
    overflow-y: scroll;
    overflow-x: auto;
}

</style>
 
		
</head>

<body>
	<form name="form1" id="form1" method="post">
	 
			<div id="content" class="span10">

			 
             			<!-- content starts -->
			    <div> 
					<ul class="breadcrumb">
					  <li> <a href="#">Home</a> <span class="divider"> &gt;&gt; </span> </li>
					  <li> <a href="#">Biller Mapping</a> <span class="divider"> &gt;&gt; </span></li>
 					  <li><a href="#">Sub Category Creation Confirmation </a></li>
					</ul>
				</div>  
		<div class="row-fluid sortable">

			<div class="box span12">

				<div class="box-header well" data-original-title>
						<i class="icon-edit"></i>Sub Category Creation Confirmation
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round"><i
							class="icon-cog"></i></a> <a href="#"
							class="btn btn-minimize btn-round"><i
							class="icon-chevron-up"></i></a>

					</div>
				</div>
						
		<div class="box-content">
		 <fieldset> 
				<table width="950" border="0" cellpadding="5" cellspacing="1" 
							class="table table-striped table-bordered bootstrap-datatable "> 
						
						<tr>
							<td width="25%"><strong><label for="Group Type"><strong>Category Name</strong></label></strong></td>
							<td width="25%" >${groupType} <input name="groupType" type="hidden" class="field" id="groupType"  value="${groupType}"  /></td>	
							<td width="25%"><strong><label for="User Level"><strong>Category Id</strong></label></strong></td>
							<td width="25%" >${catids} <input name="catids" type="hidden" class="field" id="catids"  value="${catids}"  /></td>	
							
							
						</tr>
						<tr>
							<td width="25%"><strong><label for="Group Type"><strong>Status</strong></label></strong></td>
							<td width="25%" >${status} <input name="status" type="hidden" class="field" id="status"  value="${status}"  /></td>	
							<td width="25%"></td>
							<td width="25%" ></td>	
							
							
						</tr>
						<tr >
							
					<td width="25%"><label for="Limit Code"><strong>Sub Category Name</strong></label></td>
								<td width="25%" >${subcategory}
								<input type="hidden" name="subcategory"  id="subcategory" value="${subcategory}" />
								</td>
								 <td width="25%"></td>
								 <td width="25%"></td> 
						
						</tr> 
				</table>  
		</fieldset>   
		</div>
		</div>
		</div>
					
		<div class="row-fluid sortable">

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

				  
		 
		 <table  width="950" border="0" cellpadding="5" cellspacing="1"  
		 class="table table-striped table-bordered bootstrap-datatable ">
				<tr class="odd">
					<td width="20%">&nbsp;</td> 
					<td width="80%">Mobile</td>
			 	</tr>
				<tr class="odd">
					<td width="20%">&nbsp;</td> 
					<td width="80%"><div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul> 
					</div> 
					</td>
			 	</tr>
			 	<tr class="odd">
					<td width="20%">&nbsp;</td> 
					<td width="80%">USSD</td>
			 	</tr>
			 	<tr class="odd">
					<td width="20%">&nbsp;</td> 
					<td width="80%"><div class="zTreeDemoBackground left">
						<ul id="ussdtreeDemo" class="ztree"></ul> 
					</div> 
					</td>
			 	</tr>
			 	<tr class="odd">
					<td width="20%">&nbsp;</td> 
					<td width="80%">POS</td>
			 	</tr>
			 	<tr class="odd">
					<td width="20%">&nbsp;</td> 
					<td width="80%"><div class="zTreeDemoBackground left">
						<ul id="postreeDemo" class="ztree"></ul> 
					</div> 
					</td>
			 	</tr>
			</table> 
		 
		</fieldset> 
			
			 
		   <div class="form-actions">
				<input type="button" name="btn-submit" class="btn btn-success" id="btn-submit" value="Confirm" />
				<input type="button" name="btn-cancel" class="btn btn-warning" id="btn-cancel" value="Cancel" />
 				<input type="hidden" name="jsonVal"  id="jsonVal" value='${jsonVal}' />
 				<input type="hidden" name="jsonPosVal"  id="jsonPosVal" value='${jsonPosVal}'  />
				<input type="hidden" name="jsonUssdVal"  id="jsonUssdVal" value='${jsonUssdVal}'  />
				
				<input type="hidden" name="keyVal"  id="keyVal" value="" />
				<input type="hidden" name="keyPosVal"  id="keyPosVal" value="" />
				<input type="hidden" name="keyUssdVal"  id="keyUssdVal" value="" />
				
				<input type="hidden" name="userRights"  id="userRights" value="" />
				<input type="hidden" name="userPosRights"  id="userPosRights" value="" />
				<input type="hidden" name="userUssdRights"  id="userUssdRights" value="" />
				<input type="hidden" name="entity"  id="entity" value="${entity}" />
				<input type="hidden" name="type" id="type" value="${type}"/>
			  </div>

		</div>
		</div>
		</div>
                 
              <!--/row--><!--/row-->
              
              						<!-- content ends -->
			</div><!--/#content.span10-->
		 
 </form>
</body>
</html>

