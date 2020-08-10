$(document).ready(function(){
	var usernameInput = $('#usernameInput');
	var passwordInput = $('#passwordInput');
	var new_passwordInput = $('#new_passwordInput');
	

	$('#updateSubmit').on('click', function(event){
		var username = usernameInput.val();
		var password = passwordInput.val();
		var new_password = new_passwordInput.val();
		console.log('username: ' + username)
		console.log('password: ' + password);
		console.log('new_password: ' + new_password);
		
		
		var params = {
			'username': username,
			'password': password,
			'new_password': new_password
		}
		$.post("http://localhost:8080/api/users/updatePassword/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Izmena lozinke')
			
			window.location.href = "accounts.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});