package com.example.sprintOneGrpThree.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Hotel {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hotel_id")
	private int id;
	
	private String name;
	
	private String city;
	
	private String address;
	
	private String phoneNumber;
	
	private String email;
	
	@ManyToMany
    @JoinTable(
            name = "staff_enrolled",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    public
    Set<Staff> enrolledStaffs = new HashSet<>();
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enquiry_id", referencedColumnName = "id")
    private Enquiry enquiry;

	
	
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Set<Staff> getEnrolledStaffs() {
		return enrolledStaffs;
	}


	public void setEnrolledStaffs(Set<Staff> enrolledStaffs) {
		this.enrolledStaffs = enrolledStaffs;
	}


	public Enquiry getEnquiry() {
		return enquiry;
	}


	public void setEnquiry(Enquiry enquiry) {
		this.enquiry = enquiry;
	}


	public Hotel(int id, String name, String city, String address, String phoneNumber, String email,
			Set<Staff> enrolledStaffs, Enquiry enquiry) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.enrolledStaffs = enrolledStaffs;
		this.enquiry = enquiry;
	}


	public Hotel() {
		super();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
