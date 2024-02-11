package com.skyline.bert.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skyline.bert.entity.User;
import com.skyline.bert.payloads.UserDto;

 
public interface UserService {

	public UserDto registerNewUser(UserDto user);
	public UserDto createUser(UserDto user);
	public UserDto updateUser(UserDto user, Integer userId);
	public UserDto getUserById(Integer userId);
	public List<UserDto>  getAllUsers();
	public void deleteUser(Integer userId);
	public User  findByEmail(String email);
	
}
