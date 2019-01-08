<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta charset="utf-8" />

<script src="login/js/JSValidar.js"></script>
<!-- BOOTSTRAP ONLINE
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	BOOTSTRAP ONLINE
-->
<!-- BOOTSTRAP ONLINE Nuevo
	<link
		href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
		rel="stylesheet" id="bootstrap-css">
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
BOOTSTRAP ONLINE Nuevo 
-->
<!-- 1) css, 2)jquery, 3)bootstrap js -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="bootstrap3/jquery/jquery-3.3.1.js"></script>
<script src="bootstrap3/js/bootstrap.min.js"></script>
<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet">

<!--ESTO ES PARA QUE ANDEN LOS COMBOBOX CON EL CUATRO DE BUSQUEDA QUE LLEVAN INTEGRADO-->
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" />
<script	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>
<title></title>
</head>
<body>
	<nav class="navbar navbar-inverse"> <!-- BEGIN: Menú Superior -->
	<div class="container-fluid">



		<!-- Image and text 
		<nav class="navbar navbar-light bg-light"> <a
			class="navbar-brand" href="Inicio.jsp"
			style="color: MEDIUMTURQUOISE;"> <img
			src="imagenes/icon-home.jpg" width="30" height="30"
			class="d-inline-block align-top" alt="Home"> OwnerRental
		</a> </nav>-->
		<div class="navbar-header">
			<a class="navbar-brand" href="Inicio.jsp"
				style="color: MEDIUMTURQUOISE;"> <img
				src="imagenes/icon-home.jpg" width="30" height="30"
				class="d-inline-block align-top" alt="Home">OwnerRental
			</a>
		</div>
		<!--
		<div class="navbar-header">
			<a class="navbar-brand" href="Inicio.jsp"
				title="Ir a la página de Inicio" style="color: MEDIUMTURQUOISE;"><img
				alt="Home" src="imagenes/icon-home.jpg"
				style="width: 100px; height: 80px"> </a>
		</div>
		-->
		<ul class="nav navbar-nav">


			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown"><span
					class="glyphicon glyphicon-chevron-down"></span> Publicaciones</a>
				<ul class="dropdown-menu">

					<li><a href="#"><span
							class="glyphicon glyphicon glyphicon-th" /></span> Mis publicaciones</a></li>

					<li><a href="PublicacionServlet?accionGET=Nuevo"> Nueva
							Publicación</a></li>
					<li><a
						href="PublicacionServlet?accionGET=VerComentarios&idPublicacion=1">
							Test Comentario</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-star" /></span>
							Favoritas</a></li>
					<li><a href="#"></a></li>
				</ul></li>
			<li><a href="SolicitudServlet"><span class="glyphicon glyphicon-retweet" /></span>
					Solicitudes</a></li>
			<li><a href="ReservaServlet"><span class="glyphicon glyphicon-check" /></span>
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
						<c:out value="${sessionScope.usuario.nombre}" /></a>
					<ul class="dropdown-menu">

						<li><a><span class="glyphicon glyphicon-user" /></span>
								<form method="post" action="UsuarioServlet">
									<input type="hidden" id="buscarAction" name="buscarAction"
										value="verInfoUsuario"></input> <input type="submit"
										name="btnNuevoUsuario" value="Mi Perfil"></input>
								</form> </a></li>

						<li><a href="UsuarioServlet?accionGET=MiPerfil"><span
								class="glyphicon glyphicon-user" /></span> Ver mi Perfil</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-cog" /></span>
								Configuraciones</a></li>
						<li><a href="UsuarioServlet?accionGET=Logout"><span
								class="glyphicon glyphicon-off" /></span> Salir</a></li>
					</ul></li>
		</div>
	</div>
	</nav>
	<!-- END: Menú Superior -->

</body>
</html>