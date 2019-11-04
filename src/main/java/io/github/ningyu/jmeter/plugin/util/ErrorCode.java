/*
 * Copyright (c) 2018, Jiuye SCM and/or its affiliates. All rights reserved.
 */

package io.github.ningyu.jmeter.plugin.util;

/**
 * <功能描述>
 *
 * @author ningyu
 * @date 2018/7/11 18:03
 */
public enum ErrorCode {

    MISS_INTERFACE("MISS_INTERFACE","Interface is empty!"),
    RETRIES_ERROR("RETRIES_ERROR","retries is empty or NumberFormat error!"),
    TIMEOUT_ERROR("TIMEOUT_ERROR","timeout is empty or NumberFormat error!"),
    CONNECTIONS_ERROR("CONNECTIONS_ERROR","connections is empty or NumberFormat error!"),
    GENERIC_SERVICE_IS_NULL("GENERIC_SERVICE_IS_NULL","Not found exported service: {0}, may be version or group mismatch!"),
    MISS_METHOD("MISS_METHOD","Method is empty!"),
    MISS_ADDRESS("MISS_ADDRESS","Address is empty!"),
    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION","Unknown exception!"),
    DUPLICATE_CONFIGCENTERCONFIG("DUPLICATE_CONFIGCENTERCONFIG","Duplicate Config found for ConfigCenterConfig!"),
    ;

    String code;
    String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
