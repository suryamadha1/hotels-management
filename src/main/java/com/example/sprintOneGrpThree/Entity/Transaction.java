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
	private float discount_amount;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Hotel hotel;



	

}
