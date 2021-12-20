<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Another one from the CodeVinci">
<meta name="author" content="Team">
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%
	String ctxstr = request.getContextPath();
	String appName = session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString();
%>

<script type="text/javascript">
$(document).ready(function() {
	$('#btn-submit').live('click', function () {
		var url="${pageContext.request.contextPath}/<%=appName %>/merchantmenagenet.action";
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
			 <li><a href="home.action">Home</a> <span class="divider">
							&gt;&gt; </span></li>
					<li><a href="#">Merchant Management</a> <span class="divider">
							&gt;&gt; </span></li>
					<li><a href="#">Merchant Creation Acknowledgement</a></li>
			</ul>
		</div>
		<div class="row-fluid sortable">
			<div class="box span12">
				<div class="box-header well" data-original-title>
						<i class="icon-edit"></i>Merchant Creation Acknowledgment
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round"><i
							class="icon-cog"></i></a> <a href="#"
							class="btn btn-minimize btn-round"><i
							class="icon-chevron-up"></i></a>
					</div>
				</div>
				<div class="box-content" id="acktbl">
					<span>Merchant Created Successfully</span>
				</div>
				</div>
			</div>
			<div class="form-actions">
				<input type="button" name="btn-submit" class="btn btn-primary" id="btn-submit" value="Next" />
			</div>
		</div>
</form>
</body>
</html>
