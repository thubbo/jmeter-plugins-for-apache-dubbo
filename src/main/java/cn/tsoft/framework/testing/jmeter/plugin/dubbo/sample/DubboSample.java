/*
 * Copyright (c) 2018, Tsoft and/or its affiliates. All rights reserved.
 * FileName: DubboSample.java
 * Author:   ningyu
 * Date:     2018年2月8日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.testing.jmeter.plugin.dubbo.sample;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.property.IntegerProperty;
import org.apache.jmeter.testelement.property.StringProperty;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * <功能描述>
 * 
 * @author ningyu
 * @date 2018年2月8日 上午10:09:41
 */
public class DubboSample extends AbstractSampler {
    
    private static final Logger log = LoggingManager.getLoggerForClass();


    /**
     * 
     */
    private static final long serialVersionUID = -6794913295411458705L;
    
    public static String FIELD_DUBBO_PROTOCOL = "FIELD_DUBBO_PROTOCOL";
    public static String FIELD_DUBBO_ADDRESS = "FIELD_DUBBO_ADDRESS";
    public static String FIELD_DUBBO_TIMEOUT = "FIELD_DUBBO_TIMEOUT";
    public static String FIELD_DUBBO_VERSION = "FIELD_DUBBO_VERSION";
    public static String FIELD_DUBBO_RETRIES = "FIELD_DUBBO_RETRIES";
    public static String FIELD_DUBBO_CLUSTER = "FIELD_DUBBO_CLUSTER";
    public static String FIELD_DUBBO_INTERFACE = "FIELD_DUBBO_INTERFACE";
    public static String FIELD_DUBBO_METHOD = "FIELD_DUBBO_METHOD";
    public static String FIELD_DUBBO_METHOD_ARGS = "FIELD_DUBBO_METHOD_ARGS";
    public static String FIELD_DUBBO_METHOD_ARGS_SIZE = "FIELD_DUBBO_METHOD_ARGS_SIZE";
    public static String DEFAULT_TIMEOUT = "1200000";
    public static String DEFAULT_VERSION = "1.0.0";
    public static String DEFAULT_RETRIES = "0";
    public static String DEFAULT_CLUSTER = "failfast";

    /**
     * 获取 protocol
     * @return the protocol
     */
    public String getProtocol() {
        return this.getPropertyAsString(FIELD_DUBBO_PROTOCOL);
    }

    /**
     * 设置 protocol
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.setProperty(new StringProperty(FIELD_DUBBO_PROTOCOL, protocol));
    }

    /**
     * 获取 address
     * @return the address
     */
    public String getAddress() {
        return this.getPropertyAsString(FIELD_DUBBO_ADDRESS);
    }

    /**
     * 设置 address
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.setProperty(new StringProperty(FIELD_DUBBO_ADDRESS, address));
    }

    /**
     * 获取 timeout
     * @return the timeout
     */
    public String getTimeout() {
        return this.getPropertyAsString(FIELD_DUBBO_TIMEOUT, DEFAULT_TIMEOUT);
    }

    /**
     * 设置 timeout
     * @param timeout the timeout to set
     */
    public void setTimeout(String timeout) {
        this.setProperty(new StringProperty(FIELD_DUBBO_TIMEOUT, timeout));
    }

    /**
     * 获取 version
     * @return the version
     */
    public String getVersion() {
        return this.getPropertyAsString(FIELD_DUBBO_VERSION, DEFAULT_VERSION);
    }

    /**
     * 设置 version
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.setProperty(new StringProperty(FIELD_DUBBO_VERSION, version));
    }
    
    /**
     * 获取 retries
     * @return the retries
     */
    public String getRetries() {
        return this.getPropertyAsString(FIELD_DUBBO_RETRIES, DEFAULT_RETRIES);
    }

    /**
     * 设置 retries
     * @param retries the retries to set
     */
    public void setRetries(String retries) {
        this.setProperty(new StringProperty(FIELD_DUBBO_RETRIES, retries));
    }
    
    /**
     * 获取 cluster
     * @return the cluster
     */
    public String getCluster() {
        return this.getPropertyAsString(FIELD_DUBBO_CLUSTER, DEFAULT_CLUSTER);
    }

    /**
     * 设置 cluster
     * @param cluster the cluster to set
     */
    public void setCluster(String cluster) {
        this.setProperty(new StringProperty(FIELD_DUBBO_CLUSTER, cluster));
    }

    /**
     * 获取 interfaceName
     * @return the interfaceName
     */
    public String getInterface() {
        return this.getPropertyAsString(FIELD_DUBBO_INTERFACE);
    }

    /**
     * 设置 interfaceName
     * @param interfaceName the interfaceName to set
     */
    public void setInterfaceName(String interfaceName) {
        this.setProperty(new StringProperty(FIELD_DUBBO_INTERFACE, interfaceName));
    }

    /**
     * 获取 method
     * @return the method
     */
    public String getMethod() {
        return this.getPropertyAsString(FIELD_DUBBO_METHOD);
    }

    /**
     * 设置 method
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.setProperty(new StringProperty(FIELD_DUBBO_METHOD, method));
    }

    /**
     * 获取 methodArgs
     * @return the methodArgs
     */
    public List<MethodArgument> getMethodArgs() {
    	int paramsSize = this.getPropertyAsInt(FIELD_DUBBO_METHOD_ARGS_SIZE, 0);
    	List<MethodArgument> list = new ArrayList<MethodArgument>();
		for (int i = 1; i <= paramsSize; i++) {
			String paramType = this.getPropertyAsString(FIELD_DUBBO_METHOD_ARGS + "_PARAM_TYPE" + i);
			String paramValue = this.getPropertyAsString(FIELD_DUBBO_METHOD_ARGS + "_PARAM_VALUE" + i);
			MethodArgument args = new MethodArgument(paramType, paramValue);
			list.add(args);
		}
    	return list;
    }

    /**
     * 设置 methodArgs
     * @param methodArgs the methodArgs to set
     */
    public void setMethodArgs(List<MethodArgument> methodArgs) {
    	int size = methodArgs == null ? 0 : methodArgs.size();
    	this.setProperty(new IntegerProperty(FIELD_DUBBO_METHOD_ARGS_SIZE, size));
    	if (size > 0) {
    		for (int i = 1; i <= methodArgs.size(); i++) {
    			this.setProperty(new StringProperty(FIELD_DUBBO_METHOD_ARGS + "_PARAM_TYPE" + i, methodArgs.get(i-1).getParamType()));
    			this.setProperty(new StringProperty(FIELD_DUBBO_METHOD_ARGS + "_PARAM_VALUE" + i, methodArgs.get(i-1).getParamValue()));
    		}
    	}
    }

    @SuppressWarnings("deprecation")
	@Override
    public SampleResult sample(Entry entry) {
        SampleResult res = new SampleResult();
        res.setSampleLabel("DubboResult");
        res.sampleStart();
        //构造请求数据
        res.setSamplerData(getSampleData());
        //调用dubbo
        res.setResponseData(toJson(callDubbo(res)));
        //构造响应数据
        res.setDataType(SampleResult.TEXT);
        res.setResponseCodeOK();
        res.setResponseMessageOK();
        res.sampleEnd();
        return res;
    }

    /**
     * 构造请求数据
     * 
     * @author ningyu
     * @date 2018年2月9日 上午9:58:24
     *
     * @return
     */
    private String getSampleData() {
    	StringBuilder sb = new StringBuilder();
        sb.append("Protocol: ").append(getProtocol()).append("\n");
        sb.append("Address: ").append(getAddress()).append("\n");
        sb.append("Timeout: ").append(getTimeout()).append("\n");
        sb.append("Version: ").append(getVersion()).append("\n");
        sb.append("Retries: ").append(getRetries()).append("\n");
        sb.append("Cluster: ").append(getCluster()).append("\n");
        sb.append("Interface: ").append(getInterface()).append("\n");
        sb.append("Method: ").append(getMethod()).append("\n");
        sb.append("Method Args: ").append(getMethodArgs().toString());
        return sb.toString();
    }
    
    @SuppressWarnings({"unchecked", "rawtypes", "static-access"})
    private Object callDubbo(SampleResult res) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("DubboSample");
        
        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig reference = new ReferenceConfig();
        // 引用远程服务
        reference.setApplication(application);
        RegistryConfig registry = null;
        
        String protocol = getProtocol();
        if ("zookeeper".equals(protocol)) {
            // 连接注册中心配置
            registry = new RegistryConfig();
            registry.setProtocol("zookeeper");
            registry.setAddress(getAddress());
            reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append(protocol).append("://").append(getAddress()).append("/").append(getInterface());
            log.info("rpc invoker url : " + sb.toString());
            reference.setUrl(sb.toString());
        }
        try {
            Class clazz = Class.forName(getInterface());
            reference.setInterface(clazz);
            reference.setRetries(Integer.valueOf(getRetries()));
            reference.setCluster(getCluster());
            reference.setVersion(getVersion());
            reference.setTimeout(Integer.valueOf(getTimeout()));
            Object target = reference.get();
            Method method = null;
            Class[] parameterTypes = null;
            Object[] parameterValues = null;
            
            List<Class> parameterTypesList = new ArrayList<Class>();
            List<Object> parameterValuesList = new ArrayList<Object>();
            List<MethodArgument> args = getMethodArgs();
            if (args.size() > 0) {
                //处理参数
                Iterator<MethodArgument> it = args.iterator();
                while(it.hasNext()) {
                	MethodArgument param = it.next();
                    String paramType = param.getParamType();
                    String paramValue = param.getParamValue();
                    log.info("paramValue:"+paramValue);
                    if (null == paramType || "".equals(paramType.trim())) {
                        continue;
                    } else {
                        if (paramType.equals("int")) {
                            parameterTypesList.add(int.class);
                            parameterValuesList.add(Integer.parseInt(paramValue));
                        } else if (paramType.equals("double")) {
                            parameterTypesList.add(double.class);
                            parameterValuesList.add(Double.parseDouble(paramValue));
                        } else if (paramType.equals("short")) {
                            parameterTypesList.add(short.class);
                            parameterValuesList.add(Short.parseShort(paramValue));
                        } else if (paramType.equals("float")) {
                            parameterTypesList.add(float.class);
                            parameterValuesList.add(Float.parseFloat(paramValue));
                        } else if (paramType.equals("long")) {
                            parameterTypesList.add(long.class);
                            parameterValuesList.add(Long.parseLong(paramValue));
                        } else if (paramType.equals("byte")) {
                            parameterTypesList.add(byte.class);
                            parameterValuesList.add(Byte.parseByte(paramValue));
                        } else if (paramType.equals("boolean")) {
                            parameterTypesList.add(boolean.class);
                            parameterValuesList.add(Boolean.parseBoolean(paramValue));
                        } else if (paramType.equals("char")) {
                            parameterTypesList.add(char.class);
                            parameterValuesList.add(paramValue.charAt(0));
                        } else {
                            Class c = Class.forName(paramType);
                            parameterTypesList.add(c);
                            parameterValuesList.add(formJson(paramValue, c));
                        }
                    }
                }
                parameterTypes = new Class[parameterTypesList.size()];
                parameterTypesList.toArray(parameterTypes);
                if (parameterTypes.length > 0) {
                    method = target.getClass().getMethod(getMethod(), parameterTypes);
                } else {
                    method = target.getClass().getMethod(getMethod(), null);
                }
                
            } else {
                method = target.getClass().getMethod(getMethod(), null);
            }
            if (method == null) {
                res.setSuccessful(false);
                return null;
            }
            //发起调用
            parameterValues = new Object[parameterValuesList.size()];
            parameterValuesList.toArray(parameterValues);
            Object result = method.invoke(target, parameterValues);
            res.setSuccessful(true);
            return result;
        } catch (Exception e) {
            log.error("调用dubbo接口出错：", e);
            res.setSuccessful(false);
        } finally {
            if (registry != null) {
                registry.destroyAll();
            }
            reference.destroy();
        }
        return null;
    }
    
    public static <T> T formJson(String json, Class<T> classOfT) {
        try {
            return JSON.parse(json, classOfT);
        } catch (ParseException e) {
            log.error("json to class is error! "+classOfT.getName(), e);
        }
        return null;
    }
    
    public static String toJson(Object obj) {
        try {
            return JSON.json(obj);
        } catch (IOException e) {
            log.error("class to json is error!", e);
        }
        return null;
    }

}


