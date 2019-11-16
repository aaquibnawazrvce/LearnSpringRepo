<!DOCTYPE html>
<html>
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<head>
		   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
	
	</head>
<body>
<jsp:include page="/WEB-INF/jsp/customermenu.jsp"></jsp:include>


	<div class="container">

	<div class="col-xs-12">
	
	 <div class="panel panel-primary">

<div class="panel-heading">Configure Topology</div>
	<div class="panel-body">
<form class="form-horizontal" action="<%=request.getContextPath()%>/settings.do">
 <div class="form-group">
    <label for="inputEmail3" class="col-sm-6 control-label">Please Select Your Topology</label>
    <div class="col-sm-6">
     <select class="form-control" name="topologyType">
  <option value="LINEAR">LINEAR</option>
  <option value="RANDOM">RANDOM</option>
	</select>

</div>
</div>

<div class="form-group">
    <div class="col-sm-offset-6 col-sm-6">
      <button type="submit" class="btn btn-primary">Set Topology</button>
    </div>
  </div>


</form>
</div>
</div>
</div>
</div>
</body>





</html>