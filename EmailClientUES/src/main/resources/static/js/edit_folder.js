$(document).ready(function(){
	
	var nameInput = $('#nameInput');
	var newnameInput = $('#newnameInput');
 	

	$('#updateSubmit').on('click', function(event){
		var name = nameInput.val();
		var newname = newnameInput.val();
		console.log('name: ' + name)
		console.log('newname: ' + newname);
		
		
		
		var params = {
			'name': name,
			'newname': newname
		}
		$.post("http://localhost:8080/api/folders/updateFolder/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Izmena foldera')
			
			window.location.href = "folders.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});