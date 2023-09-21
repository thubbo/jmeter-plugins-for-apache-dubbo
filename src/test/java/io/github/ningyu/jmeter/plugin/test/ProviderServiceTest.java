package io.github.ningyu.jmeter.plugin.test;

import io.github.ningyu.jmeter.plugin.dubbo.sample.ProviderService;
import org.junit.Test;

import java.util.List;

/**
 * @Author: Jason Wu
 * @Date: 2023/9/18
 * @Description:
 */
public class ProviderServiceTest {

//    @Test
    public void testGetProviders() {
        ProviderService providerService = new ProviderService();
        List<String> result = providerService.getProviders("zookeeper", "qa-config.zookeeper.service.consul:2181", "");
        result.forEach(s->{});
    }
}
