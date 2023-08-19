package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.RestaurantCreationDTO;

@UtilityClass
public class RestaurantCreationDTOExample {

    public RestaurantCreationDTO someRestaurantCreationDTO1() {
        return RestaurantCreationDTO.builder()
                .name("SOME RESTAURANT 1")
                .country("Polska")
                .city("Warszawa")
                .street("Dluga")
                .number("22")
                .zipCode("30-300")
                .foodAppUserId(1L)
                .build();
    }
}
