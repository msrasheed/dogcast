package com.nexxcast.utils;

public class JsonConstructor {
	private String retVal;
	
	public JsonConstructor() {
		this.retVal = "{";
	}
	
	public void addKeyValuePair(String key, String val) {
		retVal += "\"" + key + "\":";
		retVal += "\"" + val + "\",";
	}
	
	public String getJson() {
		retVal = retVal.substring(0, retVal.length() - 1);
		retVal += "}";
		return retVal;
	}
}
