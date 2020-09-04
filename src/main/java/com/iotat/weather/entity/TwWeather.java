package com.iotat.weather.entity;

import javax.persistence.*;

@Entity
@Table(name = "weather24")
public class TwWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fx_time")
    private String fxTime;

    @Column(name = "temp")
    private String temp;

    @Column(name = "text")
    private String text;

    @Column(name = "wind_dir")
    private String windDir;

    @Column(name = "wind_scale")
    private String windScale;

    @Column(name = "humidity")
    private String humidity;

    @Column(name = "city_id")
    private String cityId;

    @Column(name = "time")
    private String time;

    @Column(name = "icon")
    private String icon;

    @Override
    public String toString() {
        return "TwWeather{" +
                "id=" + id +
                ", fxTime='" + fxTime + '\'' +
                ", temp='" + temp + '\'' +
                ", text='" + text + '\'' +
                ", windDir='" + windDir + '\'' +
                ", windScale='" + windScale + '\'' +
                ", humidity='" + humidity + '\'' +
                ", cityId='" + cityId + '\'' +
                ", time='" + time + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFxTime() {
        return fxTime;
    }

    public void setFxTime(String fxTime) {
        this.fxTime = fxTime;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindScale() {
        return windScale;
    }

    public void setWindScale(String windScale) {
        this.windScale = windScale;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
