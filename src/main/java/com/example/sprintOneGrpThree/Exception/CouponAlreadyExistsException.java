package com.example.sprintOneGrpThree.Exception;

public class CouponAlreadyExistsException extends Exception {

	public CouponAlreadyExistsException(String msg) {
		super(msg);
	}
	
	public CouponAlreadyExistsException() {
		super();
	}
}
