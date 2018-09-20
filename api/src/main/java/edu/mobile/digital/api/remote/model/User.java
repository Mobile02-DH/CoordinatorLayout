package edu.mobile.digital.api.remote.model;

import com.google.gson.annotations.SerializedName;

import edu.mobile.digital.api.remote.BaseResponse;

public class User extends BaseResponse {

	@SerializedName("api_key")
	String apiKey;

	public String getApiKey() {
		return apiKey;
	}
}
