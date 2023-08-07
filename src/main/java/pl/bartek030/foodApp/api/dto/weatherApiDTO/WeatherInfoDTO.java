package pl.bartek030.foodApp.api.dto.weatherApiDTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherInfoDTO {
    private String description;
    private String icon;
}
