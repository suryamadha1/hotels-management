package com.example.sprintOneGrpThree.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Coupon {
	
	@Id
	private int coupon_id;
	
	private String name;
	private double amount;
	private int percentage;

}
