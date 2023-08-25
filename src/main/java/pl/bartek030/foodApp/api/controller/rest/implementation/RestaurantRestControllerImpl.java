package pl.bartek030.foodApp.api.controller.rest.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.rest.RestaurantRestController;
import pl.bartek030.foodApp.api.dto.RestaurantCreationDTO;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;
import pl.bartek030.foodApp.api.dto.mapper.RestaurantCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.RestaurantDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.services.RestaurantDeliveryAddressService;
import pl.bartek030.foodApp.business.services.RestaurantService;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class RestaurantRestControllerImpl implements RestaurantRestController {

    private final RestaurantCreationDtoMapper restaurantCreationDtoMapper;
    private final RestaurantDtoMapper restaurantDtoMapper;

    private final RestaurantService restaurantService;
    private final RestaurantDeliveryAddressService restaurantDeliveryAddressService;

    @Override
    public ResponseEntity<List<RestaurantDTO>> getOwnersRestaurants(final Long userId) {
        List<Restaurant> restaurantList = restaurantService.findRestaurantsByFoodAppUserId(userId);
        return ResponseEntity.ok(restaurantList.stream()
                .map(restaurantDtoMapper::map)
                .toList()
        );
    }

    @Override
    public ResponseEntity<RestaurantDTO> addRestaurant(final RestaurantCreationDTO restaurant) {
        restaurantService.addRestaurant(restaurantCreationDtoMapper.map(restaurant));
        return ResponseEntity
                .created(URI.create(
                        // TODO: magic number to remove after Spring Security implementation
                        RESTAURANT_URL + ID_PLACEHOLDER.formatted(1)
                )).build();
    }

    @Override
    public ResponseEntity<List<RestaurantDTO>> getRestaurantsByCountryAndCityAndStreet(
            final String country,
            final String city,
            final String street,
            final Integer page
    ) {
        final List<Restaurant> restaurants =
                restaurantDeliveryAddressService.getRestaurantsByCountryAndCityAndStreet(country, city, street, page);
        return ResponseEntity.ok(
                restaurants.stream()
                .map(restaurantDtoMapper::map)
                .toList()
        );
    }
}
