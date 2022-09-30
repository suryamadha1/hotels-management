package com.example.sprintOneGrpThree.Entity;

import java.time.LocalDateTime;

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
	
	private LocalDateTime dateTime;
	
	private String content;
	
	private int rating;
	
	private int hotelId;
	
	
	@ManyToOne
	private Customer customer;
	
	
	
	public int getHotelId() {
		return hotelId;
	}



	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}



	public int getTestimonialId() {
		return testimonialId;
	}



	public void setTestimonialId(int testimonialId) {
		this.testimonialId = testimonialId;
	}



	public LocalDateTime getDateTime() {
		return dateTime;
	}



	public void setDateTime(LocalDateTime dateTime) {
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



	



	public Testimonial(int testimonialId, LocalDateTime dateTime, String content, int rating, Customer customer, int hotelId) {
		super();
		this.testimonialId = testimonialId;
		this.dateTime = dateTime;
		this.content = content;
		this.rating = rating;
		this.hotelId = hotelId;
		this.customer = customer;
	}
	
	public Testimonial(int testimonialId,  String content, int rating, Customer customer, int hotelId) {
		super();
		this.testimonialId = testimonialId;
		this.content = content;
		this.rating = rating;
		this.hotelId = hotelId;
		this.customer = customer;
	}



	public Testimonial(LocalDateTime dateTime, String content, int rating, Customer customer) {
		super();
		this.dateTime = dateTime;
		this.content = content;
		this.rating = rating;
		this.customer = customer;
	}


	
	

}
