package com.xinning.xinningweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author guoxi at 2017-08-13
 * 数据库类， 用来创建数据表，国家省市县，用于数据的存储和展示
 */

public class XinNingWeatherOpenHelper extends SQLiteOpenHelper {
    /**
     * 省份表的建表语句
     */
    public  static final String CREATE_PROVINCE="create table Province(" +
            ""+"id integer primary key autoincrement,"+
            "province_name text,"
            +"province_code text)";
    /**
     * 城市建表语句
     */
    public static final String CREATE_CITY="create table City("
            + "id integer primary key autoinvrement," +
            "city_name text," +
            "city_code text," +
            "province_code integer)";
    /**
     * 县的建表语句
     */
    public static final String CREATE_COUNTRY="create table Country("
            + "id integer primary key autoinvrement," +
            "country_name text," +
            "country_code text," +
            "city_code integer)";

    public XinNingWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
