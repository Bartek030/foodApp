package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.Address;
import pl.bartek030.foodApp.business.serviceModel.RestaurantCreation;

import java.util.Optional;

public interface AddressService {
    Optional<Address> findAddressByData(
            final String country,
            final String city,
            final String street,
            final String number,
            final String zipCode
    );

    Address createAddressFromRestaurant(RestaurantCreation restaurantCreation);

    Address createAddress(Address address);
}
