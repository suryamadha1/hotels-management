package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Coupon;
import com.example.sprintOneGrpThree.Exception.CouponAlreadyExistsException;
import com.example.sprintOneGrpThree.Exception.CouponDoesNotExistException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidAmountException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidNameException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidPercentageException;

public interface CouponService {
	
	Coupon saveCoupon(Coupon cpn) throws CouponAlreadyExistsException, CouponInvalidNameException, CouponInvalidAmountException, CouponInvalidPercentageException;
	
	List<Coupon> saveCoupons(List<Coupon> list) throws CouponAlreadyExistsException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponInvalidNameException;
	
	List<Coupon> getCoupons();
	
	Optional<Coupon> getCouponById(int id) throws CouponDoesNotExistException;
	
	Coupon updateCoupon(Coupon cpn) throws CouponDoesNotExistException, CouponInvalidNameException, CouponInvalidAmountException, CouponInvalidPercentageException;
	
	List<Coupon> updateCoupons(List<Coupon> list) throws CouponAlreadyExistsException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponInvalidNameException;
	
	boolean deleteCoupon(int id) throws CouponDoesNotExistException;
	
	boolean deleteAllCoupons();

}
