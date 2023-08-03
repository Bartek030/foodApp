package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.Food;
import pl.bartek030.foodApp.business.serviceModel.Menu;

import java.util.List;
import java.util.Optional;

public interface FoodDao {
    void addFood(final Food food);

    List<Food> findByMenu(Menu menu);

    Optional<Food> findById(Long foodId);
}
