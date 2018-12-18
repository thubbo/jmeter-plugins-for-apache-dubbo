/*
 * Copyright (c) 2018, Jiuye SCM and/or its affiliates. All rights reserved.
 */

package io.github.ningyu.jmeter.plugin;

import io.github.ningyu.jmeter.plugin.dubbo.sample.MethodArgument;
import io.github.ningyu.jmeter.plugin.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <功能描述>
 *
 * @author ningyu
 * @date 2018/12/18 11:37
 */
public class test {
    public static void main(String[] args){
        testEnumB();
    }

    private static void testEnumB() {
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        MethodArgument arg = new MethodArgument("io.github.ningyu.jmeter.plugin.EnumB1", "PASSED");
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        System.out.println(paramterTypeList.toString());
        System.out.println(parameterValuesList.toString());
    }

    private static void testEnumA() {
        List<String> paramterTypeList = new ArrayList<>();
        List<Object> parameterValuesList = new ArrayList<>();
        MethodArgument arg = new MethodArgument("io.github.ningyu.jmeter.plugin.EnumA1", "WECHAT");
        ClassUtils.parseParameter(paramterTypeList, parameterValuesList, arg);
        System.out.println(paramterTypeList.toString());
        System.out.println(parameterValuesList.toString());
    }
}
