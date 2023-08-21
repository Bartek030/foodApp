package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

@With
@Data
@Builder
@EqualsAndHashCode(of = {"email"})
@ToString(of = {"name", "surname"})
public class FoodAppUserCreation {
    String name;
    String surname;
    String email;
    String password;
    String phone;
    String country;
    String city;
    String street;
    String number;
    String zipCode;
    Boolean isOwner;
}
