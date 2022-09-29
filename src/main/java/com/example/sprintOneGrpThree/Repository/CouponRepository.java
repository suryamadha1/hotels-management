package com.example.sprintOneGrpThree.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sprintOneGrpThree.Entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	@Query("SELECT c FROM Coupon c WHERE c.name = :name")
	public List<Coupon> findCouponByName(@Param("name") String name);
	
	public boolean existsByName(String name);
	
	public boolean existsByAmount(double amount);
	
	public boolean existsByPercentage(int percentage);
	
	@Query("SELECT c FROM Coupon c WHERE c.amount = :amount")
	public List<Coupon> findCouponByAmount(@Param("amount") double amount);
	
	@Query("SELECT c FROM Coupon c WHERE c.percentage = :percent")
	public List<Coupon> findCouponByPercentage(@Param("percent") int percentage);
	
}
