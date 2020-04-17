package ues.projekat.dto;

import java.io.Serializable;

import ues.projekat.entity.Folder;
import ues.projekat.entity.Rule;

@SuppressWarnings("serial")
public class RuleDTO implements Serializable {
	
	private Long id;
	private Short condition;
	private String value;
	private Short operation;
	private Folder destination;
	
	public RuleDTO() {
		super();
	}
	
	public RuleDTO(Long id, Short condition, String value, Short operation, Folder destination) {
		super();
		this.id = id;
		this.condition = condition;
		this.value = value;
		this.operation = operation;
		this.destination = destination;
	}
	
	public RuleDTO(Rule rule) {
		this.id = rule.getId();
		this.condition = rule.getCondition();
		this.value = rule.getValue();
		this.operation = rule.getOperation();
		this.destination = rule.getDestination();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Short getCondition() {
		return condition;
	}
	public void setCondition(Short condition) {
		this.condition = condition;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Short getOperation() {
		return operation;
	}
	public void setOperation(Short operation) {
		this.operation = operation;
	}
	public Folder getDestination() {
		return destination;
	}
	public void setDestination(Folder destination) {
		this.destination = destination;
	}
	
	

}
