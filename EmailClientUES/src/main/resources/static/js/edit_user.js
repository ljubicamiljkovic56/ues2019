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
 				var korisnickoIme = korisnickoImeInput.val();
 				var lozinka = lozinkaInput.val();
 				var datumReg = datumRegInput.val();
 				var uloga = ulogaInput.val();
 				console.log('id: ' + id);
 				console.log('korisnickoIme: ' + korisnickoIme);
 				console.log('lozinka: ' + lozinka);
 				console.log('datumReg: ' + datumReg);
 				console.log('uloga: ' + uloga);

 						
 				params = {
 					'action': 'update',
 					'id': id, 
 					'korisnickoIme': korisnickoIme,
 					'lozinka': lozinka,
 					'datumReg': datumReg,
 					'uloga': uloga
 				};
 				console.log(params);
 				$.post("http://localhost:8080/api/users/updateUser", params, function(data) {
 	
 					
 					window.location.replace('user.html');
 			
 				});

 				event.preventDefault();
 				return false;
 				});

 				
 			}
 		});
 	}
 	
 	getKorisnik();
});