package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"country", "city", "street", "deliveryTime"})
@ToString(of = {"city", "street"})
public class DeliveryAddressCreation {
    String country;
    String city;
    String street;
    Integer deliveryTime;

    Long restaurantId;
}
