$(document).ready(function(){
	var usernameInput = $('#usernameInput');
	var passwordInput = $('#passwordInput');
	var toInput = $('#toInput');
	var ccInput = $('#ccInput');
	var bccInput = $('#bccInput');
	var subjectInput = $('#subjectInput');
	var contentInput = $('#contentInput');
	
	$('#messageSubmit').on('click', function(event){
		var username = usernameInput.val();
		var password = passwordInput.val();
		var to = toInput.val();
		var cc = ccInput.val();
		var bcc = bccInput.val();
		var subject = subjectInput.val();
		var content = contentInput.val();
		console.log('username: ' + username);
		console.log('password: ' + password);
		console.log('to: ' + to);
		console.log('cc: ' + cc);
		console.log('bcc: ' + bcc);
		console.log('subject: ' + subject);
		console.log('content: ' + content);
		
		var params = {
			'username': username, 
			'password': password,
			'to': to,
			'cc': cc,
			'bcc': bcc,
			'subject': subject,
			'content': content
		}
		$.post("http://localhost:8080/sendemail/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Poslat je email')
			
			window.location.href = "messages.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});