package pl.bartek030.foodApp.business.serviceModel.weatherApi;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherInfo {
    String description;
    String icon;
}
