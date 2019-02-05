<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

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
		<div class="row col-md-12">
			<br>
		</div>
		<div class="row">
			<div class="col-md-3">
				<div class="form-group">
					<label>Cantidad de Hu�spedes</label> <select class="form-control"
						name="cmbCantHuespedes">
						<option>1</option>
						<option>2</option>
						<option>3</option>
					</select>
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
					<c:url value="PublicacionServlet?" var="urlSolReservaAlta">
						<c:param name="accionPOST" value="SolReservaAlta" />
						<c:param name="idPublicacion"
							value="${vistaPublicacion.publicacion.idPublicacion}" />
					</c:url>
					<br>
					<form method="post" action="${urlSolReservaAlta}">
						<button class="btn btn-success "
							onclick="alert(getCurrentDate());">
							<span class="glyphicon glyphicon-send"></span> Enviar solicitud
							de Reserva
						</button>
					</form>
				</div>
			</div>
		</div>

		<div class="row col-md-12">
			<br>
		</div>

		<div class="row col-md-12" style="color: red;">
			<h4>
				Informar si la cant de Personas puede ser m�s de lo permitido/
				cargado <b></b>
			</h4>
		</div>
		<div class="row col-md-12" style="color: red;">
			<h4>
				Listado de fechas en las que se encuentra reservada la publicacion:
				<b>${vistaPublicacion.publicacion.nombre}</b>
			</h4>
		</div>
	</div>
</body>
</html>