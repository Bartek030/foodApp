package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.serviceModel.OrderDetailsCreation;

import java.util.List;

public interface AppOrderService {
    AppOrder addOrder(List<OrderDetailsCreation> list);

    List<AppOrder> getOrdersByUser(Long userId);

    List<AppOrder> getOrdersByUser(String email);

    List<AppOrder> getOrdersByRestaurant(Long restaurantId);

    AppOrder cancelOrder(Long appOrderId);

    AppOrder markAsDelivered(Long appOrderId);
}
