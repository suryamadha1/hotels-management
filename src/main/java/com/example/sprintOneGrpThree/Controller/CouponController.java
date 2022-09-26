package com.example.sprintOneGrpThree.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintOneGrpThree.Entity.Coupon;
import com.example.sprintOneGrpThree.Exception.CouponAlreadyExistsException;
import com.example.sprintOneGrpThree.Exception.CouponDoesNotExistException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidAmountException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidNameException;
import com.example.sprintOneGrpThree.Exception.CouponInvalidPercentageException;
import com.example.sprintOneGrpThree.Service.CouponService;

@RestController
public class CouponController {
	
	@Autowired(required = false)
	private CouponService couponServ;
	
	@PostMapping("/addCoupon")
	@ResponseBody
	public ResponseEntity<String> saveCoupon(@RequestBody Coupon coupon) throws CouponAlreadyExistsException, CouponInvalidNameException, CouponInvalidAmountException, CouponInvalidPercentageException{
		Coupon result = couponServ.saveCoupon(coupon);
		return new ResponseEntity<String>("Hurray! Coupon saved successfully.", HttpStatus.CREATED);
	}
	
	@PostMapping("/addCoupons")
	@ResponseBody
	public ResponseEntity<String> saveCoupons(@RequestBody List<Coupon> list) throws CouponAlreadyExistsException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponInvalidNameException{
		 List<Coupon> result = couponServ.saveCoupons(list);
		 return new ResponseEntity<String>("Hurray! coupons saved successfully.",HttpStatus.CREATED);
	}
	
	@GetMapping("/coupons")
	@ResponseBody
	public ResponseEntity<List<Coupon>> getCoupons(){
		List<Coupon> result = couponServ.getCoupons();
		if(result.isEmpty())
			return new ResponseEntity<List<Coupon>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Coupon>>(result, HttpStatus.FOUND);
	}
	
	@GetMapping("/coupon/{id}")
	@ResponseBody
	public ResponseEntity<Optional<Coupon>> getCouponById(@PathVariable int id) throws CouponDoesNotExistException{
		Optional<Coupon> result = couponServ.getCouponById(id);
		if(result.isEmpty())
			return new ResponseEntity<Optional<Coupon>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Optional<Coupon>>(result, HttpStatus.FOUND);
	}
	
	@PutMapping("/coupon/update")
	@ResponseBody
	public ResponseEntity<String> updateCoupon(@RequestBody Coupon coupon) throws CouponDoesNotExistException, CouponInvalidNameException, CouponInvalidAmountException, CouponInvalidPercentageException{
		Coupon result = couponServ.updateCoupon(coupon);
		return new ResponseEntity<String>("Coupon updated successfully.", HttpStatus.OK);
	}
	
	@PutMapping("/coupons/update")
	public ResponseEntity<String> updateCoupons(@RequestBody List<Coupon> list) throws CouponAlreadyExistsException, CouponInvalidAmountException, CouponInvalidPercentageException, CouponInvalidNameException{
		List<Coupon> result = couponServ.updateCoupons(list);
		return new ResponseEntity<String>("Coupons updated successfully.", HttpStatus.OK);
	}
	
	@DeleteMapping("/coupon/delete/{id}")
	@ResponseBody
	public ResponseEntity<String> deleteCoupon(@PathVariable int id) throws CouponDoesNotExistException{
		boolean result = couponServ.deleteCoupon(id);
		if(result)
			return new ResponseEntity<String>("Coupon deleted successfully.", HttpStatus.OK);
		return new ResponseEntity<String>("Cannot delete coupon.", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/coupons/deleteAll")
	@ResponseBody
	public ResponseEntity<String> deleteAllCoupons(){
		boolean result = couponServ.deleteAllCoupons();
		if(result)
			return new ResponseEntity<String>("Coupons deleted successfully.", HttpStatus.OK);
		return new ResponseEntity<String>("Cannot delete.", HttpStatus.BAD_REQUEST);
	}

}
