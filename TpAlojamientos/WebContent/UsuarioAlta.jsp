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

	<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
		<div class="wrapper wrapper--w680">
			<div class="card card-4">
				<div class="card-body">
					<h2 class="title">Registración</h2>
					<form method="POST">
						<div class="row row-space">
							<div class="col-2">
								<div class="input-group">
									<label class="label">Nombre</label> <input
										class="input--style-4" type="text" name="nombre">
								</div>
							</div>
							<div class="col-2">
								<div class="input-group">
									<label class="label">Apellido</label> <input
										class="input--style-4" type="text" name="apellido">
								</div>
							</div>
						</div>
						<div class="row row-space">
							<div class="col-2">
								<div class="input-group">
									<label class="label">Fecha de Nacimiento</label>
									<div class="input-group-icon">
										<input class="input--style-4 js-datepicker" type="text"
											name="fechaNac"> <i
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
									<label class="label">Email</label> <input
										class="input--style-4" type="email" name="email">
								</div>
							</div>
							<div class="col-2">
								<div class="input-group">
									<label class="label">Nro de Teléfono</label> <input
										class="input--style-4" type="text" name="telefono">
								</div>
							</div>
						</div>
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
						<div class="p-t-15">
							<button class="btn btn--radius-2 btn--blue" type="submit">Enviar</button>
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



</body>
</html>