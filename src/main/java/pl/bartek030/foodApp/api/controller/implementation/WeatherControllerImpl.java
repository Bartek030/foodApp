package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.bartek030.foodApp.api.controller.WeatherController;

@Controller
@AllArgsConstructor
public class WeatherControllerImpl implements WeatherController {

    @Override
    public String getWeatherData() {
        return "weather";
    }
}
