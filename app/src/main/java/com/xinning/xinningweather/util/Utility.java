package com.xinning.xinningweather.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.xinning.xinningweather.db.City;
import com.xinning.xinningweather.db.County;
import com.xinning.xinningweather.db.Province;
import com.xinning.xinningweather.gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author guoxi at 2017-08-18
 */

public class Utility {
    /**
     * 解析省份
     * @param response
     * @return
     */
    public static boolean handleProvince(String response){
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        LogUtil.e("获取失败"+response);
        return false;
    }

    /**
     * 解析服务器返回的数据城市
     * @param response
     * @return
     */
    public static boolean handleCityResponse(String response , int  proVinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities=new JSONArray(response);
                for (int i = 0; i <allCities.length() ; i++) {
                    JSONObject cityObject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(proVinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean  handleCountryResponse(String response , int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCountrys=new JSONArray(response);
                for (int i = 0; i <allCountrys.length(); i++) {
                    JSONObject countryObject=allCountrys.getJSONObject(i);
                    County country=new County();
                    country.setCityId(cityId);
                    country.setCountyName(countryObject.getString("name"));
                    country.setWeatherId(countryObject.getString("weather_id"));
                    country.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
