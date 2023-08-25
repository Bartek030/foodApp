package pl.bartek030.foodApp.api.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.MenuCreationDTO;

@RequestMapping(MenuController.MENU_URL)
public interface MenuController {

    String MENU_URL = "/menu";
    String NEW_MENU_URL = "/new";
    String RESTAURANTS_MENUS_URL = "/{restaurantId}";
    String OWNER_RESTAURANTS_MENUS_URL = "/owner/{restaurantId}";

    @PostMapping(MenuController.NEW_MENU_URL)
    String addMenu(@ModelAttribute MenuCreationDTO menu);

    @GetMapping(RESTAURANTS_MENUS_URL)
    String getRestaurantsMenus(
            @PathVariable Long restaurantId,
            final Model model
    );

    @GetMapping(OWNER_RESTAURANTS_MENUS_URL)
    String getOwnersRestaurantsMenus(
            @PathVariable Long restaurantId,
            final Model model
    );
}
