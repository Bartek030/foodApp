package pl.bartek030.foodApp.business.services.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.RestaurantDeliveryAddressDao;
import pl.bartek030.foodApp.business.serviceModel.Address;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;
import pl.bartek030.foodApp.business.services.DeliveryAddressService;
import pl.bartek030.foodApp.business.services.RestaurantDeliveryAddressService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantDeliveryAddressServiceImpl implements RestaurantDeliveryAddressService {

    private final RestaurantDeliveryAddressDao restaurantDeliveryAddressDao;

    private final DeliveryAddressService deliveryAddressService;

    @Override
    public List<RestaurantDeliveryAddress> findByAddress(final DeliveryAddress deliveryAddress) {
        return restaurantDeliveryAddressDao.findByAddress(deliveryAddress);
    }

    @Override
    public RestaurantDeliveryAddress findByAddressAndRestaurant(final Address address, final Restaurant restaurant) {
        final DeliveryAddress deliveryAddress = deliveryAddressService.findByCountryAndCityAndStreet(
                address.getCountry(),
                address.getCity(),
                address.getStreet())
                // TODO: Custom exception
                .orElseThrow();
        return restaurantDeliveryAddressDao.findByAddressAndRestaurant(deliveryAddress, restaurant).orElseThrow();
    }
}
