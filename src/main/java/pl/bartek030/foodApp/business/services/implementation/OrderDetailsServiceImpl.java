package pl.bartek030.foodApp.business.services.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.OrderDetailsDAO;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.serviceModel.Food;
import pl.bartek030.foodApp.business.serviceModel.OrderDetails;
import pl.bartek030.foodApp.business.serviceModel.OrderDetailsCreation;
import pl.bartek030.foodApp.business.services.FoodService;
import pl.bartek030.foodApp.business.services.OrderDetailsService;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsDAO orderDetailsDAO;
    private final FoodService foodService;

    @Override
    @Transactional
    public void addOrders(final List<OrderDetailsCreation> orderList, final AppOrder appOrder) {
        final List<OrderDetails> orderDetailsList = orderList.stream()
                .map(order -> OrderDetails.builder()
                        .quantity(order.getQuantity())
                        .food(foodService.findFoodById(order.getFoodId()))
                        .appOrder(appOrder)
                        .build())
                .toList();
        orderDetailsDAO.addAllOrderDetails(orderDetailsList);
    }

    @Override
    @Transactional
    public BigDecimal calculateCost(final OrderDetailsCreation orderDetail) {
        Food food = foodService.findFoodById(orderDetail.getFoodId());
        return BigDecimal.valueOf(orderDetail.getQuantity()).multiply(food.getPrice());
    }
}
