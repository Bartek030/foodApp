package pl.bartek030.foodApp.business.services.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartek030.foodApp.business.dao.RestaurantDAO;
import pl.bartek030.foodApp.business.serviceModel.Address;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantCreation;
import pl.bartek030.foodApp.business.services.AddressService;
import pl.bartek030.foodApp.business.services.FoodAppUserService;
import pl.bartek030.foodApp.business.services.RestaurantService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDAO restaurantDAO;

    private final FoodAppUserService foodAppUserService;
    private final AddressService addressService;

    @Override
    @Transactional
    public void addRestaurant(final RestaurantCreation restaurantCreation) {
        final FoodAppUser foodAppUser = foodAppUserService.findById(restaurantCreation.getFoodAppUserId());
        final Address address = findOrCreateAddress(restaurantCreation);

        restaurantDAO.addRestaurant(buildRestaurant(restaurantCreation, foodAppUser, address));
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

    private Restaurant buildRestaurant(final RestaurantCreation restaurantCreation, final FoodAppUser foodAppUser, final Address address) {
        return Restaurant.builder()
                .name(restaurantCreation.getName())
                .foodAppUser(foodAppUser)
                .address(address)
                .build();
    }
}
