package com.example.details;


import com.example.details.pojo.City;
import com.example.details.pojo.CityWeather;
import com.example.details.service.WeatherService;
import com.example.details.service.WeatherServiceFeign;
import com.example.details.service.WeatherServiceImpl;
import com.example.details.webclient.WeatherFeign;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RunWith(SpringRunner.class)
@SpringBootTest

public class OpenFeignTest {
    @Autowired
    private WeatherFeign weatherClient;
    @Autowired
    @Qualifier("weatherServiceFeign")
    private WeatherService weatherService;


    @Test
    public void getCity(){
//        System.out.println(weatherClient.findCityIdByName("boston"));
        City[] cities = weatherClient.findCityIdByName("boston");
        System.out.println(Arrays.toString(cities));
        System.out.println(cities[0].getWoeid());



        int id = cities[0].getWoeid();
        System.out.println(weatherClient.findWeatherByCityID(id));
//        CityWeather cityWeather = (CityWeather) weatherClient.findWeatherByCityID(id);
//        System.out.println(cityWeather);

    }

    @Test
    public void getWeather(){
        City[] cities = weatherClient.findCityIdByName("boston");
        System.out.println(Arrays.toString(cities));
        System.out.println(weatherClient.findWeatherByCityID(cities[0].getWoeid()));

        CompletableFuture<CityWeather> f = new WeatherServiceFeign(weatherClient).getWeatherByID(cities[0].getWoeid());
        System.out.println(f);
        System.out.println(f.join());
        System.out.println(weatherService.findWheatherByCity("Boston"));

    }

    @Test
    public void getWeatherRestTemplate(){
        WeatherServiceImpl weatherService = new WeatherServiceImpl(new RestTemplate());
        List<Integer> cities = weatherService.findCityIdByName("boston");
        System.out.println(weatherService.findWeatherById(cities.get(0)));
        System.out.println(weatherService.findWheatherByCity("Boston"));

    }
}
