<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="./resources/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
<link href="./resources/css/sidenav.css" rel="stylesheet">
<title>Add Privilege</title>
</head>
<body>
	<jsp:include page="../master/sidenav.jsp" />
	<div id="main" class="main">
		<jsp:include page="../master/header.jsp" />
		<div class="container mt-md-5 ml-2">
			<div class="row">
				<div class="col-md-8">
					<form:form method="post" action="savePrivilege" modelAttribute="priv">
						<div class="d-none">
							<form:input path="id" />
						</div>
						<div class=" form-group row">
							<label for="inputPrivilegeName" class="col-md-3 col-form-label">Privilege Name</label>
							<form:input type="text" id="inputPrivilegeName" path="name" class="form-control" style="width: 40%" placeholder="Enter Privilege" />
							<form:errors path="name" cssClass="col-md-3 col-form-label text-danger"></form:errors>
						</div>
						<button type="submit" class="btn btn-primary btn-lg">Save</button>
						<a href="<c:url value='cancelPrivilege'/>">
						<input type="button" class="btn btn-primary btn-lg" value="Cancel">
						</a>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="./resources/js/bootstrap.min.js"></script>
</body>
</html>