package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;

import java.util.List;

public interface RestaurantDeliveryAddressDao {
    List<RestaurantDeliveryAddress> findByAddress(DeliveryAddress deliveryAddress);
}
