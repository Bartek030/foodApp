package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.Address;

import java.util.Optional;

public interface AddressDAO {

    Optional<Address> findAddressByData(
            final String country,
            final String city,
            final String street,
            final String number,
            final String zipCode
    );

    Address createAddress(Address address);
}
