$(document).ready(function(){
	var urlParams = new URLSearchParams(window.location.search);
	$('#idInput').val(urlParams.get('id'));

	var idInput = $('#idInput');
	var usernameInput = $('#usernameInput');
	var passwordInput = $('#passwordInput');
    var firstnameInput = $('#firstnameInput');
    var lastnameInput = $('#lastnameInput');
	
 	
 	function getUserData() {
 		$.get("http://localhost:8080/api/users/getUser", function(data) {
 			console.log(data);

 			$('#updateSubmit').on('click', function(event) {
 				var id = idInput.val();
 				var username = usernameInput.val();
 				var password = passwordInput.val();
 				var fistname = firstnameInput.val();
 				var lastname = lastnameInput.val();
 				console.log('id: ' + id);
 				console.log('username: ' + username);
 				console.log('password: ' + password);
 				console.log('firstname: ' + firstname);
 				console.log('lastname: ' + lastname);

 						
 				params = {
 					'id': id, 
 					'username': username,
 					'password': password,
 					'firstname': firstname,
 					'lastname': lastname
 				};
 				console.log(params);
 				$.post("http://localhost:8080/api/users/updateUser", params, function(data) {
 	
 					
 					window.location.replace('user.html');
 			
 				});

 				event.preventDefault();
 				return false;
 				});

 				
 			}
 		);
 	}
 	
 	getUserData();
});