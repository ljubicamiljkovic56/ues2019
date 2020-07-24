var folders = []

$(document).ready(function(){
	var foldersTable = $('#foldersTable');
	
	function getFolders(){
	$.get("http://localhost:8080/api/folders/getallfolders", function(data){
			console.log(data);
			
			folders = data;
			
			populateTable(folders);	

		});
	}
	
	function populateTable(foldersForTable){
		console.log('tabela?')
		
		for(it of foldersForTable){
			foldersTable.append(
				'<tr>' + 
					'<td>' + it.name + '</td>' +
					'<td>' + it.parent_folder + '</td>' + 
					'<td>' + it.account_id + '</td>' + 
					'<td>' +
					'<form>' + '<input type="submit" value="Obrisi" class="deleteSubmit" folderID="' + it.id + '">' + 
				'</form>' + '</td>' + 
					'<td>' +
					'</td>' + 
				'</tr>'
			)
		}
	};
	
	foldersTable.on('click', 'input.deleteSubmit', function(event){
		alert('Brisem...');
		var folderID = $(this).attr('folderID');
		console.log('folderID: ' + folderID);		
		params = {
				'id': folderID
		};
		console.log(params);
		$.post("http://localhost:8080/api/folders/{id}", params,function(data){
		
			alert('Obrisan account')
			window.location.replace('folders.html');
			
		});
		event.preventDefault();
		return false;
	});
	getFolders();
	console.log('dobavljeni folderi?');
});