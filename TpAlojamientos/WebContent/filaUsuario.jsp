<td>${objUsuario.idUsuario}</td>
<td>${objUsuario.nombre}</td>
<td>${objUsuario.apellido}</td>
<td>${objUsuario.dni}</td>
<td>${objUsuario.mail}</td>
<td>${objUsuario.fechaNac}</td>
<td>${objUsuario.nroTelefono}</td>
<td>${objUsuario.usuario}</td>
<%--<td>${objUsuario.clave}</td> --%>
<td>
	<c:choose>
		<c:when test="${objUsuario.sexo}">Masculino</c:when>
		<c:otherwise>Femenino</c:otherwise>
	</c:choose>
</td>
<!--<td>${objUsuario.rutaFotoPerfil}</td>-->
<td>${objUsuario.admin}</td>
<td>${objUsuario.puntaje}</td>
<td>
	<c:choose>
		<c:when test="${objUsuario.habilitado}">SI</c:when>
		<c:otherwise>NO</c:otherwise>
	</c:choose>
</td>
<td>${objUsuario.fechaAlta}</td>
<td>${objUsuario.fechaUltConexion}</td>
<td>${objUsuario.fechaUltModificado}</td>
<td>
	<c:choose>
		<c:when test="${objUsuario.verificado}">SI</c:when>
		<c:otherwise>NO</c:otherwise>
	</c:choose>
</td>
<td>
	<div>
		<c:url value="PublicacionServlet?" var="urlPerfilPublicoUsuario">
			<c:param name="accionGET" value="verPerfilPublicoOtroUsuario" />
			<c:param name="idUsuario" value="${objUsuario.idUsuario}" />
		</c:url>

		<a href="${urlPerfilPublicoUsuario}" class="btn btn-default"
			data-toggle="tooltip" title="Ver Perfil"> <span
			class="glyphicon glyphicon-eye-open"></span>
		</a>
	</div>
	<div>
		<a href="#" class="btn btn-primary"> <span
			class="glyphicon glyphicon-edit"></span>
		</a>
	</div>

	<div>
		<a href="#" class="btn btn-danger"> <span
			class="glyphicon glyphicon-remove"></span>
		</a>
	</div>
</td>