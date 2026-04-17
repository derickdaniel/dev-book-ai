package com.devbook.ai.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devbook.ai.dto.AIChatRequestDTO;
import com.devbook.ai.dto.AIChatResponseDTO;
import com.devbook.ai.dto.GeminiModelResponse;
import com.google.genai.Client;
import com.google.genai.Pager;
import com.google.genai.errors.ServerException;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Model;
import com.google.gson.Gson;

@Service
public class GoogleGeminiService {

	private static final String MODEL = "gemini-3-flash-preview";
	private static final String MODEL_2 = "gemini-2.5-flash";
	private static final String API_KEY = "AIzaSyAnj7U1-dkI1j3a2UbAxrQ4DD70X7RSdOo";

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
		} catch (Exception e) {
			genReponse = e.getMessage();
		}
		System.out.println(genReponse);
		aIChatRequestDTO.setResponse(genReponse);

		AIChatResponseDTO aiResponseDto = aiChatService.processAIChatAsynch(aIChatRequestDTO.getUserId(),
				aIChatRequestDTO.getConversationId(), aIChatRequestDTO.getPrompt(), genReponse);

		return aiResponseDto;
	}

	public List<GeminiModelResponse> getModelList() {
		List<GeminiModelResponse> modelListResponse = new ArrayList<GeminiModelResponse>();
		try {
			Client client = Client.builder().apiKey(API_KEY).build();
			Pager<Model> pager = client.models.list(null);
			Gson gson = new Gson();
			pager.iterator().forEachRemaining(p -> modelListResponse.add(gson.fromJson(p.toJson(), GeminiModelResponse.class)));

		} catch (ServerException sex) {
			sex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(Collections.singletonMap("data", modelListResponse));
		return modelListResponse;
	}

}
