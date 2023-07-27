package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;
import pl.bartek030.foodApp.infrastructure.database.entity.DeliveryAddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantDeliveryAddressId")
@ToString(of = {"restaurant", "deliveryAddress"})
public class RestaurantDeliveryAddress {

    Long restaurantDeliveryAddressId;
    OffsetDateTime deliveryTime;
    DeliveryAddressEntity deliveryAddress;
    RestaurantEntity restaurant;
}
