package ues.projekat.app.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.app.model.Account;
import ues.projekat.app.model.Folder;
import ues.projekat.app.model.Message;
import ues.projekat.app.model.User;
import ues.projekat.dto.AccountDTO;
import ues.projekat.service.intrfc.AccountServiceInterface;
import ues.projekat.service.intrfc.UserServiceInterface;

@RestController
@RequestMapping(value = "api/accounts")
public class AccountController {
	
	@Autowired
	private AccountServiceInterface accountServiceInterface;
	
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	//prikaz svih naloga
	//poziva se u accounts.js
	//localhost:8080/api/accounts/getallaccounts
	@GetMapping(value = "/getallaccounts")
	public ResponseEntity<List<AccountDTO>> getAllAccount(){
		List<Account> accounts = accountServiceInterface.findAll();
		
		if (accounts == null) {
			return new ResponseEntity<List<AccountDTO>>(HttpStatus.NOT_FOUND);
		}
	//	User user = userServiceInterface.findByUsername(username);
		List<AccountDTO> AccountDTO = new ArrayList<AccountDTO>();
		for (Account account : accounts) {
			
			
			//if(account.getUser().getId() == user.getId()) {
		//	List<Account> accountsList = new ArrayList<Account>();
			Map<String, Object> data = new LinkedHashMap<String, Object>();
			AccountDTO.add(new AccountDTO(account));
			//data.put("accountsList",);
			
			//}	
		}
		return new ResponseEntity<List<AccountDTO>>(AccountDTO, HttpStatus.OK);
	}
	
	//prikaz accounta na osnovu user username-a
	//recimo localhost:8080/api/accounts/getallaccount/miki123
	@GetMapping(value = "/getallaccount/{username}")
	public ResponseEntity<List<AccountDTO>> getAllAccountByUsername(@PathVariable("username") String username){
		List<Account> accounts = accountServiceInterface.findAll();
		
		if (accounts == null) {
			return new ResponseEntity<List<AccountDTO>>(HttpStatus.NOT_FOUND);
		}
		User user = userServiceInterface.findByUsername(username);
		List<AccountDTO> AccountDTO = new ArrayList<AccountDTO>();
		for (Account account : accounts) {
			
			if(account.getUser().getId() == user.getId()) {
			
			AccountDTO.add(new AccountDTO(account));
			}
			
		}
		return new ResponseEntity<List<AccountDTO>>(AccountDTO, HttpStatus.OK);
	}
	
	
	//prikaz accounta na osnovu id-a
	//recimo localhost:8080/api/accounts/1, 2, 3
	@GetMapping(value="/{id}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable("id") Long id){
		Account account = accountServiceInterface.findOne(id);
		if(account == null){
			return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);
	}
	
	
//	@PutMapping(value="/update", consumes="application/json")
//	public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO){
//		Account account = accountServiceInterface.findOne(accountDTO.getId());
//		if (account == null) {
//			return new ResponseEntity<AccountDTO>(HttpStatus.BAD_REQUEST);
//		}
//		
//		account.setDisplayname(accountDTO.getDisplayname());
//		account.setPassword(accountDTO.getPassword());
//		account.setUsername(accountDTO.getUsername());
//		
//		account = accountServiceInterface.save(account);
//		System.out.println("ACCOUNT CHANGED.....");
//		
//		return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);	
//	}
	
	//izmena naloga po username-u
	@PostMapping(value = "/updateAccount")
	public ResponseEntity<Void> updateAccount(@RequestParam String username, @RequestParam String new_username, 
			@RequestParam String password, @RequestParam String displayname){
		
		Account account = accountServiceInterface.findByUsername(username);
		
		if(account != null) {
			

			account.setUsername(new_username);
			account.setPassword(password);
			account.setDisplayname(displayname);
			
			
			account.setSmtpAddress(account.getSmtpAddress());
			account.setSmtpPort(account.getSmtpPort());
			account.setInServerType(account.getInServerType());
			account.setInServerAddress(account.getInServerAddress());
			account.setInServerPort(account.getInServerPort());
			
		//	account.setAccountFolders((ArrayList<Folder>) account.getAccountFolders());
		//	account.setAccountMessages((ArrayList<Message>) account.getAccountMessages());
			account.setUser(account.getUser());
			
			account = accountServiceInterface.save(account);
			
			System.out.println("Izmena naloga");
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//brisanje naloga na osnovu username-a
	@PostMapping(value = "/deleteAccount")
	public ResponseEntity<Void> deleteAccount(@RequestParam String username) {
		
		Account account = accountServiceInterface.findByUsername(username);
		
		if (account == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		accountServiceInterface.remove(account.getId());
		
		System.out.println("Obrisan je nalog");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}