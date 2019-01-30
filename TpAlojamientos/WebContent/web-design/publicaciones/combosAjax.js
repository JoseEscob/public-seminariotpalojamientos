$(function() {
	var action;
	$(".number-spinner div").mousedown(
			function() {
				btn = $(this);
				input = btn.closest('.number-spinner').find('input');

				if (btn.attr('data-dir') == 'up') {
					action = setInterval(function() {
						if (input.attr('max') == undefined
								|| parseInt(input.val()) < parseInt(input
										.attr('max'))) {
							input.val(parseInt(input.val()) + 1);
						} else {
							clearInterval(action);
						}
					}, 50);
				} else {
					action = setInterval(function() {
						if (input.attr('min') == undefined
								|| parseInt(input.val()) > parseInt(input
										.attr('min'))) {
							input.val(parseInt(input.val()) - 1);
						} else {
							btn.prop("disabled", true);
							clearInterval(action);
						}
					}, 50);
				}
			}).mouseup(function() {
		clearInterval(action);
	});
});

$(document)
		.ready(
				function() {
					$("[name='partido']")
							.change(
									function() {
										// MAGIA
										$
												.post(
														"PublicacionServlet",
														{
															"idPartido" : $(
																	"[name='partido'] option:selected")
																	.val(),
															"accionPOST" : "getLocalidades"
														},
														function(result) {
															$("#localidades")
																	.empty();
															$("#localidades")
																	.append(
																			$(
																					'<option selected disabled value="" />')
																					.text(
																							"Seleccionar una localidad"));
															$("#localidades")
																	.prop(
																			"disabled",
																			false);
															$
																	.each(
																			result.localidades,
																			function(
																					index,
																					value) {
																				$(
																						"#localidades")
																						.append(
																								$(
																										'<option />')
																										.val(
																												value.idLocalidad)
																										.text(
																												value.nombre));
																			});
															$("#localidades")
																	.selectpicker(
																			"refresh");
														});
									});
				});