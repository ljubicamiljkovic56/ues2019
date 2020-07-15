package ues.projekat.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ues.projekat.app.model.Account;
import ues.projekat.app.model.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {
	
	List<Folder> findByParent (Folder parentFolder);
	
	Folder findByName (String name);
	
	Folder findByNameAndAccount (String name, Account account);

}
