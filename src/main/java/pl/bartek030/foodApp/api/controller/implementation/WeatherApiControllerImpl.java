package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.WeatherApiController;
import pl.bartek030.foodApp.api.dto.mapper.WeatherDataDtoMapper;
import pl.bartek030.foodApp.api.dto.weatherApiDTO.WeatherDataDTO;
import pl.bartek030.foodApp.business.serviceModel.weatherApi.WeatherData;
import pl.bartek030.foodApp.business.services.WeatherApiService;

@RestController
@AllArgsConstructor
public class WeatherApiControllerImpl implements WeatherApiController {

    private final WeatherDataDtoMapper weatherDataDtoMapper;
    private final WeatherApiService weatherApiService;

    @Override
    public ResponseEntity<WeatherDataDTO> getWeatherData(final String cityName) {
        WeatherData weatherData = weatherApiService.getCurrentWeather(cityName);
        return ResponseEntity.ok(weatherDataDtoMapper.map(weatherData));
    }
}
