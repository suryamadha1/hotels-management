package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Coupon;
import com.example.sprintOneGrpThree.Exception.CouponAccessViolationException;
import com.example.sprintOneGrpThree.Exception.CouponAlreadyExistsException;
import com.example.sprintOneGrpThree.Exception.CouponDoesNotExistException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidAmountException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidNameException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidPercentageException;
import com.example.sprintOneGrpThree.Repository.CouponRepository;
import com.example.sprintOneGrpThree.Repository.SessionRepository;

@Service
public class CouponServiceImpl implements CouponService {
	
	@Autowired
	private CouponRepository couponRepo;
	
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public Coupon saveCoupon(Coupon cpn) throws CouponAlreadyExistsException, CouponInvalidNameException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponAccessViolationException {
		
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		
		if(!res)
			throw new CouponAccessViolationException();
		
			if(couponRepo.existsById(cpn.getCoupon_id()) || couponRepo.existsByAmount(cpn.getAmount()) 
					|| couponRepo.existsByName(cpn.getName()) || couponRepo.existsByPercentage(cpn.getPercentage()))
				throw new CouponAlreadyExistsException();
			
			if(!cpn.getName().matches("[a-zA-Z0-9]{1,10}[%]{0,1}[a-zA-Z]{0,10}"))
				throw new CouponInvalidNameException();
			
			if(cpn.getAmount() < 0 || cpn.getAmount() > 1000) 
				throw new CouponInvalidAmountException();
			
			if(cpn.getPercentage() < 0 || cpn.getPercentage() > 40)
				throw new CouponInvalidPercentageException();
			
			Coupon savedCoupon = couponRepo.save(cpn);
			return savedCoupon;
		
	}

	@Override
	public List<Coupon> saveCoupons(List<Coupon> list) throws CouponAlreadyExistsException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponInvalidNameException, CouponAccessViolationException {
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		
		if(!res)
			throw new CouponAccessViolationException();
		
		for(Coupon c : list) {
			if(couponRepo.existsById(c.getCoupon_id()) || couponRepo.existsByAmount(c.getAmount()) 
					|| couponRepo.existsByName(c.getName()) || couponRepo.existsByPercentage(c.getPercentage()))
				throw new CouponAlreadyExistsException();
			
			if(c.getAmount() < 0 || c.getAmount() > 1000)
				throw new CouponInvalidAmountException();
			
			if(c.getPercentage() < 0 || c.getPercentage() > 40)
				throw new CouponInvalidPercentageException();
			
			if(!c.getName().matches("[a-zA-Z0-9]{1,10}[%]{0,1}[a-zA-Z]{0,10}"))
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
		
		if(coupon.isEmpty())
			return Optional.empty();
		
		return coupon;
	}

	@Override
	public boolean updateCoupon(Coupon cpn) throws CouponDoesNotExistException, CouponInvalidNameException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponAccessViolationException {
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		
		if(!res)
			throw new CouponAccessViolationException();
		
		if(!couponRepo.existsById(cpn.getCoupon_id()))
			throw new CouponDoesNotExistException();
		
		if(!cpn.getName().matches("[a-zA-Z0-9]{1,10}[%]{0,1}[a-zA-Z]{0,10}"))
			throw new CouponInvalidNameException();
		
		if(cpn.getAmount() < 0 || cpn.getAmount() > 1000)
			throw new CouponInvalidAmountException();
		
		if(cpn.getPercentage() < 0 || cpn.getPercentage() > 40)
			throw new CouponInvalidPercentageException();
		
		try {
			couponRepo.save(cpn);
			return true;
		}catch(Exception e) {
			e.getMessage();
			return false;
		}
	}

	@Override
	public boolean updateCoupons(List<Coupon> list) throws CouponAlreadyExistsException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponInvalidNameException, CouponDoesNotExistException, CouponAccessViolationException {
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		
		if(!res)
			throw new CouponAccessViolationException();
		
		for(Coupon c : list) {
			if(!couponRepo.existsById(c.getCoupon_id()))
				throw new CouponDoesNotExistException();
			
			if(c.getAmount() < 0 || c.getAmount() > 1000)
				throw new CouponInvalidAmountException();
			
			if(c.getPercentage() < 0 || c.getPercentage() > 40)
				throw new CouponInvalidPercentageException();
			
			if(!c.getName().matches("[a-zA-Z0-9]{1,10}[%]{0,1}[a-zA-Z]{0,10}"))
				throw new CouponInvalidNameException();
			
			couponRepo.save(c);
		}
		
		try {
			couponRepo.saveAll(list);
			return true;
		}catch(Exception e) {
			e.getMessage();
			return false;
		}
	}

	@Override
	public boolean deleteCoupon(int id) throws CouponDoesNotExistException, CouponAccessViolationException {
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		
		if(!res)
			throw new CouponAccessViolationException();
		
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
	public boolean deleteAllCoupons() throws CouponAccessViolationException {
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		
		if(!res)
			throw new CouponAccessViolationException();
		
		try {
			couponRepo.deleteAll();
			return true;
		}catch(Exception e) {
			e.getMessage();
			return false;
		}
	}

	@Override
	public List<Coupon> getCouponByName(String name) throws CouponDoesNotExistException {
		if(!couponRepo.existsByName(name))
			throw new CouponDoesNotExistException();
		return couponRepo.findCouponByName(name);
	}

	@Override
	public List<Coupon> getCouponByAmount(double amount) throws CouponDoesNotExistException {
		if(!couponRepo.existsByAmount(amount))
			throw new CouponDoesNotExistException();
		return couponRepo.findCouponByAmount(amount);
	}

	@Override
	public List<Coupon> getCouponByPercentage(int percentage) throws CouponDoesNotExistException {
		if(!couponRepo.existsByPercentage(percentage))
			throw new CouponDoesNotExistException();
		return couponRepo.findCouponByPercentage(percentage);
	}

//	@Override
//	public boolean existsByName(String name) {
//		return couponRepo.existsByName(name);
//	}

}