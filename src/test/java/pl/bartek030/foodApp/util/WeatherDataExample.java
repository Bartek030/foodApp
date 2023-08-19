package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.weatherApi.WeatherData;

@UtilityClass
public class WeatherDataExample {

    public WeatherData someWeatherData() {
        return WeatherData.builder().build();
    }
}
