package com.devbook.ai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devbook.ai.entity.Conversation;
import com.devbook.ai.repository.ConversationRepository;

@Service
public class ConversationService {

	private final ConversationRepository repository;

	public ConversationService(ConversationRepository repository) {
		this.repository = repository;
	}

	public Conversation create(Conversation c) {
		return repository.save(c);
	}

	public List<Conversation> getAll() {
		return repository.findAll();
	}

	public List<Conversation> getByUserId(long userId) {
		return repository.findByUserId(userId);
	}
}