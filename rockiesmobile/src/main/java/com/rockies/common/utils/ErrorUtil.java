package com.rockies.common.utils;

import java.util.HashMap;
import java.util.Map;

public final class ErrorUtil {
    public static String getErrorMsg(int error, String defaultMsg) {
        String msg = errorMsg.get(error);
        return msg == null ? defaultMsg : msg;
    }

    private ErrorUtil() {}

    private static Map<Integer, String> errorMsg;
    static {
        errorMsg = new HashMap<>();
        errorMsg.put(500204, "角色名字已经存在");
        errorMsg.put(300504, "此订单已经发货");
    }
}
