$(document).ready(function(){
	
	var displaynameInput = $('#displaynameInput');
	var firstnameInput = $('#firstnameInput');
	var lastnameInput = $('#lastnameInput');
	var new_displaynameInput = $('#new_displaynameInput');
	var emailInput = $('#emailInput');
 	var noteInput = $('#noteInput');

	$('#updateSubmit').on('click', function(event){
		var displayname = displaynameInput.val();
		var firstname = firstnameInput.val();
		var lastname = lastnameInput.val();
		var new_displayname = new_displaynameInput.val();
		var email = emailInput.val();
		var note = noteInput.val();
		console.log('displayname: ' + displayname)
		console.log('firstname: ' + firstname);
		console.log('lastname: ' + lastname);
		console.log('new_displayname: ' + new_displayname);
		console.log('email: ' + email);
		console.log('note: ' + note);
		
		
		
		var params = {
			'displayname': displayname,
			'firstname': firstname,
			'lastname': lastname,
			'new_displayname': new_displayname,
			'email': email,
			'note': note
		}
		$.post("http://localhost:8080/api/contacts/updateContact/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Izmena kontakta')
			
			window.location.href = "contacts.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});