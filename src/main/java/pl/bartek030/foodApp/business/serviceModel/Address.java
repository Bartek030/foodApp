package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "addressId")
@ToString(of = {"country", "city", "street", "number", "zipCode"})
public class Address {
    Long addressId;
    String country;
    String city;
    String street;
    String number;
    String zipCode;
}
