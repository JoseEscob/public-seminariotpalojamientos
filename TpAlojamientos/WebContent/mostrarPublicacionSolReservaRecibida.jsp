<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="extra.ConstantesJSP"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=utf-8; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=ConstantesJSP.jspPub_PublicacionesListaGrillacss%>" />
<title></title>
</head>
<body>

	<div class="item  col-xs-4 col-lg-4">
		<div class="thumbnail">

			<c:choose>
				<c:when
					test="${not empty objPublicacionReservaRecibida.objPublicacionInfo.publicacionRutaPrimerImagen}">
					<img class="group list-group-image" width="250" height="200"
						src="${objPublicacionReservaRecibida.objPublicacionInfo.publicacionRutaPrimerImagen}"
						alt="Imagen de Publicación" />
				</c:when>
				<c:otherwise>
					<img class="group list-group-image"
						src="imagenes/home-not-found.png" alt="" />
				</c:otherwise>
			</c:choose>



			<div class="caption">
				<div>
					<h4 class="group inner list-group-item-heading">
						<b>${objPublicacionReservaRecibida.nombre}</b>
					</h4>
				</div>
				<div>
					<ul class="list-inline">
						<li>Zona:</li>
						<li><b>${objPublicacionReservaRecibida.objPublicacionInfo.publicacionPartidoLocalidad}</b></li>
					</ul>
				</div>
				<div>
					<ul class="list-inline">
						<li>Superficie Total: <b>${objPublicacionReservaRecibida.supCubierta + objPublicacionReservaRecibida.supDescubierta}
								m²</b></li>
						<c:choose>
							<c:when
								test="${objPublicacionReservaRecibida.cantAmbientes gt 1}">
								<li>Puntuación <label class="label label-default"
									data-toggle="tooltip" title="Puntuación General">
										${objPublicacionReservaRecibida.puntaje} </label></li>
							</c:when>
							<c:otherwise>
								<li><b></b></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>

				<div>
					<ul class="list-inline">

						<li><b>${objPublicacionReservaRecibida.cantHabitaciones}
								habit.</b></li>
						<c:choose>
							<c:when
								test="${objPublicacionReservaRecibida.cantAmbientes eq 1}">
								<li><b>Monoambiente</b></li>
							</c:when>
							<c:otherwise>
								<li><b>${objPublicacionReservaRecibida.cantAmbientes}
										ambientes</b></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${objPublicacionReservaRecibida.cantBanios eq 1}">
								<li><b>${objPublicacionReservaRecibida.cantBanios} baño</b></li>
							</c:when>
							<c:otherwise>
								<li><b>${objPublicacionReservaRecibida.cantBanios}
										baños</b></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when
								test="${objPublicacionReservaRecibida.aniosAntiguedad eq 0}">
								<li><b>A Estrenar</b></li>
							</c:when>
							<c:when
								test="${objPublicacionReservaRecibida.aniosAntiguedad eq 1}">
								<li><b>${objPublicacionReservaRecibida.aniosAntiguedad}
										año</b></li>
							</c:when>
							<c:otherwise>
								<li><b>${objPublicacionReservaRecibida.aniosAntiguedad}
										años</b></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div>
					<c:choose>
						<c:when
							test="${objPublicacionReservaRecibida.chkPuedeVariarCantPersonas eq true}">
							<label class="control-label">Apto para
								${objPublicacionReservaRecibida.cantPersonas} Personas o más</label>
						</c:when>
						<c:otherwise>
							<label class="control-label">Apto para
								${objPublicacionReservaRecibida.cantPersonas} Personas</label>
						</c:otherwise>
					</c:choose>
				</div>
				<div>
					<p class="group inner list-group-item-text">${objPublicacionReservaRecibida.descripcion}</p>
				</div>
				<div class="row">
					<br>
				</div>
				<div class="row">
					<div class="col-xs-12 col-xs-12">
						<p class="lead">
							<b>$&nbsp;${objPublicacionReservaRecibida.precioNoche}</b>
						</p>
					</div>
					<c:if
						test="${objPublicacionReservaRecibida.idUsuario eq sessionScope.sessionUser.idUsuario}">

						<div class="col-xs-12 col-xs-6">
							<c:url value="PublicacionServlet?" var="urlPublicacionEditar">
								<c:param name="accionGET" value="EditarPublicacion" />
								<c:param name="idPublicacion"
									value="${objPublicacionReservaRecibida.idPublicacion}" />
							</c:url>
							<a class="btn btn-default" href="${urlPublicacionEditar}"> <span
								class="glyphicon glyphicon-pencil"></span>&nbsp;Editar
							</a>
						</div>
					</c:if>

					<div class="col-xs-12 col-xs-6">
						<c:url value="SolDeReservaServlet?"
							var="urlVerSolRecibidasPorPublicacion">
							<c:param name="accionGET"
								value="cargarSolDeReservasRecibidasByIdPublicacion" />
							<c:param name="idPublicacion"
								value="${objPublicacionReservaRecibida.idPublicacion}" />
						</c:url>
						<a class="btn btn-primary"
							href="${urlVerSolRecibidasPorPublicacion}">Ver solicitudes</a>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>