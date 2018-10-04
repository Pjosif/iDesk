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
<title>Add User</title>
</head>
<body>
	<jsp:include page="../master/sidenav.jsp" />
	<div id="main" class="main">
		<jsp:include page="../master/header.jsp" />
		<div class="container mt-md-5 ml-2">
			<div class="row">
				<div class="col-md-8">
					<form:form method="POST" action="saveUser" modelAttribute="user">
						<div class="d-none">
							<form:input path="id"/>
						</div>
						<div class=" form-group row">
							<label for="inputFirstName" class="col-md-2 col-form-label">First Name</label>
							<form:input type="text" id="inputFirstName" path="firstName" class="form-control" style="width: 40%" placeholder="Enter First Name" />
							<form:errors path="firstName" cssClass="col-md-3 col-form-label text-danger"></form:errors>
						</div>
						<div class=" form-group row">
							<label for="inputLastName" class="col-md-2 col-form-label">Last Name</label>
							<form:input type="text" id="inputLastName" path="lastName" class="form-control" style="width: 40%" placeholder="Enter Last Name" />
							<form:errors path="lastName" cssClass="col-md-4 col-form-label text-danger"></form:errors>
						</div>
						<div class="form-group row">
							<label for="inputEmail" class="col-md-2 col-form-label">Email</label>
							<form:input type="email" id="inputEmail" path="email" class="form-control" style="width: 40%" placeholder="user@example.com" />
							<form:errors path="email" cssClass="col-md-4 col-form-label text-danger"></form:errors>
						</div>
						<div class="form-group row">
							<label for="inputUsername" class="col-md-2 col-form-label">Username</label>
							<form:input type="text" id="inputUsername" path="username" class="form-control" style="width: 40%" placeholder="Enter Username" />
							<form:errors path="username" cssClass="col-md-4 col-form-label text-danger"></form:errors>
						</div>
						<div class="form-group row">
							<label for="inputPassword" class="col-md-2 col-form-label">Password</label>
							<form:input type="password" id="inputPassword" path="password" class="form-control" style="width: 40%" placeholder="Enter Password" />
							<form:errors path="password" cssClass="col-md-4 col-form-label text-danger"></form:errors>
						</div>
						<div class="form-group row">
							<label class="col-md-2 col-form-label">Is Active</label>
						<div class="form-check">
							<form:checkbox cssClass="form-check-input" path="active" value="" id="inputActive" />
						</div>
						</div>
						<div class="form-group row">
							<label for="inputRole" class="col-md-2 col-form-label">Role</label>
							<form:select class="custom-select" style="width: 40%" id="inputRole" path="roles" multiple="true">
								<form:options items="${allRoles}" itemValue="id" itemLabel="name"/>
							</form:select>
							<form:errors path="roles" cssClass="col-md-4 col-form-label text-danger"></form:errors>
						</div>
						<button type="submit" class="btn btn-primary btn-lg">Save</button>
						<a href="<c:url value='cancelUser'/>">
						<input type="button" class="btn btn-primary btn-lg" value="Cancel">
						</a>
					</form:form>
				</div>
				<div class="col-md-4"></div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="./resources/js/bootstrap.min.js"></script>
</body>
</html>