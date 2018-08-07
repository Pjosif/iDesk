<div id=sideNav class=sidenav>
	<div class="navbar-dark">
		<button class="navbar-toggler" type="button" onclick="togleNav()">
			<span class="navbar-toggler-icon"></span>
		</button>
	</div>
	<ul>
		<li>
			<a href="#">
				<i class="fas fa-chalkboard-teacher"></i>Incident
			</a>
		</li>
		<li>
			<a href="#">
				<i class="fas fa-chalkboard-teacher"></i>Request
			</a>
		</li>

		<!-- 		<a href="#"><i class="fas fa-chalkboard-teacher"></i>MENU 3</a> -->
		<!-- 		<a href="#"><i class="fas fa-chalkboard-teacher"></i>MENU 4</a> -->
	</ul>
</div>
<script>
	function togleNav() {
		var navigator = document.getElementById("sideNav");
		if (navigator.style.width === "200px") {
			closeNav();
		} else {
			openNav();
		}
	}

	function openNav() {
		document.getElementById("sideNav").style.width = "200px";
		document.getElementById("main").style.marginLeft = "200px";
	}

	function closeNav() {
		document.getElementById("sideNav").style.width = "60px";
		document.getElementById("main").style.marginLeft = "60px";
	}
</script>