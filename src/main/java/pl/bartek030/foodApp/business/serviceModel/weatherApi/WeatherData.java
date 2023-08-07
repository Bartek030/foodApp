package pl.bartek030.foodApp.business.serviceModel.weatherApi;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherData {

    MainConditions main;
    WindConditions wind;
    List<WeatherInfo> weather;
}
