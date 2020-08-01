package ues.projekat.y.search.misc;

public class IndexableDocumentMessage {

	private String id;	
	
	private String messageTo;
	
	private String messageBcc;
	
	private String messageFrom;
	
	private String subject;
	
	private String content;
	
	private String folder;
	
	private Boolean unread;
	
	
	public Boolean getUnread() {
		return unread;
	}


	public void setUnread(Boolean unread) {
		this.unread = unread;
	}


	public String getMessageTo() {
		return messageTo;
	}


	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}

	public String getMessageBcc() {
		return messageBcc;
	}

	public void setMessageBcc(String messageBcc) {
		this.messageBcc = messageBcc;
	}

	public String getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
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

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

}