$(document).ready(function() {
	
	var account = []
	
	var displaynameInput = $('#displaynameInput');
	
	 var smtpAddressCell = $('#smtpAddressCell');
	 var smtpPortCell = $('#smtpPortCell');
     var inserverTypeCell = $('#inserverTypeCell');
     var inserverAddressCell = $('#inserverAddressCell');
     var inserverPortCell = $('#inserverPortCell');
     var usernameCell = $('#usernameCell');
     var passwordCell = $('#passwordCell');
     var displaynameCell = $('#displaynameCell');
   
 	$('#displaynameSubmit').on('click', function(event){
		var displayname = displaynameInput.val();

		console.log('displayname: ' + displayname);
		
		var params = {
			'displayname': displayname, 
		}
		$.post("http://localhost:8080/api/accounts/getAccountByDisplayname", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Detalji naloga...')
			
			account = data;
			
			$('#smtpAddressCell').val(account.smtpAddress);
			$('#smtpPortCell').val(account.smtpPort);
			$('#inserverTypeCell').val(account.inserverType);
			$('#inserverAddressCell').val(account.inserverAddress);
			$('#inserverPortCell').val(account.inserverPort);
			$('#usernameCell').val(account.username);
			$('#passwordCell').val(account.password);
			$('#displaynameCell').val(account.displayname);
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});