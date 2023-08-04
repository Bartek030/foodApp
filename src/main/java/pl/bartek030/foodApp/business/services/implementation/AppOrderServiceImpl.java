package pl.bartek030.foodApp.business.services.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.AppOrderDAO;
import pl.bartek030.foodApp.business.serviceModel.*;
import pl.bartek030.foodApp.business.services.*;
import pl.bartek030.foodApp.infrastructure.database.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AppOrderServiceImpl implements AppOrderService {

    private final AppOrderDAO appOrderDAO;

    private final OrderDetailsService orderDetailsService;
    private final FoodService foodService;
    private final FoodAppUserService foodAppUserService;
    private final RestaurantDeliveryAddressService restaurantDeliveryAddressService;
    private final RestaurantService restaurantService;

    @Override
    @Transactional
    public AppOrder addOrder(final List<OrderDetailsCreation> orderList) {
        final AppOrder newOrder = appOrderDAO.addAppOrder(buildNewAppOrder(orderList));
        final Set<OrderDetails> orderDetails = orderDetailsService.addOrders(orderList, newOrder);
        return newOrder.withOrderDetails(orderDetails);
    }

    @Override
    @Transactional
    public List<AppOrder> getOrdersByUser(final Long userId) {
        final FoodAppUser foodAppUser = foodAppUserService.findById(userId);
        return appOrderDAO.getAppOrdersByUserId(foodAppUser);
    }

    private AppOrder buildNewAppOrder(final List<OrderDetailsCreation> orderList) {
        Restaurant restaurant = restaurantService.findById(findRestaurantIdWhichServiceFood(orderList).getRestaurantId());
        final FoodAppUser foodAppUser = findFoodAppUser();

        return AppOrder.builder()
                .number(generateNewOrderNumber())
                .totalCost((calculateTotalCost(orderList)))
                .status(OrderStatus.ORDERED)
                .orderedAt(OffsetDateTime.now())
                .plannedDeliveryTime(checkPlannedDeliveryTime(restaurant, foodAppUser))
                .restaurant(restaurant)
                .foodAppUser(foodAppUser)
                .build();
    }

    private FoodAppUser findFoodAppUser() {
        return foodAppUserService.findById(1L);
    }

    private Restaurant findRestaurantIdWhichServiceFood(final List<OrderDetailsCreation> orderList) {
        return orderList.stream()
                .map(order -> {
                    final Food food = foodService.findFoodById(order.getFoodId());
                    return food.getMenu().getRestaurant();
                })
                // TODO: Custom exception
                .findFirst().orElseThrow();
    }

    private OffsetDateTime checkPlannedDeliveryTime(final Restaurant restaurant, final FoodAppUser foodAppUser) {
        final RestaurantDeliveryAddress deliveryAddress =
                restaurantDeliveryAddressService.findByAddressAndRestaurant(foodAppUser.getAddress(), restaurant);
        return OffsetDateTime.now().plusMinutes(deliveryAddress.getDeliveryTime());
    }

    private BigDecimal calculateTotalCost(final List<OrderDetailsCreation> orderList) {
        return orderList.stream()
                .map(orderDetailsService::calculateCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String generateNewOrderNumber() {
        Long numberOfAllOrders = appOrderDAO.countAllRecords();
        return "XXX-YYY-" + (numberOfAllOrders + 1);
    }
}
