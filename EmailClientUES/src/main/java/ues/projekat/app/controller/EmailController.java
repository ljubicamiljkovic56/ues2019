package ues.projekat.app.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.app.model.Account;
import ues.projekat.app.model.Attachment;
import ues.projekat.app.model.Folder;
import ues.projekat.app.model.Tag;
import ues.projekat.service.intrfc.AccountServiceInterface;
import ues.projekat.service.intrfc.MessageServiceInterface;

@RestController
public class EmailController {

	@Autowired
	private MessageServiceInterface messageServiceInterface;
	
	@Autowired
	private AccountServiceInterface accountServiceInterface;
	
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
//      prop.put("mail.smtp.port", "465"); //SMTP Port
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
		
		ues.projekat.app.model.Message message = new ues.projekat.app.model.Message();
		Attachment attachment = new Attachment();
		attachment.setMessage(message);
		attachment.setName("attach slika");
		attachment.setPath("C:\\Users\\Ljubica\\git\\ues2019\\EmailClientUES\\attach\\oasis.jpg");
		attachment.setMimeType(".jpg");
		ArrayList<Attachment> messsageAttach = new ArrayList<Attachment>() ;
		messsageAttach.add(attachment);
		Folder folder = new Folder();
		folder.setName("new folder");
		folder.setAccount(account);
		folder.setParentFolder(folder);
		//message.setId(new Long(3));
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
		message.setMessageAttachments(messsageAttach);
		message.setFolder(folder);
		
		
		messageServiceInterface.save(message);
		
		System.out.println("Dodat je mejl u bazu.");
		
	}
}
