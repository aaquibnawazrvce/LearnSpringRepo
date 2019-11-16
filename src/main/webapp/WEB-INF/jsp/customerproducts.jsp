<%@page import="com.supplierconsumer.constants.Messages"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>


<%@page import="com.supplierconsumer.model.AJAXResponse"%>
<%@page import="com.supplierconsumer.model.Message"%>
<%@page import="com.supplierconsumer.mongoobjects.Product"%>
<%@page import="java.util.List"%>


<html lang="en">
<head>
<title>Rank Rating</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frameworkjs/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/customcss/custom.css">
<script src="<%=request.getContextPath()%>/frameworkjs/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/frameworkjs/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/customermenu.jsp"></jsp:include>

	<%
		AJAXResponse ajaxResponse = (AJAXResponse) request.getAttribute(Messages.Keys.OBJ);
		if (null == ajaxResponse) {

		} else {
			boolean status = ajaxResponse.isStatus();
	%>
	<%
		if (!status) {
				List<Message> msg = ajaxResponse.getErrMessages();
				if (msg != null & !msg.isEmpty()) {
	%>
	<%
		for (int i = 0; i < msg.size(); i++) {
						Message tempMsg = msg.get(i);
	%>

	<div class="alert alert-danger"><%=tempMsg.getErrMessage()%></div>

	<%
		}
				}

			}
		}

		List<Product> rankedList = (List<Product>) ajaxResponse.getModel();

		if (null == rankedList) {
	%>
	<div class="alert alert-danger">Products Could not Be Found</div>
	<%
		} else {
	%>
	<div class="container">
		<h2>Products List</h2>
		<div class="row">
			<%
				for (Product productModel : rankedList) {
			%>
			<div class="col-lg-4 col-md-12 col-sm-12 col-xs-12">
				<form action="<%=request.getContextPath()%>/buyProduct.do"
					method="post">
					<div class="panel panel-primary panel-main-content">
						<div class="panel-heading"><%=productModel.getProductName()%>
						</div>
						<div class="panel-body">
							<div id="step2_container">
								<div id="step2_container_img">
									<img
										src="<%=request.getContextPath()%>/productimages/<%=productModel.getOriginalFileName()%>"
										width="100px" height="100px" class="product_page_container">
									</img>
								</div>
								<div id="step2_container_desc">
									<%=productModel.getProductDesc()%>
									<input type="hidden" name="productId"
										value="<%=productModel.getProductId()%>" /> 
									<input type="hidden" name="productName"
										value="<%=productModel.getProductName()%>" /> 
									<input type="hidden" name="productType"
										value="<%=productModel.getProductType()%>" /> 
									<input type="hidden" name="supplierName"
										value="<%=productModel.getSupplierName()%>" /> 
									<input type="hidden"
										name="price" value="<%=productModel.getPrice()%>"></input>

								</div>
								<div>
									<strong>Price:<%=productModel.getPrice()%></strong>
								</div>
								<div>
									<label for="quantity">Quantity</label> 
									<input type="text"
										name="quantity" id="quantity" maxlength="2" value="1" />
								</div>
							</div>
							<div id="step2_container">
								<button type="submit" class="btn btn-primary" value="Purchase">Purchase</button>
							</div>
						</div>



					</div>
				</form>
			</div>

			<%
				}
			%>
		</div>
		<%
			}
		%>
	</div>

</body>
</html>