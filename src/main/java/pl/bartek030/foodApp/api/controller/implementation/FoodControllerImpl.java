package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import pl.bartek030.foodApp.api.controller.FoodController;
import pl.bartek030.foodApp.api.dto.FoodCreationDTO;
import pl.bartek030.foodApp.api.dto.FoodDTO;
import pl.bartek030.foodApp.api.dto.mapper.FoodCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.FoodDtoMapper;
import pl.bartek030.foodApp.business.services.FoodService;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class FoodControllerImpl implements FoodController {

    private final FoodCreationDtoMapper foodCreationDtoMapper;
    private final FoodDtoMapper foodDtoMapper;
    private final FoodService foodService;

    @Override
    public String getFoodFromMenu(final Long menuId, final Model model) {
        final List<FoodDTO> foodDTOList = foodService.getFoodsFromMenu(menuId)
                .stream()
                .map(foodDtoMapper::map)
                .toList();
        model.addAllAttributes(Map.of("foods", foodDTOList));
        return "foodList";
    }

    @Override
    public String getOwnersFoodFromMenu(final Long menuId, final Model model) {
        final List<FoodDTO> foodDTOList = foodService.getFoodsFromMenu(menuId)
                .stream()
                .map(foodDtoMapper::map)
                .toList();
        model.addAllAttributes(Map.of("foods", foodDTOList));
        model.addAttribute("menuId", menuId);
        return "owner-foodList";
    }

    @Override
    public String addFood(final FoodCreationDTO food) {
        foodService.addFood(foodCreationDtoMapper.map(food));
        return "food-success";
    }
}
