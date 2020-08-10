$(document).ready(function(){
	var usernameInput = $('#usernameInput');
	var new_usernameInput = $('#new_usernameInput');
	var firstnameInput = $('#firstnameInput');
	var lastnameInput = $('#lastnameInput');
 	

	$('#updateSubmit').on('click', function(event){
		var username = usernameInput.val();
		var new_username = new_usernameInput.val();
		var firstname = firstnameInput.val();
		var lastname = lastnameInput.val();
		console.log('username: ' + username)
		console.log('new_username: ' + new_username);
		console.log('firstname: ' + firstname);
		console.log('lastname: ' + lastname);
		
		
		var params = {
			'username': username,
			'new_username': new_username,
			'firstname': firstname,
			'lastname': lastname
		}
		$.post("http://localhost:8080/api/users/updateUser/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Izmena korisnika')
			
			window.location.href = "accounts.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});