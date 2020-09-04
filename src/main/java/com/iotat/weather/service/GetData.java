package com.iotat.weather.service;

import com.alibaba.fastjson.JSONObject;

public interface GetData {
    String localtionId(String longitude,String latitude) throws Exception;
    String getWeather7(String id);
    String getWeather24(String id);
}
