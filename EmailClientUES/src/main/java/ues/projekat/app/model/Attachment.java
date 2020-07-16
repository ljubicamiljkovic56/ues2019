package ues.projekat.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "attachments")
public class Attachment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attachment_id")
	private Long id;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "mime_type")
	private String mimeType;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="message_id", referencedColumnName="message_id", nullable=false)
	private Message message;

	
	public Attachment() {
		super();
	}
	
	public Attachment(Long id, String path, String mimeType, String name, Message message) {
		super();
		this.id = id;
		this.path = path;
		this.mimeType = mimeType;
		this.name = name;
		this.message = message;
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

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", path=" + path + ", mimeType=" + mimeType + ", name=" + name + ", message="
				+ message + "]";
	}

}