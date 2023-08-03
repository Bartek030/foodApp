package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.RestaurantController;
import pl.bartek030.foodApp.api.dto.RestaurantCreationDTO;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;
import pl.bartek030.foodApp.api.dto.mapper.RestaurantCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.RestaurantDtoMapper;
import pl.bartek030.foodApp.business.services.RestaurantService;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class RestaurantControllerImpl implements RestaurantController {

    private final RestaurantCreationDtoMapper restaurantCreationDtoMapper;
    private final RestaurantDtoMapper restaurantDtoMapper;

    private final RestaurantService restaurantService;

    @Override
    public ResponseEntity<List<RestaurantCreationDTO>> getUsersRestaurants(final Long userId) {
        return null;
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
            final String street
    ) {
        return ResponseEntity.ok(
                restaurantService.getRestaurantsByCountryAndCityAndStreet(country, city, street)
                .stream()
                .map(restaurantDtoMapper::map)
                .toList()
        );
    }
}
