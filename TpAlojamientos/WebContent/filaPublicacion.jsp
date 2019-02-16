<td>${objPublicacion.idPublicacion}</td>
<td>${objPublicacion.idUsuario}</td>
<td>${objPublicacion.idTipoAlojamiento}</td>
<td>${objPublicacion.descripcion}</td>
<td>${objPublicacion.idLocalidad}</td>
<td>${objPublicacion.codPostal}</td>
<td>${objPublicacion.coordenadas}</td>
<td>${objPublicacion.calle}</td>
<td>${objPublicacion.altura}</td>
<td>${objPublicacion.piso}</td>
<td>${objPublicacion.dpto}</td>
<td>${objPublicacion.supCubierta}</td>
<td>${objPublicacion.supDescubierta}</td>
<td>${objPublicacion.precioExpensas}</td>
<td>${objPublicacion.precioNoche}</td>
<td>${objPublicacion.chkPuedeVariarCantPersonas}</td>
<td>${objPublicacion.cantPersonas}</td>
<td>${objPublicacion.cantAmbientes}</td>
<td>${objPublicacion.cantBanios}</td>
<td>${objPublicacion.cantHabitaciones}</td>
<td>${objPublicacion.aniosAntiguedad}</td>
<td>${objPublicacion.fechaAlta}</td>
<td>${objPublicacion.fechaUltModificado}</td>
<td>${objPublicacion.puntaje}</td>
<td><c:choose>
		<c:when test="${objPublicacion.verificado}">SI</c:when>
		<c:otherwise>NO</c:otherwise>
	</c:choose></td>
<td>
<td><c:choose>
		<c:when test="${objPublicacion.habilitado}">SI</c:when>
		<c:otherwise>NO</c:otherwise>
	</c:choose></td>
<td>
	<div>
		<c:url value="PublicacionServlet?" var="urlVerPublicacion">
			<c:param name="accionGET" value="VerPublicacion" />
			<c:param name="idPublicacion" value="${objPublicacion.idPublicacion}" />
		</c:url>

		<a href="${urlVerPublicacion}" class="btn btn-default"
			data-toggle="tooltip" title="Ver Publicación"> <span
			class="glyphicon glyphicon-eye-open"></span>
		</a>
	</div>
	<div>
		<c:url value="PublicacionServlet?" var="urlVerificadoTrue">
			<c:param name="accionGET" value="admGestionarVerificacionPublicacion" />
			<c:param name="verificado" value="true" />
			<c:param name="idPublicacion" value="${objPublicacion.idPublicacion}" />
		</c:url>

		<a href="${urlVerificadoTrue}" class="btn btn-primary"
			data-toggle="tooltip" title="Marcar como verificado"> <span
			class="glyphicon glyphicon-ok-circle"></span>
		</a>

	</div>

	<div>

		<c:url value="PublicacionServlet?" var="urlVerificadoFalse">
			<c:param name="accionGET" value="admGestionarVerificacionPublicacion" />
			<c:param name="verificado" value="false" />
			<c:param name="idPublicacion" value="${objPublicacion.idPublicacion}" />
		</c:url>

		<a href="${urlVerificadoFalse}" class="btn btn-danger"
			data-toggle="tooltip" title="Marcar como no verificado"> <span
			class="glyphicon glyphicon-remove"></span>
		</a>
	</div>
</td>