package ues.projekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.dto.AccountDTO;
import ues.projekat.entity.Account;
import ues.projekat.entity.User;
import ues.projekat.service.intrfc.AccountServiceInterface;
import ues.projekat.service.intrfc.UserServiceInterface;



@RestController
@RequestMapping(value = "api/accounts")
public class AccountController {
	
	@Autowired
	private AccountServiceInterface accountServiceInterface;
	
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	
	@GetMapping(value = "/getallaccount/{username}")
	public ResponseEntity<List<AccountDTO>> getAllAccount(@PathVariable("username") String username){
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
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable("id") Long id){
		Account account = accountServiceInterface.findOne(id);
		if(account == null){
			return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);
	}
	
	
	@PutMapping(value="/update", consumes="application/json")
	public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO){
		Account account = accountServiceInterface.findOne(accountDTO.getId());
		if (account == null) {
			return new ResponseEntity<AccountDTO>(HttpStatus.BAD_REQUEST);
		}
		
		account.setDisplayname(accountDTO.getDisplayname());
		account.setPassword(accountDTO.getPassword());
		account.setUsername(accountDTO.getUsername());
		
		account = accountServiceInterface.save(account);
		System.out.println("ACCOUNT CHANGED.....");
		
		return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable("id") Long id){
		Account account = accountServiceInterface.findOne(id);
		if (account != null){
			accountServiceInterface.remove(id);
			System.out.println("ACCOUNT DELETED.....");
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	
	

}
