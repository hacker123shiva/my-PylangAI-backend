package com.skyline.bert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyline.bert.config.AppConstant;
import com.skyline.bert.dao.RoleRepo;
import com.skyline.bert.dao.UserRepo;
import com.skyline.bert.entity.Role;
import com.skyline.bert.entity.User;
import com.skyline.bert.service.UserService;


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role=this.roleRepo.findById(AppConstant.NORMAL_USER).get();
		user.getRoles().add(role);
		
		User userS=userRepo.save(user);
		return ResponseEntity.ok(userS);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> getUsers(){
		List<User> users=userRepo.findAll();
		return ResponseEntity.ok(users);
	}
	

}
