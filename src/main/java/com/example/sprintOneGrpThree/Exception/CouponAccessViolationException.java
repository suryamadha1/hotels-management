package com.example.sprintOneGrpThree.Exception;

public class CouponAccessViolationException extends Exception {
	
	public CouponAccessViolationException(String msg) {
		super(msg);
	}
	
	public CouponAccessViolationException() {
		super();
	}

}