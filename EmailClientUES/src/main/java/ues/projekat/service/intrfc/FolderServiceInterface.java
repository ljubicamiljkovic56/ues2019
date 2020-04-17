package ues.projekat.service.intrfc;

import java.util.List;

import ues.projekat.entity.Account;
import ues.projekat.entity.Folder;

public interface FolderServiceInterface {

	List<Folder> findByParent(Folder parentFolder);
	
	Folder findOne(Long folderId);
	
	List<Folder> findAll();
	
	Folder save(Folder folder);
	
	void remove(Long id);
	
	Folder findByName(String name);
	
	Folder findByNameAndAccount(String name, Account account);
}
