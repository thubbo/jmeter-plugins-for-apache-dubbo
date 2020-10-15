/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.ningyu.jmeter.plugin.util;

import com.google.gson.*;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * JsonUtils
 */
public class JsonUtils {

	private static final Logger logger = LoggingManager.getLoggerForClass();

	private static final Gson gson = new GsonBuilder()
			.setDateFormat(Constants.DATE_FORMAT)
			.setPrettyPrinting()
			.disableHtmlEscaping()
			.serializeNulls()
			.registerTypeAdapter(Locale.class, new JsonDeserializer<Locale>() {
				@Override
				public Locale deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
					return ClassUtils.parseLocale(json.getAsJsonPrimitive().getAsString());
				}
			})
			.registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
				@Override
				public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
					return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));
				}
			})
			.registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
				@Override
				public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
					LocalDateTime localDateTime = LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));
					return localDateTime.toLocalDate();
				}
			})
			.registerTypeAdapter(LocalTime.class, new JsonDeserializer<LocalTime>() {
				@Override
				public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
					LocalDateTime localDateTime = LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));
					return localDateTime.toLocalTime();
				}
			})
			.create();

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
