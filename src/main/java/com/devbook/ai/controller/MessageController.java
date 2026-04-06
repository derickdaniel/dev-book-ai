package com.devbook.ai.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devbook.ai.entity.Message;
import com.devbook.ai.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public Message create(@RequestBody Message m) {
        return service.save(m);
    }
    
    @GetMapping("/conversation/{conversationId}")
    public List<Message> geatByConversationId(@PathVariable Long conversationId) {
    	return service.getByConversationId(conversationId);
    }

    @GetMapping
    public List<Message> getAll() {
        return service.getAll();
    }
}