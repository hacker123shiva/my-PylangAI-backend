package com.skyline.bert.payloads;

import com.skyline.bert.payloads.JwtAuthRequest;

import lombok.Data;

@Data
public class JwtAuthRequest {
private String username;
private String password;
}


