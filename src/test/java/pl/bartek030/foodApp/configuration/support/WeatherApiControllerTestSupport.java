package pl.bartek030.foodApp.configuration.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.bartek030.foodApp.api.controller.rest.WeatherRestApiController;
import pl.bartek030.foodApp.api.dto.weatherApiDTO.WeatherDataDTO;

public interface WeatherApiControllerTestSupport {

    RequestSpecification requestSpecification();

    default WeatherDataDTO getWeatherData(final String cityName) {
        return requestSpecification()
                .pathParam("cityName", cityName)
                .get(WeatherRestApiController.WEATHER_API_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(WeatherDataDTO.class);
    }
}
