package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.FoodAppUserDAO;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.FoodAppUserDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.FoodAppUserJpaRepository;
import pl.bartek030.foodApp.infrastructure.security.UserDaoMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class FoodAppUserRepository implements FoodAppUserDAO {

    private final FoodAppUserJpaRepository foodAppUserJpaRepository;

    private final FoodAppUserDaoMapper foodAppUserDaoMapper;
    private final UserDaoMapper userDaoMapper;

    @Override
    public Optional<FoodAppUser> findById(final Long foodAppUserId) {
        return foodAppUserJpaRepository.findById(foodAppUserId)
                .map(foodAppUserDaoMapper::mapFoodAppUserFromEntity);
    }

    @Override
    public Optional<FoodAppUser> findByEmail(final String email) {
        return foodAppUserJpaRepository.findByEmail(email)
                .map(foodAppUserDaoMapper::mapFoodAppUserFromEntity);
    }

    @Override
    public void createFoodAppUser(final FoodAppUser foodAppUser) {
        final FoodAppUserEntity foodAppUserEntity = foodAppUserDaoMapper.mapFoodAppUserToEntity(foodAppUser)
                .withUser(userDaoMapper.mapUserToEntity(foodAppUser.getAppUser()));
        foodAppUserJpaRepository.save(foodAppUserEntity);
    }
}
