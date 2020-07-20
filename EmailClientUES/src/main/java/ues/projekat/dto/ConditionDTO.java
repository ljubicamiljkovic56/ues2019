package ues.projekat.dto;
import java.io.Serializable;

public enum ConditionDTO implements Serializable {
	
	TO,
    FROM,
    CC,
    SUBJECT;
	
	public static ConditionDTO valueOf(short a) {
		switch(a) {
		case 1:
			return TO;
		case 2:
			return FROM;
		case 3:
			return CC;
		default:
			return SUBJECT;
			
		}
	}
	public static short toShort(ConditionDTO conditionDTO) {
		switch(conditionDTO) {
		case TO:
			return 1;
		case FROM:
			return 2;
		case CC:
			return 3;
		default:
			return 4;
		}
	}

}
