package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartek030.foodApp.api.dto.weatherApiDTO.WeatherDataDTO;

@RequestMapping(WeatherApiController.WEATHER_API_URL)
public interface WeatherApiController {

    String WEATHER_API_URL = "/api/weather/{cityName}";

    @GetMapping
    ResponseEntity<WeatherDataDTO> getWeatherData(@PathVariable String cityName);
}
