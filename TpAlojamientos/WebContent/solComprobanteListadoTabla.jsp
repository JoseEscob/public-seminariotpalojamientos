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
		<h2>Comprobantes de Reserva</h2>
		<h4>En esta sección se encuentran los Comprobantes de Reserva de
			las solicitudes aprobadas</h4>
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="row col-md-12">
		<br>
	</div>
	<div class="container">
		<div class="row">
			<ul class="nav nav-tabs nav-justified">
				<li class="active"><a href="#ComprobantesSolEnviadas"
					data-toggle="tab"><h4>
							Comprobantes de solicitudes Enviadas&nbsp;<span class="badge">${fn:length(listaComprobantesSolEnviados)}</span>
						</h4></a></li>
				<li><a href="#ComprobantesSolRecibidas" data-toggle="tab"><h4>
							Comprobantes de solicitudes Recibidas&nbsp;<span class="badge">${fn:length(listaComprobantesSolRecibidos)}</span>
						</h4></a></li>
			</ul>
		</div>

		<div class="row col-md-12">
			<br>
		</div>
		<div class="row">
			<div class="tab-content">
				<div id="ComprobantesSolEnviadas" class="tab-pane active">
					<div class="container">
						<c:choose>
							<c:when test="${empty listaComprobantesSolEnviados}">
								<div class="alert alert-info">
									<h4>No se encontraron comprobantes de reserva</h4>
								</div>
							</c:when>

							<c:otherwise>
								<table class="table table-hover table-responsive">
									<thead>
										<%@ include file="headerComprobante.jsp"%>
									</thead>
									<tbody>
										<c:forEach items="${listaComprobantesSolEnviados}"
											var="objComprobante">
											<%@ include file="filaComprobante.jsp"%>
										</c:forEach>
									</tbody>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div id="ComprobantesSolRecibidas" class="tab-pane">
					<div class="container">
						<c:choose>
							<c:when test="${empty listaComprobantesSolRecibidos}">
								<div class="alert alert-info">
									<h4>No se encontraron comprobantes de reserva</h4>
								</div>
							</c:when>

							<c:otherwise>
								<table class="table table-hover table-responsive">
									<thead>
										<%@ include file="headerComprobante.jsp"%>
									</thead>
									<tbody>
										<c:forEach items="${listaComprobantesSolRecibidos}"
											var="objComprobante">
											<%@ include file="filaComprobante.jsp"%>
										</c:forEach>
									</tbody>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>