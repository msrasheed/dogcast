package com.nexxcast.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyGsonBuilder {
	public static Gson getGson() {
		return new GsonBuilder().setExclusionStrategies(new GsonExcludeAnnotationStrategy()).create();
	}
}
