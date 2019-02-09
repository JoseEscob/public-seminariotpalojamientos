<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<p>
							<b>${objSolReserva.cantDiasReserva} días</b>
						</p>
					</div>
					<div class="col-xs-12 col-xs-6">
						<p>
							<b>${objSolReserva.cantPersonas} personas</b>
						</p>
					</div>
				</div>


				<div>
					<label class="control-label">Desde:
						${objSolReserva.fechaReservaInicio}</label>
				</div>
				<div>
					<label class="control-label">Hasta:
						${objSolReserva.fechaReservaFin}</label>
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
								value="${objPublicacion.idPublicacion}" />
						</c:url>
						<a class="btn btn-primary" href="${urlPublicacionGuardada}">Ver
							Publicación</a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<table class="table table-hover table-responsive">
			<thead>
				<tr>
					<th>idSolicitud</th>
					<th>idUsuarioHuesped</th>
					<th>idPublicacion</th>
					<th>fechaReservaInicio</th>
					<th>fechaReservaFin</th>
					<th>cantDiasReserva</th>
					<th>cantPersonas</th>
					<th>precioFinal</th>
					<th>fechaAltaSolicitud</th>
					<th>idUsuarioPropietario</th>
					<th>fechaDecisionPropietario</th>
					<th>motivoDecisionPropietario</th>
					<th>idEstadoSolicitud</th>
					<th>habilitado</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaSolDeReservaEnviada}" var="objSolReserva">
					<c:if test="${objSolReserva.idEstadoSolicitud eq 1}">
						<tr>
							<td>${objSolReserva.idSolicitud}</td>
							<td>${objSolReserva.idUsuarioHuesped}</td>
							<td>${objSolReserva.idPublicacion}</td>
							<td>${objSolReserva.fechaReservaInicio}</td>
							<td>${objSolReserva.fechaReservaFin}</td>
							<td>${objSolReserva.cantDiasReserva}</td>
							<td>${objSolReserva.cantPersonas}</td>
							<td>${objSolReserva.precioFinal}</td>
							<td>${objSolReserva.fechaAltaSolicitud}</td>
							<td>${objSolReserva.idUsuarioPropietario}</td>
							<td>${objSolReserva.fechaDecisionPropietario}</td>
							<td>${objSolReserva.motivoDecisionPropietario}</td>
							<td>${objSolReserva.idEstadoSolicitud}</td>
							<td>${objSolReserva.habilitado}</td>
						</tr>
					</c:if>
				</c:forEach>

			</tbody>
		</table>
	</div>
</body>
</html>