package ues.projekat.service.intrfc;

import java.util.List;

import ues.projekat.entity.Contact;

public interface ContactServiceInterface {

	
	Contact findOne(Long contactId);
	
	List<Contact> findAll();
	
	Contact save(Contact contact);
	
	void remove(Long id);
}
