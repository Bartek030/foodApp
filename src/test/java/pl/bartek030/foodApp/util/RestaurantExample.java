package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;

@UtilityClass
public class RestaurantExample {

    public Restaurant someRestaurant1() {
        return Restaurant.builder()
                .restaurantId(1L)
                .name("SOME RESTAURANT 1")
                .foodAppUser(FoodAppUserExample.someFoodAppUser1())
                .address(AddressExample.someAddress1())
                .build();
    }
}
