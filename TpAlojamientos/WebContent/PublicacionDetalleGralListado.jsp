
<div>
	<ul class="list-inline h4">
		<li>Zona:</li>
		<li><b>${vistaPublicacion.publicacion.objPublicacionInfo.publicacionPartidoLocalidad}</b></li>
	</ul>
</div>
<div>
	<ul class="list-inline">
		<li>Superficie Total: <b>${vistaPublicacion.publicacion.supCubierta + vistaPublicacion.publicacion.supDescubierta}
				m²</b></li>
		<c:choose>
			<c:when test="${vistaPublicacion.publicacion.cantAmbientes gt 1}">
				<li>Puntuación <label class="label label-default"
					data-toggle="tooltip" title="Puntuación General">
						${vistaPublicacion.publicacion.puntaje} </label></li>
			</c:when>
			<c:otherwise>
				<li><b></b></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>

<div>
	<ul class="list-inline">

		<li><b>${vistaPublicacion.publicacion.cantHabitaciones}
				habit.</b></li>
		<c:choose>
			<c:when test="${vistaPublicacion.publicacion.cantAmbientes eq 1}">
				<li><b>Monoambiente</b></li>
			</c:when>
			<c:otherwise>
				<li><b>${vistaPublicacion.publicacion.cantAmbientes}
						ambientes</b></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${vistaPublicacion.publicacion.cantBanios eq 1}">
				<li><b>${vistaPublicacion.publicacion.cantBanios} baño</b></li>
			</c:when>
			<c:otherwise>
				<li><b>${vistaPublicacion.publicacion.cantBanios} baños</b></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${vistaPublicacion.publicacion.aniosAntiguedad eq 0}">
				<li><b>A Estrenar</b></li>
			</c:when>
			<c:when test="${vistaPublicacion.publicacion.aniosAntiguedad eq 1}">
				<li><b>${vistaPublicacion.publicacion.aniosAntiguedad} año</b></li>
			</c:when>
			<c:otherwise>
				<li><b>${vistaPublicacion.publicacion.aniosAntiguedad} años</b></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>

<p class="lead">
	<b>$&nbsp;${vistaPublicacion.publicacion.precioNoche}</b>
</p>

<div>
	<c:choose>
		<c:when
			test="${vistaPublicacion.publicacion.chkPuedeVariarCantPersonas eq true}">
			<label class="control-label">Apto para
				${vistaPublicacion.publicacion.cantPersonas} Personas o más</label>
		</c:when>
		<c:otherwise>
			<label class="control-label">Apto para
				${vistaPublicacion.publicacion.cantPersonas} Personas</label>
		</c:otherwise>
	</c:choose>
</div>