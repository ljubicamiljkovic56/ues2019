$(document).ready(function(){
	var message_subjectInput = $('#message_subjectInput');

	
	$('#messageSubmit').on('click', function(event){
		var message_subject = message_subjectInput.val();
		console.log('message_subject: ' + message_subject);
		
		var params = {
			'message_subject': message_subject
		}
		$.post("http://localhost:8080/api/messages/deleteMessage", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Obrisana je poruka')
			
			window.location.href = "messages.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});