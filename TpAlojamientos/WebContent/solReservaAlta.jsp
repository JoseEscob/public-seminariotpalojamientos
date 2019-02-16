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

	<div class="container well">
		<h2>Solicitud de Reserva</h2>

		<div class="container row">
			<div class="row col-md-12">

				<h4>
					Usted est� por reservar el alojamiento: <b>${vistaPublicacion.publicacion.nombre}</b>
				</h4>
			</div>
			<div class="row col-md-12">
				<h4>Por favor seleccione las fechas de su estad�a</h4>
			</div>
		</div>
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
		<div class="row col-md-12">
			<br>
		</div>

		<div class="row">
			<form method="post" action="SolDeReservaServlet">
				<div class="col-md-3">
					<div class="form-group">

						<c:choose>
							<c:when
								test="${vistaPublicacion.publicacion.chkPuedeVariarCantPersonas eq false}">

								<label>Cantidad de Hu�spedes</label>
								<select class="form-control" name="cmbCantHuespedes" required>
									<c:forEach begin="1"
										end="${vistaPublicacion.publicacion.cantPersonas}"
										varStatus="loop">
										<option value="${loop.index}">${loop.index}</option>

									</c:forEach>
								</select>
							</c:when>
							<c:otherwise>
								<label>Cantidad de Hu�spedes</label>
								<select class="form-control" name="cmbCantHuespedes" required>
									<c:forEach begin="1" end="20" varStatus="loop">
										<option value="${loop.index}">${loop.index}</option>s
								</c:forEach>
								</select>
							</c:otherwise>
						</c:choose>


					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label class="control-label">Fecha de Inicio </label> <input
							type="date" class="form-control" pattern="^\d{2}-\d{2}-\d{4}$"
							maxlength="10" placeholder="dd-MM-yyyy" name="fechaInicio"
							id="fechaInicio" min="return getCurrentDate();" required>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label class="control-label">Fecha de Regreso</label> <input
							type="date" class="form-control" pattern="^\d{2}-\d{2}-\d{4}$"
							maxlength="10" placeholder="dd-MM-yyyy" name="fechaFin" required>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<c:url value="SolDeReservaServlet?" var="urlSolReservaAlta">
							<c:param name="accionPOST" value="SolReservaAlta" />
							<c:param name="idPublicacion"
								value="${vistaPublicacion.publicacion.idPublicacion}" />
						</c:url>
						<br>
						<!--
						<c:set var="vistaPublicacion" value="${vistaPublicacion}"
							scope="request" />
							
							
							<input type="hidden" name="vistaPublicacion" value="${vistaPublicacion}"></input>
						
						<input type="submit" class="btn btn-success"
							name="btnEnviarSolReserva" value="Enviar solicitud"></input>
						
						-->

						<input type="hidden" name="accionPOST"
							value="altaSolicitudReserva"></input>


						<button class="btn btn-success">
							<span class="glyphicon glyphicon-send"></span> Enviar solicitud
							de Reserva
						</button>

					</div>
				</div>
			</form>
		</div>

		<div class="row col-md-12">
			<br>
		</div>
		<div class="row col-md-12">
			<div class="col-md-12 col-md-3  alert alert-info">
				<div class="form-group">
					<c:choose>
						<c:when
							test="${vistaPublicacion.publicacion.chkPuedeVariarCantPersonas eq true}">
							<label class="control-label">Apto para
								${vistaPublicacion.publicacion.cantPersonas} Personas o m�s</label>
						</c:when>
						<c:otherwise>
							<label class="control-label">Apto para
								${vistaPublicacion.publicacion.cantPersonas} Personas</label>
						</c:otherwise>
					</c:choose>
				</div>
				<p>
					Precio por Noche: <b>$&nbsp;${vistaPublicacion.publicacion.precioNoche}</b>
				</p>

				<c:choose>
					<c:when test="${vistaPublicacion.publicacion.precioExpensas eq 0}">

						<p>
							Precio expensas: <b>$&nbsp;${vistaPublicacion.publicacion.precioExpensas}</b>
						</p>
					</c:when>
					<c:otherwise>
						<p>
							Precio expensas: <b>$&nbsp;${vistaPublicacion.publicacion.precioExpensas}</b>
						</p>
						<div class="alert alert-info">
							<h5>Las expensas se cobran solo si se solicita reservar m�s
								de 25 d�as</h5>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
		<div class="row col-md-12">
			<br>
		</div>
		<div class="row col-md-12">
			<div class="col-md-12 col-md-6 alert alert-warning">
				<div class="col-md-12">
					<h4>La publicaci�n tiene algunas fechas reservadas. Por favor
						verif�quelas para ver las disponibles</h4>
				</div>
				<div class="col-md-12" align="center">
					<c:url value="SolDeReservaServlet?" var="urlVerFechasReservadas">
						<c:param name="accionGET" value="verFechasDeReservaPublicacion" />
						<c:param name="idPublicacion"
							value="${vistaPublicacion.publicacion.idPublicacion}" />
					</c:url>
					<a class="btn btn-default" href="${urlVerFechasReservadas}"> <span
						class="glyphicon glyphicon-eye-open"></span>&nbsp;Ver Fechas ya
						Reservadas
					</a>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function getCurrentDate() {
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth() + 1; //January is 0!
			var yyyy = today.getFullYear();
			if (dd < 10) {
				dd = '0' + dd
			}
			if (mm < 10) {
				mm = '0' + mm
			}

			//today = yyyy + '-' + mm + '-' + dd;
			today = dd + '-' + mm + '-' + yyyy;

			//document.getElementById("fechaInicio").setAttribute("min", today);
			//var inputDate = document.getElementsByName('fechaFin')
			//inputDate.setAttribute("max", today);

			return today;
		};
	</script>

</body>
</html>