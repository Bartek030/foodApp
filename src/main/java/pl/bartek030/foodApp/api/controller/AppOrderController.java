package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.AppOrderDTO;
import pl.bartek030.foodApp.api.dto.OrderDetailsCreationDTO;

import java.util.List;

@RequestMapping(AppOrderController.APP_ORDER_URL)
public interface AppOrderController {

    String APP_ORDER_URL = "/app-order";
    String MARK_DELIVERED_APP_ORDER_URL = "/delivered/{appOrderId}";
    String NEW_APP_ORDER_URL = "/new";
    String USER_APP_ORDER_ID = "/user";
    String RESTAURANT_APP_ORDER_ID = "/restaurant/{restaurantId}";

    @PostMapping(NEW_APP_ORDER_URL)
    String addOrder(@RequestBody List<OrderDetailsCreationDTO> orderDetailsCreationDTO);

    @GetMapping(RESTAURANT_APP_ORDER_ID)
    String getOrdersByRestaurant(@PathVariable Long restaurantId, final Model model);

    @PatchMapping(MARK_DELIVERED_APP_ORDER_URL)
    String markAsDelivered(@PathVariable Long orderId);

    @GetMapping(USER_APP_ORDER_ID)
    String getUsersAppOrders(final Model model);
}
