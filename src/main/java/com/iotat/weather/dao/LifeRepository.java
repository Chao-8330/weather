package com.iotat.weather.dao;

import com.iotat.weather.entity.Life;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LifeRepository extends JpaRepository<Life,Integer> {
    @Query(value = "select * from life where date = :date and city_id = :city_id",nativeQuery = true)
    Life findData(@Param("date") String date,@Param("city_id")String cityId);


}
