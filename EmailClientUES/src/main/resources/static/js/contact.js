$(document).ready(function() {
	
	var contact = []
	
	var displaynameInput = $('#displaynameInput');
	
	 var firstnameCell = $('#firstnameCell');
	 var lastnameCell = $('#lastnameCell');
     var displaynameCell = $('#displaynameCell');
     var emailCell = $('#emailCell');
     var noteCell = $('#noteCell');
    // var photoCell = $('#photoCell');
   
 	$('#displaynameSubmit').on('click', function(event){
		var displayname = displaynameInput.val();

		console.log('displayname: ' + displayname);
		
		var params = {
			'displayname': displayname, 
		}
		$.post("http://localhost:8080/api/contacts/getContactByDisplayname", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Detalji naloga...')
			
			contact = data;
			
			$('#firstnameCell').val(contact.firstname);
			$('#lastnameCell').val(contact.lastname);
			$('#displaynameCell').val(contact.displayname);
			$('#emailCell').val(contact.email);
			$('#noteCell').val(contact.note);
		//	$('#photoCell').val(contact.photo);
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});