package com.xinning.xinningweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author guoxi at 2017-08-18
 * 网络工具类---使用okhttp
 */

public class HttpUtil {
      public static void sendOkHttpRequest(String address ,okhttp3.Callback callback){
          OkHttpClient client=new OkHttpClient();
         Request request= new Request.Builder().url(address).build();
          client.newCall(request).enqueue(callback);
      }
}
