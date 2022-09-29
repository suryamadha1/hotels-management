package com.example.sprintOneGrpThree.Entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transaction_id;
	private int customer_id;
	private String booked_rooms_id;
	private int hotel_id;
	@Setter(AccessLevel.NONE)
	private LocalDate transaction_date;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime check_in;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime check_out;
	private int number_of_people;
	private String payment_mode;
	private int amount;
	private int coupon_id;
	
	public void setTransaction_date(LocalDate localDate) {
		this.transaction_date = localDate;
		
	}
	
}
