package com.xinning.xinningweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author guoxi at 2017-08-21
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public  More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }
}
