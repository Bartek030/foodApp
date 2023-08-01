package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.FoodController;
import pl.bartek030.foodApp.api.dto.FoodCreationDTO;
import pl.bartek030.foodApp.api.dto.FoodDTO;
import pl.bartek030.foodApp.api.dto.mapper.FoodCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.FoodDtoMapper;
import pl.bartek030.foodApp.business.services.FoodService;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class FoodControllerImpl implements FoodController {

    private final FoodCreationDtoMapper foodCreationDtoMapper;
    private final FoodDtoMapper foodDtoMapper;
    private final FoodService foodService;

    @Override
    public ResponseEntity<List<FoodDTO>> getFoodFromMenu(final Long menuId) {
        final List<FoodDTO> list = foodService.getFoodsFromMenu(menuId)
                .stream()
                .map(foodDtoMapper::map)
                .toList();
        return ResponseEntity.ok(
                list
        );
    }

    @Override
    public ResponseEntity<FoodDTO> addFood(final FoodCreationDTO food) {
        foodService.addFood(foodCreationDtoMapper.map(food));
        return ResponseEntity
                .created(URI.create(
                        // TODO: magic number to remove after Spring Security implementation
                        FOOD_URL + ID_PLACEHOLDER.formatted(1)
                )).build();
    }
}
