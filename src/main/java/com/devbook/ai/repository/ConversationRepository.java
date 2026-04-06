package com.devbook.ai.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devbook.ai.entity.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
	
	List<Conversation> findByUserId(Long userId);
	
	Optional<Conversation> findById(Long id);
}
