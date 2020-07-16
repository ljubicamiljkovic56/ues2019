package ues.projekat.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.app.model.Account;
import ues.projekat.app.model.Contact;
import ues.projekat.app.model.Folder;
import ues.projekat.app.model.Message;
import ues.projekat.app.model.Tag;
import ues.projekat.app.model.User;
import ues.projekat.dto.AccountDTO;
import ues.projekat.dto.UserDTO;
import ues.projekat.service.intrfc.AccountServiceInterface;
import ues.projekat.service.intrfc.UserServiceInterface;

@CrossOrigin
@RestController
@RequestMapping(value="api/users")
public class UserController {
	
	@Autowired
    private AccountServiceInterface accountServiceInterface;
	
	
	@Autowired
    private UserServiceInterface userServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> users = userServiceInterface.findAll();
		List<UserDTO> userDTO = new ArrayList<UserDTO>();
		for (User user : users) {
			userDTO.add(new UserDTO(user));
		}
		return new ResponseEntity<List<UserDTO>>(userDTO, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "user/loginUser")
	public ResponseEntity<Void> loginUser(@RequestParam("username") String username, @RequestParam("password") String password){
		System.out.println("LOGIN..........");
		User user = userServiceInterface.findByUsernameAndPassword(username, password);
		if (user == null) {
			System.out.println("Neuspesna prijava");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		System.out.println(user.getUsername());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
//	@PostMapping(path="user/loginUser")
//	public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
//		System.out.println("LOGIN..........");
//		System.out.println(username);
//		System.out.println(password);
//		User user = userServiceInterface.findByUsernameAndPassword(username, password);
//		if (user == null) {
//			System.out.println("Neuspesna prijava");
//			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
//		}
//		System.out.println(user.getUsername());
//		
//		return new ResponseEntity<String>(HttpStatus.CREATED);
//	}

	
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
	
	@PutMapping(value = "/addAccount/{username}")
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
