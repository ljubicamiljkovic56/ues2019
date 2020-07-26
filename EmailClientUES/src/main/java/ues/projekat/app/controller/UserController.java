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
import ues.projekat.service.UserService;
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
	
	@Autowired
	private UserService userService;
	
	//nadji sve korisnike
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> users = userServiceInterface.findAll();
		List<UserDTO> userDTO = new ArrayList<UserDTO>();
		for (User user : users) {
			userDTO.add(new UserDTO(user));
		}
		return new ResponseEntity<List<UserDTO>>(userDTO, HttpStatus.OK);
	}
	
	//login za korisnika
	@PostMapping(value = "user/loginUser")
	public ResponseEntity<Void> loginUser(@RequestParam("username") String username, @RequestParam("password") String password){
		System.out.println("Login...");
		User user = userServiceInterface.findByUsernameAndPassword(username, password);
		if (user == null) {
			System.out.println("Neuspesna prijava");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		System.out.println(user.getUsername());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	

	
	@GetMapping(value = "/getUser")
	public ResponseEntity<UserDTO> getUser(@RequestParam("username") String username, @RequestParam("password") String password){
		User user = userServiceInterface.findByUsernameAndPassword(username, password);
		
		if (user == null) {
			System.out.println("Neuspesan get");
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user) ,HttpStatus.OK);
	}
	
	//registracija korisnika
	@PostMapping(value = "/registerUser")
	public ResponseEntity<User> registerUser(@RequestParam String username, @RequestParam String password, 
			@RequestParam String firstname, @RequestParam String lastname) {
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		System.out.println("First name: " + firstname);
		System.out.println("Last name: " + lastname);
		User user = new User();
		boolean check = userService.checkUsername(username);
		if(check == true) {
			System.out.println("Moguca registracija");
			user.setUsername(username);
			user.setPassword(password);
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setUserAccounts(new ArrayList<Account>());
			user.setUserContacts(new ArrayList<Contact>());
			user.setUserTags(new ArrayList<Tag>());
			userService.addUser(user);
			
			System.out.println("Novi korisnik je registrovan");
			
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		
		}else {
			System.out.println("Username vec postoji i ne mozete da se sa njim registrujete");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		
	}
	
	//dodavanje novog account-a
	@PostMapping(value = "/addAccount")
	public ResponseEntity<Void> addAccount(@RequestParam("user_username") String user_username, @RequestParam("username") String username, 
			@RequestParam("password") String password, @RequestParam("displayname") String displayName) {
		
		User user = userServiceInterface.findByUsername(user_username);
		
		System.out.println("Dodavanje novog account-a");
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		System.out.println("Display name: "  + displayName);
		
		Account account = new Account();
		account.setAccountFolders(new ArrayList<Folder>());
		account.setDisplayname(displayName);
		account.setUsername(username);
		account.setPassword(password);
		account.setInServerAddress("InServerAddress");
		account.setInServerPort(22);
		account.setInServerType((short) 223);
		account.setAccountMessages(new ArrayList<Message>());
		account.setSmtpAddress("smtp.gmail.com\"");
		account.setSmtpPort(2233);
		account.setUser(user);
		
		accountServiceInterface.save(account);
		
		System.out.println("Dodat je novi nalog");
		
		return new ResponseEntity<Void>(HttpStatus.OK);		
	}
	
//	@PutMapping(value = "/updateUser")
//	public ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO) {
//		User user = userServiceInterface.findOne(userDTO.getId());
//		
//		user.setUsername(userDTO.getUsername());
//		user.setFirstname(userDTO.getFirstname());
//		user.setLastname(userDTO.getLastname());
//		user.setPassword(userDTO.getPassword());
//		user.setUsername(userDTO.getUsername());
//		
//		userServiceInterface.save(user);
//		System.out.println("Promenjeni su korisnikovi podaci...");
//		
//		return new ResponseEntity<Void>(HttpStatus.OK);
//		
//	}
	
//	@PostMapping(value = "/updateUser")
//	public ResponseEntity<Void> updateUser(@RequestParam("id") String , @RequestParam("username") String username,
//			@RequestParam String password, @RequestParam String firstname, @RequestParam String lastname) {
//		
//
////		Long userLongId = new Long(id);
////		User user = userServiceInterface.findOne(userLongId);
////		userServiceInterface.remove(userLongId);
////		
////		user.setId(id.longValue());
////		user.setUsername(username);
////		user.setPassword(password);
////		user.setFirstname(firstname);
////		user.setLastname(lastname);
//		
//		//userServiceInterface.save(user);
//		
//		System.out.println("Promenjeni su korisnikovi podaci");
//		
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
}
