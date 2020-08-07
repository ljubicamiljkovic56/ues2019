$(document).ready(function(){
	var nameInput = $('#nameInput');

	
	$('#folderSubmit').on('click', function(event){
		var name = nameInput.val();
		console.log('name: ' + name);
		
		var params = {
			'name': name
		}
		$.post("http://localhost:8080/api/folders/deleteFolder", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Obrisan je folder')
			
			window.location.href = "folders.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});