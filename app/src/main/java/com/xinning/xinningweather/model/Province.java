package com.xinning.xinningweather.model;

/**
 * @author guoxi at 2017-08-15
 * 省实体类
 *
 */

public class Province {
    private int id;//id
    private String provinceName;//城市名
    private String provinceCode;//城市代码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
