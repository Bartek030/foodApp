package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.serviceModel.OrderDetailsCreation;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDetailsService {

    BigDecimal calculateCost(OrderDetailsCreation orderDetails);

    void addOrders(List<OrderDetailsCreation> orderList, AppOrder appOrder);
}
