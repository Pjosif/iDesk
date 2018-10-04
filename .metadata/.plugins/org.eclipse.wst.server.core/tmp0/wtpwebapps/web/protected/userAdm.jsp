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
<link href="./resources/css/table.css" rel="stylesheet">
<title>User Management</title>
</head>
<body>
	<jsp:include page="../master/sidenav.jsp" />
	<div id="main" class="main">
		<jsp:include page="../master/header.jsp" />
		<c:if test="${not empty messageSuccess}">
			<div class="alert alert-success" role="alert">${messageSuccess}</div>
		</c:if>
		<c:if test="${not empty messageFail}">
			<div class="alert alert-danger" role="alert">${messageFail}</div>
		</c:if>
		<div class="mt-5 mx-1">
			<form:form method="GET" action="searchUser" modelAttribute="user">
				<div class="row">
					<div class="col-md-6">
						<form:input type="text" path="firstName" class="form-control" placeholder="First Name" aria-label="First Name" />
					</div>
					<div class="col-md-6">
						<form:input type="text" path="lastName" class="form-control" placeholder="Last Name" aria-label="Last Name" />
					</div>
				</div>
				<div class="row mt-1">
					<div class="col-md-6">
						<form:input type="text" path="username" class="form-control" placeholder="Username" aria-label="Username" />
					</div>
					<div class="col-md-6">
						<form:select class="custom-select" path="roles" multiple="false">
							<form:option value="" label="--Select Role--"></form:option>
							<form:options items="${allRoles}" itemValue="id" itemLabel="name" />
						</form:select>
					</div>
				</div>
				<div class="row mt-1">
					<div class="col-md-2">
						<button type="submit" class="btn btn-dark" style="float: right; width: 100%;">Search</button>
					</div>
				</div>
			</form:form>
		</div>
		<div class="mt-3 mx-1">
			<table class="table table-bordered" id="userTable">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">First Name</th>
						<th scope="col">Last Name</th>
						<th scope="col">Email</th>
						<th scope="col">Username</th>
						<th scope="col">Active</th>
						<th scope="col">Role</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<c:forEach var="emp" items="${list}">
					<tr>
						<th scope="row">${emp.id}</th>
						<td>${emp.firstName}</td>
						<td>${emp.lastName}</td>
						<td>${emp.email}</td>
						<td>${emp.username}</td>
						<td>${emp.active}</td>
						<td>
							<c:forEach var="role" items="${emp.roles }">
								<p class="table-inline">${role.name}</p>
							</c:forEach></td>
						<td class="table-buttons"><a href="deleteUser-${emp.id}">
								<i class="far fa-trash-alt"></i>
							</a> <a href="editUser-${emp.id}">
								<i class="fas fa-pencil-alt"></i>
							</a></td>
					</tr>
				</c:forEach>
			</table>
			<a class="btn btn-outline-primary" href="addUser">Add</a>
		</div>

	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="./resources/js/bootstrap.min.js"></script>
</body>
</html>