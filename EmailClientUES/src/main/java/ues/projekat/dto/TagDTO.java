package ues.projekat.dto;

import java.io.Serializable;
import java.util.ArrayList;

import ues.projekat.entity.Message;
import ues.projekat.entity.Tag;

@SuppressWarnings("serial")
public class TagDTO implements Serializable {

	
	private Long id;
	private String name;
	private ArrayList<Message> messagesOfTags;
	
	public TagDTO() {
		super();
	}
	
	public TagDTO(Long id, String name, ArrayList<Message> messagesOfTags) {
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
	public ArrayList<Message> getMessagesOfTags() {
		return messagesOfTags;
	}
	public void setMessagesOfTags(ArrayList<Message> messagesOfTags) {
		this.messagesOfTags = messagesOfTags;
	}
	
	
}
