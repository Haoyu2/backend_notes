package com.example.details.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.Date;

@Data
public class WeatherState {
    private long id;
    @JsonAlias({"weather_state_name"})
    private String name;
    @JsonAlias({"applicable_date"})
    private Date date;
    private float humidity, air_pressure,visibility;
    private float the_temp, min_temp,max_temp;
}
