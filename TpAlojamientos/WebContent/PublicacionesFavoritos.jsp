<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mis Favoritos</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Mis Publicaciones Favoritas</h2>
		<hr />

		<!--Horizantal line divider  <div class="page-header"/> -->
	</div>

	<div class="container">
		<c:choose>
			<c:when test="${empty listadoDePublicaciones}">
				<div class="alert alert-info">
					<h4>No se encontraron publicaciones cargadas</h4>
				</div>
			</c:when>
			<c:otherwise>
				<h4>Tenés ${fn:length(listadoDePublicaciones)} publicaciones
					guardadas como favoritas. Apuráte todavía están disponibles para
					reservarlas</h4>
				<br>
				<div class="row container">
					<%@ include file="mostrarListadoDePublicaciones.jsp"%>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>