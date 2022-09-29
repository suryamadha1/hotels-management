package com.example.sprintOneGrpThree.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sprintOneGrpThree.Entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer>{

}
