package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;

@UtilityClass
public class RestaurantDeliveryAddressExample {

    public RestaurantDeliveryAddress someRestaurantDeliveryAddress1() {
        return RestaurantDeliveryAddress.builder()
                .restaurantDeliveryAddressId(1L)
                .deliveryTime(50)
                .deliveryAddress(DeliveryAddressExample.someDeliveryAddress1())
                .restaurant(RestaurantExample.someRestaurant1())
                .build();
    }

    public RestaurantDeliveryAddress someRestaurantDeliveryAddress2() {
        return RestaurantDeliveryAddress.builder()
                .restaurantDeliveryAddressId(2L)
                .deliveryTime(45)
                .deliveryAddress(DeliveryAddressExample.someDeliveryAddress1())
                .restaurant(RestaurantExample.someRestaurant2())
                .build();
    }

    public RestaurantDeliveryAddress someRestaurantDeliveryAddress3() {
        return RestaurantDeliveryAddress.builder()
                .restaurantDeliveryAddressId(3L)
                .deliveryTime(30)
                .deliveryAddress(DeliveryAddressExample.someDeliveryAddress1())
                .restaurant(RestaurantExample.someRestaurant3())
                .build();
    }
}
