package ues.projekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ues.projekat.app.model.Account;
import ues.projekat.app.model.Contact;
import ues.projekat.app.model.Tag;
import ues.projekat.app.model.User;
import ues.projekat.app.repository.UserRepository;
import ues.projekat.service.intrfc.UserServiceInterface;


@Service
public class UserService implements UserServiceInterface {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public void add(User user) {
		user = userRepository.findOne((Long) user.getId());
		userRepository.save(user);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findOne(Long userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
    public User addUser(User user) {
    	User user1 = new User();
    	user1.setId(user.getId());
    	user1.setUsername(user.getUsername());
    	user1.setPassword(user.getPassword());
    	user1.setFirstname(user.getFirstname());
    	user1.setLastname(user.getLastname());
    	user1.setUserAccounts((ArrayList<Account>) user.getUserAccounts());
    	user1.setUserContacts((ArrayList<Contact>) user1.getUserContacts());
    	user1.setUserTags((ArrayList<Tag>) user1.getUserTags());
    	userRepository.save(user);
		return user1;
    }
	
	public boolean checkUsername(String username) {
		User user3 = userRepository.findByUsername(username);
		if(user3 == null) {
		    return true;
		}else {
		   return false;
		}
	}

}