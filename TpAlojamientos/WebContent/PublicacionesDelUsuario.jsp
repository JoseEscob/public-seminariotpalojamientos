<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="extra.ConstantesJSP"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<%=ConstantesJSP.jspPub_PublicacionesListaGrillacss%>"
	rel="stylesheet">
<title>Mis Publicaciones</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>

	<div class="container">
		<div class="row">
			<h2>Mis publicaciones</h2>
			<hr />
		</div>
		<c:choose>
			<c:when test="${empty listaPublicaciones}">
				<div class="alert alert-info">
					<h4>No se encontraron publicaciones cargadas</h4>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row list-group" id="products">
					<c:forEach items="${listaPublicaciones}" var="objPublicacion">
						<div class="item  col-xs-4 col-lg-4">
							<div class="thumbnail">
								<img class="group list-group-image"
									src="imagenes/home-not-found.png" alt="" />
								<!--  <img class="group list-group-image"
							src="http://placehold.it/400x250/000/fff" alt="" />  {objPublicacion.imagenes[0].rutaImgPublicacion}-->
								<div class="caption">
									<h4 class="group inner list-group-item-heading">
										${objPublicacion.nombre}</h4>
									<p class="group inner list-group-item-text">${objPublicacion.descripcion}</p>
									<div class="row">
										<div class="col-xs-12 col-md-6">
											<p class="lead">${objPublicacion.precioNoche}</p>
										</div>

										<c:url value="PublicacionServlet?"
											var="urlPublicacionGuardada">
											<c:param name="accionGET" value="VerPublicacion" />
											<c:param name="idPublicacion"
												value="${objPublicacion.idPublicacion}" />
										</c:url>

										<div class="col-xs-12 col-md-6">
											<a class="btn btn-primary" href="${urlPublicacionGuardada}">Ingresar</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:otherwise>
		</c:choose>
	</div>


</body>
</html>