package com.xinning.xinningweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author guoxi at 2017-08-18
 */

public class Basic {
    @SerializedName("city")
    public  String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public  String updateTime;
    }
}
