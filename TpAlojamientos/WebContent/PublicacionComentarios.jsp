<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Publicación - Comentarios</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>


	<div class="container">
		<h2>Comentarios de la publicación</h2>
		<h4>
			<b>Puntuación General ${objPublicacion.puntaje}&nbsp;/5</b>
		</h4>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<c:forEach items="${listaComentarios}" var="objComentario">
					<div class="media">
						<div class="media-left media-middle">
							<img src="${objComentario.rutaFotoPerfilUsuario}"
								class="media-object" style="height: 50px; width: 50px;">
						</div>
						<div class="media-body">
							<h4 class="media-heading">
								<a> <c:out value="${objComentario.nombreApellidoUsuario}"></c:out>
								</a>
							</h4>
							<p>
								<b>Puntuación ${objComentario.puntaje}&nbsp;/5</b>
								<text style="color:SILVER;">
								${objComentario.fechaComentario}</text>
							</p>
							<p>${objComentario.descripcion}</p>
						</div>
					</div>
					<div class="page-header" />
				</c:forEach>
			</div>

		</div>
		<div class="row"></div>
	</div>
</body>
</html>