package com.danish.journalApp.api.responce;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponce {
    public Main main;
    public int visibility;
    public int timezone;
    public String name;


    public class Main {
        public double temp;
        @JsonProperty("feels_like")
        public double feelsLike;
        public int pressure;
        public int humidity;


    }
}