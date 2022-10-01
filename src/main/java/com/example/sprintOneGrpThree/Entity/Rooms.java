package com.example.sprintOneGrpThree.Entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Rooms implements Serializable{
	@Id
	private int id;
	

	private String room_no;
	private int floor_no;
	private boolean booked_status;
	
	@JoinColumn(name="room_type",referencedColumnName="type")
	@ManyToOne(optional = true,fetch = FetchType.LAZY)
	private Room_desc room_type;
	@JoinColumn(name="fk_hotel_id",referencedColumnName="hotel_id")
	@ManyToOne(optional = true,fetch = FetchType.LAZY)
	private Hotel fk_hotel_id;

	@Override
	public String toString() {
		return "[id=" + id + ", room_no=" + room_no + ", floor_no=" + floor_no + ", hotel_name=" + fk_hotel_id.getName() +"]";
	}

}
