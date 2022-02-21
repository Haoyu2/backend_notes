package com.example.details.webclient;
import com.example.details.pojo.City;
import com.example.details.pojo.CityWeather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "metaweather", url = "https://www.metaweather.com/api")
public interface WeatherFeign {
    @GetMapping("/location/search")
    public City[] findCityIdByName(@RequestParam(required = true) String query);
    @GetMapping("/location/{id}")
    public CityWeather findWeatherByCityID(@PathVariable("id") int id);
}
