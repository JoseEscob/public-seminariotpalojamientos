<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="modelo.Usuario, extra.Tag, extra.Constantes"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio - Alojamientos</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>

	<div class="container" style="background-color: LIGHTSLATEGRAY;">
		<div class="jumbotron" style="background-color: white;">
			<h1>Hola&nbsp;${sessionScope.sessionUser.nombre}&nbsp;${sessionScope.sessionUser.apellido}</h1>
		</div>
		<div class="jumbotron">
			<h1>Alojamientos en Bs As</h1>
			<p>Descubrí nuestro catálogo de alojamientos que ofrecen nuestros
				usuarios</p>

			<a class="btn-lg btn-primary"
				href="PublicacionServlet?accionGET=VerPublicaciones"
				data-toggle="tooltip" title=""> <span
				class="glyphicon glyphicon-check" /></span>&nbsp;Ver publicaciones de
				alojamientos
			</a>
		</div>
		<div class="jumbotron" style="background-color: white;">
			<h1>Publicá tu alojamiento</h1>
			<p>Te interesa convertirte tu alojamiento Te invitamos a cargar
				tu alojamiento en nuestro sistema</p>
			<p>Ingrese a su listado de publicaciones creadas o guardadas como
				favoritas</p>

			<button class="btn-lg btn-primary"
				href="PublicacionServlet?accionGET=VerPublicaciones"
				data-toggle="tooltip" title="">
				<span class="glyphicon glyphicon-eye-open" /></span> &nbsp;Ver mis
				publicaciones
			</button>
		</div>

		<div class="jumbotron">
			<h1>Listado de Favoritos</h1>
			<p>Guarde las publicaciones que le interesan como favoritos para
				poder consultarlas en un futuro</p>
			<p>Ingrese a su listado de publicaciones creadas o guardadas como
				favoritas</p>

			<button class="btn-lg btn-primary"
				href="PublicacionServlet?accionGET=VerPublicaciones"
				data-toggle="tooltip" title="">
				<span class="glyphicon glyphicon-eye-open" /></span> &nbsp;Ver mis
				publicaciones
			</button>
		</div>
	</div>

</body>
</html>