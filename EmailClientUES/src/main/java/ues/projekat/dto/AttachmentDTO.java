package ues.projekat.dto;

import java.io.Serializable;

import ues.projekat.app.model.Attachment;
import ues.projekat.app.model.Message;

@SuppressWarnings("serial")
public class AttachmentDTO implements Serializable {
	
	private Long id;
	private String path;
	private String mimeType;
	private String name;
	private Message message_id;
	
	public AttachmentDTO() {
		super();
	}
	
	public AttachmentDTO(Long id, String path, String mimeType, String name, Message message_id) {
		super();
		this.id = id;
		this.path = path;
		this.mimeType = mimeType;
		this.name = name;
		this.message_id = message_id;
	}
	
	public AttachmentDTO(Attachment attachment) {
		this.id = attachment.getId();
		this.path = attachment.getPath();
		this.mimeType = attachment.getMimeType();
		this.name = attachment.getName();
		this.message_id = attachment.getMessage();
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

//	public String getMessage_id() {
//		return message_id;
//	}
//
//	public void setMessage_id(String message_id) {
//		this.message_id = message_id;
//	}

//	public Message getMessage_id() {
//		return message_id;
//	}
//
//	public void setMessage_id(Message message_id) {
//		this.message_id = message_id;
//	}
	
}