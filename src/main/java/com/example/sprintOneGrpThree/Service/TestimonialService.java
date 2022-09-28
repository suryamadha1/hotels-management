package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import com.example.sprintOneGrpThree.Entity.Testimonial;

public interface TestimonialService {

	String addTestimonial(Testimonial t);

	String updateTestimonial(Testimonial t);

	List<Testimonial> getAllTestimonials();

	Optional<Testimonial> getPostById(int id);

	String deletePost(int id);

}
