package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;

public interface FoodAppUserService {

    FoodAppUser findById(Long foodAppUserId);
}
