package pl.bartek030.foodApp.business.services.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.AppOrderDAO;
import pl.bartek030.foodApp.business.serviceModel.*;
import pl.bartek030.foodApp.business.services.*;
import pl.bartek030.foodApp.business.util.OffsetDateTimeWrapper;
import pl.bartek030.foodApp.infrastructure.database.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppOrderServiceImpl implements AppOrderService {

    private final AppOrderDAO appOrderDAO;

    private final OrderDetailsService orderDetailsService;
    private final FoodService foodService;
    private final FoodAppUserService foodAppUserService;
    private final RestaurantDeliveryAddressService restaurantDeliveryAddressService;
    private final RestaurantService restaurantService;
    private final DeliveryAddressService deliveryAddressService;
    private final OffsetDateTimeWrapper offsetDateTimeWrapper;

    @Override
    @Transactional
    public AppOrder addOrder(final List<OrderDetailsCreation> orderList) {
        final AppOrder newOrder = appOrderDAO.addAppOrder(buildNewAppOrder(orderList));
        orderDetailsService.addOrders(orderList, newOrder);
        return newOrder;
    }

    @Override
    @Transactional
    public List<AppOrder> getOrdersByUser(final Long userId) {
        final FoodAppUser foodAppUser = foodAppUserService.findById(userId);
        return appOrderDAO.getAppOrdersByUser(foodAppUser);
    }

    @Override
    @Transactional
    public List<AppOrder> getOrdersByUser(final String email) {
        final FoodAppUser foodAppUser = foodAppUserService.findByEmail(email);
        return appOrderDAO.getAppOrdersByUser(foodAppUser);
    }

    @Override
    @Transactional
    public List<AppOrder> getOrdersByRestaurant(final Long restaurantId) {
        final Restaurant restaurant = restaurantService.findById(restaurantId);
        return appOrderDAO.getAppOrdersByRestaurant(restaurant);
    }

    @Override
    @Transactional
    public AppOrder cancelOrder(final Long appOrderId) {
        final AppOrder appOrder = appOrderDAO.findById(appOrderId).orElseThrow(
                () -> new RuntimeException("AppOrder with id: [%s] has not been found".formatted(appOrderId))
        );
        final OffsetDateTime now = offsetDateTimeWrapper.getCurrentTime();
        final OffsetDateTime orderedAt = appOrder.getOrderedAt();
        final OffsetDateTime timeToCancel = orderedAt.plusMinutes(20);
        if(now.isAfter(timeToCancel)) {
            throw new RuntimeException("You can cancel your order before 20 minutes from order time");
        }
        return appOrderDAO.update(appOrderId, OrderStatus.CANCELLED);
    }

    @Override
    @Transactional
    public AppOrder markAsDelivered(final Long appOrderId) {
        final AppOrder appOrder = appOrderDAO.findById(appOrderId)
                .orElseThrow(() -> new RuntimeException("Order with id: [%s] not found".formatted(appOrderId)));
        if(!OrderStatus.ORDERED.equals(appOrder.getStatus())) {
            throw new RuntimeException("Unable to change order status");
        }
        return appOrderDAO.update(appOrderId, OrderStatus.DELIVERED);
    }

    private AppOrder buildNewAppOrder(final List<OrderDetailsCreation> orderList) {
        Restaurant restaurant = restaurantService.findById(findRestaurantIdWhichServiceFood(orderList).getRestaurantId());
        final String email = SecurityContextHolder.getContext().getAuthentication().getName();
        final FoodAppUser foodAppUser = foodAppUserService.findByEmail(email);

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

    private Restaurant findRestaurantIdWhichServiceFood(final List<OrderDetailsCreation> orderList) {
        return orderList.stream()
                .map(order -> {
                    final Food food = foodService.findFoodById(order.getFoodId());
                    return food.getMenu().getRestaurant();
                })
                .findFirst().orElseThrow(() -> new RuntimeException("No restaurant has been found!"));
    }

    private OffsetDateTime checkPlannedDeliveryTime(final Restaurant restaurant, final FoodAppUser foodAppUser) {
        final DeliveryAddress deliveryAddress = deliveryAddressService.findByCountryAndCityAndStreet(
                        foodAppUser.getAddress().getCountry(),
                        foodAppUser.getAddress().getCity(),
                        foodAppUser.getAddress().getStreet())
                .orElseThrow(() -> new RuntimeException("Restaurant does not deliver food to your address"));
        final RestaurantDeliveryAddress restaurantDeliveryAddress =
                restaurantDeliveryAddressService.findByAddressAndRestaurant(deliveryAddress, restaurant);
        return OffsetDateTime.now().plusMinutes(restaurantDeliveryAddress.getDeliveryTime());
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