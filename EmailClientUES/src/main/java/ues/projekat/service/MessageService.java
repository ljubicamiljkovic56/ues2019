package ues.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ues.projekat.app.model.Message;
import ues.projekat.app.repository.MessageRepository;
import ues.projekat.service.intrfc.MessageServiceInterface;

@Service
public class MessageService implements MessageServiceInterface {

	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public Message findOne(Long messageId) {
		return messageRepository.findOne(messageId);
	}

	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}

	@Override
	public Message save(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public void remove(Long id) {
		messageRepository.delete(id);

	}

	@Override
	public List<Message> findBySubject(String subject) {
		return messageRepository.findBySubject(subject);
	}

	@Override
	public Message findOneBySubject(String message_subject) {
		return messageRepository.findOneBySubject(message_subject);
	}

	@Override
	public Message findOneByAccountUsername(String from) {
		return messageRepository.findOneByAccountUsername(from);
	}

}