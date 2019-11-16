<%@page import="com.supplierconsumer.constants.Messages"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="java.util.List"%>

<%@page
	import="com.supplierconsumer.model.AJAXResponse,
	java.util.List,com.supplierconsumer.model.Message,
	com.supplierconsumer.mongoobjects.OrderInformation
	"%>


<html lang="en">
<head>
<title>Transaction Sucess</title>
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
		AJAXResponse ajax = (AJAXResponse) request.getAttribute(Messages.Keys.OBJ);
		if (null == ajax) {

		} else {

			List<Message> errMsgList = ajax.getErrMessages();
			if (null == errMsgList) {

			} else {
				if(errMsgList!=null && !errMsgList.isEmpty()){
					for(Message msg:errMsgList){
	%>
	<div class="alert alert-danger">

		<%=msg.getErrMessage()%>
	</div>

	<%
					}
				}
		}

			if (ajax.isStatus()) {
	%>
	<div class="alert alert-success">

		<%=ajax.getMessage()%>
	</div>
	<%
		OrderInformation orderInformation = (OrderInformation) ajax.getModel();

				if (null == orderInformation) {

				} else {
					
	%>
				<div class="container">
					<div class="row">
					
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<!-- Panel Start -->
				<div class="panel panel-primary">

					<div class="panel-heading">OrderInformation</div>
					<div class="panel-body">

						<div>
							<h3>Order Id</h3> <%=orderInformation.getOrderid() %>
						</div>
						
						<div>
							<h3>Product Id</h3> <%=orderInformation.getProductid()%>
						</div>
						
						<div>
							<h3>Product Name</h3> <%=orderInformation.getProductname() %>
						</div>
						
						<div>
							<h3>Quantity</h3> <%=orderInformation.getQuantity() %>
						</div>
						
						<div>
							<h3>Price</h3> <%=orderInformation.getTransactioncost() %>
						</div>
					

					</div>
				</div>
					
					</div>
				
				
				</div>
	<%

				}

			}

		}
	%>
</body>
</html>