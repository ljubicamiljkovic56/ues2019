$(document).ready(function(){
	
	var message_subjectInput = $('#message_subjectInput');
	var fromInput = $('#fromInput');
	var toInput = $('#toInput');
	var ccInput = $('#ccInput');
	var bccInput = $('#bccInput');
	var subjectInput = $('#subjectInput');
	var contentInput = $('#contentInput');
 	

	$('#updateSubmit').on('click', function(event){
		var message_subject = message_subjectInput.val();
		var from = fromInput.val();
		var to = toInput.val();
		var cc = ccInput.val();
		var bcc = bccInput.val();
		var subject = subjectInput.val();
		var content = contentInput.val();
		console.log('message_subject: ' + message_subject);
		console.log('from: ' + from)
		console.log('to: ' + to);
		console.log('cc: ' + cc);
		console.log('bcc: '+ bcc);
		console.log('subject: ' + subject);
		console.log('content: ' + content);
		
		
		
		var params = {
			'message_subject': message_subject,
			'from': from,
			'to': to,
			'cc': cc,
			'bcc': bcc,
			'subject': subject,
			'content': content
		}
		$.post("http://localhost:8080/api/messages/updateMessage/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Izmena poruke')
			
			window.location.href = "messages.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
	
});