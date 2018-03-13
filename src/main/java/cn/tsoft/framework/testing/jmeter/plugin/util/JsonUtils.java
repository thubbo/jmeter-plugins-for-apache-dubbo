package cn.tsoft.framework.testing.jmeter.plugin.util;

import java.lang.reflect.Type;

import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class JsonUtils {

	private static final Logger logger = LoggingManager.getLoggerForClass();

	private static final Gson gson = new GsonBuilder().serializeNulls().create();

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static String toJson(Object obj, Type type) {
		return gson.toJson(obj, type);
	}

	public static <T> T formJson(String json, Class<T> classOfT) {
		try {
			return gson.fromJson(json, classOfT);
		} catch (JsonSyntaxException e) {
			logger.error("json to class[" + classOfT.getName() + "] is error!",
					e);
		}
		return null;
	}

	public static <T> T formJson(String json, Type type) {
		try {
			return gson.fromJson(json, type);
		} catch (JsonSyntaxException e) {
			logger.error("json to class[" + type.getClass().getName()
					+ "] is error!", e);
		}
		return null;
	}
}
