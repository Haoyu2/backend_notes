package com.example.details.service;

import com.example.details.pojo.City;
import com.example.details.pojo.CityWeather;
import com.example.details.webclient.WeatherFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Primary
public class WeatherServiceFeign  implements WeatherService{

    WeatherFeign weatherFeign;

    @Autowired
    public WeatherServiceFeign(WeatherFeign weatherFeign) {
        this.weatherFeign = weatherFeign;
    }

    @Override
    public List<Integer> findCityIdByName(String city) {
        return Arrays.stream(weatherFeign.findCityIdByName(city))
                .map(City::getWoeid)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Map> findCityNameById(int id) {
        return null;
    }

    public CompletableFuture<CityWeather> getWeatherByID(int id){
        System.out.println(id);
        return CompletableFuture
                .supplyAsync(() ->weatherFeign.findWeatherByCityID(id));
    }

    @Override
    public List<CityWeather> findWheatherByCity(String city) {
        return findCityIdByName(city).parallelStream()
                .map(this::getWeatherByID)
                .map(f->f.join())
                .collect(Collectors.toList());
    }
}
