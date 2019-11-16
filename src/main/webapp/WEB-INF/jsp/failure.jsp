
<%@page import="com.constants.EEDRServerMessages"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page
	import="com.dropsapppro.response.AJAXResponse,java.util.List,com.dropsapppro.response.ErrorMessagesObj"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="<%=request.getContextPath()%>/images/welcome.jpg">
	<jsp:include page="/WEB-INF/jsp/customermenu.jsp"></jsp:include>


	<%
		AJAXResponse ajax = (AJAXResponse) request.getAttribute(EEDRServerMessages.Keys.OBJ);

		if (null == ajax) {

		} else {
			List<ErrorMessagesObj> ebErrors = ajax.getErrMessages();

			if (null == ebErrors) {

			} else {
				ErrorMessagesObj msg = ebErrors.get(0);
	%>
	<div class="container">
		<div class="col-xs-12">
			<div class="alert alert-success">
				<%=msg.getErrMessage()%>
			</div>
		</div>
	</div>
	<%
		}
		}
	%>



</body>
</html>