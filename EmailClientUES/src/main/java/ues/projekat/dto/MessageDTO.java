package ues.projekat.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ues.projekat.app.model.Attachment;
import ues.projekat.app.model.Message;
import ues.projekat.app.model.Tag;


@SuppressWarnings("serial")
public class MessageDTO implements Serializable {
	
	private Long id;
	private String from; 
	private String to;
	private String cc;
	private String bcc;
	private Timestamp dateTime;
	private String subject;
	private String content;
	private boolean unread;
	private List<Tag> messageTags;
	private List<Attachment> messageAttachments;
	
	public MessageDTO() {
		super();
	}
	
	public MessageDTO(Long id, String from, String to, String cc, String bcc, Timestamp dateTime, String subject,
			String content, boolean unread, List<Tag> messageTags, List<Attachment> messageAttachments) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.dateTime = dateTime;
		this.subject = subject;
		this.content = content;
		this.unread = unread;
		this.messageTags = messageTags;
		this.messageAttachments = messageAttachments;
	}
	
	public MessageDTO(Message message) {
		this.id = message.getId();
		this.from = message.getFrom();
		this.to = message.getTo();
		this.cc = message.getCc();
		this.bcc = message.getBcc();
		this.dateTime = message.getDateTime();
		this.subject = message.getSubject();
		this.content = message.getContent();
		this.unread = false;
		this.messageTags = message.getMessageTags();
		this.messageAttachments = message.getMessageAttachments();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isUnread() {
		return unread;
	}
	public void setUnread(boolean unread) {
		this.unread = unread;
	}
	public List<Tag> getMessageTags() {
		return messageTags;
	}
	public void setMessageTags(ArrayList<Tag> messageTags) {
		this.messageTags = messageTags;
	}
	public List<Attachment> getMessageAttachments() {
		return messageAttachments;
	}
	public void setMessageAttachments(ArrayList<Attachment> messageAttachments) {
		this.messageAttachments = messageAttachments;
	}
	
}