<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h2>{nombre o titulo publicacion}</h2>
		<hr/>
		<div class="row">
			<!--Seccion de las imagenes de la publicacion-->
			<div class="col-md-8">
				{carrousel}
			</div>
			<!--Seccion del propietario a la izquierda-->
			<div class="col-md-4">
				<h3>Propietario</h3>
				<hr/>
				<div class="row">
					<div class="col-md-6">{foto de perfil}</div>
					<div class="col-md-6">
						<div class="row">								
							<div class="col-md-12">{nombre propietario}</div>
							<div class="col-md-6">{puntuacion}</div>
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
			<h4>Puntuacion Gral. {puntaje de la publicacion}&nbsp;/5</h4>
			<div class="col-md-8">
				<c:forEach items="${listaComentarios}" var="objComentario">
					<div class="media">
						<div class="media-left media-middle">
							<img src="${objComentario.rutaFotoPerfilUsuario}"
								class="media-object" style="height: 50px; width: 50px;">
						</div>
						<div class="media-body">
							<h4 class="media-heading">
								<a> <c:out value="${objComentario.nombreApellidoUsuario}"></c:out>
								</a> <small><i> ${objComentario.fechaComentario} </i></small>
							</h4>
							<p>
								<b>Puntuación ${objComentario.puntaje}&nbsp;/5</b>
							</p>
							<p>${objComentario.descripcion}</p>
						</div>
					</div>
					<hr />
				</c:forEach>
			</div>
		</div>
	</div>
<%@ include file="Footer.jsp"%>

<script src="bootstrap3/jquery/jquery-3.3.1.js"></script>
<script src="bootstrap3/js/bootstrap.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>
</body>
</html>