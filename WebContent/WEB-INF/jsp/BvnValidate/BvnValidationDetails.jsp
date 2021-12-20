<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<%@taglib uri="/struts-tags" prefix="s"%>  
<%@page import="com.ceva.base.common.utils.CevaCommonConstants"%>
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<%String ctxstr = request.getContextPath(); %>
<% String appName= session.getAttribute(CevaCommonConstants.ACCESS_APPL_NAME).toString(); %> 
 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.PrintArea.js"></script>
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
<script type="text/javascript">



function PrintDiv() {
    var divToPrint = document.getElementById('print');
    var popupWin = window.open('', '', 'width=300,height=300');
   // popupWin.document.open();
    popupWin.document.write('<html><body onload="window.print()">' + divToPrint.innerHTML + '</html>');
    popupWin.document.close();
}

$(document).ready(function(){
$("#print_button").click(function(){
	
	  // alert($(this).prop("name"));
	   var v="QR CODE";
	
  var options = {mode:"popup",popHt: 500,   popWd: 400, popX: 500,   popY: 600,popTitle:v,popClose: false};
  //$("#printarea").append("<img src=${pageContext.request.contextPath}/images/BANK_logo.png ></img>");
  
  $("#print").printArea( options );   
  
  	
  
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
					  <li> <a href="#">BVN Details View</a>  </li> 
 					</ul>
				</div>  

				<table>
					<tr>
						<td colspan="3">
							<div class="messages" id="messages"><s:actionmessage /></div>
							<div class="errors" id="errors"><s:actionerror /></div>
						</td>
					</tr>
				</table>
<div id="print">
		<div class="row-fluid sortable"> 
			<div class="box span12"> 
					<div class="box-header well" data-original-title>
							<i class="icon-edit"></i>BVN Validation
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> 
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
						</div>
					</div>  
					
				<div class="box-content">
					<fieldset> 
						<table width="950" border="0" cellpadding="5" cellspacing="1" 
									class="table table-striped table-bordered bootstrap-datatable " id="user-details"> 
						   
						<tr class="odd">
							<td width="25%"><label for="First Name"><strong>First Name</strong></label></td>
							<td width="25%">${responseJSON.fullname}<input type="hidden" name="fullname"  id="fullname"   value="${responseJSON.fullname}"   /></td>
							<td width="20%"><label for="From Date"><strong>Middle Name</strong></label></td>
							<td width="30%">${responseJSON.middlename} <input type="hidden" name="middlename"  id="middlename"   value="${responseJSON.middlename}"   />  </td>
						</tr> 
							
							
						<tr class="even">
						    <td width="25%"><label for="Last Name"><strong>Last Name</strong></label> </td>
							<td width="20%">${responseJSON.lastname} <input type="hidden" name="lastname"  id="lastname"   value="${responseJSON.lastname}"   />  </td>
							<td  width="20%"><label for="To Date"><strong>Date Of Birth</strong></label></td>
							<td  width="30%">${responseJSON.dateofbirth} <input type="hidden" name="dateofbirth"  id="dateofbirth"   value="${responseJSON.dateofbirth}"   />  </td>
						</tr>
							 
						<tr class="odd">
							<td  width="25%"><label for="Email ID"><strong>Email ID</strong></label></td>
							<td  width="25%">${responseJSON.email} <input type="hidden" name="email"  id="email"   value="${responseJSON.email}"   />  </td>
							<td width="20%" ><label for="Mobile Number"><strong>Mobile Number</strong></label> </td>
					       <td  width="30%" >${responseJSON.telephone}<input type="hidden" value='${responseJSON.telephone}' style="width:155px;" maxlength="13" name="telephone" id="telephone"  /> 
 							 </td>
						</tr>
						<tr class="even">
						     </td> 
								<td  width="25%"><strong>Gender</strong></td>
								<td  width="25%">${responseJSON.gender} 
								<input type="hidden" value='${responseJSON.gender}' style="width:155px;"  name="gender" id="gender"  /></td>
						       <td  width="20%"><label for="Marital Status"><strong>Marital Status</strong></label></td>
							<td  width="30%">${responseJSON.martStatus} <input type="hidden" name="martStatus"  id="martStatus"   value="${responseJSON.martStatus}"   />  </td>
						</tr>
 						
 						 <tr class="odd">
						     </td> 
								<td  width="25%"><strong>Level Of Account</strong></td>
								<td  width="25%">${responseJSON.levelOfAccount} 
								<input type="hidden" value='${responseJSON.levelOfAccount}' style="width:155px;"  name="mcc" id="mcc"  /></td>
						       <td  width="20%"><label for="state Of Origin"><strong>Enrollment Branch</strong></label></td>
							<td  width="30%">${responseJSON.enrollmentBranch} <input type="hidden" name="bsnname"  id="bsnname"   value="${responseJSON.enrollmentBranch}"   />  </td>
						</tr>
						
					
					
					
						
 												
				 </table>
				 </div> 
				 </div>
				<div class="row-fluid sortable"> 
	 
				 <div class="row-fluid sortable">
						<div class="box span12">
								<div class="box-header well" data-original-title>
										<i class="icon-edit"></i>Communication Details
									<div class="box-icon">
										<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
										<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
									</div>
								</div>

								<div id="communicationDetails" class="box-content">
									<fieldset>
										<table width="950" border="0" cellpadding="5" cellspacing="1"
												class="table table-striped table-bordered bootstrap-datatable ">
															
					 <tr class="odd">
						<td width="25%"><label for="Id Card Type"><strong>Name On Card</strong></label></td>
						<td width="25%">${responseJSON.nameOnCard}<input type="hidden" name="idtype"  id="idtype" value="${responseJSON.nameOnCard}"   /></td>
						<td  width="25%"><label for="Id Card Number"><strong>National Identity Number</strong></label></td>
						<td  width="25%">${responseJSON.nationalIdentityNumber} <input type="hidden" name="iddetails"  id="iddetails"   value="${responseJSON.nationalIdentityNumber}"   />  </td>
					</tr> 
					   
                       <tr class="even">
								<td width="25%"><label for="Address"><strong>Residential Address</strong></label></td>
								<td width="25%">${responseJSON.address}<input type="hidden" name="address"  id="address" value="${responseJSON.address}"   /></td>
								<td  width="25%"><label for="To Date"><strong>Nationality</strong></label></td>
								<td  width="25%">${responseJSON.nationality} <input type="hidden" name="nationality"  id="nationality"   value="${responseJSON.nationality}"   />  </td>
					</tr>
										    
										<tr class="odd">
											<td width="25%"><label for="Street Name"><strong>State Of Origin</strong></label></td>
											<td width="25%">${responseJSON.stateOfOrigin}<input type="hidden" name="area"  id="area" value="${responseJSON.stateOfOrigin}"   /></td>
										<td width="25%" ><label for="Local Government"><strong>State Of Residence</strong></label> </td>
											<td  width="25%" >${responseJSON.stateOfResidence}<input type="hidden" value='${responseJSON.stateOfResidence}' style="width:155px;" maxlength="13" name="lga" id="lga"  /> 
											</td>
											</tr>

									 <tr class="even">
										
										
											<td  width="25%" >${responseJSON.image}<input type="hidden" value='${responseJSON.image}' style="width:155px;" maxlength="13" name="image" id="image"  /> 
											</td>
										
										
										</tr>
										 
									  

									</table>
								</fieldset>
							</div>
						</div>
				</div>
				
		
		
						</div>
						</div>
						
				  
								<input type="hidden" name="langugae"  id="langugae"   value="English"  />
								
				</fieldset> 
				</div>
						
			<div>
								<a href="#" id="print_button" class="btn btn-success ajax-link" >&nbsp;Print</a> 
							</div>
				</div> 
				 	
				
		</div> 	
</form>	

		
		
	</div> 

</body> 
</html>