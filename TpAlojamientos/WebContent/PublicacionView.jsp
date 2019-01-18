<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ver publicacion</title>
</head>
<body>

	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>${vistaPublicacion.publicacion.nombre}</h2>
		<h4>Puntuación Gral.
			${vistaPublicacion.publicacion.puntaje}&nbsp;/5</h4>
		<hr />
		<div class="row col-md-12">
			<!--Seccion de las imagenes de la publicacion-->
			<div class="col-md-8">
				<div id="myCarousel" class="carousel slide col-md-6"
					data-ride="carousel">

					<!-- <ol class="carousel-indicators">
							  <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
							  <li data-target="#myCarousel" data-slide-to="1"></li>
							  <li data-target="#myCarousel" data-slide-to="2"></li>
							</ol> -->
					<!-- Agregar algun comentario dentro de cada imagen? -->
					<!-- Wrapper for slides -->
					<div class="carousel-inner">
						<c:forEach items="${vistaPublicacion.imagenes}" var="imagen">
							<div class="item">
								<img src="${imagen.rutaImgPublicacion}" class="img-responsive">
							</div>
						</c:forEach>
					</div>

					<!-- Left and right controls -->
					<a class="left carousel-control" href="#myCarousel"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left"></span>
					</a> <a class="right carousel-control" href="#myCarousel"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right"></span>
					</a>
				</div>
			</div>
			<!--Seccion del propietario a la izquierda-->


			<div class="col-md-2 well">
				<div class="row col-md-12">
					<c:choose>
						<c:when test="${vistaPublicacion.cantComentarios gt 0}">
							<h4>Comentarios de la publicación:
								${vistaPublicacion.cantComentarios}</h4>
							<form
								action="PublicacionServlet?accionPOST=VerComentarios&idPublicacion=${vistaPublicacion.publicacion.idPublicacion}"
								method="post">
								<button class="btn btn-info">Ver los comentarios de
									esta publicación</button>
							</form>

							<a class="btn btn-primary"
								href="PublicacionServlet?accionPOST=VerComentarios&idPublicacion=${vistaPublicacion.publicacion.idPublicacion}">Ver
								${vistaPublicacion.cantComentarios} Comentarios</a>
						</c:when>
						<c:otherwise>
							<h4>No hay comentarios para esta publicación.</h4>
						</c:otherwise>
					</c:choose>
				</div>


				<div class="row col-md-12">
					<button class="btn btn-success">Solicitar reserva</button>
				</div>
			</div>

			<div class="col-md-2 well" align="center">
				<h4>Propietario</h4>
				<div class="row col-md-12">
					<img src="${vistaPublicacion.usuario.rutaFotoPerfil}"
						class="media-object img-circle" style="height: 50px; width: 50px;">
				</div>
				<div class="row">
					<div class="col-md-12">${vistaPublicacion.usuario.nombre}&nbsp;${vistaPublicacion.usuario.apellido}</div>
					<div class="col-md-12">${vistaPublicacion.usuario.puntaje}&nbsp;/5
						Puntos</div>

				</div>
				<div class="row col-md-12">
					<button class="btn btn-info">Ver perfil</button>
				</div>

			</div>
		</div>
		<hr />
		<!--Seccion de los comentarios abajo-->
		<!--Hay que verificar existencia de comentarios -->



		<div class="row">
			<h3>Descripción</h3>
			<hr />
			<div class="col-md-12">
				<div class="col-md-8 form-group">
					<p>${vistaPublicacion.publicacion.descripcion}</p>
				</div>
			</div>
		</div>

		<div class="row">
			<h3>Características</h3>
			<hr />
			<div class="col-md-12">
				<div class="col-md-8 form-group">
					<p>${vistaPublicacion.publicacion.puntaje}</p>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>


</body>
</html>