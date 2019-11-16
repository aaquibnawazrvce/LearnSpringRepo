<%@page import="com.supplierconsumer.constants.Messages"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page
	import="com.supplierconsumer.model.AJAXResponse,java.util.List,com.supplierconsumer.model.Message"%>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>
	<jsp:include page="/WEB-INF/jsp/adminmenu.jsp"></jsp:include>


	<%
		AJAXResponse ajax = (AJAXResponse) request.getAttribute(Messages.Keys.OBJ);

		if (null == ajax) {

		} else {
	%>
	<div class="container">
		<div class="col-xs-12">
			<div class="alert alert-success">
				<%=ajax.getMessage()%>
			</div>
		</div>
	</div>


	<%
		}
	%>


</body>
</html>