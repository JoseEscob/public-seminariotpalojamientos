<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="extra.ConstantesJSP"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mis Favoritos</title>
<link href="<%=ConstantesJSP.jspPub_PublicacionesListaGrillacss%>"
	rel="stylesheet">

<script type="text/javascript">
	$(document).ready(function() {
		$('#list').click(function(event) {
			event.preventDefault();
			$('#products .item').addClass('list-group-item');
		});
		$('#grid').click(function(event) {
			event.preventDefault();
			$('#products .item').removeClass('list-group-item');
			$('#products .item').addClass('grid-group-item');
		});
	});
</script>

</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Mis Publicaciones Favoritas</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
	</div>

	<div class="container">
		<div class="row">
			<div class="well well-sm">
				<strong>Category Title</strong>
				<div class="btn-group">
					<a href="#" id="list" class="btn btn-default btn-sm"><span
						class="glyphicon glyphicon-th-list"> </span>List</a> <a href="#"
						id="grid" class="btn btn-default btn-sm"><span
						class="glyphicon glyphicon-th"></span>Grid</a>
				</div>
			</div>
		</div>

		<div class="row">
			<%@ include file="mostrarListadoDePublicaciones.jsp"%>
		</div>
	</div>
</body>
</html>