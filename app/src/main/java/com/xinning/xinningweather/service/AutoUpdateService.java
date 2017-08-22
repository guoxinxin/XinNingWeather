package com.xinning.xinningweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.xinning.xinningweather.gson.Weather;
import com.xinning.xinningweather.util.HttpUtil;
import com.xinning.xinningweather.util.LogUtil;
import com.xinning.xinningweather.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author guoxi at 2017-08-21
 * 自动更新服务
 */

public class AutoUpdateService  extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       updateWeather();
        updateBingPic();
        AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
         int anHour=8*60*60*1000;//8小时的毫秒值
        long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        Intent i=new Intent(this,AutoUpdateService.class);
        PendingIntent service = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(service);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,service);

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 天气的更新
     */
    public  void updateWeather(){
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather",null);
        if(weatherString!=null){
            //有缓存时直接进行解析天气数据
            Weather weather = Utility.handleWeatherResponse(weatherString);
           final String weatherId= weather.basic.weatherId;
            String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=bc0418b57b2d4918819d3974ac1285d9";
            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    LogUtil.e("请求失败");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseTxt = response.body().toString();
                    LogUtil.e(responseTxt);
                    Weather weather = Utility.handleWeatherResponse(responseTxt);
                    if(weather!=null&&"ok".equals(weather.status)){
                        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        edit.putString("weather",responseTxt);
                        edit.apply();

                    }
                }
            });

        }

    }

    /**
     * 更新必应壁纸--每日更新
     */
    private void updateBingPic(){
        String requestBingPic="http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e("打印失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().toString();
                LogUtil.e(bingPic);
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                edit.putString("bing_pic",bingPic);
                edit.apply();

            }
        });
    }
}
