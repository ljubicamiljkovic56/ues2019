package ues.projekat.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tag_id", unique=true, nullable=false)
	private Integer id;
	  
	@Column(name="image_name", unique=false, nullable=false)
	private String name;
	  
	@ManyToOne
	@JoinColumn(name="message_id", referencedColumnName="message_id", nullable=true)
	private Message messageTag;
	  
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id", nullable=true)
	private User userTag;

	public Tag() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Message getMessage() {
		return messageTag;
	}

	public void setMessage(Message message) {
		this.messageTag = message;
	}

	public User getUserTag() {
		return userTag;
	}

	public void setUserTag(User userTag) {
		this.userTag = userTag;
	}

}
