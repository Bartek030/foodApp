package pl.bartek030.foodApp.api.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.MenuCreationDTO;
import pl.bartek030.foodApp.api.dto.MenuDTO;

import java.util.List;

@RequestMapping(MenuRestController.MENU_URL)
public interface MenuRestController {

    String MENU_URL = "/api/menu";
    String NEW_MENU_URL = "/new";
    String RESTAURANTS_MENUS_URL = "/{restaurantId}";
    String ID_PLACEHOLDER = "/%s";

    @PostMapping(MenuRestController.NEW_MENU_URL)
    ResponseEntity<MenuCreationDTO> addMenu(@RequestBody MenuCreationDTO menu);

    @GetMapping(RESTAURANTS_MENUS_URL)
    ResponseEntity<List<MenuDTO>> getRestaurantsMenus(
            @PathVariable Long restaurantId
    );
}
