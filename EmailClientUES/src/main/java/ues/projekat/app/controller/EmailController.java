package ues.projekat.app.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import ues.projekat.app.model.Account;
import ues.projekat.app.model.Attachment;
import ues.projekat.app.model.Folder;
import ues.projekat.app.model.Tag;
import ues.projekat.service.intrfc.AccountServiceInterface;
import ues.projekat.service.intrfc.AttachmentServiceInterface;
import ues.projekat.service.intrfc.MessageServiceInterface;

@RestController
public class EmailController {

	@Autowired
	private MessageServiceInterface messageServiceInterface;
	
	@Autowired
	private AccountServiceInterface accountServiceInterface;
	
	@Autowired
	private AttachmentServiceInterface attachmentServiceInterface;
	
	private static String saveDirectory = "C://Users//Ljubica//Downloads//attachs";
	
	//slanje mejla
	@RequestMapping(value = "/sendemail")
	private void sendmail(@RequestParam String username, @RequestParam String password, @RequestParam String to,
			@RequestParam String cc, @RequestParam String bcc,
			@RequestParam String subject, @RequestParam String content) throws AddressException, MessagingException, IOException {
	
		Account account = accountServiceInterface.findByUsername(username);
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Clas
		props.put("mail.smtp.ssl.enable", "true");
		   
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		   return new PasswordAuthentication(username, password);
		 }
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username, false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
		msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
		msg.setSubject(subject);
		msg.setContent(content, "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(content, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		MimeBodyPart attachPart = new MimeBodyPart();

		attachPart.attachFile("C:\\Users\\Ljubica\\git\\ues2019\\EmailClientUES\\attach\\oasis.jpg");
		multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		Transport.send(msg);   
		
		System.out.println("Mejl poslat");
		
		System.out.println("Dodavaje mejla u bazu");
		
		
		Folder folder = new Folder();
		folder.setName("new folder");
		folder.setAccount(account);
		folder.setParentFolder(folder);
		
		ues.projekat.app.model.Message message = new ues.projekat.app.model.Message();
		
		Attachment attachment = new Attachment();
		attachment.setMessage(message);
		attachment.setName("attach slika");
		attachment.setPath("C:\\Users\\Ljubica\\git\\ues2019\\EmailClientUES\\attach\\oasis.jpg");
		attachment.setMimeType(".jpg");
		
		ArrayList<Attachment> messsageAttach = new ArrayList<Attachment>() ;
		messsageAttach.add(attachment);
		
		
		message.setFrom(username);
		message.setTo(to);
		message.setCc(cc);
		message.setBcc(bcc);
		message.setSubject(subject);
		message.setContent(content);
		message.setDateTime(new Timestamp(0));
		message.setUnread(true);
		message.setAccount(account);
		message.setMessageTags(new ArrayList<Tag>());
		message.setMessageAttachments(message.getMessageAttachments()); //ovde sam stavila bila prvo messageAttach, ali bila je greska
		message.setFolder(folder);
		
		
		messageServiceInterface.save(message);
		
		System.out.println("Dodat je mejl u bazu.");
		
	}
	
	//primanje mejla
	@RequestMapping(value = "/receivemail")
	private void receivemail(@RequestParam String username, @RequestParam String password) {
		
		Account account = accountServiceInterface.findByUsername(username);
		
		Properties properties = new Properties();
		properties.setProperty("mail.store.protocol", "imaps");
		
		Session session = Session.getInstance(properties, 
				new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
				
				});
		try {
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", username, password);
			javax.mail.Folder folder = store.getFolder("INBOX");
			folder.open(javax.mail.Folder.READ_ONLY);
			
			//ovo kada zelimo apsolutno sve mejlove
//			Message message[] = folder.getMessages();
//			for(int i = 0; i < message.length; i++) {
//				System.out.println("received mail...");
//				System.out.println(message[i].getSubject());
//			}
			
			Message[] messages = folder.search(
			       new FlagTerm(new Flags(Flags.Flag.SEEN), false));

			    // Sort messages from recent to oldest
			    Arrays.sort( messages, ( m1, m2 ) -> {
			      try {
			        return m2.getSentDate().compareTo( m1.getSentDate() );
			      } catch ( MessagingException e ) {
			        throw new RuntimeException( e );
			      }
			    } );

			    
			  //  ArrayList<ues.projekat.app.model.Message> gotMessages = new ArrayList<ues.projekat.app.model.Message>();
			    for ( Message message : messages ) {
			    	Address[] froms = message.getFrom();
			      System.out.println( "from: " + froms[0].toString() + 
					" sendDate: " + message.getSentDate().toString()
			          + " subject: " + message.getSubject() + " content: " + message.getContent() + " content type: " + message.getContentType());
			      
			     
			     // store attachment file name, separated by comma
				String attachFiles = "";
				String messageContent = "";
				if (message.getContentType().contains("multipart")) {
				// content may contain attachments
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
						for (int partCount = 0; partCount < numberOfParts; partCount++) {
							MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
								if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
								// this part is attachment
								String fileName = part.getFileName();
								attachFiles += fileName + ", ";
								part.saveFile(saveDirectory + File.separator + fileName);
							} else {
								// this part may be the message content
								messageContent = part.getContent().toString();
							}
						}

						if (attachFiles.length() > 1) {
							attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
						}
					} else if (message.getContentType().contains("text/plain") || message.getContentType().contains("text/html")) {
						Object content = message.getContent();
						if (content != null) {
							messageContent = content.toString();
						}
					}
			      
				System.out.println("Attachments: " + attachFiles);
			      
			      ues.projekat.app.model.Folder folder1 = new ues.projekat.app.model.Folder();
			      folder1.setName("primljeni mejlovi");
			      folder1.setAccount(account);
			      folder1.setParentFolder(folder1);
			      
			      ues.projekat.app.model.Message message1 = new ues.projekat.app.model.Message();
			      message1.setFrom(froms[0].toString());
			      message1.setTo(username);
			      message1.setCc("");
			      message1.setBcc("");
			      message1.setSubject(message.getSubject());
			      message1.setContent(message.getContent().toString());
			      message1.setDateTime(new Timestamp(0));
			      message1.setUnread(true);
			      message1.setAccount(account);
			      message1.setMessageTags(new ArrayList<Tag>());
			      
			      
			 //    new ArrayList<Attachment>().add(attachment);
			      
			      message1.setMessageTags(message1.getMessageTags());
			      //iz nekog razloga uporno trazi boolean iako toga nema u Message
			   //   message1.setMessageAttachments(message1.getMessageAttachments().add(attachment)); 
			      message1.setFolder(folder1);
			      message1.setUnread(true);
			      
			      messageServiceInterface.save(message1);
			      
			      Attachment attachment = new Attachment();
			      attachment.setMessage(message1);
			      attachment.setMimeType(message.getContentType().toString());
			      attachment.setName(attachFiles);
			      attachment.setPath("C://Users//Ljubica//Downloads//attachs");
			      
			      attachmentServiceInterface.save(attachment);
			      
			      System.out.println("Dobijeni mejl je dodat u bazu");
			    }
			folder.close(true);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
