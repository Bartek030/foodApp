package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;

import java.util.List;

public interface AppOrderDAO {
    Long countAllRecords();

    AppOrder addAppOrder(AppOrder appOrder);

    List<AppOrder> getAppOrdersByUserId(FoodAppUser foodAppUser);
}
