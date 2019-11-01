package io.github.ningyu.jmeter.plugin;

import io.github.ningyu.jmeter.plugin.util.JsonUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.Test;

public class GenericServiceTest {
    @Test
    public void test() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-generic-consumer");
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setUrl("dubbo://192.168.56.1:20880/org.apache.dubbo.samples.basic.api.DemoService");
//        reference.setVersion("1.0.0");
        reference.setTimeout(2000);
//        reference.setGroup("test");
        reference.setGeneric(true);
        reference.setApplication(application);
        reference.setInterface("com.jiuyescm.account.api.IUserService");
        GenericService genericService = reference.get();
        RpcContext.getContext().setAttachment("test.ningyu","this is attachmentValue");
//        Object obj = genericService.$invoke("getUserById", new String[]{Long.class.getName()}, new Long[]{1L});
        Object obj = genericService.$invoke("sayHello", new String[]{String.class.getName()}, new String[]{"ningyu"});
        String json = JsonUtils.toJson(obj);
        System.out.println(json);
    }
}
