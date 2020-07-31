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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "user")
	//@JoinColumn(name = "user_contacts", referencedColumnName = "contact_id", nullable = true)
	private List<Contact> userContacts = new ArrayList<Contact>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//@JoinColumn(name = "user_tags", referencedColumnName = "tag_id", nullable = true)
	private List<Tag> userTags = new ArrayList<Tag>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	//@JoinColumn(name = "user_accounts", referencedColumnName = "account_id", nullable = true)
	private List<Account> userAccounts = new ArrayList<Account>();
	
	public User() {
		super();
	}

	public User(Long id, String username, String password, String firstname, String lastname,
			ArrayList<Contact> userContacts, ArrayList<Tag> userTags, ArrayList<Account> userAccounts) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.userContacts = userContacts;
		this.userTags = userTags;
		this.userAccounts = userAccounts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Contact> getUserContacts() {
		return userContacts;
	}

	public void setUserContacts(ArrayList<Contact> userContacts) {
		this.userContacts = userContacts;
	}

	public List<Tag> getUserTags() {
		return userTags;
	}

	public void setUserTags(ArrayList<Tag> userTags) {
		this.userTags = userTags;
	}

	public List<Account> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(ArrayList<Account> userAccounts) {
		this.userAccounts = userAccounts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", userContacts=" + userContacts + ", userTags=" + userTags
				+ ", userAccounts=" + userAccounts + "]";
	}	
}