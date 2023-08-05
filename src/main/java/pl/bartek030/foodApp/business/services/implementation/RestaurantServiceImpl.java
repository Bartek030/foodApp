package pl.bartek030.foodApp.business.services.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartek030.foodApp.business.dao.RestaurantDAO;
import pl.bartek030.foodApp.business.serviceModel.*;
import pl.bartek030.foodApp.business.services.*;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDAO restaurantDAO;

    private final FoodAppUserService foodAppUserService;
    private final AddressService addressService;
    private final DeliveryAddressService deliveryAddressService;
    private final RestaurantDeliveryAddressService restaurantDeliveryAddressService;

    @Override
    @Transactional
    public Restaurant findById(final Long restaurantId) {
        // TODO: Custom exception
        return restaurantDAO.findById(restaurantId).orElseThrow();
    }

    @Override
    public List<Restaurant> findRestaurantsByFoodAppUserId(final Long userId) {
        final FoodAppUser foodAppUser = foodAppUserService.findById(userId);
        return restaurantDAO.findByFoodAppUserId(foodAppUser);
    }

    @Override
    @Transactional
    public void addRestaurant(final RestaurantCreation restaurantCreation) {
        final FoodAppUser foodAppUser = foodAppUserService.findById(restaurantCreation.getFoodAppUserId());
        final Address address = findOrCreateAddress(restaurantCreation);

        restaurantDAO.addRestaurant(buildRestaurant(restaurantCreation, foodAppUser, address));
    }

    @Override
    public List<Restaurant> getRestaurantsByCountryAndCityAndStreet(
            final String country,
            final String city,
            final String street,
            final Integer page
    ) {
        // TODO: Custom exception
        DeliveryAddress deliveryAddress = deliveryAddressService.findByCountryAndCityAndStreet(country, city, street)
                .orElseThrow();
        List<RestaurantDeliveryAddress> restaurantDeliveryAddresses =
                restaurantDeliveryAddressService.findByAddress(deliveryAddress);
        final List<Long> restaurantsIdList = restaurantDeliveryAddresses.stream()
                .map(address -> address.getRestaurant().getRestaurantId())
                .toList();
        return restaurantDAO.findRestaurantsByIdList(restaurantsIdList, page);
    }

    private Address findOrCreateAddress(final RestaurantCreation restaurantCreation) {
        Optional<Address> addressOpt = addressService.findAddressByData(
                restaurantCreation.getCountry(),
                restaurantCreation.getCity(),
                restaurantCreation.getStreet(),
                restaurantCreation.getNumber(),
                restaurantCreation.getZipCode()
        );
        return addressOpt.orElseGet(() -> addressService.createAddressFromRestaurant(restaurantCreation));
    }

    private Restaurant buildRestaurant(
            final RestaurantCreation restaurantCreation,
            final FoodAppUser foodAppUser,
            final Address address
    ) {
        return Restaurant.builder()
                .name(restaurantCreation.getName())
                .foodAppUser(foodAppUser)
                .address(address)
                .build();
    }
}
