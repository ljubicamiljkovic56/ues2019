var messages = []

$(document).ready(function(){
	var messagesTable = $('#messagesTable');
	
	function getMessages(){
	$.get("http://localhost:8080/api/messages/getallmessages", function(data){
			console.log(data);
			
			messages = data;
			
			populateTable(messages);	

		});
	}
	
	function populateTable(messagesForTable){
		
		console.log('tabela?')
		
		for(it of messagesForTable){
			messagesTable.append(
				'<tr>' + 
					'<td>' + it.from + '</td>' +
					'<td>' + it.to + '</td>' + 
					'<td>' + it.cc + '</td>' +
					'<td>' + it.bcc + '</td>' +
					'<td>' + new Date(it.date) + '</td>' +
					'<td>' + it.subject + '</td>' +
					'<td>' + it.content + '</td>' +
					'<td>' + it.unread + '</td>' + 
					'<td>' + it.accountID + '</td>' +
					'<td>' + it.folderID + '</td>' + 
					'<td>' + it.tags + '</td>' +
					'<td>' + it.attachments + '</td>' + 
					'<td>' +
//					'<form>' + '<input type="submit" value="Obrisi" class="deleteSubmit" messageID="' + it.id + '">' + 
//				'</form>' +
				'</td>' + 
					'<td>' +
					'</td>' + 
				'</tr>'
			)
		}
	};
	
//	messagesTable.on('click', 'input.deleteSubmit', function(event){
//		alert('Brisem...');
//		var messageID = $(this).attr('messageID');
//		console.log('messageID: ' + messageID);		
//		params = {
//				'id': messageID
//		};
//		console.log(params);
//		$.post("http://localhost:8080/api/messages/{id}", params,function(data){
//		
//			alert('Obrisana poruka')
//			window.location.replace('messages.html');
//			
//		});
//		event.preventDefault();
//		return false;
//	});
	

	getMessages();
	console.log('dobavljene poruke?');
});