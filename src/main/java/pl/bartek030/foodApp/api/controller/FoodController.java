package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.FoodCreationDTO;
import pl.bartek030.foodApp.api.dto.FoodDTO;

@RequestMapping(FoodController.FOOD_URL)
public interface FoodController {

    String FOOD_URL = "/food";
    String NEW_FOOD_URL = "/new";
    String MENUS_FOODS_URL = "/{menuId}";
    String OWNER_MENUS_FOODS_URL = "/owner/{menuId}";

    @GetMapping(MENUS_FOODS_URL)
    String getFoodFromMenu(@PathVariable Long menuId, final Model model);

    @GetMapping(OWNER_MENUS_FOODS_URL)
    String getOwnersFoodFromMenu(@PathVariable Long menuId, final Model model);

    @PostMapping(NEW_FOOD_URL)
    String addFood(@ModelAttribute FoodCreationDTO food);
}
