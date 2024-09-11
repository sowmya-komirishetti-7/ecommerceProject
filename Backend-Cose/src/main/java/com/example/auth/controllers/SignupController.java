package com.example.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.dto.SignupRequest;
import com.example.auth.entity.Customer;
import com.example.auth.services.AuthService;

@RestController
@RequestMapping("/user")
public class SignupController {
	
	 private final AuthService authService;

	    @Autowired
	    public SignupController(AuthService authService) {
	        this.authService = authService;
	    }

	    @PostMapping("/signup")
	    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
	    	if(authService.hasCustomerWithEmail(signupRequest.getEmail())) {
	    		return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
	    	}
	        Customer createdCustomer = authService.createCustomer(signupRequest);
	        if (createdCustomer != null) {
	            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create customer");
	        }
	    }

}
