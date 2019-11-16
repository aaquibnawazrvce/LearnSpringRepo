<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id='cssmenu'>

		<ul>

			<li class='active '><a
				href="<%=request.getContextPath()%>/supplierPage"><span>Supplier
						Home</span></a></li>

			<li class='active '><a
				href="<%=request.getContextPath()%>/supplierProductPage"><span>Supplier
						Product</span></a></li>

			<li class='active '><a
				href="<%=request.getContextPath()%>/viewSupplierProductPage"><span>View
						Products</span></a></li>

			<li class='active '><a
				href="<%=request.getContextPath()%>/viewSupplierTxnData"><span>View
						Supplier Transactions</span></a></li>
						
			<li class='active '><a
				href="<%=request.getContextPath()%>/viewSupplierRevenueByProductType"><span>
						Revenue Product Type</span></a></li>

			<li class='active '><a
				href="<%=request.getContextPath()%>/changePasswordSupplierNav"><span>
						Change Password </span></a></li>



			<li class='active '><a
				href="<%=request.getContextPath()%>/logout.do"><span>Logout</span></a>
			</li>

		</ul>


	</div>
</body>
</html>