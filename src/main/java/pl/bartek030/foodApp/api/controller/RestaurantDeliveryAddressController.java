package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;
import pl.bartek030.foodApp.api.dto.RestaurantDeliveryAddressDTO;

import java.util.List;

@RequestMapping(RestaurantDeliveryAddressController.DELIVERY_ADDRESS_URL)
public interface RestaurantDeliveryAddressController {

    String DELIVERY_ADDRESS_URL = "/delivery-address";
    String NEW_DELIVERY_ADDRESS_URL = "/new";
    String RESTAURANTS_URL = "/restaurants";

    @PostMapping(NEW_DELIVERY_ADDRESS_URL)
    ResponseEntity<RestaurantDeliveryAddressDTO> addDeliveryAddress(
            @RequestBody RestaurantDeliveryAddressDTO restaurantDeliveryAddress
    );

    @GetMapping(RESTAURANTS_URL)
    ResponseEntity<List<RestaurantDTO>> getRestaurantsByAddress(
        @RequestParam String country,
        @RequestParam String city,
        @RequestParam String street,
        @RequestParam Integer page
    );
}
