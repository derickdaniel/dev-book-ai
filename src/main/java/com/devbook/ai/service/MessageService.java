package com.devbook.ai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devbook.ai.entity.Message;
import com.devbook.ai.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Message save(Message m) {
        return repository.save(m);
    }
    
	public List<Message> getByConversationId(Long conversationId) {
		return repository.findByConversationId(conversationId);
	}

    public List<Message> getAll() {
        return repository.findAll();
    }
}