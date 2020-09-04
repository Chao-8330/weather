package com.iotat.weather.entity;

import javax.persistence.*;

@Entity
@Table(name = "life")
public class Life {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "api")
    private String api;

    @Column(name = "date")
    private String date;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private String level;

    @Column(name = "category")
    private String category;

    @Column(name = "text")
    private String text;

    @Column(name = "city_id")
    private String cityId;

    @Override
    public String toString() {
        return "Life{" +
                "id=" + id +
                ", api='" + api + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", category='" + category + '\'' +
                ", text='" + text + '\'' +
                ", cityId='" + cityId + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
