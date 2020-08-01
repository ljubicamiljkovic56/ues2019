package ues.projekat.y.search.controller;

import java.io.IOException;
import java.security.Principal;

import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.app.model.Contact;
import ues.projekat.app.model.User;
import ues.projekat.service.intrfc.ContactServiceInterface;
import ues.projekat.service.intrfc.UserServiceInterface;
import ues.projekat.y.search.indexing.IndexerContact;
import ues.projekat.y.search.misc.IndexableDocumentContact;

@RestController
@RequestMapping(value="api/indexcontacts")
@CrossOrigin
public class IndexerContactController {

	
	@Autowired
	private ContactServiceInterface contactServiceInterface;
	
	
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	
	@GetMapping
	public ResponseEntity<?> index(Principal principal) throws IOException  {
	//	List<ContactDTO> contactDTO = new ArrayList<ContactDTO>();
	
		User user = userServiceInterface.findByUsername(principal.getName());
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		for (Contact contact : contactServiceInterface.findAllByUser(user)) {
		//	contactDTO.add(new ContactDTO(contact));
			IndexableDocumentContact indexContact = IndexerContact.prepareDocForTesting(contact);
			Document documentContact = IndexerContact.createIndexDocument(indexContact);
			IndexerContact.indexDocument(documentContact);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
