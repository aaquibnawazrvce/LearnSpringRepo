<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="com.supplierconsumer.model.AJAXResponse"%>
<%@page import="com.supplierconsumer.model.Message"%>
<%@page import="com.supplierconsumer.constants.Messages"%>
<html class="no-js">
<!--<![endif]-->
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset='utf-8'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="cssmenu/styles.css">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="cssmenu/script.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,800,700'
	rel='stylesheet' type='text/css'>


<!-- kendo and angular js -->
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.common.min.css" />
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.rtl.min.css" />
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.silver.min.css" />
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.mobile.all.min.css" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://kendo.cdn.telerik.com/2017.2.621/js/angular.min.js"></script>
<script
	src="https://kendo.cdn.telerik.com/2017.2.621/js/kendo.all.min.js"></script>

<script>
        /*
            This demo renders the grid in "DejaVu Sans" font family, which is
            declared in kendo.common.css. It also declares the paths to the
            fonts below using <tt>kendo.pdf.defineFont</tt>, because the
            stylesheet is hosted on a different domain.
        */
        kendo.pdf.defineFont({
            "DejaVu Sans": "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans.ttf",
            "DejaVu Sans|Bold": "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Bold.ttf",
            "DejaVu Sans|Bold|Italic": "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Oblique.ttf",
            "DejaVu Sans|Italic": "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Oblique.ttf",
            "WebComponentsIcons": "https://kendo.cdn.telerik.com/2017.1.223/styles/fonts/glyphs/WebComponentsIcons.ttf"
        });
    </script>
<!-- Load Pako ZLIB library to enable PDF compression -->
<script
	src="https://kendo.cdn.telerik.com/2017.3.1026/js/pako_deflate.min.js"></script>


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

#productForm{
	padding:10px;
}
</style>

<script type="text/javascript">
	var contextPath='<%=request.getContextPath()%>';
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/suppliermenu.jsp"></jsp:include>

	<div class="container">


		<div class="panel panel-primary">

			<div class="panel-heading">Create Product</div>
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
						<form action="<%=request.getContextPath()%>/addProduct.do"
							method="post" enctype="multipart/form-data" id="productForm">
							<div class="form-group">
								<label for="productName">Product Name:</label> <input
									type="text" class="form-control" id="productName"
									name="productName"
									placeholder="Product Name" name="productName">
							</div>
							<div class="form-group">
								<label for="productDesc">Product Description:</label>

								<textarea class="form-control" id="productDesc"
									placeholder="Product Description" name="productDesc">
									
									</textarea>
							</div>

							<div class="form-group">
								<label for="price">Product Price:</label> <input type="number"
									class="form-control" id="price" placeholder="Price"
									name="price">
							</div>

							<div class="form-group">
								<label for="productType">Product Type:</label> <select
									class="form-control" name="productType" id="productType">
									<option value="1">Paintings</option>
									<option value="2">Sculpture</option>
								</select>
							</div>

							<div class="form-group">
								<label for="file">Product File:</label> <input type="file"
									name="file" id="file" />
							</div>

							<button type="submit" class="btn btn-primary">Save
								Product</button>
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