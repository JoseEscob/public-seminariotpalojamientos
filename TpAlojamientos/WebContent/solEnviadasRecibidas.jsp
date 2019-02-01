<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitudes</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>
			Solicitudes de Publicaciones<span
				class="pull-right label label-default">:)</span>
		</h2>


	</div>
	<div class="container">
		<div class="row">
			<ul class="nav nav-tabs nav-justified">
				<li class="active"><a href="#solEnviadas" data-toggle="tab"><h4>Solicitudes
							Enviadas</h4></a></li>
				<li><a href="#solRecibidas" data-toggle="tab"><h4>Solicitudes
							Recibidas</h4></a></li>
				<!-- <li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="">Solicitudes Recibidas <span
						class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#solRecibidas">Enviadas</a></li>
						<li><a href="#">Aprovadas</a></li>
						<li><a href="#">Rechazadas</a></li>
					</ul></li> -->
			</ul>
		</div>
		<div class="row">
			<div class="tab-content">
				<div id="solEnviadas" class="tab-pane active">
					<div class="container">
						<div class="row">
							<br>
						</div>
						<div class="row">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<b>Solictudes Enviadas por Usted - En revisión</b>
								</div>
								<div class="panel-body">Panel Content</div>
							</div>
						</div>
						<div class="row">
							<div class="panel panel-success">
								<div class="panel-heading">
									<b>Solictudes Enviadas por Usted - Aprobadas</b>
								</div>
								<div class="panel-body">Panel Content</div>
							</div>
						</div>
						<div class="row">
							<div class="panel panel-danger">
								<div class="panel-heading">
									<b>Solictudes Enviadas por Usted - Rechazadas</b>
								</div>
								<div class="panel-body">Panel Content</div>
							</div>
						</div>

					</div>
				</div>
				<div id="solRecibidas" class="tab-pane">
					<div class="container">
						<div class="row">
							<br>
						</div>
						<div class="row">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<b>Solictudes Recibidas - En revisión por Ud.</b>
								</div>
								<div class="panel-body">Panel Content</div>
							</div>
						</div>
						<div class="row">
							<div class="panel panel-success">
								<div class="panel-heading">
									<b>Solictudes Recibidas - Aprobadas por Ud.</b>
								</div>
								<div class="panel-body">Panel Content</div>
							</div>
						</div>
						<div class="row">
							<div class="panel panel-danger">
								<div class="panel-heading">
									<b>Solictudes Recibidas - Rechazadas por Ud.</b>
								</div>
								<div class="panel-body">Panel Content</div>
							</div>
						</div>

					</div>


				</div>
			</div>
		</div>
	</div>
</body>
</html>