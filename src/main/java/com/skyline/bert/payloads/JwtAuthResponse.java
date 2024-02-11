package com.skyline.bert.payloads;

import com.skyline.bert.entity.User;
import com.skyline.bert.payloads.JwtAuthResponse;

import lombok.Data;

@Data
public class JwtAuthResponse {
	private String token;
	private User user;
}


