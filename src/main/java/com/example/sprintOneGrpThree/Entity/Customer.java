package com.example.sprintOneGrpThree.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
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

	

	public Customer(int customerId, String name, String email, String gender, String passcode, String mobile,
			List<Coupon> coupons) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.passcode = passcode;
		this.mobile = mobile;
		this.coupons = coupons;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public Customer() {
		super();
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

}
