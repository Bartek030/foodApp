package pl.bartek030.foodApp.business.serviceModel.weatherApi;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainConditions {

    Double temp;
    Integer pressure;
    Integer humidity;
}
