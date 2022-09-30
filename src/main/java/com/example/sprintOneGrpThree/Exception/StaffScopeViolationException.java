package com.example.sprintOneGrpThree.Exception;

public class StaffScopeViolationException extends Exception {
	
private String msg;
	
	public StaffScopeViolationException(String msg) {
		super();
		this.msg = msg;
	}
	
	public StaffScopeViolationException() {
		super();
	}

}
