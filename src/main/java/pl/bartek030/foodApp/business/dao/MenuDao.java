package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.Menu;

import java.util.Optional;

public interface MenuDao {
    void addMenu(final Menu menu);

    Optional<Menu> findById(Long menuId);
}
