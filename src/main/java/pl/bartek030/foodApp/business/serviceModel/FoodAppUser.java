package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

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
//    Set<RestaurantEntity> restaurants;
//    Set<AppOrderEntity> appOrders;
}
