package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.Food;
import pl.bartek030.foodApp.business.serviceModel.FoodCreation;

import java.util.List;

public interface FoodService {
    void addFood(FoodCreation map);

    List<Food> getFoodsFromMenu(Long menuId);

    Food findFoodById(Long foodId);
}
