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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	//za prikaz svih foldera
	//poziv u folders.js
	//putanja localhost:8080/api/folders/getallfolders
	@GetMapping(value = "/getallfolders")
	public ResponseEntity<List<FolderDTO>> getFolders() {
		List<Folder> folders = folderServiceInterface.findAll();
		List<FolderDTO> foldersDTO = new ArrayList<FolderDTO>();
		for (Folder f : folders) {
			foldersDTO.add(new FolderDTO(f));
		}
		return new ResponseEntity<List<FolderDTO>>(foldersDTO, HttpStatus.OK);
	}
	
	
	//prikaz foldera sa datim id-em
	//localhost:8080/api/folders/1, 2, 3 recimo	
	@GetMapping(value="/{id}")
	public ResponseEntity<FolderDTO> getFolder(@PathVariable("id") Integer id){
		Folder folder = folderServiceInterface.findOne(id);
		if(folder == null){
			return new ResponseEntity<FolderDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<FolderDTO>(new FolderDTO(folder), HttpStatus.OK);
	}
	
	//prikaz foldera na osnovu account id-a
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
			//f.setSubFolders(new ArrayList<>());
			folders.add(f);
		}
		
		//accountFolder.setSubFolders(folders);
		
		return new ResponseEntity<FolderDTO>(accountFolder, HttpStatus.OK);
	}
	
	
	//dodavanje novog foldera na osnovu ukucanog account username-a
	//poziva se u add_folder.js
	@PostMapping(value = "/addFolder") 
	public ResponseEntity<Void> addFolder(@RequestParam("account_username") String account_username, @RequestParam("name") String name) {
		
		Account account = accountServiceInterface.findByUsername(account_username);
		
		System.out.println("Account_username: " + account_username);
		System.out.println("Folder name: " + name);
		
		Folder folder = new Folder();
		folder.setName(name);
		folder.setParentFolder(folder);
		folder.setFolderMessages(new ArrayList<Message>());
		folder.setRules(new HashSet<Rule>());
		folder.setAccount(account);
		
		folderServiceInterface.save(folder);
		
		System.out.println("Dodat je novi folder.");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping(value="/update/{id}", consumes="application/json")
	public ResponseEntity<FolderDTO> updateFolder(@RequestBody FolderDTO folderDTO, @PathVariable("id") Long id){
		Folder folder = folderServiceInterface.findOne(id); 
		if (folder == null) {
			return new ResponseEntity<FolderDTO>(HttpStatus.BAD_REQUEST);
		}
		
		folder.setName(folderDTO.getName());
		folder.setRules(new HashSet<Rule>());
		Rule rule = new Rule();

		if(folderDTO.getRules() != null && !folderDTO.getRules().isEmpty()) {
			rule.setCondition(folderDTO.getRules().get(0).getCondition());
			rule.setOperation(folderDTO.getRules().get(0).getOperation());
		}
		folder.addRule(rule);
		
	
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
