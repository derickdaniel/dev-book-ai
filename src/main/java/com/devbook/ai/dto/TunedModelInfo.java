package com.devbook.ai.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class TunedModelInfo {
	public TunedModelInfo() {
	}

	@Override
	public String toString() {
		return "TunedModelInfo{}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		return o != null && getClass() == o.getClass();
	}

	@Override
	public int hashCode() {
		return 0;
	}
}