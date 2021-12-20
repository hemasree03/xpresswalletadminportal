
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<%String ctxstr = request.getContextPath(); %>
<% String appName= session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript">

$(function() {


	var val = 0;
	var rowindex = 1;
	var bankfinalData="${multiData}";	
	console.log(bankfinalData);
	var bankfinalDatarows=bankfinalData.split("#");

	var addclass = "";
	var offArr = '${officeLocation}'.split(",");

	for(var i=0;i<bankfinalDatarows.length;i++) {

			if(val % 2 == 0 ){
				addclass = "even";
				val++;
			} else {
				addclass = "odd";
				val++;
			}

			var eachrow=bankfinalDatarows[i];
			var eachfieldArr=eachrow.split(",");
			var service=eachfieldArr[0];
			var accountname=eachfieldArr[2];
			var openbalance=eachfieldArr[3];
			var secondname=eachfieldArr[4];
			var closebalance = eachfieldArr[1];
			var accounttype = eachfieldArr[12];
			var accounttype1 = eachfieldArr[8];

			var appendTxt = "<tr class="+addclass+" index='"+rowindex+"'> "+
				 "<td>"+rowindex+"</td>"+
				 "<td>"+service+"</td>"+
				 "<td>"+closebalance+"</td>"+
				 "<td>"+accountname+" </td>"+
				 "<td>"+openbalance+"</td>"+
				 "<td>"+accounttype1+"</td>"+
				 "<td>"+accounttype+"</td>"+
			 "</tr>";
			$("#tbody_data").append(appendTxt);
			rowindex++;
	}

	$('#btn-cancel').live('click', function () {
		//var url="${pageContext.request.contextPath}/<%=appName %>/userGrpCreation.action";
		var url="${pageContext.request.contextPath}/<%=appName %>/geTICTAdmincreatePgae.action";
		$("#form1")[0].action=url;
		$("#form1").submit();
	});

	$('#btn-submit').live('click', function () {
		var url="${pageContext.request.contextPath}/<%=appName %>/insertIctAdminDetails.action";
		$("#form1")[0].action=url;
		$("#form1").submit();

	});
});


</script>

</head>
<body>
<form name="form1" id="form1" method="post">

	<div id="content" class="span10">

		<div>
			<ul class="breadcrumb">
			  <li> <a href="home.action">Home</a> <span class="divider"> &gt;&gt; </span> </li>
			  <li> <a href="userGrpCreation.action">User Management</a> <span class="divider"> &gt;&gt; </span></li>
			  <li> <a href="#">User Creation Confirmation</a>  </li>
			</ul>
		</div>


	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
					<i class="icon-edit"></i>User Details Confirm
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
				</div>
			</div>
			
			<div class="box-content">
				<table class="table table-striped table-bordered bootstrap-datatable dataTable"
							id="mytable" style="width: 100%;">
				  <thead>
						<tr>
							<th>Sno</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>User Id</th>
							<th>Employee No</th>
							<th>E-Mail</th>
							<th>Office Location</th>
						</tr>
				  </thead>
				  <tbody id="tbody_data">
				  </tbody>
				</table>
			</div>
			
		</div>
	</div>
	<div class="form-actions">
		  <input type="button" class="btn btn-success" type="text"  name="btn-submit" id="btn-submit" value="Confirm" width="100" ></input>
			&nbsp;<input type="button" class="btn btn-danger" type="text"  name="btn-cancel" id="btn-cancel" value="Back" width="100" ></input>

			<input name="groupID" type="hidden" id="groupID" value="${groupID}" />
			<input name="entity" type="hidden" id="entity" value="${entity}" />
			<input type="hidden" name="multiData" id="multiData" value="${multiData}" />
			<input type="hidden" name="officeLocation" id="officeLocation" value="${officeLocation}" />
			<input type="hidden" name="typeuser" id="typeuser" value="${typeuser}" />
			
	</div>
</div>
</form>
</body>
</html>