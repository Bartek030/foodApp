package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.OrderDetails;

import java.util.List;

public interface OrderDetailsDAO {
    void addAllOrderDetails(List<OrderDetails> orderDetailsList);
}
