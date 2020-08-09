$(document).ready(function(){

	var usernameInput = $('#usernameInput');
	var new_usernameInput = $('#new_usernameInput');
	var passwordInput = $('#passwordInput');
	var displaynameInput = $('#displaynameInput');
 	

	$('#updateSubmit').on('click', function(event){
		var username = usernameInput.val();
		var new_username = new_usernameInput.val();
		var password = passwordInput.val();
		var displayname = displaynameInput.val();
		console.log('username: ' + username)
		console.log('new_username: ' + new_username);
		console.log('password: ' + password);
		console.log('displayname: ' + displayname);
		
		
		var params = {
			'username': username,
			'new_username': new_username,
			'password': password,
			'displayname': displayname
		}
		$.post("http://localhost:8080/api/accounts/updateAccount/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Izmena naloga')
			
			window.location.href = "accounts.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});