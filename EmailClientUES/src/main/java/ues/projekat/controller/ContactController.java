package ues.projekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.app.model.Contact;
import ues.projekat.app.model.Photo;
import ues.projekat.app.model.User;
import ues.projekat.dto.ContactDTO;
import ues.projekat.service.intrfc.ContactServiceInterface;
import ues.projekat.service.intrfc.UserServiceInterface;

@RestController
@RequestMapping(value="api/contacts")
public class ContactController {
	
	@Autowired
	private ContactServiceInterface contactServiceInterface;
	
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<ContactDTO>> getContacts() {
		List<Contact> contacts = contactServiceInterface.findAll();
		List<ContactDTO> contactsDTO = new ArrayList<ContactDTO>();
		for (Contact c : contacts) {
			contactsDTO.add(new ContactDTO(c));
		}
		return new ResponseEntity<List<ContactDTO>>(contactsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ContactDTO> getContact(@PathVariable("id") Long id){
		Contact contact = contactServiceInterface.findOne(id);
		if(contact == null){
			return new ResponseEntity<ContactDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.OK);
	}
	
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
	
	@PostMapping(value="/add/{username}", consumes="application/json")
	public ResponseEntity<ContactDTO> saveContact(@RequestBody ContactDTO contactDTO, @PathVariable("username") String username){
		User user = userServiceInterface.findByUsername(username);
		if (user == null) {
			return new ResponseEntity<ContactDTO>(HttpStatus.BAD_REQUEST);
		}
		Contact contact = new Contact();
		contact.setEmail(contactDTO.getEmail());
		contact.setFirstname(contactDTO.getFirstname());
		contact.setLastname(contactDTO.getLastname());
		contact.setDisplayname(contactDTO.getDisplayname());
		if(contactDTO.getNote() != null)
			contact.setNote(contactDTO.getNote());
		contact.setUser(user);
		
		if(contactDTO.getContactPhoto() != null)
			contact.setContactPhoto(new Photo(contactDTO.getContactPhoto().getId(), contactDTO.getContactPhoto().getPath()));
	
		contact = contactServiceInterface.save(contact);
		return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/update/{id}", consumes="application/json")
	public ResponseEntity<ContactDTO> updateProduct(@RequestBody ContactDTO contactDTO, @PathVariable("id") Long id){
		Contact contact = contactServiceInterface.findOne(id); 
		
		if (contact == null) {
			return new ResponseEntity<ContactDTO>(HttpStatus.BAD_REQUEST);
		}
		
		contact.setEmail(contactDTO.getEmail());
		contact.setFirstname(contactDTO.getFirstname());
		contact.setLastname(contactDTO.getLastname());
		contact.setDisplayname(contactDTO.getDisplayname());
		if(contactDTO.getNote() != null)
			contact.setNote(contactDTO.getNote());
		
		if(contactDTO.getContactPhoto() != null)
			contact.setContactPhoto(new Photo(contactDTO.getContactPhoto().getId(), contactDTO.getContactPhoto().getPath()));
	
		contact = contactServiceInterface.save(contact);
		
		return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable("id") Long id){
		Contact contact = contactServiceInterface.findOne(id);
		if (contact != null){
			contactServiceInterface.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

}