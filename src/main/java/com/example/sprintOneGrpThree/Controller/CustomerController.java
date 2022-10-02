package com.example.sprintOneGrpThree.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Transaction;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Exception.InvalidOperationException;
import com.example.sprintOneGrpThree.Exception.StaffScopeViolationException;
import com.example.sprintOneGrpThree.Service.CustomerService;

@RestController
public class CustomerController {

	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/SignUpCustomer")
	public ResponseEntity<String> signUpCustomer(@RequestBody Customer c) {
		String response = customerService.signUpCustomer(c);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/LoginCustomer")
	public ResponseEntity<String> loginCustomer(@RequestBody Customer c) {
		String response = customerService.loginCustomer(c);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PutMapping("/UpdateMyDetails")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer c) {
		String response = customerService.updateCustomer(c);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getCustomerByEmail/{email}")
	public ResponseEntity<Object> getCustomerByEmail(@PathVariable("email") String email){
		List<Customer> cust = customerService.getCustomerByEmail(email);
		if(!cust.isEmpty())
			return new ResponseEntity<>(cust, HttpStatus.OK);
		else
			return new ResponseEntity<>("Customer with given email id not found",HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<Object> getAllCustomers() throws CustomerScopeViolationException{
		List<Customer> cust = customerService.getAllCustomers();
			return new ResponseEntity<>(cust,HttpStatus.OK);
		
	}
	
	@GetMapping("/getMyAllTransactions")
	public ResponseEntity<Object> getTransactions() throws StaffScopeViolationException, InvalidOperationException{
		List<Transaction> transactions = customerService.getTransactions();
			return new ResponseEntity<>(transactions,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteCustomerByEmail/{email}")
	public ResponseEntity<String> deleteCustomer(@PathVariable String email) throws InvalidOperationException{
		String response = customerService.deleteCustomer(email);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	
	
}
