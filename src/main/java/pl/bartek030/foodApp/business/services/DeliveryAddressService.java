package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;

import java.util.List;
import java.util.Optional;

public interface DeliveryAddressService {
    Optional<DeliveryAddress> findByCountryAndCityAndStreet(String country, String city, String street);

    List<DeliveryAddress> findDeliveryAddressesByIdList(List<Long> deliveryAddressIdList);

    DeliveryAddress addDeliveryAddress(DeliveryAddress deliveryAddress);
}
