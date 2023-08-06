package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantCreation;

import java.util.List;

public interface RestaurantService {

    void addRestaurant(final RestaurantCreation restaurantCreation);

    Restaurant findById(final Long restaurantId);

    List<Restaurant> findRestaurantsByFoodAppUserId(final Long userId);

    List<Restaurant> findRestaurantsByIdList(List<Long> restaurantsIdList, Integer page);
}
