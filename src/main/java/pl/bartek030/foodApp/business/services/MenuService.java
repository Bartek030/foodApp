package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.business.serviceModel.MenuCreation;

import java.util.List;

public interface MenuService {
    void addMenu(final MenuCreation menuCreation);

    Menu findById(Long menuId);

    List<Menu> getMenusByRestaurantId(Long restaurantId);
}
