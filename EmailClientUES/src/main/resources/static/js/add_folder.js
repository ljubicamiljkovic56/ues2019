$(document).ready(function(){
	var account_usernameInput = $('#account_usernameInput');
	var nameInput = $('#nameInput');
	
	$('#folderSubmit').on('click', function(event){
		var account_username = account_usernameInput.val();
		var name = nameInput.val();
		console.log('account_username: ' + account_username)
		console.log('name: ' + name);
		
		var params = {
			'account_username': account_username,
			'name': name
		}
		$.post("http://localhost:8080/api/folders/addFolder/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Dodat je novi folder')
			
			window.location.href = "folders.html";
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
});