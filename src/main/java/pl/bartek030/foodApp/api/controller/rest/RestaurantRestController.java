package pl.bartek030.foodApp.api.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.RestaurantCreationDTO;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;

import java.util.List;

@RequestMapping(RestaurantRestController.RESTAURANT_URL)
public interface RestaurantRestController {

    String RESTAURANT_URL = "/api/restaurants";
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