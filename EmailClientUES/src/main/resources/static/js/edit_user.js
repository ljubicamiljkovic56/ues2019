$(document).ready(function(){
	var urlParams = new URLSearchParams(window.location.search);
	$('#idInput').val(urlParams.get('id'));

	var idInput = $('#idInput');
	var usernameInput = $('#usernameInput');
	var lozinkaInput = $('#lozinkaInput');
    var datumRegInput = $('#datumRegInput');
    var ulogaInput = $('#ulogaInput');
	
 	
 	function getKorisnik() {
 		$.get('KorisnikServlet', {'action': 'ulogovanKorisnikUloga'}, function(data) {
 			console.log(data);

 			if (data.status == 'unauthenticated') {
 				window.location.replace('login.html');
 				return;
 			}
 			
 			if (data.status == 'success') {
 				//var korisnik1 = data.korisnik1;
 				//console.log(korisnik1);
 				console.log('success');
 				if (data.ulogovanKorisnikUloga == 'korisnik') {
 					console.log('ispis...');
 					$('#korisnikForm').hide();
 					$('#adminForm').hide();
 					
 					
 					$('#korisnickoImeCell').text(korisnik1.korisnickoIme);
 					$('#lozinkaCell').text(korisnik1.lozinka);
 					$('#datumRegCell').text(korisnik1.datumReg);
 					$('#ulogaCell').text(korisnik1.uloga);
 				} else if (data.ulogovanKorisnikUloga == 'admin') {
 					console.log('ispis...')
 					console.log(data);
 					$('#korisnikForm').hide()
 					$('#adminForm').show();
 					

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
 						$.post('KorisnikServlet', params, function(data) {
 							if (data.status == 'unauthenticated') {
 								window.location.replace('login.html');
 								return;
 							}

 							if (data.status == 'success') {
 								window.location.replace('korisnici.html');
 								return;
 							}
 						});

 						event.preventDefault();
 						return false;
 					});

 				}
 			}
 		});
 	}
 	
 	getKorisnik();
});