package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;

import java.util.List;

public interface RestaurantDeliveryAddressService {
    List<RestaurantDeliveryAddress> findByAddress(DeliveryAddress deliveryAddress);
}
