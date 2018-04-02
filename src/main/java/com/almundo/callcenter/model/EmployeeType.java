package com.almundo.callcenter.model;

public enum EmployeeType {
	
	OPERATOR (1), SUPERVISOR (2), MANAGER (3);
	
	private int priority;

	private EmployeeType(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return priority;
	}
}
