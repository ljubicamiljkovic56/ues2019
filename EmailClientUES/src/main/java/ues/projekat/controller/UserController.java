package ues.projekat.controller;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.dto.AccountDTO;
import ues.projekat.dto.UserDTO;
import ues.projekat.entity.Account;
import ues.projekat.entity.Contact;
import ues.projekat.entity.Folder;
import ues.projekat.entity.Message;
import ues.projekat.entity.Tag;
import ues.projekat.entity.User;
import ues.projekat.service.intrfc.AccountServiceInterface;
import ues.projekat.service.intrfc.UserServiceInterface;

@RestController
@RequestMapping(value="api/users")
public class UserController {
	
	@Autowired
    private AccountServiceInterface accountServiceInterface;
	
	
	@Autowired
    private UserServiceInterface userServiceInterface;
	
	@PostMapping(value = "/loginUser")
	public ResponseEntity<Void> loginUser(@RequestParam("username") String username, @RequestParam("password") String password){
		System.out.println("LOGIN..........");
		User user = userServiceInterface.findByUsernameAndPassword(username, password);
		if (user == null) {
			System.out.println("Neuspesna prijava");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		System.out.println(user.getUsername());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/getUser")
	public ResponseEntity<UserDTO> getUser(@RequestParam("username") String username, @RequestParam("password") String password){
		User user = userServiceInterface.findByUsernameAndPassword(username, password);
		
		if (user == null) {
			System.out.println("Neuspesan get");
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user) ,HttpStatus.OK);
	}
	
	@PostMapping(value = "/registrationUser")
	public ResponseEntity<Void> registrationUser(@RequestBody UserDTO userDTO){
		User user = new User();
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		user.setUserTags(new ArrayList<Tag>());
		user.setUserAccounts(new ArrayList<Account>());
		user.setUserContacts(new ArrayList<Contact>());
		
		System.out.println("REGISTRATION.....");
		userServiceInterface.save(user);
		
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@PutMapping(value = "/addAccaunt/{username}")
	public ResponseEntity<Void> addAccaunt(@RequestBody AccountDTO accountDTO,@PathVariable("username") String username){
		
		User user = userServiceInterface.findByUsername(username);
		
		Account account=new Account();
		account.setAccountFolders(new ArrayList<Folder>());
		account.setDisplayname(accountDTO.getDisplayname());
		
		account.setInServerAddress("");
		account.setInServerPort(2230);
		account.setInServerType((short) 123);
		account.setAccountMessages(new ArrayList<Message>());
		account.setPassword(accountDTO.getPassword());
		account.setSmtpAddress("smtp.gmail.com\"");
		account.setSmtpPort(2233);
		account.setUser(user);
		account.setUsername(accountDTO.getUsername());
		
		accountServiceInterface.save(account);
		
		
		
		System.out.println("ACCOUNT ADDED.....");
		
		
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@PutMapping(value = "/updateUser")
	public ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO) {
		User user = userServiceInterface.findOne(userDTO.getId());
		
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		
		userServiceInterface.save(user);
		System.out.println("PROFILE INFO CHANGED.....");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
}
