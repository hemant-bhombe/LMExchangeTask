package com.lm.login.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lm.login.app.binding.Login;
import com.lm.login.app.binding.User;
import com.lm.login.app.entity.Product;
import com.lm.login.app.entity.UserMaster;
import com.lm.login.app.serviceinterface.ServiceInterface;

@RestController
public class LoginController {

	@Autowired
	private ServiceInterface si;
	
	@PostMapping("/registration")
	public ResponseEntity<String> userReg(@RequestBody User user)
	{
		boolean saveUser=si.saveUser(user);
		if(saveUser)
		{
			return new ResponseEntity<String>("Registration Success", HttpStatus.CREATED);
		}else
		{
			return new ResponseEntity<String>("Registration Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Login login)
	{
		String status=si.login(login);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
	
	@PutMapping("/changepwd/{userId}")
	public ResponseEntity<UserMaster> changePwd(@PathVariable Integer userId, @RequestBody UserMaster um)
	{
		UserMaster status=si.changepwd(userId, um);
			
		return new ResponseEntity<UserMaster>(status, HttpStatus.OK);	
	}
	@GetMapping("/userinfo")
	public ResponseEntity<Iterable<UserMaster>> userInfo()
	{
		Iterable<UserMaster>	itr	=si.userInfo();
		return new ResponseEntity<Iterable<UserMaster>>(itr, HttpStatus.OK);
	}
	
	@GetMapping("/userinfo/{userId}")
	public ResponseEntity<UserMaster> userInfo(@PathVariable Integer userId)
	{
		UserMaster um=si.userInfo(userId);
		return new ResponseEntity<UserMaster>(um,HttpStatus.OK);
	}
	
	@PostMapping("/productregister")
	public ResponseEntity<Product> registerProduct(@RequestBody Product pro)
	{
			Product savepro	=si.registerProduct(pro);
				
			return new ResponseEntity<Product>(savepro, HttpStatus.CREATED);		
				
	}
	
	@GetMapping("/productinfo")
	public ResponseEntity<Iterable<Product>> productInfo()
	{
				Iterable<Product> proinfo =	si.productInfo();
				
				return new ResponseEntity<Iterable<Product>>(proinfo, HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<Page<Product>> getProducts(@RequestParam(defaultValue = "0") int page,
	                                            @RequestParam(defaultValue = "10") int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<Product> products = si.getProducts(pageable);
	    return ResponseEntity.ok(products);
	}
}
