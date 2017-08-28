package com.xinning.xinningweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author guoxi at 2017-08-21
 */

public class Forecast {
    public String date;
    @SerializedName("tmp")
    public  Temperature temperature;
    @SerializedName("cond")
    public More more;

    public class Temperature{
        public  String max;
        public  String min;
    }

    public class More{
        @SerializedName("txt_d")
        public String info;
    }
}
