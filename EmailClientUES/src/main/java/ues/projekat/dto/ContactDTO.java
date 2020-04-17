package ues.projekat.dto;

import java.io.Serializable;

import ues.projekat.entity.Contact;
import ues.projekat.entity.Photo;

@SuppressWarnings("serial")
public class ContactDTO implements Serializable {

	private Long id;
	private String firstname;
	private String lastname;
	private String displayname;
	private String email;
	private String note;
	private Photo contactPhoto;
	
	public ContactDTO() {
		super();
	}
	
	public ContactDTO(Long id, String firstname, String lastname, String displayname, String email, String note,
			Photo contactPhoto) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.displayname = displayname;
		this.email = email;
		this.note = note;
		this.contactPhoto = contactPhoto;
	}
	
	public ContactDTO(Contact contact) {
		this.id = contact.getId();
		this.firstname = contact.getFirstname();
		this.lastname = contact.getLastname();
		this.displayname = contact.getDisplayname();
		this.email = contact.getEmail();
		this.note = contact.getNote();
		this.contactPhoto = contact.getContactPhoto();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Photo getContactPhoto() {
		return contactPhoto;
	}

	public void setContactPhoto(Photo contactPhoto) {
		this.contactPhoto = contactPhoto;
	}
	
	
	
}
