package com.devbook.ai.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usage_logs")
public class UsageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long conversationId;
    private String modelName;

    private Integer promptTokens;
    private Integer completionTokens;
    private Integer totalTokens;

    private Double cost;
    private LocalDateTime createdAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getConversationId() {
		return conversationId;
	}
	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Integer getPromptTokens() {
		return promptTokens;
	}
	public void setPromptTokens(Integer promptTokens) {
		this.promptTokens = promptTokens;
	}
	public Integer getCompletionTokens() {
		return completionTokens;
	}
	public void setCompletionTokens(Integer completionTokens) {
		this.completionTokens = completionTokens;
	}
	public Integer getTotalTokens() {
		return totalTokens;
	}
	public void setTotalTokens(Integer totalTokens) {
		this.totalTokens = totalTokens;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
}
