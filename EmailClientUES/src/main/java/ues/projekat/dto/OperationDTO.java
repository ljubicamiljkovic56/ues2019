package ues.projekat.dto;

import java.io.Serializable;

public enum OperationDTO implements Serializable {
	
	MOVE,
	COPY,
	DELETE;

	
	public static OperationDTO valueOf(short a) {
		switch(a) {
		case 1:
			return MOVE;
		case 2:
			return COPY;
		default:
			return DELETE;
			
		}
	}
	public static short toShort(OperationDTO operationDTO) {
		switch(operationDTO) {
		case MOVE:
			return 1;
		case COPY:
			return 2;
		default:
			return 3;
		}
	}

}
