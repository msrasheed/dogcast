package com.nexxcast.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

public class HttpUtility {
	
	public static String httpGetGetContent(String url, Properties headers, Properties parameters) 
			throws ClientProtocolException, IOException, URISyntaxException {
		
		ResponseHandler<String> rh = new ResponseHandler<String>() {
			@Override
			public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				StatusLine statusLine = response.getStatusLine();
				HttpEntity entity = response.getEntity();
				if (statusLine.getStatusCode() >= 300) {
					throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
				}
				if (entity == null) {
					throw new ClientProtocolException("Response contains no content");
				}
				ContentType contentType = ContentType.getOrDefault(entity);
				Charset charset = contentType.getCharset();
				if (charset == null) {
					charset = Charset.forName("UTF-8");
				}
				Reader reader = new InputStreamReader(entity.getContent(), charset);
				StringBuilder strBuild = new StringBuilder();
				int numCharsRead;
				char[] arr = new char[8 * 1024];
				while ((numCharsRead = reader.read(arr, 0, arr.length)) != -1) {
					strBuild.append(arr, 0, numCharsRead);
				}
				return strBuild.toString();
			}
		};
		
		return httpGetBase(url, headers, parameters, rh);
	}
	
	public static <T> T httpGetBase(String url, Properties headers, Properties parameters, ResponseHandler<T> rh) 
			throws ClientProtocolException, IOException, URISyntaxException {
		
		URIBuilder uriBuilder = new URIBuilder(url);
		Enumeration<String> paramEnums = (Enumeration<String>) parameters.propertyNames();
		while (paramEnums.hasMoreElements()) {
			String param = paramEnums.nextElement();
			uriBuilder.addParameter(param, parameters.getProperty(param));
		}
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		
		Enumeration<String> headEnums = (Enumeration<String>) headers.propertyNames();
		while (headEnums.hasMoreElements()) {
			String param = headEnums.nextElement();
			httpGet.addHeader(param, headers.getProperty(param));
		}
		
		return httpclient.execute(httpGet, rh);
	}
	
	public static <T> T httpPostGetObject(final Class<T> type, String url, Properties headers, Properties parameters) 
			throws ClientProtocolException, IOException {
		
		ResponseHandler<T> rh = new ResponseHandler<T>() {
			@Override
			public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				StatusLine statusLine = response.getStatusLine();
				HttpEntity entity = response.getEntity();
				if (statusLine.getStatusCode() >= 300) {
					throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
				}
				if (entity == null) {
					throw new ClientProtocolException("Response contains no content");
				}
				Gson gson = new Gson();
				ContentType contentType = ContentType.getOrDefault(entity);
				Charset charset = contentType.getCharset();
				if (charset == null) {
					charset = Charset.forName("UTF-8");
				}
				Reader reader = new InputStreamReader(entity.getContent(), charset);
				return gson.fromJson(reader, type);
			}
		};
		
		return httpPostBase(url, headers, parameters, rh);
	}
	
	public static String httpPostGetContent(String url, Properties headers, Properties parameters) 
			throws ClientProtocolException, IOException {
		
		ResponseHandler<String> rh = new ResponseHandler<String>() {
			@Override
			public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				StatusLine statusLine = response.getStatusLine();
				HttpEntity entity = response.getEntity();
				if (statusLine.getStatusCode() >= 300) {
					throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
				}
				if (entity == null) {
					throw new ClientProtocolException("Response contains no content");
				}
				ContentType contentType = ContentType.getOrDefault(entity);
				Charset charset = contentType.getCharset();
				if (charset == null) {
					charset = Charset.forName("UTF-8");
				}
				Reader reader = new InputStreamReader(entity.getContent(), charset);
				StringBuilder strBuild = new StringBuilder();
				int numCharsRead;
				char[] arr = new char[8 * 1024];
				while ((numCharsRead = reader.read(arr, 0, arr.length)) != -1) {
					strBuild.append(arr, 0, numCharsRead);
				}
				return strBuild.toString();
			}
		};
		
		return httpPostBase(url, headers, parameters, rh);
	}
	
	public static <T> T httpPostBase(String url, Properties headers, Properties parameters, ResponseHandler<T> rh) 
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		
		Enumeration<String> headEnums = (Enumeration<String>) headers.propertyNames();
		while (headEnums.hasMoreElements()) {
			String param = headEnums.nextElement();
			httpPost.addHeader(param, headers.getProperty(param));
		}
		
		ArrayList<NameValuePair> postParams = new ArrayList<NameValuePair>();
		Enumeration<String> paramEnums = (Enumeration<String>) parameters.propertyNames();
		while (paramEnums.hasMoreElements()) {
			String param = paramEnums.nextElement();
			postParams.add(new BasicNameValuePair(param, parameters.getProperty(param)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(postParams, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return httpclient.execute(httpPost, rh);
	}
}
