package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.RestaurantCreationDTO;

import java.util.List;

@RequestMapping(RestaurantController.RESTAURANT_URL)
public interface RestaurantController {

    String RESTAURANT_URL = "/restaurants";
    String NEW_RESTAURANT_URL = "/new";
    String USERS_RESTAURANTS_URL = "/{userId}";
    String ID_PLACEHOLDER = "/%s";

    @PostMapping(RestaurantController.NEW_RESTAURANT_URL)
    ResponseEntity<RestaurantCreationDTO> addRestaurant(@RequestBody RestaurantCreationDTO restaurant);

    @GetMapping(USERS_RESTAURANTS_URL)
    ResponseEntity<List<RestaurantCreationDTO>> getUsersRestaurants(
            @PathVariable Long userId
    );
}