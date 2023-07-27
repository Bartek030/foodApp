package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantCreation;

public interface RestaurantService {

    void addRestaurant(RestaurantCreation restaurantCreation);

    Restaurant findById(Long restaurantId);
}
