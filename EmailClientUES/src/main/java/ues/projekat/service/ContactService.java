package ues.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ues.projekat.app.model.Contact;
import ues.projekat.app.repository.ContactRepository;
import ues.projekat.service.intrfc.ContactServiceInterface;

@Service
public class ContactService implements ContactServiceInterface {
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public Contact findOne(Long contactId) {
		return contactRepository.findOne(contactId);
	}

	@Override
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	@Override
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public void remove(Long id) {
		contactRepository.delete(id);
	}
	
	@Override
	public List<Contact> findAllByUser(ues.projekat.app.model.User user) {
		return contactRepository.findAllByUser(user);

	}

	@Override
	public Contact findContactByDisplayname(String displayname) {
		return contactRepository.findByDisplayname(displayname);
	}


}
