<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/extjs41/resources/css/ext-all.css" />
<script type="text/javascript" >
var contextPath='<%=request.getContextPath()%>';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/extjs41/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/customizedjs/viewsuppliertransactions.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/suppliermenu.jsp"></jsp:include>

<div id="content">

<div id="confirmationMessage"></div>
<div id="ErrorDiv" ></div>
<div id="buttonDiv"></div>

<div id="contentDiv" ></div>
<div id="AddDiv"> </div>
<div id="reviewGridContainer">

</div>
</div>

</body>
</html>