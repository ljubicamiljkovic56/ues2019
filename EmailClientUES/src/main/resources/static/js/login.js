$(document).ready(function(){
	var usernameInput = $('#usernameInput');
	var passwordInput = $('#passwordInput');
	
	$('#loginSubmit').on('click', function(event){
		var username = usernameInput.val();
		var password = passwordInput.val();
		console.log('username: ' + username);
		console.log('password: ' + password);
		
		var params = {
			'username': username, 
			'password': password
		}
		$.post("http://localhost:8080/api/users/user/loginUser", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			window.location.href = "accounts.html";
		});
		console.log('slanje poruke');
		
		event.preventDefault();
		return false;
	});
});