package ues.projekat.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ues.projekat.app.model.Contact;
import ues.projekat.app.model.Tag;
import ues.projekat.app.model.User;

@SuppressWarnings("serial")
public class UserDTO implements Serializable {
	
	private Long id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private List<Contact> userContacts;
	private List<Tag> userTags;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(Long id, String username, String password, String firstname, String lastname,
			List<Contact> userContacts, List<Tag> userTags) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.userContacts = userContacts;
		this.userTags = userTags;
	}
	
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.userContacts = user.getUserContacts();
		this.userTags = user.getUserTags();
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
	
}