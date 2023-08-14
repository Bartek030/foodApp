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

    public Restaurant someRestaurant2() {
        return Restaurant.builder()
                .restaurantId(1L)
                .name("SOME RESTAURANT 2")
                .foodAppUser(FoodAppUserExample.someFoodAppUser1())
                .address(AddressExample.someAddress2())
                .build();
    }

    public Restaurant someRestaurant3() {
        return Restaurant.builder()
                .restaurantId(3L)
                .name("SOME RESTAURANT 3")
                .foodAppUser(FoodAppUserExample.someFoodAppUser1())
                .address(AddressExample.someAddress3())
                .build();
    }
}
