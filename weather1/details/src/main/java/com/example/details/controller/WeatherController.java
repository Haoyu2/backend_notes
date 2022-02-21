package com.example.details.controller;

import com.example.details.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WeatherController {

    private final WeatherService weatherService;


    @Autowired
    public WeatherController(@Qualifier( "weatherServiceFeign") WeatherService weatherService) {
        this.weatherService = weatherService;
    }



    @GetMapping("/details")
    public ResponseEntity<?> queryWeatherByCity(@RequestParam(required = true) String city) {
        return new ResponseEntity<>(weatherService.findWheatherByCity(city), HttpStatus.OK);
    }

    @GetMapping("/details/{city}")
    public ResponseEntity<?> findCityIdByName(@PathVariable String  city) {
        return new ResponseEntity<>(weatherService.findCityIdByName(city), HttpStatus.OK);
    }


}
