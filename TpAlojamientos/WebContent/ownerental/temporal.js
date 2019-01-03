/*$(document).ready(function(){
	$("[name='cmbPartido']").submit(function(){
		$.ajax({
			url: $(this).attr('action'),
			type: $(this).attr('method'),
			dataType: 'json',
			data: $("[name='editClient']").serialize(),
			success: function(data){
				if(!data.isValid){
					$('#ans').html('no se puede');
				}else{
					$('#ans').html('hecho');
				}
			}
		});
		return false;
	});
});*/

$(document).ready(function(){
	$("[name='cmbPartido']").change(function(){
		var a = $("[name='cmbPartido'] option:selected");
		console.log(a.val());
	});
	
});