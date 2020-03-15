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
@Table(name = "rules")
public class Rule implements Serializable {
	
	@Id                                 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rule_id", unique=true, nullable=false) 
	private Integer id;
	  	
	@Column(name="rule_condition", unique=false, nullable=false)
	private ConditionDTO condition;
	  
	@Column(name="rule_value", unique=false, nullable=true, length=100)
	private String value;
	  
	@Column(name="rule_operation", unique=false, nullable=false)
	private OperationDTO operation;
	  
	@ManyToOne
	@JoinColumn(name="destination_folder_id", referencedColumnName="folder_id", nullable=true)
	private Folder destination;

	public Rule() {
		super();
	}
	
	public Rule(ConditionDTO condition, String value, OperationDTO operation, Folder folder) {
		super();
		this.condition = condition;
		this.value = value;
		this.operation = operation;
		this.destination = folder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ConditionDTO getCondition() {
		return condition;
	}

	public void setCondition(ConditionDTO condition) {
		this.condition = condition;
	}

	public OperationDTO getOperation() {
		return operation;
	}

	public void setOperation(OperationDTO operation) {
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
		return "Rule [id=" + id + ", condition=" + condition + ", value=" + ", operation=" + operation + "]";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	

}
