package ues.projekat.y.search.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.app.model.Account;
import ues.projekat.app.model.Message;
import ues.projekat.app.model.User;
import ues.projekat.dto.MessageDTO;
import ues.projekat.service.intrfc.AccountServiceInterface;
import ues.projekat.service.intrfc.MessageServiceInterface;
import ues.projekat.service.intrfc.UserServiceInterface;
import ues.projekat.y.search.indexing.IndexerMessage;
import ues.projekat.y.search.misc.IndexableDocumentMessage;

@RestController
@RequestMapping(value="api/indexmessages")
@CrossOrigin
public class IndexerMessageController {

	@Autowired
	private MessageServiceInterface messageServiceInterface;
	
	
	@Autowired
	private AccountServiceInterface accountServiceInterface;
	
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	@GetMapping
	public ResponseEntity<?> index(Principal principal) throws IOException {
		User user = userServiceInterface.findByUsername(principal.getName());
		List<MessageDTO> messagesDTO = new ArrayList<MessageDTO>();
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		//Account account = accountServiceInterface.resolveAccount(principal, 0);
		
	//	for (Message message : messageServiceInterface.getMessages(account)) {
		//messagesDTO.add(new MessageDTO(message));
			
		//IndexableDocumentMessage indexdocument = IndexerMessage.prepareDocForTesting(message);
		//Document document = IndexerMessage.createIndexDocument(indexdocument);
		//IndexerMessage.indexDocument(document);
	
//		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
