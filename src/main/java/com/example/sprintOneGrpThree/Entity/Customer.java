package com.example.sprintOneGrpThree.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Customer {
	
	@Id
	private int cust_id;
	
	private String email_address;
	private String password;

}
