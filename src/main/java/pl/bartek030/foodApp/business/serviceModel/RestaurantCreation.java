package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"name", "country", "city", "street", "number", "zipCode"})
@ToString(of = {"name"})
public class RestaurantCreation {
    String name;

    String country;
    String city;
    String street;
    String number;
    String zipCode;
}
