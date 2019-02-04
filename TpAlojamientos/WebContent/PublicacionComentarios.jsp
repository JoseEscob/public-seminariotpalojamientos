<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Publicaci�n - Comentarios</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h4>${message}</h4>
	</div>
	<div class="container">
		<h2>Comentarios de la publicaci�n</h2>
		<h4>Puntuaci�n Gral. ${publicacionPuntaje}&nbsp;/5</h4>
		<c:choose>
			<c:when test="${fn:length(listaComentarios) gt 0}">
				<h4>
					<small>Cant. de Comentarios: ${fn:length(listaComentarios)}</small>
					<br> Ac� se listan los comentarios de las personas que alguna
					vez reservaron este alojamiento
				</h4>
			</c:when>
			<c:otherwise>
				<h4>
					<small>No se encontraron comentarios para esta publicaci�n</small>
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
								<c:url value="PublicacionServlet?" var="urlPerfilPublicoUsuario">
									<c:param name="accionGET" value="verPerfilPublicoOtroUsuario" />
									<c:param name="idUsuario" value="${objComentario.idUsuario}" />
								</c:url>

								<a href="${urlPerfilPublicoUsuario}"> <c:out
										value="${objComentario.nombreApellidoUsuario}"></c:out>
								</a> <small><i> ${objComentario.fechaComentario} </i></small>
							</h4>
							<p>
								<b>Puntuaci�n ${objComentario.puntaje}&nbsp;/5</b>
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