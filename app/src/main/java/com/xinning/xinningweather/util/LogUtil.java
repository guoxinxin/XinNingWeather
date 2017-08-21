package com.xinning.xinningweather.util;

import android.util.Log;

/**
 * @author guoxi at 2017-08-21
 */

public class LogUtil {
    private static String TAG="XinNingWeather";

    public static void i(String str){
        if(str!=null){
            Log.i(TAG,str);
        }
    }

    public static void e(String str) {
        if (str != null) {
            Log.e(TAG, str);
        }
    }

    public static void d(String str) {
        if (str != null) {
            Log.d(TAG, str);
        }
    }


}
