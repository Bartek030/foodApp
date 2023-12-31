package pl.bartek030.foodApp.business.services.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.RestaurantDeliveryAddressDao;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddressCreation;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;
import pl.bartek030.foodApp.business.services.DeliveryAddressService;
import pl.bartek030.foodApp.business.services.RestaurantDeliveryAddressService;
import pl.bartek030.foodApp.business.services.RestaurantService;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantDeliveryAddressServiceImpl implements RestaurantDeliveryAddressService {

    private final RestaurantDeliveryAddressDao restaurantDeliveryAddressDao;

    private final DeliveryAddressService deliveryAddressService;
    private final RestaurantService restaurantService;

    @Override
    @Transactional
    public List<RestaurantDeliveryAddress> findByAddress(
            final String country,
            final String city,
            final String street
    ) {
        DeliveryAddress deliveryAddress = deliveryAddressService.findByCountryAndCityAndStreet(country, city, street)
                .orElseThrow(() -> new RuntimeException("Delivery address not found"));
        return restaurantDeliveryAddressDao.findAllByAddress(deliveryAddress);
    }

    @Override
    @Transactional
    public RestaurantDeliveryAddress findByAddressAndRestaurant(
            final DeliveryAddress deliveryAddress,
            final Restaurant restaurant
    ) {
        return restaurantDeliveryAddressDao.findByAddressAndRestaurant(deliveryAddress, restaurant)
                .orElseThrow(() -> new RuntimeException("Restaurant delivery address not found"));
    }

    @Override
    @Transactional
    public List<DeliveryAddress> findDeliveryAddressByRestaurant(final Long restaurantId) {
        final Restaurant restaurant = restaurantService.findById(restaurantId);
        List<RestaurantDeliveryAddress> restaurantDeliveryAddresses =
                restaurantDeliveryAddressDao.findAllByRestaurant(restaurant);
        final List<Long> deliveryAddressIdList = restaurantDeliveryAddresses.stream()
                .map(address -> address.getDeliveryAddress().getDeliveryAddressId())
                .toList();
        return deliveryAddressService.findDeliveryAddressesByIdList(deliveryAddressIdList);
    }

    @Override
    @Transactional
    public List<Restaurant> getRestaurantsByCountryAndCityAndStreet(
            final String country,
            final String city,
            final String street,
            final Integer page
    ) {
        DeliveryAddress deliveryAddress = deliveryAddressService.findByCountryAndCityAndStreet(country, city, street)
                .orElseThrow(() -> new RuntimeException("There is no restaurant that deliver food to the given address"));
        List<RestaurantDeliveryAddress> restaurantDeliveryAddresses =
                restaurantDeliveryAddressDao.findByDeliveryAddress(deliveryAddress);
        final List<Long> restaurantsIdList = restaurantDeliveryAddresses.stream()
                .map(address -> address.getRestaurant().getRestaurantId())
                .toList();
        return restaurantService.findRestaurantsByIdList(restaurantsIdList, page);
    }

    @Override
    @Transactional
    public void addDeliveryAddress(final DeliveryAddressCreation deliveryAddressCreation) {
        final Restaurant restaurant = restaurantService.findById(deliveryAddressCreation.getRestaurantId());
        final DeliveryAddress deliveryAddress = findOrCreateDeliveryAddress(deliveryAddressCreation);
        final RestaurantDeliveryAddress restaurantDeliveryAddress =
                buildRestaurantDeliveryAddress(deliveryAddressCreation, restaurant, deliveryAddress);
        restaurantDeliveryAddressDao.addRestaurantDeliveryAddress(restaurantDeliveryAddress);
    }

    private DeliveryAddress findOrCreateDeliveryAddress(final DeliveryAddressCreation deliveryAddressCreation) {
        return deliveryAddressService.findByCountryAndCityAndStreet(
                deliveryAddressCreation.getCountry(),
                deliveryAddressCreation.getCity(),
                deliveryAddressCreation.getStreet()
        ).orElseGet(() -> deliveryAddressService.addDeliveryAddress(buildDeliveryAddress(deliveryAddressCreation)));
    }

    private RestaurantDeliveryAddress buildRestaurantDeliveryAddress(
            final DeliveryAddressCreation deliveryAddressCreation,
            final Restaurant restaurant,
            final DeliveryAddress deliveryAddress
    ) {
        return RestaurantDeliveryAddress.builder()
                .deliveryTime(deliveryAddressCreation.getDeliveryTime())
                .restaurant(restaurant)
                .deliveryAddress(deliveryAddress)
                .build();
    }

    private DeliveryAddress buildDeliveryAddress(final DeliveryAddressCreation deliveryAddressCreation) {
        return DeliveryAddress.builder()
                .country(deliveryAddressCreation.getCountry())
                .city(deliveryAddressCreation.getCity())
                .street(deliveryAddressCreation.getStreet())
                .build();
    }
}
