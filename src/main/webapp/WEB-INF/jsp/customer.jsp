<!DOCTYPE html>
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


<body>

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
	%>


	<jsp:include page="/WEB-INF/jsp/customermenu.jsp"></jsp:include>
	<div class="container">

		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

				<!-- Panel Start -->
				<div class="panel panel-primary">

					<div class="panel-heading">Paintings</div>
					<div class="panel-body">

						<div>
							<img
								src="<%=request.getContextPath()%>/productypeimages/painting.jpg"
								 class="product_page1_container">
							</img>
						</div>

						<div>

							<a href="<%=request.getContextPath()%>/specProduct.do?id=1"
								class="btn btn-primary">Paintings</a>
						</div>

					</div>
				</div>
				<!-- Panel End -->

			</div>

			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

				<!-- Panel Start -->
				<div class="panel panel-primary">

					<div class="panel-heading">Sculpture</div>
					<div class="panel-body">

						<div>
							<img
								src="<%=request.getContextPath()%>/productypeimages/scripture.jpg"
							 class="product_page1_container">
							</img>
						</div>

						<div>

							<a href="<%=request.getContextPath()%>/specProduct.do?id=2"
								class="btn btn-primary">Sculpture</a>
						</div>

					</div>
				</div>
				<!-- Panel End -->

			</div>

		

		</div>



	</div>
</body>
</html>