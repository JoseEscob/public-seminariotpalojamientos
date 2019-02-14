<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="extra.ConstantesJSP"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=ConstantesJSP.jspPub_combosAjax%>"></script>
<title>Publicación - Modificación</title>
</head>

<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<div class="row">
			<h2>Registro de Publicaciones</h2>
			<hr />
		</div>
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
		<div class="row well">
			<div class="row col-md-6">
				<form action="UploadFilesServlet" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="accionPOST" value="cargarImagenesEdit">
					<input type="hidden" name="idPublicacion" value="${idPublicacion}">

					<input type="submit" name="btnEditarImagenes"
						value="Editar imagenes de la publicacion" class="btn btn-primary">
				</form>
			</div>
			<div class="row col-md-6">
				<div class="alert alert-info">
					<h4>Seleccione esta opción para editar solo las imagenes</h4>
				</div>
			</div>
		</div>
		<form method="POST" action="PublicacionServlet">
			<input type="hidden" name="accionPOST" value="updatePublicacion">
			<input type="hidden" name="idPublicacion" value="${idPublicacion}">



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
								<c:choose>
									<c:when test="${not empty objPartido }">
										<option selected value="${objPartido.idPartido }">${objPartido.nombre}</option>
									</c:when>
									<c:otherwise>
										<option selected value="" disabled>Seleccionar un
											partido</option>
									</c:otherwise>
								</c:choose>
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
								name="localidad" required>
								<c:choose>
									<c:when test="${not empty objLocalidad }">
										<option selected value="${objLocalidad.idLocalidad }">${objLocalidad.nombre}</option>
									</c:when>
									<c:otherwise>
										<option selected value="" disabled>Seleccionar una
											localidad</option>
									</c:otherwise>
								</c:choose>
								<c:forEach items="${listaLodalicadad}" var="item">
									<option value="${item.idLocalidad}">${item.nombre}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-md-4">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label">Dirección Calle</label> <input
									type="text" name="calle" class="form-control"
									onkeypress="return soloLetras(event)" required="true"
									maxlength="50" value="${objPublicacion.calle}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Altura </label> <input type="text" name="altura"
									class="form-control" onkeypress="return soloNros(event)"
									onpaste="return false" required="true" maxlength="6"
									value="${objPublicacion.altura}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Código Postal </label> <input type="text"
									name="codPostal" class="form-control"
									onkeypress="return soloNros(event)" onpaste="return false"
									required="true" maxlength="6"
									value="${objPublicacion.codPostal}">
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label>Piso </label> <input type="text" name="piso"
									class="form-control" onkeypress="return soloNros(event)"
									onpaste="return false" maxlength="2" placeholder="4"
									value="${objPublicacion.piso}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Departamento </label> <input type="text"
									name="departamento" class="form-control"
									onkeypress="return soloLetras(event)" onpaste="return false"
									maxlength="1" placeholder="B" value="${objPublicacion.dpto}">
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label">ID Publicación</label> <input
									type="text" name="calle" class="form-control"
									onkeypress="return soloNros(event)" readonly maxlength="50"
									value="${objPublicacion.idPublicacion}">
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label">Fecha de Creación</label> <input
									type="text" name="calle" class="form-control"
									onkeypress="return soloNros(event)" readonly maxlength="50"
									value="${objPublicacion.fechaAlta}">
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label">Última modificación</label> <input
									type="text" name="calle" class="form-control"
									onkeypress="return soloNros(event)" readonly maxlength="50"
									value="${objPublicacion.fechaUltModificado}">
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
									<c:if
										test="${item.idTipoAlojamiento eq objPublicacion.idTipoAlojamiento}">
										<option selected value="${item.idTipoAlojamiento}">${item.descripcion}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>

						<div class="form-group">
							<label>Superficie Cubierta (m²)</label> <input type="text"
								name="superficieCubierta" class="form-control"
								onkeypress="return soloNros(event)" onpaste="return false"
								required="true" maxlength="2" pattern="\d*"
								value="${objPublicacion.supCubierta}">
						</div>

						<div class="form-group">
							<label>Superficie Descubierta (m²)</label> <input type="text"
								name="superficieDescubierta" class="form-control"
								onkeypress="return soloNros(event)" onpaste="return false"
								required="true" maxlength="2" pattern="\d*"
								value="${objPublicacion.supDescubierta}">
						</div>

						<div class="form-group" data-toggle="tooltip"
							title="Si los años de Antigüedad se cargan con cero significa que es un alojamiento a estrenar">
							<label>Antigüedad en años</label> <input type="text"
								name="aniosAntiguedad" class="form-control" required
								maxlength="3" pattern="\d*"
								value="${objPublicacion.aniosAntiguedad}">
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
										min="1" max="30" name="cantidadPersonas"
										value="${objPublicacion.cantPersonas}"> <span
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
										min="1" max="10" name="cantidadAmbientes"
										value="${objPublicacion.cantAmbientes}"> <span
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
										min="1" max="20" name="cantidadDormitorios"
										value="${objPublicacion.cantHabitaciones}"> <span
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
								<label>Cant. Baños</label>
								<div class="input-group number-spinner">
									<span class="input-group-btn data-dwn">
										<div class="btn btn-default btn-info" data-dir="dwn">
											<span class="glyphicon glyphicon-minus"></span>
										</div>
									</span> <input type="text" class="form-control text-center" readonly
										min="1" max="10" name="cantidadBaños"
										value="${objPublicacion.cantBanios}"> <span
										class="input-group-btn data-up">
										<div class="btn btn-default btn-info" data-dir="up">
											<span class="glyphicon glyphicon-plus"></span>
										</div>
									</span>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="form-group" data-toggle="tooltip"
								title="Si se marca que sí, las reservas podrán ser solicitadas con más cantidad de huéspedes que la cantidad apta de personas para el alojamiento ">
								<label>Si el Huésped lo desea ¿Puede variar la Cantidad
									de Personas límite?</label>

								<c:choose>
									<c:when test="${objPublicacion.chkPuedeVariarCantPersonas}">
										<label class="radio-inline"><input type="radio"
											name="chkPuedeVariarCantPersonas" value="true" checked>Si</label>
										<label class="radio-inline"><input type="radio"
											name="chkPuedeVariarCantPersonas" value="false">No</label>
									</c:when>
									<c:otherwise>
										<label class="radio-inline"><input type="radio"
											name="chkPuedeVariarCantPersonas" value="true">Si</label>
										<label class="radio-inline"><input type="radio"
											name="chkPuedeVariarCantPersonas" value="false" checked>No</label>
									</c:otherwise>
								</c:choose>

							</div>
						</div>
					</div>


					<div class="col-md-4">
						<!-- Características - Precio  -->
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="form-group">
									<c:choose>
										<c:when test="${objPublicacion.chkExpensas}">

											<label class="radio-inline"><input type="radio"
												name="chkExpensas" value="false">No tiene expensas</label>
										</c:when>
										<c:otherwise>

											<label class="radio-inline"><input type="radio"
												name="chkExpensas" value="false" checked>No tiene
												expensas</label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<c:choose>
										<c:when test="${objPublicacion.chkExpensas}">
											<label class="radio-inline"><input type="radio"
												name="chkExpensas" value="true" checked>Tiene
												expensas</label>
										</c:when>
										<c:otherwise>
											<label class="radio-inline"><input type="radio"
												name="chkExpensas" value="true">Tiene expensas</label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<br>
						<div class="col-md-12 form-group">
							<label>Precio Expensas</label>
							<div class="input-group">
								<span class="input-group-addon">$</span> <input type="text"
									class="form-control" onkeypress="return soloNros(event)"
									onpaste="return false" name="precioExpensas"
									value="${objPublicacion.precioExpensas}" maxlength="6"
									aria-label="Pesos"> <span class="input-group-addon">.00</span>
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
									value="${objPublicacion.precioNoche}" required="true"
									maxlength="6" aria-label="Pesos"> <span
									class="input-group-addon">.00</span>
							</div>
						</div>

					</div>
				</div>

				<div class="row col-md-12">
					<div class="form-group col-md-12">
						<label>Descripción</label>
						<textarea class="form-control" name="descripcion" id="descripcion"
							maxlength="300" rows="3" cols="50"
							placeholder="Breve descripción del alojamiento..." value=""
							style="resize: none;" pattern="[^()/><\][\\\x22,;|]+">${objPublicacion.descripcion}</textarea>
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
									<c:choose>
										<c:when
											test="${objTipoServicio.idServicio eq objPublicacion.idTipoAlojamiento}">
											<label class="checkbox"><input type="checkbox"
												checked name="chklistServicios"
												value="${objTipoServicio.idServicio}">${objTipoServicio.descripcion}</label>
										</c:when>
										<c:otherwise>
											<label class="checkbox"><input type="checkbox"
												name="chklistServicios"
												value="${objTipoServicio.idServicio}">${objTipoServicio.descripcion}</label>
										</c:otherwise>
									</c:choose>


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
		</form>
	</div>

	<script src="<%=ConstantesJSP.jspPub_combosAjax%>"></script>
</body>
</html>