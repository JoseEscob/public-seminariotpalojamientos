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
						<select id="partidos"class="form-control selectpicker" data-live-search="true" name="partido">
							<option selected value="null" disabled>Seleccionar un partido</option>
							<c:forEach items="${listaPartidos}" var="item">
								<option value="${item.idPartido}">${item.nombre}</option>
							</c:forEach>
						</select>
					</div>
					<noscript>
						<input type="submit">
					</noscript>
					<input type="hidden" name="accionPOST" value="nuevaPublicacion">

					<div class="form-group">
						<label for="localidades">Localidad</label> 
						<select id="localidades" class="form-control selectpicker" data-live-search="true" name="localidad" disabled>
							<option selected value="null" disabled>Se debe seleccionar un partido antes</option>
						</select>
					</div>
			</div>
			<div class="col-md-4">
				<div class="col-md-12">
					<div class="form-group">
						<label for="calle" class="control-label">Dirección Calle</label>
						<input type="text" name="calle"  class="form-control"
								onkeypress="return soloLetras(event)"
								required="true"  maxlength="50">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
							<label for="altura" class="control-label">Altura </label>
							<input type="text" name="altura" id="altura" class="form-control"
									onkeypress="return soloNros(event)" onpaste="return false"
									required="true" maxlength="8">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
							<label for="codPostal" class="control-label">Código Postal </label>
							<input type="text" name="codPostal" id="codPostal" class="form-control"
									onkeypress="return soloNros(event)" onpaste="return false"
									required="true" maxlength="6">
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
							<label for="piso" class="control-label">Piso </label>
							<input type="text" name="piso" id="piso" class="form-control"
									onkeypress="return soloNros(event)" onpaste="return false"
									required="true" maxlength="2" placeholder="">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
							<label for="dpto" class="control-label">Departamento </label>
							<input type="text" name="departamento" id="dpto" class="form-control"
									onkeypress="return soloLetras(event)" onpaste="return false"
									required="true" maxlength="1" placeholder="">
					</div>
				</div>

			</div>

			
			<!-- Ubicación en Mapa 
			<div class="col-md-4">
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
			-->
		</div>
			<div class="container" id="a_caracteristicasPrincipales">
		<h3>Características principales</h3>
		<hr />
		<div class="row">
			<div class="col-md-4"> <!-- Características - Superficie Espacio  -->
						<div class="form-group" style="">
						<label>Tipo de Alojamiento</label>
						<select class="form-control selectpicker" name="tAlojamiento" id="tAlojamiento">
							<option selected value="null" disabled>Seleccionar un tipo de alojamiento</option>
							<c:forEach items="${listaTiposAlojamientos}" var="item">
								<option value="${item.idTipoAlojamiento}">${item.descripcion}</option>
							</c:forEach>
						</select>
					</div>	

					<div class="form-group">
						<label for="calle" class="control-label">Superficie Cubierta (m²)</label>
						<input type="text" name="superficieCubierta"  class="form-control"
								onkeypress="return soloNros(event)" onpaste="return false"
								required="true" maxlength="2">
					</div>

					<div class="form-group">
						<label for="calle" class="control-label">Superficie Descubierta (m²)</label>
						<input type="text" name="superficieDescubierta"  class="form-control"
								onkeypress="return soloNros(event)" onpaste="return false"
								required="true" maxlength="2">
					</div>
			</div>

			<div class="col-md-4"> <!-- Características - Combos de cantidades  -->
				<div class="col-md-6">
					<div class="form-group">
						<label>Cant. Personas</label>
						<div class="input-group number-spinner">
							<span class="input-group-btn data-dwn">
								<div class="btn btn-default btn-info" data-dir="dwn"><span class="glyphicon glyphicon-minus"></span></div>
							</span>
							<input type="text" class="form-control text-center" readonly value="1" min="1" max="30" name="cantidadPersonas">
							<span class="input-group-btn data-up">
								<div class="btn btn-default btn-info" data-dir="up"><span class="glyphicon glyphicon-plus"></span></div>
							</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group" style="">
						<label>Cant. Ambientes</label>
						<div class="input-group number-spinner">
							<span class="input-group-btn data-dwn">
								<div class="btn btn-default btn-info" data-dir="dwn"><span class="glyphicon glyphicon-minus"></span></div>
							</span>
							<input type="text" class="form-control text-center" readonly value="1" min="1" max="10" name="cantidadAmbientes">
							<span class="input-group-btn data-up">
								<div class="btn btn-default btn-info" data-dir="up"><span class="glyphicon glyphicon-plus"></span></div>
							</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group" style="">
						<label>Cant. Dormitorios</label>
						<div class="input-group number-spinner">
							<span class="input-group-btn data-dwn">
								<div class="btn btn-default btn-info" data-dir="dwn"><span class="glyphicon glyphicon-minus"></span></div>
							</span>
							<input type="text" class="form-control text-center" readonly value="1" min="1" max="20" name="cantidadDormitorios">
							<span class="input-group-btn data-up">
								<div class="btn btn-default btn-info" data-dir="up"><span class="glyphicon glyphicon-plus"></span></div>
							</span>
						</div>
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group" style="">
						<label>Cant. Baños</label>
						<div class="input-group number-spinner">
							<span class="input-group-btn data-dwn">
								<div class="btn btn-default btn-info" data-dir="dwn"><span class="glyphicon glyphicon-minus"></span></div>
							</span>
							<input type="text" class="form-control text-center" readonly value="1" min="1" max="10" name="cantidadBaños">
							<span class="input-group-btn data-up">
								<div class="btn btn-default btn-info" data-dir="up"><span class="glyphicon glyphicon-plus"></span></div>
							</span>
						</div>
					</div>
				</div>
				
			</div>

			<div class="col-md-4"> <!-- Características - Precio  -->
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group">
							<label class="radio"><input type="radio" name="chkExpensas" value="0">No tiene expensas</label>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="radio"><input type="radio" name="chkExpensas" value="1">Tiene expensas</label>
						</div>
					</div>
				</div>
				</br>
				<div class="col-md-12 form-group">
					<label for="precioExpensas" class="control-label">Precio Expensas</label>
					<div class="input-group">
					  <span class="input-group-addon">$</span>
					  <input type="text" class="form-control" onkeypress="return soloNros(event)" onpaste="return false"
									name="precioExpensas" value="${obj.precioExpensas}" required="true" 
									maxlength="6" aria-label="Pesos">
					  <span class="input-group-addon">.00</span>
					</div>
				</div>

				<div class="col-md-12 form-group">
					<label for="precioNoche" class="control-label">Precio por noche</label>
					<div class="input-group">
					  <span class="input-group-addon">$</span>
					  <input type="text" class="form-control" onkeypress="return soloNros(event)" onpaste="return false"
									name="precioNoche" value="${obj.precioNoche}" required="true" 
									maxlength="6" aria-label="Pesos">
					  <span class="input-group-addon">.00</span>
					</div>
				</div>

			</div>

		</div>
	</div>
	<input type="submit" name="subir" value="Cargar datos" class="btn btn-success">
	</form>

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

$(function() {
    var action;
    $(".number-spinner div").mousedown(function () {
        btn = $(this);
        input = btn.closest('.number-spinner').find('input');

    	if (btn.attr('data-dir') == 'up') {
            action = setInterval(function(){
                if ( input.attr('max') == undefined || parseInt(input.val()) < parseInt(input.attr('max')) ) {
                    input.val(parseInt(input.val())+1);
                }else{
                    clearInterval(action);
                }
            }, 50);
    	} else {
            action = setInterval(function(){
                if ( input.attr('min') == undefined || parseInt(input.val()) > parseInt(input.attr('min')) ) {
                    input.val(parseInt(input.val())-1);
                }else{
                    btn.prop("disabled", true);
                    clearInterval(action);
                }
            }, 50);
    	}
    }).mouseup(function(){
        clearInterval(action);
    });
});

$(document).ready(function(){
	$("[name='partido']").change(function(){
		//MAGIA
		$.post("PublicacionServlet",{"idPartido":$("[name='partido'] option:selected").val(),"accionPOST":"getLocalidades"}, function(result){
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