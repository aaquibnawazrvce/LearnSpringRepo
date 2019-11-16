<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="com.supplierconsumer.model.AJAXResponse"%>
<%@page import="com.supplierconsumer.model.Message"%>
<%@page import="com.supplierconsumer.constants.Messages"%>
<html class="no-js">
<!--<![endif]-->
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frameworkjs/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/customcss/custom.css">
<script src="<%=request.getContextPath()%>/frameworkjs/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/frameworkjs/bootstrap.min.js"></script>
<%@page
	import="com.supplierconsumer.model.AJAXResponse,
	java.util.List,com.supplierconsumer.model.Message"%>
</head>

<style>
#productForm {
	padding: 10px;
}
</style>

<body>
	<jsp:include page="/WEB-INF/jsp/customermenu.jsp"></jsp:include>

	<div class="container">


		<div class="panel panel-primary">

			<div class="panel-heading">Change Password</div>
			<div class="panel-body">

				<div>

					<!-- Start of Error Messages -->

					<%
						AJAXResponse ajax = (AJAXResponse) request.getAttribute(Messages.Keys.OBJ);

						if (null == ajax) {

						} else {
							List<Message> ebErrors = ajax.getErrMessages();

							if (null == ebErrors) {

							} else {
								Message msg = ebErrors.get(0);
					%>
					<div class="alert alert-danger" name="errorMessages">
						<%=msg.getErrMessage()%>
					</div>
					<%
						}
							
							if(ajax.isStatus()){
								
							if(ajax.getMessage()!=null){
								
						%>
					<div class="alert alert-success">
						<%=ajax.getMessage()%>
					</div>
					<%
							}
								
							}
							
							
						}
					%>

					<!-- End of Error Messages -->

					<!-- Start of Row -->
					<div class="row productForm">

						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<a class="productBtn"
								href="<%=request.getContextPath()%>/adminPage">Home</a>
						</div>

					</div>
					<!-- End of  Row -->

					<!-- Start of the 2nd Row For Home -->
					<div class="row">
						<form action="<%=request.getContextPath()%>/changePassword.do"
							method="post" id="productForm">
							<div class="form-group">
								<label for="oldpassword">Old Password:</label> <input
									type="text" class="form-control" id="oldpassword"
									name="oldpassword" placeholder="Old Password">
								<input
									type="hidden" class="form-control" id="logintype"
									name="logintype" value="3">
							</div>
							<div class="form-group">
								<label for="newpassword">New Password:</label> <input
									type="text" class="form-control" id="newpassword"
									name="newpassword" placeholder="New Password">

							</div>


							<div class="form-group">
								<label for="newpassword">Confirm New Password:</label> <input
									type="text" class="form-control" id="confirmnewpassword"
									name="confirmnewpassword" placeholder="Confirm New Password">

							</div>


							<button type="submit" class="btn btn-primary">Change
								Password</button>
						</form>

					</div>
					<!--  End of the 2nd Row For Home -->


				</div>
				<!-- Closing of Controller -->

			</div>
			<!-- Closing of Panel Body -->

		</div>
		<!-- Closing of Panel primary -->

	</div>
</body>