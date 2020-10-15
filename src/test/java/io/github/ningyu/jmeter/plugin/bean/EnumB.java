/*
 * Copyright (c) 2018, Jiuye SCM and/or its affiliates. All rights reserved.
 */

package io.github.ningyu.jmeter.plugin.bean;

/**
 * <功能描述>
 *
 * @author ningyu
 * @date 2018/12/18 11:38
 */
public enum EnumB {
    AUDITING(1), PASSED(2), FAILED(3);

    private int code;

    EnumB(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static EnumB convert(int code) {
        EnumB[] enums = EnumB.values();
        for (EnumB e : enums) {
            if (e.code == code)
                return e;
        }
        return null;
    }
}
