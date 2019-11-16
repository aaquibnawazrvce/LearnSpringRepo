<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frameworkjs/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/customcss/custom.css">
<script src="<%=request.getContextPath()%>/frameworkjs/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/frameworkjs/bootstrap.min.js"></script>
</head>

<body>

	<div class="container" id="contentContainer">

		<div class="row">

			<!-- First Column Division -->

			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<!-- Panel Start -->
				<div class="panel panel-primary">

					<div class="panel-heading">Login User</div>
					<div class="panel-body">

						<div>
							<h1>Login for All</h1>
						</div>

						<div>

							<a href="<%=request.getContextPath()%>/loginUser"
								class="btn btn-primary">Login</a>
						</div>

					</div>
				</div>
				<!-- Panel End -->



			</div>

			<!-- First Column Division  End-->



			<!-- Second Column Division -->

			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<!-- Panel Start -->
				<div class="panel panel-primary">

					<div class="panel-heading">Register User</div>
					<div class="panel-body">

						<div>
							<h1>Registration for Customers</h1>
						</div>

						<div>
							<a href="<%=request.getContextPath()%>/registerCustomerPage"
								class="btn btn-primary">Sign Up user</a>
						</div>

					</div>
				</div>
				<!-- Panel End -->



			</div>

			<!-- Second Column Division  End-->
			
			
			<!-- Third Column Division -->

			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<!-- Panel Start -->
				<div class="panel panel-primary">

					<div class="panel-heading">Register Supplier</div>
					<div class="panel-body">

						<div>
							<h1>Registration for Supplier</h1>
						</div>

						<div>
							<a href="<%=request.getContextPath()%>/registerSupplierPage"
								class="btn btn-primary">Sign Up user</a>
						</div>

					</div>
				</div>
				<!-- Panel End -->



			</div>

			<!-- Third Column Division  End-->



		</div>

	</div>

</body>

</html>