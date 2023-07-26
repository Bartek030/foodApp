package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.FoodController;
import pl.bartek030.foodApp.api.dto.FoodDTO;
import pl.bartek030.foodApp.api.dto.MenuCreationDTO;

import java.util.List;

@RestController
@AllArgsConstructor
public class FoodControllerImpl implements FoodController {

    @Override
    public ResponseEntity<List<MenuCreationDTO>> getRestaurantsMenus(final Long restaurantId) {
        return null;
    }

    @Override
    public ResponseEntity<FoodDTO> addFood(final FoodDTO food) {
        return null;
    }
}
