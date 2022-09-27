package com.example.sprintOneGrpThree.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Room_booking_history {
	@Id
	private int booking_id;
	private int room_id;
	private int transaction_id;

}
