package pl.bartek030.foodApp.api.controller.rest.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.rest.DeliveryAddressRestController;
import pl.bartek030.foodApp.api.dto.DeliveryAddressCreationDTO;
import pl.bartek030.foodApp.api.dto.DeliveryAddressDTO;
import pl.bartek030.foodApp.api.dto.mapper.DeliveryAddressCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.DeliveryAddressDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.services.RestaurantDeliveryAddressService;

import java.util.List;

@RestController
@AllArgsConstructor
public class DeliveryAddressRestControllerImpl implements DeliveryAddressRestController {

    private final DeliveryAddressDtoMapper deliveryAddressDtoMapper;
    private final DeliveryAddressCreationDtoMapper deliveryAddressCreationDtoMapper;

    private final RestaurantDeliveryAddressService restaurantDeliveryAddressService;

    @Override
    public ResponseEntity<DeliveryAddressDTO> addDeliveryAddress(
            final DeliveryAddressCreationDTO deliveryAddressCreationDTO
    ) {
        restaurantDeliveryAddressService
                .addDeliveryAddress(deliveryAddressCreationDtoMapper.map(deliveryAddressCreationDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<DeliveryAddressDTO>> getAddressesByRestaurant(final Long restaurantId) {
        List<DeliveryAddress> deliveryAddressList =
                restaurantDeliveryAddressService.findDeliveryAddressByRestaurant(restaurantId);
        return ResponseEntity.ok(deliveryAddressList.stream()
                .map(deliveryAddressDtoMapper::map)
                .toList()
        );
    }
}
