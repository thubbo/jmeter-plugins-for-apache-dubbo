package cn.tsoft.framework.testing.jmeter.plugin.dubbo.sample;

import java.io.Serializable;

import cn.tsoft.framework.testing.jmeter.plugin.util.JsonUtils;

public class MethodArgument implements Serializable {

	private static final long serialVersionUID = -2567457932227227262L;
	private String paramType;
	private String paramValue;

	public MethodArgument(String paramType, String paramValue) {
		this.paramType = paramType;
		this.paramValue = paramValue;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}
