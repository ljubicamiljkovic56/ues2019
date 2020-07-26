$(document).ready(function(){
	var idInput = $('#idInput');
	var usernameInput = $('#usernameInput');
	var passwordInput = $('#passwordInput');
	var firstnameInput = $('#firstnameInput');
	var lastnameInput = $('#lastnameInput');
	
	$('#updateSubmit').on('click', function(event){
		var id = idInput.val();
		var username = usernameInput.val();
		var password = passwordInput.val();
		var firstname = firstnameInput.val();
		var lastname = lastnameInput.val();
		console.log('id: ' + id)
		console.log('username: ' + username);
		console.log('password: ' + password);
		console.log('firstname: ' + firstname);
		console.log('lastname: ' + lastname);
		
		var params = {
			'id': id,
			'username': username, 
			'password': password,
			'firstname': firstname,
			'lastname': lastname
		}
		$.post("http://localhost:8080/api/users/updateUser/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Izmenjen je korisnik.')
			
			window.location.href = "accounts.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});