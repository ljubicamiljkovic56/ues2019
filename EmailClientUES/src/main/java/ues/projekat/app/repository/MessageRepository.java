package ues.projekat.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ues.projekat.app.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findBySubject (String subject);

	Message findOneBySubject(String message_subject);

}