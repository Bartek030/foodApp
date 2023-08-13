package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.weatherApi.WeatherData;

public interface WeatherApiService {
    WeatherData getCurrentWeather(String cityName);
}
