package com.xinning.xinningweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author guoxi at 2017-08-18
 * 天气的gson
 */

public class Weather {

    public  String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;


}
