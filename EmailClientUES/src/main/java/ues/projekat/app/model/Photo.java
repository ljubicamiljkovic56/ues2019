package ues.projekat.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity                
@Table(name = "photos")
public class Photo implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "photo_id", unique=true, nullable=true) 
	private Long id;
	
	
	@Column(name = "path")
	private String path;
	
	 @OneToOne
	 @JoinColumn(name="contact_id", referencedColumnName="contact_id", nullable=true)
	 private Contact contact;
	
	public Photo() {
		super();
	}


	public Photo(Long id, String path, Contact contact) {
		super();
		this.id = id;
		this.path = path;
		this.contact = contact;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}

	

	public Contact getContact() {
		return contact;
	}


	public void setContact(Contact contact) {
		this.contact = contact;
	}


	@Override
	public String toString() {
		return "Photo [id=" + id + ", path=" + path + ", contact=" + contact + "]";
	}

}