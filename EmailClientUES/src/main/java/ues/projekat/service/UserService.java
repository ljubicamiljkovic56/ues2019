package ues.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ues.projekat.entity.User;
import ues.projekat.repository.UserRepository;
import ues.projekat.service.intrfc.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {
	
	@Autowired
	UserRepository userRepository;

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

}
