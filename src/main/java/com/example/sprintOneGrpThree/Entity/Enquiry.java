package com.example.sprintOneGrpThree.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Set;

@Entity
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @OneToMany(mappedBy = "enquiry")
    private Set<Hotel> hotels;

    private String email;
    
    private String message;
    
    private String phoneNumber;
    

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Hotel> getHotels() {
        return this.hotels;
    }

}