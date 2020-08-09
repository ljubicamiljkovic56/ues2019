$(document).ready(function(){
	var user_usernameInput = $('#user_usernameInput');

	
	$('#userSubmit').on('click', function(event){
		var user_username = user_usernameInput.val();
		console.log('user_username: ' + user_username);
		
		var params = {
			'user_username': user_username
		}
		$.post("http://localhost:8080/api/users/deleteUser", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Obrisan je korisnik')
			
			window.location.href = "index.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});