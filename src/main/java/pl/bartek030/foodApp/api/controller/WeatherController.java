package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartek030.foodApp.api.dto.weatherApiDTO.WeatherDataDTO;

@RequestMapping(WeatherController.WEATHER_URL)
public interface WeatherController {

    String WEATHER_URL = "/weather";

    @GetMapping
    String getWeatherData();
}
