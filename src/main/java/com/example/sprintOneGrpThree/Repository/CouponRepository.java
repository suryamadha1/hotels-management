package com.example.sprintOneGrpThree.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprintOneGrpThree.Entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	
	

}
