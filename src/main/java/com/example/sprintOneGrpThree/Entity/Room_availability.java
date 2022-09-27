package com.example.sprintOneGrpThree.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Room_availability {
	@Id
	private int room_id;
	private Room_desc room_type;
	private int floor_no;
	private String room_no;
	private boolean booked_status;

}
