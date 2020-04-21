package ues.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ues.projekat.entity.Message;
import ues.projekat.repository.MessageRepository;
import ues.projekat.service.intrfc.MessageServiceInterface;

@Service
public class MessageService implements MessageServiceInterface {

	@Autowired
	MessageRepository messageRepository;
	
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

}