package com.skyline.bert.payloads;

import java.util.List;

import lombok.Data;

@Data
public class ChatGptResponse {

	private List<Choice> choices;
	
	@Data
	public static class Choice{
		private int index;
		private Message message;
	}
}
