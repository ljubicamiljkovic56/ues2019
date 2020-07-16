//package ues.projekat.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import ues.projekat.app.model.User;
//
//@Service
//public class EmailService {
//
//	private JavaMailSender javaMailSender;
//	
//	@Autowired
//	public EmailService(JavaMailSender javaMailSender) {
//		this.javaMailSender = javaMailSender;
//	}
//	
//	public void sendEmail(User user) throws MailException {
//		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setTo(user.getFirstname());
//		mail.setFrom("example@gmail.com");
//		mail.setSubject("Primeri");
//		mail.setText("Evo primera");
//		
//		javaMailSender.send(mail);
//	}
//}
