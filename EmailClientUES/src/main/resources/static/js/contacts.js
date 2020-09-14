var contacts = []

var foundcontact = []

var sortFirstnameSmer = 1;
var sortLastnameSmer = 1;
var sortDisplaynameSmer = 1;
var sortEmailSmer = 1;
var sortNoteSmer = 1;
var sortUserSmer = 1;
var sortPhotoSmer = 1;


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
//				'</td>' + 
//					'<td>' +
					'</td>' + 
				'</tr>'
			)
		}
	};
	

	
	
	$('#sortFirstname').on('click', function(event){
		alert('Sortiram...');
		sortiraj('firstname');
	});
		
	$('#sortLastname').on('click', function(event){
		alert('Sortiram...');
		sortiraj('lastname');
	});
	
	$('#sortDisplayname').on('click', function(event){
		alert('Sortiram...');
		sortiraj('displayname');
	});
	
	$('#sortEmail').on('click', function(event){
		alert('Sortiram...');
		sortiraj('email');
	});
	
	$('#sortNote').on('click', function(event){
		alert('Sortiram...');
		sortiraj('note');
	});
	
	$('#sortUser').on('click', function(event){
		alert('Sortiram...');
		sortiraj('user');
	});
	
	$('#sortPhoto').on('click', function(event){
		alert('Sortiram...');
		sortiraj('photo');
	});
		
	
	function sortiraj(sort){
		let sortedContacts = contacts;

		for(let i = 0; i < sortedContacts.length - 1; i++){
			for(let j = i+1; j < sortedContacts.length; j++){
				if (sort === 'firstname'){
					if (sortFirstnameSmer == 1){
						if (sortedContacts[i].firstname > sortedContacts[j].firstname){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					} else {
						if (sortedContacts[i].firstname < sortedContacts[j].firstname){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					}
					
				} else if (sort === 'lastname'){
					if (sortLastnameSmer == 1){
						if (sortedContacts[i].lastname > sortedContacts[j].lastname){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					} else {
						if (sortedContacts[i].lastname < sortedContacts[j].lastname){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					}
				} else if (sort === 'displayname'){
					if (sortDisplaynameSmer == 1){
						if(sortedContacts[i].displayname > sortedContacts[j].displayname){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					} else {
						if(sortedContacts[i].displayname < sortedContacts[j].displayname){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					}
				} else if (sort === 'email'){
					if (sortEmailSmer == 1){
						if(sortedContacts[i].email > sortedContacts[j].email){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					} else {
						if(sortedContacts[i].email < sortedContacts[j].email){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					}
				} else if (sort === 'note'){
					if (sortNoteSmer == 1){
						if(sortedContacts[i].note > sortedContacts[j].note){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					} else {
						if(sortedContacts[i].note < sortedContacts[j].note){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					}
				} else if (sort === 'user'){
					if (sortUserSmer == 1){
						if(sortedContacts[i].user > sortedContacts[j].user){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					} else {
						if(sortedContacts[i].user < sortedContacts[j].user){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					}
				} else if (sort === 'photo'){
					if (sortPhotoSmer == 1){
						if(sortedContacts[i].photo > sortedContacts[j].photo){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					} else {
						if(sortedContacts[i].photo < sortedContacts[j].photo){
							let temp = sortedContacts[i];
							sortedContacts[i] = sortedContacts[j];
							sortedContacts[j] = temp;
						}
					}
				}
			}
		}
		if (sort === 'firstname'){
			sortFirstnameSmer = -1 * sortFirstnameSmer;
		}else if (sort === 'lastname'){
			sortLastnameSmer = -1 * sortLastnameSmer;
		}else if (sort === 'displayname'){
			sortDisplaynameSmer = -1 * sortDisplaynameSmer;
		}else if (sort === 'email'){
			sortEmailSmer = -1 * sortEmailSmer;
		}else if (sort === 'note'){
			sortNoteSmer = -1 * sortNoteSmer;
		}else if (sort === 'user'){
			sortUserSmer = -1 * sortUserSmer;
		}else if (sort === 'photo'){
			sortPhotoSmer = -1 * sortPhotoSmer;
		}

		populateTable(sortedContacts);
	};		
	
	var regularSearchInput = $("#regularSearchInput")

    var foundContactDiv = $('#foundContactDiv')
	
	
	$('#searchRegularButtonContacts').on('click', function(event){
		var regularSearch = regularSearchInput.val();

		console.log('regularSearch: ' + regularSearch);

		
		var params = {
			'regularSearch': regularSearch
		}
		$.post("http://localhost:8080/search/regular/contact", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Searching..')
			
			foundcontact = data;
			
			console.log(foundcontact);
			
			if(foundContactDiv.text().length == 0) {
				for(c in foundcontact) {
					console.log(c);
					foundContactDiv.append("Firstname: " + foundcontact[c].firstname + " " + "Lastname: " + foundcontact[c].lastname + " " 
							+ "Displayname: " + foundcontact[c].displayname + " " +
							"Email: " + foundcontact[c].email + " " + "Note: " +foundcontact[c].note + " ")
				}
			}else {
				foundContactDiv.empty();
				for(c in foundcontact) {
					console.log(c);
					foundContactDiv.append("Firstname: " + foundcontact[c].firstname + " " + "Lastname: " +  foundcontact[c].lastname + " " 
							+ "Displayname: " + foundcontact[c].displayname + " " 
							+ "Email: " + foundcontact[c].email + " " + "Note:" + foundcontact[c].note + " ")
				}
			}
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
	

	
	var foundContactDiv2 = $('#foundContactDiv2')
	
	$('#searchFuzzyButtonContacts').on('click', function(event) {
		
		var field = $('#fuzzyDropContacts option:selected').val();
		var word = $('#fuzzySearchInput').val();
		
		console.log('field: ' + field + " " + 'word: ' + word);
		
		var params = {
				'field':field,
				'word':word
		}
		$.post("http://localhost:8080/search/fuzzy/contact", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Searching..')
			
			foundcontact = data;
			
			console.log(foundcontact);
			
			if(foundContactDiv2.text().length == 0) {
				for(c in foundcontact) {
					console.log(c);
					foundContactDiv2.append("Firstname: " + foundcontact[c].firstname + " " + "Lastname: " + foundcontact[c].lastname + " " 
							+ "Displayname: " + foundcontact[c].displayname + " " +
							"Email: " + foundcontact[c].email + " " + "Note: " +foundcontact[c].note + " ")
				}
			}else {
				foundContactDiv2.empty();
				for(c in foundcontact) {
					console.log(c);
					foundContactDiv2.append("Firstname: " + foundcontact[c].firstname + " " + "Lastname: " + foundcontact[c].lastname + " " + 
							"Displayname: " + foundcontact[c].displayname + " " 
							+ "Email: " + foundcontact[c].email + " " + "Note: " + foundcontact[c].note + " ")
				}
			}
			
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
		
	});
	 
	var foundContactDiv3 = $('#foundContactDiv3');
	
	$('#searchPhraseButtonContacts').on('click', function(event) {
		
		var field1 = $('#phraseDropContacts option:selected').val();
		var term1 = $('#phraseInput').val();
		
		console.log('field1: ' + field1 + 'term1: ' + term1);
		
		var params = {
			'field1': field1,
			'term1': term1
		}
		$.post("http://localhost:8080/search/phrase/contact", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Searching..')
			
			foundcontact = data;
			
			console.log(foundcontact);
			
			if(foundContactDiv3.text().length == 0) {
				for(c in foundcontact) {
					console.log(c);
					foundContactDiv3.append("Firstname: " + foundcontact[c].firstname + " " + "Lastname: " + foundcontact[c].lastname + " " 
							+ "Displayname: " + foundcontact[c].displayname + " " +
							"Email: " + foundcontact[c].email + " " + "Note: " +foundcontact[c].note + " ")
				}
			}else {
				foundContactDiv3.empty();
				for(c in foundcontact) {
					console.log(c);
					foundContactDiv3.append("Firstname: " + foundcontact[c].firstname + " " + "Lastname: " + foundcontact[c].lastname + " " + 
							"Displayname: " + foundcontact[c].displayname + " " 
							+ "Email: " + foundcontact[c].email + " " + "Note: " + foundcontact[c].note + " ")
				}
			}
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
		
	});
	
	var foundContactDiv4 = $('#foundContactDiv4');
	
	$('#searchTermButtonContacts').on('click', function(event) {
		
		var field1 = $('#termDropContacts option:selected').val();
		var term1 = $('#termInput').val();
		
		console.log('field1: ' + field1 + 'term1: ' + term1);
		
		var params = {
			'field1': field1,
			'term1': term1
		}
		$.post('http://localhost:8080/search/term/contact', params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Searching..')
			
			foundcontact = data;
			
			console.log(foundcontact);
			
			if(foundContactDiv4.text().length == 0) {
				for(c in foundcontact) {
					console.log(c);
					foundContactDiv4.append("Firstname: " + foundcontact[c].firstname + " " + "Lastname: " + foundcontact[c].lastname + " " 
							+ "Displayname: " + foundcontact[c].displayname + " " +
							"Email: " + foundcontact[c].email + " " + "Note: " +foundcontact[c].note + " ")
				}
			}else {
				foundContactDiv4.empty();
				for(c in foundcontact) {
					console.log(c);
					foundContactDiv4.append("Firstname: " + foundcontact[c].firstname + " " + "Lastname: " + foundcontact[c].lastname + " " + 
							"Displayname: " + foundcontact[c].displayname + " " 
							+ "Email: " + foundcontact[c].email + " " + "Note: " + foundcontact[c].note + " ")
				}
			}
			
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
		
	});
	
	getContacts();
	console.log('dobavljene poruke?');
});