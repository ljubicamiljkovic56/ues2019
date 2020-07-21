package ues.projekat.service.intrfc;

import java.util.List;

import ues.projekat.app.model.User;

public interface UserServiceInterface {
	
	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);
	
	void add(User user);

	User save(User user);

	User findOne(Long userId);

	List<User> findAll();
	
}
