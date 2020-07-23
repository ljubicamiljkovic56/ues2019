$(document).ready(function(){
	var user_usernameInput = $('#user_usernameInput');
	var usernameInput = $('#usernameInput');
	var passwordInput = $('#passwordInput');
	var displaynameInput = $('#displaynameInput');
	
	$('#accountSubmit').on('click', function(event){
		var user_username = user_usernameInput.val();
		var username = usernameInput.val();
		var password = passwordInput.val();
		var displayname = displaynameInput.val();
		console.log('user_username: ' + user_username)
		console.log('username: ' + username);
		console.log('password: ' + password);
		console.log('display name: ' + displayname);
		
		var params = {
			'user_username': user_username,
			'username': username, 
			'password': password,
			'displayname': displayname
		}
		$.post("http://localhost:8080/api/users/addAccount/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Dodat je novi nalog')
			
			window.location.href = "accounts.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});