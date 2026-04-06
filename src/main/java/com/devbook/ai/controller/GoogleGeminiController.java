package com.devbook.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devbook.ai.dto.AIChatRequestDTO;
import com.devbook.ai.dto.AIChatResponseDTO;
import com.devbook.ai.service.GoogleGeminiService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/ai")
public class GoogleGeminiController {

	@Autowired
	private GoogleGeminiService geminiService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@PostMapping
	public AIChatResponseDTO generateContent(@RequestBody AIChatRequestDTO genContentDTO) {

		genContentDTO.setUserId(Long.valueOf(httpServletRequest.getHeader("userid")));
		
		return geminiService.generateContent(genContentDTO);
	}

}
