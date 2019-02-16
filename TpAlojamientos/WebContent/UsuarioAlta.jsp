<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="extra.ConstantesJSP"%>
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
<link href="<%=ConstantesJSP.jspLogin_designIconicFont_css%>"
	rel="stylesheet" media="all">
<link href="<%=ConstantesJSP.jspLogin_fontAwe_css%>" rel="stylesheet"
	media="all">

<!-- Font special for pages-->
<link href="<%=ConstantesJSP.jspLogin_fontAweGoogle_css%>"
	rel="stylesheet">

<!-- Vendor CSS-->
<link href="<%=ConstantesJSP.jspLogin_select2_css%>" rel="stylesheet"
	media="all">
<link href="<%=ConstantesJSP.jspLogin_daterangepicker_css%>"
	rel="stylesheet" media="all">

<!-- Main CSS-->
<link href="<%=ConstantesJSP.jspLogin_main_css%>" rel="stylesheet"
	media="all">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta Usuario - Completar Formulario</title>
</head>
<body>
	<!-- Al incluir el banner me parece q ya incluye lo del HEAD y los script del final -->
	<div class="page-wrapper bg-skyblue p-t-130 p-b-100 font-poppins">
		<div class="wrapper wrapper--w680">
			<div class="card card-4">
				<div class="card-body">
					<h1 class="title">Registración</h1>
					<!-- ${pageContext.request.contextPath}/ -->
					<form method="POST" action="UsuarioServlet">
						<div class="col-2">
							<div class="input-group">
								<label class="label">Email</label> <input class="input--style-4"
									name="mail" value="asd@gmail.com">
							</div>
						</div>
						<div class="row row-space">
							<div class="col-2">
								<div class="input-group pass_show">
									<label class="label">Contraseña</label> <input
										class="input--style-4 " type="password" maxlength="20"
										name="claveUno" value="asd123">
								</div>
							</div>
							<div class="col-2">
								<div class="input-group">
									<label class="label">Repita Contraseña</label> <input
										class="input--style-4" type="password" maxlength="20"
										name="claveDos" value="asd123" onpaste="return false">
								</div>
							</div>

						</div>
						<h3 class="title">Datos Personales</h3>
						<div class="row row-space">
							<div class="col-2">
								<div class="input-group">
									<label class="label">Nombre</label> <input
										class="input--style-4" onkeypress="return soloLetras(event)"
										type="text" name="nombre" maxlength="50" value="José">
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
											pattern="^\d{2}-\d{2}-\d{4}$" name="fechaNac" maxlength="10"
											placeholder="dd-MM-yyyy" required="true" readonly> <i
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
											type="radio" name="rdbSexo" value="true"> <span
											class="checkmark"></span>
										</label> <label class="radio-container">Femenino <input
											type="radio" checked name="rdbSexo" value="false"> <span
											class="checkmark"></span>
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


							<div class="col-2">
								<div class="input-group">
									<label class="label">Nro de Teléfono</label> <input
										class="input--style-4" onkeypress="return soloNros(event)"
										type="text" name="nroTelefono" maxlength="20">
								</div>
							</div>
						</div>
						<div class="row row-space">
							<div class="col-4">
								<div class="input-group">
									<label class="label" style="color: red;">${message}</label>
								</div>
							</div>
						</div>
						<div class="p-t-15">
							<input type="hidden" id="buscarAction" name="accionPOST"
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
	<script src="<%=ConstantesJSP.jspLogin_jquery_js%>"></script>
	<!-- Vendor JS-->
	<script src="<%=ConstantesJSP.jspLogin_select2_js%>"></script>
	<script src="<%=ConstantesJSP.jspLogin_moment_js%>"></script>
	<script src="<%=ConstantesJSP.jspLogin_daterangepicker_js%>"></script>

	<!-- Main JS-->
	<script src="<%=ConstantesJSP.jspLogin_global_js%>"></script>
	<script src="<%=ConstantesJSP.jspValidar_js%>"></script>
</body>
</html>