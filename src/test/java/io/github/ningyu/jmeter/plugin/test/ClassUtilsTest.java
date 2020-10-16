package io.github.ningyu.jmeter.plugin.test;

import com.google.common.reflect.TypeToken;
import com.google.gson.internal.LinkedTreeMap;
import io.github.ningyu.jmeter.plugin.bean.LocaleTestBean;
import io.github.ningyu.jmeter.plugin.bean.LongTestBean;
import io.github.ningyu.jmeter.plugin.bean.TestBean;
import io.github.ningyu.jmeter.plugin.dubbo.sample.MethodArgument;
import io.github.ningyu.jmeter.plugin.util.ClassUtils;
import io.github.ningyu.jmeter.plugin.util.Constants;
import io.github.ningyu.jmeter.plugin.util.JsonUtils;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        String paramValue = "{\"locale\":\"CHINESE\",\"name\":\"aaa\",\"item\":{\"class\":\"io.github.ningyu.jmeter.plugin.bean.TestBean\",\"name\":\"bbb\"}}";
        String paramType = "io.github.ningyu.jmeter.plugin.bean.LocaleTestBean";
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

    @Test
    public void testLocalDateTime() {
        String paramValue = "2020-10-15 20:08:56";
        String paramType = "java.time.LocalDateTime";
        MethodArgument arg = new MethodArgument(paramType, paramValue);
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        Assert.assertEquals(paramterTypeList.get(0),paramType);
        Assert.assertEquals(parameterValuesList.get(0).getClass(), LocalDateTime.class);
        Assert.assertEquals(((LocalDateTime)parameterValuesList.get(0)).format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)), paramValue);
    }

    @Test
    public void testLocalDate() {
        String paramValue = "2020-10-15 20:08:56";
        String paramType = "java.time.LocalDate";
        MethodArgument arg = new MethodArgument(paramType, paramValue);
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        Assert.assertEquals(paramterTypeList.get(0),paramType);
        Assert.assertEquals(parameterValuesList.get(0).getClass(), LocalDate.class);
        Assert.assertEquals(((LocalDate)parameterValuesList.get(0)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "2020-10-15");
    }

    @Test
    public void testLocalTime() {
        String paramValue = "2020-10-15 20:08:56";
        String paramType = "java.time.LocalTime";
        MethodArgument arg = new MethodArgument(paramType, paramValue);
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        Assert.assertEquals(paramterTypeList.get(0),paramType);
        Assert.assertEquals(parameterValuesList.get(0).getClass(), LocalTime.class);
        Assert.assertEquals(((LocalTime)parameterValuesList.get(0)).format(DateTimeFormatter.ofPattern("HH:mm:ss")), "20:08:56");
    }

    @Test
    public void testLongType() {
        String paramValue = "{\"name\":\"tomcat\",\"bigLong\":6906764140092371368,\"smallLong\":6906764140092371368,\"item\":{\"class\":\"io.github.ningyu.jmeter.plugin.bean.TestBean\",\"name\":\"aaaa\"}}\n";
        String paramType = "io.github.ningyu.jmeter.plugin.bean.LongTestBean";
        MethodArgument arg = new MethodArgument(paramType, paramValue);
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        Assert.assertEquals(paramterTypeList.get(0),paramType);
        Assert.assertEquals(parameterValuesList.get(0).getClass(), LongTestBean.class);
        Assert.assertEquals(((LongTestBean)parameterValuesList.get(0)).getBigLong(), Long.valueOf(6906764140092371368L));
        Assert.assertEquals(((LongTestBean)parameterValuesList.get(0)).getSmallLong(), 6906764140092371368L);


        String jsonString = paramValue;
        System.out.println(jsonString); // output ok: {"first":6906764140092371368}
        Map<String, Object> mapFromJson = JsonUtils.fromJson(jsonString, new TypeToken<HashMap<String, Object>>(){}.getType());
        System.out.println(mapFromJson.get("first"));
        System.out.println(mapFromJson.get("aaa"));
    }


}
