package ues.projekat.app.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
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
import ues.projekat.app.model.Attachment;
import ues.projekat.app.model.Message;
import ues.projekat.app.model.User;
import ues.projekat.dto.AttachmentDTO;
import ues.projekat.dto.MessageDTO;
import ues.projekat.service.intrfc.AccountServiceInterface;
import ues.projekat.service.intrfc.AttachmentServiceInterface;
import ues.projekat.service.intrfc.FolderServiceInterface;
import ues.projekat.service.intrfc.MessageServiceInterface;
import ues.projekat.service.intrfc.UserServiceInterface;
import ues.projekat.y.search.indexing.IndexerAttachments;
import ues.projekat.y.search.indexing.IndexerForPdf;
import ues.projekat.y.search.indexing.IndexerMessage;
import ues.projekat.y.search.indexing.PdfFileParser;
import ues.projekat.y.search.misc.SerbianAnalyzer;

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
	
	
	private final String source = "C:\\Users\\Ljubica\\Downloads\\attachs";
	private final String indexFile = "C:\\Users\\Ljubica\\Downloads\\indexDirPdf";
	private IndexWriter writer = null;
	private File indexDirectory = null;
	private String fileContent;

	
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
		
		
		
		System.out.println(messagesDTO);
		
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
	
	@Autowired
	private AttachmentServiceInterface attachmentServiceInterface;
	
	@GetMapping(value = "/getattach")
	public ResponseEntity<List<AttachmentDTO>> getAttachments() throws IOException {
		List<Attachment> attachments = attachmentServiceInterface.findAll();
		
		if(attachments == null) {
			return new ResponseEntity<List<AttachmentDTO>>(HttpStatus.NOT_FOUND);
		}
		List<AttachmentDTO> attachDTO = new ArrayList<AttachmentDTO>();
		for(Attachment a : attachments) {
			attachDTO.add(new AttachmentDTO(a));
		}
		
		Directory indexDirAttach;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDirAttach = new SimpleFSDirectory(new File(rb.getString("indexDirAttach")));
		File dataDirAttach = new File(rb.getString("dataDirAttach"));
		IndexerAttachments.index(indexDirAttach, dataDirAttach);
		
		//poziv indexera za pdf fajlove
		try {
			new IndexerForPdf();
		}catch (Exception e) {
			System.out.println("Greska kod pokretanja: " + e);
		}
		
		return new ResponseEntity<List<AttachmentDTO>>(attachDTO, HttpStatus.OK);
		
		}
	
	 public MessageController() throws FileNotFoundException, CorruptIndexException, IOException {
	        try {
	            long start = System.currentTimeMillis();
	            createIndexWriter();
	            checkFileValidity();
	            closeIndexWriter();
	            long end = System.currentTimeMillis();
	            System.out.println("Total Document Indexed : " + TotalDocumentsIndexed());
	            System.out.println("Total time" + (end - start) / (100 * 60));
	        } catch (Exception e) {
	            System.out.println("Greska pri indeksiranju.");
	        }
	    }

	    @SuppressWarnings("deprecation")
		private void createIndexWriter() {
	        try {
	            indexDirectory = new File(indexFile);
	            if (!indexDirectory.exists()) {
	                indexDirectory.mkdir();
	            }
	            FSDirectory dir = FSDirectory.open(indexDirectory);
	            SerbianAnalyzer analyzer = new SerbianAnalyzer();
	            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
	            writer = new IndexWriter(dir, config);
	        } catch (Exception ex) {
	            System.out.println("Greska sa index writerom");
	        }
	    }

	    private void checkFileValidity() {

	        File[] filesToIndex = new File[100]; // suppose there are 100 files at max
	        filesToIndex = new File(source).listFiles();
	        for (File file : filesToIndex) {
	            try {
	                //to check whenther the file is a readable file or not.
	                if (!file.isDirectory()
	                        && !file.isHidden()
	                        && file.exists()
	                        && file.canRead()
	                        && file.length() > 0.0
	                        && file.isFile() ) {
	                    if(file.getName().endsWith(".txt")){
	                        indexTextFiles(file);//if the file text file no need to parse text. 
	                    System.out.println("INDEXED FILE " + file.getAbsolutePath() + " :-) ");
	                    }
	                    else if(file.getName().endsWith(".doc") || file.getName().endsWith(".pdf")){
	                        //different methof for indexing doc and pdf file.
	                       StartIndex(file);                    
	                    }
	                }
	            } catch (Exception e) {
	                System.out.println("Greska, ne moze se indeksirati fajl " + file.getAbsolutePath());
	            }
	        }
	    }
	    

	    @SuppressWarnings("deprecation")
		public void StartIndex(File file) throws FileNotFoundException, CorruptIndexException, IOException {
	         fileContent = null;
	        try {
	            Document doc = new Document();
	            if (file.getName().endsWith(".pdf")) {
	                //call the pdf file parser and get the content of pdf file in txt format
	                fileContent = new PdfFileParser().PdfFileParser(file.getAbsolutePath());
	            }
	            doc.add(new Field("content", fileContent, Field.Store.YES, Field.Index.ANALYZED,Field.TermVector.WITH_POSITIONS_OFFSETS));
	            doc.add(new Field("filename", file.getName(),Field.Store.YES, Field.Index.ANALYZED));
	            doc.add(new Field("fullpath", file.getAbsolutePath(),Field.Store.YES, Field.Index.ANALYZED));
	            if (doc != null) {
	                writer.addDocument(doc);
	            }
	            System.out.println("Indexed" + file.getAbsolutePath());
	        } catch (Exception e) {
	            System.out.println("Greska pri indeksiranju" + (file.getAbsolutePath()));
	        }
	    }

	    @SuppressWarnings("deprecation")
		private void indexTextFiles(File file) throws CorruptIndexException, IOException {
	        Document doc = new Document();
	        doc.add(new Field("content", new FileReader(file)));
	        doc.add(new Field("filename", file.getName(),Field.Store.YES, Field.Index.ANALYZED));
	        doc.add(new Field("fullpath", file.getAbsolutePath(),Field.Store.YES, Field.Index.ANALYZED));
	        if (doc != null) {
	            writer.addDocument(doc);
	        }
	    }

	    @SuppressWarnings("deprecation")
		private int TotalDocumentsIndexed() {
	        try {
	            IndexReader reader = IndexReader.open(FSDirectory.open(indexDirectory));
	            return reader.maxDoc();
	        } catch (Exception ex) {
	            System.out.println("Greska, nema indeksa");
	        }
	        return 0;
	    }
	    private void closeIndexWriter() {
	        try {
	         //   writer.optimize();
	            writer.close();
	        } catch (Exception e) {
	            System.out.println("Indexer se ne moze zatvoriti");
	        }
	    }
}