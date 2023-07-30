package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;

import java.util.Optional;

public interface DeliveryAddressService {
    Optional<DeliveryAddress> findByCountryAndCityAndStreet(String country, String city, String street);
}
