package com.nexxcast.spotify;

public class SpotifyUserCredentials {
	private String access_token;
	private String token_type;
	private String scope;
	private int expires_in;
	private String refresh_token;
	private transient long timeMade;
	
	public SpotifyUserCredentials() {
		super();
		this.timeMade = System.currentTimeMillis();
	}

	public String getAccess_token() {
		return access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public String getScope() {
		return scope;
	}
	
	public int getExpires_in() {
		return expires_in;
	}
	
	public String getRefresh_token() {
		return refresh_token;
	}
	
	public long getTimeMade() {
		return this.timeMade;
	}
}
