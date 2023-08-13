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
}
