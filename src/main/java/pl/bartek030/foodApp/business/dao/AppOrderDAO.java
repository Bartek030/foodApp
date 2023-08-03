package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.AppOrder;

public interface AppOrderDAO {
    Long countAllRecords();

    AppOrder addAppOrder(AppOrder appOrder);
}
