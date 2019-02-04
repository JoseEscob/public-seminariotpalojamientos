<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="extra.ConstantesJSP"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=ConstantesJSP.jspPub_combosAjax%>"></script>
<title>Publicación - Alta</title>
</head>

<body>
	<%@ include file="Banner.jsp"%>
	<form method="POST" action="PublicacionServlet">
		<input type="hidden" name="accionPOST" value="nuevaPublicacion">
		<div class="container">
			<div class="row">
				<h2>Registro de Publicaciones</h2>
				<hr />
			</div>
			<!--Horizantal line divider  <div class="page-header"/> -->
			<div class="row" title="Ubicación - Zona del Domicilio">
				<div class="row col-md-12">
					<h3>Ubicación - Zona del Domicilio</h3>
					<hr />
				</div>
				<div class="row col-md-12">
					<div class="col-md-4">
						<div class="form-group">
							<label>Partido</label> <select id="partidos"
								class="form-control selectpicker" data-live-search="true"
								name="partido" required>
								<option selected value="" disabled>Seleccionar un
									partido</option>
								<c:forEach items="${listaPartidos}" var="item">
									<option value="${item.idPartido}">${item.nombre}</option>
								</c:forEach>
							</select>
						</div>
						<noscript>
							<input type="submit">
						</noscript>

						<div class="form-group">
							<label>Localidad</label> <select id="localidades"
								class="form-control selectpicker" data-live-search="true"
								name="localidad" disabled required>
								<option selected value="null" disabled>Se debe
									seleccionar un partido antes</option>
							</select>
						</div>
					</div>
					<div class="col-md-4">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label">Dirección Calle</label> <input
									type="text" name="calle" class="form-control"
									onkeypress="return soloLetras(event)" required="true"
									maxlength="50">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Altura </label> <input type="text" name="altura"
									class="form-control" onkeypress="return soloNros(event)"
									onpaste="return false" required="true" maxlength="6">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Código Postal </label> <input type="text"
									name="codPostal" class="form-control"
									onkeypress="return soloNros(event)" onpaste="return false"
									required="true" maxlength="6">
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label>Piso </label> <input type="text" name="piso"
									class="form-control" onkeypress="return soloNros(event)"
									onpaste="return false" maxlength="2" placeholder="">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Departamento </label> <input type="text"
									name="departamento" class="form-control"
									onkeypress="return soloLetras(event)" onpaste="return false"
									maxlength="1" placeholder="">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row" title="Características de la publicación">
				<div class="row col-md-12">
					<h3>Características principales</h3>
					<hr />
				</div>
				<div class="row col-md-12">
					<div class="col-md-4">
						<!-- Características - Superficie Espacio  -->

						<div class="form-group">
							<label>Tipo de Alojamiento</label> <select class="form-control"
								name="cmbTipoAlojamiento" required>

								<c:forEach items="${listaTiposAlojamientos}" var="item">
									<option value="${item.idTipoAlojamiento}">${item.descripcion}</option>
								</c:forEach>
							</select>
						</div>

						<div class="form-group">
							<label>Superficie Cubierta (m²)</label> <input type="text"
								name="superficieCubierta" class="form-control"
								onkeypress="return soloNros(event)" onpaste="return false"
								required="true" maxlength="2" pattern="\d*">
						</div>

						<div class="form-group">
							<label>Superficie Descubierta (m²)</label> <input type="text"
								name="superficieDescubierta" class="form-control"
								onkeypress="return soloNros(event)" onpaste="return false"
								required="true" maxlength="2" pattern="\d*">
						</div>

						<div class="form-group">
							<label>Antigüedad en años</label> <input type="text"
								name="aniosAntiguedad" class="form-control" required="true"
								maxlength="3" pattern="\d*">
						</div>
					</div>

					<div class="col-md-4">
						<!-- Características - Combos de cantidades  -->
						<div class="col-md-6">
							<div class="form-group">
								<label>Cant. Personas</label>
								<div class="input-group number-spinner">
									<span class="input-group-btn data-dwn">
										<div class="btn btn-default btn-info" data-dir="dwn">
											<span class="glyphicon glyphicon-minus"></span>
										</div>
									</span> <input type="text" class="form-control text-center" readonly
										value="1" min="1" max="30" name="cantidadPersonas"> <span
										class="input-group-btn data-up">
										<div class="btn btn-default btn-info" data-dir="up">
											<span class="glyphicon glyphicon-plus"></span>
										</div>
									</span>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group" style="">
								<label>Cant. Ambientes</label>
								<div class="input-group number-spinner">
									<span class="input-group-btn data-dwn">
										<div class="btn btn-default btn-info" data-dir="dwn">
											<span class="glyphicon glyphicon-minus"></span>
										</div>
									</span> <input type="text" class="form-control text-center" readonly
										value="1" min="1" max="10" name="cantidadAmbientes"> <span
										class="input-group-btn data-up">
										<div class="btn btn-default btn-info" data-dir="up">
											<span class="glyphicon glyphicon-plus"></span>
										</div>
									</span>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group" style="">
								<label>Cant. Dormitorios</label>
								<div class="input-group number-spinner">
									<span class="input-group-btn data-dwn">
										<div class="btn btn-default btn-info" data-dir="dwn">
											<span class="glyphicon glyphicon-minus"></span>
										</div>
									</span> <input type="text" class="form-control text-center" readonly
										value="1" min="1" max="20" name="cantidadDormitorios">
									<span class="input-group-btn data-up">
										<div class="btn btn-default btn-info" data-dir="up">
											<span class="glyphicon glyphicon-plus"></span>
										</div>
									</span>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group" style="">
								<label>Cant. Baños</label>
								<div class="input-group number-spinner">
									<span class="input-group-btn data-dwn">
										<div class="btn btn-default btn-info" data-dir="dwn">
											<span class="glyphicon glyphicon-minus"></span>
										</div>
									</span> <input type="text" class="form-control text-center" readonly
										value="1" min="1" max="10" name="cantidadBaños"> <span
										class="input-group-btn data-up">
										<div class="btn btn-default btn-info" data-dir="up">
											<span class="glyphicon glyphicon-plus"></span>
										</div>
									</span>
								</div>
							</div>
						</div>


						<div class="col-md-12">
							<div class="form-group">
								<label>Si el Huésped lo desea ¿Puede variar la Cantidad
									de Personas límite?</label>
								<div class="col-md-6">
									<label class="radio"><input type="radio"
										name="chkPuedeVariarCantPersonas" value="true">Si</label>
								</div>
								<div class="col-md-6">
									<label class="radio"><input type="radio"
										name="chkPuedeVariarCantPersonas" value="false">No</label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-4">
						<!-- Características - Precio  -->
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="form-group">
									<label class="radio"><input type="radio"
										name="chkExpensas" value="false">No tiene expensas</label>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="radio"><input type="radio"
										name="chkExpensas" value="true">Tiene expensas</label>
								</div>
							</div>
						</div>
						</br>
						<div class="col-md-12 form-group">
							<label>Precio Expensas</label>
							<div class="input-group">
								<span class="input-group-addon">$</span> <input type="text"
									class="form-control" onkeypress="return soloNros(event)"
									onpaste="return false" name="precioExpensas"
									value="${obj.precioExpensas}" maxlength="6" aria-label="Pesos">
								<span class="input-group-addon">.00</span>
							</div>
						</div>
						<div class="col-md-12">
							<br>
						</div>
						<div class="col-md-12 form-group">
							<label>Precio por noche</label>
							<div class="input-group">
								<span class="input-group-addon">$</span> <input type="text"
									class="form-control" onkeypress="return soloNros(event)"
									onpaste="return false" name="precioNoche"
									value="${obj.precioNoche}" required="true" maxlength="6"
									aria-label="Pesos"> <span class="input-group-addon">.00</span>
							</div>
						</div>

					</div>

				</div>
				<div class="row col-md-12">
					<div class="form-group col-md-12">
						<label>Descripción</label>
						<textarea class="form-control" name="descripcion" id="descripcion"
							maxlength="300" rows="3" cols="50"
							placeholder="Breve descripción del alojamiento..."
							style="resize: none;"></textarea>
					</div>
				</div>
			</div>
			<div class="row" id="ServiciosDeLaPublicacion">
				<div class="row col-md-12">
					<h3>Servicios - Facilidades</h3>
					<hr />
					<p>Seleccione los servicios que ofrece su alojamiento/
						publicación</p>
				</div>
				<div class="row col-md-12">
					<div class="col-md-4">
						<c:forEach items="${listaTipoServicios}" var="objTipoServicio">
							<c:if test="${objTipoServicio.idTipoServicio eq 1}">
								<div class="form-group">
									<label class="checkbox"><input type="checkbox"
										name="chklistServicios" value="${objTipoServicio.idServicio}">${objTipoServicio.descripcion}</label>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<div class="col-md-4">
						<c:forEach items="${listaTipoServicios}" var="objTipoServicio">
							<c:if test="${objTipoServicio.idTipoServicio eq 2}">
								<div class="form-group">
									<label class="checkbox"><input type="checkbox"
										name="chklistServicios" value="${objTipoServicio.idServicio}">${objTipoServicio.descripcion}</label>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<div class="col-md-4">
						<c:forEach items="${listaTipoServicios}" var="objTipoServicio">
							<c:if test="${objTipoServicio.idTipoServicio eq 3}">
								<div class="form-group">
									<label class="checkbox col-xs-12"><input
										type="checkbox" name="chklistServicios"
										value="${objTipoServicio.idServicio}">${objTipoServicio.descripcion}</label>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>

			</div>


			<!--<div class="row" id="ImagenesDeLaPublicacion">
				<div class="row col-md-12">
					<h3>Imágenes del alojamiento</h3>
					<hr />
				</div>
				<div class="row col-md-12">				
					<form action="UploadFilesServlet" method="post" enctype="multipart/form-data">
						<label for="imagenes" class="btn btn-info">Seleccionar imagenes</label>
					
						<input type="file" id="imagenes" name="archivo" accept="image/jpeg,image/gif,image/png" multiple style="visibility: hidden;"/>
						<input type="hidden" name="accionPOST" value="cargarImagenesPublicacion"/>
						<input type="hidden" name="idPublicacion" value="1"/>
							
						<input type="submit"/>
					</form> 
				</div>


			</div>-->

			<div class="row">
				<input type="submit" class="btn btn-success" name="subir"
					value="Cargar datos">
			</div>
		</div>
	</form>
	<div class="container" id="footer"></div>


	<script src="<%=ConstantesJSP.jspPub_combosAjax%>"></script>
</body>
</html>