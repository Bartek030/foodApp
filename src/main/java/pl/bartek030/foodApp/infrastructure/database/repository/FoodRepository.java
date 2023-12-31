package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.FoodDao;
import pl.bartek030.foodApp.business.serviceModel.Food;
import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.FoodDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.MenuDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.FoodJpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class FoodRepository implements FoodDao {

    private final FoodJpaRepository foodJpaRepository;
    private final FoodDaoMapper foodDaoMapper;
    private final MenuDaoMapper menuDaoMapper;

    @Override
    public Optional<Food> findById(final Long foodId) {
        return foodJpaRepository.findById(foodId)
                .map(foodDaoMapper::mapFoodFromEntityWithMenu);
    }

    @Override
    public void addFood(final Food food) {
        foodJpaRepository.save(foodDaoMapper.mapFoodToEntity(food));
    }

    @Override
    public List<Food> findByMenu(final Menu menu) {
        final List<FoodEntity> allByMenu = foodJpaRepository.findAllByMenu(menuDaoMapper.mapMenuToEntity(menu));
        return allByMenu.stream()
                .map(foodDaoMapper::mapFoodFromEntity)
                .toList();
    }
}
