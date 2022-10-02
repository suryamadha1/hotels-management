package com.example.sprintOneGrpThree.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Session {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sessionId;
	
	private String email;
	private String type;
	private int id;
	public Session(int sessionId, String email, String type, int id) {
		super();
		this.sessionId = sessionId;
		this.email = email;
		this.type = type;
		this.id = id;
	}
	public Session(String email, String type, int id) {
		super();
		this.email = email;
		this.type = type;
		this.id = id;
	}
	public Session() {
		super();
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
