package pl.bartek030.foodApp.business.services.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.DeliveryAddressDao;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.services.DeliveryAddressService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private final DeliveryAddressDao deliveryAddressDao;

    @Override
    public Optional<DeliveryAddress> findByCountryAndCityAndStreet(
            final String country,
            final String city,
            final String street
    ) {
        return deliveryAddressDao.findByCountryAndCityAndStreet(country, city, street);
    }
}
