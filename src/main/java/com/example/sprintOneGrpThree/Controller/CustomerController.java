package com.example.sprintOneGrpThree.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Exception.CouponDoesNotExistException;
import com.example.sprintOneGrpThree.Service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/SignUpCustomer")
	public ResponseEntity<String> signUpCustomer(@RequestBody Customer c) throws CouponDoesNotExistException {
		
		String response = customerService.signUpCustomer(c);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/LoginCustomer")
	public ResponseEntity<String> loginCustomer(@RequestBody Customer c) {
		String response = customerService.loginCustomer(c);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
