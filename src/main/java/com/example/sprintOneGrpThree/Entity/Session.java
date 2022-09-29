package com.example.sprintOneGrpThree.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Session {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sessionId;
	
	private String email;
	private String type;
	private int id;
	
	public Session(String email, String type, int id) {
		super();
		this.email = email;
		this.type = type;
		this.id = id;
	}
	
	public Session() {
		super();
	}
	
}
