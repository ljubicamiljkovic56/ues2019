$(document).ready(function() {
	
	var message = []
	
	var subjectInput = $('#subjectInput');
	
	var fromCell = $('#fromCell');
	var toCell = $('#toCell');
	var ccCell = $('#ccCell');
	var bccCell = $('#bccCell');
	var dateTimeCell = $('#dateTimeCell');
	var subjectCell = $('#subjectCell');
	var contentCell = $('#contentCell');
	var unreadCell = $('#unreadCell');
	var tagsCell = $('#tagsCell');
	var attachmentsCell = $('#attachmentsCell');
	
	
 	$('#subjectSubmit').on('click', function(event){
		var subject = subjectInput.val();

		console.log('subject: ' + subject);
		
		var params = {
			'subject': subject, 
		}
		$.post("http://localhost:8080/api/messages/getMessageBySubject", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Detalji poruke...')
			
			message = data;
			
			$('#fromCell').val(message.from);
			$('#toCell').val(message.to);
			$('#ccCell').val(message.cc);
			$('#bccCell').val(message.bcc);
			$('#dateTimeCell').val(new Date(message.dateTime));
			$('#subjectCell').val(message.subject);
			$('#contentCell').val(message.content);
			$('#unreadCell').val(message.unread);
//			$('#tagsCell').val(message.tags);
//			$('#attachmentsCell').val(message.attachments);
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
	
});