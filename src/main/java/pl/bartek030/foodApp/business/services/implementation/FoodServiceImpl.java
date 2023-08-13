package pl.bartek030.foodApp.business.services.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.FoodDao;
import pl.bartek030.foodApp.business.serviceModel.Food;
import pl.bartek030.foodApp.business.serviceModel.FoodCreation;
import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.business.services.FoodService;
import pl.bartek030.foodApp.business.services.MenuService;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final MenuService menuService;
    private final FoodDao foodDao;

    @Override
    @Transactional
    public Food findFoodById(final Long foodId) {
        // TODO: Custom exception
        return foodDao.findById(foodId).orElseThrow();
    }

    @Override
    @Transactional
    public void addFood(final FoodCreation foodCreation) {
        final Menu menu = menuService.findById(foodCreation.getMenuId());
        foodDao.addFood(buildFood(foodCreation, menu));
    }

    @Override
    @Transactional
    public List<Food> getFoodsFromMenu(final Long menuId) {
        final Menu menu = menuService.findById(menuId);
        return foodDao.findByMenu(menu);
    }

    private Food buildFood(final FoodCreation foodCreation, final Menu menu) {
        return Food.builder()
                .name(foodCreation.getName())
                .price(foodCreation.getPrice())
                .description(foodCreation.getDescription())
                .menu(menu)
                .build();
    }
}
