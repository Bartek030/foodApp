package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.Restaurant;

import java.util.Optional;

public interface RestaurantDAO {

    void addRestaurant(Restaurant restaurant);

    Optional<Restaurant> findById(Long restaurantId);
}
