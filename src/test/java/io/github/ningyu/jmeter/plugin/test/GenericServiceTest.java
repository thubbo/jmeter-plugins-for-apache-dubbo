package io.github.ningyu.jmeter.plugin.test;

import io.github.ningyu.jmeter.plugin.bean.EnumA;
import io.github.ningyu.jmeter.plugin.bean.EnumB;
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
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericServiceTest {
    @Test
    public void test() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-generic-consumer");
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setUrl("dubbo://192.168.9.182:28077/com.pupu.account.api.ITemperatureRecordingApi");
        reference.setVersion("1.0.0");
        reference.setTimeout(2000);
        reference.setGeneric(true);
        reference.setApplication(application);
        reference.setInterface("com.pupu.account.api.ITemperatureRecordingApi");
        GenericService genericService = reference.get();
        Object obj = genericService.$invoke("countByAdminUser", new String[]{String.class.getName()}, new String[]{"118303"});
        String json = JsonUtils.toJson(obj);
        System.out.println(json);
    }

    @Test
    public void testAttachment() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-generic-consumer");
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setUrl("dubbo://192.168.9.182:28077/com.pupu.account.api.ITemperatureRecordingApi");
        reference.setVersion("1.0.0");
        reference.setTimeout(2000);
        reference.setGeneric(true);
        reference.setApplication(application);
        reference.setInterface("com.pupu.account.api.ITemperatureRecordingApi");
        GenericService genericService = reference.get();
        RpcContext.getContext().setAttachment("test.ningyu","this is attachmentValue");
        Object obj = genericService.$invoke("countByAdminUser", new String[]{String.class.getName()}, new String[]{"118303"});
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
            reference.setGroup("");
            RegistryConfig registry = new RegistryConfig();
            registry.setProtocol(Constants.REGISTRY_ZOOKEEPER);
            registry.setAddress("qa-config.zookeeper.service.consul");
            registry.setPort(2181);
//            registry.setAddress("192.168.0.44:2181,192.168.0.44:2182,192.168.0.44:2183");
            registry.setTimeout(10000);
            reference.setRegistry(registry);
            ConfigCenterConfig cc = new ConfigCenterConfig();
//            cc.setAddress("192.168.0.44:2181,192.168.0.44:2182,192.168.0.44:2183");
            cc.setAddress("qa-config.zookeeper.service.consul:2181");

            cc.setProtocol(Constants.REGISTRY_ZOOKEEPER);
            cc.setTimeout(Long.valueOf("10000"));
            cc.setGroup("");
            cc.setNamespace("");
            reference.setConfigCenter(cc);
            reference.setTimeout(2000);
            reference.setGeneric(true);
            reference.setApplication(application);
            reference.setInterface("com.pupu.account.api.ITemperatureRecordingApi");
            GenericService genericService = reference.get();
            Object obj = genericService.$invoke("countByAdminUser", new String[]{String.class.getName()}, new String[]{"118303"});
            String json = JsonUtils.toJson(obj);
            System.out.println(json);
        }
    }

    @Test
    public void testEnumA() {
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        MethodArgument arg = new MethodArgument("io.github.ningyu.jmeter.plugin.bean.EnumA", "WECHAT");
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        System.out.println(paramterTypeList.toString());
        System.out.println(parameterValuesList.toString());
        Assert.assertEquals(EnumA.WECHAT, parameterValuesList.get(0));
    }

    @Test
    public void testEnumB() {
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        MethodArgument arg = new MethodArgument("io.github.ningyu.jmeter.plugin.bean.EnumB", "PASSED");
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        System.out.println(paramterTypeList.toString());
        System.out.println(parameterValuesList.toString());
        Assert.assertEquals(EnumB.PASSED, parameterValuesList.get(0));
    }
}
