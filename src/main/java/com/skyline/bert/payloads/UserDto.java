package com.skyline.bert.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private String name;
	private String email;
	private String password;
}
