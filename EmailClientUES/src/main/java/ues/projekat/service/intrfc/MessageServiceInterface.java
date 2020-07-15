package ues.projekat.service.intrfc;

import java.util.List;

import ues.projekat.app.model.Message;

public interface MessageServiceInterface {
	
	Message findOne(Long messageId);
	
	List<Message> findAll();
	
	Message save(Message message);
	
	void remove(Long id);
	
	List<Message> findBySubject(String subject);

}
