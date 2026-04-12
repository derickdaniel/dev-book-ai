package com.devbook.ai.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devbook.ai.dto.AIChatResponseDTO;
import com.devbook.ai.entity.AiResponse;
import com.devbook.ai.entity.Conversation;
import com.devbook.ai.entity.Message;
import com.devbook.ai.entity.Sender;
import com.devbook.ai.entity.UsageLog;
import com.devbook.ai.repository.AiResponseRepository;
import com.devbook.ai.repository.ConversationRepository;
import com.devbook.ai.repository.MessageRepository;
import com.devbook.ai.repository.UsageLogRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AiChatService {

	private final ConversationRepository conversationRepo;
	private final MessageRepository messageRepo;
	private final AiResponseRepository aiResponseRepo;
	private final UsageLogRepository usageLogRepo;

	@Autowired
	MessageService messageService;

	@Autowired
	public AiChatService(ConversationRepository conversationRepo, MessageRepository messageRepo,
			AiResponseRepository aiResponseRepo, UsageLogRepository usageLogRepo) {
		this.conversationRepo = conversationRepo;
		this.messageRepo = messageRepo;
		this.aiResponseRepo = aiResponseRepo;
		this.usageLogRepo = usageLogRepo;
	}

	// @Async
	public AIChatResponseDTO processAIChatAsynch(Long userId, Long conversationId, String userInput, String aiReply) {

		// Get or create conversation
		Conversation conversation;
		if (conversationId != null) {
			conversation = conversationRepo.findById(conversationId)
					.orElseThrow(() -> new RuntimeException("Conversation not found"));
		} else {
			conversation = new Conversation();
			conversation.setUserId(userId);
			conversation.setTitle(generateTitle(userInput));
			conversation.setCreatedAt(LocalDateTime.now());
			conversation = conversationRepo.save(conversation);
		}
		
		updateMissingFields(conversation);

		// Save USER message
		Message userMessage = new Message();
		userMessage.setConversation(conversation);
		userMessage.setSender(Sender.user);
		userMessage.setContent(userInput);

		userMessage = messageRepo.save(userMessage);

		// Save AI message
		Message aiMessage = new Message();
		aiMessage.setConversation(conversation);
		aiMessage.setSender(Sender.assistant);
		aiMessage.setContent(aiReply);

		aiMessage = messageRepo.save(aiMessage);

		// Save AI response metadata
		AiResponse aiResponse = new AiResponse();
		aiResponse.setMessage(aiMessage);
		aiResponse.setModelName("gpt-4");
		aiResponse.setPromptTokens(100);
		aiResponse.setCompletionTokens(200);
		aiResponse.setTotalTokens(300);
		aiResponse.setStatus("success");

		aiResponseRepo.save(aiResponse);

		// Save usage logs
		UsageLog log = new UsageLog();
		log.setUserId(userId);
		log.setConversationId(conversation.getId());
		log.setModelName("gpt-4");
		log.setPromptTokens(100);
		log.setCompletionTokens(200);
		log.setTotalTokens(300);
		log.setCost(0.005);

		usageLogRepo.save(log);

		System.out.println(
				"AI chat saved.  Message Id: " + aiMessage.getId() + " Conversation Id: " + conversation.getId());

		return new AIChatResponseDTO(userId, conversation.getId(), userInput, aiReply);
	}

	private void updateMissingFields(Conversation conversation) {
		if(conversation.getTitle().contains("Conversation")) {
			conversation.setTitle(generateTitle((messageService.getByConversationId(conversation.getId()).get(0).getContent())));
		}
		
		if (conversation.getCreatedAt() == null) {
			conversation.setCreatedAt(LocalDateTime.now());
		}
	}
	
	public void deleteConversation(Long conversationId) {

        // 1. delete AI responses (if exists)
		aiResponseRepo.deleteByConversationId(conversationId);

        // 2. delete messages
        messageRepo.deleteByConversationId(conversationId);

        // 3. delete conversation
        conversationRepo.deleteById(conversationId);
    }

	private String generateTitle(String prompt) {
		return prompt.length() > 40 ? prompt.substring(0, 40) + "..." : prompt;
	}

}
