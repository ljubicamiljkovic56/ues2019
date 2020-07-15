package ues.projekat.app.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "rules")
public class Rule implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rule_id")
	private Long id;
	
	@Column(name = "condition")
	private Short condition;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "operation")
	private Short operation;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "destination", referencedColumnName = "folder_id", nullable = true)
	private Folder destination;

	public Rule() {
		super();
	}
	
	
	public Rule(Long id, Short condition, String value, Short operation, Folder destination) {
		super();
		this.id = id;
		this.condition = condition;
		this.value = value;
		this.operation = operation;
		this.destination = destination;
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

	@Override
	public String toString() {
		return "Rule [id=" + id + ", condition=" + condition + ", value=" + value + ", operation=" + operation
				+ ", destination=" + destination + "]";
	}
}