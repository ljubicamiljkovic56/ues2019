$(document).ready(function(){
	var usernameInput = $('#usernameInput');
	var passwordInput = $('#passwordInput');
	
	$('#messageSubmit').on('click', function(event){
		var username = usernameInput.val();
		var password = passwordInput.val();
		console.log('username: ' + username);
		console.log('password: ' + password);

		
		var params = {
			'username': username, 
			'password': password
		}
		$.post("http://localhost:8080/receivemail/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Primljen je email')
			
			window.location.href = "messages.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});
