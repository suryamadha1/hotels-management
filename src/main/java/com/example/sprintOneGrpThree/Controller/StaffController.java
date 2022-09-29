package com.example.sprintOneGrpThree.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintOneGrpThree.Entity.Staff;
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

}
