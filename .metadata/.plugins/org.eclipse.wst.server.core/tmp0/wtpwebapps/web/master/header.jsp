<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	<div>
		<a class="navbar-brand" href="home">
			<i class="fas fa-desktop fa-2x"></i>
		</a>
	</div>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="header" aria-controls="header" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="header">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<a class="nav-link text-light" href="#">Dashboard</a>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle text-light" style="cursor:pointer" id="settings" data-toggle=dropdown aria-haspopup="true" aria-expanded="false">Settings</a>
				<div class="dropdown-menu" aria-labelledby="settings">
					<a class="dropdown-item" href="#">Configuration Item</a>
				</div>
			</li>
			<sec:authorize access="hasAuthority('ADMIN_PRIVILEGE')">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle text-light" style="cursor: pointer" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Administrator</a>
				<div class="dropdown-menu" aria-labelledby="dropdown03">
					<a class="dropdown-item" href="userlist">Users</a>
					<a class="dropdown-item" href="rolelist">Roles</a>
					<a class="dropdown-item" href="privlist">Privilege</a>
				</div>
			</li>
			</sec:authorize>
		</ul>
		<a class="navbar-brand" href="logout">
			<i class="fas fa-door-open fa-2x"></i>
		</a>
	</div>
</nav>