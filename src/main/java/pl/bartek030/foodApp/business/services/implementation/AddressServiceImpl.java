package pl.bartek030.foodApp.business.services.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.AddressDAO;
import pl.bartek030.foodApp.business.serviceModel.Address;
import pl.bartek030.foodApp.business.serviceModel.RestaurantCreation;
import pl.bartek030.foodApp.business.services.AddressService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressDAO addressDAO;

    @Override
    public Optional<Address> findAddressByData(
            final String country,
            final String city,
            final String street,
            final String number,
            final String zipCode
    ) {
        // TODO: Custom exception
        return addressDAO.findAddressByData(country, city, street, number, zipCode);
    }

    @Override
    public Address createAddressFromRestaurant(final RestaurantCreation restaurantCreation) {
        final Address address = Address.builder()
                .country(restaurantCreation.getCountry())
                .city(restaurantCreation.getCity())
                .street(restaurantCreation.getStreet())
                .number(restaurantCreation.getNumber())
                .zipCode(restaurantCreation.getZipCode())
                .build();
        return addressDAO.createAddressFromRestaurant(address);
    }

    @Override
    public Address createAddress(final Address address) {
        return addressDAO.createAddress(address);
    }
}
