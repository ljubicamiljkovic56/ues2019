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

import ues.projekat.app.model.Account;
import ues.projekat.app.model.Message;
import ues.projekat.app.model.User;
import ues.projekat.dto.MessageDTO;
import ues.projekat.service.intrfc.AccountServiceInterface;

import ues.projekat.service.intrfc.FolderServiceInterface;
import ues.projekat.service.intrfc.MessageServiceInterface;
import ues.projekat.service.intrfc.UserServiceInterface;
import ues.projekat.y.search.indexing.IndexerMessage;

@RestController
@RequestMapping(value = "api/messages")
public class MessageController {

	@Autowired
	private MessageServiceInterface messageServiceInterface;
	
	@SuppressWarnings("unused")
	@Autowired
	private AccountServiceInterface accountServiceInterface;
	
	@SuppressWarnings("unused")
	@Autowired
	private FolderServiceInterface folderServiceInterface;
	
	@Autowired
    private UserServiceInterface userServiceInterface;
	
	
	//za prikaz svih poruka
	//gadja se u messages.js fajlu da bi imali prikaz u messages.html
	//localhost:8080/api/messages/getallmessages
	@GetMapping(value = "/getallmessages")
	public ResponseEntity<List<MessageDTO>> getAllMessages() throws IOException {
		List<Message> messages = messageServiceInterface.findAll();
		List<MessageDTO> messagesDTO = new ArrayList<MessageDTO>();
		for (Message m : messages) {
			messagesDTO.add(new MessageDTO(m));
		}
		
		//WriteTextFileMessage.write();
		Directory indexDirMessages;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDirMessages = new SimpleFSDirectory(new File(rb.getString("indexDirMessages")));
		File dataDirMessages = new File(rb.getString("dataDirMessages"));
		IndexerMessage.index(indexDirMessages, dataDirMessages);
		
		
		return new ResponseEntity<List<MessageDTO>>(messagesDTO, HttpStatus.OK);
	}
	
	//prikaz poruke na osnovu id-a, radi u browseru ako ukucamo 
	//ako ukucamo u browseru localhost:8080/api/messages/1 ili 2 dobijemo poruku sa tim id-em
	@GetMapping(value="/{id}")
	public ResponseEntity<MessageDTO> getMessage(@PathVariable("id") Long id){
		Message message = messageServiceInterface.findOne(id);
		if(message == null){
			return new ResponseEntity<MessageDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MessageDTO>(new MessageDTO(message), HttpStatus.OK);
	}
	
	//prikazuje poruke korisnika sa datim username-om
	//radi u browseru ako ukucamo, putanja localhost:8080/api/messages/byUser/miki123
	@GetMapping(value="/byUser/{username}")
	public ResponseEntity<List<MessageDTO>> getMessagesByUsername(@PathVariable("username") String username){
		List<MessageDTO> messages = new ArrayList<>();
		User user = userServiceInterface.findByUsername(username);
		for(Account a : user.getUserAccounts()) {
			for(Message m : a.getAccountMessages()) {
				messages.add(new MessageDTO(m));
			}
		}
		if(messages.isEmpty()){
			return new ResponseEntity<List<MessageDTO>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<MessageDTO>>(messages, HttpStatus.OK);
	}
	
	//prikaz poruke na osnovu subject-a
	@PostMapping(value = "/getMessageBySubject") 
	public ResponseEntity<MessageDTO> getMessageBySubject(@RequestParam String subject) {
			
		Message message = messageServiceInterface.findOneBySubject(subject);
			
		if(message == null) {
			return new ResponseEntity<MessageDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<MessageDTO>(new MessageDTO(message), HttpStatus.OK);
		}
	}
	
	
//	@GetMapping(value = "/emails/{accountId}")
//	public ResponseEntity<List<MessageDTO>> getAllMessagesFromUser(@PathVariable("account_id") Long id){
//		//EmailReceiver emailReceiver = new EmailReceiver(messageServiceInterface, folderServiceInterface);
//		Account account = accountServiceInterface.findOne(id);
//		if (account == null) {
//			return new ResponseEntity<List<MessageDTO>>(HttpStatus.BAD_REQUEST);
//		}
//		
//	//	Date date = account.getAccountMessages().stream().map(Message::getDate).max(Date::compareTo).get();
//	//	emailReceiver.receiveEmail(account, date);
//		List<MessageDTO> messageDTOs = new ArrayList<>();
//		for (Message itMessage : account.getAccountMessages()) {
//		//	if (itMessage.getFolder().getName().equals("Inbox")) {
//				messageDTOs.add(new MessageDTO(itMessage));
//		//	}
//
//		}
//		System.out.println("id ulogovanog: "+ account.getId());
//		System.out.println("username : "+ account.getUsername());
//		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
//	}
	
	//izmena poruke na osnovu subject-a
	@PostMapping(value = "/updateMessage")
	public ResponseEntity<Void> updateMessage(@RequestParam String message_subject, @RequestParam String to, 
			@RequestParam String cc, @RequestParam String bcc, @RequestParam String subject, @RequestParam String content){
		
		//Message message = messageServiceInterface.findOneByAccountUsername(from);
		
		Message message = messageServiceInterface.findOneBySubject(message_subject);
		
	//	Message message = messageServiceInterface.findOne(Long.parseLong(id));
		
		if(message != null) {
			
			
			message.setFrom(message.getFrom());
			message.setTo(to);
			message.setCc(cc);
			message.setBcc(bcc);
			message.setDateTime(message.getDateTime());
			message.setSubject(subject);
			message.setContent(content);
			message.setUnread(message.isUnread());
			message.setFolder(message.getFolder());
			message.setAccount(message.getAccount());
			message.setMessageTags(message.getMessageTags());
			message.setMessageAttachments(message.getMessageAttachments());
			
			
			message = messageServiceInterface.save(message);
			
			System.out.println("Izmena poruke");
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	

	//brisanje mejla na osnovu message subject-a
	@PostMapping(value = "/deleteMessage")
	public ResponseEntity<Void> deleteMessage(@RequestParam String message_subject) {
		
		Message message = messageServiceInterface.findOneBySubject(message_subject);
		
	//	Message message = messageServiceInterface.findOne(Long.parseLong(id));
	
		
		if (message != null) {
	
			
			messageServiceInterface.remove(message.getId());
			
			System.out.println("Obrisana je poruka");
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {

			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}		
		
	}
}
