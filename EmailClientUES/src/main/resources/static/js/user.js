$(document).ready(function() {
	
	var user = []
	
	var usernameInput = $('#usernameInput');
	
	var usernameCell = $('#usernameCell');
	var passwordCell = $('#passwordCell');
	var firstnameCell = $('#firstnameCell');
	var lastnameCell = $('#lastnameCell');
	
 	$('#usernameSubmit').on('click', function(event){
		var username = usernameInput.val();

		console.log('username: ' + username);
		
		var params = {
			'username': username
		}
		$.post("http://localhost:8080/api/users/getUserByUsername", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Detalji korisnika...')
			
			user = data;
			
			$('#usernameCell').val(user.username);
			$('#passwordCell').val(user.password);
			$('#firstnameCell').val(user.firstname);
			$('#lastnameCell').val(user.lastname);
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});