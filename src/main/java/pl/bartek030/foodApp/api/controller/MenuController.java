package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartek030.foodApp.api.dto.RestaurantDeliveryAddressDTO;

@RequestMapping(MenuController.MENU_URL)
public interface MenuController {

    String MENU_URL = "/menu";
    String NEW_MENU_URL = "/new";

    @PostMapping(MenuController.NEW_MENU_URL)
    ResponseEntity<RestaurantDeliveryAddressDTO> addMenu(@RequestBody RestaurantDeliveryAddressDTO menu);
}
