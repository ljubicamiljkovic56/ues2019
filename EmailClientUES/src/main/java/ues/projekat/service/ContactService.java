package ues.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ues.projekat.entity.Contact;
import ues.projekat.repository.ContactRepository;
import ues.projekat.service.intrfc.ContactServiceInterface;

@Service
public class ContactService implements ContactServiceInterface {
	
	@Autowired
	ContactRepository contactRepository;

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

}
