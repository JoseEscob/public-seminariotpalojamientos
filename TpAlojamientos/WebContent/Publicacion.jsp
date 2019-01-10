<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ver publicacion</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" />
</head>
<body>
<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet">

<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>${vistaPublicacion.publicacion.nombre}</h2>
		<hr/>
		<div class="row">
			<!--Seccion de las imagenes de la publicacion-->
			<div class="col-md-8">
				<div id="myCarousel" class="carousel slide col-md-6" data-ride="carousel">
						
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
					<a class="left carousel-control" href="#myCarousel" data-slide="prev">
					  <span class="glyphicon glyphicon-chevron-left"></span>
					</a>
					<a class="right carousel-control" href="#myCarousel" data-slide="next">
					  <span class="glyphicon glyphicon-chevron-right"></span>
					</a>
				</div>
			</div>
			<!--Seccion del propietario a la izquierda-->
			<div class="col-md-4">
				<h3>Propietario</h3>
				<hr/>
				<div class="row">
					<div class="col-md-6"><img src="${vistaPublicacion.usuario.rutaFotoPerfil}"
								class="media-object" style="height: 50px; width: 50px;"></div>
					<div class="col-md-6">
						<div class="row">								
							<div class="col-md-12">${vistaPublicacion.usuario.nombre}</div>
							<div class="col-md-12">${vistaPublicacion.usuario.apellido}</div>
							<div class="col-md-12">${vistaPublicacion.usuario.puntaje}&nbsp;/5 Puntos.</div>
							<div class="col-md-6">
								<button class="btn btn-info">Ver perfil</button>
							</div>
						</div>
					</div>
				</div>
				<hr/>
				<button class="btn btn-success">Solicitar reserva</button>
			</div>			
		</div>
		<hr/>
		<!--Seccion de los comentarios abajo-->
		<!--Hay que verificar existencia de comentarios -->
		<div class="row">
			<h2>Comentarios de la publicacion</h2>
			<h4>Puntuacion Gral. ${vistaPublicacion.publicacion.puntaje}&nbsp;/5</h4>
			<div class="col-md-8">
				<c:choose>
					<c:when test="${fn:length(vistaPublicacion.comentarios) gt 0}">
						<c:forEach items="${vistaPublicacion.comentarios}" var="coment">
							<div class="media">
								<div class="media-left media-middle">
								<script type="text/javascript">
									<c:set var="cc" value="coment"/>
									console.log('${cc}');
									console.log("sjdofisdfoi");
								</script>
									<img src="${coment.usuario.rutaFotoPerfilUsuario}"
										class="media-object" style="height: 50px; width: 50px;">
								</div>
								<div class="media-body">
									<h4 class="media-heading">
										<a> <c:out value="${coment.usuario.nombre}"></c:out>
										</a> <small><i> ${coment.comentario.fechaComentario} </i></small>
									</h4>
									<p>
										<b>Puntuación ${coment.comentario.puntaje}&nbsp;/5</b>
									</p>
									<p>${coment.comentario.descripcion}</p>
								</div>
							</div>
							<hr />
						</c:forEach>
					</c:when>
					<c:otherwise>
						No hay comentarios para esta publicacion
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
<%@ include file="Footer.jsp"%>

<script src="bootstrap3/jquery/jquery-3.3.1.js"></script>
<script src="bootstrap3/js/bootstrap.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>
</body>
</html>