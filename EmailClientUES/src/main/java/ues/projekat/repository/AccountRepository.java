package ues.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ues.projekat.app.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account findByUsername (String username);
	
	Account findByUsernameAndPassword (String username, String password);

}
