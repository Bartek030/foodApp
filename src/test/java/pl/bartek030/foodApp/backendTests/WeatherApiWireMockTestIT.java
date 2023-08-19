package pl.bartek030.foodApp.backendTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.bartek030.foodApp.api.dto.weatherApiDTO.WeatherDataDTO;
import pl.bartek030.foodApp.configuration.RestAssuredIntegrationTestBase;
import pl.bartek030.foodApp.configuration.support.WeatherApiControllerTestSupport;
import pl.bartek030.foodApp.configuration.support.WireMockTestSupport;

public class WeatherApiWireMockTestIT extends RestAssuredIntegrationTestBase
        implements WireMockTestSupport, WeatherApiControllerTestSupport {

//    @Test
//    void shouldReturnWeatherDataCorrectly() {
//        // given
//        String cityName = "Warszawa";
//        Double temperature = 304.25;
//        Integer pressure = 1017;
//        Double windSpeed = 1.54;
//
//        stubForWeatherApi(wireMockServer, cityName);
//
//        // when
//        final WeatherDataDTO weatherData = getWeatherData(cityName);
//
//        // then
//        Assertions.assertEquals(temperature, weatherData.getMain().getTemp());
//        Assertions.assertEquals(pressure, weatherData.getMain().getPressure());
//        Assertions.assertEquals(windSpeed, weatherData.getWind().getSpeed());
//    }
}
