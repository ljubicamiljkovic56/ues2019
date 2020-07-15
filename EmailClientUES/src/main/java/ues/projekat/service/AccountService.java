package ues.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import ues.projekat.entity.Account;
import ues.projekat.repository.AccountRepository;
import ues.projekat.service.intrfc.AccountServiceInterface;

@Service
public class AccountService implements AccountServiceInterface {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
		
	}

	@Override
	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	public Account findOne(Long accountId) {
		return accountRepository.findOne(accountId);
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account findByUsernameAndPassword(String username, String password) {
		return accountRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public void remove(Long id) {
		accountRepository.delete(id);

	}

}
