package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;

import java.util.Optional;

public interface DeliveryAddressDao {
    Optional<DeliveryAddress> findByCountryAndCityAndStreet(String country, String city, String street);
}
