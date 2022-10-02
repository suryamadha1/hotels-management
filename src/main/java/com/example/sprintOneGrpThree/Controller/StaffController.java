package com.example.sprintOneGrpThree.Controller;

import java.util.List;

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
import com.example.sprintOneGrpThree.Entity.Staff;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Service.StaffService;

@RestController
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	@PostMapping("/SignUpStaff")
	public ResponseEntity<String> signUpStaff(@RequestBody Staff s) {
		String response = staffService.signUpStaff(s);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/LoginStaff")
	public ResponseEntity<String> loginStaff(@RequestBody Staff c) {
		String response = staffService.loginStaff(c);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PutMapping("/UpdateStaffDetails")
	public ResponseEntity<String> updateStaff(@RequestBody Staff s) {
		String response = staffService.updateStaff(s);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getStaffByEmail/{email}")
	public ResponseEntity<Object> getStaffByEmail(@PathVariable("email") String email) throws CustomerScopeViolationException{
		List<Staff> s = staffService.getStaffByEmail(email);
		if(!s.isEmpty())
			return new ResponseEntity<>(s, HttpStatus.OK);
		else
			return new ResponseEntity<>("Staff with given email id not found",HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/getAllStaff")
	public ResponseEntity<Object> getAllStaff() throws CustomerScopeViolationException{
		List<Staff> staff = staffService.getAllStaff();
			return new ResponseEntity<>(staff,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteStaffByEmail/{email}")
	public ResponseEntity<String> deleteStaff(@PathVariable String email){
		String response = staffService.deleteStaff(email);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
