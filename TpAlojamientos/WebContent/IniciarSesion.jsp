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
<link
	href="login/vendor/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">
<link href="login/vendor/font-awesome-4.7/css/font-awesome.min.css"
	rel="stylesheet" media="all">

<!-- Font special for pages-- href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" -->
<!--  <link rel="stylesheet" href="login/fontGoogle.css"> -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i">
<!-- Vendor CSS-->
<link href="login/vendor/select2/select2.min.css" rel="stylesheet"
	media="all">
<link href="login/vendor/datepicker/daterangepicker.css"
	rel="stylesheet" media="all">

<!-- Main CSS-->
<link href="login/css/main.css" rel="stylesheet" media="all">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Iniciar Sesión</title>
</head>
<body>
	<!-- Al incluir el banner me parece q ya incluye lo del HEAD y los script del final -->
	<div
		class="page-wrapper bg-gra-turquesa-purpura p-t-130 p-b-100 font-poppins">
		<div class="wrapper wrapper--w680">
			<div class="card card-4">
				<div class="card-body">
					<h1 class="title">Iniciar Sesión</h1>
					<form method="POST" action="UsuarioServlet">

						<div class="input-group">
							<label class="label">Usuario</label> <input
								class="input--style-4" type="text" name="txtUser"
								value="soyUsuario">
						</div>

						<div class="row row-space"></div>
						<div class="input-group pass_show">
							<label class="label">Contraseña</label> <input
								class="input--style-4" type="password" name="txtPass"
								value="asd123">
						</div>


						<div class="row row-space"></div>
						<div class="row row-space">

							<div class="col-2">
								<div class="input-group">
									<label class="label"><a href="UsuarioAlta.jsp">Registrarse</a></label>
								</div>
							</div>
							<div class="col-2">
								<div class="input-group">
									<label class="label">${message}</label>
									<p class="text-danger">${message}</p>
								</div>
							</div>
						</div>

						<input type="hidden" id="buscarAction" name="buscarAction"
							value="login"></input>
						<div class="p-t-15">
							<input class="btn btn--radius-2 btn--blue" type="submit"
								name="btnLogin" value="Iniciar Sesión"></input>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<!-- Jquery JS-->
	<script src="login/vendor/jquery/jquery.min.js"></script>
	<!-- Vendor JS-->
	<script src="login/vendor/select2/select2.min.js"></script>
	<script src="login/vendor/datepicker/moment.min.js"></script>
	<script src="login/vendor/datepicker/daterangepicker.js"></script>

	<!-- Main JS-->
	<script src="login/js/global.js"></script>
	<script src="login/js/JSValidar.js"></script>
</body>
</html>