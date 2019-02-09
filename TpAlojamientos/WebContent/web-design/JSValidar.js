function soloNros(e) {
	key = e.keyCode || e.which;
	teclado = String.fromCharCode(key);
	numeros = "0123456789";
	teclasEspeciales = "8-37-38-46";

	tecla_especial = false;

	for ( var i in teclasEspeciales) {
		if (key == teclasEspeciales[i]) {
			tecla_especial = true;
		}
	}
	if (numeros.indexOf(teclado) == -1 && !tecla_especial) {
		return false;
	}
}

function soloLetras(e) {
	key = e.keyCode || e.which;
	teclado = String.fromCharCode(key).toLowerCase();
	letras = "áéíóú abcdefghijklmnñopqrstuvwxyz";
	teclasEspeciales = "8-37-38-46-164";

	tecla_especial = false;

	for ( var i in teclasEspeciales) {
		if (key == teclasEspeciales[i]) {
			tecla_especial = true;
			break;
		}
	}
	if (letras.indexOf(teclado) == -1 && !tecla_especial) {
		return false;
	}
}
// 2019
function infoRemainChars(event, idResponseRemainChars) {
	var textoIngresado, counter, maxlenghtValue;

	elementoEvent = event.target;
	textoIngresado = elementoEvent.value; // event.target.value;
	maxlenghtValue = elementoEvent.getAttribute('maxlength');

	counter = (maxlenghtValue - (textoIngresado.length));

	document.getElementById(idResponseRemainChars).textContent = counter + '/'
			+ maxlenghtValue;

}

function checkSubmit(e) {
	// e.preventDefault();
	if (e && e.keyCode == 13) {
		document.forms[0].submit();
		// this.click();
	}
};

function mostrarDivError() {
	if (estado == true) {
		document.getElementById('GuardarOK').style.display = 'block';
	} else {
		document.getElementById('GuardarError').style.display = 'block';
	}
}

function Search_Gridview(strKey, strGV) {
	var strData = strKey.value.toLowerCase().split(" ");

	var tblData = document.getElementById(strGV);
	var rowData;
	for (var i = 1; i < tblData.rows.length; i++) {
		rowData = tblData.rows[i].innerHTML;
		var styleDisplay = 'none';
		for (var j = 0; j < strData.length; j++) {
			if (rowData.toLowerCase().indexOf(strData[j]) >= 0)
				styleDisplay = '';
			else {
				styleDisplay = 'none';
				break;
			}
		}
		tblData.rows[i].style.display = styleDisplay;
	}
}

// function Search_Gridview(strKey) {
// var strData = strKey.value.toLowerCase().split(" ");
// var tblData = document.getElementById("<%=grdUsuarios.DNI1 %>");
// var rowData;
// for (var i = 1; i < tblData.rows.length; i++) {
// rowData = tblData.rows[i].innerHTML;
// var styleDisplay = 'none';
// for (var j = 0; j < strData.length; j++) {
// if (rowData.toLowerCase().indexOf(strData[j]) >= 0)
// styleDisplay = '';
// else {
// styleDisplay = 'none';
// break;
// }
// }
// tblData.rows[i].style.display = styleDisplay;
// }
function validarFecha(fecha) {
	dia = fecha.split('/')[0];
	mes = fecha.split('/')[1];
	anio = fecha.split('/')[2];
	if (anio < 1900
			|| anio > 2050
			|| mes < 1
			|| mes > 12
			|| dia < 1
			|| dia > 31
			|| ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia == 31)
			|| (mes == 2 && dia > 29)
			|| (mes == 2 && (anio % 4 != 0 && anio % 100 == 0)
					&& anio % 400 != 0 && dia == 29))
		return "Mal";
	// return false;
	else
		return "Bien";
	// return true;
}
function validate(date) {
	var eighteenYearsAgo = moment().subtract(18, "years");
	var birthday = moment(date);

	if (!birthday.isValid()) {
		return "invalid date";
	} else if (eighteenYearsAgo.isAfter(birthday)) {
		return "okay, you're good";
	} else {
		return "sorry, no";
	}
}
// jsprint(validate, "4/10/1995");
