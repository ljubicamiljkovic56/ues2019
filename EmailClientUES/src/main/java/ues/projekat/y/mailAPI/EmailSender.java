package ues.projekat.y.mailAPI;
//
//import java.util.Properties;
//
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;

//
//import javax.mail.Authenticator;
//import javax.mail.BodyPart;
//import javax.mail.Multipart;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//import ues.projekat.entity.Attachment;
//import ues.projekat.entity.Message;
//import ues.projekat.service.intrfc.MessageServiceInterface;
//
//public class EmailSender {
//	
//private final MessageServiceInterface messageService;
//	
//	public EmailSender(MessageServiceInterface messageService) {
//		this.messageService = messageService;
//	}
//	
//	public void sendEmail(Message message) {
//		
//		//Declare to
//		String EMAIL_TO = message.getTo();
//		String EMAIL_CC = message.getCc();
//		String EMAIL_BCC = message.getBcc();
//		String EMAIL_FROM = message.getFrom();
//		
////		final String username = message.getAccount().getUsername();
////		final String password = message.getAccount().getPassword();
//		
//		//Set properties and their values
//		Properties prop = new Properties();
//        prop.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
//        prop.put("mail.smtp.socketFactory.port", "465"); //SSL Port
//        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
//        prop.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
//        prop.put("mail.smtp.starttls.enable", "true");
//        prop.put("mail.smtp.port", "465"); //SMTP Port
//        prop.put("mail.smtp.ssl.enable", "true");
//        
//        //Create a Session object & authenticate uid and pwd
//        Session session = Session.getInstance(prop, new Authenticator() {
//        	protected PasswordAuthentication getPasswordAuthentication() {
//        		return new PasswordAuthentication(username, password);
//        	}
//		});
//        
//        try {
//			//Create MimeMessage object & set values
//        	MimeMessage messageObj = new MimeMessage(session);
//        	
//        	messageObj.setFrom(new InternetAddress(EMAIL_FROM));
//            messageObj.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO));
//            if(EMAIL_CC != null)
//            	messageObj.setRecipients(javax.mail.Message.RecipientType.CC, InternetAddress.parse(EMAIL_CC));
//            if(EMAIL_BCC != null)
//            	messageObj.setRecipients(javax.mail.Message.RecipientType.BCC, InternetAddress.parse(EMAIL_BCC));
//            messageObj.setSubject(message.getSubject());
//            
//            //Declare text values
//            StringBuilder builder = new StringBuilder();
//            String messageContent = message.getContent() + "\n";
//            message.getMessageTags().forEach(t -> builder.append(t.getName()));
//            messageContent += builder.toString();
//            
//            Multipart mp = new MimeMultipart();
//            BodyPart messageBody = new MimeBodyPart();
//            
//            for (Attachment attachment : message.getMessageAttachments()) {
//				MimeBodyPart mbp2 = new MimeBodyPart();
////				mbp2.setContent(((Object) attachment).getData(), "image/*");
//				mbp2.setFileName(attachment.getName());
//				mp.addBodyPart(mbp2);
//			}
//            
//            messageBody.setText(messageContent);
//            mp.addBodyPart(messageBody);
//            messageObj.setContent(mp);
//            Transport.send(messageObj);
//            System.out.println(message.getFrom());
//            message = messageService.save(message);
//        	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//
//}
