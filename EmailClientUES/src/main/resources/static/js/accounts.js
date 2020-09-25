var accounts = []

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
		
		console.log('tabela?')
	
		for(it of accountsForTable){
			accountsTable.append(
				'<tr>' + 
					'<td>' + it.smtpAddress + '</td>' +
					'<td>' + it.smtpPort + '</td>' + 
					'<td>' + it.inserverType + '</td>' +
					'<td>' + it.inserverAddress + '</td>' +
					'<td>' + it.inserverPort + '</td>' +
					'<td>' + it.username + '</td>' +
					'<td>' + it.password + '</td>' +
					'<td>' + it.displayname + '</td>' +
					'<td>' + it.userID + '</td>' + 
					'<td>' +
				'</td>' + 
					'<td>' +
					'</td>' + 
				'</tr>'
			)
		}
	};
	

	getAccounts();
	console.log('dobavljeni nalozi?');
});