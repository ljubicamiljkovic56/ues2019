package ues.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ues.projekat.app.model.Account;
import ues.projekat.app.model.Folder;
import ues.projekat.app.repository.FolderRepository;
import ues.projekat.service.intrfc.FolderServiceInterface;

@Service
public class FolderService implements FolderServiceInterface {
	
	@Autowired
	private FolderRepository folderRepository;

	@Override
	public List<Folder> findByParent(Folder parentFolder) {
		return folderRepository.findByParentFolder(parentFolder);
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