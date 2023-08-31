package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.RestaurantCreation;

@UtilityClass
public class RestaurantCreationExample {
    public RestaurantCreation someRestaurantCreation1() {
        return RestaurantCreation.builder()
                .name("SOME RESTAURANT 1")
                .country("Polska")
                .city("Warszawa")
                .street("Dluga")
                .number("22")
                .zipCode("30-300")
                .build();
    }
}
