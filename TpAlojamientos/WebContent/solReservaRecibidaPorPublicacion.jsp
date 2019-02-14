<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Solicitudes de la Publicación</h2>
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">
		<table class="table table-hover table-responsive">
			<thead>
				<tr>
					<th>Check</th>
					<th>ID Solicitud</th>
					<th>Nombre y apellido del solicitador</th>
					<th>ID Publicacion</th>
					<th>Desde</th>
					<th>Hasta</th>
					<th>Cant. de Días</th>
					<th>Cant. de Personas</th>
					<th>Precio Final</th>
					<th>Fecha de solicitud</th>
					<th>Acciones</th>
					<!--
				<th>fechaAltaSolicitud</th>
				<th>idUsuarioPropietario</th>
				<th>fechaDecisionPropietario</th>
				<th>motivoDecisionPropietario</th>
				<th>idEstadoSolicitud</th>
				<th>habilitado</th>
				-->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaSolDeReservasRecibidasPorPublicacion}"
					var="objSolReserva">
					<c:if test="${objSolReserva.idEstadoSolicitud eq 1}">
						<tr>
							<td><input type="checkbox" class="checkbox"
								name="chklistSolSeleccionadas"
								value="${objSolReserva.idSolicitud}"></td>
							<td>${objSolReserva.idSolicitud}</td>
							<td>${objSolReserva.nombreApellidoHuesped}</td>
							<td>${objSolReserva.idPublicacion}</td>
							<td>${objSolReserva.fechaReservaInicio}</td>
							<td>${objSolReserva.fechaReservaFin}</td>
							<td>${objSolReserva.cantDiasReserva}</td>
							<td>${objSolReserva.cantPersonas}</td>
							<td>${objSolReserva.precioFinal}</td>
							<td>${objSolReserva.fechaAltaSolicitud}</td>

							<td>
								<div>
									<c:url value="PublicacionServlet?"
										var="urlPerfilPublicoUsuario">
										<c:param name="accionGET" value="verPerfilPublicoOtroUsuario" />
										<c:param name="idUsuario"
											value="${objSolReserva.idUsuarioHuesped}" />
									</c:url>

									<a href="${urlPerfilPublicoUsuario}" class="btn btn-default"
										data-toggle="tooltip" title="Ver Perfil"> <span
										class="glyphicon glyphicon-eye-open"></span> Ver Perfil
									</a>
								</div>
								<div>
									<!-- Aprobar Solicitud-->
								</div>
							</td>
							<!--
						<td>${objSolReserva.idUsuarioPropietario}</td>
						<td>${objSolReserva.fechaDecisionPropietario}</td>
						<td>${objSolReserva.motivoDecisionPropietario}</td>
						<td>${objSolReserva.idEstadoSolicitud}</td>
						<td>${objSolReserva.habilitado}</td>
						-->
						</tr>
					</c:if>
				</c:forEach>

			</tbody>
		</table>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-6">

				<div class="row col-md-12" align="center">
					<!-- <button class="btn btn-success btn-lg">Solicitar una reserva</button> href="#formSolReserva" -->

					<a class="btn btn-success btn-lg" data-toggle="modal"
						data-target="#formSolReservaConfirmacion"> <span
						class="glyphicon glyphicon-flag"></span> Aprobar Solicitudes
					</a>
				</div>
			</div>
			<!-- SolDeReservaServlet validarAprobacionDeSolicitudes
					aprobarSolicitudesDeReserva  -->
			<div class="col-md-6"></div>


		</div>
	</div>
</body>
</html>