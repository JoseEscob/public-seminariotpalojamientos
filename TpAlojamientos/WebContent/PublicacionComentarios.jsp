<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Publicación - Comentarios</title>
<%@ include file="Bootstrap.jsp"%>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h4>${message}</h4>
	</div>
	<!--https://www.w3schools.com/howto/howto_css_login_form.asp-->
	<!--https://www.w3schools.com/w3css/tryit.asp?filename=tryw3css_lists_avatar-->
	<!--https://www.w3schools.com/howto/howto_css_profile_card.asp-->
	<!--https://www.w3schools.com/howto/howto_css_rounded_images.asp-->
	<div class="container">
		<h2>Comentarios de la publicación</h2>
		<h4>Puntuación Gral. ${publicacionPuntaje}&nbsp;/5</h4>
		<c:choose>
			<c:when test="${fn:length(listaComentarios) gt 0}">
				<h4>
					<small>Cant. de Comentarios: ${fn:length(listaComentarios)}</small>
				</h4>
			</c:when>
			<c:otherwise>
				<h4>
					<small>No se encontraron comentarios para esta publicación</small>
				</h4>
			</c:otherwise>
		</c:choose>
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
		<div class="row"></div>
	</div>


</body>
</html>