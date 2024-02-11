package com.skyline.bert.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyline.bert.dao.UserRepo;
import com.skyline.bert.entity.User;
import com.skyline.bert.exception.ApiException;
import com.skyline.bert.payloads.JwtAuthRequest;
import com.skyline.bert.payloads.JwtAuthResponse;
import com.skyline.bert.payloads.UserDto;
import com.skyline.bert.security.JwtTokenHelper;
import com.skyline.bert.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenhelper;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
		@RequestBody JwtAuthRequest request	
			) throws Exception{
		
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails=this.userDetailService.loadUserByUsername(request.getUsername());
		User user= userService.findByEmail(request.getUsername());
		
	String token=	this.jwtTokenhelper.generateToken(userDetails);
	JwtAuthResponse response=new JwtAuthResponse();
	response.setToken(token);
	response.setUser(user);
	
	return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
	 
		try {
		this.authenticationManager.authenticate(authenticationToken);
		}
		
		catch(BadCredentialsException e) {
			System.out.println("Invalid details");
			
			throw new ApiException("Invalid username or password");
		}
	}
	
	//register new user api
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto){
	UserDto registeredUser=	this.userService.registerNewUser(userDto);	
	return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
	}
}
