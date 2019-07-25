package com.nexxcast.spotify;

import java.util.Properties;

import com.nexxcast.utils.HttpUtility;

public class SpotifyAppClient {
	
	public static final String clientID = "ca4e95b4e5f94250924ffcae8930c92c";
	public static final String clientSecret = "50a694895f0f49a4ab37e80becff67bb";
	public static final String clikey = "Y2E0ZTk1YjRlNWY5NDI1MDkyNGZmY2FlODkzMGM5MmM6NTBhNjk0ODk1ZjBmNDlhNGFiMzdlODBiZWNmZjY3YmI=";
	
	private static ClientCredentials clientCreds = null;
	
	public static ClientCredentials getClientCredentials() {
		if (clientCreds == null || ((System.currentTimeMillis() - clientCreds.getTimeMade()) / 1000) > clientCreds.getExpires_in()) {
			if (clientCreds == null) clientCreds = new ClientCredentials();
			synchronized (clientCreds) {
				Properties headers = new Properties();
				headers.setProperty("Authorization", "Basic " + clikey);
				
				Properties params = new Properties();
				params.setProperty("grant_type", "client_credentials");
				
				String url = "https://accounts.spotify.com/api/token";
				
				try {
					clientCreds = HttpUtility.httpPostGetObject(ClientCredentials.class, url, headers, params);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//System.out.println("access_token " + clientCreds.getAccess_token());
				//System.out.println("token type " + clientCreds.getToken_type());
				//System.out.println("expires_in " + clientCreds.getExpires_in());
			}
		}
		return clientCreds;
	}
	
	public static String getAccessToken() {
		return getClientCredentials().getAccess_token();
	}
	
	public static String getTokenType() {
		return getClientCredentials().getToken_type();
	}
}
