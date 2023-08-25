package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUserCreation;

public interface FoodAppUserService {

    FoodAppUser findById(Long foodAppUserId);

    void addUser(FoodAppUserCreation foodAppUserCreation);

    FoodAppUser findByEmail(String email);
}
