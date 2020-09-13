package ues.projekat.app.model;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "messages")
public class Message implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private Long id;
	
	@Column(name = "message_from")
	private String from;
	
	@Column(name = "message_to")
	private String to;
	
	@Column(name = "cc")
	private String cc;
	
	@Column(name = "bcc")
	private String bcc;
	
	@Column(name = "message_date")
	private Timestamp dateTime;
	
	@Column(name = "message_subject")
	private String subject;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "unread")
	private boolean unread;
	
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name="folder_id", referencedColumnName="folder_id", nullable=false)
	 private Folder folder;
	  
	 @ManyToOne
	 @JoinColumn(name="account_id", referencedColumnName="account_id", nullable=false)
	 private Account account;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//@JoinColumn(name = "message_tags", referencedColumnName = "tag_id", nullable = true)
	private List<Tag> messageTags;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	//@JoinColumn(name = "message_attach", nullable = true)
	private List<Attachment> messageAttachments;
	
	public Message() {
		super();
	}



	public Message(Long id, String from, String to, String cc, String bcc, Timestamp dateTime, String subject,
			String content, boolean unread, Folder folder, Account account, List<Tag> messageTags,
			List<Attachment> messageAttachments) {
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
		this.folder = folder;
		this.account = account;
		this.messageTags = messageTags;
		this.messageAttachments = messageAttachments;
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

	public void setMessageTags(List<Tag> messageTags) {
		this.messageTags = messageTags;
	}

	public List<Attachment> getMessageAttachments() {
		return messageAttachments;
	}

	

	public void setMessageAttachments(List<Attachment> messageAttachments) {
		this.messageAttachments = messageAttachments;
	}



	public Folder getFolder() {
		return folder;
	}


	public void setFolder(Folder folder) {
		this.folder = folder;
	}



	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	@Override
	public String toString() {
		return "Message [id=" + id + ", from=" + from + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", dateTime="
				+ dateTime + ", subject=" + subject + ", content=" + content + ", unread=" + unread + ", folder="
				+ folder + ", account=" + account + ", messageTags=" + messageTags + ", messageAttachments="
				+ messageAttachments + "]";
	}
	
}