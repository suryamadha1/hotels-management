package com.example.sprintOneGrpThree.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Testimonial {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int testimonialId;
	
	private String dateTime;
	
	private String content;
	
	private int rating;
	
	
	@ManyToOne
	private Customer customer;
	
	
	
	public int getTestimonialId() {
		return testimonialId;
	}



	public void setTestimonialId(int testimonialId) {
		this.testimonialId = testimonialId;
	}



	public String getDateTime() {
		return dateTime;
	}



	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public int getRating() {
		return rating;
	}



	public void setRating(int rating) {
		this.rating = rating;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Testimonial() {
		super();
	}



	public Testimonial(int testimonialId, String dateTime, String content, int rating, Customer customer) {
		super();
		this.testimonialId = testimonialId;
		this.dateTime = dateTime;
		this.content = content;
		this.rating = rating;
		this.customer = customer;
	}



	public Testimonial(String dateTime, String content, int rating, Customer customer) {
		super();
		this.dateTime = dateTime;
		this.content = content;
		this.rating = rating;
		this.customer = customer;
	}


	
	

}
