package ues.projekat.entity;

import java.io.Serializable;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity                 
@Table(name="accounts")
public class Account implements Serializable {
	
	@Id                                 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="account_id", unique=true, nullable=false) 
	private Integer id;
	  
	@Column(name="smtp_address", unique=false, nullable=false) 
	private String smtpAddress;
	
	@Column(name="smtp_port", unique=false, nullable=false) 
	private Integer smtpPort;
	
	@Column(name="in_server_type", unique=false, nullable=false) 
	private Integer inServerType;
	  
	@Column(name="in_server_address", unique=false, nullable=false) 
	private String inServerAddress;
	
	@Column(name="in_server_port", unique=false, nullable=false) 
	private Integer inServerPort;
	
	@Column(name="username", unique=false, nullable=false)
	private String username;
	  
	@Column(name="pasword", unique=false, nullable=false)
	private String password;
	  
	@Column(name="display_name", unique=false, nullable=false)
	private String displayName;

	@Column(name="account_token",unique=true,nullable=true, length=200)
	private String token;
	  
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id", nullable=false)
	private User user;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="account")
	private Set<Message> accountMessages = new HashSet<Message>();
	  
	  @OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="account")
	  private Set<Folder> accountFolders = new HashSet<Folder>();
	  
	public Account() {
		super();
	}
	
	public void addMessage(Message message) {
		if (message.getAccount() != null) {
			message.getAccount().getAccountMessages().remove(message);
		}
		message.setAccount(this);
		getAccountMessages().add(message);
	}
	
	public void addFolder(Folder folder) {
		if (folder.getAccount() != null) {
			folder.getAccount().getAccountFolders().remove(folder);
		}
		folder.setAccount(this);
		getAccountFolders().add(folder);
	}
	
	public void removeMessage(Message message) {
		message.setAccount(null);
		getAccountMessages().remove(message);
	}
	
	public void removeFolder(Folder folder) {
		folder.setAccount(null);
		getAccountFolders().remove(folder);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getInServerType() {
		return inServerType;
	}
	public void setInServerType(Integer inServerType) {
		this.inServerType = inServerType;
	}
	public String getInServerAddress() {
		return inServerAddress;
	}
	public void setInServerAddress(String inServerAddress) {
		this.inServerAddress = inServerAddress;
	}
	public Integer getInServerPort() {
		return inServerPort;
	}
	public void setInServerPort(Integer inServerPort) {
		this.inServerPort = inServerPort;
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Message> getAccountMessages() {
		return accountMessages;
	}
	public void setAccountMessages(Set<Message> accountMessages) {
		this.accountMessages = accountMessages;
	}
	public Set<Folder> getAccountFolders() {
		return accountFolders;
	}
	public void setAccountFolders(Set<Folder> accountFolders) {
		this.accountFolders = accountFolders;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public void add(Message message) {
		if (message.getAccount() != null) {
			message.getAccount().getAccountMessages().remove(message);
		}
		message.setAccount(this);
		getAccountMessages().add(message);
	}
	
	
	
	 

}
