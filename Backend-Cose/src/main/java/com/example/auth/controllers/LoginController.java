package com.example.auth.controllers;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.LoginResponse;
import com.example.auth.entity.Customer;
import com.example.auth.repository.CustomerRepository;
import com.example.auth.services.jwt.CustomerServiceImpl;
import com.example.auth.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class LoginController {
	
	private final AuthenticationManager authenticationManager;

    private final CustomerServiceImpl customerService;

    private final JwtUtil jwtUtil;
    
    private final CustomerRepository customerRepository;

    public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
	
    @Autowired
    public LoginController(AuthenticationManager authenticationManager, CustomerServiceImpl customerService, JwtUtil jwtUtil, CustomerRepository customerRepository ) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/authenticate")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws IOException, JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password.");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer is not activated");
            return null;
        }
        final UserDetails userDetails = customerService.loadUserByUsername(loginRequest.getEmail());
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

      
        
//        if(optionalCustomer.isPresent()) {
//			response.getWriter().write(new JSONObject()
//					.put("userId",optionalCustomer.get().getId())
//					.put("role", optionalCustomer.get().getRole())
//					.toString()
//					);
//			response.addHeader("Access-Control-Expose-Headers", "Authorization");
//			response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, " + 
//			"X-Requested-With, Content-Type, Accept, X-Custom-header");
//			response.addHeader(HEADER_STRING, TOKEN_PREFIX+jwt);
//			
//		}
        return new LoginResponse(jwt);
    }

}
