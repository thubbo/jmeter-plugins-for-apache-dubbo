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

import com.google.common.reflect.TypeToken;
import io.github.ningyu.jmeter.plugin.dubbo.sample.MethodArgument;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

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
	
	public static boolean isBlank(String paramValue) {
		if (StringUtils.isBlank(paramValue) || "null".equals(paramValue.toLowerCase())) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("serial")
	public static void parseParameter(List<String> paramterTypeList, 
			List<Object> parameterValuesList, MethodArgument arg) {
		try {
			String className = arg.getParamType();
			if (className.equals("int")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.INT_DEFAULT : Integer.parseInt(arg.getParamValue()));
			} else if(className.equals("int[]")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.INT_ARRAY_DEFAULT : JsonUtils.formJson(arg.getParamValue(), new TypeToken<int[]>() {}.getType()));
			} else if (className.equals("double")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.DOUBLE_DEFAULT : Double.parseDouble(arg.getParamValue()));
			}  else if (className.equals("double[]")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.DOUBLE_ARRAY_DEFAULT : JsonUtils.formJson(arg.getParamValue(), new TypeToken<double[]>() {}.getType()));
			} else if (className.equals("short")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.SHORT_DEFAULT : Short.parseShort(arg.getParamValue()));
			} else if (className.equals("short[]")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.SHORT_ARRAY_DEFAULT : JsonUtils.formJson(arg.getParamValue(), new TypeToken<short[]>() {}.getType()));
			} else if (className.equals("float")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.FLOAT_DEFAULT : Float.parseFloat(arg.getParamValue()));
			} else if (className.equals("float[]")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.FLOAT_ARRAY_DEFAULT : JsonUtils.formJson(arg.getParamValue(), new TypeToken<float[]>() {}.getType()));
			} else if (className.equals("long")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.LONG_DEFAULT : Long.parseLong(arg.getParamValue()));
			} else if (className.equals("long[]")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.LONG_ARRAY_DEFAULT : JsonUtils.formJson(arg.getParamValue(), new TypeToken<long[]>() {}.getType()));
			} else if (className.equals("byte")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.BYTE_DEFAULT : Byte.parseByte(arg.getParamValue()));
			} else if (className.equals("byte[]")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.BYTE_ARRAY_DEFAULT : JsonUtils.formJson(arg.getParamValue(), new TypeToken<byte[]>() {}.getType()));
			} else if (className.equals("boolean")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.BOOLEAN_DEFAULT : Boolean.parseBoolean(arg.getParamValue()));
			} else if (className.equals("boolean[]")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.BOOLEAN_ARRAY_DEFAULT : JsonUtils.formJson(arg.getParamValue(), new TypeToken<boolean[]>() {}.getType()));
			} else if (className.equals("char")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.CHAR_DEFAULT : arg.getParamValue().charAt(0));
			} else if (className.equals("char[]")) {
				paramterTypeList.add(arg.getParamType());
				parameterValuesList.add(isBlank(arg.getParamValue()) ? Constants.CHAT_ARRAY_DEFAULT : JsonUtils.formJson(arg.getParamValue(), new TypeToken<char[]>() {}.getType()));
			} else if (className.equals("java.lang.String")
					|| className.equals("String") 
					|| className.equals("string")) {
				paramterTypeList.add("java.lang.String");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : String.valueOf(arg.getParamValue()));
			} else if (className.equals("java.lang.String[]")
					|| className.equals("String[]") 
					|| className.equals("string[]")) {
				paramterTypeList.add("java.lang.String[]");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : JsonUtils.formJson(arg.getParamValue(), new TypeToken<String[]>() {}.getType()));
			} else if (className.equals("java.lang.Integer")
					|| className.equals("Integer") 
					|| className.equals("integer")) {
				paramterTypeList.add("java.lang.Integer");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : Integer.valueOf(arg.getParamValue()));
			} else if (className.equals("java.lang.Integer[]")
					|| className.equals("Integer[]") 
					|| className.equals("integer[]")) {
				paramterTypeList.add("java.lang.Integer[]");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : JsonUtils.formJson(arg.getParamValue(), new TypeToken<Integer[]>() {}.getType()));
			} else if (className.equals("java.lang.Double")
					|| className.equals("Double")) {
				paramterTypeList.add("java.lang.Double");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : Double.valueOf(arg.getParamValue()));
			} else if (className.equals("java.lang.Double[]")
					|| className.equals("Double[]")) {
				paramterTypeList.add("java.lang.Double[]");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : JsonUtils.formJson(arg.getParamValue(), new TypeToken<Double[]>() {}.getType()));
			} else if (className.equals("java.lang.Short")
					|| className.equals("Short")) {
				paramterTypeList.add("java.lang.Short");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : Short.valueOf(arg.getParamValue()));
			} else if (className.equals("java.lang.Short[]")
					|| className.equals("Short[]")) {
				paramterTypeList.add("java.lang.Short[]");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : JsonUtils.formJson(arg.getParamValue(), new TypeToken<Short[]>() {}.getType()));
			} else if (className.equals("java.lang.Long")
					|| className.equals("Long")) {
				paramterTypeList.add("java.lang.Long");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : Long.valueOf(arg.getParamValue()));
			} else if(className.equals("java.lang.Long[]")
					|| className.equals("Long[]")) {
				paramterTypeList.add("java.lang.Long[]");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : JsonUtils.formJson(arg.getParamValue(), new TypeToken<Long[]>() {}.getType()));
			} else if (className.equals("java.lang.Float")
					|| className.equals("Float")) {
				paramterTypeList.add("java.lang.Float");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : Float.valueOf(arg.getParamValue()));
			} else if (className.equals("java.lang.Float[]")
					|| className.equals("Float[]")) {
				paramterTypeList.add("java.lang.Float[]");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : JsonUtils.formJson(arg.getParamValue(), new TypeToken<Float[]>() {}.getType()));
			} else if (className.equals("java.lang.Byte")
					|| className.equals("Byte")) {
				paramterTypeList.add("java.lang.Byte");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : Byte.valueOf(arg.getParamValue()));
			} else if (className.equals("java.lang.Byte[]")
					|| className.equals("Byte[]")) {
				paramterTypeList.add("java.lang.Byte[]");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : JsonUtils.formJson(arg.getParamValue(), new TypeToken<Byte[]>() {}.getType()));
			} else if (className.equals("java.lang.Boolean")
					|| className.equals("Boolean")) {
				paramterTypeList.add("java.lang.Boolean");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : Boolean.valueOf(arg.getParamValue()));
			} else if (className.equals("java.lang.Boolean[]")
					|| className.equals("Boolean[]")) {
				paramterTypeList.add("java.lang.Boolean[]");
				parameterValuesList.add(isBlank(arg.getParamValue()) ? null : JsonUtils.formJson(arg.getParamValue(), new TypeToken<Boolean[]>() {}.getType()));
			} else {
				if (className.endsWith("[]")) {
					List<?> list = null;
					if (!isBlank(arg.getParamValue())) {
						list = JsonUtils.formJson(arg.getParamValue(), new TypeToken<List<?>>() {}.getType());
					}
					paramterTypeList.add(arg.getParamType());
					parameterValuesList.add(list == null ? null : list.toArray());
				} else {
					try {
						Class<?> clazz = Class.forName(className);
						paramterTypeList.add(arg.getParamType());
						parameterValuesList.add(isBlank(arg.getParamValue()) ? null : JsonUtils.formJson(arg.getParamValue(), clazz));
					} catch (ClassNotFoundException e) {
						//不是jdk或者lib下的类，使用通用map格式反序列化值
						paramterTypeList.add(arg.getParamType());
						parameterValuesList.add(isBlank(arg.getParamValue()) ? null : JsonUtils.formJson(arg.getParamValue(), new TypeToken<HashMap<String,Object>>() {}.getType()));
					}
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid parameter => [ParamType="+arg.getParamType()+",ParamValue="+arg.getParamValue()+"]", e);
		}
	}
}
