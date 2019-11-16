<!DOCTYPE html>
<%@page import="com.supplierconsumer.constants.Messages"%>
<html class="no-js">
<!--<![endif]-->
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="cssmenu/styles.css">
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
    <script src="cssmenu/script.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,800,700' rel='stylesheet' type='text/css'>


    <!-- kendo and angular js -->
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.common.min.css"/>
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.rtl.min.css"/>
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.silver.min.css"/>
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.mobile.all.min.css"/>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2017.2.621/js/angular.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2017.2.621/js/kendo.all.min.js"></script>

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
    <script src="https://kendo.cdn.telerik.com/2017.3.1026/js/pako_deflate.min.js"></script>


    <meta charset="utf-8"/>
    <meta content="text/html charset=UTF-8" http-equiv="Content-Type"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Register</title>
    <meta name="description"
          content="We are a vertically integrated online interior design and furniture company. We custom design home interiors, show you how your new home will look in 3D, manufacture the furniture at our factory and undertake project execution. 5 Year Warranty "/>
    <meta name="keywords"
          content="full house furnishing, full home interiors, home interiors, custom design furniture, interior desinger, interior designs, modular kitchen, furniture online, wardrobes online, furniture online india, bedroom furniture, online furniture, home furniture online, living room furniture, office furniture "/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="icon" href="css/static/images/miscellaneous/favicon.ico?3"/>

    <style>
		
		.productBtn{
			color: #fff!important;
			background-color: #4CAF50!important;
			padding: 8px 16px;
			float: left;
			width: auto;
			border: none;
			display: block;
			outline: 0;
		}

    </style>
    
     <script type="text/javascript" >
	var contextPath='<%=request.getContextPath()%>';
	var app_name_registered='<%=Messages.Keys.APP_NAME%>';
	</script>

	   <script src="<%=request.getContextPath()%>/customjs/productCreationApp.js"></script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/adminmenu.jsp"></jsp:include>

<div class="container" ng-app="productCreationApp" id="contentContainer">


 <div class="panel panel-primary">
        
    <div class="panel-heading">Create Product</div>
	<div class="panel-body">

    <div ng-controller="productCreationController" class="main-content-wrapper">

	    <!-- Start of Error Messages -->
        <div class="row">
            <div class="alert alert-success" ng-show="showSucess">{{ sucessMsg }}</div>
            <div class="alert alert-danger" ng-show="showError">{{ errorMsg }}</div>
        </div>
		<!-- End of Error Messages -->
		
		<!-- Start of Row -->
		 <div class="row">
		 
			<!--- Product Name -->
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">

                <div class="form-group">
                    <label for="productName"> Product Name:</label>
                    <input type="text" class="form-control" id="productName"
                           ng-disabled="data_submission" ng-required="true"
                           name="productName" ng-model="register.productName" maxlength="20">
                </div>
            </div>
			<!--- User Name -->
			
			 <!--- Product Description-->
			<div class="col-lg-4 col-md-4 col-xs-12  col-sm-12">
         	   
                
                <div class="form-group">
                    <label for="productDesc"> Product Description:</label>
                    
                    <textarea id="productDesc" name="productDesc"
                    rows="5" cols="50" ng-model="register.productDesc"
                    ng-disabled="data_submission" ng-required="true"
                    ></textarea>
                    
                    
                </div>
                
            </div>
			
			
         
			
			<!--- Email -->
	
			
		 
		 </div> <!-- End of 1st Row -->
		
		 <div class="row"> <!-- Start of 2nd Row -->
		 
		 	<!-- price_per_unit -->
            <div class="col-lg-4 col-md-4 col-xs-12  col-sm-12">
                
                <div class="form-group">
                    <label for="price_per_unit">Price Per Unit:</label>
                    <input type="text" class="form-control" id="price_per_unit" name="pricePerUnit"
                           ng-disabled="data_submission"
                           ng-model="register.pricePerUnit"
                           maxlength="15" ng-required="true">
                </div>
            </div>
			<!-- price_per_unit -->
			
		
			<!-- ProductTypeName -->
			
			<div class="col-lg-4 col-md-4 col-xs-12  col-sm-12">

                <div class="form-group">
                <label for="country">ProductTypeName</label>
                <select class="form-control" id="productTypeName" name="productTypeName" ng-model="register.productTypeName" ng-options="x for x in productTypeNames">
				</select>
				
                </div>
				
            </div>
			
			<!-- ProductTypeName -->
			
		 
		 
		 
		 
		 </div><!-- End of 2nd Row -->
		
		<div class="row"><!-- Start of 4th Row -->
		
			<div class="col-lg-4 col-md-4 col-sm=12 col-xs-12">
				</div>
			
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
		
				<button type="submit" class="productBtn" ng-click="registerProduct()">Add Product</button>
			
			</div>
			
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				<a class="productBtn" href="<%=request.getContextPath()%>/adminPage">Home</a>
			</div>
		
		</div> <!-- End of 4th Row -->
		
		
       

    </div> <!-- Closing of Controller -->
    
	</div> <!-- Closing of Panel Body -->

	</div><!-- Closing of Panel primary -->
	
</div> 
</body>