package com.example.sprintOneGrpThree.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=CustomerScopeViolationException.class)
	public ResponseEntity<String> customerScope(CustomerScopeViolationException custmerScopeViolationException){
		return new ResponseEntity<String>("Operation not allowed to customer.",HttpStatus.CONFLICT);
	}
	
	
	@ExceptionHandler(value=StaffScopeViolationException.class)
	public ResponseEntity<String> staffScope(StaffScopeViolationException staffScopeViolationException){
		return new ResponseEntity<String>("Operation not allowed to staff.",HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value=InvalidOperationException.class)
	public ResponseEntity<String> InvalidOperation(InvalidOperationException invalidOperationException){
		return new ResponseEntity<String>("Cannot perform the operation. Please login.",HttpStatus.CONFLICT);
	}
	
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
	
	@ExceptionHandler(value = CouponInvalidAmountException.class)
	public ResponseEntity<String> cpnInvalidAmount(CouponInvalidAmountException couponInvalidAmountException){
		return new ResponseEntity<String>("OOPS! Invalid amount. Please check and try again.", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = CouponInvalidPercentageException.class)
	public ResponseEntity<String> cpnInvalidPercentage(CouponInvalidPercentageException couponInvalidPercentageException){
		return new ResponseEntity<String>("OOPS! Invalid percentage. Please check and try again.", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = CouponAccessViolationException.class)
	public ResponseEntity<String> couponAccessViolation(CouponAccessViolationException couponAccessViolationException){
		return new ResponseEntity<String>("ACCESS DENIED!!!", HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = InvalidHotelIdException.class)
	public ResponseEntity<String> invalidHotelId(InvalidHotelIdException invalidHotelIdException){
		return new ResponseEntity<String>("Invalid hotel id given", HttpStatus.FORBIDDEN);
	}
	
	

}
