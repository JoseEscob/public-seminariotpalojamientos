<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Publicaciones</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" />
</head>
<body>
<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet">

	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Publicaciones</h2>
		<hr/>
		<div class="col-md-3">
			Filtros
		</div>
		<div class="col-md-9">
			<!--Foreach, para la paginacion hay que traer primero todos los datos, despues dividirlo por la cantidad de registros que vamos a mostrar y te daria la cantidad de paginas-->
			<div class="row">
				<div class="col-md-6">
					{carrousel}
				</div>
				<div class="col-md-6">
					<h3>{Titulo o nombre de la publicacion}</h3>
					<hr/>
					{datos de la publicacion}
					{puntuacion}
					<div class="row container">
						<button class="btn btn-success">Solicitar reserva</button>
					</div>
				</div>
			</div>
			<hr/>

			<div class="row">
				<div class="col-md-6">
					{carrousel}
				</div>
				<div class="col-md-6">
					<h3>{Titulo o nombre de la publicacion}</h3>
					<hr/>
					{datos de la publicacion}
					{puntuacion}
					<div class="row container">
						<button class="btn btn-success">Solicitar reserva</button>
					</div>
				</div>
			</div>
			<hr/>

			<ul class="pagination">
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">6</a></li>
				<li class="disabled"><a href="#">7</a></li>
				<li class="disabled"><a href="#">8</a></li>
				<li class="disabled"><a href="#">9</a></li>
			</ul>
		</div>
		
	</div>
<%@ include file="Footer.jsp"%>

<script src="bootstrap3/jquery/jquery-3.3.1.js"></script>
<script src="bootstrap3/js/bootstrap.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>
</body>
</html>