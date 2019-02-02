<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="extra.ConstantesJSP"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mis Favoritos</title>
<link href="<%=ConstantesJSP.jspPub_PublicacionesListaGrillacss%>"
	rel="stylesheet">

<script type="text/javascript">
	$(document).ready(function() {
		$('#list').click(function(event) {
			event.preventDefault();
			$('#products .item').addClass('list-group-item');
		});
		$('#grid').click(function(event) {
			event.preventDefault();
			$('#products .item').removeClass('list-group-item');
			$('#products .item').addClass('grid-group-item');
		});
	});
</script>

</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Mis Publicaciones Favoritos</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
	</div>

	<div class="container">
		<div class="row">
			<div class="well well-sm">
				<strong>Category Title</strong>
				<div class="btn-group">
					<a href="#" id="list" class="btn btn-default btn-sm"><span
						class="glyphicon glyphicon-th-list"> </span>List</a> <a href="#"
						id="grid" class="btn btn-default btn-sm"><span
						class="glyphicon glyphicon-th"></span>Grid</a>
				</div>
			</div>
		</div>

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

								<c:url value="PublicacionServlet?" var="urlPublicacionGuardada">
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
	</div>
</body>
</html>