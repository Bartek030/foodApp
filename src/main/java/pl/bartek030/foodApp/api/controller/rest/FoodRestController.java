package pl.bartek030.foodApp.api.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.FoodCreationDTO;
import pl.bartek030.foodApp.api.dto.FoodDTO;

import java.util.List;

@RequestMapping(FoodRestController.FOOD_URL)
public interface FoodRestController {

    String FOOD_URL = "/api/food";
    String NEW_FOOD_URL = "/new";
    String MENUS_FOODS_URL = "/{menuId}";
    String ID_PLACEHOLDER = "/%s";

    @GetMapping(MENUS_FOODS_URL)
    ResponseEntity<List<FoodDTO>> getFoodFromMenu(@PathVariable Long menuId);

    @PostMapping(NEW_FOOD_URL)
    ResponseEntity<FoodDTO> addFood(@RequestBody FoodCreationDTO food);
}
