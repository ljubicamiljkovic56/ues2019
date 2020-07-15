package ues.projekat.mailAPI;

import java.io.File;
import java.nio.file.Files;
import java.sql.Timestamp;
import org.apache.tomcat.util.codec.binary.Base64;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import ues.projekat.entity.Account;
import ues.projekat.entity.Attachment;
import ues.projekat.entity.Message;
import ues.projekat.service.intrfc.FolderServiceInterface;
import ues.projekat.service.intrfc.MessageServiceInterface;

//public class EmailReceiver {
//	
//private final String TMP_PATH = "EmailClient/tmp";
//	
//	private final MessageServiceInterface messageService;
//	private final FolderServiceInterface folderService;
//
//	public EmailReceiver(MessageServiceInterface messageService, FolderServiceInterface folderService) {
//		this.messageService = messageService;
//		this.folderService = folderService; 
//	}
//	
//	
//	public void receiveEmail(Account account, Date date) 
//	{
//		try {
//			
//			System.out.println("Da li je pozvana metoda?");
//			Properties properties = new Properties();
//			  properties.put("mail.imap.host", "imap.gmail.com");
//		      properties.put("mail.imap.port", "993");
//		      properties.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		      properties.setProperty("mail.imap.socketFactory.fallback", "false");
//		      properties.setProperty("mail.imap.socketFactory.port", "993");
//		      
//		      Session session = Session.getDefaultInstance(properties, null);
//		      Store store = session.getStore();
//		      store.connect("imap.gmail.com", 993, account.getUsername(), account.getPassword());
//		      Folder emailFolder = store.getFolder("inbox");
//		      emailFolder.open(Folder.READ_ONLY);
//		      javax.mail.Message[] messages = emailFolder.getMessages();
//		      System.out.println(messages.length);
//		      
//		        for (javax.mail.Message message : messages) {
//					if (message.getSentDate().after(date)) {
//						Message temp = new Message();
//						temp.setFrom(message.getFrom()[0].toString());
//						StringBuilder sb = new StringBuilder();
//
//						for (int i = 0; i < message.getReplyTo().length; i++) {
//							if (i != 0) {
//								sb.append(",");
//							}
//							sb.append(message.getReplyTo()[i].toString());
//						}
//						temp.setTo(sb.toString());
//						sb.setLength(0);
//						temp.setCc("");
//						temp.setBcc("");
//						temp.setDateTime((Timestamp) (message.getSentDate()));
//						temp.setSubject(message.getSubject());
//						
//						String result = "";
//						try {
//							MimeMultipart body = (MimeMultipart) message.getContent();
//							for (int i = 0; i < body.getCount(); i++) {
//								MimeBodyPart bodyPart= (MimeBodyPart) body.getBodyPart(i);
//	                            if (bodyPart.isMimeType("text/plain")) {
//	                                result += "\n" + bodyPart.getContent();
//	                                break; 
//	                            } else if (bodyPart.isMimeType("text/html")) {
//	                                result = (String) bodyPart.getContent();
//	                            }
//	                            if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
//									String fileName = bodyPart.getFileName();
//									File file = new File(TMP_PATH + File.separator + fileName);
//									bodyPart.saveFile(file);
//									byte[] bFile = Files.readAllBytes(file.toPath());
//									String sFile = Base64.encodeBase64String(bFile);
//									Attachment attachment = new Attachment();
//									attachment.setName(fileName);
////									((Object) attachment).setData(sFile);
////									attachment.setMessage(temp);
////									attachment.setMimeType("image*/");
////									temp.addAttachment(attachment);
//									file.delete();
//								}
//							}
//						} catch (Exception ex) {
//							ex.printStackTrace();
//						}
//					
//						temp.setContent(result);
//						ues.projekat.entity.Folder inboxFolder = folderService.findByNameAndAccount("Inbox", account);
////						inboxFolder.addMessage(temp);
////						temp.setUnread(false);
////						account.addMessage(temp);
//						messageService.save(temp);
//					}
//				}
//		        emailFolder.close();
//		        store.close();
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//
//
//}
