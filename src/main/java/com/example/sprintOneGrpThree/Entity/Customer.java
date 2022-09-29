package com.example.sprintOneGrpThree.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;
	
	private String name;
	
	private String email;
	
	private String gender;
	
	private String passcode;
	
	private String mobile;
	
	@ManyToMany
	@JoinTable(
			name = "customer_coupon",
			joinColumns = @JoinColumn(name ="customer_id"),
			inverseJoinColumns = @JoinColumn(name ="coupon_id")
			)
	private List<Coupon> coupons = new ArrayList<>();


}
