$(document).ready(function(){
	var displaynameInput = $('#displaynameInput');

	
	$('#contactSubmit').on('click', function(event){
		var displayname = displaynameInput.val();
		console.log('displayname: ' + displayname);
		
		var params = {
			'displayname': displayname
		}
		$.post("http://localhost:8080/api/contacts/deleteContact", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Obrisan je kontakt')
			
			window.location.href = "contacts.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});