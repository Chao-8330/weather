package com.iotat.weather.dao;

import com.iotat.weather.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Integer> {
    @Query(value = "select * from city where c_adm1 = :c_adm1 and c_adm2 = :c_adm2 and c_name = :c_name", nativeQuery = true)
    List<City> findResult(@Param("c_adm1") String adm1, @Param("c_adm2") String adm2, @Param("c_name") String c_name);
}
