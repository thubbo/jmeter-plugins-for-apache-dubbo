package io.github.ningyu.jmeter.plugin;

import io.github.ningyu.jmeter.plugin.dubbo.sample.MethodArgument;
import io.github.ningyu.jmeter.plugin.util.ClassUtils;
import io.github.ningyu.jmeter.plugin.util.Constants;
import io.github.ningyu.jmeter.plugin.util.JsonUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericServiceTest {
    @Test
    public void test() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-generic-consumer");
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setUrl("dubbo://192.168.56.1:20880/org.apache.dubbo.samples.basic.api.DemoService");
        reference.setVersion("1.0.0");
        reference.setTimeout(2000);
        reference.setGeneric(true);
        reference.setApplication(application);
        reference.setInterface("com.jiuyescm.account.api.IUserService");
        GenericService genericService = reference.get();
        Object obj = genericService.$invoke("getUserById", new String[]{Long.class.getName()}, new Long[]{1L});
        String json = JsonUtils.toJson(obj);
        System.out.println(json);
    }

    @Test
    public void testAttachment() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-generic-consumer");
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setUrl("dubbo://192.168.56.1:20880/org.apache.dubbo.samples.basic.api.DemoService");
        reference.setVersion("1.0.0");
        reference.setTimeout(2000);
        reference.setGeneric(true);
        reference.setApplication(application);
        reference.setInterface("com.jiuyescm.account.api.IUserService");
        GenericService genericService = reference.get();
        RpcContext.getContext().setAttachment("test.ningyu","this is attachmentValue");
        Object obj = genericService.$invoke("sayHello", new String[]{String.class.getName()}, new String[]{"ningyu"});
        String json = JsonUtils.toJson(obj);
        System.out.println(json);
    }

    @Test
    public void testZk() {
        for(int i=0;i<5;i++) {
            ApplicationConfig application = new ApplicationConfig();
            application.setName("api-generic-consumer");
            ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
            reference.setVersion("1.0.0");
            RegistryConfig registry = new RegistryConfig();
            registry.setProtocol(Constants.REGISTRY_ZOOKEEPER);
            registry.setAddress("192.168.0.44:2181,192.168.0.44:2182,192.168.0.44:2183");
            registry.setTimeout(10000);
            reference.setRegistry(registry);
            ConfigCenterConfig cc = new ConfigCenterConfig();
            cc.setAddress("192.168.0.58:2181,192.168.0.59:2181,192.168.0.60:2181");
            cc.setProtocol(Constants.REGISTRY_ZOOKEEPER);
            cc.setTimeout(Long.valueOf("10000"));
            cc.setGroup("");
            cc.setNamespace("");
            reference.setConfigCenter(cc);
            reference.setTimeout(2000);
            reference.setGeneric(true);
            reference.setApplication(application);
            reference.setInterface("com.jiuyescm.account.api.IUserService");
            GenericService genericService = reference.get();
            Object obj = genericService.$invoke("getUserById", new String[]{Long.class.getName()}, new Long[]{1L});
            String json = JsonUtils.toJson(obj);
            System.out.println(json);
        }
    }

    @Test
    public void testEnumA() {
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        MethodArgument arg = new MethodArgument("io.github.ningyu.jmeter.plugin.EnumA1", "WECHAT");
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        System.out.println(paramterTypeList.toString());
        System.out.println(parameterValuesList.toString());
    }

    @Test
    public void testEnumB() {
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        MethodArgument arg = new MethodArgument("io.github.ningyu.jmeter.plugin.EnumB1", "PASSED");
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        System.out.println(paramterTypeList.toString());
        System.out.println(parameterValuesList.toString());
    }
}
