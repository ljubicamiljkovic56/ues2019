package ues.projekat.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ues.projekat.app.model.Folder;
import ues.projekat.app.model.Message;
import ues.projekat.app.model.Rule;

@SuppressWarnings("serial")
public class FolderDTO implements Serializable {

	
	private Long id;
	private String name;
    private List<RuleDTO> rules;
    private List<MessageDTO> folderMessages;

	
	public FolderDTO() {
		super();
	}
	

	public FolderDTO(Long id, String name, List<RuleDTO> rules, List<MessageDTO> folderMessages) {
		super();
		this.id = id;
		this.name = name;
		this.rules = rules;
		this.folderMessages = folderMessages;
	}
	
    public FolderDTO(Folder f){
        this.id = f.getId();
        this.name = f.getName();
        
    	List<MessageDTO> messageDTOs = new ArrayList<>();
    	if (f.getFolderMessages() != null) {
        	for (Message itMessage: f.getFolderMessages()) {
    			messageDTOs.add(new MessageDTO(itMessage));
    		}
		}
    	this.folderMessages = messageDTOs;
    	
    	List<RuleDTO> ruleDTOs= new ArrayList<>();
    	if (f.getRules() != null) {
    		for(Rule itRule : f.getRules()) {
    			ruleDTOs.add(new RuleDTO(itRule));
    		}
		}
    	this.rules = ruleDTOs;
//    	
//    	List<FolderDTO> folderDTOs = new ArrayList<>();
//    	if (f.getSubfolders() != null) {
//    		for (Folder itFolder : f.getSubfolders()) {
//    			folderDTOs.add(new FolderDTO(itFolder));
//    		}
//    	}
    	
    	/*if (f.getSubfolders() != null && !f.getSubfolders().isEmpty()) {
        	for (Folder itFolder : f.getSubfolders()) {
        		List<MessageDTO> messageDTOs2 = new ArrayList<>();
        		if(itFolder.getMessages() != null && !itFolder.getMessages().isEmpty())
                	for (Message itMessage: itFolder.getMessages()) {
            			messageDTOs2.add(new MessageDTO(itMessage));
            		}
        		List<RuleDTO> ruleDTOs2 = new ArrayList<>();
            	for (Rule itRule: itFolder.getRules()) {
            		ruleDTOs2.add(new RuleDTO(itRule));
        		}
        		
    			FolderDTO itFolderDTO = new FolderDTO(itFolder.getId(), itFolder.getName(), new ArrayList<>(), ruleDTOs2, messageDTOs2);
    			folderDTOs.add(itFolderDTO);
    		}
		}*/
    	
    	//this.subFolders = folderDTOs;
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


	public List<RuleDTO> getRules() {
		return rules;
	}


	public void setRules(List<RuleDTO> rules) {
		this.rules = rules;
	}


	public List<MessageDTO> getFolderMessages() {
		return folderMessages;
	}


	public void setFolderMessages(List<MessageDTO> folderMessages) {
		this.folderMessages = folderMessages;
	}
	
	
}