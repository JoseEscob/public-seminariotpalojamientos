<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Publicación - Crear Comentario</title>
</head>
<body>

	<div class="container">
		<h4>${message}</h4>
	</div>
	<div class="container">
		<h2>Hacer un comentario devolución de la publicación</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form action="PublicacionServlet" method="POST">
					<!--  accept-charset="UTF-8"  -->
					<div class="media">
						<div class="media-left media-middle">
							<img src="${sessionScope.sessionUser.rutaFotoPerfil}"
								class="media-object" style="height: 50px; width: 50px;">
						</div>
						<div class="media-body">
							<div class="col-md-12">
								<h4 class="media-heading">
									${sessionScope.sessionUser.nombre}&nbsp;${sessionScope.sessionUser.apellido}
								</h4>
							</div>


							<div class="col-md-12">
								<div class="form-group col-md-6">
									<label>Puntaje</label> <select class="form-control"
										name="cmbPuntaje" required>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
								</div>
							</div>
							<div class="col-md-12">
								<textarea class="form-control" name="comentarioDescripcion"
									maxlength="300" rows="3" cols="50"
									placeholder="Ingresá tu comentario..." style="resize: none;"></textarea>
							</div>
							<div class="col-md-12">
								<br> <input type="hidden" name="idPublicacion"
									value="${vistaPublicacion.publicacion.idPublicacion}">
							</div>
							<div class="col-md-12" align="center">
								<input type="hidden" name="accionPOST"
									value="guardarComentarioUsuario"> <input type="submit"
									class="btn btn-primary" name="btnComentario" value="Guardar ">
							</div>
						</div>
					</div>
					<hr />
				</form>
			</div>
		</div>
		<div class="row"></div>
		<div class="row">
			<c:choose>
				<c:when test="">
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

</body>
</html>