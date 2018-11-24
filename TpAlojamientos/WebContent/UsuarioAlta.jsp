<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Colorlib Templates">
<meta name="author" content="Colorlib">
<meta name="keywords" content="Colorlib Templates">

<!-- Icons font CSS-->
<link href="vendor/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">
<link href="vendor/font-awesome-4.7/css/font-awesome.min.css"
	rel="stylesheet" media="all">

<!-- Font special for pages-->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Vendor CSS-->
<link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
<link href="vendor/datepicker/daterangepicker.css" rel="stylesheet"
	media="all">

<!-- Main CSS-->
<link href="css/main.css" rel="stylesheet" media="all">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Completar Formulario</title>
</head>
<body>
	<!-- Al incluir el banner me parece q ya incluye lo del HEAD y los script del final -->
	<div class="page-wrapper bg-skyblue p-t-130 p-b-100 font-poppins">
		<div class="wrapper wrapper--w680">
			<div class="card card-4">
				<div class="card-body">
					<h1 class="title">Registraci�n</h1>
					<!-- ${pageContext.request.contextPath}/ -->
					<form method="POST" action="UsuarioServlet">
						<div class="col-2">
							<div class="input-group">
								<label class="label">Email</label> <input class="input--style-4"
									type="email" name="mail" value="asd@gmail.com">
							</div>
						</div>
						<div class="row row-space">
							<div class="col-2">
								<div class="input-group">
									<label class="label">Contrase�a</label> <input
										class="input--style-4" type="password" maxlength="20"
										name="claveUno" value="asd123">
								</div>
							</div>
							<div class="col-2">
								<div class="input-group">
									<label class="label">Repita Contrase�a</label> <input
										class="input--style-4" type="password" maxlength="20"
										name="claveDos" value="asd1234">
								</div>
							</div>
						</div>
						<h3 class="title">Datos Personales</h3>
						<div class="row row-space">
							<div class="col-2">
								<div class="input-group">
									<label class="label">Nombre</label> <input
										class="input--style-4" onkeypress="return soloLetras(event)"
										type="text" name="nombre" maxlength="50" value="Jos�">
								</div>
							</div>
							<div class="col-2">
								<div class="input-group">
									<label class="label">Apellido</label> <input
										class="input--style-4" onkeypress="return soloLetras(event)"
										type="text" name="apellido" maxlength="50" value="Apelli">
								</div>
							</div>
						</div>
						<div class="row row-space">
							<div class="col-2">
								<div class="input-group">
									<label class="label">Fecha de Nacimiento</label>
									<div class="input-group-icon">
										<input class="input--style-4 js-datepicker" type="text"
											name="fechaNac" readonly> <i
											class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
									</div>
								</div>
							</div>
							<div class="col-2">
								<div class="input-group">
									<label class="label">Sexo</label>
									<div class="p-t-10">
										<!--class="radio-container m-r-45" -->
										<label class="radio-container">Masculino <input
											type="radio" checked="false" name="gender"> <span
											class="checkmark"></span>
										</label> <label class="radio-container">Femenino <input
											type="radio" name="gender"> <span class="checkmark"></span>
										</label>
									</div>
								</div>
							</div>
						</div>
						<div class="row row-space">
							<div class="col-2">
								<div class="input-group">
									<label class="label">DNI</label> <input class="input--style-4"
										onkeypress="return soloNros(event)" onpaste="return false"
										autocomplete="off" type="text" name="dni" value="123"
										maxlength="10">
								</div>
							</div>
							<!-- 
							<div class="col-2">
								<div class="input-group">
									<label class="label">Nro de Tel�fono</label> <input
										class="input--style-4" onkeypress="return soloNros(event)"
										type="text" name="telefono">
								</div>
							</div>
							 -->
						</div>
						<!-- 
						<div class="input-group">
							<label class="label">Subject</label>
							<div class="rs-select2 js-select-simple select--no-search">
								<select name="subject">
									<option disabled="disabled" selected="selected">Choose
										option</option>
									<option>Subject 1</option>
									<option>Subject 2</option>
									<option>Subject 3</option>
								</select>
								<div class="select-dropdown"></div>
							</div>
						</div>
						 -->
						<div class="p-t-15">
							<input type="hidden" id="buscarAction" name="buscarAction"
								value="nuevo"></input> <input
								class="btn btn--radius-2 btn--blue" type="submit"
								name="btnNuevoUsuario" value="Guardar"></input>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<!-- Jquery JS-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<!-- Vendor JS-->
	<script src="vendor/select2/select2.min.js"></script>
	<script src="vendor/datepicker/moment.min.js"></script>
	<script src="vendor/datepicker/daterangepicker.js"></script>

	<!-- Main JS-->
	<script src="js/global.js"></script>
	<script src="js/JSValidar.js"></script>
</body>
</html>