package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"name"})
public class RestaurantCreation {
    Long restaurantId;
    String name;

    Long foodAppUserId;

    String country;
    String city;
    String street;
    String number;
    String zipCode;
}
