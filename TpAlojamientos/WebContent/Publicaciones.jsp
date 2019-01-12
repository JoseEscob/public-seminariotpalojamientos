<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Publicaciones</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Publicaciones</h2>
		<c:if test="${fn:length(publicaciones) gt 1 }">
			<h5>Se encontraron <c:out value="${fn:length(publicaciones)}"/> publicaciones.</h5>
		</c:if>
		<hr/>
		<div class="col-md-3">
			Filtros
		</div>
		<div class="col-md-9">
			<!--Foreach, para la paginacion hay que traer primero todos los datos, despues dividirlo por la cantidad de registros que vamos a mostrar y te daria la cantidad de paginas-->
			<c:choose>
				<c:when test="${fn:length(publicaciones) gt 0}">
					<c:forEach items="${publicaciones}" var="vistaPublicacion">
						<div class="row">
							<div class="col-md-6">
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
							<div class="col-md-6">
								<h3>${vistaPublicacion.publicacion.nombre}</h3>
								<hr/>
								{datos de la publicacion}
								{puntuacion}
								<div class="row container">
										<a class="btn btn-info" href="PublicacionServlet?accionGET=VerPublicacion&idPublicacion=${vistaPublicacion.publicacion.idPublicacion}">Ver publicacion</a>
								</div>
							</div>
						</div>
						<hr/>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="alert alert-info">
						<h4>No se han encontrado publicaciones</h4>
					</div>
				</c:otherwise>				
			</c:choose>
			
			<c:choose>
				<c:when test="${not empty paginacion}">
					<ul class="pagination">
						<c:forEach var="item" begin="1" end="${paginacion.paginas}">
							<c:choose>
								<c:when test="${item eq paginacion.paginaActual}">
									<li class="active"><a href="#"><c:out value="${item}"/></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="#"><c:out value="${item}"/></a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					NO.
				</c:otherwise>
			</c:choose>

		</div>
		
	</div>
<%@ include file="Footer.jsp"%>

</body>
</html>