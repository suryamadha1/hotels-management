package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.sprintOneGrpThree.Entity.Coupon;
import com.example.sprintOneGrpThree.Repository.CouponRepository;

public class CouponServiceImplementation implements CouponService {
	
	@Autowired
	private CouponRepository couponRepo;

	@Override
	public Coupon saveCoupon(Coupon cpn) {
		Coupon savedCoupon = couponRepo.save(cpn);
		return savedCoupon;
	}

	@Override
	public List<Coupon> saveCoupons(List<Coupon> list) {
		List<Coupon> savedCoupons = couponRepo.saveAll(list);
		return savedCoupons;
	}

	@Override
	public List<Coupon> getCoupons() {
		List<Coupon> coupons = couponRepo.findAll();
		return coupons;
	}

	@Override
	public Optional<Coupon> getCouponById(int id) {
		Optional<Coupon> coupon = couponRepo.findById(id);
		return coupon;
	}

	@Override
	public Coupon updateCoupon(Coupon cpn) {
		Coupon coupon = couponRepo.save(cpn);
		return coupon;
	}

	@Override
	public List<Coupon> updateCoupons(List<Coupon> list) {
		List<Coupon> coupons = couponRepo.saveAll(list);
		return coupons;
	}

	@Override
	public boolean deleteCoupon(int id) {
		try {
			couponRepo.deleteById(id);
			return true;
		}catch(Exception e) {
			e.getMessage();
			return false;
		}
	}

	@Override
	public boolean deleteAllCoupons() {
		try {
			couponRepo.deleteAll();
			return true;
		}catch(Exception e) {
			e.getMessage();
			return false;
		}
	}

}
