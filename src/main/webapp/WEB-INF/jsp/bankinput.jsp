<%@page import="com.supplierconsumer.constants.Messages"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<head>
<title>Budget Rating</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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

<body>
	<jsp:include page="/WEB-INF/jsp/customermenu.jsp"></jsp:include>


	<%
		AJAXResponse ajax = (AJAXResponse) request.getAttribute(Messages.Keys.OBJ);

		if (null == ajax) {

		} else {

			List<Message> msg = ajax.getErrMessages();
			if (null == msg) {

			} else {
				Message errMsg = msg.get(0);
	%>
	<div class="alert alert-danger"><%=errMsg.getErrMessage()%></div>
	<%
		}

		}
	%>




	<div class="container">
		<h1>Payments Engine</h1>
		<div class="col-lg-4 col-md-12 col-sm-12 col-xs-12">
			<div class="panel panel-primary panel-main-content">
				<div class="panel-heading">Make Payment</div>
				<div class="panel-body">
					<form action="<%=request.getContextPath()%>/banking.do"
						method="post">

						<div class="form-group">
							<label for="cardNumber">Account Number:</label> <input
								name="accountNo" id="accountNo" class="form-control" type="text"
								size="40" maxlength="40" />
						</div>

						<div class="form-group">
							<label for="password">IPIN:(Password)</label> <input
								name="password" id="password" type="password"
								class="form-control" size="40" maxlength="40" />
						</div>

						<div class="form-group">
							<label for="deliveryaddress">Delivery Address:</label>
							<textarea class="form-control" rows="5" name="deliveryaddress" id="deliveryaddress"></textarea>
						</div>

						<div class="form-group">

							<button type="submit" class="btn btn-primary" value="Apply">PAY</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
</body>
</html>