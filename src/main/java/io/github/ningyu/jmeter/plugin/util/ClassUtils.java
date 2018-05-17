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

import io.github.ningyu.jmeter.plugin.dubbo.sample.MethodArgument;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;

/**
 * ClassUtils
 */
public class ClassUtils {

	private static final String TYPE_NAME_PREFIX = "class ";

	public static String getClassName(Type type) {
		if (type == null) {
			return "";
		}
		String className = type.toString();
		if (className.startsWith(TYPE_NAME_PREFIX)) {
			className = className.substring(TYPE_NAME_PREFIX.length());
		}
		return className;
	}

	@SuppressWarnings("rawtypes")
	public static String[] getMethodParamType(String interfaceName,
			String methodName) {
		try {
			// 创建类
			Class<?> class1 = Class.forName(interfaceName);
			// 获取所有的公共的方法
			Method[] methods = class1.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] paramClassList = method.getParameterTypes();
					String[] paramTypeList = new String[paramClassList.length];
					int i = 0;
					for (Class className : paramClassList) {
						paramTypeList[i] = className.getName();
						i++;
					}
					return paramTypeList;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	@SuppressWarnings("serial")
	public static void parseParameter(List<String> paramterTypeList, 
			List<Object> parameterValuesList, MethodArgument arg) {
		String className = arg.getParamType();
		if (className.equals("int")) {
			paramterTypeList.add(arg.getParamType());
			parameterValuesList.add(Integer.parseInt(arg.getParamValue()));
		} else if (className.equals("double")) {
			paramterTypeList.add(arg.getParamType());
			parameterValuesList.add(Double.parseDouble(arg.getParamValue()));
		} else if (className.equals("short")) {
			paramterTypeList.add(arg.getParamType());
			parameterValuesList.add(Short.parseShort(arg.getParamValue()));
		} else if (className.equals("float")) {
			paramterTypeList.add(arg.getParamType());
			parameterValuesList.add(Float.parseFloat(arg.getParamValue()));
		} else if (className.equals("long")) {
			paramterTypeList.add(arg.getParamType());
			parameterValuesList.add(Long.parseLong(arg.getParamValue()));
		} else if (className.equals("byte")) {
			paramterTypeList.add(arg.getParamType());
			parameterValuesList.add(Byte.parseByte(arg.getParamValue()));
		} else if (className.equals("boolean")) {
			paramterTypeList.add(arg.getParamType());
			parameterValuesList.add(Boolean.parseBoolean(arg.getParamValue()));
		} else if (className.equals("char")) {
			paramterTypeList.add(arg.getParamType());
			parameterValuesList.add(arg.getParamValue().charAt(0));
		} else if (className.equals("java.lang.String")
				|| className.equals("String") || className.equals("string")) {
			paramterTypeList.add("java.lang.String");
			parameterValuesList.add(String.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Integer")
				|| className.equals("Integer") || className.equals("integer")) {
			paramterTypeList.add("java.lang.Integer");
			parameterValuesList.add(Integer.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Double")
				|| className.equals("Double")) {
			paramterTypeList.add("java.lang.Double");
			parameterValuesList.add(Double.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Short")
				|| className.equals("Short")) {
			paramterTypeList.add("java.lang.Short");
			parameterValuesList.add(Short.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Long")
				|| className.equals("Long")) {
			paramterTypeList.add("java.lang.Long");
			parameterValuesList.add(Long.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Float")
				|| className.equals("Float")) {
			paramterTypeList.add("java.lang.Float");
			parameterValuesList.add(Float.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Byte")
				|| className.equals("Byte")) {
			paramterTypeList.add("java.lang.Byte");
			parameterValuesList.add(Byte.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Boolean")
				|| className.equals("Boolean")) {
			paramterTypeList.add("java.lang.Boolean");
			parameterValuesList.add(Boolean.valueOf(arg.getParamValue()));
		} else {
			try {
				Class<?> clazz = Class.forName(className);
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(JsonUtils.formJson(arg.getParamValue(), clazz));
			} catch (ClassNotFoundException e) {
				//不是jdk或者lib下的类，使用通用map格式反序列化值
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(JsonUtils.formJson(arg.getParamValue(), new TypeToken<Map<String,Object>>() {}.getType()));
			}
		}
	}
}
