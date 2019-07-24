package com.nexxcast.spotify;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

public class SpotifyClientCredentials {
	public static final String clientID = "ca4e95b4e5f94250924ffcae8930c92c";
	public static final String clientSecret = "50a694895f0f49a4ab37e80becff67bb";
	public static final String clikey = "Y2E0ZTk1YjRlNWY5NDI1MDkyNGZmY2FlODkzMGM5MmM6NTBhNjk0ODk1ZjBmNDlhNGFiMzdlODBiZWNmZjY3YmI=";
	
	public static ClientCredentials getClientCredentials() throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://accounts.spotify.com/api/token");
		
		String clientID64 = Base64.getEncoder().encodeToString(clientID.getBytes());
		String clientSecret64 = Base64.getEncoder().encodeToString(clientSecret.getBytes());
		String clientKeyStr = clientID + ":" + clientSecret;
		String clientKey = Base64.getEncoder().encodeToString(clientKeyStr.getBytes());
		//System.out.println(clientID64);
		//System.out.println(clientSecret64);
		//System.out.println(clientKey);
		String authValue = "Basic " + clientKey;
		httpPost.addHeader("Authorization", authValue);
		
		ArrayList<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "client_credentials"));
		httpPost.setEntity(new UrlEncodedFormEntity(postParams, "UTF-8"));
		
		ResponseHandler<ClientCredentials> rh = new ResponseHandler<ClientCredentials>() {

			@Override
			public ClientCredentials handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				// TODO Auto-generated method stub
				StatusLine statusLine = response.getStatusLine();
				HttpEntity entity = response.getEntity();
				if (statusLine.getStatusCode() >= 300) {
					throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
				}
				if (entity == null) {
					throw new ClientProtocolException("Response containse no content");
				}
				Gson gson = new Gson();
				ContentType contentType = ContentType.getOrDefault(entity);
				Charset charset = contentType.getCharset();
				if (charset == null) {
					charset = Charset.forName("UTF-8");
				}
				Reader reader = new InputStreamReader(entity.getContent(), charset);
				return gson.fromJson(reader, ClientCredentials.class);
			}
			
		};
		
		ClientCredentials cliCreds = httpclient.execute(httpPost, rh);
		
		System.out.println(cliCreds.getAccess_token());
		return cliCreds;
	}
}
