package ues.projekat.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

@SuppressWarnings("serial")
@Entity                
@Table(name="accounts")
public class Account implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;
	
	@Column(name = "smtp_address")
	private String smtpAddress;
	
	@Column(name = "smtp_port")
	private Integer smtpPort;
	
	@Column(name = "inserver_type")
	private Short inServerType;
	
	@Column(name = "inserver_address")
	private String inServerAddress;
	
	@Column(name = "inserver_port")
	private Integer inServerPort;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "displayname")
	private String displayname;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	//@JoinColumn(name = "account_folders", referencedColumnName = "folder_id", nullable = true)
	private List<Folder> accountFolders = new ArrayList<Folder>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	//@JoinColumn(name = "account_messages", referencedColumnName = "message_id", nullable = true)
	private List<Message> accountMessages = new ArrayList<Message>();
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id", nullable=false)
	private User user;


	
	public Account() {
		super();
	}

	public Account(Long id, String smtpAddress, Integer smtpPort, Short inServerType, String inServerAddress,
			Integer inServerPort, String username, String password, String displayname,
			ArrayList<Folder> accountFolders, ArrayList<Message> accountMessages, User user) {
		super();
		this.id = id;
		this.smtpAddress = smtpAddress;
		this.smtpPort = smtpPort;
		this.inServerType = inServerType;
		this.inServerAddress = inServerAddress;
		this.inServerPort = inServerPort;
		this.username = username;
		this.password = password;
		this.displayname = displayname;
		this.accountFolders = accountFolders;
		this.accountMessages = accountMessages;
		this.user  = user;
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

	public Short getInServerType() {
		return inServerType;
	}

	public void setInServerType(Short inServerType) {
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

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public List<Folder> getAccountFolders() {
		return accountFolders;
	}

	public void setAccountFolders(ArrayList<Folder> accountFolders) {
		this.accountFolders = accountFolders;
	}

	public List<Message> getAccountMessages() {
		return accountMessages;
	}

	public void setAccountMessages(ArrayList<Message> accountMessages) {
		this.accountMessages = accountMessages;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", smtpAddress=" + smtpAddress + ", smtpPort=" + smtpPort + ", inServerType="
				+ inServerType + ", inServerAddress=" + inServerAddress + ", inServerPort=" + inServerPort
				+ ", username=" + username + ", password=" + password + ", displayname=" + displayname
				+ ", accountFolders=" + accountFolders + ", accountMessages=" + accountMessages + ", user=" + user
				+ "]";
	}


}
