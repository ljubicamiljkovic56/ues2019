package ues.projekat.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.app.model.Contact;
import ues.projekat.app.model.User;
import ues.projekat.dto.ContactDTO;
import ues.projekat.service.intrfc.ContactServiceInterface;
import ues.projekat.service.intrfc.UserServiceInterface;
import ues.projekat.y.search.indexing.Indexer;

@RestController
@RequestMapping(value="api/contacts")
public class ContactController {
	
	@Autowired
	private ContactServiceInterface contactServiceInterface;
	
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	//prikaz svih kontakata
	//poziv u contacts.js
	//localhost:8080/api/contacts/getallcontacts
	@GetMapping(value = "/getallcontacts")
	public ResponseEntity<List<ContactDTO>> getContacts() throws IOException {
		List<Contact> contacts = contactServiceInterface.findAll();
		List<ContactDTO> contactsDTO = new ArrayList<ContactDTO>();
		for (Contact c : contacts) {
			contactsDTO.add(new ContactDTO(c));
		}
		
		
	//	WriteTextFileContacts.write();
		Directory indexDir;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDir = new SimpleFSDirectory(new File(rb.getString("indexDir")));
		File dataDir = new File(rb.getString("dataDir"));
		Indexer.index(indexDir, dataDir);
		
		return new ResponseEntity<List<ContactDTO>>(contactsDTO, HttpStatus.OK);
	}
	
	
	//prikaz kontakta na osnovu id-a
	//recimo localhost:8080/api/contacts/1 ili 2, 3
	@GetMapping(value="/{id}")
	public ResponseEntity<ContactDTO> getContact(@PathVariable("id") Long id){
		Contact contact = contactServiceInterface.findOne(id);
		if(contact == null){
			return new ResponseEntity<ContactDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.OK);
	}
	
	//prikaz kontakta na osnovu user username-a
	//recimo localhost:8080/api/contacts/byUser/pera
	@GetMapping(value="/byUser/{username}")
	public ResponseEntity<List<ContactDTO>> getContactsByUsername(@PathVariable("username") String username) {
		User user = userServiceInterface.findByUsername(username);
		if (user == null) {
			return new ResponseEntity<List<ContactDTO>>(HttpStatus.NOT_FOUND);
		}
		List<Contact> contacts = contactServiceInterface.findAll();
		List<ContactDTO> contactsDTO = new ArrayList<ContactDTO>();
		for (Contact c : contacts) {
			if(c.getUser().getId() == user.getId()) {
				contactsDTO.add(new ContactDTO(c));
			}
		}
		return new ResponseEntity<List<ContactDTO>>(contactsDTO, HttpStatus.OK);
	}
	
	//prikaz jednog kontakta na osnovu displayname-a
	@PostMapping(value = "/getContactByDisplayname")
	public ResponseEntity<ContactDTO> getContactByDisplayname(@RequestParam String displayname) {
		
		Contact contact = contactServiceInterface.findContactByDisplayname(displayname);
		
		if (contact == null) {
			return new ResponseEntity<ContactDTO>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.OK);
		}
	}
	
	//dodavanje novog kontakta na osnovu user username-a
	//poziv u add_contact.js
	@PostMapping(value="/addContact")
	public ResponseEntity<Void> addContact(@RequestParam("user_username") String user_username, @RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam("displayname") String displayname, @RequestParam("email") String email, @RequestParam("note") String note){
		
		User user = userServiceInterface.findByUsername(user_username);
	
		System.out.println("User username: " + user_username);
		System.out.println("Firstname: " + firstname);
		System.out.println("Lastname: " + lastname);
		System.out.println("Display name: " + displayname);
		System.out.println("Email: " + email);
		System.out.println("Note: " + note);
		//System.out.println("Photo: " + photo);
		
		Contact contact = new Contact();
		contact.setFirstname(firstname);
		contact.setLastname(lastname);
		contact.setDisplayname(displayname);
		contact.setEmail(email);
		contact.setNote(note);
		contact.setContactPhoto(null);
		contact.setUser(user);
		
		contactServiceInterface.save(contact);
		
		System.out.println("Dodat je novi kontakt");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
//	@PutMapping(value="/update/{id}", consumes="application/json")
//	public ResponseEntity<ContactDTO> updateProduct(@RequestBody ContactDTO contactDTO, @PathVariable("id") Long id){
//		Contact contact = contactServiceInterface.findOne(id); 
//		
//		if (contact == null) {
//			return new ResponseEntity<ContactDTO>(HttpStatus.BAD_REQUEST);
//		}
//		
//		contact.setEmail(contactDTO.getEmail());
//		contact.setFirstname(contactDTO.getFirstname());
//		contact.setLastname(contactDTO.getLastname());
//		contact.setDisplayname(contactDTO.getDisplayname());
//		if(contactDTO.getNote() != null)
//			contact.setNote(contactDTO.getNote());
//		
//		if(contactDTO.getContactPhoto() != null)
//			contact.setContactPhoto(new Photo(contactDTO.getContactPhoto().getId(), contactDTO.getContactPhoto().getPath(), contact));
//	
//		contact = contactServiceInterface.save(contact);
//		
//		return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.OK);	
//	}
	
	//izmena kontakta na osnovu displayname-a
	@PostMapping(value = "/updateContact")
	public ResponseEntity<Void> updateContact(@RequestParam String displayname, @RequestParam String firstname, 
			@RequestParam String lastname, @RequestParam String new_displayname, @RequestParam String email,
			@RequestParam String note){
		
		Contact contact = contactServiceInterface.findContactByDisplayname(displayname);
		
		if (contact != null) {
			
			contact.setFirstname(firstname);
			contact.setLastname(lastname);
			contact.setDisplayname(new_displayname);
			contact.setEmail(email);
			contact.setNote(note);
			contact.setUser(contact.getUser());
			contact.setContactPhoto(contact.getContactPhoto());
			
			contact = contactServiceInterface.save(contact);
			
			
			System.out.println("Izmena kontakta");
		
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//brisanje kontakta se radi preko displayname-a
	@PostMapping(value = "/deleteContact")
	public ResponseEntity<Void> deleteContact(@RequestParam String displayname) {
		
		Contact contact = contactServiceInterface.findContactByDisplayname(displayname);
				
		if (contact == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		contactServiceInterface.remove(contact.getId());
		
		System.out.println("Obrisan je kontakt");
	
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}