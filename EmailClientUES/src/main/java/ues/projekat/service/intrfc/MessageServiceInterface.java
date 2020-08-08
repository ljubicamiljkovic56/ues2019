package ues.projekat.service.intrfc;

import java.util.List;

import ues.projekat.app.model.Account;
import ues.projekat.app.model.Attachment;
import ues.projekat.app.model.Message;

public interface MessageServiceInterface {
	
	Message findOne(Long messageId);
	
	List<Message> findAll();
	
	Message save(Message message);
	
	void remove(Long id);
	
	List<Message> findBySubject(String subject);

	Message findOneBySubject(String message_subject);

//	void removeAttachments(Attachment attachment);
	//public List<Message> getMessages(Account account);

}
