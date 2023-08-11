package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.FoodAppUserDAO;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.FoodAppUserDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.FoodAppUserJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class FoodAppUserRepository implements FoodAppUserDAO {

    private final FoodAppUserJpaRepository foodAppUserJpaRepository;

    private final FoodAppUserDaoMapper foodAppUserDaoMapper;

    @Override
    public Optional<FoodAppUser> findById(final Long foodAppUserId) {
        return foodAppUserJpaRepository.findById(foodAppUserId)
                .map(foodAppUserDaoMapper::mapFoodAppUserFromEntity);
    }

    @Override
    public void createFoodAppUser(final FoodAppUser foodAppUser) {
        foodAppUserJpaRepository.save(foodAppUserDaoMapper.mapFoodAppUserToEntity(foodAppUser));
    }
}
