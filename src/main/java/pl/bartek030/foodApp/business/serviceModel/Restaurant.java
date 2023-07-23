package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

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
//    Set<MenuEntity> menus;
//    Set<AppOrderEntity> appOrders;
//    Set<RestaurantDeliveryAddressEntity> restaurantDeliveryAddresses;
}
