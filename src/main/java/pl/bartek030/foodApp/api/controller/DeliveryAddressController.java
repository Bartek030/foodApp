package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.DeliveryAddressCreationDTO;
import pl.bartek030.foodApp.api.dto.DeliveryAddressDTO;

import java.util.List;

@RequestMapping(DeliveryAddressController.DELIVERY_ADDRESS_URL)
public interface DeliveryAddressController {

    String DELIVERY_ADDRESS_URL = "/delivery-address";
    String NEW_DELIVERY_ADDRESS_URL = "/new";
    String RESTAURANTS_URL = "/restaurants/{restaurantId}";

    @PostMapping(NEW_DELIVERY_ADDRESS_URL)
    ResponseEntity<DeliveryAddressDTO> addDeliveryAddress(
            @RequestBody DeliveryAddressCreationDTO deliveryAddressCreationDTO
    );

    @GetMapping(RESTAURANTS_URL)
    ResponseEntity<List<DeliveryAddressDTO>> getAddressesByRestaurant(
        @PathVariable Long restaurantId
    );
}