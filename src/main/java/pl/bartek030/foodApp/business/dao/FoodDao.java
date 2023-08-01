package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.Food;
import pl.bartek030.foodApp.business.serviceModel.Menu;

import java.util.List;

public interface FoodDao {
    void addFood(final Food food);

    List<Food> findByMenu(Menu menu);
}
