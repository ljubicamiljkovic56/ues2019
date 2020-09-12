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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.app.model.Account;
import ues.projekat.app.model.Folder;
import ues.projekat.app.model.Message;
import ues.projekat.app.model.Tag;
import ues.projekat.app.model.User;
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
	@GetMapping(value = "/getallusers")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<User> users = userServiceInterface.findAll();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for (User u : users) {
			usersDTO.add(new UserDTO(u));
		}
		return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);
	}
	
	//login za korisnika
	//poziva se u login.js
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
	

	
	@GetMapping(value = "/getUser/{username}/{password}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username, @PathVariable("password") String password){
		User user = userServiceInterface.findByUsernameAndPassword(username, password);
		
		if (user == null) {
			System.out.println("Neuspesan get");
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user) ,HttpStatus.OK);
	}
	
	//prikaz korisnika na osnovu username-a
	@PostMapping(value = "/getUserByUsername")
	public ResponseEntity<UserDTO> getUserByUsername(@RequestParam String username) {
		
		User user = userServiceInterface.findByUsername(username);
		
		if (user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
		}
	}
	
	//registracija korisnika
	//poziva se u register_user.js
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
			//user.setUserContacts(new ArrayList<Contact>());
			user.setUserTags(new ArrayList<Tag>());
			userService.addUser(user);
			
			System.out.println("Novi korisnik je registrovan");
			
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		
		}else {
			System.out.println("Username vec postoji i ne mozete da se sa njim registrujete");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		
	}
	
	//dodavanje novog account-a, na osnovu ukucanog user username-a
	//poziva se u add_account.js
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
	
	
	//izmena lozinke 
	@PostMapping(value = "/updatePassword")
	public ResponseEntity<Void> updatePassword(@RequestParam String username, @RequestParam String password, @RequestParam String new_password) {
		
		User user = userServiceInterface.findByUsernameAndPassword(username,password);
		
		if(user != null) {
			user.setUsername(user.getUsername());
			user.setPassword(new_password);
			user.setFirstname(user.getFirstname());
			user.setLastname(user.getLastname());
			
			user = userServiceInterface.save(user);
			
			System.out.println("Izmena lozinke");
			
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//izmena korisnika na usnovu username-a
	@PostMapping(value = "/updateUser")
	public ResponseEntity<Void> updateUser(@RequestParam String username,@RequestParam String new_username,
			 @RequestParam String firstname, @RequestParam String lastname){
		
		User user = userServiceInterface.findByUsername(username);
		
		if(user != null) {
			user.setUsername(new_username);
			user.setPassword(user.getPassword());
			user.setFirstname(firstname);
			user.setLastname(lastname);
			
			user = userServiceInterface.save(user);
			
			System.out.print("Izmena korisnikovih podataka");
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//brisanje korisnika na osnovu username-a
	@PostMapping(value = "/deleteUser")
	public ResponseEntity<Void> deleteUser(@RequestParam String user_username){
		
		User user = userServiceInterface.findByUsername(user_username);
		
		if (user != null) {
			
			userServiceInterface.remove(user.getId());
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
			
	}
}
