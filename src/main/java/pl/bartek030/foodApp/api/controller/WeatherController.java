package pl.bartek030.foodApp.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(WeatherController.WEATHER_URL)
public interface WeatherController {

    String WEATHER_URL = "/weather";

    @GetMapping
    String getWeatherData();
}
