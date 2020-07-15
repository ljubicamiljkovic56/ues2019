package ues.projekat.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ues.projekat.app.model.Message;
import ues.projekat.app.model.Tag;

@SuppressWarnings("serial")
public class TagDTO implements Serializable {

	
	private Long id;
	private String name;
	private List<Message> messagesOfTags;
	
	public TagDTO() {
		super();
	}
	
	public TagDTO(Long id, String name, List<Message> messagesOfTags) {
		super();
		this.id = id;
		this.name = name;
		this.messagesOfTags = messagesOfTags;
	}
	
	public TagDTO(Tag tag) {
		this.id = tag.getId();
		this.name = tag.getName();
		this.messagesOfTags = tag.getMessagesOfTags();
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
	
}
