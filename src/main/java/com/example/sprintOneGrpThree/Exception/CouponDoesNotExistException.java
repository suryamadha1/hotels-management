package com.example.sprintOneGrpThree.Exception;

public class CouponDoesNotExistException extends Exception {

	public CouponDoesNotExistException(String msg) {
		super(msg);
	}
	
	public CouponDoesNotExistException(){
		super();
	}
	
}
