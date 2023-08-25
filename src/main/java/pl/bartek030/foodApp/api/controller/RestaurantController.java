package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.RestaurantCreationDTO;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;

import java.util.List;

@RequestMapping(RestaurantController.RESTAURANT_URL)
public interface RestaurantController {

    String RESTAURANT_URL = "/restaurants";
    String RESTAURANT_MAIN_URL = "/main";
    String RESTAURANT_LIST_URL = "/list";
    String NEW_RESTAURANT_URL = "/new";
    String USERS_RESTAURANTS_URL = "/owner";

    @PostMapping(NEW_RESTAURANT_URL)
    String addRestaurant(@ModelAttribute RestaurantCreationDTO restaurant);

    @GetMapping(USERS_RESTAURANTS_URL)
    String getOwnersRestaurants(final Model model);

    @GetMapping(RESTAURANT_MAIN_URL)
    String getRestaurantPage();

    @GetMapping(RESTAURANT_LIST_URL)
    String getRestaurantsByCountryAndCityAndStreet(
            @RequestParam String country,
            @RequestParam String city,
            @RequestParam String street,
            @RequestParam Integer page,
            Model model
    );
}