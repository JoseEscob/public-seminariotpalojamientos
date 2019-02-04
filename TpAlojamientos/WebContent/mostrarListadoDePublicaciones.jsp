<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<div class="container">

		<c:choose>
			<c:when test="${empty listadoDePublicaciones}">
				<div class="alert alert-info">
					<h4>No se encontraron registros de publicaciones</h4>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row list-group" id="products">
					<c:forEach items="${listadoDePublicaciones}" var="objPublicacion">
						<div class="item  col-xs-4 col-lg-4">
							<div class="thumbnail">
								<img class="group list-group-image"
									src="imagenes/home-not-found.png" alt="" />
								<!--  <img class="group list-group-image"
							src="http://placehold.it/400x250/000/fff" alt="" />  {objPublicacion.imagenes[0].rutaImgPublicacion}-->

								<div class="caption">
									<div>
										<h4 class="group inner list-group-item-heading">
											<b>${objPublicacion.nombre}</b>
										</h4>
									</div>
									<div>
										<ul class="list-inline">
											<li>Superficie Total: <b>${objPublicacion.supCubierta + objPublicacion.supDescubierta}
													m�</b></li>
											<c:choose>
												<c:when test="${objPublicacion.cantAmbientes gt 1}">
													<li>Puntuaci�n <label class="label label-default"
														data-toggle="tooltip" title="Puntuaci�n General">
															${objPublicacion.puntaje} </label></li>
												</c:when>
												<c:otherwise>
													<li><b></b></li>
												</c:otherwise>
											</c:choose>
										</ul>
									</div>

									<div>
										<ul class="list-inline">

											<li><b>${objPublicacion.cantHabitaciones} habit.</b></li>
											<c:choose>
												<c:when test="${objPublicacion.cantAmbientes eq 1}">
													<li><b>Monoambiente</b></li>
												</c:when>
												<c:otherwise>
													<li><b>${objPublicacion.cantAmbientes} ambientes</b></li>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${objPublicacion.cantBanios eq 1}">
													<li><b>${objPublicacion.cantBanios} ba�o</b></li>
												</c:when>
												<c:otherwise>
													<li><b>${objPublicacion.cantBanios} ba�os</b></li>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${objPublicacion.aniosAntiguedad eq 0}">
													<li><b>A Estrenar</b></li>
												</c:when>
												<c:when test="${objPublicacion.aniosAntiguedad eq 1}">
													<li><b>${objPublicacion.aniosAntiguedad} a�o</b></li>
												</c:when>
												<c:otherwise>
													<li><b>${objPublicacion.aniosAntiguedad} a�os</b></li>
												</c:otherwise>
											</c:choose>
										</ul>
									</div>
									<div>
										<c:choose>
											<c:when
												test="${objPublicacion.chkPuedeVariarCantPersonas eq true}">
												<label class="control-label">Apto para
													${objPublicacion.cantPersonas} Personas o m�s</label>
											</c:when>
											<c:otherwise>
												<label class="control-label">Apto para
													${objPublicacion.cantPersonas} Personas</label>
											</c:otherwise>
										</c:choose>
									</div>
									<div>
										<p class="group inner list-group-item-text">${objPublicacion.descripcion}</p>
									</div>
									<div class="row">
										<br>
									</div>
									<div class="row">
										<div class="col-xs-12 col-xs-6">
											<p class="lead">
												<b>$&nbsp;${objPublicacion.precioNoche}</b>
											</p>
										</div>
										<div class="col-xs-12 col-xs-6">
											<c:url value="PublicacionServlet?"
												var="urlPublicacionGuardada">
												<c:param name="accionGET" value="VerPublicacion" />
												<c:param name="idPublicacion"
													value="${objPublicacion.idPublicacion}" />
											</c:url>
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