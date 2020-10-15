package io.github.ningyu.jmeter.plugin;

import com.google.gson.internal.LinkedTreeMap;
import io.github.ningyu.jmeter.plugin.dubbo.sample.MethodArgument;
import io.github.ningyu.jmeter.plugin.util.ClassUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ClassUtilsTest {
    @Test
    public void testLocale() {
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        MethodArgument arg = new MethodArgument("java.util.Locale","aaaaa");
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        Assert.assertEquals(paramterTypeList.get(0),"java.util.Locale");
        Assert.assertEquals(parameterValuesList.get(0), Locale.ROOT);

        arg = new MethodArgument("java.util.Locale","CHINESE");
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        Assert.assertEquals(paramterTypeList.get(1),"java.util.Locale");
        Assert.assertEquals(parameterValuesList.get(1), Locale.CHINESE);
    }

    @Test
    public void testLocaleArray() {
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        MethodArgument arg = new MethodArgument("java.util.Locale[]","[aaaaa,CHINESE]");
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        Assert.assertEquals(paramterTypeList.get(0),"java.util.Locale[]");
        Assert.assertEquals(parameterValuesList.get(0).getClass(), Locale[].class);
        Assert.assertEquals(((Locale[])parameterValuesList.get(0))[0], Locale.ROOT);
        Assert.assertEquals(((Locale[])parameterValuesList.get(0))[1], Locale.CHINESE);
    }

    @Test
    public void testGeneric() {
        String paramValue = "{\"locale\":\"CHINESE\",\"name\":\"aaa\",\"item\":{\"class\":\"io.github.ningyu.jmeter.plugin.TestBean\",\"name\":\"bbb\"}}";
        String paramType = "io.github.ningyu.jmeter.plugin.LocaleTestBean";
        MethodArgument arg = new MethodArgument(paramType, paramValue);
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        Assert.assertEquals(paramterTypeList.get(0),paramType);
        Assert.assertEquals(parameterValuesList.get(0).getClass(), LocaleTestBean.class);
        Assert.assertEquals(((LocaleTestBean)parameterValuesList.get(0)).getName(), "aaa");
        Assert.assertEquals(((LocaleTestBean)parameterValuesList.get(0)).getLocale(), Locale.CHINESE);
        Assert.assertEquals(((LocaleTestBean)parameterValuesList.get(0)).getItem().getClass(), LinkedTreeMap.class);
        Assert.assertEquals(((Map)((LocaleTestBean)parameterValuesList.get(0)).getItem()).get("name"), "bbb");
        Assert.assertEquals(((Map)((LocaleTestBean)parameterValuesList.get(0)).getItem()).get("item"), null);
    }
}
