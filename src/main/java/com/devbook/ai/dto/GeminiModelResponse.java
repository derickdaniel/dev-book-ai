package com.devbook.ai.dto;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize 
public class GeminiModelResponse {

	private String name;
	private String displayName;
	private String description;
	private String version;
	private TunedModelInfo tunedModelInfo;
	private int inputTokenLimit;
	private int outputTokenLimit;
	private List<String> supportedActions;

	public GeminiModelResponse() {
	}

	public GeminiModelResponse(String name, String displayName, String description, String version,
			TunedModelInfo tunedModelInfo, int inputTokenLimit, int outputTokenLimit, List<String> supportedActions) {
		this.name = name;
		this.displayName = displayName;
		this.description = description;
		this.version = version;
		this.tunedModelInfo = tunedModelInfo;
		this.inputTokenLimit = inputTokenLimit;
		this.outputTokenLimit = outputTokenLimit;
		this.supportedActions = supportedActions;
	}

	// --- Getters and Setters ---
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public TunedModelInfo getTunedModelInfo() {
		return tunedModelInfo;
	}

	public void setTunedModelInfo(TunedModelInfo tunedModelInfo) {
		this.tunedModelInfo = tunedModelInfo;
	}

	public int getInputTokenLimit() {
		return inputTokenLimit;
	}

	public void setInputTokenLimit(int inputTokenLimit) {
		this.inputTokenLimit = inputTokenLimit;
	}

	public int getOutputTokenLimit() {
		return outputTokenLimit;
	}

	public void setOutputTokenLimit(int outputTokenLimit) {
		this.outputTokenLimit = outputTokenLimit;
	}

	public List<String> getSupportedActions() {
		return supportedActions;
	}

	public void setSupportedActions(List<String> supportedActions) {
		this.supportedActions = supportedActions;
	}

	public String toString() {
		return "GeminiModelResponse{" + "name='" + name + '\'' + ", displayName='" + displayName + '\''
				+ ", description='" + description + '\'' + ", version='" + version + '\'' + ", tunedModelInfo="
				+ tunedModelInfo + ", inputTokenLimit=" + inputTokenLimit + ", outputTokenLimit=" + outputTokenLimit
				+ ", supportedActions=" + supportedActions + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GeminiModelResponse that = (GeminiModelResponse) o;
		return inputTokenLimit == that.inputTokenLimit && outputTokenLimit == that.outputTokenLimit
				&& Objects.equals(name, that.name) && Objects.equals(displayName, that.displayName)
				&& Objects.equals(description, that.description) && Objects.equals(version, that.version)
				&& Objects.equals(tunedModelInfo, that.tunedModelInfo)
				&& Objects.equals(supportedActions, that.supportedActions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, displayName, description, version, tunedModelInfo, inputTokenLimit, outputTokenLimit,
				supportedActions);
	}
}
