package com.skyline.bert.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skyline.bert.payloads.UserDto;
import com.skyline.bert.service.UserService;
import com.skyline.bert.config.AppConstant;
import com.skyline.bert.dao.RoleRepo;
import com.skyline.bert.dao.UserRepo;
import com.skyline.bert.entity.Role;
import com.skyline.bert.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private PasswordEncoder passwordEncode;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDto registerNewUser(UserDto userDto) {
		 
	 User user=modelMapper.map(userDto, User.class);
		//encoded the password
		user.setPassword(this.passwordEncode.encode(user.getPassword()));
		
		//roles 
		Role role=this.roleRepo.findById(AppConstant.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser=this.userRepo.save(user);
		
		return modelMapper.map(newUser,UserDto.class);
	}


	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
	}
	
	public User  findByEmail(String email) {
		
		return userRepo.findByEmail(email).get();
		
	}

}
