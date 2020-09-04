package com.iotat.weather.entity;

import javax.persistence.*;


@Entity
@Table(name = "weather7")
public class SevenWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private String date;

    @Column(name = "fx_date")
    private String fxDate;

    @Column(name = "temp_max")
    private String tempMax;

    @Column(name = "temp_min")
    private String tempMin;

    @Column(name = "text_day")
    private String textDay;

    @Column(name = "text_night")
    private String textNight;

    @Column(name = "city_id")
    private String cityId;

    @Column(name = "icon_day")
    private String iconDay;

    @Column(name = "icon_night")
    private String iconNight;

    @Override
    public String toString() {
        return "SevenWeather{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", fxDate='" + fxDate + '\'' +
                ", tempMax='" + tempMax + '\'' +
                ", tempMin='" + tempMin + '\'' +
                ", textDay='" + textDay + '\'' +
                ", textNight='" + textNight + '\'' +
                ", cityId='" + cityId + '\'' +
                ", iconDay='" + iconDay + '\'' +
                ", iconNight='" + iconNight + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFxDate() {
        return fxDate;
    }

    public void setFxDate(String fxDate) {
        this.fxDate = fxDate;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public String getTextNight() {
        return textNight;
    }

    public void setTextNight(String textNight) {
        this.textNight = textNight;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getIconDay() {
        return iconDay;
    }

    public void setIconDay(String iconDay) {
        this.iconDay = iconDay;
    }

    public String getIconNight() {
        return iconNight;
    }

    public void setIconNight(String iconNight) {
        this.iconNight = iconNight;
    }
}
