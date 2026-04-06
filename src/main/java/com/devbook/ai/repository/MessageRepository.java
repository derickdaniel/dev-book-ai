package com.devbook.ai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devbook.ai.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findByConversationId(Long conversationId);
}
