package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;

import java.util.Optional;

public interface FoodAppUserDAO {

    Optional<FoodAppUser> findById(Long foodAppUserId);
}
