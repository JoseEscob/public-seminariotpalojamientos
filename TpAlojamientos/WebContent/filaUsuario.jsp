<td>${objUsuario.idUsuario}</td>
<td>${objUsuario.nombre}</td>
<td>${objUsuario.apellido}</td>
<td>${objUsuario.dni}</td>
<td>${objUsuario.mail}</td>
<td>${objUsuario.fechaNac}</td>
<td>${objUsuario.nroTelefono}</td>
<td>${objUsuario.usuario}</td>
<%--<td>${objUsuario.clave}</td> --%>
<td><c:choose>
		<c:when test="${objUsuario.sexo}">Masculino</c:when>
		<c:otherwise>Femenino</c:otherwise>
	</c:choose></td>
<!--<td>${objUsuario.rutaFotoPerfil}</td>-->
<td>${objUsuario.admin}</td>
<td>${objUsuario.puntaje}</td>
<td><c:choose>
		<c:when test="${objUsuario.habilitado}">SI</c:when>
		<c:otherwise>NO</c:otherwise>
	</c:choose></td>
<td>${objUsuario.fechaAlta}</td>
<td>${objUsuario.fechaUltConexion}</td>
<td>${objUsuario.fechaUltModificado}</td>
<td><c:choose>
		<c:when test="${objUsuario.verificado}">SI</c:when>
		<c:otherwise>NO</c:otherwise>
	</c:choose></td>
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
		<c:url value="UsuarioServlet" var="urlVerificadoTrue">
			<c:param name="accionGET" value="admGestionarVerificacionUsuarios" />
			<c:param name="verificado" value="true" />
			<c:param name="idUsuario" value="${objUsuario.idUsuario}" />
		</c:url>

		<a href="${urlVerificadoTrue}" class="btn btn-primary"
			data-toggle="tooltip" title="Marcar como verificado"> <span
			class="glyphicon glyphicon-ok-circle"></span>
		</a>

	</div>

	<div>

		<c:url value="UsuarioServlet" var="urlVerificadoFalse">
			<c:param name="accionGET" value="admGestionarVerificacionUsuarios" />
			<c:param name="verificado" value="false" />
			<c:param name="idUsuario" value="${objUsuario.idUsuario}" />
		</c:url>

		<a href="${urlVerificadoFalse}" class="btn btn-danger"
			data-toggle="tooltip" title="Marcar como no verificado"> <span
			class="glyphicon glyphicon-remove"></span>
		</a>
	</div>
</td>