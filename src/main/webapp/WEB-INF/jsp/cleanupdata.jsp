<!DOCTYPE html>
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>
	<jsp:include page="/WEB-INF/jsp/customermenu.jsp"></jsp:include>


	<div class="container">

		<div class="col-xs-12">

			<div class="panel panel-primary">

				<div class="panel-heading">Clean Up Data</div>
				<div class="panel-body">
					<form class="form-horizontal"
						action="<%=request.getContextPath()%>/deleteData.do">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-6 control-label">Please
								Select Data to be Removed</label>
							<div class="col-sm-6">
								<select class="form-control" name="type">
									<option value="TIMETAKEN">TIMETAKEN</option>
									<option value="HOPS">HOPS</option>
									<option value="ENERGYCONSUMED">ENERGYCONSUMED</option>
									<option value="RESIDUALENERGY">RESIDUALENERGY</option>
									<option value="ALIVENODES">ALIVENODES</option>
									<option value="DEADNODES">DEADNODES</option>
									<option value="LTR">Life Time Ratio</option>
									<option value="RO">Routing Overhead</option>
									<option value="THROUGHPUT">Throughput</option>
								</select>

							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-6 col-sm-6">
								<button type="submit" class="btn btn-primary">
									Delete Data
								</button>
							</div>
						</div>


					</form>
				</div>
			</div>
		</div>
	</div>
</body>





</html>