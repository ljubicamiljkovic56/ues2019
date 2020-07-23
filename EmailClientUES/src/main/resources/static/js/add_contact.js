$(document).ready(function(){
	var user_usernameInput = $('#user_usernameInput');
	var firstnameInput = $('#firstnameInput');
	var lastnameInput = $('#lastnameInput');
	var displaynameInput = $('#displaynameInput');
	var emailInput = $('#emailInput');
	var noteInput = $('#noteInput');
	//var photoInput = $('#photoInput');
	
	$('#contactSubmit').on('click', function(event){
		var user_username = user_usernameInput.val();
		var firstname = firstnameInput.val();
		var lastname = lastnameInput.val();
		var displayname = displaynameInput.val();
		var email = emailInput.val();
		var note = noteInput.val();
		//var photo = photoInput.val();
		console.log('user_username: ' + user_username)
		console.log('firstname: ' + firstname);
		console.log('lastname: ' + lastname);
		console.log('display name: ' + displayname);
		console.log('email: ' + email);
		console.log('note: ' + note);
		//console.log('photo: ' + photo);
		
		var params = {
			'user_username': user_username,
			'firstname': firstname, 
			'lastname': lastname,
			'displayname': displayname,
			'email': email,
			'note': note
			//'photo': photo
			
		}
		$.post("http://localhost:8080/api/contacts/addContact", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Dodat je novi kontakt')
			
			window.location.href = "contacts.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});