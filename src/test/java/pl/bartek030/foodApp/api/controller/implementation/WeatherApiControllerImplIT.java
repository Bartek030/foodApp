package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import pl.bartek030.foodApp.api.controller.WeatherApiController;
import pl.bartek030.foodApp.api.dto.mapper.WeatherDataDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.weatherApi.WeatherData;
import pl.bartek030.foodApp.business.services.WeatherApiService;
import pl.bartek030.foodApp.util.WeatherDataDTOExample;
import pl.bartek030.foodApp.util.WeatherDataExample;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WeatherApiControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-test.yml")
@AllArgsConstructor(onConstructor = @__(@Autowired))
class WeatherApiControllerImplIT {

    private MockMvc mockMvc;

    @MockBean
    private WeatherDataDtoMapper weatherDataDtoMapper;

    @MockBean
    private WeatherApiService weatherApiService;

    @Test
    void shouldReturnWeatherData() throws Exception {
        // given
        String cityName = "Warszawa";
        when(weatherApiService.getCurrentWeather(anyString())).thenReturn(WeatherDataExample.someWeatherData());
        when(weatherDataDtoMapper.map(any(WeatherData.class))).thenReturn(WeatherDataDTOExample.someWeatherDataDTO());

        // when then
        String endpoint = WeatherApiController.WEATHER_API_URL;
        mockMvc.perform(get(endpoint, cityName))
                .andExpect(status().isOk());
    }
}