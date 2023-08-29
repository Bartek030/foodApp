package pl.bartek030.foodApp.business.services.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.RestaurantDAO;
import pl.bartek030.foodApp.business.serviceModel.Address;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantCreation;
import pl.bartek030.foodApp.business.services.AddressService;
import pl.bartek030.foodApp.business.services.FoodAppUserService;
import pl.bartek030.foodApp.business.services.RestaurantService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDAO restaurantDAO;

    private final FoodAppUserService foodAppUserService;
    private final AddressService addressService;

    @Override
    @Transactional
    public Restaurant findById(final Long restaurantId) {
        return restaurantDAO.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant with id: [%s] not found". formatted(restaurantId)));
    }

    @Override
    @Transactional
    public List<Restaurant> findRestaurantsByFoodAppUserId(final Long userId) {
        final FoodAppUser foodAppUser = foodAppUserService.findById(userId);
        return restaurantDAO.findByFoodAppUser(foodAppUser);
    }

    @Override
    public List<Restaurant> findRestaurantsByFoodAppUserEmail(final String email) {
        final FoodAppUser foodAppUser = foodAppUserService.findByEmail(email);
        return restaurantDAO.findByFoodAppUser(foodAppUser);
    }

    @Override
    @Transactional
    public void addRestaurant(final RestaurantCreation restaurantCreation) {
        final String email = SecurityContextHolder.getContext().getAuthentication().getName();
        final FoodAppUser foodAppUser = foodAppUserService.findByEmail(email);
        final Address address = findOrCreateAddress(restaurantCreation);

        restaurantDAO.addRestaurant(buildRestaurant(restaurantCreation, foodAppUser, address));
    }

    @Override
    @Transactional
    public List<Restaurant> findRestaurantsByIdList(final List<Long> restaurantsIdList, final Integer page) {
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
