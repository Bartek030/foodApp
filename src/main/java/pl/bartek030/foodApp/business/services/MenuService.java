package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.business.serviceModel.MenuCreation;

public interface MenuService {
    void addMenu(final MenuCreation menuCreation);

    Menu findById(Long menuId);
}
