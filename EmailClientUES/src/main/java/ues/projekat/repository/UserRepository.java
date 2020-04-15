package ues.projekat.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ues.projekat.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername (String username);
	

}
