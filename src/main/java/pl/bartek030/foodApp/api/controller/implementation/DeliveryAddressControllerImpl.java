package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import pl.bartek030.foodApp.api.controller.DeliveryAddressController;
import pl.bartek030.foodApp.api.dto.DeliveryAddressCreationDTO;
import pl.bartek030.foodApp.api.dto.DeliveryAddressDTO;
import pl.bartek030.foodApp.api.dto.mapper.DeliveryAddressCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.DeliveryAddressDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.services.RestaurantDeliveryAddressService;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class DeliveryAddressControllerImpl implements DeliveryAddressController {

    private final DeliveryAddressDtoMapper deliveryAddressDtoMapper;
    private final DeliveryAddressCreationDtoMapper deliveryAddressCreationDtoMapper;

    private final RestaurantDeliveryAddressService restaurantDeliveryAddressService;

    @Override
    public String addDeliveryAddress(final DeliveryAddressCreationDTO deliveryAddressCreationDTO) {
        restaurantDeliveryAddressService
                .addDeliveryAddress(deliveryAddressCreationDtoMapper.map(deliveryAddressCreationDTO));
        return "delivery-address-success";
    }

    @Override
    public String getAddressesByRestaurant(final Long restaurantId, final Model model) {
        List<DeliveryAddress> deliveryAddressList =
                restaurantDeliveryAddressService.findDeliveryAddressByRestaurant(restaurantId);

        final List<DeliveryAddressDTO> deliveryAddressDTOList = deliveryAddressList.stream()
                .map(deliveryAddressDtoMapper::map)
                .toList();

        model.addAllAttributes(Map.of("deliveryAddresses", deliveryAddressDTOList));
        model.addAttribute("restaurantId", restaurantId);
        return "delivery-address-list";
    }
}
