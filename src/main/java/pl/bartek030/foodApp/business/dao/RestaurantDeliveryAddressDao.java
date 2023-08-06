package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;

import java.util.List;
import java.util.Optional;

public interface RestaurantDeliveryAddressDao {
    List<RestaurantDeliveryAddress> findAllByAddress(DeliveryAddress deliveryAddress);

    Optional<RestaurantDeliveryAddress> findByAddressAndRestaurant(DeliveryAddress address, Restaurant restaurant);

    List<RestaurantDeliveryAddress> findAllByRestaurant(final Restaurant restaurant);

    List<RestaurantDeliveryAddress> findByDeliveryAddress(DeliveryAddress deliveryAddress);

    void addRestaurantDeliveryAddress(RestaurantDeliveryAddress restaurantDeliveryAddress);
}
