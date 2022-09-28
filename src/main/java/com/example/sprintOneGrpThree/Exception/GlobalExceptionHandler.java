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
	
	

}
