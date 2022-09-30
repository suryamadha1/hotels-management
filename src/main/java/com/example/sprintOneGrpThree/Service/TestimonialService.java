package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import com.example.sprintOneGrpThree.Entity.Testimonial;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Exception.StaffScopeViolationException;

public interface TestimonialService {

	String addTestimonial(Testimonial t) throws StaffScopeViolationException;

	String updateTestimonial(Testimonial t) throws StaffScopeViolationException;

	List<Testimonial> getAllTestimonials();

	Optional<Testimonial> getPostById(int id);

	String deletePost(int id);

	List<Testimonial> getTestimonialsCondition(int rating) throws CustomerScopeViolationException;

}
