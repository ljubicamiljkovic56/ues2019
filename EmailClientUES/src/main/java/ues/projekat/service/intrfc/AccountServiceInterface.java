package ues.projekat.service.intrfc;

import java.util.List;

import ues.projekat.entity.Account;

public interface AccountServiceInterface {
	
	Account save(Account account);
    
	Account findByUsername(String username);

	Account findOne(Long accountId);
	
	List<Account> findAll();
	
	Account findByUsernameAndPassword(String username, String password);

	void remove(Long id);

}
