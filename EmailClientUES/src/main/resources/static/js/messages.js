var messages = []

var foundmessage = []

var attach = []

var foundattach = []

var foundpdf =[]

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
	
	var foundMessageDiv2 = $('#foundMessageDiv2');
	
	$('#searchFuzzyButtonMessages').on('click', function(event) {
		var field = $('#fuzzyDropMessages option:selected').val();
		var word = $('#fuzzySearchInput').val();
		
		console.log('field: ' + field + " " + 'word: ' + word);
		
		var params = {
				'field':field,
				'word':word
		}
		$.post('http://localhost:8080/searchm/fuzzy/message', params, function(data) {
			
			console.log('ispis...')
			console.log(data);
			
			alert('Searching..')
			
			foundmessage = data;
			
			console.log(foundmessage);
			
			if(foundMessageDiv2.text().length == 0) {
				for(m in foundmessage) {
					console.log(m);
					foundMessageDiv2.append("From: " + foundmessage[m].from + " " + "To: " + foundmessage[m].to + " " +
							"Cc: " + foundmessage[m].cc + " " + "Bcc: " + foundmessage[m].bcc + " " + "Subject: " +
							foundmessage[m].subject + " " + "Content: " + foundmessage[m].content + " ");
				}
				
			} else {
				foundMessageDiv2.empty();
				for(m in foundmessage) {
					console.log(m);
					foundMessageDiv2.append("From: " + foundmessage[m].from + " " + "To: " + foundmessage[m].to + " " +
							"Cc: " + foundmessage[m].cc + " " + "Bcc: " + foundmessage[m].bcc + " " + "Subject: " +
							foundmessage[m].subject + " " + "Content: " + foundmessage[m].content + " ");
				}
				
			}
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
	
	var foundMessageDiv3 = $('#foundMessageDiv3');
	
	$('#searchPhraseButtonMessages').on('click', function(event) {
		
		var field1 = $('#phraseDropMessages option:selected').val();
		var term1 = $('#phraseInput').val();
		
		console.log('field1: ' + field1 + 'term1: ' + term1);
		
		var params = {
			'field1': field1,
			'term1': term1
		}
		$.post('http://localhost:8080/searchm/phrase/message', params, function(data) {
			
			console.log('ispis...')
			console.log(data);
			
			alert('Searching..')
			
			foundmessage = data;
			
			console.log(foundmessage);
			
			if(foundMessageDiv3.text().length == 0) {
				for(m in foundmessage) {
					console.log(m);
					foundMessageDiv3.append("From: " + foundmessage[m].from + " " + "To: " + foundmessage[m].to + " " +
							"Cc: " + foundmessage[m].cc + " " + "Bcc: " + foundmessage[m].bcc + " " + "Subject: " +
							foundmessage[m].subject + " " + "Content: " + foundmessage[m].content + " ");
				}
				
			} else {
				foundMessageDiv3.empty();
				for(m in foundmessage) {
					console.log(m);
					foundMessageDiv3.append("From: " + foundmessage[m].from + " " + "To: " + foundmessage[m].to + " " +
							"Cc: " + foundmessage[m].cc + " " + "Bcc: " + foundmessage[m].bcc + " " + "Subject: " +
							foundmessage[m].subject + " " + "Content: " + foundmessage[m].content + " ");
				}
				
			}
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
		
	});
	var attachsTable = $('#attachsTable');
	
	
	function getAttachments(){
		$.get("http://localhost:8080/api/attachments/getattach", function(data){
				console.log(data);
				
				attach = data;
				
				populateTableAttach(attach);	

			});
		}
		
		function populateTableAttach(attachsForTable){
			
			console.log('tabela attachmenti?')
			
			for(it of attachsForTable){
				console.log(attachsForTable)
				attachsTable.append(
					'<tr>' + 
						'<td>' + it.mimeType + '</td>' +
						'<td>' + it.name + '</td>' + 
						'<td>' + it.path + '</td>' +
						'<td>' +
					'</td>' + 
						'<td>' +
						'</td>' + 
					'</tr>'
				)
			}
		};
		
		var foundAttachDiv = $('#foundAttachDiv');
		
		$('#searchTermButtonAttachs').on('click', function(event) {
			var field1 = $('#termDropAttachs option:selected').val();
			var term1 = $('#termAttachInput').val();
			
			console.log('field1: ' + field1 + 'term1: ' + term1);
			
			var params = {
				'field1': field1,
				'term1': term1
			}
			$.post('http://localhost:8080/searchattach/term/attach', params, function(data) {
				
				console.log('ispis...')
				console.log(data);
				
				alert('Searching..')
				
				foundattach = data;
				
				console.log(foundattach);
				
				if(foundAttachDiv.text().length == 0) {
					for(a in foundattach) {
						console.log(a);
						foundAttachDiv.append("Attach id: " + foundattach[a].id + " " + 
								"Mime type: " + foundattach[a].mimetype + " " + 
								"Name: " + foundattach[a].name + " " + "Path: " + foundattach[a].path + " ");
					}
					
				} else {
					foundAttachDiv.empty();
					for(a in foundattach) {
						console.log(a);
						foundAttachDiv.append("Attach id: " + foundattach[a].id + " " + 
								"Mime type: " + foundattach[a].mimetype + " " + 
								"Name: " + foundattach[a].name + " " + "Path: " + foundattach[a].path + " ");
					}
					
				}
				
			});
			console.log('slanje poruke');
			event.preventDefault();
			return false;
			
			
		});
	
		var regularSearchPdfInput = $('#regularSearchPdfInput');
		
		var foundPdfDiv = $('#foundPdfDiv');
		
		$('#searchRegularButtonPdf').on('click', function(event) {
			var regularSearchPdf = regularSearchPdfInput.val();

			console.log('regularSearchPdf: ' + regularSearchPdf);

			
			var params = {
				'regularSearchPdf': regularSearchPdf
			}
			$.post('http://localhost:8080/searchattach/regular/pdf', params, function(data) {
				console.log('ispis...')
				console.log(data);
				
				alert('Searching..')
				
				foundpdf = data;
				
				console.log(foundpdf);
				
				if(foundPdfDiv.text().length == 0) {
					for(p in foundpdf) {
						console.log(p);
						foundPdfDiv.append("Name: " + foundpdf[p].name + " ");
					}
					
				} else {
					foundPdfDiv.empty();
					for(p in foundpdf) {
						console.log(p);
						foundPdfDiv.append("Name: " + foundpdf[p].name + " ");
					}
					
				}
				
				
			});
			console.log('slanje poruke');
			event.preventDefault();
			return false;
			
		});
		
		
	getMessages();
	getAttachments();
	console.log('dobavljene poruke?');
});