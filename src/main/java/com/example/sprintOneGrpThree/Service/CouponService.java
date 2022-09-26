package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import com.example.sprintOneGrpThree.Entity.Coupon;

public interface CouponService {
	
	Coupon saveCoupon(Coupon cpn);
	
	List<Coupon> saveCoupons(List<Coupon> list);
	
	List<Coupon> getCoupons();
	
	Optional<Coupon> getCouponById(int id);
	
	Coupon updateCoupon(Coupon cpn);
	
	List<Coupon> updateCoupons(List<Coupon> list);
	
	boolean deleteCoupon(int id);
	
	boolean deleteAllCoupons();

}
