package com.example.sprintOneGrpThree.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception {
	
	@ExceptionHandler(value = CouponAlreadyExistsException.class)
	public ResponseEntity<String> cpnAlreadyExists(CouponAlreadyExistsException couponAlreadyExistsException){
		return new ResponseEntity<String>("OOPS! Coupon already exists. Please try again.", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = CouponDoesNotExistException.class)
	public ResponseEntity<String> cpnDoesNotExixsts(CouponDoesNotExistException couponDoesNotExistException){
		return new ResponseEntity<String>("Sorry! Coupon does not exists. Please try again.", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = CouponInvalidNameException.class)
	public ResponseEntity<String> cpnInvalidName(CouponInvalidNameException couponInvalidNameException){
		return new ResponseEntity<String>("OOPS! Invalid name for coupon. Please try again.", HttpStatus.BAD_REQUEST);
	}

}
