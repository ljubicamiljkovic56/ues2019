package ues.projekat.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ues.projekat.app.model.Folder;
import ues.projekat.app.model.Message;
import ues.projekat.app.model.Rule;

@SuppressWarnings("serial")
public class FolderDTO implements Serializable {

	
	private Long id;
	private String name;
	private Folder parentFolder;
	private List<Message> folderMessages;
	private HashSet<Rule> rules;

	
	public FolderDTO() {
		super();
	}
	
	
	
	public FolderDTO(Long id, String name, Folder parentFolder, ArrayList<Message> folderMessages, HashSet<Rule> rules) {
		super();
		this.id = id;
		this.name = name;
		this.parentFolder = parentFolder;
		this.folderMessages = folderMessages;
		this.rules = rules;
		
	}

	public FolderDTO(Folder folder) {
		this.id = folder.getId();
		this.name = folder.getName();
		this.parentFolder = folder.getParentFolder();
		this.folderMessages = folder.getFolderMessages();
		this.rules = (HashSet<Rule>) folder.getRules();
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Folder getParentFolder() {
		return parentFolder;
	}
	public void setParentFolder(Folder parentFolder) {
		this.parentFolder = parentFolder;
	}
	public List<Message> getFolderMessages() {
		return folderMessages;
	}
	public void setFolderMessages(ArrayList<Message> folderMessages) {
		this.folderMessages = folderMessages;
	}

	public HashSet<Rule> getRules() {
		return rules;
	}

	public void setRules(HashSet<Rule> rules) {
		this.rules = rules;
	}
	
}