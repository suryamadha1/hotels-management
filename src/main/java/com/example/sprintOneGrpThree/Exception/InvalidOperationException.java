package com.example.sprintOneGrpThree.Exception;

public class InvalidOperationException extends Exception{
	
	private String msg;
	
	public InvalidOperationException(String msg) {
		super();
		this.msg = msg;
	}
	
	public InvalidOperationException() {
		super();
	}
	
	

}
