<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="extra.ConstantesJSP"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>


	<div class="item  col-xs-4 col-lg-4">
		<div class="thumbnail">
			<img class="group list-group-image" src="imagenes/home-not-found.png"
				alt="" />

			<div class="caption">
				<div>
					<h4 class="group inner list-group-item-heading">
						<b></b>
					</h4>
				</div>
				<div>
					<ul class="list-inline">
						<li>ID Solicitud:</li>
						<li><b>${objSolReserva.idSolicitud}</b></li>
					</ul>
				</div>
				<div>
					<ul class="list-inline">
						<li>Fecha Solicitada:</li>
						<li><b>${objSolReserva.fechaAltaSolicitud}</b></li>
					</ul>
				</div>
				<div class="row">
					<div class="col-xs-12 col-xs-6">
						<c:when test="${objSolReserva.cantDiasReserva eq 1}">
							<p>
								<b>${objSolReserva.cantDiasReserva} día</b>
							</p>
						</c:when>
						<c:otherwise>
							<p>
								<b>${objSolReserva.cantDiasReserva} días</b>
							</p>
						</c:otherwise>
					</div>
					<div class="col-xs-12 col-xs-6">
						<c:when test="${objSolReserva.cantPersonas eq 1}">
							<p>
								<b>${objSolReserva.cantPersonas} persona</b>
							</p>
						</c:when>
						<c:otherwise>
							<p>
								<b>${objSolReserva.cantPersonas} personas</b>
							</p>
						</c:otherwise>
					</div>
				</div>


				<div>
					<label class="control-label">Desde: <fmt:formatDate
							value="${objSolReserva.fechaReservaInicio}" type="date"
							pattern="dd/MM/yyyy" />
					</label>
				</div>
				<div>
					<label class="control-label">Hasta: <fmt:formatDate
							value="${objSolReserva.fechaReservaFin}" type="date"
							pattern="dd/MM/yyyy" />
					</label>
				</div>

				<div class="row">
					<br>
				</div>
				<div class="row">
					<div class="col-xs-12 col-xs-6">
						<p class="lead">
							Precio Final &nbsp; <b>$&nbsp;${objSolReserva.precioFinal}</b>
						</p>
					</div>
					<div class="col-xs-12 col-xs-6">
						<c:url value="PublicacionServlet?" var="urlPublicacionGuardada">
							<c:param name="accionGET" value="VerPublicacion" />
							<c:param name="idPublicacion"
								value="${objSolReserva.idPublicacion}" />
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