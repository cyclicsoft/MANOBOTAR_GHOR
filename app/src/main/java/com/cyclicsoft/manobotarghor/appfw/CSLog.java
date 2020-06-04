/**
 * author Shipon
 * An ultimate class for log control
 */
package com.cyclicsoft.manobotarghor.appfw;

import android.util.Log;

import com.cyclicsoft.manobotarghor.BuildConfig;

public class CSLog {
    private static String LOG_TAG = "CSLog";
    public static boolean isShowLog = BuildConfig.DEBUG;
    public static void d(String tag, String message){
        if(isShowLog)
            Log.d(">>"+tag, "" + message);
    }
    public static void e(String tag, String message){
        if(isShowLog)
            Log.e(">>"+tag, "" + message);
    }

    public static void d(String message){
        if(isShowLog)
            Log.d(LOG_TAG, "" + message);
    }
    public static void e(String message){
        if(isShowLog)
            Log.e(LOG_TAG, "" + message);
    }
}
