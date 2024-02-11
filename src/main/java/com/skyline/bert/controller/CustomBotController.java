package com.skyline.bert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.skyline.bert.config.AppConstant;
import com.skyline.bert.entity.Translated;
import com.skyline.bert.payloads.ChatGPTRequest;
import com.skyline.bert.payloads.ChatGptResponse;

@RestController
@RequestMapping("/api/bot")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomBotController {
	@Value("${openai.model}")
	private String model;
	
	@Value("${openai.api.url}")
	private String apiURL;
	
	@Autowired
	private RestTemplate template;
	
	@GetMapping("/chat")
public ResponseEntity<Translated>  chat(  @RequestParam("prompt") String prompt, @RequestParam("to") String to ) {
   prompt ="convert the given prompt: "+prompt+" into provided language "+to+" if language is not correct show Your proper language";
	ChatGPTRequest request=new ChatGPTRequest(model, prompt);
	ChatGptResponse chatGptResponse=template.postForObject(apiURL,request,ChatGptResponse.class);
	Translated ts=new Translated(chatGptResponse.getChoices().get(0).getMessage().getContent());
   return ResponseEntity.ok(ts);
}
}
