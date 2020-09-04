package com.iotat.weather.service.Imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iotat.weather.dao.LifeRepository;
import com.iotat.weather.entity.Life;
import com.iotat.weather.service.GetLife;
import com.iotat.weather.util.HttpClient;
import com.iotat.weather.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class GetLifeImp implements GetLife {
    @Autowired
    private LifeRepository lifeRepository;
    @Override
    public String getLife(String localId) {
        if (localId.isEmpty()){
            return Result.resultData(500,"参数为空");
        }
        SimpleDateFormat formatter= new SimpleDateFormat("YYYY-MM-dd");
        Calendar cal = Calendar.getInstance();
        String date = formatter.format(cal.getTime());
        //System.out.println(date);
        Life life = lifeRepository.findData(date,localId);
        //System.out.println(life);
        if (life == null){
            String url1 = "https://devapi.heweather.net/v7/air/now?location=" + localId + "&key=1039bb39c7264698b2fb6234f8fc4e74";
            String url2 = "https://devapi.heweather.net/v7/indices/1d?location=" + localId + "&key=1039bb39c7264698b2fb6234f8fc4e74&type=8";
            String airData = null;
            String lifeData = null;
            try {
                airData = HttpClient.GzipGetHttp(url1);
                lifeData = HttpClient.GzipGetHttp(url2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject jsonObject1 = JSON.parseObject(airData);
            if (!jsonObject1.getString("code").equals("200")) {
                return Result.resultStatus(500,"访问失败");
            }
            JSONObject jsonObject2 = JSON.parseObject(lifeData);
            String airStr = jsonObject1.getString("now");
            String lifeStr = jsonObject2.getString("daily").substring(1,jsonObject2.getString("daily").length()-1);
           // System.out.println(airStr);
           // System.out.println(lifeStr);
            JSONObject airJson = JSON.parseObject(airStr);
            JSONObject lifeJson = JSON.parseObject(lifeStr);
            Life life1 = new Life();
            life1.setApi(airJson.getString("aqi"));
            life1.setCategory(lifeJson.getString("category"));
            life1.setCityId(localId);
            life1.setDate(date);
            life1.setLevel(lifeJson.getString("level"));
            life1.setName(lifeJson.getString("name"));
            life1.setText(lifeJson.getString("text"));
            //System.out.println(life1);
            lifeRepository.save(life1);
            return Result.resultData(200,life1);
        }else{
            return Result.resultData(200,life);
        }
    }
}
