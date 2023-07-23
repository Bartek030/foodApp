package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.RestaurantController;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;
import pl.bartek030.foodApp.api.dto.mapper.RestaurantDtoMapper;
import pl.bartek030.foodApp.business.services.RestaurantService;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class RestaurantControllerImpl implements RestaurantController {

    private final RestaurantDtoMapper restaurantDtoMapper;
    private final RestaurantService restaurantService;

    @Override
    public ResponseEntity<List<RestaurantDTO>> getUsersRestaurants(final Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<RestaurantDTO> addRestaurant(final RestaurantDTO restaurant) {
        restaurantService.addRestaurant(restaurantDtoMapper.mapto(restaurant));
        return ResponseEntity
                .created(URI.create(
//                        RESTAURANT_URL + ID_PLACEHOLDER.formatted(restaurant.getFoodAppUser().getFoodAppUserId())
                        RESTAURANT_URL + ID_PLACEHOLDER.formatted(1)
                )).build();
    }


}
