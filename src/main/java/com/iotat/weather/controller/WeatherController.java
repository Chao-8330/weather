package com.iotat.weather.controller;

import com.iotat.weather.dao.CityRepository;
import com.iotat.weather.service.GetData;
import com.iotat.weather.service.GetLife;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "天气" ,tags = {"天气访问接口"})
@RestController
public class WeatherController {
    @Autowired
    private GetData getData;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    private GetLife getLife;

    /**
     *  访问地理位置，省市区
     * @param longitude
     * @param latitude
     * @return
     * @throws Exception
     */
    @GetMapping("/getLocation")
    public String getLocation(String longitude,String latitude) throws Exception {
        return getData.localtionId(longitude,latitude);
    }

    /**
     * 7天天气数据
     * @param localId
     * @return
     */
    @GetMapping("/getweather7")
    public String getWeather7(String localId){
        return getData.getWeather7(localId);
    }

    /**
     * 24小时天气数据
     * @param localId
     * @return
     */
    @GetMapping("/getweather24")
    public String getWeather24(String localId){
        return getData.getWeather24(localId);
    }

    /**
     * 生活指数
     * @param localId
     * @return
     */
    @GetMapping("/getlife")
    public String getLife(String localId){
        return getLife.getLife(localId);
    }


}
