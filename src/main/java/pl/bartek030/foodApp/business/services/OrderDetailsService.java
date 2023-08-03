package pl.bartek030.foodApp.business.services;

import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.serviceModel.OrderDetails;
import pl.bartek030.foodApp.business.serviceModel.OrderDetailsCreation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface OrderDetailsService {

    BigDecimal calculateCost(OrderDetailsCreation orderDetails);

    Set<OrderDetails> addOrders(List<OrderDetailsCreation> orderList, AppOrder appOrder);
}
