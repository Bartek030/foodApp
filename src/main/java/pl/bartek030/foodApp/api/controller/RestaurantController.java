package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;

@RequestMapping(RestaurantController.RESTAURANT_URL)
public interface RestaurantController {

    String RESTAURANT_URL = "/restaurants";
    String NEW_RESTAURANT_URL = "/new";

    @PostMapping(RestaurantController.NEW_RESTAURANT_URL)
    ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurant);
}