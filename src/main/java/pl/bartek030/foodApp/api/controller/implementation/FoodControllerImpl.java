package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.FoodController;
import pl.bartek030.foodApp.api.dto.FoodCreationDTO;
import pl.bartek030.foodApp.api.dto.FoodDTO;
import pl.bartek030.foodApp.api.dto.MenuCreationDTO;
import pl.bartek030.foodApp.api.dto.mapper.FoodDtoMapper;
import pl.bartek030.foodApp.business.services.FoodService;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class FoodControllerImpl implements FoodController {

    private final FoodService foodService;
    private final FoodDtoMapper foodDtoMapper;

    @Override
    public ResponseEntity<List<MenuCreationDTO>> getRestaurantsMenus(final Long restaurantId) {
        return null;
    }

    @Override
    public ResponseEntity<FoodDTO> addFood(final FoodCreationDTO food) {
        foodService.addMenu(foodDtoMapper.map(food));
        return ResponseEntity
                .created(URI.create(
                        // TODO: magic number to remove after Spring Security implementation
                        FOOD_URL + ID_PLACEHOLDER.formatted(1)
                )).build();
    }
}
