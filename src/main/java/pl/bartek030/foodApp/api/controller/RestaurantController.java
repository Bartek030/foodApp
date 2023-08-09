package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.RestaurantCreationDTO;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;

import java.util.List;

@RequestMapping(RestaurantController.RESTAURANT_URL)
public interface RestaurantController {

    String RESTAURANT_URL = "/restaurants";
    String NEW_RESTAURANT_URL = "/new";
    String USERS_RESTAURANTS_URL = "/owner/{userId}";
    String ID_PLACEHOLDER = "/%s";

    @PostMapping(NEW_RESTAURANT_URL)
    ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantCreationDTO restaurant);

    @GetMapping(USERS_RESTAURANTS_URL)
    ResponseEntity<List<RestaurantDTO>> getOwnersRestaurants(
            @PathVariable Long userId
    );

    @GetMapping
    ResponseEntity<List<RestaurantDTO>> getRestaurantsByCountryAndCityAndStreet(
            @RequestParam String country,
            @RequestParam String city,
            @RequestParam String street,
            @RequestParam Integer page
    );
}