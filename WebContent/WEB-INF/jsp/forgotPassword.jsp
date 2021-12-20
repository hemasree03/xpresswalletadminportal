 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
<%@taglib uri="/struts-tags" prefix="s"%>  
 <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<%String ctxstr = request.getContextPath(); %>
<% String appName= session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=application.getInitParameter("pageTitle") %></title>

<%@ include file="css.jsp" %>
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>


<style type="text/css">
.errorMessage {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}

.errors {
	background-color: #FFCCCC;
	border: 0px solid #CC0000;
	width: 400px;
	margin-bottom: 8px;
}

.error {
	background-color: #FFCCCC;
	border: 0px solid #CC0000;
	width: 400px;
	margin-bottom: 8px;
}

.errors li {
	list-style: none;
}

body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

.full-spn-right {
	margin-left: 1200px;
}
</style>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript">
	


var validationRules = {
   rules : {
	   userid : { required : true }
   },  
   messages : {
	   userid : { 
			required : "Enter User Id"
        }
   } 
 };
 
function submitPasswordInfo(){
	$("#form1").validate(validationRules);
	if($("#form1").valid()){
		$("#form1")[0].action='<%=request.getContextPath()%>/forgotPassword.action';
		$("#form1").submit();
		return true; 
	}else{
		return false;
	}
}
$(document).ready(function() {

	

	$('#confirm-save').on('click', function() {
		submitPasswordInfo();
	}); 
});
</script>
</head>
<body style="background-color: rgb(8, 68, 34);">
	<form name="form1" id="form1" method="post" action="">
		<section class="container"> 
			<div class="login"> 
				<div class="display:table-row;" style="background-color: rgb(8, 68, 34);">
				<section class="display:table-cell">
					<img src="images/logo-main.png" width="200" height="80" style="margin-right: -150px; text-align: right; margin-bottom: 30px; margin-left: 50px;"
						alt="logo"></img>
				</section>
			
				</div> 
				<s:actionmessage />
				<s:actionerror />
				
				<p>
					<input  name="userid" id="userid" type="text" autocomplete="off"
						required="true" value="" placeholder="Enter User Id" />
				</p>
				
				<p class="submit">
					<input type="button" name="confirm-save" id="confirm-save" value="Submit"  
						class="login__submit"    />
					 
				</p> 
			</div>

		</section> 
<script src="${pageContext.request.contextPath}/js/authenticate.js"></script>
<script src="${pageContext.request.contextPath}/js/sha256.js"></script> 
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
	</form> 
</body> 
</html>