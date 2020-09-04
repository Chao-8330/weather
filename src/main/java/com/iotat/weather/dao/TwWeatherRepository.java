package com.iotat.weather.dao;

import com.iotat.weather.entity.TwWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TwWeatherRepository extends JpaRepository<TwWeather,Integer> {
    @Query(value = "select * from weather24 where fx_time like %:fx_time% and city_id = :city_id",nativeQuery = true)
    TwWeather findWeather24(@Param("fx_time") String localTime,@Param("city_id")String cityId);
}
