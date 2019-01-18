<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Publicación - Alta</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>

	<div class="container">
		<h2>Publicación</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
	</div>

	<div class="container" id="a_ubicacionDomicilio">
		<h4>Ubicación - Zona del Domicilio</h4>
		<div class="row">
			<div class="col-md-4">
				<!-- Combos de Zona  -->
				<form method="POST" action="PublicacionServlet">					
					<div class="form-group">
						<label for="partidos">Partido</label> 
						<select id="partidos"class="form-control selectpicker" data-live-search="true" name="cmbPartido">
							<option selected value="null" disabled>Seleccionar un partido</option>
							<c:forEach items="${listaPartidos}" var="item">
								<option value="${item.idPartido}">${item.nombre}</option>
							</c:forEach>
						</select>
					</div>
					<noscript>
						<input type="submit">
					</noscript>
					<input type="hidden" name="accionPOST" value="cmbPartidoSubmit">

					<div class="form-group">
						<label for="localidades">Localidad</label> 
						<select id="localidades" class="form-control selectpicker" data-live-search="true" name="cmbLocalidad" disabled>
							<option selected value="null" disabled>Se debe seleccionar un partido antes</option>
						</select>
					</div>
				</form>
			</div>

			<div class="col-md-4">
				<!-- Ubicación en Mapa  -->
				<div class="form-group" align="center">
					<form class="form-horizontal" action="/action_page.php">
						<div class="form-group">
							<div class="col-sm-12">
								<button class="btn btn-info">Ver en Mapa</button>
							</div>
						</div>




					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="container" id="a_servicios">
		<h3>Servicios - Facilidades</h3>
		<p>Seleccione los Servicios que ofrece su publicación</p>
		<hr />
		<div class="row">
			<div class="col-md-4">
				<!--   -->
				<div class="col-md-6">
					<div class="form-group">
						<label class="checkbox"><input type="checkbox"
							name="fruit" value="">Checkbox 1</label>
					</div>
					<div class="form-group">
						<label class="checkbox"><input type="checkbox"
							name="fruit" value="">Checkbox 3</label>
					</div>
					<div class="form-group">
						<label class="checkbox"><input type="checkbox"
							name="fruit" value="">Checkbox 5</label>
					</div>

				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label class="checkbox"><input type="checkbox"
							name="fruit" value="">Checkbox 2</label>
					</div>
					<div class="form-group">
						<label class="checkbox"><input type="checkbox"
							name="fruit" value="">Checkbox 4</label>
					</div>
					<div class="form-group">
						<label class="checkbox"><input type="checkbox"
							name="fruit" value="">Checkbox 6</label>
					</div>
				</div>
			</div>

			<div class="col-md-4">Instalaciones del edificio Gimnasio
				Pileta Sauna Parrilla Servicios del edificio Agua corriente Cocina
				Electricidad Gas natural Seguridad Telefono Mascotas; Fumadores;
				Amoblada; Desayuno; Ambientes del departamento Balcon Baño Baulera
				Cochera Cocina Dormitorio Jardin Living comedor Suite Vestidor</div>


			<div class="col-md-4"></div>

		</div>
	</div>
<input type="file" class="filestyle" data-buttonText="Find file">

	<div class="container" id="footer"></div>

<script type="text/javascript">

$(document).ready(function(){
	$("[name='cmbPartido']").change(function(){
		//MAGIA
		$.post("PublicacionServlet",{"idPartido":$("[name='cmbPartido'] option:selected").val(),"accionPOST":"getLocalidades"}, function(result){
			$("#localidades").empty();
			$("#localidades").append($('<option selected disabled />').text("Seleccionar una localidad"));
			$("#localidades").prop("disabled",false);
			$.each(result.localidades, function(index, value){
				$("#localidades").append($('<option />').val(value.idLocalidad).text(value.nombre));
			});
			$("#localidades").selectpicker("refresh");			
		});
	});	
});


</script>
</body>
</html>