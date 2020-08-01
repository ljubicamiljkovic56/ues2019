package ues.projekat.service.intrfc;

import java.util.List;

import ues.projekat.app.model.Contact;
import ues.projekat.app.model.User;

public interface ContactServiceInterface {
	
	Contact findOne(Long contactId);
	
	List<Contact> findAll();
	
	Contact save(Contact contact);
	
	void remove(Long id);

	List<Contact> findAllByUser(User user);
	
}