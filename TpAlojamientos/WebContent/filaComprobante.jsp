<tr>
	<td>${objComprobante.idComprobante}</td>
	<td>${objComprobante.idSolicitud}</td>
	<td>${objComprobante.idPublicacion}</td>
	<td>${objComprobante.nombreApellidoPropietario}</td>
	<td>${objComprobante.nombreApellidoHuesped}</td>
	<td>${objComprobante.fechaReservaInicio}</td>
	<td>${objComprobante.fechaReservaFin}</td>
	<td>${objComprobante.cantDiasReserva}</td>
	<td>${objComprobante.cantPersonas}</td>
	<td>$&nbsp;${objComprobante.precioFinal}</td>
	<td>${objComprobante.fechaAlta}</td>
	<td>
		<div>
			<c:url value="SolDeReservaServlet?" var="urlVerComprobante">
				<c:param name="accionGET" value="verComprobanteDeReserva" />
				<c:param name="idComprobante"
					value="${objComprobante.idComprobante}" />
			</c:url>

			<a href="${urlVerComprobante}" class="btn btn-default"> <span
				class="glyphicon glyphicon-eye-open"></span> Ver Comprobante
			</a>
		</div>
	</td>
</tr>