package ues.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ues.projekat.entity.Account;
import ues.projekat.entity.Folder;
import ues.projekat.repository.FolderRepository;
import ues.projekat.service.intrfc.FolderServiceInterface;

@Service
public class FolderService implements FolderServiceInterface {
	
	@Autowired
	FolderRepository folderRepository;

	@Override
	public List<Folder> findByParent(Folder parentFolder) {
		return folderRepository.findByParent(parentFolder);
	}

	@Override
	public Folder findOne(Long folderId) {
		return folderRepository.findOne(folderId);
		
	}

	@Override
	public List<Folder> findAll() {
		return folderRepository.findAll();
	}

	@Override
	public Folder save(Folder folder) {
		return folderRepository.save(folder);
	}

	@Override
	public void remove(Long id) {
		folderRepository.delete(id);

	}

	@Override
	public Folder findByName(String name) {
		return folderRepository.findByName(name);
	}

	@Override
	public Folder findByNameAndAccount(String name, Account account) {
		return folderRepository.findByNameAndAccount(name, account);
	}

}
