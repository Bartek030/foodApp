package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;

@UtilityClass
public class RestaurantEntityExample {

    public RestaurantEntity someRestaurantEntity1() {
        return RestaurantEntity.builder()
                .restaurantId(1L)
                .name("RESTAURANT 1")
                .foodAppUser(FoodAppUserEntityExample.someFoodAppUserEntity1())
                .address(AddressEntityExample.someAddressEntity1())
                .build();
    }

    public RestaurantEntity someRestaurantEntity2() {
        return RestaurantEntity.builder()
                .restaurantId(2L)
                .name("RESTAURANT 2")
                .foodAppUser(FoodAppUserEntityExample.someFoodAppUserEntity1())
                .address(AddressEntityExample.someAddressEntity2())
                .build();
    }

    public RestaurantEntity someRestaurantEntity3() {
        return RestaurantEntity.builder()
                .restaurantId(3L)
                .name("RESTAURANT 3")
                .foodAppUser(FoodAppUserEntityExample.someFoodAppUserEntity1())
                .address(AddressEntityExample.someAddressEntity3())
                .build();
    }
}
