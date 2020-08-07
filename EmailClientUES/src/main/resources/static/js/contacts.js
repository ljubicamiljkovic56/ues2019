var contacts = []

$(document).ready(function(){
	var contactsTable = $('#contactsTable');
	
	var searchFuzzyButtonContacts = $('#searchFuzzyButtonContacts'); 
	var resultFuzzyContacts = $('#resultFuzzyContacts');
	var searchPhraseButtonContacts = $('#searchPhraseButtonContacts');
	var resultPhraseContacts = $('#resultPhraseContacts');
	var searchBooleanButtonContacts = $('#searchBooleanButtonContacts');
	var resultBooleanContacts = $('#resultBooleanContacts');
	
	function getContacts(){
	$.get("http://localhost:8080/api/contacts/getallcontacts", function(data){
			console.log(data);
			
			contacts = data;
			
			populateTable(contacts);	

		});
	}
	
	function populateTable(contactsForTable){
		
		console.log('tabela?')
	
		
		for(it of contactsForTable){
			contactsTable.append(
				'<tr>' + 
					'<td>' + it.firstname + '</td>' +
					'<td>' + it.lastname + '</td>' + 
					'<td>' + it.displayname + '</td>' +
					'<td>' + it.email + '</td>' +
					'<td>' + it.note + '</td>' + 
					'<td>' + it.userID + '</td>' +
					'<td>' + it.photo + '</td>' +
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
	

	searchFuzzyButtonContacts.on('click',function(event){
		
	var term1 = $('#fuzzySearchDropdownContacts option:selected').val();
	var searchField1 = $('#searchFuzzyInputContacts').val();

	var jsonData = {
			'term1' : term1,
			'searchField1' : searchField1
		}
	alert('Dugme kliknuto');
//	

		$.ajax({
			url : "http://localhost:8080/api/search/contacts/fuzzy",
			type : "POST",
			contentType : 'application/json',
			data : JSON.stringify(jsonData),
			//headers:{"Authorization" : "Bearer " + localStorage.getItem("token")},
			success: function(contacts) {
				console.log(contacts);
				if (resultFuzzyContacts.text().length == 0) {
					for(contact  of contacts) {
						resultFuzzyContacts.append(contact.firstName + " " + contact.lastName + " " + contact.email);
					}
				}else {
					resultFuzzyContacts.empty();
					for(contact  of contacts) {
						resultFuzzyContacts.append(contact.firstName + " " + contact.lastName + " " + contact.email);
					}

				}
			},error:function(response) {
				console.log(response);
			}
		})
		event.preventDefault();
	});
		
//	$.post("http://localhost:8080/api/searchcontacts/contacts/fuzzy", function (data) {
//		
//		alert("Usao u funkciju");
//		
//		if (resultFuzzyContacts.text().length == 0) {
//			for(contact  of contacts) {
//				resultFuzzyContacts.append(contact.firstName + " " + contact.lastName + " " + contact.email);
//			}
//		}else {
//			resultFuzzyContacts.empty();
//			for(contact  of contacts) {
//				resultFuzzyContacts.append(contact.firstName + " " + contact.lastName + " " + contact.email);
//			}
//
//		}
//		
//	})
	
	
//	
//	contactsTable.on('click', 'input.deleteSubmit', function(event){
//		alert('Brisem...');
//		var contactID = $(this).attr('contactID');
//		console.log('contactID: ' + contactID);		
//		params = {
//				'id': contactID
//		};
//		console.log(params);
//		$.post("http://localhost:8080/api/contacts/{id}", params,function(data){
//		
//			alert('Obrisana poruka')
//			window.location.replace('contacts.html');
//			
//		});
//		event.preventDefault();
//		return false;
//	});
	

	getContacts();
	console.log('dobavljene poruke?');
});