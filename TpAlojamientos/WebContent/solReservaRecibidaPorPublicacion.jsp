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
					<!--<th>Check</th>-->
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
					var="objSolReserva" varStatus="loop">
					<c:if test="${objSolReserva.idEstadoSolicitud eq 1}">
						<tr>
							<!--
							<td><input type="checkbox" class="checkbox"
								name="chklistSolSeleccionadas"
								value="${objSolReserva.idSolicitud}"></td>
							-->
							<td>${objSolReserva.idSolicitud}</td>
							<td>${objSolReserva.nombreApellidoHuesped}</td>
							<td>${objSolReserva.idPublicacion}</td>
							<td>${objSolReserva.fechaReservaInicio}</td>
							<td>${objSolReserva.fechaReservaFin}</td>
							<td>${objSolReserva.cantDiasReserva}</td>
							<td>${objSolReserva.cantPersonas}</td>
							<td>$&nbsp;${objSolReserva.precioFinal}</td>
							<td>${objSolReserva.fechaAltaSolicitud}</td>

							<td>
								<div>
									<c:url value="PublicacionServlet?"
										var="urlPerfilPublicoUsuario">
										<c:param name="accionGET" value="verPerfilPublicoOtroUsuario" />
										<c:param name="idUsuario"
											value="${objSolReserva.idUsuarioHuesped}" />
									</c:url>
									<c:url value="SolDeReservaServlet?"
										var="urlAprobarUnaSolicitud">
										<c:param name="accionGET" value="aprobarUnaSolicitudDeReserva" />
										<c:param name="idSolicitud"
											value="${objSolReserva.idSolicitud}" />
									</c:url>
									<c:url value="SolDeReservaServlet?"
										var="urlRechazarUnaSolicitud">
										<c:param name="accionGET"
											value="rechazarUnaSolicitudDeReserva" />
										<c:param name="idSolicitud"
											value="${objSolReserva.idSolicitud}" />
									</c:url>
								</div>
								<div class="btn-group ">
									<div class="btn">
										<a href="${urlPerfilPublicoUsuario}" class="btn btn-default"
											data-toggle="tooltip"
											title="Ver Perfil del usuario solicitador"> <span
											class="glyphicon glyphicon-eye-open"></span> Ver Perfil
										</a>
									</div>
									<div class="btn">
										<!-- Aprobar Solicitud-->
										<a href="${urlAprobarUnaSolicitud}" class="btn btn-success"
											data-toggle="tooltip" title="Aprueba la solicitud"> <span
											class="glyphicon glyphicon-ok"></span> Aprobar
										</a>
									</div>
									<!--<div>
									${loop.index}
										 Aprobar Solicitud
										<a href="${urlAprobarUnaSolicitud}" name="btnVer" value="${loop.index}"> <span
											class="glyphicon glyphicon-ok"></span> Ver
										</a> ${loop.index}
									</div>
									-->
									<div class="btn">
										<a href="${urlRechazarUnaSolicitud}" class="btn btn-danger"
											data-toggle="tooltip" title="Rechaza la solicitud"> <span
											class="glyphicon glyphicon-remove-circle"></span> Rechazar
										</a>
									</div>
								</div>


							</td>
						</tr>
					</c:if>
				</c:forEach>

			</tbody>
		</table>
	</div>


	<div class="container">
		<c:set var="rowno" value="${param.btnVer - 1}" />
		${listaSolDeReservasRecibidasPorPublicacion[rowno].idSolicitud}
		${listaSolDeReservasRecibidasPorPublicacion[rowno].nombreApellidoHuesped}
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




	<div class="container">
		<div class="row">
			<div class="col-md-6">

				<div class="row col-md-12" align="center">
					<!-- <button class="btn btn-success btn-lg">Solicitar una reserva</button> href="#formSolReserva"

					<a class="btn btn-success btn-lg" data-toggle="modal"
						data-target="#formSolReservaConfirmacion"> <span
						class="glyphicon glyphicon-flag"></span> Aprobar Solicitudes
					</a>
					 -->
				</div>
			</div>
			<!-- SolDeReservaServlet validarAprobacionDeSolicitudes
					aprobarSolicitudesDeReserva  -->
			<div class="col-md-6"></div>


		</div>
	</div>
</body>
</html>