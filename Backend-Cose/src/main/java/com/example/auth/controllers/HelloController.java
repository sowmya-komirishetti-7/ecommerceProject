package com.example.auth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.dto.HelloResponse;
import com.example.auth.entity.Customer;
import com.example.auth.services.AuthServiceImpl;
import com.example.auth.services.jwt.CustomerServiceImpl;

@RestController
@RequestMapping("/user")
public class HelloController {
	
	private CustomerServiceImpl custSer;
	
	private AuthServiceImpl authService;

	@Autowired  //DI
	public HelloController(CustomerServiceImpl custRepo, AuthServiceImpl authService) 
	{
		this.custSer = custRepo;
		this.authService = authService;
	}
	
	@GetMapping("/hello")
	public HelloResponse hello() {
		return new HelloResponse("Hello from Authorized API request.");
	}
	@GetMapping("/list")
	public List<Customer> getAllUsersData(){
	 return authService.displayAllUserDetails();

}


}
