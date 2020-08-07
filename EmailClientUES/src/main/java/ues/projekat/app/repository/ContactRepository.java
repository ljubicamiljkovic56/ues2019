package ues.projekat.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ues.projekat.app.model.Contact;
import ues.projekat.app.model.User;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	public List<Contact> findAllByUser(User user);
	
	public Contact findByDisplayname(String displayname);
	
	
}