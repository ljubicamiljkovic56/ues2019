var accounts = []
//var sortSmtpAddress = 1;
//var sortSmtpPort = 1;
//var sortInServerType = 1
//var sortInServerAddress = 1;
//var sortInServerPort = 1;
//var sortUsername = 1;
//var sortPassword = 1;
//var sortDisplayName = 1;
//var sortUserID = 1;

$(document).ready(function(){
	var accountsTable = $('#accountsTable');
	
	function getAccounts(){
	$.get("http://localhost:8080/api/accounts/getallaccounts", function(data){
			console.log(data);
			
			accounts = data;
			
			populateTable(accounts);	

		});
	}
	
	function populateTable(accountsForTable){
		//accountsTable.find('tr:gt(1)').remove();
		
		console.log('tabela?')
		
		//console.log(accountsForTable);
		for(it of accountsForTable){
			accountsTable.append(
				'<tr>' + 
					'<td>' + it.smtpAddress + '</td>' +
					'<td>' + it.smtpPort + '</td>' + 
					'<td>' + new String(it.inServerType) + '</td>' +
					'<td>' + it.inServerAddress + '</td>' +
					'<td>' + it.inServerPort + '</td>' +
					'<td>' + it.username + '</td>' +
					'<td>' + it.password + '</td>' +
					'<td>' + it.displayname + '</td>' +
					'<td>' + it.userID + '</td>' + 
					'<td>' +
					'<form>' + '<input type="submit" value="Obrisi" class="deleteSubmit" accountID="' + it.id + '">' + 
				'</form>' + '</td>' + 
					'<td>' +
					'</td>' + 
				'</tr>'
			)
		}
	};
	
	accountsTable.on('click', 'input.deleteSubmit', function(event){
		alert('Brisem...');
		var accountID = $(this).attr('accountID');
		console.log('accountID: ' + accountID);		
		params = {
				'id': accountID
		};
		console.log(params);
		$.post("http://localhost:8080/api/accounts/{id}", params,function(data){
		
			alert('Obrisan account')
			window.location.replace('accounts.html');
			
		});
		event.preventDefault();
		return false;
	});
	
//	$('#sortSmtpAddress').on('click', function(event){
//		alert('Sortiram...');
//		sortiraj('smtpAddress');
//	});
//	$('#sortSmtpPort').on('click', function(event){
//		alert('Sortiram...');
//		sortiraj('smtpPort');
//	});
//	$('#sortInServerType').on('click', function(event){
//		alert('Sortiram...');
//		sortiraj('inServerType');
//	});
//	
//	$('#sortInServerAddress').on('click', function(event){
//		alert('Sortiram...');
//		sortiraj('inServerAddress');
//	});
//	$('#sortInServerPort').on('click', function(event){
//		alert('Sortiram...');
//		sortiraj('inServerPort');
//	});
//	$('#sortUsername').on('click', function(event){
//		alert('Sortiram...');
//		sortiraj('username');
//	});
//	$('#sortPassword').on('click', function(event){
//		alert('Sortiram...');
//		sortiraj('password');
//	});
//	$('#sortDisplayName').on('click', function(event){
//		alert('Sortiram...');
//		sortiraj('displayName');
//	});
//	$('#sortUserID').on('click', function(event){
//		alert('Sortiram...');
//		sortiraj('userID');
//	});
//	
//	function sortiraj(sort){
//		let sortedAccounts = accounts;
//
//		for(let i = 0; i < sortedAccounts.length - 1; i++){
//			for(let j = i+1; j < sortedAccounts.length; j++){
//				if (sort === 'smtpAddress'){
//					if (sortSmtpAddress == 1){
//						if (sortedAccounts[i].smtpAddress > sortedAccounts[j].smtpAddress){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					} else {
//						if (sortedAccounts[i].smtpAddress < sortedAccounts[j].smtpAddress){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					}
//					//Username
//					//Password
//					//Displayname
//					//userId
//					
//				} else if (sort === 'smtpPort'){
//					if (sortSmtpPort == 1){
//						if (sortedAccounts[i].smtpPort > sortedAccounts[j].smtpPort){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					} else {
//						if (sortedAccounts[i].smtpPort < sortedAccounts[j].smtpPort){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					}
//				} else if (sort === 'inServerType'){
//					if (sortInServerType == 1){
//						if(sortedAccounts[i].inServerType > sortedAccounts[j].inServerType){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					} else {
//						if(sortedAccounts[i].inServerType < sortedAccounts[j].inServerType){
//							let temp = sortedAccounts[i];
//							sorteAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					}
//				} else if (sort === 'inServerAddress'){
//					if (sortInServerAddress == 1){
//						if(sortedAccounts[i].inServerAddress > sortedAccounts[j].inServerAddress){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					} else {
//						if(sortedAccounts[i].inServerAddress < sortedAccounts[j].inServerAddress){
//							let temp = sortedAccountss[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					}
//				} else if (sort === 'inServerPort'){
//					if (sortInServerPort == 1){
//						if(sortedAccounts[i].inServerPort > sortedAccounts[j].inServerPort){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					} else {
//						if(sortedAccounts[i].inServerPort < sortedAccounts[j].inServerPort){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					}
//				} else if (sort === 'username'){
//					if (sortUsername == 1){
//						if(sortedAccounts[i].username > sortedAccounts[j].username){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					} else {
//						if(sortedAccounts[i].username < sortedAccounts[j].username){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					}
//				} else if (sort === 'password'){
//					if (sortPassword == 1){
//						if(sortedAccounts[i].password > sortedAccounts[j].password){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					} else {
//						if(sortedAccounts[i].password < sortedAccounts[j].password){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					}
//				} else if (sort === 'displayName'){
//					if (sortDisplayName == 1){
//						if(sortedAccounts[i].displayName > sortedAccounts[j].displayName){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					} else {
//						if(sortedAccounts[i].displayName < sortedAccounts[j].displayName){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					}
//				} else if (sort === 'userID'){
//					if (sortUserID == 1){
//						if(sortedAccounts[i].userID > sortedAccounts[j].userID){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					} else {
//						if(sortedAccounts[i].userID < sortedAccounts[j].userID){
//							let temp = sortedAccounts[i];
//							sortedAccounts[i] = sortedAccounts[j];
//							sortedAccounts[j] = temp;
//						}
//					}
//				}
//			}
//		}
//		if (sort === 'smtpAddress'){
//			sortSmtpAddress = -1 * sortSmtpAddress;
//		}else if (sort === 'smtpPort'){
//			sortSmtpPort = -1 * sortSmtpPort;
//		}else if (sort === 'inServerType'){
//			sortInServerType = -1 * sortInServerType;
//		}else if (sort === 'inServerAddress'){
//			sortInServerAddress = -1 * sortInServerAddress;
//		}else if (sort === 'inServerPort'){
//			sortInServerPort = -1 * sortInServerPort;
//		}else if (sort === 'username'){
//			sortUsername = -1 * sortUsername;
//		}else if (sort === 'password'){
//			sortPassword = -1 * sortPassword;
//		}else if (sort === 'displayName'){
//			sortDisplayName = -1 * sortDisplayName;
//		}else if (sort === 'userID'){
//			sortUserID = -1 * sortUserID;
//		}
//
//		populateTable(sortedAccounts);
//	};
	getAccounts();
	console.log('dobavljeni nalozi?');
});