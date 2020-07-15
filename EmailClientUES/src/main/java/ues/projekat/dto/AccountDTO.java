package ues.projekat.dto;

import java.io.Serializable;
import java.util.ArrayList;

import ues.projekat.app.model.Account;
import ues.projekat.app.model.Folder;
import ues.projekat.app.model.Message;

@SuppressWarnings("serial")
public class AccountDTO implements Serializable {
	
	private Long id;
	private String smtpAddress;
	private Integer smtpPort;
	private Short inserverType;
	private String inserverAddress;
	private Integer inserverPort;
	private String username;
	private String password;
	private String displayname;
	private ArrayList<Folder> accountFolders;
	private ArrayList<Message> accountMessages;
	
	
	public AccountDTO() {
		super();
	}


	public AccountDTO(Long id, String smtpAddress, Integer smtpPort, Short inserverType, String inserverAddress, Integer inserverPort,
			String username, String password, String displayname, ArrayList<Folder> accountFolders,
			ArrayList<Message> accountMessages) {
		super();
		this.id = id;
		this.smtpAddress = smtpAddress;
		this.smtpPort = smtpPort;
		this.inserverType = inserverType;
		this.inserverAddress = inserverAddress;
		this.inserverPort = inserverPort;
		this.username = username;
		this.password = password;
		this.displayname = displayname;
		this.accountFolders = accountFolders;
		this.accountMessages = accountMessages;
	}

	
	public AccountDTO(Account account) {
		this.id = account.getId();
		this.smtpAddress = account.getSmtpAddress();
		this.smtpPort = account.getSmtpPort();
		this.inserverType = account.getInServerType();
		this.username = account.getUsername();
		this.password = account.getPassword();
		this.displayname = account.getDisplayname();
//    	List<MessageDTO> messageDTOs = new ArrayList<>(); 
//    	if (account.getAccountMessages() != null) {
//	    	for (Message itMessage : account.getAccountMessages()) {
//				messageDTOs.add(new MessageDTO(itMessage));
//			}	
//		}
//    	this.messages = messageDTOs;
//    	List<FolderDTO> folderDTOs = new ArrayList<>(); 
//    	if (account.getAccountFolders() != null) {
//	    	for (Folder itFolder : a.getAccountFolders()) {
//	    		folderDTOs.add(new FolderDTO(itFolder));
//			}	
//		}
//    	this.folders = folderDTOs;
//		this.token = account.getToken();
		
		
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSmtpAddress() {
		return smtpAddress;
	}


	public void setSmtpAddress(String smtpAddress) {
		this.smtpAddress = smtpAddress;
	}


	public Integer getSmtpPort() {
		return smtpPort;
	}


	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}


	public Short getInserverType() {
		return inserverType;
	}


	public void setInserverType(Short inserverType) {
		this.inserverType = inserverType;
	}


	public Integer getInserverPort() {
		return inserverPort;
	}


	public void setInserverPort(Integer inserverPort) {
		this.inserverPort = inserverPort;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDisplayname() {
		return displayname;
	}


	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}


	public ArrayList<Folder> getAccountFolders() {
		return accountFolders;
	}


	public void setAccountFolders(ArrayList<Folder> accountFolders) {
		this.accountFolders = accountFolders;
	}


	public ArrayList<Message> getAccountMessages() {
		return accountMessages;
	}


	public void setAccountMessages(ArrayList<Message> accountMessages) {
		this.accountMessages = accountMessages;
	}

	public String getInserverAddress() {
		return inserverAddress;
	}

	
	public void setInserverAddress(String inserverAddress) {
		this.inserverAddress = inserverAddress;
	}

}