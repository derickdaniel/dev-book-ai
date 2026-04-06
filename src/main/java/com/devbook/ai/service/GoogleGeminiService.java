package com.devbook.ai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devbook.ai.dto.AIChatRequestDTO;
import com.devbook.ai.dto.AIChatResponseDTO;
import com.devbook.ai.entity.Conversation;
import com.devbook.ai.entity.Message;
import com.google.genai.Client;
import com.google.genai.errors.ServerException;
import com.google.genai.types.GenerateContentResponse;

@Service
public class GoogleGeminiService {

	private static final String MODEL = "gemini-3-flash-preview";
	private static final String MODEL_2 = "gemini-2.5-flash";
	private static final String API_KEY = "AIzaSyB8sFY4a2J_GbomLriWzYPUTahY4xhin8k";

	@Autowired
	AiChatService aiChatService;

	public AIChatResponseDTO generateContent(AIChatRequestDTO aIChatRequestDTO) {

		String genReponse = "";
		try {
			Client client = Client.builder().apiKey(API_KEY).build();
			GenerateContentResponse response = client.models.generateContent(MODEL_2, aIChatRequestDTO.getPrompt(),
					null);
			genReponse = response.text();

		} catch (ServerException sex) {
			genReponse = sex.getMessage();
		}
		System.out.println(genReponse);
		aIChatRequestDTO.setResponse(genReponse);

		AIChatResponseDTO aiResponseDto = aiChatService.processAIChatAsynch(aIChatRequestDTO.getUserId(), aIChatRequestDTO.getConversationId(),
				aIChatRequestDTO.getPrompt(), genReponse);

		return aiResponseDto;
	}

}
