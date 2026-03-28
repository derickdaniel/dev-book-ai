package com.devbook.ai.service;

import org.springframework.beans.factory.annotation.Value;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class GenerateTextFromTextInput {
	
	@Value("${gemini.ai.api-key}")
	private static String apiKey;
	
	public static void main(String[] args) {
		// The client gets the API key from the environment variable `GEMINI_API_KEY`.
		
		System.out.println("API Key: " + apiKey);
		Client client = Client.builder()
		        .apiKey("AIzaSyBbabBQiRh-2k3tsv4jQdU8s48G78OJA2Y")
		        .build();
		GenerateContentResponse response = client.models.generateContent("gemini-3-flash-preview",
				"Java lts version list", null);

		System.out.println(response.text());
	}
}