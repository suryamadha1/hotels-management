package com.example.sprintOneGrpThree.Entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Coupon implements Serializable {
	
	@Id
	private int coupon_id;
	
	private String name;
	private double amount;
	private int percentage;
}
