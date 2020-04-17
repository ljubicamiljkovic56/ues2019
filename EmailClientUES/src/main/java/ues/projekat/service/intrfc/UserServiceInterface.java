package ues.projekat.service.intrfc;

import ues.projekat.entity.User;

public interface UserServiceInterface {
	
	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);
	
	void add(User user);

	User save(User user);

	User findOne(Long userId);

}
