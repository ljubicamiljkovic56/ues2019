package ues.projekat.entity;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JoinColumn(name = "account_folders", referencedColumnName = "folder_id", nullable = true)
	private ArrayList<Folder> accountFolders;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	@JoinColumn(name = "account_messages", referencedColumnName = "message_id", nullable = true)
	private ArrayList<Message> accountMessages;
	
	public Account() {
		super();
	}

	public Account(Long id, String smtpAddress, Integer smtpPort, Short inServerType, String inServerAddress,
			Integer inServerPort, String username, String password, String displayname,
			ArrayList<Folder> accountFolders, ArrayList<Message> accountMessages) {
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

	@Override
	public String toString() {
		return "Account [id=" + id + ", smtpAddress=" + smtpAddress + ", smtpPort=" + smtpPort + ", inServerType="
				+ inServerType + ", inServerAddress=" + inServerAddress + ", inServerPort=" + inServerPort
				+ ", username=" + username + ", password=" + password + ", displayname=" + displayname
				+ ", accountFolders=" + accountFolders + ", accountMessages=" + accountMessages + "]";
	}

}
