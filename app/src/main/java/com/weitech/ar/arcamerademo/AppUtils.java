package com.weitech.ar.arcamerademo;

import android.content.res.Resources;

public class AppUtils {

    public static int GetScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int GetScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
