package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddressCreation;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;

import java.util.List;

public interface RestaurantDeliveryAddressService {
    List<RestaurantDeliveryAddress> findByAddress(
            final String country,
            final String city,
            final String street
    );

    RestaurantDeliveryAddress findByAddressAndRestaurant(DeliveryAddress address, Restaurant restaurant);

    List<DeliveryAddress> findDeliveryAddressByRestaurant(final Long restaurant);

    List<Restaurant> getRestaurantsByCountryAndCityAndStreet(String country, String city, String street, Integer page);

    void addDeliveryAddress(DeliveryAddressCreation deliveryAddress);
}
