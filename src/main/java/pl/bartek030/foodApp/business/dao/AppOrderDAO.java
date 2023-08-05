package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.infrastructure.database.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface AppOrderDAO {
    Long countAllRecords();

    AppOrder addAppOrder(AppOrder appOrder);

    List<AppOrder> getAppOrdersByUserId(FoodAppUser foodAppUser);

    Optional<AppOrder> findById(Long appOrderId);

    AppOrder update(Long appOrderId, OrderStatus orderStatus);
}
