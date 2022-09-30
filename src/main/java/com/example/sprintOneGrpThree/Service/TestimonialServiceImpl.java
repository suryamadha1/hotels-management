package com.example.sprintOneGrpThree.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Testimonial;
import com.example.sprintOneGrpThree.Entity.Transaction;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Exception.StaffScopeViolationException;
import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Session;
import com.example.sprintOneGrpThree.Repository.CustomerRepository;
import com.example.sprintOneGrpThree.Repository.SessionRepository;
import com.example.sprintOneGrpThree.Repository.TestimonialRepository;
import com.example.sprintOneGrpThree.Repository.TransactionRepository;

@Service
public class TestimonialServiceImpl implements TestimonialService{

	
	@Autowired
	private TestimonialRepository testimonialRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public String addTestimonial(Testimonial t) throws StaffScopeViolationException {
		if(sessionRepository.count()==0) {
			return "Operation cannot be performed. Please login.";
		}
		Session obj = sessionRepository.findAll().stream().findFirst().get();
		if(obj.getType().equals("staff")) {
			throw new StaffScopeViolationException();
		}
		List<Transaction> list = transactionRepository.findAll().stream().filter(n->n.getCustomer().getEmail().equals(
				obj.getEmail()) && n.getHotel().getId()==t.getHotelId()).collect(Collectors.toList());
		
		if(list.isEmpty()) {
			return "No transaction found with the given hotel";
		}
		LocalDateTime myDateObj = LocalDateTime.now();
	    t.setDateTime(myDateObj);
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
	public String updateTestimonial(Testimonial t) throws StaffScopeViolationException {
		if(sessionRepository.count()==0) {
			return "Operation cannot be performed. Please login.";
		}
		Session obj = sessionRepository.findAll().stream().findFirst().get();
		if(obj.getType().equals("staff")) {
			throw new StaffScopeViolationException();
		}
		List<Testimonial> list = testimonialRepository.findAll().stream().filter(n->n.getCustomer().getEmail().equals(
				obj.getEmail()) && n.getHotelId()==t.getHotelId()).collect(Collectors.toList());
		
		if(list.isEmpty()) {
			return "No transaction found with the given hotel";
		}
		Customer c = customerRepository.findById(obj.getId()).get();
	
		testimonialRepository.save(new Testimonial(list.get(0).getTestimonialId(),
				list.get(0).getDateTime(),
				t.getContent(),
				t.getRating(),
				c,
				t.getHotelId()));
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

	@Override
	public List<Testimonial> getTestimonialsCondition(int rating) throws CustomerScopeViolationException {
		Session obj = sessionRepository.findAll().stream().findFirst().get();
		if(!obj.getType().equals("staff")) {
			throw new CustomerScopeViolationException();
		}
		List<Testimonial> testList = testimonialRepository.findByRatingCondition(rating);
		return testList;
	}

	
	
}
