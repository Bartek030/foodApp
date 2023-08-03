package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantDeliveryAddressEntity;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "deliveryAddressId")
@ToString(of = {"city", "street"})
public class DeliveryAddress {
    Long deliveryAddressId;
    String country;
    String city;
    String street;
    Set<RestaurantDeliveryAddressEntity> restaurantDeliveryAddresses;
}
