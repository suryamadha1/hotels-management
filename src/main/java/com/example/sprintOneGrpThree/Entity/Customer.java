package com.example.sprintOneGrpThree.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int id;
	
	private String name;
	
	@OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL)
	@JoinTable(name="customer_transactions",joinColumns = @JoinColumn(name="customer_id"),inverseJoinColumns = @JoinColumn(name="transactions_id"))
	private List<Transaction> transactions = new ArrayList<Transaction>();


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public void addToTransactions(Transaction tr) {
		this.transactions.add(tr);
	}
	
	public Customer() {
		
	}

	public Customer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

}
