package com.example.sprintOneGrpThree.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintOneGrpThree.Entity.Enquiry;
import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Entity.Staff;
import com.example.sprintOneGrpThree.Repository.EnquiryRepository;
import com.example.sprintOneGrpThree.Service.EnquiryService;
import com.example.sprintOneGrpThree.Service.StaffService;

@RestController
@RequestMapping("/enquiry")
public class EnquiryController {

    @Autowired
    EnquiryRepository enquiryRepository;
    
    @Autowired
	private EnquiryService enquiryService;

    @GetMapping
    List<Enquiry> getEnquiry() {
        return enquiryRepository.findAll();
    }

    @PostMapping
    Enquiry createEnquiry(@RequestBody Enquiry enquiry) {
        return enquiryRepository.save(enquiry);
    }
    
    @GetMapping("/id/{id}")
    Enquiry getEnquiryById(@PathVariable int id) {
        Enquiry e = enquiryRepository.findById(id).get();
       return e;
    }
    
    @DeleteMapping("/{id}")  
	public void deleteEnquiry(@PathVariable("id") int id)   
	{  
		enquiryService.delete(id);
	}
    
    @GetMapping("/email/{email}")
	public ResponseEntity<Object> getEnquiryByEmail(@PathVariable("email") String email){
		List<Enquiry> e = enquiryService.getEnquiryByEmail(email);
		if(!e.isEmpty())
			return new ResponseEntity<>(e, HttpStatus.OK);
		else
			return new ResponseEntity<>("Enquiry with given email not found",HttpStatus.NOT_FOUND);
		
	}
}
