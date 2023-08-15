package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.weatherApiDTO.WeatherDataDTO;

@UtilityClass
public class WeatherDataDTOExample {

    public WeatherDataDTO someWeatherDataDTO() {
        return WeatherDataDTO.builder().build();
    }
}
