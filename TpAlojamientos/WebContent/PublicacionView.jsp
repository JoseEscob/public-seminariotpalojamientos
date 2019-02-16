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
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
		<div class="row" title="Ubicación - Publicación">

			<h2>${vistaPublicacion.publicacion.nombre}</h2>
			<div class="h3" style="color: SLATEGRAY;">
				<ul class="list-inline">
					<li>Zona: <b>${vistaPublicacion.objLocalidad.nombre}</b></li>
					<li>Pdo de <b>${vistaPublicacion.objLocalidad.nombrePartido}</b></li>
					<!-- ${fn:replace(vistaPublicacion.objLocalidad.nombrePartido,"?","")} -->
				</ul>
			</div>
			<hr />
			<!--Seccion de las imagenes de la publicacion-->
			<div class="col-md-8" align="center">
				<c:choose>
					<c:when test="${not empty vistaPublicacion.imagenes}">
						<div id="myCarousel" class="carousel slide col-md-10"
							data-ride="carousel">
							<!-- <ol class="carousel-indicators" col-md-6>
										  <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
										  <li data-target="#myCarousel" data-slide-to="1"></li>
										  <li data-target="#myCarousel" data-slide-to="2"></li>
								</ol>->
								<!-- Agregar algun comentario dentro de cada imagen? -->
							<!-- Wrapper for slides -->
							<div class="carousel-inner">
								<c:forEach items="${vistaPublicacion.imagenes}" var="objImagen">
									<c:choose>
										<c:when
											test="${objImagen.idImagen eq vistaPublicacion.imagenes[0].idImagen }">
											<div class="item active">
												<img src="${objImagen.rutaImgPublicacion}">
												<!-- <img src="imagenes\publicaciones\Publicacion_1\1.jpg" class="img-responsive"> -->
											</div>
										</c:when>
										<c:otherwise>
											<div class="item">
												<img src="${objImagen.rutaImgPublicacion}">
												<!-- <img src="imagenes\publicaciones\Publicacion_1\1.jpg" class="img-responsive"> -->
											</div>
										</c:otherwise>
									</c:choose>
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
					</c:when>
					<c:otherwise>
						<div class="row alert alert-info">No se encontraron imagenes
							para esta publicación.</div>
					</c:otherwise>
				</c:choose>
			</div>

			<!--Seccion del propietario a la izquierda-->
			<div class="col-md-4 well">
				<div class="row col-md-12">
					<h3>
						Precio por Noche: <b>$&nbsp;${vistaPublicacion.publicacion.precioNoche}</b>
					</h3>
				</div>
				<div class="row col-md-12">
					<h4>
						<span class="glyphicon glyphicon-stats" /></span> Puntuación Gral.
						${vistaPublicacion.publicacion.puntaje}&nbsp;/5
					</h4>
					<div class="row">
						<div class="col-md-6 col-md-12">
							<ul class="list-inline">
								<li><b>ID Publicación:</b></li>
								<li><b>${vistaPublicacion.publicacion.idPublicacion}</b></li>
							</ul>
						</div>
						<div class="col-md-6 col-md-12">
							<c:if
								test="${vistaPublicacion.publicacion.isVerificado() eq true}">
								<label class="control-label pull-right"
									style="color: ROYALBLUE;" data-toggle="tooltip"
									title="Esta Publicación fue verificada por un administrador">
									<span class="glyphicon glyphicon-ok-sign"></span> Publicación
									Verificada
								</label>
							</c:if>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-md-12">
							<ul class="list-inline">
								<li><b>Fecha publicada: </b></li>
								<li><b>${vistaPublicacion.publicacion.fechaAlta}</b></li>
							</ul>

							<ul class="list-inline">
								<li><b>Tipo de Alojamiento: </b></li>
								<li><b>${vistaPublicacion.descripcionTipoAlojamiento}</b></li>
							</ul>

							<ul class="list-inline">
								<li><b>Antigüedad: </b></li>
								<c:choose>
									<c:when
										test="${vistaPublicacion.publicacion.aniosAntiguedad eq 0}">
										<li><b>A Estrenar</b></li>
									</c:when>
									<c:when
										test="${vistaPublicacion.publicacion.aniosAntiguedad eq 1}">
										<li><b>${vistaPublicacion.publicacion.aniosAntiguedad}&nbsp;año</b>
										</li>
									</c:when>
									<c:otherwise>
										<li><b>${vistaPublicacion.publicacion.aniosAntiguedad}&nbsp;años</b>
										</li>
									</c:otherwise>
								</c:choose>

							</ul>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<label class="control-label col-xs-4">Ubicado en: </label> <label
							class="control-label col-xs-8">${vistaPublicacion.publicacion.calle}
							al ${vistaPublicacion.publicacion.altura}</label>
					</div>
					<div class="row">
						<label class="control-label col-xs-4">Partido de: </label> <label
							class="control-label col-xs-8">${vistaPublicacion.objLocalidad.nombrePartido}</label>
					</div>
					<div class="row">
						<label class="control-label col-xs-4">Localidad: </label> <label
							class="control-label col-xs-8">${vistaPublicacion.objLocalidad.nombre}</label>
					</div>
					<div class="row">
						<label class="control-label col-xs-4">Cód. Postal: </label> <label
							class="control-label col-xs-8">${vistaPublicacion.objLocalidad.codPostal}</label>
					</div>

					<div class="row">
						<a class="col-xs-12 text-center"
							href="#caracteristicasPublicacion">Ver más características</a>
					</div>
				</div>
				<div class="row col-md-12">
					<br>
				</div>
				<div class="row col-md-12">
					<div class="row col-md-6 pull-left">
						<c:choose>
							<c:when test="${vistaPublicacion.cantComentarios gt 0}">
								<c:url value="PublicacionServlet?"
									var="urlPublicacionComentarios">
									<c:param name="accionPOST" value="VerComentarios" />
									<c:param name="idPublicacion"
										value="${vistaPublicacion.publicacion.idPublicacion}" />
								</c:url>
								<form method="post" action="${urlPublicacionComentarios}">
									<button class="btn btn-default">Ver
										${vistaPublicacion.cantComentarios} Comentarios</button>
								</form>
							</c:when>
							<c:otherwise>
								<!-- <button class="btn btn-default"
									title="Esta publicación aún no tiene comentarios. Dejá el tuyo">Dejar
									un Comentario</button>
								 -->
							</c:otherwise>
						</c:choose>
					</div>

					<c:if test="${not empty sessionScope.sessionUser}">
						<div class="row col-md-6 pull-right">
							<c:choose>
								<c:when test="${vistaPublicacion.objFavorito.isHabilitado()}">
									<c:url value="PublicacionServlet?"
										var="urlEliminarPublicacionFav">
										<c:param name="accionPOST" value="GestionarFavoritos" />
										<c:param name="idPublicacion"
											value="${vistaPublicacion.publicacion.idPublicacion}" />
										<c:param name="agregaAFavoritos" value="false" />
									</c:url>
									<form method="post" action="${urlEliminarPublicacionFav}">
										<button class="btn btn-danger" data-toggle="tooltip"
											title="Esta publicación ya no aparecerá en tu lista de favoritos">
											<span class="glyphicon glyphicon-heart" /></span>&nbsp;Eliminar de
											favoritos
										</button>
									</form>
								</c:when>
								<c:otherwise>
									<c:url value="PublicacionServlet?"
										var="urlGuardarPublicacionFav">
										<c:param name="accionPOST" value="GestionarFavoritos" />
										<c:param name="idPublicacion"
											value="${vistaPublicacion.publicacion.idPublicacion}" />
										<c:param name="agregaAFavoritos" value="true" />
									</c:url>
									<form method="post" action="${urlGuardarPublicacionFav}">
										<button class="btn btn-primary" data-toggle="tooltip"
											title="Guardar esta publicación para verla más tarde">
											<span class="glyphicon glyphicon-heart" /></span>&nbsp;Agregar a
											favoritos
										</button>
									</form>

								</c:otherwise>
							</c:choose>

							<br>
							<c:choose>

								<c:when test="${vistaPublicacion.cantFavoritos gt 1}">
									<p>
										<b>${vistaPublicacion.cantFavoritos}&nbsp;personas
											agregaron esta publicación a su lista de favoritos</b>
									</p>
								</c:when>
								<c:when test="${vistaPublicacion.cantFavoritos eq 1}">
									<p>
										<b>${vistaPublicacion.cantFavoritos}&nbsp;persona agregó
											esta publicación a sus favoritos</b>
									</p>
								</c:when>
								<c:otherwise>

								</c:otherwise>
							</c:choose>

						</div>

					</c:if>

				</div>
				<div class="row col-md-12">
					<br>
				</div>

				<div class="row col-md-12" align="center">
					<c:if test="${puedeHacerComentario eq true }">
						<a class="btn btn-warning" data-toggle="modal"
							data-target="#formAltaComentario"> <span
							class="glyphicon glyphicon-envelope"></span> Hacer un comentario/
							devolución
						</a>
					</c:if>
				</div>

				<div class="row col-md-12">
					<br>
				</div>
				<div class="row col-md-12" align="center">
					<!-- <button class="btn btn-success btn-lg">Solicitar una reserva</button> href="#formSolReserva" -->
					<c:choose>

						<c:when test="${empty sessionScope.sessionUser}">
							<div class="alert alert-info">
								<h4>Para solicitar una reserva primero debés iniciar tu
									sesión</h4>
							</div>
						</c:when>
						<c:when
							test="${vistaPublicacion.usuario.idUsuario eq sessionScope.sessionUser.idUsuario}">

						</c:when>
						<c:otherwise>
							<a class="btn btn-success btn-lg" data-toggle="modal"
								data-target="#formSolReserva"> <span
								class="glyphicon glyphicon-flag"></span> Solicitar una reserva
							</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<div class="row" title="Descripción de la publicación">
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
					<div class="col-md-12">
						<b>${vistaPublicacion.usuario.nombre}&nbsp;${vistaPublicacion.usuario.apellido}</b>
					</div>
					<c:if test="${vistaPublicacion.usuario.isVerificado() eq true}">
						<div class="col-md-12">
							<label class="control-label" style="color: ROYALBLUE;"
								data-toggle="tooltip"
								title="La cuenta del usuario fue verificada por un administrador">
								<span class="glyphicon glyphicon-ok-circle"></span> Usuario
								Verificado
							</label>
						</div>
					</c:if>
					<div class="col-md-12">${vistaPublicacion.usuario.puntaje}&nbsp;/5
						Puntos</div>
				</div>
				<div class="row col-md-12">
					<c:url value="PublicacionServlet?" var="urlPerfilPublicoUsuario">
						<c:param name="accionGET" value="verPerfilPublicoOtroUsuario" />
						<c:param name="idUsuario"
							value="${vistaPublicacion.usuario.idUsuario }" />
					</c:url>
					<a class="btn btn-info" href="${urlPerfilPublicoUsuario}">Ver
						perfil</a>
				</div>
			</div>
		</div>

		<div class="row" title="Características de la publicación"
			id="caracteristicasPublicacion">
			<h3>Características</h3>
			<hr />
			<div class="col-md-12">

				<div class="col-md-4">
					<p>
						Tipo de Alojamiento: <b>${vistaPublicacion.descripcionTipoAlojamiento}</b>
					</p>

					<c:if test="${vistaPublicacion.publicacion.idTipoAlojamiento eq 3}">
						<p>
							Piso : <b>${vistaPublicacion.publicacion.piso}°&nbsp;${vistaPublicacion.publicacion.dpto}</b>
						</p>
					</c:if>


					<c:choose>
						<c:when
							test="${vistaPublicacion.publicacion.aniosAntiguedad eq 0}">
							<p>
								Años de Antigüedad: <b>A Estrenar</b>
							</p>
						</c:when>
						<c:when
							test="${vistaPublicacion.publicacion.aniosAntiguedad eq 1}">
							<p>
								Años de Antigüedad: <b>${vistaPublicacion.publicacion.aniosAntiguedad}&nbsp;año</b>
							</p>
						</c:when>
						<c:otherwise>
							<p>
								Años de Antigüedad: <b>${vistaPublicacion.publicacion.aniosAntiguedad}&nbsp;años</b>
							</p>
						</c:otherwise>
					</c:choose>
					<p>
						Superficie Cubierta: <b>${vistaPublicacion.publicacion.supCubierta}&nbsp;m²</b>
					</p>
					<c:choose>
						<c:when test="${vistaPublicacion.publicacion.supDescubierta eq 0}">
							<p>Superficie Descubierta: No tiene</p>
						</c:when>
						<c:otherwise>
							<p>
								Superficie Descubierta: <b>${vistaPublicacion.publicacion.supDescubierta}&nbsp;m²</b>
							</p>
						</c:otherwise>
					</c:choose>


					<c:choose>
						<c:when test="${vistaPublicacion.publicacion.chkExpensas}">
							<p>
								Tiene expensas : <b>Si</b>
							</p>
							<p>
								Precio expensas: <b>$&nbsp;${vistaPublicacion.publicacion.precioExpensas}</b>
							</p>
						</c:when>
						<c:otherwise>
							<p>
								Tiene expensas: <b>No</b>
							</p>
						</c:otherwise>
					</c:choose>


				</div>

				<div class="col-md-4">
					<c:choose>
						<c:when
							test="${vistaPublicacion.publicacion.chkPuedeVariarCantPersonas eq true}">
							<p class="text-info" data-toggle="tooltip"
								title="La publicación está apta para superar
								la cantidad de personas/ huéspedes establecidas">
								Cant. Personas: <b>${vistaPublicacion.publicacion.cantPersonas}
									o más</b>
							</p>
						</c:when>
						<c:otherwise>
							<p>
								Cant. Personas: <b>${vistaPublicacion.publicacion.cantPersonas}</b>
							</p>
						</c:otherwise>
					</c:choose>

					<p>
						Cant. Ambientes: <b>${vistaPublicacion.publicacion.cantAmbientes}</b>
					</p>
					<p>
						Cant. Baños: <b>${vistaPublicacion.publicacion.cantBanios}</b>
					</p>
					<p>
						Cant. Habitaciones: <b>${vistaPublicacion.publicacion.cantHabitaciones}</b>
					</p>
				</div>

				<div class="col-md-4">
					<p>
						Calle: <b>${vistaPublicacion.publicacion.calle} al
							${vistaPublicacion.publicacion.altura}</b>
					</p>
					<p>
						Partido: <b>${vistaPublicacion.objLocalidad.nombrePartido}</b>
					</p>
					<p>
						Localidad: <b>${vistaPublicacion.objLocalidad.nombre}</b>
					</p>
					<p>
						Código Postal: <b>${vistaPublicacion.objLocalidad.codPostal}</b>
					</p>
				</div>
			</div>
		</div>
		<!-- 
		<div class="row" title="Test - listado Imagenes extension">
			Imagenes leidas
			<c:forEach items="${vistaPublicacion.listaRutaImg}" var="objRutaImg">
				<label class="control-label col-xs-12">${objRutaImg}</label>
			</c:forEach>
		</div> 
		-->


		<div class="row" title="Servicios de la publicación">
			<h3>Servicios</h3>
			<hr />
			<div class="row col-md-12">

				<c:forEach items="${vistaPublicacion.listaServicios}"
					var="objServicio">
					<c:choose>
						<c:when test="${objServicio.objTipoServicio.idTipoServicio eq 1}">
							<div class="col-md-4">
								<label><span class="glyphicon glyphicon-ok"
									style="color: LIMEGREEN;"></span>&nbsp;${objServicio.objTipoServicio.descripcion}
								</label>
							</div>
						</c:when>
						<c:when test="${objServicio.objTipoServicio.idTipoServicio eq 2}">
							<div class="col-md-4">
								<label><span class="glyphicon glyphicon-ok"
									style="color: LIMEGREEN;"></span>&nbsp;${objServicio.objTipoServicio.descripcion}
								</label>
							</div>
						</c:when>
						<c:when test="${objServicio.objTipoServicio.idTipoServicio eq 3}">
							<div class="col-md-4">
								<label><span class="glyphicon glyphicon-ok"
									style="color: LIMEGREEN;"></span>&nbsp;${objServicio.objTipoServicio.descripcion}
								</label>
							</div>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</div>

		<div class="row" title="Solicitud de Reserva(Modal)">
			<div class="modal fade" id="formSolReserva" role="dialog">
				<!-- <div class="modal-dialog"></div> -->
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<%@ include file="solReservaAlta.jsp"%>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar/
							Volver</button>
					</div>
				</div>
			</div>
		</div>

		<div class="row" title="FormularioAltaComentario (Modal)">
			<div class="modal fade" id="formAltaComentario" role="dialog">
				<!-- <div class="modal-dialog"></div> -->
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<%@ include file="PublicacionComentariosAlta.jsp"%>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar/
							Volver</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- div End container -->
	<%@ include file="Footer.jsp"%>

</body>
</html>