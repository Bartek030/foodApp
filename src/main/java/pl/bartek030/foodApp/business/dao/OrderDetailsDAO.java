package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.OrderDetails;

import java.util.List;
import java.util.Set;

public interface OrderDetailsDAO {
    Set<OrderDetails> addAllOrderDetails(List<OrderDetails> orderDetailsList);
}
