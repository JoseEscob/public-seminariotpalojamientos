<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Page</title>
</head>
<body>

	<div class="container">
		<h2>Test Page</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
	</div>

	<div class="container" id="a_ubicacionDomicilio">
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
							<option value="57">Tigre</option>
							<option value="84">Pilar</option>
						</select>
					</div>
					<noscript>
						<input type="submit">
					</noscript>
					<input type="hidden" name="actionPublicacion" value="cmbPartidoSubmit">

					<div class="form-group">
						<label for="localidades">Localidad</label> 
						<div id="loc"></div>
						<select id="localidades" class="form-control selectpicker" data-live-search="true" name="cmbLocalidad">
							<option selected value="null" disabled>Seleccionar una localidad</option>
						</select>
					</div>
				</form>
				<c:when test="${fn:length(vistaPublicacion.comentarios) gt 0}">
						<c:forEach items="${vistaPublicacion.comentarios}" var="coment">
							<div class="media">
								<div class="media-left media-middle">
									<img src="${coment.usuario.rutaFotoPerfil}"
										class="media-object" style="height: 50px; width: 50px;">
								</div>
								<div class="media-body">
									<h4 class="media-heading">
										<a> <c:out value="${coment.usuario.nombre}"></c:out>
										</a> <small><i> ${coment.comentario.fechaComentario} </i></small>
									</h4>
									<p>
										<b>Puntuación ${coment.comentario.puntaje}&nbsp;/5</b>
									</p>
									<p>${coment.comentario.descripcion}</p>
								</div>
							</div>
							<hr />
						</c:forEach>
					</c:when>
			</div>
		</div>
	</div>

<script src="bootstrap3/jquery/jquery-3.3.1.js"></script>
<script src="bootstrap3/js/bootstrap.min.js"></script>
<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("[name='cmbPartido']").change(function(){
		//Working on it
	
		$.post("TestServlet",{"cmbPartido":$("[name='cmbPartido'] option:selected").val(), "accionPublicacion":"getLocalidades"}, function(result){
			$("#localidades").empty();
			$("#localidades").append($('<option />').text("Seleccionar una localidad"));
			$.each(result.localidades, function(index, value){
				$("#localidades").append($('<option />').val(value.idLocalidad).text(value.nombre));
			});
			$("#localidades").selectpicker("refresh");
			/*var $select = $('<select />');
			$select.addClass("form-control selectpicker");
			$select.attr({'data-live-search':true, name:"cmbLocalidad"});
			$.each(result.localidades, function(index, value){
				$select.append($('<option />').val(value.idLocalidad).text(value.nombre));
				
				
			});
			$("#loc").append($select);*/
			
			/*
			var $select = $('<select />',{'class':'selectpicker'});
			$.each(result.localidades, function(index, value){
				$select.append($('<option />').val(value.idLocalidad).text(value.nombre));
			});
			$select.appendTo("#loc").selectpicker("refresh");*/
		});				
	});
});	


</script>



</body>
</html>