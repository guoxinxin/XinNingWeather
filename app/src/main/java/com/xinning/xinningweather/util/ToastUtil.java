package com.xinning.xinningweather.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author guoxi at 2017-08-22
 */

public class ToastUtil {
    private static Toast toast;

    public static void show(Context context,String str){
        if(toast==null){
            toast=Toast.makeText(context,str,Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void show(Context context,int str){
        if(toast==null){
            toast=Toast.makeText(context,str+"",Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
