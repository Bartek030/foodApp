package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;

import java.util.Optional;

public interface FoodAppUserDAO {

    Optional<FoodAppUser> findById(Long foodAppUserId);
}
