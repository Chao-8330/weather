package com.iotat.weather.service.Imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iotat.weather.dao.CityRepository;
import com.iotat.weather.dao.SevenWeatherRepository;
import com.iotat.weather.dao.TwWeatherRepository;
import com.iotat.weather.entity.City;
import com.iotat.weather.entity.SevenWeather;
import com.iotat.weather.entity.TwWeather;
import com.iotat.weather.service.GetData;
import com.iotat.weather.util.Cut;
import com.iotat.weather.util.HttpClient;
import com.iotat.weather.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class GetDataImp implements GetData {


    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private SevenWeatherRepository sevenWeatherRepository;

    @Autowired
    private TwWeatherRepository twWeatherRepository;

    @Override
    public String localtionId(String longitude, String latitude) throws Exception {
        if (longitude.isEmpty() || latitude.isEmpty()) {
            return Result.resultStatus(500, "参数为空");
        }
        String location = "http://whois.pconline.com.cn/ipAreaCoordJson.jsp?coords=" + longitude + "," + latitude + "&level=3&json=true";
        String localStr = HttpClient.GetHttp(location);
        JSONObject localJson = JSON.parseObject(localStr);
        String adm1 = Cut.CutStr(localJson.getString("pro"));
        String adm2 = Cut.CutStr(localJson.getString("city"));
        String name = Cut.CutStr(localJson.getString("region"));
        //System.out.println(adm1);
        //System.out.println(adm2);
        //System.out.println(name);
        List<City> cityList = cityRepository.findResult(adm1, adm2, name);
        //System.out.println(cityList.toString().equals("[]"));
        if (cityList.toString().equals("[]")) {
            String url = "https://geoapi.heweather.net/v2/city/lookup?location=" + longitude + "," + latitude + "&key=1039bb39c7264698b2fb6234f8fc4e74";
            String data = null;
            try {
                data = HttpClient.EnGzipGetHttp(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (data == null) {
                return Result.resultStatus(500, "访问失败");
            }
            JSONObject jsonObject1 = JSON.parseObject(data);
            if (!jsonObject1.getString("status").equals("200")) {
                return Result.resultStatus(400,"参数错误");
            }
            String localString = jsonObject1.getString("location");
            //System.out.println(localString);
            String jsonStr = localString.substring(1, localString.length() - 1);
            //System.out.println(jsonStr);
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            String localId = jsonObject.getString("id");
            //System.out.println(localId);
            JSONObject dataJson = JSON.parseObject(data);
            if (!dataJson.getString("status").equals("200")) {
                return Result.resultStatus(500,"访问失败");
            }
            else {
                City city = new City();
                city.setcAdm1(adm1);
                city.setcAdm2(adm2);
                city.setcName(name);
                city.setcId(localId);
                cityRepository.save(city);
                cityList.add(city);
                return Result.resultData(200, cityList);
            }
        } else {
            return Result.resultData(200, cityList);
        }

    }

    @Override
    public String getWeather7(String localId) {
        if (localId.isEmpty()) {
            return Result.resultData(500, "参数为空");
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String localDateFirst = sf.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, 6);
        String localDateFinal = sf.format(c.getTime());
       // System.out.println(localDateFirst);
       // System.out.println(localDateFinal);
        SevenWeather dateFirst = sevenWeatherRepository.findWeather(localDateFirst, localId);
        SevenWeather dateFinal = sevenWeatherRepository.findWeather(localDateFinal, localId);
        //System.out.println(dateFirst);
        //System.out.println(dateFinal);
       // System.out.println(dateFirst == null);
       // System.out.println(dateFinal == null);
        if (dateFirst == null || dateFinal == null) {
            String url = "https://devapi.heweather.net/v7/weather/7d?location=" + localId + "&key=1039bb39c7264698b2fb6234f8fc4e74";
            String weatherData = null;
            try {
                weatherData = HttpClient.EnGzipGetHttp(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
           // System.out.println(weatherData);
            JSONObject JsonWeather = JSON.parseObject(weatherData);
            if (!JsonWeather.getString("code").equals("200")) {
                return Result.resultStatus(400,"参数错误");
            }

            String dateStr = JsonWeather.getString("daily");
            String jsonDateString = dateStr.substring(1, dateStr.length() - 1);
            //System.out.println(jsonDateString);
            String[] arry = jsonDateString.split("},");
            for (int i = 0; i < arry.length; i++) {
                if (arry[i].charAt(arry[i].length() - 1) != '}') {
                    arry[i] = arry[i] + "}";
                }
                //System.out.println(arry[i]);
            }
            List<SevenWeather> list = new ArrayList<SevenWeather>();
            SevenWeather[] listSevenWeather = new SevenWeather[7];
            SimpleDateFormat formatterDate = new SimpleDateFormat("M.dd");
            Calendar calDate = Calendar.getInstance();
            String localDate = formatterDate.format(calDate.getTime());
            for (int i = 0; i < 7; i++) {
                JSONObject jsonObject = JSON.parseObject(arry[i]);
               // System.out.println(jsonObject);
                SevenWeather sevenWeather = new SevenWeather();
                sevenWeather.setCityId(localId);
                sevenWeather.setDate(localDate);
                calDate.add(Calendar.DAY_OF_MONTH, 1);
                localDate = formatterDate.format(calDate.getTime());
                sevenWeather.setFxDate(jsonObject.getString("fxDate"));
                sevenWeather.setTempMax(jsonObject.getString("tempMax"));
                sevenWeather.setTempMin(jsonObject.getString("tempMin"));
                sevenWeather.setTextDay(jsonObject.getString("textDay"));
                sevenWeather.setTextNight(jsonObject.getString("textNight"));
                sevenWeather.setIconDay(jsonObject.getString("iconDay"));
                sevenWeather.setIconNight(jsonObject.getString("iconNight"));
                listSevenWeather[i] = sevenWeather;
                //System.out.println(listSevenWeather[i]);
                SevenWeather sevenWeathers = sevenWeatherRepository.findWeather(jsonObject.getString("fxDate"), localId);
                if (sevenWeathers == null) {
                    sevenWeatherRepository.save(listSevenWeather[i]);
                }
                list.add(listSevenWeather[i]);
            }
            //System.out.println(list);
            return Result.resultData(200, list);
        } else {
            List<SevenWeather> list = new ArrayList<SevenWeather>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            String localDate = simpleDateFormat.format(calendar.getTime());
           // System.out.println(localDate);
            SevenWeather[] listSevenWeather = new SevenWeather[7];
            for (int i = 0; i < 7; i++) {
                SevenWeather weather = sevenWeatherRepository.findWeather(localDate, localId);
                list.add(weather);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                localDate = simpleDateFormat.format(calendar.getTime());
                //System.out.println(localDate);
            }
            //System.out.println("执行");
            return Result.resultData(200, list);
        }
    }

    @Override
    public String getWeather24(String id) {
        if (id.isEmpty()) {
            return Result.resultData(500, "参数为空");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 1);
        //String localTimeFirst = formatter.format(cal.getTime()) + "00+08:00";
        String localTimeFirst = formatter.format(cal.getTime());
        //System.out.println(localTimeFirst);
        cal.add(Calendar.HOUR, 23);
       // String localTimeFinal = formatter.format(cal.getTime()) + "00+08:00";
        String localTimeFinal = formatter.format(cal.getTime());
        //System.out.println(localTimeFinal);
        TwWeather dateFirst = twWeatherRepository.findWeather24(localTimeFirst, id);
        TwWeather dateFinal = twWeatherRepository.findWeather24(localTimeFinal, id);
        //System.out.println(dateFirst == null);
        //System.out.println(dateFinal == null);
        if (dateFinal == null || dateFirst == null) {
            //System.out.println("执行");
            String url = "https://devapi.heweather.net/v7/weather/24h?location=" + id + "&key=1039bb39c7264698b2fb6234f8fc4e74";
            String weatherData = null;
            try {
                weatherData = HttpClient.EnGzipGetHttp(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject JsonWeather = JSON.parseObject(weatherData);
            //System.out.println(JsonWeather);
            if (!JsonWeather.getString("code").equals("200")) {
                return Result.resultStatus(400,"参数错误");
            }
            String dateStr = JsonWeather.getString("hourly");
            String jsonDateString = dateStr.substring(1, dateStr.length() - 1);
            //System.out.println(jsonDateString);
            String[] arry = jsonDateString.split("},");
            for (int i = 0; i < arry.length; i++) {
                if (arry[i].charAt(arry[i].length() - 1) != '}') {
                    arry[i] = arry[i] + "}";
                }
                //System.out.println(arry[i]);
            }
            List<TwWeather> list = new ArrayList<TwWeather>();
            TwWeather[] listTwWeather = new TwWeather[24];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:00");
            Calendar simpCal = Calendar.getInstance();
            String time = simpleDateFormat.format(simpCal.getTime());
            for (int i = 0; i < 24; i++) {
                JSONObject jsonObject = JSON.parseObject(arry[i]);
                //System.out.println(jsonObject);
                TwWeather twWeather = new TwWeather();
                twWeather.setTime(time);//
                simpCal.add(Calendar.HOUR, 1);
                time = simpleDateFormat.format(simpCal.getTime());
                twWeather.setCityId(id);
                twWeather.setFxTime(jsonObject.getString("fxTime"));
                twWeather.setHumidity(jsonObject.getString("humidity"));
                twWeather.setTemp(jsonObject.getString("temp"));
                twWeather.setText(jsonObject.getString("text"));
                twWeather.setWindDir(jsonObject.getString("windDir"));
                twWeather.setWindScale(jsonObject.getString("windScale"));
                twWeather.setIcon(jsonObject.getString("icon"));
                listTwWeather[i] = twWeather;
                //System.out.println(listTwWeather[i]);
                TwWeather twWeathers = twWeatherRepository.findWeather24(jsonObject.getString("fxTime"), id);
               // System.out.println(twWeathers);
                if (twWeathers == null) {
                    twWeatherRepository.save(listTwWeather[i]);
                }
                list.add(listTwWeather[i]);
            }
            //System.out.println(list);
            return Result.resultData(200, list);
        } else {
            List<TwWeather> list = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 1);
            //String localTime = dateFormat.format(calendar.getTime()) + "00+08:00";
            String localTime = dateFormat.format(calendar.getTime());
            //System.out.println(localTime);
            TwWeather[] listTwWeather = new TwWeather[24];
            for (int i = 0; i < 24; i++) {
                TwWeather twWeather = twWeatherRepository.findWeather24(localTime, id);
                list.add(twWeather);
                calendar.add(Calendar.HOUR, 1);
               // localTime = formatter.format(calendar.getTime()) + "00+08:00";
                localTime = formatter.format(calendar.getTime());
                //System.out.println(localTime);
            }
            System.out.println("执行");
            return Result.resultData(200, list);
        }

    }
}
