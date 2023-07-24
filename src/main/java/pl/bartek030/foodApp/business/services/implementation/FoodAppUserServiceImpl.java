package pl.bartek030.foodApp.business.services.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.FoodAppUserDAO;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.business.services.FoodAppUserService;

@Service
@AllArgsConstructor
public class FoodAppUserServiceImpl implements FoodAppUserService {

    private final FoodAppUserDAO foodAppUserDao;

    @Override
    public FoodAppUser findById(final Long foodAppUserId) {
        // TODO: Custom exception
        return foodAppUserDao.findById(foodAppUserId).orElseThrow();
    }
}
