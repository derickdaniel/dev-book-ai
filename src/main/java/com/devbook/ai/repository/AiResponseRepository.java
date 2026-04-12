package com.devbook.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.devbook.ai.entity.AiResponse;

import jakarta.transaction.Transactional;

public interface AiResponseRepository extends JpaRepository<AiResponse, Long> {
	
	@Modifying
	@Transactional
    @Query("DELETE FROM AiResponse ar WHERE ar.message.conversation.id = :conversationId")
    void deleteByConversationId(Long conversationId);
}
