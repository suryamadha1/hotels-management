package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Testimonial;
import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Session;
import com.example.sprintOneGrpThree.Repository.CustomerRepository;
import com.example.sprintOneGrpThree.Repository.SessionRepository;
import com.example.sprintOneGrpThree.Repository.TestimonialRepository;

@Service
public class TestimonialServiceImpl implements TestimonialService{

	
	@Autowired
	private TestimonialRepository testimonialRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public String addTestimonial(Testimonial t) {
		Session obj = sessionRepository.findAll().stream().findFirst().get();
		List<Customer> custList = customerRepository.findAll();
		for(Customer c: custList) {
			if(c.getEmail().equals(obj.getEmail())) {
				t.setCustomer(c);
			}
		}
		testimonialRepository.save(t);
		return "Testimonial added";
		// TODO Auto-generated method stub
	}

	@Override
	public String updateTestimonial(Testimonial t) {
		if(sessionRepository.count()==0) {
			return "Update operation cannot be performed";
		}
		Session obj = sessionRepository.findAll().stream().findFirst().get();
	
		List<Testimonial> list = testimonialRepository.findAll().stream().filter(n->n.getCustomer().getEmail().equals(
				obj.getEmail())).collect(Collectors.toList());
		
		Customer c = customerRepository.findById(obj.getId()).get();
	
		testimonialRepository.save(new Testimonial(list.get(0).getTestimonialId(),
				t.getDateTime(),
				t.getContent(),
				t.getRating(),
				c));
		return "Your post is updated";
		}

	@Override
	public List<Testimonial> getAllTestimonials() {
		List<Testimonial> testList = testimonialRepository.findAll();
		return testList;
	}

	@Override
	public Optional<Testimonial> getPostById(int id) {
			Optional<Testimonial> test = testimonialRepository.findById(id);
		return test;
	}

	@Override
	public String deletePost(int id) {
		if(testimonialRepository.existsById(id)) {
			testimonialRepository.deleteById(id);
			return "Testimonial is deleted";
		}
		else {
			return "Testimonial with given id not found";
		}
		
	}

	
	
}
