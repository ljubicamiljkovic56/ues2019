package ues.projekat.dto;

import java.io.Serializable;

import ues.projekat.app.model.Rule;

@SuppressWarnings("serial")
public class RuleDTO implements Serializable {
	
	private Long id;
	private short condition;
    private short operation;

    public RuleDTO() {
    }

    public RuleDTO(Long id, short condition, short operation) {
        this.id = id;
        this.condition = condition;
        this.operation = operation;
    }
    
    public RuleDTO(Rule r) {
        this.id = r.getId();
        this.condition = r.getCondition();
        this.operation = r.getOperation();
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
    
}
