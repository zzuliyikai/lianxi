package com.deviceutils;

public class DeviceUtils {

    /**
     * 获取设备Model，可以用来判断设备型号
     */
    public static String getBuildModel() {
        return android.os.Build.MODEL;
    }

}
