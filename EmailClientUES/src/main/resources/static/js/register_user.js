$(document).ready(function(){
	var usernameInput = $('#usernameInput');
	var passwordInput = $('#passwordInput');
	var repeatedPasswordInput = $('#repeatedPasswordInput');
	var firstnameInput = $('#firstnameInput');
	var lastnameInput = $('#lastnameInput');

	
	$('#registerSubmit').on('click', function(event){
		var username = usernameInput.val();
		var password = passwordInput.val();
		var repeatedPassword = repeatedPasswordInput.val();
		var firstname = firstnameInput.val();
		var lastname = lastnameInput.val();
		console.log('username: ' + username);
		console.log('password: ' + password);
		console.log('repeatedPassword: ' + repeatedPassword);
		
		if(password != repeatedPassword) {
			alert('Lozinke nisu iste!');
			
			event.preventDefault();
			return false;
		}
		var params = {
			'username': username, 
			'password': password,
			'firstname': firstname,
			'lastname': lastname
		}
		$.post("http://localhost:8080/api/users/registerUser", params, function(data){
			console.log(data);
			console.log(username);
			console.log(password);
			console.log(firstname);
			console.log(lastname);
			
			alert('Uspesna registracija');
			
			
			window.location.replace('login.html');
			
//			if(data.status == 'failure'){
//				porukaParagraph.text(data.message);
//				return;
//			}
//			if(data.status == 'success'){
//				window.location.replace('login.html');
//			}
		});
		event.preventDefault();
		return false;
	});
});