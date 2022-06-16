package com.weitech.ar.arcamerademo;

import android.content.res.Resources;
import android.os.Build;

public class AppUtils {

    public static int GetScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int GetScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static boolean IsHuaweiPhone() {
        return "HUAWEI".equalsIgnoreCase(Build.MANUFACTURER);
    }
}
