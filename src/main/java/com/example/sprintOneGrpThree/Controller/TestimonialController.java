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
import com.example.sprintOneGrpThree.Entity.Testimonial;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Exception.StaffScopeViolationException;
import com.example.sprintOneGrpThree.Service.TestimonialService;

@RestController
public class TestimonialController {
	
	
	@Autowired
	private TestimonialService testimonialService;
	
	@PostMapping("/AddMyPost")
	public ResponseEntity<String> addTestimonial(@RequestBody Testimonial t) throws StaffScopeViolationException {
		String response = testimonialService.addTestimonial(t);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PutMapping("/UpdateMyPost")
	public ResponseEntity<String> updateTestimonial(@RequestBody Testimonial t) throws StaffScopeViolationException {
		String response = testimonialService.updateTestimonial(t);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getPostById/{id}")
	public ResponseEntity<Object> getPostById(@PathVariable("id") int id){
		Optional<Testimonial> test = testimonialService.getPostById(id);
		if(test.isPresent())
			return new ResponseEntity<>(test, HttpStatus.OK);
		else
			return new ResponseEntity<>("Testimonial with given id not found",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/getMyAllPosts")
	public ResponseEntity<Object> getAllTestimonials(){
		List<Testimonial> test = testimonialService.getAllTestimonials();
		return new ResponseEntity<>(test,HttpStatus.OK);
		
	}
	
	@GetMapping("/getRatings/{rating}")
	public ResponseEntity<Object> getTestimonialsCondition(@PathVariable int rating) throws CustomerScopeViolationException{
		List<Testimonial> test = testimonialService.getTestimonialsCondition(rating);
		return new ResponseEntity<>(test,HttpStatus.OK);
		
	}
	
	
	
	
	@DeleteMapping("/deleteMyPostWithId/{id}")
	public ResponseEntity<String> deletePost(@PathVariable int id){
		String response = testimonialService.deletePost(id);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	

}
