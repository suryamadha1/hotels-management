package com.example.sprintOneGrpThree.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "coupons")
public class Coupon implements Serializable {
	
	@Id
	@Column(name = "coupon_id")
	private int coupon_id;
	
	private String name;
	private double amount;
	private int percentage;
	
	public Coupon(int coupon_id, String name, double amount, int percentage) {
		super();
		this.coupon_id = coupon_id;
		this.name = name;
		this.amount = amount;
		this.percentage = percentage;
	}

}
