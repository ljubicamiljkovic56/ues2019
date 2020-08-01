package ues.projekat.y.search.misc;

public class FoundDocumentMessage {
	
	private String id;
	
	private String messageFrom;
	
	private String messageBcc;
	
	private String messageTo;
	
	private String subject;
	
	private String folder;
	
	private String content;
	
	private String accounts;
	
	private String unread;
	
	
	public String getUnread() {
		return unread;
	}

	
	public void setUnread(String unread) {
		this.unread = unread;
	}

	
	public String getId() {
		return id;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	
	public String getFolder() {
		return folder;
	}

	
	public void setFolder(String folder) {
		this.folder = folder;
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
	

	
	public String getMessageBcc() {
		return messageBcc;
	}

	
	public void setMessageBcc(String messageBcc) {
		this.messageBcc = messageBcc;
	}

	
	public String getMessageTo() {
		return messageTo;
	}

	
	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}

	

	public String getAccounts() {
		return accounts;
	}

	
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	
	public boolean validate() {
	      return (subject != null && subject.length() > 0) 
	         && (content != null && content.length() > 0)
	         && (messageFrom != null && messageFrom.length() > 0)
	        ;
	   }


}
