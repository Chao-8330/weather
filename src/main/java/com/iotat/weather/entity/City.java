package com.iotat.weather.entity;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "c_id")
    private String cId;

    @Column(name = "c_name")
    private String cName;

    @Column(name = "c_adm1")
    private String cAdm1;

    @Column(name = "c_adm2")
    private String cAdm2;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cId='" + cId + '\'' +
                ", cName='" + cName + '\'' +
                ", cAdm1='" + cAdm1 + '\'' +
                ", cAdm2='" + cAdm2 + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAdm1() {
        return cAdm1;
    }

    public void setcAdm1(String cAdm1) {
        this.cAdm1 = cAdm1;
    }

    public String getcAdm2() {
        return cAdm2;
    }

    public void setcAdm2(String cAdm2) {
        this.cAdm2 = cAdm2;
    }
}
