function login() {
	
	var usernameInput = $('#usernameInput');
	var passwordInput = $('#passwordInput');
	
	var username = usernameInput.val();
	var password = passwordInput.val();
	
	$.post("http://localhost:8080/api/users/user/loginUser",
		{
			'username':username,
			'password':password
		
		},
	function (response) {
			console.log('login');
			console.log(response);
			alert('Successful login');
			
			window.location.href = 'messages.html'
		}
			
	
	);
}