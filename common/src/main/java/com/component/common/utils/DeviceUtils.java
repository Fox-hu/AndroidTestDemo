package com.component.common.utils;

import android.content.Context;
import android.os.Build;

import java.lang.reflect.Method;

public class DeviceUtils {

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return Build.DISPLAY;
        //return android.os.Build.VERSION.RELEASE;

    }


    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }


    /**
     * 获取SN
     *
     * @return
     */
    public static String getSn(Context ctx) {
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");

        } catch (Exception ignored) {

        }

        return serial;
    }

}
