package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "foodAppUserId")
@ToString(of = {"name", "surname", "email"})
public class FoodAppUser {
    Long foodAppUserId;
    String name;
    String surname;
    String email;
    String phone;
    Address address;
    Set<Restaurant> restaurants;
    Set<AppOrder> appOrders;
    User user;
}
