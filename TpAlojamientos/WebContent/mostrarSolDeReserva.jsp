<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<img class="group list-group-image" width="250" height="200"
				src="${objSolReservaView.publicacionRutaPrimerImagen}"
				alt="Imagen de Publicación" />

			<div class="caption">
				<div>
					<h4 class="group inner list-group-item-heading">
						<b>${objSolReservaView.publicacionNombre}</b>
					</h4>
				</div>
				<div>
					<ul class="list-inline">
						<li>ID Solicitud:</li>
						<li><b>${objSolReservaView.objSolReserva.idSolicitud}</b></li>
					</ul>
				</div>
				<div>
					<ul class="list-inline">
						<li>Zona:</li>
						<li><b>${objSolReservaView.publicacionPartidoLocalidad}</b></li>
					</ul>
				</div>
				<div>
					<ul class="list-inline">
						<li>Fecha Solicitada:</li>
						<li><b>${objSolReservaView.objSolReserva.fechaAltaSolicitud}</b></li>
					</ul>
				</div>



				<div>
					<ul class="list-inline">
						<c:choose>
							<c:when
								test="${objSolReservaView.objSolReserva.cantDiasReserva eq 1}">
								<li><b>${objSolReservaView.objSolReserva.cantDiasReserva}
										día </b></li>
							</c:when>
							<c:otherwise>
								<li><b>${objSolReservaView.objSolReserva.cantDiasReserva}
										días </b></li>
							</c:otherwise>
						</c:choose>
						<li><b> | </b></li>
						<c:choose>
							<c:when
								test="${objSolReservaView.objSolReserva.cantPersonas eq 1}">
								<li><b>${objSolReservaView.objSolReserva.cantPersonas}
										persona </b></li>
							</c:when>
							<c:otherwise>
								<li><b>${objSolReservaView.objSolReserva.cantPersonas}
										personas </b></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>



				<div>
					<ul class="list-inline">
						<li><label class="control-label">Desde: <fmt:formatDate
									value="${objSolReservaView.objSolReserva.fechaReservaInicio}"
									type="date" pattern="dd/MM/yyyy" />
						</label></li>
						<li><b> </b></li>
						<li><label class="control-label">Hasta: <fmt:formatDate
									value="${objSolReservaView.objSolReserva.fechaReservaFin}"
									type="date" pattern="dd/MM/yyyy" />
						</label></li>
					</ul>
				</div>


				<div class="row">
					<br>
				</div>
				<div class="row">
					<div class="col-xs-12 col-xs-6">
						<p class="lead">
							Precio Final &nbsp; <b>$&nbsp;${objSolReservaView.objSolReserva.precioFinal}</b>
						</p>
					</div>
					<div class="col-xs-12 col-xs-6">
						<c:url value="PublicacionServlet?" var="urlPublicacionGuardada">
							<c:param name="accionGET" value="VerPublicacion" />
							<c:param name="idPublicacion"
								value="${objSolReservaView.objSolReserva.idPublicacion}" />
						</c:url>
						<a class="btn btn-primary" href="${urlPublicacionGuardada}">Ver
							Publicación</a>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>