package com.example.sprintOneGrpThree.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Hotel{
	@Id
	private int id;
	private String name;
	
	@OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL)
	@JoinTable(name="hotel_transactions",joinColumns = @JoinColumn(name="hotel_id"),inverseJoinColumns = @JoinColumn(name="transactions_id"))
	private List<Transaction> transactions = new ArrayList<Transaction>();
}
