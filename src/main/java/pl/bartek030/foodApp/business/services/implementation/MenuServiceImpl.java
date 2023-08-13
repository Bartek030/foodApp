package pl.bartek030.foodApp.business.services.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.MenuDao;
import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.business.serviceModel.MenuCreation;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.services.MenuService;
import pl.bartek030.foodApp.business.services.RestaurantService;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuDao menuDao;

    private final RestaurantService restaurantService;

    @Override
    @Transactional
    public Menu findById(final Long menuId) {
        // TODO: Custom exception
        return menuDao.findById(menuId).orElseThrow();
    }

    @Override
    @Transactional
    public void addMenu(final MenuCreation menuCreation) {
        final Restaurant restaurant = restaurantService.findById(menuCreation.getRestaurantId());
        menuDao.addMenu(buildMenu(menuCreation, restaurant));
    }

    @Override
    @Transactional
    public List<Menu> getMenusByRestaurantId(final Long restaurantId) {
        final Restaurant restaurant = restaurantService.findById(restaurantId);
        return menuDao.findByRestaurant(restaurant);
    }

    private Menu buildMenu(final MenuCreation menuCreation, final Restaurant restaurant) {
        return Menu.builder()
                .name(menuCreation.getName())
                .category(menuCreation.getCategory())
                .restaurant(restaurant)
                .build();
    }
}
