package com.example.search.client;

import com.example.details.pojo.CityWeather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("weather-details-service")
public interface DetailsClient {
    @GetMapping("/details")
    CityWeather queryWeatherByCity(@RequestParam(required = true)String city);

}

