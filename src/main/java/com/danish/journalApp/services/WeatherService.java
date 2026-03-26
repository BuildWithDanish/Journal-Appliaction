package com.danish.journalApp.services;

import com.danish.journalApp.api.responce.WeatherResponce;
import com.danish.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private AppCache appCache;

    @Value("${weather.api.key}")
    private String apiKey;


    @Autowired
    RestTemplate restTemplate;

    public WeatherResponce getWeather(String city) {
        String FINAL_API = appCache.getAppCache().get("weather_api").replace("<apiKey>", apiKey).replace("<city>", city);
        ResponseEntity<WeatherResponce> response = restTemplate.exchange(FINAL_API, HttpMethod.GET, null, WeatherResponce.class);
        WeatherResponce body = response.getBody();
        return body;
    }

}
