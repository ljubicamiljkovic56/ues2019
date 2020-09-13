package ues.projekat.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@SuppressWarnings("serial")
@Entity                 
@Table(name="rules")
public class Rule implements Serializable{

	@Id                                 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rule_id", unique=true, nullable=false) 
	private Long id;
	  	
	@Column(name="rule_condition", unique=false, nullable=false)
	private short condition;
	  
	@Column(name="rule_value", unique=false, nullable=true, length=100)
	private String value;
	  
	@Column(name="rule_operation", unique=false, nullable=false)
	private short operation;
	  
	@ManyToOne
	@JoinColumn(name="destination", referencedColumnName="folder_id", nullable=true)
	private Folder destination;

	public Rule() {
		super();
	}
	
	public Rule(short condition, String value, short operation, Folder folder) {
		super();
		this.condition = condition;
		this.value = value;
		this.operation = operation;
		this.destination = folder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public short getCondition() {
		return condition;
	}

	public void setCondition(short condition) {
		this.condition = condition;
	}

	public short getOperation() {
		return operation;
	}

	public void setOperation(short operation) {
		this.operation = operation;
	}

	public Folder getDestination() {
		return destination;
	}

	public void setDestination(Folder destination) {
		this.destination = destination;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Rule [id=" + id + ", condition=" + condition + ", value=" + ", operation=" + operation + "]";
	}
}