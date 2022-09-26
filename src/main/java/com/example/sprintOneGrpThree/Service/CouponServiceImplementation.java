package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Coupon;
import com.example.sprintOneGrpThree.Exception.CouponAlreadyExistsException;
import com.example.sprintOneGrpThree.Exception.CouponDoesNotExistException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidAmountException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidNameException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidPercentageException;
import com.example.sprintOneGrpThree.Repository.CouponRepository;

@Service
public class CouponServiceImplementation implements CouponService {
	
	@Autowired
	private CouponRepository couponRepo;

	@Override
	public Coupon saveCoupon(Coupon cpn) throws CouponAlreadyExistsException, CouponInvalidNameException, CouponInvalidAmountException, CouponInvalidPercentageException {
		
		boolean flagAmount = false;
		boolean flagPercentage = false;
		
		if(couponRepo.existsById(cpn.getCoupon_id()))
			throw new CouponAlreadyExistsException();
		
		if(!cpn.getName().matches("[a-zA-Z0-9]{1,10}"))
			throw new CouponInvalidNameException();
		
		if(cpn.getAmount() < 0 || cpn.getAmount() > 1000) 
			throw new CouponInvalidAmountException();
		
		if(cpn.getPercentage() < 0 || cpn.getPercentage() > 40)
			throw new CouponInvalidPercentageException();
		
		Coupon savedCoupon = couponRepo.save(cpn);
		return savedCoupon;
	}

	@Override
	public List<Coupon> saveCoupons(List<Coupon> list) throws CouponAlreadyExistsException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponInvalidNameException {
		for(Coupon c : list) {
			if(couponRepo.existsById(c.getCoupon_id()))
				throw new CouponAlreadyExistsException();
			
			if(c.getAmount() < 0 || c.getAmount() > 1000)
				throw new CouponInvalidAmountException();
			
			if(c.getPercentage() < 0 || c.getPercentage() > 40)
				throw new CouponInvalidPercentageException();
			
			if(!c.getName().matches("[a-zA-Z0-9]{1,10}"))
				throw new CouponInvalidNameException();
			
		}
		List<Coupon> savedCoupons = couponRepo.saveAll(list);
		return savedCoupons;
	}

	@Override
	public List<Coupon> getCoupons() {
		List<Coupon> coupons = couponRepo.findAll();
		return coupons;
	}

	@Override
	public Optional<Coupon> getCouponById(int id) throws CouponDoesNotExistException {
		if(!couponRepo.existsById(id))
			throw new CouponDoesNotExistException();
			
		Optional<Coupon> coupon = couponRepo.findById(id);
		return coupon;
	}

	@Override
	public Coupon updateCoupon(Coupon cpn) throws CouponDoesNotExistException, CouponInvalidNameException, CouponInvalidAmountException, CouponInvalidPercentageException {
		if(!couponRepo.existsById(cpn.getCoupon_id()))
			throw new CouponDoesNotExistException();
		
		if(!cpn.getName().matches("[a-zA-Z0-9]{1,10}"))
			throw new CouponInvalidNameException();
		
		if(cpn.getAmount() < 0 || cpn.getAmount() > 1000)
			throw new CouponInvalidAmountException();
		
		if(cpn.getPercentage() < 0 || cpn.getPercentage() > 40)
			throw new CouponInvalidPercentageException();
		
		Coupon coupon = couponRepo.save(cpn);
		return coupon;
	}

	@Override
	public List<Coupon> updateCoupons(List<Coupon> list) throws CouponAlreadyExistsException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponInvalidNameException {
		for(Coupon c : list) {
			if(couponRepo.existsById(c.getCoupon_id()))
				throw new CouponAlreadyExistsException();
			
			if(c.getAmount() < 0 || c.getAmount() > 1000)
				throw new CouponInvalidAmountException();
			
			if(c.getPercentage() < 0 || c.getPercentage() > 40)
				throw new CouponInvalidPercentageException();
			
			if(!c.getName().matches("[a-zA-Z0-9]{1,10}"))
				throw new CouponInvalidNameException();
		}
		
		List<Coupon> coupons = couponRepo.saveAll(list);
		return coupons;
	}

	@Override
	public boolean deleteCoupon(int id) throws CouponDoesNotExistException {
		if(!couponRepo.existsById(id))
			throw new CouponDoesNotExistException();
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
