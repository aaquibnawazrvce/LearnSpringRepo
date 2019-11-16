<!DOCTYPE html>
<%@page import="com.supplierconsumer.constants.Messages"%>
<html class="no-js">
<jsp:include page="/WEB-INF/jsp/commonfiles.jsp"></jsp:include>
<!--<![endif]-->
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<head>


<meta charset="utf-8" />
<meta content="text/html charset=UTF-8" http-equiv="Content-Type" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Register</title>
<meta name="description"
	content="We are a vertically integrated online interior design and furniture company. We custom design home interiors, show you how your new home will look in 3D, manufacture the furniture at our factory and undertake project execution. 5 Year Warranty " />
<meta name="keywords"
	content="full house furnishing, full home interiors, home interiors, custom design furniture, interior desinger, interior designs, modular kitchen, furniture online, wardrobes online, furniture online india, bedroom furniture, online furniture, home furniture online, living room furniture, office furniture " />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="icon" href="css/static/images/miscellaneous/favicon.ico?3" />

<style>
.productBtn {
	color: #fff !important;
	background-color: #4CAF50 !important;
	padding: 8px 16px;
	float: left;
	width: auto;
	border: none;
	display: block;
	outline: 0;
}
</style>


<script src="<%=request.getContextPath()%>/customizedjs/registerSupApp.js"></script>


</head>


<body>


	<div class="container" ng-app="registerUserApp" id="contentContainer">


		<div class="panel panel-primary">

			<div class="panel-heading">Register user</div>
			<div class="panel-body">

				<div ng-controller="registerUserController"
					class="main-content-wrapper">

					<!-- Start of Error Messages -->
					<div class="row">
						<div class="alert alert-success" ng-show="showSucess">{{
							sucessMsg }}</div>
						<div class="alert alert-danger" ng-show="showError">{{
							errorMsg }}</div>
					</div>
					<!-- End of Error Messages -->

					<!-- Start of Row -->
					<div class="row">

						<!--- User Name -->
						<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">

							<div class="form-group">
								<label for="userName"> User Name:</label> <input type="text"
									class="form-control" id="userName"
									ng-disabled="data_submission" ng-required="true"
									name="userName" ng-model="register.userName" maxlength="20">
							</div>
						</div>
						<!--- User Name -->

						<!--- Password -->
						<div class="col-lg-4 col-md-4 col-xs-12  col-sm-12">


							<div class="form-group">
								<label for="password"> Password:</label> <input type="password"
									class="form-control" id="password"
									ng-disabled="data_submission" ng-required="true"
									name="password" ng-model="register.password" maxlength="20">
							</div>

						</div>

						<!--- Password -->

						<!--- Email -->

						<div class="col-lg-4 col-md-4 col-xs-12  col-sm-12">

							<div class="form-group">
								<label for="email">Email address:</label> <input type="email"
									class="form-control" id="email" name="email"
									ng-disabled="data_submission" ng-model="register.email"
									maxlength="40" ng-required="true">
							</div>
						</div>

						<!--- Email -->



					</div>
					<!-- End of 1st Row -->

					<div class="row">
						<!-- Start of 2nd Row -->

						<!-- Phone No -->
						<div class="col-lg-4 col-md-4 col-xs-12  col-sm-12">

							<div class="form-group">
								<label for="phoneNo">Phone No:</label> <input type="text"
									class="form-control" id="phoneNo" name="phoneNo"
									ng-disabled="data_submission" ng-model="register.phoneNo"
									maxlength="10" ng-required="true">
							</div>
						</div>
						<!-- Phone No -->

						<!-- Gender -->

						<div class="col-lg-4 col-md-4 col-xs-12  col-sm-12">

							<div class="form-group">
								<label for="gender">Gender:</label> <select class="form-control"
									ng-model="register.gender">
									<option value="MALE">Male</option>
									<option value="FEMALE">Female</option>
									<option value="OTHER">Other</option>
								</select>

							</div>

						</div>

						<!-- Gender -->

						<!-- Country -->

						<div class="col-lg-4 col-md-4 col-xs-12  col-sm-12">

							<div class="form-group">
								<label for="country">Country</label> <input type="text"
									class="form-control" id="country" name="country"
									ng-disabled="data_submission" ng-model="register.country"
									maxlength="15" ng-required="true">
							</div>

						</div>

						<!-- Country -->





					</div>
					<!-- End of 2nd Row -->

					<div class="row">
						<!-- Start of 3rd Row -->

						<!-- Start of State -->

						<div class="col-lg-4 col-md-4 col-xs-12  col-sm-12">

							<div class="form-group">
								<label for="state">State</label> <input type="text"
									class="form-control" id="state" name="state"
									ng-disabled="data_submission" ng-model="register.state"
									maxlength="15" ng-required="true">
							</div>
						</div>

						<!-- End of State -->

						<!-- Start of City -->

						<div class="col-lg-4 col-md-4 col-xs-12  col-sm-12">


							<div class="form-group">
								<label for="city">City</label> <input type="text"
									class="form-control" id="city" name="city"
									ng-disabled="data_submission" ng-model="register.city"
									maxlength="15" ng-required="true">
							</div>
						</div>

						<!-- End of City -->

						<!-- Start of Age -->

						<div class="col-lg-2 col-md-2 col-xs-12  col-sm-12">


							<div class="form-group">
								<label for="age">Age</label> <input type="number"
									class="form-control" id="age" name="age"
									ng-disabled="data_submission" ng-model="register.age"
									maxlength="3" ng-required="true">
							</div>

						</div>


						<!-- End of Age -->



					</div>
					<!-- End of 3rd Row -->

					<div class="row">
						<!-- Start of 4th Row -->

						<div class="col-lg-4 col-md-4 col-sm=12 col-xs-12"></div>

						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">

							<button type="submit" class="productBtn"
								ng-click="register_user()">Register User</button>

						</div>

						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<a class="productBtn" href="<%=request.getContextPath()%>/">Home</a>
						</div>

					</div>
					<!-- End of 4th Row -->




				</div>
				<!-- Closing of Controller -->

			</div>
			<!-- Closing of Panel Body -->

		</div>
		<!-- Closing of Panel primary -->

	</div>
</body>