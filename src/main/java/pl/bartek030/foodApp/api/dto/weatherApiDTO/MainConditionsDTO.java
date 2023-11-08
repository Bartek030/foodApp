package pl.bartek030.foodApp.api.dto.weatherApiDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainConditionsDTO {

    private Double temp;
    private Integer pressure;
    private Integer humidity;
}
