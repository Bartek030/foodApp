package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantDeliveryAddressEntity;

@UtilityClass
public class RestaurantDeliveryAddressEntityExample {

    public RestaurantDeliveryAddressEntity someRestaurantDeliveryAddressEntity1() {
        return RestaurantDeliveryAddressEntity.builder()
                .restaurantDeliveryAddressId(1L)
                .deliveryTime(30)
                .deliveryAddress(DeliveryAddressEntityExample.someDeliveryAddressEntity1())
                .restaurant(RestaurantEntityExample.someRestaurantEntity1())
                .build();
    }

    public RestaurantDeliveryAddressEntity someRestaurantDeliveryAddressEntity2() {
        return RestaurantDeliveryAddressEntity.builder()
                .restaurantDeliveryAddressId(2L)
                .deliveryTime(30)
                .deliveryAddress(DeliveryAddressEntityExample.someDeliveryAddressEntity1())
                .restaurant(RestaurantEntityExample.someRestaurantEntity1())
                .build();
    }

    public RestaurantDeliveryAddressEntity someRestaurantDeliveryAddressEntity3() {
        return RestaurantDeliveryAddressEntity.builder()
                .restaurantDeliveryAddressId(3L)
                .deliveryTime(30)
                .deliveryAddress(DeliveryAddressEntityExample.someDeliveryAddressEntity1())
                .restaurant(RestaurantEntityExample.someRestaurantEntity1())
                .build();
    }
}
