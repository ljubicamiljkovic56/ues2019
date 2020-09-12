$(document).ready(function() {
	
	var folder = []
	
	var nameInput = $('#nameInput');
	
	var nameCell = $('#nameCell');
	//var parentFolderCell = $('#parentFolderCell');
	
	$('#nameSubmit').on('click', function(event){
		var name = nameInput.val();

		console.log('name: ' + name);
		
		var params = {
			'name': name 
		}
		$.post("http://localhost:8080/api/folders/getFolder", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Detalji foldera...')
			
			folder = data;
			
			$('#nameCell').val(folder.name);
			//$('#parentFolderCell').val(folder.parentFolder);
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});