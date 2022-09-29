package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Coupon;
import com.example.sprintOneGrpThree.Exception.CouponAccessViolationException;
import com.example.sprintOneGrpThree.Exception.CouponAlreadyExistsException;
import com.example.sprintOneGrpThree.Exception.CouponDoesNotExistException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidAmountException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidNameException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidPercentageException;

public interface CouponService {
	
	Coupon saveCoupon(Coupon cpn) throws CouponAlreadyExistsException, CouponInvalidNameException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponAccessViolationException;
	
	List<Coupon> saveCoupons(List<Coupon> list) throws CouponAlreadyExistsException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponInvalidNameException, CouponAccessViolationException;
	
	List<Coupon> getCoupons();
	
	Optional<Coupon> getCouponById(int id) throws CouponDoesNotExistException;
	
	boolean updateCoupon(Coupon cpn) throws CouponDoesNotExistException, CouponInvalidNameException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponAccessViolationException;
	
	boolean updateCoupons(List<Coupon> list) throws CouponAlreadyExistsException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponInvalidNameException, CouponDoesNotExistException, CouponAccessViolationException;
	
	boolean deleteCoupon(int id) throws CouponDoesNotExistException, CouponAccessViolationException;
	
	boolean deleteAllCoupons() throws CouponAccessViolationException;
	
	List<Coupon> getCouponByName(String name) throws CouponDoesNotExistException;
	
//	boolean existsByName(String name);
	
	List<Coupon> getCouponByAmount(double amount) throws CouponDoesNotExistException;
	
	List<Coupon> getCouponByPercentage(int percentage) throws CouponDoesNotExistException;

}
