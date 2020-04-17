package ues.projekat.dto;

import java.io.Serializable;
import java.util.ArrayList;

import ues.projekat.entity.Folder;
import ues.projekat.entity.Message;

@SuppressWarnings("serial")
public class FolderDTO implements Serializable {

	
	private Long id;
	private String name;
	private Folder parentFolder;
	private ArrayList<Message> folderMessages;
	
	public FolderDTO() {
		super();
	}
	
	
	
	public FolderDTO(Long id, String name, Folder parentFolder, ArrayList<Message> folderMessages) {
		super();
		this.id = id;
		this.name = name;
		this.parentFolder = parentFolder;
		this.folderMessages = folderMessages;
	}

	public FolderDTO(Folder folder) {
		this.id = folder.getId();
		this.name = folder.getName();
		this.parentFolder = folder.getParentFolder();
		this.folderMessages = folder.getFolderMessages();
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
	public ArrayList<Message> getFolderMessages() {
		return folderMessages;
	}
	public void setFolderMessages(ArrayList<Message> folderMessages) {
		this.folderMessages = folderMessages;
	}
	
	
}
