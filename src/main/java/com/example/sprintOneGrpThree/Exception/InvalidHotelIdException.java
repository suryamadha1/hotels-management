package com.example.sprintOneGrpThree.Exception;

public class InvalidHotelIdException extends Exception{
	
	private String msg;
	
	public InvalidHotelIdException(String msg) {
		super();
		this.msg = msg;
	}
	
	public InvalidHotelIdException() {
		super();
	}

}
