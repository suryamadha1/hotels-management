package com.example.sprintOneGrpThree.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedMetric;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintOneGrpThree.Entity.Coupon;
import com.example.sprintOneGrpThree.Service.CouponService;

@RestController
public class CouponController {
	
	@Autowired
	private CouponService couponServ;
	
	@PostMapping("/addCoupon")
	public ResponseEntity<String> saveCoupon(@RequestBody Coupon coupon){
		Coupon result = couponServ.saveCoupon(coupon);
		return new ResponseEntity<String>("Hurray! Coupon saved successfully.", HttpStatus.CREATED);
	}
	
	@PostMapping("/addCoupons")
	public ResponseEntity<String> saveCoupons(@RequestBody List<Coupon> list){
		 List<Coupon> result = couponServ.saveCoupons(list);
		 return new ResponseEntity<String>("Hurray! coupons saved successfully.",HttpStatus.CREATED);
	}
	
	@GetMapping("/coupons")
	public ResponseEntity<List<Coupon>> getCoupons(){
		List<Coupon> result = couponServ.getCoupons();
		return new ResponseEntity<List<Coupon>>(result, HttpStatus.FOUND);
	}
	
	@GetMapping("/coupon/{id}")
	public ResponseEntity<Optional<Coupon>> getCouponById(@PathVariable int id){
		Optional<Coupon> result = couponServ.getCouponById(id);
		return new ResponseEntity<Optional<Coupon>>(result, HttpStatus.FOUND);
	}
	
	@PutMapping("/coupon/update")
	public ResponseEntity<String> updateCoupon(@RequestBody Coupon coupon){
		Coupon result = couponServ.updateCoupon(coupon);
		return new ResponseEntity<String>("Coupon updated successfully.", HttpStatus.OK);
	}
	
	@PutMapping("/coupons/update")
	public ResponseEntity<String> updateCoupons(@RequestBody List<Coupon> list){
		List<Coupon> result = couponServ.updateCoupons(list);
		return new ResponseEntity<String>("Coupons updated successfully.", HttpStatus.OK);
	}
	
	@DeleteMapping("/coupon/delete/{id}")
	public ResponseEntity<String> deleteCoupon(@PathVariable int id){
		boolean result = couponServ.deleteCoupon(id);
		if(result)
			return new ResponseEntity<String>("Coupon deleted successfully.", HttpStatus.OK);
		return new ResponseEntity<String>("Cannot delete coupon.", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/coupons/deleteAll")
	public ResponseEntity<String> deleteAllCoupons(){
		boolean result = couponServ.deleteAllCoupons();
		if(result)
			return new ResponseEntity<String>("Coupons deleted successfully.", HttpStatus.OK);
		return new ResponseEntity<String>("Cannot delete.", HttpStatus.BAD_REQUEST);
	}

}
