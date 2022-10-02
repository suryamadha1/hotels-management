package com.example.sprintOneGrpThree.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Room_desc implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int room_desc_id;
//	@OneToMany(fetch = FetchType.LAZY,mappedBy = "room_type",cascade = CascadeType.ALL)
	private String type;
	private int room_price,no_of_beds;
	private boolean balcony;
	private String description;
}
