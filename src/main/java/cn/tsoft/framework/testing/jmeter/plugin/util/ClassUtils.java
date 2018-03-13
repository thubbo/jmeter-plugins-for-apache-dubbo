package cn.tsoft.framework.testing.jmeter.plugin.util;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

import cn.tsoft.framework.testing.jmeter.plugin.dubbo.sample.MethodArgument;

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

	public static void parseParameter(Type type,
			List<Object> parameterValuesList, MethodArgument arg)
			throws ClassNotFoundException {
		String className = getClassName(type);
		if (className.equals("int")) {
			parameterValuesList.add(Integer.parseInt(arg.getParamValue()));
		} else if (className.equals("double")) {
			parameterValuesList.add(Double.parseDouble(arg.getParamValue()));
		} else if (className.equals("short")) {
			parameterValuesList.add(Short.parseShort(arg.getParamValue()));
		} else if (className.equals("float")) {
			parameterValuesList.add(Float.parseFloat(arg.getParamValue()));
		} else if (className.equals("long")) {
			parameterValuesList.add(Long.parseLong(arg.getParamValue()));
		} else if (className.equals("byte")) {
			parameterValuesList.add(Byte.parseByte(arg.getParamValue()));
		} else if (className.equals("boolean")) {
			parameterValuesList.add(Boolean.parseBoolean(arg.getParamValue()));
		} else if (className.equals("char")) {
			parameterValuesList.add(arg.getParamValue().charAt(0));
		} else if (className.equals("java.lang.String")
				|| className.equals("String") || className.equals("string")) {
			parameterValuesList.add(String.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Integer")
				|| className.equals("Integer") || className.equals("integer")) {
			parameterValuesList.add(Integer.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Double")
				|| className.equals("Double")) {
			parameterValuesList.add(Double.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Short")
				|| className.equals("Short")) {
			parameterValuesList.add(Short.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Long")
				|| className.equals("Long")) {
			parameterValuesList.add(Long.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Float")
				|| className.equals("Float")) {
			parameterValuesList.add(Float.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Byte")
				|| className.equals("Byte")) {
			parameterValuesList.add(Byte.valueOf(arg.getParamValue()));
		} else if (className.equals("java.lang.Boolean")
				|| className.equals("Boolean")) {
			parameterValuesList.add(Boolean.valueOf(arg.getParamValue()));
		} else {
			parameterValuesList.add(JsonUtils.formJson(arg.getParamValue(),
					type));
		}
	}
}
