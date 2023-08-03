package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantDeliveryAddressId")
@ToString(of = {"restaurant", "deliveryAddress"})
public class RestaurantDeliveryAddress {

    Long restaurantDeliveryAddressId;
    Integer deliveryTime;
    DeliveryAddress deliveryAddress;
    Restaurant restaurant;
}
