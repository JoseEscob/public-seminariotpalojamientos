<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis Solicitudes</title>
<%@ include file="Bootstrap.jsp"%>
<link rel="stylesheet"	href="css/reservas_style_sheet.css" />
</head>
<body>
<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>En construccion!</h2>
		<h2>Las solicitudes son las reservas de otros para mi alojamiento</h2>
		<div class="row">
			<div class="col-md-3 med-sidebar">
				<div class="med-sidebar-title">
					<h3>Filtrar</h3>
				</div>
			
				<select class="selectpicker">
					<option selected disabled value="">Estado de solicitud</option>
					<option disabled value="">Cargar los datos que se encuentran en la tabla tiposestadossolicitudes :)</option>
					
				</select>
				
				<label class="checkbox">
					<input type="checkbox" name="a" value="deshabbilitadas">Solicitudes deshabilitadas
				</label>
					
				
				
				
			</div>
			<div class="col-md-9 med-main">
				<div class="row">
					<div class="col-md-4">
						<div class=" med-solicitud">						
			 				Solicitud
						</div>
			 		</div>
			 		<div class="col-md-4">
						<div class=" med-solicitud">						
			 				Solicitud
						</div>
			 		</div>
			 		<div class="col-md-4">
						<div class=" med-solicitud">						
			 				Solicitud
						</div>
			 		</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class=" med-solicitud">						
			 				Solicitud
						</div>
			 		</div>
			 		<div class="col-md-4">
						<div class=" med-solicitud">						
			 				Solicitud
						</div>
			 		</div>
			 		<div class="col-md-4">
						<div class=" med-solicitud">						
			 				Solicitud
						</div>
			 		</div>
				</div>
		
			 	
			</div>
		</div>
	</div>

</body>
</html>