package com.nexxcast.spotify;

public class ClientCredentials {
	private String access_token;
	private String token_type;
	private int expires_in;
	
	public ClientCredentials() {
		super();
	}

	public String getAccess_token() {
		return access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public int getExpires_in() {
		return expires_in;
	}
}
