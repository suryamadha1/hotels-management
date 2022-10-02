package com.example.sprintOneGrpThree.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Staff {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int staffId;
	
	private String name;
	
	private String type;
	
	private String email;
	
	private String passcode;
	
	private String gender;
	
	private String mobile;
	
	@JsonIgnore
    @ManyToMany(mappedBy = "enrolledStaffs",cascade = CascadeType.ALL)
    public Set<Hotel> hotels = new HashSet<>();
	

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Staff() {
		super();
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Set<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(Set<Hotel> hotels) {
		this.hotels = hotels;
	}

	public Staff(int staffId, String name, String type, String email, String passcode, String gender, String mobile,
			Set<Hotel> hotels) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.type = type;
		this.email = email;
		this.passcode = passcode;
		this.gender = gender;
		this.mobile = mobile;
		this.hotels = hotels;
	}

	
	
	

}
