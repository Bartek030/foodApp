package pl.bartek030.foodApp.api.dto.weatherApiDTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainConditionsDTO {

    private Double temp;
    private Integer pressure;
    private Integer humidity;
}
