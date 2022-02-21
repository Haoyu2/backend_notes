package com.example.details.pojo;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.Date;

@Data
public class CityWeather {
    @JsonAlias("title")
    private String city;
    private Date time;
    private Date sun_rise, sun_set;
    @JsonAlias("timezone_name")
    private String time_zone;
    private City parent;

    @JsonAlias("consolidated_weather")
    private WeatherState[] weatherState;
}
