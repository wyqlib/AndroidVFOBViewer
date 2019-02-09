package com.wyqlib.java.utils.io.serialization.Json;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

public class GsonUtils {
	// Gson-------------------------------------------------------------------------------------------------------------
	public static String jsonStringSerialize(Object o) {
		try {
			Gson gson = new Gson();
			return gson.toJson(o);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T jsonStringDeserialize(Class<T> t, String jsonData) {
		try {
			Gson gson = new Gson();
			return gson.fromJson(jsonData, t);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T jsonFileDeserialize(Class<T> t, String jsonFile) {
		try {
			String jsonData = FileUtils.readFileToString(new File(jsonFile), "UTF-8");
			Gson gson = new Gson();
			return gson.fromJson(jsonData, t);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void jsonFileSerialize(Object o, String jsonFile) {
		try {
			Gson gson = new Gson();
			FileUtils.writeStringToFile(new File(jsonFile), gson.toJson(o), "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
