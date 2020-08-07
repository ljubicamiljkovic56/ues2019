$(document).ready(function(){
	var usernameInput = $('#usernameInput');

	
	$('#accountSubmit').on('click', function(event){
		var username = usernameInput.val();
		console.log('username: ' + username);
		
		var params = {
			'username': username
		}
		$.post("http://localhost:8080/api/accounts/deleteAccount", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Obrisan je nalog')
			
			window.location.href = "accounts.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});