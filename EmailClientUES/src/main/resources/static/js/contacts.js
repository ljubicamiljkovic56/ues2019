var contacts = []

$(document).ready(function(){
	var contactsTable = $('#contactsTable');
	
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
					'<td>' + it.userID + '</td>' +
					'<td>' + it.photo + '</td>' +
					'<td>' +
					'<form>' + '<input type="submit" value="Obrisi" class="deleteSubmit" messageID="' + it.id + '">' + 
				'</form>' + '</td>' + 
					'<td>' +
					'</td>' + 
				'</tr>'
			)
		}
	};
	
	contactsTable.on('click', 'input.deleteSubmit', function(event){
		alert('Brisem...');
		var contactID = $(this).attr('contactID');
		console.log('contactID: ' + contactID);		
		params = {
				'id': contactID
		};
		console.log(params);
		$.post("http://localhost:8080/api/contacts/{id}", params,function(data){
		
			alert('Obrisana poruka')
			window.location.replace('contacts.html');
			
		});
		event.preventDefault();
		return false;
	});
	

	getContacts();
	console.log('dobavljene poruke?');
});