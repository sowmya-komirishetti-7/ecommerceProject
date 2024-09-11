package com.example.auth.services;

import com.example.auth.dto.SignupRequest;
import com.example.auth.entity.Customer;

public interface AuthService {
	
	Customer createCustomer(SignupRequest signupRequest);
	
	Boolean hasCustomerWithEmail(String email);

}
