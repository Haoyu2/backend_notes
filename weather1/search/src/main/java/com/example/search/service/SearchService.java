package com.example.search.service;

import com.example.details.pojo.CityWeather;
import com.example.search.client.DetailsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class SearchService {
    DetailsClient detailsClient;

    @Autowired
    public SearchService(DetailsClient detailsClient) {
        this.detailsClient = detailsClient;
    }

    public List<CityWeather> getWeatherCities(List<String> cities){
        return cities.parallelStream()
                .map((city)-> CompletableFuture.supplyAsync(()-> detailsClient.queryWeatherByCity(city)))
                .map(f->f.join())
                .collect(Collectors.toList());
    }
}
