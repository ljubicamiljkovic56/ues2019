var messages = []

var foundmessage = []

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
			console.log(messagesForTable)
			messagesTable.append(
				'<tr>' + 
					'<td>' + it.from + '</td>' +
					'<td>' + it.to + '</td>' + 
					'<td>' + it.cc + '</td>' +
					'<td>' + it.bcc + '</td>' +
					'<td>' + new Date(it.dateTime) + '</td>' +
					'<td>' + it.subject.toString() + '</td>' +
					'<td>' + it.content.toString() + '</td>' +
					'<td>' + it.unread + '</td>' + 
					'<td>' + it.messageTags + '</td>' +
					'<td>' + it.messageAttachments + '</td>' + 
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
	
	var regularSearchInput = $('#regularSearchInput');
	
	var foundMessageDiv = $('#foundMessageDiv');
	
	$('#searchRegularButtonMessages').on('click', function(event) {
		var regularSearch = regularSearchInput.val();

		console.log('regularSearch: ' + regularSearch);

		
		var params = {
			'regularSearch': regularSearch
		}
		$.post('http://localhost:8080/searchm/regular/message', params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Searching..')
			
			foundmessage = data;
			
			console.log(foundmessage);
			
			if(foundMessageDiv.text().length == 0) {
				for(m in foundmessage) {
					console.log(m);
					foundMessageDiv.append("From: " + foundmessage[m].from + " " + "To: " + foundmessage[m].to + " " +
							"Cc: " + foundmessage[m].cc + " " + "Bcc: " + foundmessage[m].bcc + " " + "Subject: " +
							foundmessage[m].subject + " " + "Content: " + foundmessage[m].content + " ");
				}
				
			} else {
				foundMessageDiv.empty();
				for(m in foundmessage) {
					console.log(m);
					foundMessageDiv.append("From: " + foundmessage[m].from + " " + "To: " + foundmessage[m].to + " " +
							"Cc: " + foundmessage[m].cc + " " + "Bcc: " + foundmessage[m].bcc + " " + "Subject: " +
							foundmessage[m].subject + " " + "Content: " + foundmessage[m].content + " ");
				}
				
			}
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
		
		
	});
	
	var foundMessageDiv5 = $('#foundMessageDiv5');
	$('#searchBooleanButtonMessages').on('click', function(event) {
		
		var field1 = $('#boolDropMessages1 option:selected').val();
		var term1 = $('#boolInput1').val();
		var field2 = $('#boolDropMessages2 option:selected').val();
		var term2 = $('#boolInput2').val();
		var op = $('#op option:selected').val();
		
		
		console.log('field1: ' + field1 + 'term1: ' + term1);
		console.log('field2: ' + field2 + 'term2: ' + term2);
		console.log('op: ' + op);
		
		var params = {
			'field1': field1,
			'term1': term1,
			'field2': field2,
			'term2': term2,
			'op': op
		}
		$.post('http://localhost:8080/searchm/boolean/message', params, function(data) {
			
			console.log('ispis...')
			console.log(data);
			
			alert('Searching..')
			
			foundmessage = data;
			
			console.log(foundmessage);
			
			if(foundMessageDiv5.text().length == 0) {
				for(m in foundmessage) {
					console.log(m);
					foundMessageDiv5.append("From: " + foundmessage[m].from + " " + "To: " + foundmessage[m].to + " " +
							"Cc: " + foundmessage[m].cc + " " + "Bcc: " + foundmessage[m].bcc + " " + "Subject: " +
							foundmessage[m].subject + " " + "Content: " + foundmessage[m].content + " ");
				}
				
			} else {
				foundMessageDiv5.empty();
				for(m in foundmessage) {
					console.log(m);
					foundMessageDiv5.append("From: " + foundmessage[m].from + " " + "To: " + foundmessage[m].to + " " +
							"Cc: " + foundmessage[m].cc + " " + "Bcc: " + foundmessage[m].bcc + " " + "Subject: " +
							foundmessage[m].subject + " " + "Content: " + foundmessage[m].content + " ");
				}
				
			}
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
	
	var foundMessageDiv4 = $('#foundMessageDiv4');
	
	$('#searchTermButtonMessages').on('click', function(event) {
		var field1 = $('#termDropMessages option:selected').val();
		var term1 = $('#termInput').val();
		
		console.log('field1: ' + field1 + 'term1: ' + term1);
		
		var params = {
			'field1': field1,
			'term1': term1
		}
		$.post('http://localhost:8080/searchm/term/message', params, function(data) {
			
			console.log('ispis...')
			console.log(data);
			
			alert('Searching..')
			
			foundmessage = data;
			
			console.log(foundmessage);
			
			if(foundMessageDiv4.text().length == 0) {
				for(m in foundmessage) {
					console.log(m);
					foundMessageDiv4.append("From: " + foundmessage[m].from + " " + "To: " + foundmessage[m].to + " " +
							"Cc: " + foundmessage[m].cc + " " + "Bcc: " + foundmessage[m].bcc + " " + "Subject: " +
							foundmessage[m].subject + " " + "Content: " + foundmessage[m].content + " ");
				}
				
			} else {
				foundMessageDiv4.empty();
				for(m in foundmessage) {
					console.log(m);
					foundMessageDiv4.append("From: " + foundmessage[m].from + " " + "To: " + foundmessage[m].to + " " +
							"Cc: " + foundmessage[m].cc + " " + "Bcc: " + foundmessage[m].bcc + " " + "Subject: " +
							foundmessage[m].subject + " " + "Content: " + foundmessage[m].content + " ");
				}
				
			}
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});

	getMessages();
	console.log('dobavljene poruke?');
});