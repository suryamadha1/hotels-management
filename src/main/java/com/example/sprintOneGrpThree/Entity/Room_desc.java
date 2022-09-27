package com.example.sprintOneGrpThree.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Room_desc {
	@Id
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="room_id")
	private int type;
	private int room_price,no_of_beds,no_of_rooms,no_of_bathrooms,total_rooms;
	private boolean kitchen,balcony;
	private String description;
}
