package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;
import pl.bartek030.foodApp.infrastructure.database.entity.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"name"})
public class Restaurant {

    Long restaurantId;
    String name;
    FoodAppUser foodAppUser;
    Address address;
    Set<Menu> menus;
    Set<AppOrder> appOrders;
    Set<RestaurantDeliveryAddress> restaurantDeliveryAddresses;
}
