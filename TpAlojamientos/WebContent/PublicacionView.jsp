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
		<div class="row">
		<h4>${message}</h4>
		</div>
		<div class="row">
			<h2>${vistaPublicacion.publicacion.nombre}</h2>
			<hr />
			<!--Seccion de las imagenes de la publicacion-->
			<div class="col-md-8">
				<div id="myCarousel" class="carousel slide col-md-6" data-ride="carousel">
					<ol class="carousel-indicators">
							  <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
							  <li data-target="#myCarousel" data-slide-to="1"></li>
							  <li data-target="#myCarousel" data-slide-to="2"></li>
							</ol>
					<!-- Agregar algun comentario dentro de cada imagen? -->
					<!-- Wrapper for slides -->
					<div class="carousel-inner">
						<c:forEach items="${vistaPublicacion.listaRutaImg}" var="objRutaImg">
							<div class="item">
								<!--<img src="${objRutaImg}" class="img-responsive"> -->
								<img src="imagenes\publicaciones\Publicacion_1\1.jpg" class="img-responsive"> 
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
			<div class="col-md-4 well">
				<div class="row col-md-12">
					<h3>Precio por Noche: <b>$&nbsp;${vistaPublicacion.publicacion.precioNoche}</b></h3>
				</div>
				<div class="row col-md-12">
					<h4>
						<span class="glyphicon glyphicon-stats"/></span>
						Puntuación Gral. ${vistaPublicacion.publicacion.puntaje}&nbsp;/5
					</h4>
					<div class="col-md-6 col-md-12">
						<label class="control-label">
							ID Publicación: ${vistaPublicacion.publicacion.idPublicacion}
						</label>
					</div>
					<div class="col-md-6 col-md-12">
						<c:if test="${vistaPublicacion.publicacion.isVerificado() eq true}">
							<label class="control-label pull-right"> <span
								class="glyphicon glyphicon-ok-circle"></span> Publicación Verificada
							</label>
						</c:if>
					</div>
					
					
					<div class="col-md-12 col-md-12">
						<label class="control-label">
							Fecha publicada: ${vistaPublicacion.publicacion.fechaAlta}
						</label>
					</div>
				</div>
				<div class="form-group">
						<label class="control-label col-xs-4">Ubicado en: </label>
						<label class="control-label col-xs-8">${vistaPublicacion.publicacion.calle} al ${vistaPublicacion.publicacion.altura}</label>
						
						<label class="control-label col-xs-4">Partido de: </label>
						<label class="control-label col-xs-8">${vistaPublicacion.objLocalidad.nombrePartido}</label>
						
						<label class="control-label col-xs-4">Localidad: </label>
						<label class="control-label col-xs-8">${vistaPublicacion.objLocalidad.nombre}</label>
						
						<label class="control-label col-xs-4">Cód. Postal: </label>
						<label class="control-label col-xs-8">${vistaPublicacion.objLocalidad.codPostal}</label>
				</div>
				<div class="row col-md-12">
					<br>
				</div>
				<div class="row col-md-12">
					<div class="row col-md-6 pull-left">
						<c:choose>
							<c:when test="${vistaPublicacion.cantComentarios gt 0}">
								<c:url value="PublicacionServlet?" var="urlPublicacionComentarios">
					                <c:param name="accionPOST" value="VerComentarios"/>
					                <c:param name="idPublicacion" value="${vistaPublicacion.publicacion.idPublicacion}"/>
					            </c:url>
								<form method="post" action="${urlPublicacionComentarios}">
									<button class="btn btn-default">Ver ${vistaPublicacion.cantComentarios} Comentarios</button>
								</form>
							</c:when>
							<c:otherwise>
								<button class="btn btn-default" title="Esta publicación aún no tiene comentarios. Dejá el tuyo">Dejar un Comentario</button>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="row col-md-6 pull-right">
						<c:choose>
							<c:when test="${vistaPublicacion.objFavorito.isHabilitado()}">
								<c:url value="PublicacionServlet?" var="urlEliminarPublicacionFav">
										<c:param name="accionPOST" value="GestionarFavoritos"/>
										<c:param name="idPublicacion" value="${vistaPublicacion.publicacion.idPublicacion}"/>
										<c:param name="agregaAFavoritos" value="false"/>
								</c:url>
								<form method="post" action="${urlEliminarPublicacionFav}">
									<button class="btn btn-danger" title="Esta publicación ya no aparecerá en tu lista de favoritos">
										<span class="glyphicon glyphicon-heart"/></span>&nbsp;Eliminar de favoritos
									</button>
								</form>
							</c:when>
							<c:otherwise>
								<c:url value="PublicacionServlet?" var="urlGuardarPublicacionFav">
										<c:param name="accionPOST" value="GestionarFavoritos"/>
										<c:param name="idPublicacion" value="${vistaPublicacion.publicacion.idPublicacion}"/>
										<c:param name="agregaAFavoritos" value="true"/>
								</c:url>
								<form method="post" action="${urlGuardarPublicacionFav}">
									<button class="btn btn-primary" title="Guardar esta publicación para verla más tarde">
										<span class="glyphicon glyphicon-heart"/></span>&nbsp;Agregar a favoritos
									</button>
								</form>
								
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="row col-md-12">
					<br>
				</div>
				<div class="row col-md-12" align="center">
					<button class="btn btn-success btn-lg">Solicitar una reserva</button>
				</div>
			</div>
		</div>



		<div class="row">
			<h3>Descripción</h3>
			<hr />
			<div class="col-md-8">
				<div class="form-group">
					<p>${vistaPublicacion.publicacion.descripcion}</p>
				</div>
			</div>
			
			<div class="col-md-4 well" align="center">
				<h4>Datos del Propietario</h4>
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

		<div class="row">
			<h3>Características</h3>
			<hr />
			<div class="col-md-12">
				<div class="form-group col-md-8">
					<div class="col-md-6">
						<p>Cant. Personas: <b>${vistaPublicacion.publicacion.cantPersonas}</b></p>
						<p>Cant. Ambientes: <b>${vistaPublicacion.publicacion.cantAmbientes}</b></p>
						<p>Cant. Banios:  <b>${vistaPublicacion.publicacion.cantBanios}</b></p>
						<p>Cant. Habitaciones: <b>${vistaPublicacion.publicacion.cantHabitaciones}</b></p>
						<p>Años de Antiguedad: <b>${vistaPublicacion.publicacion.aniosAntiguedad}</b></p>
					</div>
					<div class="col-md-6">
						<p>Superficie Cubierta(m²): <b>${vistaPublicacion.publicacion.supCubierta}</b></p>
						<p>Superficie Descubierta(m²): <b>${vistaPublicacion.publicacion.supDescubierta}</b></p>
						<c:choose>
							<c:when test="${vistaPublicacion.publicacion.chkExpensas}">
								<p>Tiene expensas : <b>Si</b></p>
								<p>Precio expensas: <b>$&nbsp;${vistaPublicacion.publicacion.precioExpensas}</b></p>
							</c:when>
							<c:otherwise>
								<p>Tiene expensas: <b>No</b></p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	
		<div class="row">
			Imagenes leidas
			<c:forEach items="${vistaPublicacion.listaRutaImg}" var="objRutaImg">
			<label class="control-label col-xs-12">${objRutaImg}</label>
			</c:forEach>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>


</body>
</html>