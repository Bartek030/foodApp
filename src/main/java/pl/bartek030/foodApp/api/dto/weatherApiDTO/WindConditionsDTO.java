package pl.bartek030.foodApp.api.dto.weatherApiDTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WindConditionsDTO {
    private Double speed;
}
