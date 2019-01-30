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
								value="jescobar@gmail.com">
						</div>

						<div class="row row-space"></div>
						<div class="input-group pass_show">
							<label class="label">Contraseña</label> <input
								class="input--style-4" type="password" name="txtPass"
								value="je123">
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
									<label class="label" style="color:red;">${message}</label>
								</div>
							</div>
						</div>

						<input type="hidden" id="buscarAction" name="accionPOST"
							value="login"></input>
						<div class="p-t-15">
							<input class="btn btn--radius-2 btn--blue" type="submit"
								name="btnLogin" value="Iniciar Sesión" onKeyPress="return checkSubmit(event)"></input>
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