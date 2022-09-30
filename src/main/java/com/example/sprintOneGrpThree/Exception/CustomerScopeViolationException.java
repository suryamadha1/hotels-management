package com.example.sprintOneGrpThree.Exception;

public class CustomerScopeViolationException extends Exception{
	
	private String msg;
	
	public CustomerScopeViolationException(String msg) {
		super();
		this.msg = msg;
	}
	
	public CustomerScopeViolationException() {
		super();
	}
	
	

}
