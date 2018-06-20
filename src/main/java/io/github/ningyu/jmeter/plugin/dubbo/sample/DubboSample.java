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
package io.github.ningyu.jmeter.plugin.dubbo.sample;

import io.github.ningyu.jmeter.plugin.util.ClassUtils;
import io.github.ningyu.jmeter.plugin.util.Constants;
import io.github.ningyu.jmeter.plugin.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.property.IntegerProperty;
import org.apache.jmeter.testelement.property.StringProperty;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache.KeyGenerator;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * DubboSample
 */
public class DubboSample extends AbstractSampler {
    
    private static final Logger log = LoggingManager.getLoggerForClass();


    /**
     * 
     */
    private static final long serialVersionUID = -6794913295411458705L;
    
    public static String FIELD_DUBBO_REGISTRY_PROTOCOL = "FIELD_DUBBO_REGISTRY_PROTOCOL";
    public static String FIELD_DUBBO_RPC_PROTOCOL = "FIELD_DUBBO_RPC_PROTOCOL";
    public static String FIELD_DUBBO_ADDRESS = "FIELD_DUBBO_ADDRESS";
    public static String FIELD_DUBBO_TIMEOUT = "FIELD_DUBBO_TIMEOUT";
    public static String FIELD_DUBBO_VERSION = "FIELD_DUBBO_VERSION";
    public static String FIELD_DUBBO_RETRIES = "FIELD_DUBBO_RETRIES";
    public static String FIELD_DUBBO_CLUSTER = "FIELD_DUBBO_CLUSTER";
    public static String FIELD_DUBBO_GROUP = "FIELD_DUBBO_GROUP";
    public static String FIELD_DUBBO_CONNECTIONS = "FIELD_DUBBO_CONNECTIONS";
    public static String FIELD_DUBBO_LOADBALANCE = "FIELD_DUBBO_LOADBALANCE";
    public static String FIELD_DUBBO_ASYNC = "FIELD_DUBBO_ASYNC";
    public static String FIELD_DUBBO_INTERFACE = "FIELD_DUBBO_INTERFACE";
    public static String FIELD_DUBBO_METHOD = "FIELD_DUBBO_METHOD";
    public static String FIELD_DUBBO_METHOD_ARGS = "FIELD_DUBBO_METHOD_ARGS";
    public static String FIELD_DUBBO_METHOD_ARGS_SIZE = "FIELD_DUBBO_METHOD_ARGS_SIZE";
    public static String DEFAULT_TIMEOUT = "3000";
    public static String DEFAULT_VERSION = "1.0.0";
    public static String DEFAULT_RETRIES = "0";
    public static String DEFAULT_CLUSTER = "failfast";
    public static String DEFAULT_CONNECTIONS = "1";

    /**
     * get Registry Protocol
     * @return the protocol
     */
    public String getRegistryProtocol() {
        return this.getPropertyAsString(FIELD_DUBBO_REGISTRY_PROTOCOL);
    }

    /**
     * set Registry Protocol
     * @param registryProtocol the protocol to set
     */
    public void setRegistryProtocol(String registryProtocol) {
        this.setProperty(new StringProperty(FIELD_DUBBO_REGISTRY_PROTOCOL, registryProtocol));
    }
    
    /**
     * get RPC protocol
     * @return the RPC protocol
     */
    public String getRpcProtocol() {
        return this.getPropertyAsString(FIELD_DUBBO_RPC_PROTOCOL);
    }

    /**
     * set RPC protocol
     * @param RPC protocol the protocol to set
     */
    public void setRpcProtocol(String rpcProtocol) {
        this.setProperty(new StringProperty(FIELD_DUBBO_RPC_PROTOCOL, rpcProtocol));
    }

    /**
     * get address
     * @return the address
     */
    public String getAddress() {
        return this.getPropertyAsString(FIELD_DUBBO_ADDRESS);
    }

    /**
     * set address
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.setProperty(new StringProperty(FIELD_DUBBO_ADDRESS, address));
    }

    /**
     * get timeout
     * @return the timeout
     */
    public String getTimeout() {
        return this.getPropertyAsString(FIELD_DUBBO_TIMEOUT, DEFAULT_TIMEOUT);
    }

    /**
     * set timeout
     * @param timeout the timeout to set
     */
    public void setTimeout(String timeout) {
        this.setProperty(new StringProperty(FIELD_DUBBO_TIMEOUT, timeout));
    }

    /**
     * get version
     * @return the version
     */
    public String getVersion() {
        return this.getPropertyAsString(FIELD_DUBBO_VERSION, DEFAULT_VERSION);
    }

    /**
     * set version
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.setProperty(new StringProperty(FIELD_DUBBO_VERSION, version));
    }
    
    /**
     * get retries
     * @return the retries
     */
    public String getRetries() {
        return this.getPropertyAsString(FIELD_DUBBO_RETRIES, DEFAULT_RETRIES);
    }

    /**
     * set retries
     * @param retries the retries to set
     */
    public void setRetries(String retries) {
        this.setProperty(new StringProperty(FIELD_DUBBO_RETRIES, retries));
    }
    
    /**
     * get cluster
     * @return the cluster
     */
    public String getCluster() {
        return this.getPropertyAsString(FIELD_DUBBO_CLUSTER, DEFAULT_CLUSTER);
    }

    /**
     * set cluster
     * @param cluster the cluster to set
     */
    public void setCluster(String cluster) {
        this.setProperty(new StringProperty(FIELD_DUBBO_CLUSTER, cluster));
    }
    
    /**
     * get group
     * @return the group
     */
    public String getGroup() {
    	return this.getPropertyAsString(FIELD_DUBBO_GROUP, null);
    }
    
    /**
     * set group
     * @param group the group to set
     */
    public void setGroup(String group) {
    	this.setProperty(new StringProperty(FIELD_DUBBO_GROUP, group));
    }
    
    /**
     * get connections
     * @return the group
     */
    public String getConnections() {
    	return this.getPropertyAsString(FIELD_DUBBO_CONNECTIONS, DEFAULT_CONNECTIONS);
    }
    
    /**
     * set connections
     * @param connections the connections to set
     */
    public void setConnections(String connections) {
    	this.setProperty(new StringProperty(FIELD_DUBBO_CONNECTIONS, connections));
    }
    
    /**
     * get loadbalance
     * @return the loadbalance
     */
    public String getLoadbalance() {
    	return this.getPropertyAsString(FIELD_DUBBO_LOADBALANCE);
    }
    
    /**
     * set loadbalance
     * @param loadbalance the loadbalance to set
     */
    public void setLoadbalance(String loadbalance) {
    	this.setProperty(new StringProperty(FIELD_DUBBO_LOADBALANCE, loadbalance));
    }
    
    /**
     * get async
     * @return the async
     */
    public String getAsync() {
    	return this.getPropertyAsString(FIELD_DUBBO_ASYNC);
    }
    
    /**
     * set async
     * @param async the async to set
     */
    public void setAsync(String async) {
    	this.setProperty(new StringProperty(FIELD_DUBBO_ASYNC, async));
    }

    /**
     * get interfaceName
     * @return the interfaceName
     */
    public String getInterface() {
        return this.getPropertyAsString(FIELD_DUBBO_INTERFACE);
    }

    /**
     * set interfaceName
     * @param interfaceName the interfaceName to set
     */
    public void setInterfaceName(String interfaceName) {
        this.setProperty(new StringProperty(FIELD_DUBBO_INTERFACE, interfaceName));
    }

    /**
     * get method
     * @return the method
     */
    public String getMethod() {
        return this.getPropertyAsString(FIELD_DUBBO_METHOD);
    }

    /**
     * set method
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.setProperty(new StringProperty(FIELD_DUBBO_METHOD, method));
    }

    /**
     * get methodArgs
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
     * set methodArgs
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
        res.setSampleLabel(getName());
        res.sampleStart();
        //构造请求数据
        res.setSamplerData(getSampleData());
        //调用dubbo
        res.setResponseData(JsonUtils.toJson(callDubbo(res)));
        //构造响应数据
        res.setDataType(SampleResult.TEXT);
        res.setResponseCodeOK();
        res.setResponseMessageOK();
        res.sampleEnd();
        return res;
    }

    /**
     * Construct request data
     */
    private String getSampleData() {
    	StringBuilder sb = new StringBuilder();
        sb.append("Registry Protocol: ").append(getRegistryProtocol()).append("\n");
        sb.append("Address: ").append(getAddress()).append("\n");
        sb.append("RPC Protocol: ").append(getRpcProtocol()).append("\n");
        sb.append("Timeout: ").append(getTimeout()).append("\n");
        sb.append("Version: ").append(getVersion()).append("\n");
        sb.append("Retries: ").append(getRetries()).append("\n");
        sb.append("Cluster: ").append(getCluster()).append("\n");
        sb.append("Group: ").append(getGroup()).append("\n");
        sb.append("Connections: ").append(getConnections()).append("\n");
        sb.append("Loadbalance: ").append(getLoadbalance()).append("\n");
        sb.append("Async: ").append(getAsync()).append("\n");
        sb.append("Interface: ").append(getInterface()).append("\n");
        sb.append("Method: ").append(getMethod()).append("\n");
        sb.append("Method Args: ").append(getMethodArgs().toString());
        return sb.toString();
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    private Object callDubbo(SampleResult res) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("DubboSample");
        
        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig reference = new ReferenceConfig();
        // 引用远程服务
        reference.setApplication(application);
        RegistryConfig registry = null;
        
        String protocol = getRegistryProtocol();
        String group = getGroup();
		switch (protocol) {
		case Constants.REGISTRY_ZOOKEEPER:
			registry = new RegistryConfig();
			registry.setProtocol(Constants.REGISTRY_ZOOKEEPER);
			registry.setAddress(getAddress());
			registry.setGroup(StringUtils.isBlank(group) ? null : group);
			reference.setRegistry(registry);
			reference.setProtocol(getRpcProtocol().replaceAll("://", ""));
			break;
		case Constants.REGISTRY_MULTICAST:
			registry = new RegistryConfig();
			registry.setProtocol(Constants.REGISTRY_MULTICAST);
			registry.setAddress(getAddress());
            registry.setGroup(StringUtils.isBlank(group) ? null : group);
			reference.setRegistry(registry);
			reference.setProtocol(getRpcProtocol().replaceAll("://", ""));
			break;
		case Constants.REGISTRY_REDIS:
			registry = new RegistryConfig();
			registry.setProtocol(Constants.REGISTRY_REDIS);
			registry.setAddress(getAddress());
            registry.setGroup(StringUtils.isBlank(group) ? null : group);
			reference.setRegistry(registry);
			reference.setProtocol(getRpcProtocol().replaceAll("://", ""));
			break;
		case Constants.REGISTRY_SIMPLE:
			registry = new RegistryConfig();
			registry.setAddress(getAddress());
            registry.setGroup(StringUtils.isBlank(group) ? null : group);
			reference.setRegistry(registry);
			reference.setProtocol(getRpcProtocol().replaceAll("://", ""));
			break;
		default:
			// 直连方式
			StringBuffer sb = new StringBuffer();
			sb.append(getRpcProtocol()).append(getAddress()).append("/").append(getInterface());
			log.debug("rpc invoker url : " + sb.toString());
			reference.setUrl(sb.toString());
		}
        try {
            reference.setInterface(getInterface());
            reference.setRetries(Integer.valueOf(getRetries()));
            reference.setCluster(getCluster());
            reference.setVersion(getVersion());
            reference.setTimeout(Integer.valueOf(getTimeout()));
            reference.setConnections(Integer.valueOf(getConnections()));
            reference.setLoadbalance(getLoadbalance());
            reference.setAsync(Constants.ASYNC.equals(getAsync()) ? true : false);
            reference.setGeneric(true);
            //TODO 不同的注册中心地址使用不同的cache对象
            ReferenceConfigCache cache = ReferenceConfigCache.getCache(getAddress(), new KeyGenerator() {
				public String generateKey(ReferenceConfig<?> referenceConfig) {
					return referenceConfig.toString();
				}
			});
            GenericService genericService = (GenericService) cache.get(reference);
            String[] parameterTypes = null;
            Object[] parameterValues = null;
            List<MethodArgument> args = getMethodArgs();
            List<String> paramterTypeList =  new ArrayList<String>();;
            List<Object> parameterValuesList = new ArrayList<Object>();;
            for(MethodArgument arg : args) {
            	ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
            }
            //发起调用
            parameterTypes = paramterTypeList.toArray(new String[paramterTypeList.size()]);
            parameterValues = parameterValuesList.toArray(new Object[parameterValuesList.size()]);
            Object result = null;
			try {
				result = genericService.$invoke(getMethod(), parameterTypes, parameterValues);
				res.setSuccessful(true);
			} catch (Exception e) {
				log.error("接口返回异常：", e);
				//TODO
				//当接口返回异常时，sample标识为successful，通过响应内容做断言来判断是否标识sample错误，因为sample的错误会统计到用例的error百分比内。
				//比如接口有一些校验性质的异常，不代表这个操作是错误的，这样就可以灵活的判断，不至于正常的校验返回导致测试用例error百分比的不真实
				res.setSuccessful(true);
				result = e;
			}
            return result;
        } catch (Exception e) {
            log.error("未知异常：", e);
            res.setSuccessful(false);
            return e;
        } finally {
        	//TODO 不能在sample结束时destroy
//            if (registry != null) {
//                registry.destroyAll();
//            }
//            reference.destroy();
        }
    }
    
    public static void main(String[] args) throws Exception {
//    	  ApplicationConfig application = new ApplicationConfig();
//        application.setName("DubboSample");
//        
//        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
//        ReferenceConfig reference = new ReferenceConfig();
//        // 引用远程服务
//        reference.setApplication(application);
//        RegistryConfig registry = null;
//        
//        StringBuffer sb = new StringBuffer();
//        sb.append("dubbo").append("://").append("192.168.6.47:20835").append("/").append("com.jiuyescm.tenant.api.IMenuResourceService");
//        log.debug("rpc invoker url : " + sb.toString());
//        reference.setUrl(sb.toString());
//        Class clazz = Class.forName("com.jiuyescm.tenant.api.IMenuResourceService");
//        reference.setInterface(clazz);
//        reference.setRetries(Integer.valueOf("0"));
//        reference.setCluster("failfast");
//        reference.setVersion("1.0.0");
//        reference.setTimeout(Integer.valueOf("1200000"));
//        Object target = reference.get();
//        Method method = null;
//        Object[] parameterValues = null;
//        Method[] methods = target.getClass().getMethods();
//    	String methodName = "createMenuResourceMapping";
//    	List<MethodArgument> list = new ArrayList<MethodArgument>();
//    	list.add(new MethodArgument("java.util.List", "[{\"menuId\":30002,\"appId\":8,\"resourceId\":40052},{\"menuId\":30003,\"appId\":8,\"resourceId\":40052}]"));
//    	Class clazz = Class.forName("com.jiuyescm.tenant.api.IResourceService");
//    	String methodName = "query";
//    	List<MethodArgument> list = new ArrayList<MethodArgument>();
//    	list.add(new MethodArgument("com.jiuyescm.tenant.vo.ResourceVo", "{\"menuId\":30002,\"appId\":8,\"resourceId\":40052}"));
//    	list.add(new MethodArgument("java.lang.Integer", "1"));
//    	list.add(new MethodArgument("java.lang.Integer", "10"));
//    	List<Object> parameterValuesList = null;
//    	Class clazz = Class.forName("com.jiuyescm.tenant.api.IResourceService");
//    	String methodName = "testMethod";
//    	List<MethodArgument> list = new ArrayList<MethodArgument>();
//    	list.add(new MethodArgument("java.util.Map", "{\"name\":\"name\",\"value\":{\"service\":\"test1\",\"url\":\"test\",\"action\":\"GET\",\"enabled\":true,\"isPublic\":false,\"appId\":8,\"menuId\":30001}}"));
//    	list.add(new MethodArgument("java.util.List", "[{\"name\":1,\"value\":{\"service\":\"test1\",\"url\":\"test\",\"action\":\"GET\",\"enabled\":true,\"isPublic\":false,\"appId\":8,\"menuId\":30001}},{\"name\":2,\"value\":{\"service\":\"test1\",\"url\":\"test\",\"action\":\"GET\",\"enabled\":true,\"isPublic\":false,\"appId\":8,\"menuId\":30001}}]"));
//    	Method[] methods = clazz.getMethods();
//    	parameterValuesList = new ArrayList<Object>();
//    	for (Method m : methods) {
//    		if (m.getName().equals(methodName)) {
//    			Type[] paramTypes = m.getGenericParameterTypes();
//				for (int j = 0; j < paramTypes.length; j++) {
//					ClassUtils.parseParameter(paramTypes[j], parameterValuesList, list.get(j));
//				}
//    		}
//    	}
//		System.out.println(int.class.getName());
	}

}
