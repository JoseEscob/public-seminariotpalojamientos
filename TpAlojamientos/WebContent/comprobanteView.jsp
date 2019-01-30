<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comprobante - Detalle</title>
</head>
<body>

	<%@ include file="Banner.jsp"%>


	<div class="container">
		<h2>Comprobante - Detalle</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-6">

				<div class="form-group">
					<label class="control-label col-xs-4">${objComprobante.idComprobante}</label>
					<label class="control-label col-xs-8">${objComprobante.idSolicitud}</label>
					<label class="control-label col-xs-4">${objComprobante.idUsuarioHuesped}</label>
					<label class="control-label col-xs-8">${objComprobante.idPublicacion}</label>
					<label class="control-label col-xs-4">${objComprobante.fechaReservaInicio}</label>
					<label class="control-label col-xs-8">${objComprobante.fechaReservaFin}</label>
					<label class="control-label col-xs-4">${objComprobante.cantPersonas}</label>
					<label class="control-label col-xs-8">${objComprobante.precioFinal}</label>
					<label class="control-label col-xs-4">${objComprobante.fechaAlta}</label>
					<label class="control-label col-xs-8">${objComprobante.idUsuarioPropietario}</label>
				</div>
			</div>
			<div class="col-md-6"></div>
		</div>
	</div>
</body>
</html>