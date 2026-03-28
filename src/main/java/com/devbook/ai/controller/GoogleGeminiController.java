package com.devbook.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devbook.ai.dto.GenerateContentDTO;
import com.devbook.ai.service.GoogleGeminiService;

@RestController
@RequestMapping("/api/ai")
public class GoogleGeminiController {

	@Autowired
	private GoogleGeminiService geminiService;

	@PostMapping
	public GenerateContentDTO getGuides(@RequestBody GenerateContentDTO genContentDTO) {
		return geminiService.generateContent(genContentDTO);	
	}

}
