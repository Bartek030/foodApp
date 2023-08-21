package pl.bartek030.foodApp.api.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(FoodController.FOOD_URL)
public interface FoodController {

    String FOOD_URL = "/food";
    String MENUS_FOODS_URL = "/{menuId}";
    String OWNER_MENUS_FOODS_URL = "/owner/{menuId}";

    @GetMapping(MENUS_FOODS_URL)
    String getFoodFromMenu(@PathVariable Long menuId, final Model model);

    @GetMapping(OWNER_MENUS_FOODS_URL)
    String getOwnersFoodFromMenu(@PathVariable Long menuId, final Model model);
}
