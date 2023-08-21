package pl.bartek030.foodApp.api.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.DeliveryAddressCreationDTO;

@RequestMapping(DeliveryAddressController.DELIVERY_ADDRESS_URL)
public interface DeliveryAddressController {

    String DELIVERY_ADDRESS_URL = "/delivery-address";
    String NEW_DELIVERY_ADDRESS_URL = "/new";
    String RESTAURANTS_URL = "/restaurants/{restaurantId}";

    @PostMapping(NEW_DELIVERY_ADDRESS_URL)
    String addDeliveryAddress(
            @ModelAttribute DeliveryAddressCreationDTO deliveryAddressCreationDTO
    );

    @GetMapping(RESTAURANTS_URL)
    String getAddressesByRestaurant(
            @PathVariable Long restaurantId,
            final Model model
    );
}
