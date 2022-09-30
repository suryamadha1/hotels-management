package com.example.sprintOneGrpThree.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String booked_rooms_id;
	
	private LocalDate transaction_date;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime check_in;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime check_out;
	private int number_of_people;
	private String payment_mode;
	private int amount;
	private int coupon_id;
	private double discount_amount;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Hotel hotel;

	public Transaction(int id, String booked_rooms_id, LocalDate transaction_date, LocalDateTime check_in,
			LocalDateTime check_out, int number_of_people, String payment_mode, int amount, int coupon_id,
			Customer customer, Hotel hotel) {
		super();
		this.id = id;
		this.booked_rooms_id = booked_rooms_id;
		this.transaction_date = transaction_date;
		this.check_in = check_in;
		this.check_out = check_out;
		this.number_of_people = number_of_people;
		this.payment_mode = payment_mode;
		this.amount = amount;
		this.coupon_id = coupon_id;
		this.customer = customer;
		this.hotel = hotel;
	}


	

}
