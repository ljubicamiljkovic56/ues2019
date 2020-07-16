package ues.projekat.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "tags")
public class Tag implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private Long id; 
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_tags")
	private List<Message> messagesOfTags;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id", nullable=true)
	private User userTag;

	
	public Tag() {
		super();
	}
	

	public Tag(Long id, String name, List<Message> messagesOfTags, User userTag) {
		super();
		this.id = id;
		this.name = name;
		this.messagesOfTags = messagesOfTags;
		this.userTag = userTag;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Message> getMessagesOfTags() {
		return messagesOfTags;
	}

	public void setMessagesOfTags(ArrayList<Message> messagesOfTags) {
		this.messagesOfTags = messagesOfTags;
	}
	
	

	public User getUserTag() {
		return userTag;
	}


	public void setUserTag(User userTag) {
		this.userTag = userTag;
	}


	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", messagesOfTags=" + messagesOfTags + ", userTag=" + userTag + "]";
	}
	
}