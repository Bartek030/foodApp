package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.Address;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;

import java.util.List;

public interface RestaurantDeliveryAddressService {
    List<RestaurantDeliveryAddress> findByAddress(DeliveryAddress deliveryAddress);

    RestaurantDeliveryAddress findByAddressAndRestaurant(Address address, Restaurant restaurant);
}
