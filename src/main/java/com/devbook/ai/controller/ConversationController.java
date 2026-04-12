package com.devbook.ai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devbook.ai.entity.Conversation;
import com.devbook.ai.service.AiChatService;
import com.devbook.ai.service.ConversationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

	@Autowired
	private ConversationService service;
	
	@Autowired
	private AiChatService chatService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@PostMapping
	public Conversation create(@RequestBody Conversation c) {
		return service.create(c);
	}

	@GetMapping("/user/{userId}")
	public List<Conversation> getByUserId(@PathVariable Long userId) {
		return service.getByUserId(Long.valueOf(httpServletRequest.getHeader("userid")));
	}

	@GetMapping
	public List<Conversation> getAll() {
		return service.getAll();
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConversation(@PathVariable Long id) {

		chatService.deleteConversation(id);

        return ResponseEntity.ok().body("Conversation deleted successfully");
    }
}