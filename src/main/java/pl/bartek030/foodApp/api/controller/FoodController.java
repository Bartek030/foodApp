package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartek030.foodApp.api.dto.FoodDTO;

@RequestMapping(FoodController.FOOD_URL)
public interface FoodController {

    String FOOD_URL = "/food";
    String NEW_FOOD_URL = "/new";

    @PostMapping(FoodController.NEW_FOOD_URL)
    ResponseEntity<FoodDTO> addFood(@RequestBody FoodDTO food);
}
