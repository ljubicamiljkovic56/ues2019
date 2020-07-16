package ues.projekat.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.app.model.Account;
import ues.projekat.app.model.Folder;
import ues.projekat.app.model.Message;
import ues.projekat.app.model.Rule;
import ues.projekat.dto.FolderDTO;
import ues.projekat.service.intrfc.AccountServiceInterface;
import ues.projekat.service.intrfc.FolderServiceInterface;

@RestController
@RequestMapping(value="api/folders")
public class FolderController {

	
	@Autowired
	private FolderServiceInterface folderServiceInterface;
	

	@Autowired
    private AccountServiceInterface accountServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<FolderDTO>> getFolders() {
		List<Folder> folders = folderServiceInterface.findAll();
		List<FolderDTO> foldersDTO = new ArrayList<FolderDTO>();
		for (Folder f : folders) {
			foldersDTO.add(new FolderDTO(f));
		}
		return new ResponseEntity<List<FolderDTO>>(foldersDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<FolderDTO> getFolder(@PathVariable("id") Long id){
		Folder folder = folderServiceInterface.findOne(id);
		if(folder == null){
			return new ResponseEntity<FolderDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<FolderDTO>(new FolderDTO(folder), HttpStatus.OK);
	}
	
	@GetMapping(value="/byAccount/{id}")
	public ResponseEntity<FolderDTO> getFoldersByAccountId(@PathVariable("id") Long id){
		List<FolderDTO> folders = new ArrayList<>();
		FolderDTO accountFolder = new FolderDTO();
		
		Account account = accountServiceInterface.findOne(id);
		if(account == null){
			return new ResponseEntity<FolderDTO>(HttpStatus.NOT_FOUND);
		}
		accountFolder.setName(account.getUsername());
		for(Folder f : account.getAccountFolders()) {
			if(f.getParentFolder() == null) {
				folders.add(new FolderDTO(f));
			}
		}
		
		if(folders.isEmpty()){
			FolderDTO f = new FolderDTO();
			f.setName("Drafts");
			f.setFolderMessages(new ArrayList<>());
			folders.add(f);
		}
		
		
		return new ResponseEntity<FolderDTO>(accountFolder, HttpStatus.OK);
	}
	
	@PostMapping(value="/add/{parentId}/{accountUsername}", consumes="application/json")
	public ResponseEntity<FolderDTO> saveFolder(@RequestBody FolderDTO folderDTO, @PathVariable("parentId") Long parentId, 
			@PathVariable("accountUsername") String accountUsername){
		Folder folder = new Folder();
		folder.setName(folderDTO.getName());
		
		if(parentId != 0) {
			Folder parentFolder = folderServiceInterface.findOne(parentId);
			folder.setParentFolder(parentFolder);
			//folder.setAccount(parentFolder.getAccount());
		}else {
			Account account = accountServiceInterface.findByUsername(accountUsername + ".com");
			folder.setAccount(account);
		}
		
		
//		Rule rule = new Rule();

//		if(folderDTO.getRules() != null && !folderDTO.getRules().isEmpty()) {
//			rule.setCondition(((List<Message>) folderDTO.getRules()).get(0).getCondition());
//			rule.setOperation(folderDTO.getRules().get(0).getOperation());
//		}
	//	folder.addRule(rule);
		folder.setFolderMessages(new ArrayList<Message>());
	//	folder.setSubfolders(new ArrayList<Folder>());
	
		folder = folderServiceInterface.save(folder);
		return new ResponseEntity<FolderDTO>(new FolderDTO(folder), HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/update/{id}", consumes="application/json")
	public ResponseEntity<FolderDTO> updateFolder(@RequestBody FolderDTO folderDTO, @PathVariable("id") Long id){
		Folder folder = folderServiceInterface.findOne(id); 
		if (folder == null) {
			return new ResponseEntity<FolderDTO>(HttpStatus.BAD_REQUEST);
		}
		
		folder.setName(folderDTO.getName());
		folder.setRules(new HashSet<Rule>());
//		Rule rule = new Rule();
//
//		if(folderDTO.getRules() != null && !folderDTO.getRules().isEmpty()) {
//			rule.setCondition(folderDTO.getRules().get(0).getCondition());
//			rule.setOperation(folderDTO.getRules().get(0).getOperation());
//		}
//		folder.addRule(rule);
		
	
		folder = folderServiceInterface.save(folder);
		
		return new ResponseEntity<FolderDTO>(new FolderDTO(folder), HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteFolder(@PathVariable("id") Long id){
		Folder folder = folderServiceInterface.findOne(id);
		if (folder != null){
			folderServiceInterface.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}	
}