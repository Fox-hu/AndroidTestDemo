package com.component.common.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 系统4.0的时候
     * 获取手机IMEI 或者Meid
     *
     * @return 手机IMEI
     */
    public static String getImeiOrMeid(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            return tm.getDeviceId();
        }
        return null;
    }

    /**
     * 拿到imei或者meid后判断是有多少位数
     *
     * @param ctx
     * @return
     */
    public static int getNumber(Context ctx) {
        int count = 0;
        long number = Long.parseLong(getImeiOrMeid(ctx).trim());

        while (number > 0) {
            number = number / 10;
            count++;
        }
        return count;
    }


    /**
     * Flyme 说 5.0 6.0统一使用这个获取IMEI IMEI2 MEID
     *
     * @param ctx
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static String getImeiAndMeid(Context ctx) {
        String imeiID = "";
        Map<String, String> ret = new HashMap<>();
        TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
                //5.0 6.0采用getDeviceId 方式
                ret.put("imei1", telephonyManager.getDeviceId(0));
                ret.put("imei2", telephonyManager.getDeviceId(1));
            }else {
                //8.0 9.getImei 方式
                ret.put("imei1", telephonyManager.getImei(0));
                ret.put("imei2", telephonyManager.getImei(1));
                ret.put("meid1", telephonyManager.getMeid(0));
                ret.put("meid2", telephonyManager.getMeid(1));
            }
            imeiID = ret.toString();
        } catch (Throwable e) {
            imeiID = "";
        }
        return imeiID == null ? "" : imeiID;
    }

}
