package com.devbook.ai.dto;

public class AIChatRequestDTO {
	
	private Long userId;
	private Long conversationId;
	private String prompt;
	private String response;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPrompt() {
		return prompt;
	}
	public Long getConversationId() {
		return conversationId;
	}
	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

}
