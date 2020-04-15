package ues.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ues.projekat.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findBySubject (String subject);

}
