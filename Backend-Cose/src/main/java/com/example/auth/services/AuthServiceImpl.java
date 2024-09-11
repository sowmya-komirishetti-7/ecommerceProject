package com.example.auth.services;

import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth.dto.SignupRequest;
import com.example.auth.entity.Customer;
import com.example.auth.repository.CustomerRepository;

@Service
public class AuthServiceImpl implements AuthService{
	
	private final CustomerRepository customerRepository;
	
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AuthServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
		this.customerRepository = customerRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Customer createCustomer(SignupRequest signupRequest) {
	   
		if(customerRepository.existsByEmail(((SignupRequest) signupRequest).getEmail())) {
			
		
		return null;
	}
	
	Customer customer = new Customer();
	 BeanUtils.copyProperties(signupRequest,customer);

     //Hash the password before saving
     String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
     customer.setPassword(hashPassword);
     Customer createdCustomer = customerRepository.save(customer);
     customer.setId(createdCustomer.getId());
     return customer;
 }
	
	public Boolean hasCustomerWithEmail(String email) {
		return customerRepository.findByEmail(email).isPresent();
	}
	
	public List<Customer> displayAllUserDetails(){
		 List<Customer>customer = customerRepository.findAll();
		 return customer;
	}

	
	

}
