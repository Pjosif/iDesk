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
<title>Role Management</title>
</head>
<body>
	<jsp:include page="../master/sidenav.jsp" />
	<div id="main" class="main">
		<jsp:include page="../master/header.jsp" />
		<c:if test="${not empty messageSuccess}">
			<div class="alert alert-success" role="alert">
					${messageSuccess}
			</div>
		</c:if>
		<c:if test="${not empty messageFail}">
			<div class="alert alert-danger" role="alert">
				${messageFail}
			</div>
		</c:if>
		<div class="mt-5 mx-1">
			<div class="row">
				<div class="col-md-10">
					<input type="text" class="form-control" placeholder="Search" aria-label="Search">
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-dark" style="float: right; width: 100%;">Search</button>
				</div>
			</div>
		</div>
		
		<div class="mt-3 mx-1">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Role</th>
						<th scope="col">Privileges</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<c:forEach var="role" items="${list}">
					<tr>
						<th scope="row">${role.id}</th>
						<td>${role.name}</td>
						<td>
							<c:forEach var="priv" items="${role.privilieges}">
								<p class="table-inline">${priv.name}</p>
							</c:forEach>
						</td>
						<td class="table-buttons"><a href="deleteRole-${role.id}">
								<i class="far fa-trash-alt"></i>
							</a> <a href="editRole-${role.id}">
								<i class="fas fa-pencil-alt"></i>
							</a></td>
					</tr>
				</c:forEach>
			</table>
			<a class="btn btn-outline-primary" href="addRole">Add</a>
		</div>
		
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="./resources/js/bootstrap.min.js"></script>
</body>
</html>