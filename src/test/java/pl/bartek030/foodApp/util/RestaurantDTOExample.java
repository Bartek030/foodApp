package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;

@UtilityClass
public class RestaurantDTOExample {

    public RestaurantDTO someRestaurantDTO1() {
        return RestaurantDTO.builder()
                .restaurantId(1L)
                .name("SOME RESTAURANT 1")
                .build();
    }

    public RestaurantDTO someRestaurantDTO2() {
        return RestaurantDTO.builder()
                .restaurantId(2L)
                .name("SOME RESTAURANT 2")
                .build();
    }
}
