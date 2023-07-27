package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.FoodDao;
import pl.bartek030.foodApp.business.serviceModel.Food;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.FoodDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.FoodJpaRepository;

@Repository
@AllArgsConstructor
public class FoodRepository implements FoodDao {

    private final FoodJpaRepository foodJpaRepository;
    private final FoodDaoMapper foodDaoMapper;

    @Override
    public void addFood(final Food food) {
        foodJpaRepository.save(foodDaoMapper.mapToEntity(food));
    }
}
