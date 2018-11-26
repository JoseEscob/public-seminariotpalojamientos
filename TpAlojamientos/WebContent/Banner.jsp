<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<!-- BOOTSTRAP ONLINE-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- BOOTSTRAP ONLINE-->
<!-- BOOTSTRAP ONLINE Nuevo-->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- BOOTSTRAP ONLINE Nuevo-->

<title></title>
</head>
<body>
	<nav class="navbar navbar-inverse"> <!-- BEGIN: Menú Superior -->
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="home" title="Ir a la página de Inicio"
				style="color: MEDIUMTURQUOISE;">OwnerRental</a>
		</div>
		<ul class="nav navbar-nav">


			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown"><span
					class="glyphicon glyphicon-chevron-down"></span> Publicaciones</a>
				<ul class="dropdown-menu">
					<li><a href="#"><span
							class="glyphicon glyphicon glyphicon-th" /></span> Mis publicaciones</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-star" /></span>
							Favoritas</a></li>
					<li><a href="#"></a></li>
				</ul></li>
			<li><a href="#"><span class="glyphicon glyphicon-retweet" /></span>
					Solicitudes</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-check" /></span>
					Reservas</a></li>

		</ul>
		<form class="navbar-form navbar-left" action="/action_page.php">
			<div class="input-group">
				<input type="text" class="form-control"
					placeholder="Buscar alojamiento en..." name="search">
				<div class="input-group-btn">
					<button class="btn btn-default" type="submit"
						title="Te permite realizar una búsqueda">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>
		</form>
		<div class="nav navbar-nav navbar-right">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
						<c:out value="${usuario.nombre}" /></a>
					<ul class="dropdown-menu">
						<li><a href="#"><span class="glyphicon glyphicon-user" /></span>
								Mi Perfil</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-cog" /></span>
								Configuraciones</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-off" /></span>
								Salir</a></li>
					</ul></li>
		</div>
	</div>
	</nav>
	<!-- END: Menú Superior -->



	<!-- Jquery JS-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<!-- Vendor JS-->
	<script src="vendor/select2/select2.min.js"></script>
	<script src="vendor/datepicker/moment.min.js"></script>
	<script src="vendor/datepicker/daterangepicker.js"></script>

	<!-- Main JS-->
	<script src="js/global.js"></script>
	<script src="js/JSValidar.js"></script>

</body>
</html>