function login(){
	
	var usernameInput = $('#usernameInput');
	var passwordInput = $('#passwordInput');
	
	var username = usernameInput.val();
	var password = passwordInput.val();
	
	$.post("http://localhost:8080/api/users/user/loginUser",
			
	{
		'username': username, 
		'password': password
	},
	
	function repondre(response){
		console.log('login');
		console.log(response);
		console.log(username);
		console.log(password);
		alert('Uspesno ste se ulogovali');
		window.location.href = 'messages.html';
	}
	);
	
}