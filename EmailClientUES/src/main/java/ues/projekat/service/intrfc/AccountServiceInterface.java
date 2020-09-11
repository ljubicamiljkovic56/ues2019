package ues.projekat.service.intrfc;

import java.util.List;

import ues.projekat.app.model.Account;

public interface AccountServiceInterface {
	
	Account save(Account account);
    
	Account findByUsername(String username);

	Account findOne(Long accountId);
	
	List<Account> findAll();
	
	Account findByUsernameAndPassword(String username, String password);
	
	Account findByDisplayname(String displayname);

	void remove(Long id);

}
