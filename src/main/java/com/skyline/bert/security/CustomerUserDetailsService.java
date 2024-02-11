package com.skyline.bert.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skyline.bert.dao.UserRepo;
import com.skyline.bert.entity.User;
import com.skyline.bert.entity.UserPrinciple;

@Service
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user= userRepo.findByEmail(username).orElseThrow(()->new RuntimeException("username not found"));
		return new UserPrinciple(user);
	}

}
