package ues.projekat.app.controller;

import java.util.ArrayList;
import java.util.List;

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

@RestController
@RequestMapping(value = "api/messages")
public class MessageController {

	@Autowired
	private MessageServiceInterface messageServiceInterface;
	
	@Autowired
	private AccountServiceInterface accountServiceInterface;
	
	@Autowired
	private FolderServiceInterface folderServiceInterface;
	
	@Autowired
    private UserServiceInterface userServiceInterface;
	
	
	//za prikaz svih poruka
	//gadja se u messages.js fajlu da bi imali prikaz u messages.html
	//localhost:8080/api/messages/getallmessages
	@GetMapping(value = "/getallmessages")
	public ResponseEntity<List<MessageDTO>> getAllMessages() {
		List<Message> messages = messageServiceInterface.findAll();
		List<MessageDTO> messagesDTO = new ArrayList<MessageDTO>();
		for (Message m : messages) {
			messagesDTO.add(new MessageDTO(m));
		}
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
//	
	
	
	
	
	
//	@PostMapping(value = "/send/{username}", consumes="application/json")
//	public ResponseEntity<Void> send(@RequestBody MessageDTO messageDTO, @PathVariable("username") String accountUsername){
//		
//		Account account = accountServiceInterface.findByUsername(accountUsername + ".com");
//		
//		if (account == null) {
//			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
//		}
//		
		
//		User user = account.getUser();
//		Folder folder = folderServiceInterface.findByNameAndAccount("Sent", account);
//		if (folder == null) {
//			Rule rule = new Rule(ConditionDTO.SUBJECT, "Sent", OperationDTO.MOVE, null);
//			Set<Rule> rules = new HashSet<Rule>();
//			rules.add(rule);
//			folder = new Folder("Sent", account, rules, new HashSet<Message>(), null, null);
//			rule.setDestination(folder);
//		}
//		Message message = null;
//		/*if (messageService.findOne(messageDTO.getId()) != null) {
//			message = messageService.findOne(messageDTO.getId());
//		}
//		
//		if (message != null && message.getFolder().getName().equals("Drafts")) {
//			messageService.remove(message.getId());
//		} else {
//			message = new Message();
//		}*/
//		message = new Message();
//		getToCCBccToString(messageDTO, message);
//		message.setAccount(account);
//		account.addMessage(message);
//		
//		
//		if (messageDTO.getAttachments() != null) {			
//			for (AttachmentDTO itAttachmentDTO : messageDTO.getAttachments()) {
//				Attachment attachment = new Attachment();
//				System.out.println(itAttachmentDTO.getName());
//				attachment.setData(itAttachmentDTO.getData().toString());
//				attachment.setMessage(message);
//				attachment.setMimeType(itAttachmentDTO.getType());
//				attachment.setName(itAttachmentDTO.getName());
//				
//				message.addAttachment(attachment);
//			}
//		}
//		message.setDate(messageDTO.getDateTime());
//		folder.addMessage(message);
//		message.setFrom(messageDTO.getFrom());
//		message.setSubject(messageDTO.getSubject());
//		message.setContent(messageDTO.getContent());
//		Tag tag = new Tag();
//		if (messageDTO.getTags() != null) {
//			for (TagDTO itTagDTO : messageDTO.getTags()) {
//				tag.setId(itTagDTO.getId());
//				tag.setName(itTagDTO.getName());
//				//tag.set(user);
//				message.getMessageTags().add(tag);
//			}
//		}
//		message.setUnread(messageDTO.isUnread());
//		message.setSubject(messageDTO.getSubject());
//		
//		folder = folderServiceInterface.save(folder);
//		
//		EmailSender mailSender = new EmailSender(messageServiceInterface);
//		mailSender.sendEmail(message);
//		System.out.println(message.toString());
		
//		Message message = new Message(55, account.getUsername(), "", "", "", dateTime, subject, content, unread, account, folder, attachments, tags)
		
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//	
//	@DeleteMapping(value="/{id}")
//	public ResponseEntity<Void> deleteMessage(@PathVariable("id") Long id){
//		Message message = messageServiceInterface.findOne(id);
//		if (message != null){
//			messageServiceInterface.remove(id);
//			return new ResponseEntity<Void>(HttpStatus.OK);
//		} else {		
//			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	@PostMapping(value = "/deleteMessage")
	public ResponseEntity<Void> deleteMessage(@RequestParam String message_subject) {
		
		Message message = messageServiceInterface.findOneBySubject(message_subject);
		
		if (message == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		messageServiceInterface.remove(message.getId());
		
		System.out.println("Obrisana je poruka");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
