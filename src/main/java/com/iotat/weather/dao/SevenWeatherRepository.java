package com.iotat.weather.dao;

import com.iotat.weather.entity.SevenWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SevenWeatherRepository extends JpaRepository<SevenWeather,Integer> {
    @Query(value = "select * from weather7 where fx_date = :fx_date and city_id = :city_id",nativeQuery = true)
    SevenWeather findWeather(@Param("fx_date")String fxDate ,@Param("city_id")String cityId);
}
