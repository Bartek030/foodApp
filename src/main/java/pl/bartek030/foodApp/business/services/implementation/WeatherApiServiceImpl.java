package pl.bartek030.foodApp.business.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.bartek030.foodApp.business.serviceModel.weatherApi.WeatherData;
import pl.bartek030.foodApp.business.services.WeatherApiService;

@Service
@RequiredArgsConstructor
public class WeatherApiServiceImpl implements WeatherApiService {

    @Value("${api.open-weather-map.apiKey}")
    private String apiKey;

    @Value("${api.open-weather-map.language}")
    private String apiLanguage;

    @Value("${api.open-weather-map.units}")
    private String units;

    private final WebClient webClient;

    @Override
    public WeatherData getCurrentWeather(final String cityName) {
        try {
            return webClient.get()
                    .uri("?appid=" + apiKey + "&lang=" + apiLanguage + "&units=" + units + "&q=" + cityName)
                    .retrieve()
                    .bodyToMono(WeatherData.class)
                    .block();
        } catch (Exception e) {
            // TODO: Custom exception
            throw new RuntimeException();
        }
    }
}
