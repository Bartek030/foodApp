package pl.bartek030.foodApp.api.dto.weatherApiDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDataDTO {

    private MainConditionsDTO main;
    private WindConditionsDTO wind;
    private List<WeatherInfoDTO> weather;
}
